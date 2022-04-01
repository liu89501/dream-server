package com.dream.server.param;

import com.dream.server.utils.PacketUtils;
import com.dream.service.codec.ParameterLoader;
import com.dream.service.codec.ParameterSaver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.dream.service.codec.Packet;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PPlayerMessage implements ParameterSaver, ParameterLoader
{
    /** @see EMessageType */
    private int type;

    private int playerId;

    /** message Data */
    private byte[] data;

    @Override
    public void load(Packet packet)
    {
        type = packet.readInt();
        playerId = packet.readInt();
        data = PacketUtils.loadBytes(packet);
    }

    @Override
    public void save(Packet packet)
    {
        packet.writeInt(type);
        packet.writeInt(playerId);
        PacketUtils.saveBytes(data, packet);
    }
}
