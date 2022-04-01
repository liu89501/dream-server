package com.dream.server.database;

import com.dream.server.param.PConditionList;

public class TaskConditionTypeHandler extends ParameterBaseTypeHandler<PConditionList>
{
    public TaskConditionTypeHandler()
    {
        super(256, true);
    }
}
