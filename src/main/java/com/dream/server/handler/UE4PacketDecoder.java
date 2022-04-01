package com.dream.server.handler;

import com.dream.service.bound.RoughingMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class UE4PacketDecoder extends ByteToMessageDecoder
{
    private boolean readyReadData;

    private int dataLength;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception
    {
        if (readyReadData)
        {
            if (msg.readableBytes() >= dataLength)
            {
                ByteBuf byteBuf = msg.readBytes(dataLength);
                int Mark = byteBuf.readIntLE();

                dataLength = 0;
                readyReadData = false;

                RoughingMessage message = new RoughingMessage();
                message.setServiceMark(Mark);
                message.setParameterData(byteBuf);

                out.add(message);
            }
        }
        else
        {
            if (msg.readableBytes() >= 4)
            {
                dataLength = msg.readIntLE();

                if (dataLength > 0)
                {
                    readyReadData = true;
                }
                else
                {
                    out.add(msg);
                }
            }
        }
    }
}
