package com.dream.server.service.component;

import com.dream.container.anno.Assign;
import com.dream.container.anno.Component;
import com.dream.container.anno.Transaction;
import com.dream.server.database.mapper.PlayerMaterialMapper;
import com.dream.server.database.mapper.PlayerModuleMapper;
import com.dream.server.database.mapper.PlayerWeaponMapper;
import com.dream.server.database.model.PlayerMaterial;
import com.dream.server.database.model.PlayerMaterialExample;
import com.dream.server.param.EItemQuality;
import com.dream.server.param.PMaterialCounter;
import com.dream.server.param.PPlayerMaterial;
import com.dream.server.param.SUpgradeResult;
import com.dream.server.settings.UpgradeGearSettings;
import com.dream.server.utils.Logs;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.math3.analysis.UnivariateFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CGearsUpgrade
{
    @Assign
    private CPlayerDataCache playerDataCache;

    @Assign
    private PlayerWeaponMapper weaponMapper;

    @Assign
    private PlayerModuleMapper moduleMapper;

    @Assign
    private PlayerMaterialMapper materialMapper;

    @Assign(uid = "upgrade_curve")
    private UnivariateFunction upgradeCurve;
    
    @Assign
    private UpgradeGearSettings upgradeGearSettings;


    @Transaction(batch = true)
    public SUpgradeResult handleUpgrade(int currentLevel, int itemQuality, int playerId)
    {
        SUpgradeResult upgradeResult = new SUpgradeResult();
        upgradeResult.setUpgradeSuccess(false);

        List<UpgradeGearSettings.CostItem> costs = switch (itemQuality)
                {
                    case EItemQuality.Normal -> upgradeGearSettings.getNormal();
                    case EItemQuality.Advanced -> upgradeGearSettings.getAdvanced();
                    case EItemQuality.Rare -> upgradeGearSettings.getRare();
                    case EItemQuality.Epic -> upgradeGearSettings.getEpic();
                    case EItemQuality.Legendary -> upgradeGearSettings.getLegendary();
                    default -> null;
                };

        if (CollectionUtils.isEmpty(costs))
        {
            return upgradeResult;
        }

        PMaterialCounter materialCounter = playerDataCache.getMaterialCounter(playerId);

        boolean materialCheck = true;

        HashMap<Integer, Integer> costMats = new HashMap<>();

        for (UpgradeGearSettings.CostItem cost : costs)
        {
            if (cost.level > currentLevel)
            {
                // 低于配置等级的不算
                continue;
            }

            int eval = eval(currentLevel, cost.baseValue, cost.var1, cost.var2);
            if (materialCounter.getNum(cost.itemGuid) < eval)
            {
                materialCheck = false;
                break;
            }

            costMats.put(cost.itemGuid, eval);
        }

        if (!materialCheck)
        {
            return upgradeResult;
        }

        List<PPlayerMaterial> consumeMaterials = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : costMats.entrySet())
        {
            Integer itemGuid = entry.getKey();
            int newNum = materialCounter.subtract(itemGuid, entry.getValue());

            PlayerMaterial playerMaterial = new PlayerMaterial();
            playerMaterial.setNum(newNum);

            PlayerMaterialExample materialExample = new PlayerMaterialExample();
            materialExample.createCriteria()
                    .andPlayerIdEqualTo(playerId)
                    .andItemGuidEqualTo(itemGuid);

            materialMapper.updateByExampleSelective(playerMaterial, materialExample);

            consumeMaterials.add(new PPlayerMaterial(itemGuid, newNum));
        }

        upgradeResult.setTaskCondition(consumeMaterials);

        double value = upgradeCurve.value(currentLevel);
        upgradeResult.setUpgradeSuccess(Math.random() <= value);

        if (Logs.LOG.isInfoEnabled())
        {
            Logs.LOG.info("当前几率: {}, 结果: {}", value, upgradeResult.isUpgradeSuccess());
        }

        return upgradeResult;
    }

    private int eval(int level, int base, float var1, float var2)
    {
        double v = base * Math.pow(var1, level * var2);
        return (int)Math.ceil(v) & Integer.MAX_VALUE;
    }
}
