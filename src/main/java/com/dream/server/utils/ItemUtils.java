package com.dream.server.utils;

/**
 *  32位整数 [1111 (预留暂未使用)] [1111 1111 (物品类型)] [1111 (物品品质)] [FF (编号)]
 */
public interface ItemUtils
{
    static int getItemType(int itemGuid)
    {
        return (itemGuid >>> 20) & 0xFF;
    }

    static int getItemQuality(int itemGuid)
    {
        return (itemGuid >>> 16) & 0xF;
    }
}
