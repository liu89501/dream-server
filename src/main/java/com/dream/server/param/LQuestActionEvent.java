package com.dream.server.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.dream.service.codec.Packet;

@EqualsAndHashCode(callSuper = true)
@Data
public class LQuestActionEvent extends LQuestAction
{
    private String eventName;

    @Override
    public void load(Packet packet)
    {
        eventName = packet.readString();
    }
}
