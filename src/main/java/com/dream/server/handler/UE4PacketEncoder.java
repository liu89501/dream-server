package com.dream.server.handler;

import com.dream.service.bound.RoughingMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;


public class UE4PacketEncoder extends MessageToByteEncoder<RoughingMessage>
{
    @Override
    protected void encode(ChannelHandlerContext ctx, RoughingMessage msg, ByteBuf out) throws Exception
    {
        // 4 表示是 Message中的 mark 占 4个字节
        int msgLength = msg.getParameterData().readableBytes() + 4;

        out.writeIntLE(msgLength);
        out.writeIntLE(msg.getServiceMark());
        out.writeBytes(msg.getParameterData());
    }
}
