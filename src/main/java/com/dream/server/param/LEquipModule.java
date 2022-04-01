package com.dream.server.param;

import com.dream.service.codec.Packet;
import com.dream.service.codec.ParameterLoader;
import com.dream.service.codec.ParameterSaver;
import lombok.Data;

@Data
public class LEquipModule implements ParameterLoader, ParameterSaver
{
    private long moduleId;
    private byte category;

    @Override
    public void save(Packet packet)
    {
        packet.writeLong(moduleId);
        packet.writeByte(category);
    }

    @Override
    public void load(Packet packet)
    {
        moduleId = packet.readLong();
        category = packet.readByte();
    }
}
