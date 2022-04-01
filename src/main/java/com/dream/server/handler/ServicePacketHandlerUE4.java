package com.dream.server.handler;

import com.dream.container.anno.Component;
import com.dream.server.param.PacketUE4;
import com.dream.service.codec.Packet;
import com.dream.service.codec.ServicePacketHandler;
import io.netty.buffer.ByteBuf;

@Component(proxy = false, instant = true)
public class ServicePacketHandlerUE4 implements ServicePacketHandler
{
    @Override
    public Packet buildPacket(ByteBuf byteBuf)
    {
        return new PacketUE4(byteBuf);
    }
}
