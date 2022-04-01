package com.dream.server.param;

import com.dream.service.codec.Packet;
import com.dream.service.codec.ParameterLoader;
import com.dream.service.codec.ParameterSaver;
import lombok.Data;

@Data
public class LEquipWeapon implements ParameterSaver,ParameterLoader
{
    private long weaponId;
    private int equipmentIndex;

    @Override
    public void save(Packet packet)
    {
        packet.writeLong(weaponId);
        packet.writeInt(equipmentIndex);
    }

    @Override
    public void load(Packet packet)
    {
        weaponId = packet.readLong();
        equipmentIndex = packet.readInt();
    }
}
