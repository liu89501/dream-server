package com.dream.server.param;

public interface EPlayerInfoQueryCondition
{
    int Weapon = 0x1;

    int Weapon_Equipped = 0x2;

    int Module = 0x4;

    int Module_Equipped = 0x8;

    int Materials = 0x10;

    int Skin = 0x20;

    static boolean Contains(int condition, int conditionMark)
    {
        return (condition & conditionMark) == conditionMark;
    }
}
