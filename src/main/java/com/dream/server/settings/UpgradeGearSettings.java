package com.dream.server.settings;

import com.dream.container.anno.Config;
import lombok.Data;

import java.util.List;

@Data
@Config(classpath = "settings-upgrade-gears.json")
public class UpgradeGearSettings
{
    /**
     * 普通物品分解时的奖励
     */
    private List<CostItem> normal;

    /**
     * 高级物品分解时的奖励
     */
    private List<CostItem> advanced;

    /**
     * 稀有物品分解时的奖励
     */
    private List<CostItem> rare;

    /**
     * 史诗物品分解时的奖励
     */
    private List<CostItem> epic;

    /**
     * 传奇物品分解时的奖励
     */
    private List<CostItem> legendary;


    public static class CostItem
    {
        public int itemGuid;

        public int baseValue;

        public float var1 = 1.1f;
        
        public float var2 = 1.1f;

        /**
         * 多少级之后才会消耗这个材料
         */
        public int level;
    }

}
