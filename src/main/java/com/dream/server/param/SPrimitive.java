package com.dream.server.param;

import com.dream.service.codec.Packet;
import com.dream.service.codec.ParameterSaver;
import com.dream.service.utils.SerializeUtils;

public record SPrimitive<T>(T value) implements ParameterSaver
{
    @Override
    public void save(Packet packet)
    {
        SerializeUtils.savePrimitiveParam(value, packet);
    }
}
