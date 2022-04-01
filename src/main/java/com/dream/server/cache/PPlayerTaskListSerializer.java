package com.dream.server.cache;

import com.dream.server.param.PPlayerTaskList;

public class PPlayerTaskListSerializer extends ParameterSerializer<PPlayerTaskList>
{
    public PPlayerTaskListSerializer()
    {
    }

    public PPlayerTaskListSerializer(ClassLoader classLoader)
    {
    }

    @Override
    protected PPlayerTaskList newInstance()
    {
        return new PPlayerTaskList();
    }
}
