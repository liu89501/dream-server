package com.dream.server.param;

import lombok.Data;
import com.dream.service.codec.Packet;
import com.dream.service.codec.ParameterLoader;

@Data
public class LItemHandle implements ParameterLoader
{
    private int playerId;

    private PItemList reward;

    @Override
    public void load(Packet packet)
    {
        playerId = packet.readInt();

        reward = new PItemList();
        reward.load(packet);
    }
}
