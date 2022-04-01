package com.dream.server.database;

import com.dream.server.param.PItemList;

public class ItemListTypeHandler extends ParameterBaseTypeHandler<PItemList>
{
    public ItemListTypeHandler()
    {
        super(256, true);
    }
}
