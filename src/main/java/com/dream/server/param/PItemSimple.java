package com.dream.server.param;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.dream.service.codec.Packet;

/**
 * 无属性的物品
 */
@JsonTypeName("num")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PItemSimple extends PItem
{
    /**
     * 物品数量
     */
    private int num;

    public PItemSimple(int itemGuid, int num)
    {
        super(itemGuid);
        this.num = num;
    }

    @Override
    public void load(Packet packet)
    {
        super.load(packet);
        num = packet.readInt();
    }

    @Override
    public void save(Packet packet)
    {
        super.save(packet);
        packet.writeInt(num);
    }
}
