package com.dream.server.param;

import lombok.Data;
import com.dream.service.codec.Packet;
import com.dream.service.codec.ParameterLoader;

@Data
public class LQuestActionHandle implements ParameterLoader
{
    private int playerId;

    private LQuestActionInfo action;

    @Override
    public void load(Packet packet)
    {
        playerId = packet.readInt();

        action = new LQuestActionInfo();
        action.load(packet);
    }
}
