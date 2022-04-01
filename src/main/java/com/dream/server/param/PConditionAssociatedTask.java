package com.dream.server.param;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.dream.service.codec.Packet;

@JsonTypeName("ast")
@EqualsAndHashCode(callSuper = true)
@Data
public class PConditionAssociatedTask extends PCondition
{
    private int associatedTaskId;

    @Override
    public void load(Packet packet)
    {
        super.load(packet);

        associatedTaskId = packet.readInt();
    }

    @Override
    public void save(Packet packet)
    {
        super.save(packet);

        packet.writeInt(associatedTaskId);
    }
}
