package com.dream.server.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.dream.service.codec.Packet;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class PConditionCount extends PCondition
{
    private int currentValue;

    private int targetValue;

    public int incrementAndGet()
    {
        return Math.min(++currentValue, targetValue);
    }

    @Override
    public void load(Packet packet)
    {
        super.load(packet);

        currentValue = packet.readInt();
        targetValue = packet.readInt();
    }

    @Override
    public void save(Packet packet)
    {
        super.save(packet);

        packet.writeInt(currentValue);
        packet.writeInt(targetValue);
    }

}
