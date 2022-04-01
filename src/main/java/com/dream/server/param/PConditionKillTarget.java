package com.dream.server.param;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.dream.service.codec.Packet;

@JsonTypeName("kt")
@EqualsAndHashCode(callSuper = true)
@Data
public class PConditionKillTarget extends PConditionCount
{
    private byte pawnType;

    @Override
    public void load(Packet packet)
    {
        super.load(packet);

        pawnType = packet.readByte();
    }

    @Override
    public void save(Packet packet)
    {
        super.save(packet);

        packet.writeByte(pawnType);
    }
}
