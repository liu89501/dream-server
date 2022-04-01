package com.dream.server.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.dream.service.codec.Packet;

@EqualsAndHashCode(callSuper = true)
@Data
public class PAcquisitionCost extends ParameterArchive
{
    private int itemGuid;

    private int costAmount;

    @Override
    public void load(Packet packet)
    {
        itemGuid = packet.readInt();
        costAmount = packet.readInt();
    }

    @Override
    public void save(Packet packet)
    {
        packet.writeInt(itemGuid);
        packet.writeInt(costAmount);
    }
}
