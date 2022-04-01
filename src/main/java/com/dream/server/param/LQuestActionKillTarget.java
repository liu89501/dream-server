package com.dream.server.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.dream.service.codec.Packet;

@EqualsAndHashCode(callSuper = true)
@Data
public class LQuestActionKillTarget extends LQuestAction
{
    private byte targetPawnType;

    @Override
    public void load(Packet packet)
    {
        targetPawnType = packet.readByte();
    }
}
