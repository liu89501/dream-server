package com.dream.server.cache;

import com.dream.server.param.PItemList;

public class PItemListSerializer extends ParameterSerializer<PItemList>
{
    public PItemListSerializer()
    {
    }

    public PItemListSerializer(ClassLoader classLoader)
    {
    }

    @Override
    protected PItemList newInstance()
    {
        return new PItemList();
    }
}
