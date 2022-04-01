package com.dream.server.config;

public interface ServiceMark
{
    int PLAYER_PROPERTY_UPDATE = 23;

    // 参数 BoundItemList
    int REWARDS_NOTIFY = 24;

    int LAUNCHED_NOTIFY_SERVER = 6;
    int NOTIFY_SERVER_READY = 7;

    int SEARCH_DEDICATED_SERVER = 3;

    int UPDATE_TASK_STATE = 14;
    int UPDATE_TRACKING_TASKS = 9;

    int STORE_ITEMS = 19;
    int STORE_BUY_ITEM = 20;

    int TALENT_QUERY = 21;
    int LEARNING_TALENTS = 17;

    // 分解物品
    int DECOMPOSE_ITEM = 25;

    // 查询材料
    int MATERIALS_CHANGE = 26;

    // 升级装备
    int UPGRADE_WEAPON = 28;
    int UPGRADE_MODULE = 29;
}
