package com.dream.server.param;

import com.dream.server.database.model.Player;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.dream.service.codec.Packet;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class PPlayer extends ParameterArchive
{
    private int playerLevel;

    private int playerExp;

    private int maxExp;

    public PPlayer(Player player)
    {
        playerLevel = player.getPlayerLevel();
        playerExp = player.getPlayerExp();
        maxExp = player.getMaxExp();
    }

    @Override
    public void load(Packet packet)
    {
        playerLevel = packet.readInt();
        playerExp = packet.readInt();
        maxExp = packet.readInt();
    }

    @Override
    public void save(Packet packet)
    {
        packet.writeInt(playerLevel);
        packet.writeInt(playerExp);
        packet.writeInt(maxExp);
    }
}
