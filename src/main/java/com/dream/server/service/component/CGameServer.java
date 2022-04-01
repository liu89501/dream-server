package com.dream.server.service.component;

import com.dream.container.anno.Assign;
import com.dream.container.anno.Component;
import com.dream.container.anno.Transaction;
import com.dream.server.config.ServiceMark;
import com.dream.server.database.mapper.*;
import com.dream.server.database.model.*;
import com.dream.server.param.*;
import com.dream.server.settings.ProjectSettings;
import com.dream.server.utils.ItemUtils;
import com.dream.server.utils.Logs;
import com.dream.server.utils.MyUtils;
import io.netty.channel.Channel;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class CGameServer
{
    @Assign
    private ServerInfoMapper serverInfoMapper;
    @Assign
    private PlayerWeaponMapper weaponMapper;
    @Assign
    private PlayerModuleMapper moduleMapper;
    @Assign
    private PlayerMapper playerMapper;

    @Assign
    private PlayerMaterialMapper materialMapper;

    @Assign
    private CPlayerDataCache playerDataCache;


    @Assign
    private CTaskUpdater taskUpdater;


    @Assign
    private CThreadPool threadPool;

    @Assign
    private ProjectSettings projectSettings;

    @Transaction
    public List<STaskInProgress> updatePlayerTaskState(LQuestActionHandle param)
    {
        if (Logs.LOG.isInfoEnabled())
        {
            Logs.LOG.info("updatePlayerTaskState: {}", param);
        }

        int playerId = param.getPlayerId();

        PPlayerTaskList acceptTask = playerDataCache.getAcceptTask(playerId);

        if (!acceptTask.isValid())
        {
            return null;
        }

        LQuestActionInfo actionInfo = param.getAction();

        if (actionInfo == null || !actionInfo.isValid())
        {
            return null;
        }

        List<PPlayerTask> pendingUpdate = new ArrayList<>();

        for (PPlayerTask task : acceptTask.getList())
        {
            List<PCondition> conditions = task.getTaskCondition().getConditions();

            boolean dirty = false;

            boolean taskCompleted = true;

            // start condition match
            for (PCondition condition : conditions)
            {
                int CID = actionInfo.getConditionID();

                if (CID != condition.getConditionID())
                {
                    continue;
                }

                if (condition.isCompleted())
                {
                    continue;
                }

                LQuestAction action = actionInfo.getQuestAction();

                switch (CID)
                {
                    case EConditionID.KILL_TARGET -> {

                        assert action instanceof LQuestActionKillTarget &&
                                condition instanceof PConditionKillTarget;

                        LQuestActionKillTarget actionKtCond = (LQuestActionKillTarget) action;
                        PConditionKillTarget ktCond = (PConditionKillTarget) condition;

                        if (actionKtCond.getTargetPawnType() == ktCond.getPawnType())
                        {
                            if (ktCond.getTargetValue() == ktCond.incrementAndGet())
                            {
                                ktCond.setCompleted(true);
                            }

                            dirty = true;

                            taskCompleted &= ktCond.isCompleted();
                        }
                    }
                    case EConditionID.EVENT_TRIGGER -> {

                        assert action instanceof LQuestActionEvent &&
                                condition instanceof PConditionEvent;

                        LQuestActionEvent actionEvent = (LQuestActionEvent) action;
                        PConditionEvent conditionEvent = (PConditionEvent) condition;

                        if (conditionEvent.getEventName().equals(actionEvent.getEventName()))
                        {
                            if (conditionEvent.getTargetValue() == conditionEvent.incrementAndGet())
                            {
                                conditionEvent.setCompleted(true);
                            }

                            dirty = true;

                            taskCompleted &= conditionEvent.isCompleted();
                        }
                    }
                    case EConditionID.ASSOC_TASK -> {


                    }
                }
            }
            // end condition match

            if (taskCompleted)
            {
                task.setTaskState((byte)ETaskState.Completed);
            }

            if (dirty)
            {
                pendingUpdate.add(task);
            }
        }

        if (pendingUpdate.size() > 0)
        {
            List<STaskInProgress> updatedTrackingTasks = pendingUpdate.stream()
                    .filter(PPlayerTask::isTaskTracking)
                    .map(e -> new STaskInProgress(e.getPtid(), e.getTaskCondition()))
                    .toList();

            playerDataCache.updateAcceptTaskCache(playerId, acceptTask);

            taskUpdater.markDirty(playerId);

            return updatedTrackingTasks;
        }

        return null;
    }

    @Transaction(batch = true)
    public void handleGenerateRewards(Player player, PItemList itemList, Channel notifyChannel)
    {
        if (itemList != null)
        {
            handleGenerateRewards(player, itemList.getItems(), notifyChannel);
        }
    }

    @Transaction(batch = true)
    public void handleGenerateRewards(Player player, List<PItem> items, Channel notifyChannel)
    {
        if (CollectionUtils.isEmpty(items))
        {
            return;
        }

        boolean playerDataChange = false;

        List<PPlayerMaterial> changedMaterials = null;

        for (PItem item : items)
        {
            int itemType = ItemUtils.getItemType(item.getItemGuid());

            switch (itemType)
            {
                case EItemType.Weapon -> {

                    PItemEquipment itemEquipment = (PItemEquipment) item;
                    PlayerWeapon playerWeapon = itemEquipment.castToPlayerWeapon(player.getPlayerId());
                    weaponMapper.insert(playerWeapon);
                }

                case EItemType.Module -> {

                    PItemEquipment itemEquipment = (PItemEquipment) item;
                    PlayerModule playerModule = itemEquipment.castToPlayerModule(player.getPlayerId());
                    moduleMapper.insert(playerModule);
                }

                case EItemType.Experience -> {

                    MyUtils.ExperienceResult result = MyUtils.calcExperience(player.getPlayerExp(), player.getMaxExp(),
                            player.getPlayerLevel(), ((PItemSimple) item).getNum());

                    player.setPlayerExp(result.newExp);
                    player.setMaxExp(result.newMaxExp);
                    player.setPlayerLevel(result.newLevel);

                    playerDataChange = true;
                }
                case EItemType.Material -> {

                    PItemSimple itemSimple = (PItemSimple) item;

                    PMaterialCounter materialCounter = playerDataCache.getMaterialCounter(player.getPlayerId());
                    int num = materialCounter.getNum(item.getItemGuid());

                    PlayerMaterial material = new PlayerMaterial();

                    if (changedMaterials == null)
                    {
                        changedMaterials = new ArrayList<>();
                    }

                    if (num > 0)
                    {
                        int newCount = materialCounter.add(itemSimple.getItemGuid(), itemSimple.getNum());

                        PlayerMaterialExample materialExample = new PlayerMaterialExample();
                        materialExample.createCriteria()
                                .andPlayerIdEqualTo(player.getPlayerId())
                                .andItemGuidEqualTo(itemSimple.getItemGuid());

                        material.setNum(newCount);

                        materialMapper.updateByExampleSelective(material, materialExample);
                    }
                    else
                    {
                        material.setNum(num);
                        material.setItemGuid(itemSimple.getItemGuid());
                        material.setPlayerId(player.getPlayerId());

                        materialMapper.insert(material);
                    }

                    changedMaterials.add(new PPlayerMaterial(item.getItemGuid(), material.getNum()));
                }
                case EItemType.Consumable -> {

                    // todo 待完善
                }
            }
        }

        if (playerDataChange)
        {
            playerMapper.updateByPrimaryKeySelective(player);

            if (notifyChannel != null)
            {
                CChannels.writeSpec(notifyChannel, ServiceMark.PLAYER_PROPERTY_UPDATE, new PPlayer(player));
            }
        }

        if (CollectionUtils.isNotEmpty(changedMaterials))
        {
            if (notifyChannel != null)
            {
                PMaterialCounter materialCounter = playerDataCache.getMaterialCounter(player.getPlayerId());
                List<PPlayerMaterial> materials = materialCounter.toMaterials();

                CChannels.writeSpec(notifyChannel, ServiceMark.MATERIALS_CHANGE, materials);
            }
        }
    }

}
