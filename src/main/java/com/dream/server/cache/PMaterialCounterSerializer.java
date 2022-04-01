package com.dream.server.cache;

import com.dream.server.param.PMaterialCounter;

public class PMaterialCounterSerializer extends ParameterSerializer<PMaterialCounter>
{
    public PMaterialCounterSerializer()
    {
    }

    public PMaterialCounterSerializer(ClassLoader classLoader)
    {
    }

    @Override
    protected PMaterialCounter newInstance()
    {
        return new PMaterialCounter();
    }
}
