package com.dream.server.param;

import lombok.Data;
import com.dream.service.codec.Packet;
import com.dream.service.codec.ParameterLoader;

@Data
public class LGameServerNotify implements ParameterLoader
{
    private String serverId;

    private int port;

    private int playerId;

    private int maxPlayers;

    @Override
    public void load(Packet packet)
    {
        serverId = packet.readString();
        port = packet.readInt();
        playerId = packet.readInt();
        maxPlayers = packet.readInt();
    }
}
