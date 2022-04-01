package com.dream.server.param;

import com.dream.server.utils.PacketUtils;
import lombok.Data;
import com.dream.service.codec.Packet;
import com.dream.service.codec.ParameterLoader;
import com.dream.service.codec.ParameterSaver;

import java.util.List;

@Data
public class PRequestPlayerInfo implements ParameterSaver, ParameterLoader
{
    private List<PPlayerWeapon> weapons;

    private List<PPlayerModule> modules;

    private long learnedTalents;

    private int level;

    private String characterMeshPath;

    private List<PPlayerMaterial> materials;

    @Override
    public void save(Packet packet)
    {
        PacketUtils.saveList(weapons, packet);
        PacketUtils.saveList(modules, packet);
        packet.writeLong(learnedTalents);
        packet.writeInt(level);
        PacketUtils.saveList(materials, packet);
        packet.writeString(characterMeshPath);
    }

    @Override
    public void load(Packet packet)
    {
        PacketUtils.loadList(PPlayerWeapon::new, packet);
        PacketUtils.loadList(PPlayerModule::new, packet);

        learnedTalents = packet.readLong();
        level = packet.readInt();

        PacketUtils.loadList(PPlayerMaterial::new, packet);

        characterMeshPath = packet.readString();
    }
}
