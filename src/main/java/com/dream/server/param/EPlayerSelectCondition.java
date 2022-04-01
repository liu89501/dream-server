package com.dream.server.param;

public enum EPlayerSelectCondition
{
    ALL(0),
    Equipped(1),
    UnEquipped(2);

    private final int value;

    EPlayerSelectCondition(int value)
    {
        this.value = value;
    }

    public static EPlayerSelectCondition get(int value)
    {
        for (EPlayerSelectCondition condition : values())
        {
            if (condition.value == value)
            {
                return condition;
            }
        }

        return EPlayerSelectCondition.ALL;
    }

    public int getValue()
    {
        return value;
    }
}
