package com.dream.server.param;

import lombok.Data;
import com.dream.service.codec.Packet;
import com.dream.service.codec.ParameterLoader;

@Data
public class LLaunchServer implements ParameterLoader
{
    private String mapAssetPath;
    private String modeName;

    @Override
    public void load(Packet packet)
    {
        mapAssetPath = packet.readString();
        modeName = packet.readString();
    }
}
