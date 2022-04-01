package com.dream.server.settings;

import com.dream.container.anno.Config;
import lombok.Data;

import java.util.List;

@Data
@Config(classpath = "settings-decompose.json")
public class DecomposeSettings
{
    /**
     * 普通物品分解时的奖励
     */
    private List<DecomposeRewards> normal;

    /**
     * 高级物品分解时的奖励
     */
    private List<DecomposeRewards> advanced;

    /**
     * 稀有物品分解时的奖励
     */
    private List<DecomposeRewards> rare;

    /**
     * 史诗物品分解时的奖励
     */
    private List<DecomposeRewards> epic;

    /**
     * 传奇物品分解时的奖励
     */
    private List<DecomposeRewards> legendary;


    public static class DecomposeRewards
    {
        public int itemGuid;

        public int minNum;

        public int maxNum;

        @Override
        public String toString()
        {
            return String.format("[%d(%d ~ %d)]", itemGuid, minNum, maxNum);
        }
    }


}
