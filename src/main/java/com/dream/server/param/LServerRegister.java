package com.dream.server.param;

import lombok.Data;
import com.dream.service.codec.Packet;
import com.dream.service.codec.ParameterLoader;

@Data
public class LServerRegister implements ParameterLoader
{
    private int port;
    private int maxPlayers;
    private String mapName;
    private String gameModeName;

    @Override
    public void load(Packet packet)
    {
        port = packet.readInt();
        maxPlayers = packet.readInt();
        mapName = packet.readString();
        gameModeName = packet.readString();
    }
}
