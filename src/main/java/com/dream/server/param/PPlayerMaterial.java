package com.dream.server.param;

import com.dream.server.database.model.PlayerMaterial;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.dream.service.codec.Packet;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PPlayerMaterial extends ParameterArchive
{
    private int itemGuid;

    private int num;

    public PPlayerMaterial(PlayerMaterial material)
    {
        this.itemGuid = material.getItemGuid();
        this.num = material.getNum();
    }

    @Override
    public void load(Packet packet)
    {
        itemGuid = packet.readInt();
        num = packet.readInt();
    }

    @Override
    public void save(Packet packet)
    {
        packet.writeInt(itemGuid);
        packet.writeInt(num);
    }
}
