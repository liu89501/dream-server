package com.dream.server.param;

import lombok.Data;
import com.dream.service.codec.Packet;
import com.dream.service.codec.ParameterLoader;

@Data
public class LQueryPlayerInfo implements ParameterLoader
{
    private int playerId;

    private int condition;

    @Override
    public void load(Packet packet)
    {
        playerId = packet.readInt();
        condition = packet.readInt();
    }
}
