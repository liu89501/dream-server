package com.dream.server.service.component;

import com.dream.container.anno.Assign;
import com.dream.container.anno.Component;
import com.dream.container.anno.Transaction;
import com.dream.server.config.Constant;
import com.dream.server.config.ServiceMark;
import com.dream.server.database.mapper.*;
import com.dream.server.database.model.*;
import com.dream.server.param.*;
import com.dream.server.settings.ProjectSettings;
import com.dream.server.utils.*;
import com.dream.service.auth.AuthenticatedInfo;
import com.dream.service.bound.AuthenticateHandler;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import io.netty.channel.Channel;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class CPlayer
{
    @Assign
    private PlayerWeaponMapper weaponMapper;
    @Assign
    private PlayerModuleMapper moduleMapper;
    @Assign
    private PlayerMapper playerMapper;
    @Assign
    private PlayerTaskMapper playerTaskMapper;
    @Assign
    private TaskTemplateMapper taskTemplateMapper;
    @Assign
    private GameStoreItemMapper storeItemMapper;
    @Assign
    private PlayerMaterialMapper playerMaterialMapper;


    @Assign
    private CChannels channels;

    @Assign
    private CPlayerDataCache playerCache;

    @Assign
    private CDecomposeItem decomposer;

    @Assign
    private CGearsUpgrade gearsUpgrade;

    @Assign
    private CGameServer cGameServer;

    @Assign
    private MongoDatabase mongoDatabase;

    @Assign
    private ProjectSettings projectSettings;


    /**
     * 获取玩家当前所连接的 server 的 instanceUID
     */
    public String getPlayerCurrentServerInstanceUID(int playerId)
    {
        MongoCollection<Document> collection = mongoDatabase.getCollection("Players");
        Document document = collection.find(Filters.eq("player_id", playerId)).first();
        return document == null ? null : document.getString("instance_id");
    }

    /**
     * 玩家登录逻辑
     * @param param     登录参数
     * @return          返回null标识登录验证失败, 否则成功
     */
    @Transaction
    public Player login(LLogin param, Channel channel)
    {
        if (Logs.LOG.isInfoEnabled())
        {
            Logs.LOG.info("player login, {}", param.toString());
        }

        Authenticate authenticate;

        if (ClientPlatform.STEAM.equal(param.getPlatform()))
        {
            // todo 平台校验功能待完善
            //authenticate = SteamAuthenticates.authenticate(parameter.getTicket());
            authenticate = new Authenticate();
            authenticate.setAccountId("AAAAAA");
            authenticate.setAuthorized(true);
        }
        else
        {
            authenticate = null;
        }

        if (authenticate == null || !authenticate.isAuthorized())
        {
            return null;
        }

        Player player;

        PlayerExample example = new PlayerExample();
        example.createCriteria().andPlatformAccountIdEqualTo(authenticate.getAccountId());
        List<Player> players = playerMapper.selectByExample(example);
        if (players == null || players.size() == 0)
        {
            player = new Player();
            player.setPlatformAccountId(authenticate.getAccountId());
            player.setPlayerExp(0);
            player.setMaxExp(MyUtils.getMaxExperience(1));
            player.setPlayerLevel(1);
            player.setLearnedTalents(0L);
            player.setLastLoginDate(new Date());
            //player.setLoginHost(projectSettings.getInstanceGUID());
            playerMapper.insert(player);

            TaskTemplateExample templateExample = new TaskTemplateExample();
            List<TaskTemplateWithBLOBs> templateWithBLOBs = taskTemplateMapper.selectByExampleWithBLOBs(templateExample);

            for (TaskTemplateWithBLOBs template : templateWithBLOBs)
            {
                PlayerTask task = new PlayerTask();
                task.setTaskState((byte) ETaskState.NotAccept);
                task.setTaskCondition(template.getTaskCondition());
                task.setTaskId(template.getTaskId());
                task.setPlayerId(player.getPlayerId());
                task.setTaskTracking(false);

                playerTaskMapper.insert(task);
            }

            for (PlayerWeapon weapon : MyUtils.defaultWeapons(player.getPlayerId()))
            {
                weaponMapper.insert(weapon);
            }

            for (PlayerModule module : MyUtils.defaultModules(player.getPlayerId()))
            {
                moduleMapper.insert(module);
            }
        }
        else
        {
            player = players.get(0);
        }

        channels.add(player.getPlayerId(), channel);

        // 通知UE4 玩家信息更新
        CChannels.writeSpec(channel, ServiceMark.PLAYER_PROPERTY_UPDATE, new PPlayer(player));


        // 材料更新通知
        PMaterialCounter materialCounter = playerCache.getMaterialCounter(player.getPlayerId());
        CChannels.writeSpec(channel, ServiceMark.MATERIALS_CHANGE, materialCounter.toMaterials());

        try
        {
            Player updatePlayer = new Player();
            updatePlayer.setPlayerId(player.getPlayerId());
            updatePlayer.setLastLoginDate(new Date());
            playerMapper.updateByPrimaryKeySelective(updatePlayer);

            UpdateOptions updateOptions = new UpdateOptions();
            updateOptions.upsert(true);

            mongoDatabase.getCollection("Players")
                    .updateOne(Filters.eq("player_id", player.getPlayerId()),
                            Updates.set("instance_id", projectSettings.getInstanceUID()),
                            updateOptions);
        }
        catch (Exception e)
        {
            Logs.LOG.warn("更新玩家登录信息失败", e);
        }

        channel.attr(AuthenticateHandler.AUTHORIZATION_KEY).setIfAbsent(new AuthenticatedInfo(player.getPlayerId(), null, false));

        if (Logs.LOG.isInfoEnabled())
        {
            Logs.LOG.info("登录成功: {}, {}", player.getPlayerId(), player.getPlatformAccountId());
        }

        return player;
    }


    public PRequestPlayerInfo queryPlayerInfo(int playerId, int condition)
    {
        PRequestPlayerInfo requestPlayerInfo = new PRequestPlayerInfo();

        Player player = playerCache.getPlayerData(playerId);
        requestPlayerInfo.setLearnedTalents(player.getLearnedTalents());
        requestPlayerInfo.setLevel(player.getPlayerLevel());

        if (EPlayerInfoQueryCondition.Contains(condition, EPlayerInfoQueryCondition.Weapon))
        {
            PlayerWeaponExample weaponExample = new PlayerWeaponExample();
            PlayerWeaponExample.Criteria weaponCriteria = weaponExample.createCriteria();

            weaponCriteria.andPlayerIdEqualTo(playerId);

            if (EPlayerInfoQueryCondition.Contains(condition, EPlayerInfoQueryCondition.Weapon_Equipped))
            {
                weaponCriteria.andEquippedEqualTo(true);
            }

            weaponExample.setLimit(Constant.DEFAULT_EQUIPMENT_NUM);

            List<PPlayerWeapon> bPlayerWeapons = weaponMapper.selectByExampleWithBLOBs(weaponExample)
                    .stream()
                    .map(PPlayerWeapon::new)
                    .toList();

            requestPlayerInfo.setWeapons(bPlayerWeapons);
        }

        if (EPlayerInfoQueryCondition.Contains(condition, EPlayerInfoQueryCondition.Module))
        {
            PlayerModuleExample moduleExample = new PlayerModuleExample();
            PlayerModuleExample.Criteria moduleCriteria = moduleExample.createCriteria();
            moduleCriteria.andPlayerIdEqualTo(playerId);

            if (EPlayerInfoQueryCondition.Contains(condition, EPlayerInfoQueryCondition.Module_Equipped))
            {
                moduleCriteria.andEquippedEqualTo(true);
            }

            moduleExample.setLimit(Constant.DEFAULT_EQUIPMENT_NUM);
            List<PPlayerModule> playerModules = moduleMapper.selectByExampleWithBLOBs(moduleExample)
                    .stream()
                    .map(PPlayerModule::new)
                    .toList();

            requestPlayerInfo.setModules(playerModules);
        }

        if (EPlayerInfoQueryCondition.Contains(condition, EPlayerInfoQueryCondition.Materials))
        {
            PMaterialCounter counter = playerCache.getMaterialCounter(playerId);
            requestPlayerInfo.setMaterials(counter.toMaterials());
        }

        if (EPlayerInfoQueryCondition.Contains(condition, EPlayerInfoQueryCondition.Skin))
        {
            // todo 测试 待修改
            requestPlayerInfo.setCharacterMeshPath(Constant.DEFAULT_SKIN);
        }

        return requestPlayerInfo;
    }


    /**
     * 装备武器逻辑
     * @param param     参数
     * @param info      登录认证信息
     */
    @Transaction(batch = true)
    public PlayerWeapon equipWeapon(LEquipWeapon param, AuthenticatedInfo info)
    {
        if (Logs.LOG.isInfoEnabled())
        {
            Logs.LOG.info("equipWeapon: {}", param);
        }

        PlayerWeapon weapon = new PlayerWeapon();
        weapon.setEquipped(false);
        weapon.setEquipmentIndex(0);

        PlayerWeaponExample example1 = new PlayerWeaponExample();
        example1.createCriteria()
                .andEquippedEqualTo(true)
                .andPlayerIdEqualTo(info.getPlayerId())
                .andEquipmentIndexEqualTo(param.getEquipmentIndex());

        weaponMapper.updateByExampleSelective(weapon, example1);

        weapon.setWeaponId(param.getWeaponId());
        weapon.setEquipped(true);
        weapon.setEquipmentIndex(param.getEquipmentIndex());

        weaponMapper.updateByPrimaryKeySelective(weapon);

        return weaponMapper.selectByPrimaryKey(param.getWeaponId());
    }

    @Transaction(batch = true)
    public PlayerModule equipModule(LEquipModule param, AuthenticatedInfo info)
    {
        PlayerModule module = new PlayerModule();
        module.setEquipped(false);

        PlayerModuleExample moduleExample = new PlayerModuleExample();
        moduleExample.createCriteria()
                .andEquippedEqualTo(true)
                .andPlayerIdEqualTo(info.getPlayerId())
                .andModuleCategoryEqualTo((int) param.getCategory());

        moduleMapper.updateByExampleSelective(module, moduleExample);

        module.setModuleId(param.getModuleId());
        module.setEquipped(true);
        module.setModuleCategory((int)param.getCategory());

        moduleMapper.updateByPrimaryKeySelective(module);

        return moduleMapper.selectByPrimaryKey(param.getModuleId());
    }


    public SPageData<PPlayerTask> requestTaskList(LRequestTask param, AuthenticatedInfo info)
    {
        PPlayerTaskList acceptTask = playerCache.getAcceptTask(info.getPlayerId());

        if (param.getCondition() == EGetTaskCondition.UnSubmit)
        {
            int totalPage = Math.max(acceptTask.size() / Constant.TASK_MAX_QUERY_NUM, 1);

            int startIdx = param.getPage() * Constant.TASK_MAX_QUERY_NUM;
            int endIdx = Math.min(startIdx + Constant.TASK_MAX_QUERY_NUM, acceptTask.size());

            List<PPlayerTask> subTask = acceptTask.getList().subList(startIdx, endIdx);
            return new SPageData<>(totalPage, acceptTask.size(), subTask);
        }
        else if (param.getCondition() == EGetTaskCondition.Tracking)
        {
            if (acceptTask.isNotEmpty())
            {
                List<PPlayerTask> trackingTasks = acceptTask.stream()
                        .filter(PPlayerTask::isTaskTracking)
                        .toList();

                return new SPageData<>(0, 0, trackingTasks);
            }

            return new SPageData<>(0, 0, null);
        }


        PlayerTaskExample taskExample = new PlayerTaskExample();
        PlayerTaskExample.Criteria criteria = taskExample.createCriteria();
        criteria.andPlayerIdEqualTo(info.getPlayerId());

        if (param.getTaskGroupId() > 0)
        {
            criteria.andGroupIdEqualTo(param.getTaskGroupId());
        }

        switch (param.getCondition())
        {
            case EGetTaskCondition.UnSubmit -> criteria.andTaskStateIn(List.of(ETaskState.Completed, ETaskState.Accepted));
            case EGetTaskCondition.Submitted -> criteria.andTaskStateEqualTo(ETaskState.Submitted);
            case EGetTaskCondition.NotAccept -> criteria.andTaskStateEqualTo(ETaskState.NotAccept);
            case EGetTaskCondition.Tracking -> criteria.andTaskStateEqualTo(ETaskState.Accepted).andTaskTrackingEqualTo(true);
        }

        int count = (int) playerTaskMapper.countByExample(taskExample);
        int totalPage = Math.max(count / Constant.TASK_MAX_QUERY_NUM, 1);

        taskExample.setLimit(Constant.TASK_MAX_QUERY_NUM);
        taskExample.setOffset((long) param.getPage());
        taskExample.setOrderByClause("ptid");

        List<PPlayerTask> playerTasks = playerTaskMapper.selectByExampleWithBLOBs(taskExample)
                .stream()
                .map(PPlayerTask::new)
                .toList();

        return new SPageData<>(totalPage, count, playerTasks);
    }


    /**
     * 接受任务
     * @param ptid      任务id
     * @param info      玩家信息
     * @return          T/F
     */
    @Transaction
    public boolean acceptTask(long ptid, AuthenticatedInfo info)
    {
        PlayerTaskExample playerTaskExample = new PlayerTaskExample();
        playerTaskExample.createCriteria()
                .andPtidEqualTo(ptid)
                .andPlayerIdEqualTo(info.getPlayerId());

        PlayerTask playerTask = new PlayerTask();
        playerTask.setTaskState((byte) ETaskState.Accepted);

        if (playerTaskMapper.updateByExampleSelective(playerTask, playerTaskExample) > 0)
        {
            PPlayerTaskList acceptTask = playerCache.getAcceptTask(info.getPlayerId());
            acceptTask.add(new PPlayerTask(playerTaskMapper.selectByPrimaryKey(ptid)));
            playerCache.updateAcceptTaskCache(info.getPlayerId(), acceptTask);

            return true;
        }

        return false;
    }


    @Transaction
    public boolean updateTrackingState(LUpdateTaskTracking param, AuthenticatedInfo info)
    {
        Logs.LOG.info("updateTrackingState param: {}", param);

        PlayerTaskExample taskExample = new PlayerTaskExample();
        taskExample.createCriteria()
                .andPlayerIdEqualTo(info.getPlayerId())
                .andTaskTrackingEqualTo(true);

        long count = playerTaskMapper.countByExample(taskExample);

        if (count >= Constant.TASK_MAX_TRACKING)
        {
            Logs.LOG.info("updateTrackingState failure count: {}", count);

            return false;
        }

        PPlayerTaskList acceptTask = playerCache.getAcceptTask(info.getPlayerId());
        Optional<PPlayerTask> optional = acceptTask.stream()
                .filter(e -> e.getPtid() == param.getPtid())
                .findAny();

        optional.ifPresent(playerTask -> playerTask.setTaskTracking(param.isTracking()));
        playerCache.updateAcceptTaskCache(info.getPlayerId(), acceptTask);

        PlayerTask task = new PlayerTask();
        task.setPtid(param.getPtid());
        task.setTaskTracking(param.isTracking());
        playerTaskMapper.updateByPrimaryKeySelective(task);

        return true;
    }


    @Transaction(batch = true)
    public boolean deliverTask(long ptid, int playerId, Channel channel)
    {
        Logs.LOG.info("deliverTask: {}", ptid);

        PPlayerTaskList acceptTask = playerCache.getAcceptTask(playerId);

        Optional<PPlayerTask> playerTask = acceptTask.stream()
                .filter(e -> e.getTaskState() == ETaskState.Completed)
                .filter(e -> e.getPtid() == ptid)
                .findAny();

        PPlayerTask queryTask = playerTask.orElse(null);

        if (queryTask == null)
        {
            return false;
        }

        PlayerTask update = new PlayerTask();
        update.setPtid(ptid);
        update.setTaskState((byte) ETaskState.Submitted);

        if (playerTaskMapper.updateByPrimaryKeySelective(update) == 0)
        {
            return false;
        }

        acceptTask.remove(queryTask);
        playerCache.updateAcceptTaskCache(playerId, acceptTask);

        Player playerData = playerCache.getPlayerData(playerId);

        cGameServer.handleGenerateRewards(playerData, queryTask.getTaskReward(), channel);

        return true;
    }


    public SPageData<PStoreItem> requestStoreItems(int storeId, int page)
    {
        GameStoreItemExample example = new GameStoreItemExample();
        example.createCriteria()
                .andStoreIdEqualTo(storeId);

        example.setLimit(Constant.STORE_MAX_QUERY_NUM);
        example.setOffset((long) page);

        int totalItem = (int) storeItemMapper.countByExample(example);
        List<PStoreItem> storeItems = storeItemMapper.selectByExampleWithBLOBs(example)
                .stream()
                .map(PStoreItem::new)
                .toList();

        int totalPage = Math.max(0, totalItem / Constant.STORE_MAX_QUERY_NUM);

        return new SPageData<>(totalPage, totalItem, storeItems);
    }


    /**
     * 购买商店物品
     * @param itemId    物品id
     * @param channel   Netty Channel
     * @return          玩家的 已产生变化的材料
     */
    @Transaction(batch = true)
    public ResultBuyItem buyStoreItem(long itemId, int playerId, Channel channel)
    {
        Player playerData = playerCache.getPlayerData(playerId);

        GameStoreItemWithBLOBs storeItem = storeItemMapper.selectByPrimaryKey(itemId);

        List<PAcquisitionCost> costs = storeItem.getItemCostData().getCosts();

        PMaterialCounter counter = playerCache.getMaterialCounter(playerId);

        List<PPlayerMaterial> changedMats = new ArrayList<>();

        ResultBuyItem buyItemResult = new ResultBuyItem();
        buyItemResult.setChangedMaterials(changedMats);

        for (PAcquisitionCost cost : costs)
        {
            int itemCount = counter.getNum(cost.getItemGuid());

            if (cost.getCostAmount() <= itemCount)
            {
                int newNum = counter.subtract(cost.getItemGuid(), cost.getCostAmount());

                PlayerMaterial material = new PlayerMaterial();
                material.setNum(newNum);

                PlayerMaterialExample example = new PlayerMaterialExample();
                example.createCriteria()
                        .andPlayerIdEqualTo(playerId)
                        .andItemGuidEqualTo(cost.getItemGuid());
                playerMaterialMapper.updateByExampleSelective(material, example);

                changedMats.add(new PPlayerMaterial(cost.getItemGuid(), newNum));
            }
            else
            {
                Logs.LOG.warn("购买物品的条件不足: guid,{}", cost.getItemGuid());
                buyItemResult.setSuccess(false);
                return buyItemResult;
            }
        }

        cGameServer.handleGenerateRewards(playerData, new PItemList(storeItem.getItemData()), channel);

        buyItemResult.setSuccess(true);
        return buyItemResult;
    }


    public long requestTalent(byte talentCategoryId, AuthenticatedInfo info)
    {
        Player playerData = playerCache.getPlayerData(info.getPlayerId());
        return playerData.getLearnedTalents();
    }


    @Transaction
    public boolean learnTalents(long learnedTalents, AuthenticatedInfo info)
    {
        Player playerData = playerCache.getPlayerData(info.getPlayerId());
        playerData.setLearnedTalents(learnedTalents);

        Player updatePlayer = new Player();
        updatePlayer.setPlayerId(playerData.getPlayerId());
        updatePlayer.setLearnedTalents(learnedTalents);

        playerMapper.updateByPrimaryKeySelective(updatePlayer);

        return true;
    }


    @Transaction(batch = true)
    public ResultDecomposeGear decomposeEquipment(int itemGuid, long itemId, AuthenticatedInfo info)
    {
        ResultDecomposeGear result = new ResultDecomposeGear();

        int itemType = ItemUtils.getItemType(itemGuid);

        switch (itemType)
        {
            case EItemType.Weapon -> {
                PlayerWeapon playerWeapon = weaponMapper.selectByPrimaryKey(itemId);
                if (playerWeapon != null && !playerWeapon.getEquipped())
                {
                    weaponMapper.deleteByPrimaryKey(playerWeapon.getWeaponId());
                }
            }
            case EItemType.Module -> {

                PlayerModule playerModule = moduleMapper.selectByPrimaryKey(itemId);
                if (playerModule != null && !playerModule.getEquipped())
                {
                    moduleMapper.deleteByPrimaryKey(playerModule.getModuleId());
                }
            }
        }

        List<PPlayerMaterial> materials = decomposer.decompose(itemGuid);

        if (MyUtils.isValidCollection(materials))
        {
            List<PPlayerMaterial> changedMats = new ArrayList<>();
            PItemList decomposeRewards = new PItemList();

            PMaterialCounter materialCounter = playerCache.getMaterialCounter(info.getPlayerId());

            for (PPlayerMaterial material : materials)
            {
                int itemCount = materialCounter.getNum(material.getItemGuid());

                PlayerMaterial updaterMat = new PlayerMaterial();
                updaterMat.setItemGuid(material.getItemGuid());

                if (itemCount > 0)
                {
                    int newCount = materialCounter.add(material.getItemGuid(), material.getNum());

                    updaterMat.setNum(newCount);

                    PlayerMaterialExample materialExample = new PlayerMaterialExample();
                    materialExample.createCriteria()
                            .andPlayerIdEqualTo(info.getPlayerId())
                            .andItemGuidEqualTo(material.getItemGuid());

                    playerMaterialMapper.updateByExampleSelective(updaterMat, materialExample);
                }
                else
                {
                    updaterMat.setNum(material.getNum());
                    updaterMat.setPlayerId(info.getPlayerId());

                    playerMaterialMapper.insert(updaterMat);
                }

                changedMats.add(new PPlayerMaterial(updaterMat));
                decomposeRewards.add(new PItemSimple(material.getItemGuid(), material.getNum()));
            }

            result.setDecomposeRewards(decomposeRewards);
            result.setChangedMaterials(changedMats);
            result.setSuccess(true);

        }
        else
        {
            result.setSuccess(false);
        }

        return result;
    }


    @Transaction(batch = true)
    public SUpgradeResult upgradeWeapon(long weaponId, AuthenticatedInfo info)
    {
        PlayerWeapon playerWeapon = weaponMapper.selectByPrimaryKey(weaponId);

        int currentLevel = playerWeapon.getLevel();

        int itemQuality = ItemUtils.getItemQuality(playerWeapon.getItemGuid());

        SUpgradeResult upgradeResult = gearsUpgrade.handleUpgrade(currentLevel, itemQuality, info.getPlayerId());
        if (upgradeResult.isUpgradeSuccess())
        {
            PlayerWeapon temp = new PlayerWeapon();
            temp.setWeaponId(weaponId);
            temp.setLevel((byte)(playerWeapon.getLevel() + 1));
            weaponMapper.updateByPrimaryKeySelective(temp);
        }

        return upgradeResult;
    }


    @Transaction(batch = true)
    public SUpgradeResult upgradeModule(long moduleId, AuthenticatedInfo info)
    {
        PlayerModule playerModule = moduleMapper.selectByPrimaryKey(moduleId);
        int currentLevel = playerModule.getLevel();

        int itemQuality = ItemUtils.getItemQuality(playerModule.getItemGuid());

        SUpgradeResult upgradeResult = gearsUpgrade.handleUpgrade(currentLevel, itemQuality, info.getPlayerId());

        if (upgradeResult.isUpgradeSuccess())
        {
            PlayerModule temp = new PlayerModule();
            temp.setModuleId(playerModule.getModuleId());
            temp.setLevel((byte)(playerModule.getLevel() + 1));
            moduleMapper.updateByPrimaryKeySelective(temp);
        }

        return upgradeResult;
    }
}
