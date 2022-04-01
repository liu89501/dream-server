package com.dream.server.param;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.dream.service.codec.Packet;

@JsonTypeName("evt")
@EqualsAndHashCode(callSuper = true)
@Data
public class PConditionEvent extends PConditionCount
{
    private String eventName;

    @Override
    public void load(Packet packet)
    {
        super.load(packet);
        eventName = packet.readString();
    }

    @Override
    public void save(Packet packet)
    {
        super.save(packet);
        packet.writeString(eventName);
    }
}
