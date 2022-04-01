package com.dream.server.database;

import com.dream.server.param.PIntArray;

public class NIntArrayTypeHandler extends ParameterBaseTypeHandler<PIntArray>
{
    public NIntArrayTypeHandler()
    {
        super(32, true);
    }

    @Override
    public PIntArray createInstance()
    {
        return new PIntArray();
    }
}
