package com.dream.server.param;

import lombok.Data;
import com.dream.service.codec.Packet;
import com.dream.service.codec.ParameterLoader;

@Data
public class LRequestTask implements ParameterLoader
{
    private byte condition;

    private int taskGroupId;

    private int page;

    @Override
    public void load(Packet packet)
    {
        condition = packet.readByte();
        taskGroupId = packet.readInt();
        page = packet.readInt();
    }
}
