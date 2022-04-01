package com.dream.server.cache;

import com.dream.server.param.PTemporaryItems;

public class LTemporaryItemsSerializer extends ParameterSerializer<PTemporaryItems>
{
    public LTemporaryItemsSerializer()
    {
    }

    public LTemporaryItemsSerializer(ClassLoader classLoader)
    {
    }

    @Override
    protected PTemporaryItems newInstance()
    {
        return new PTemporaryItems();
    }
}
