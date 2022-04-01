package com.dream.server.utils;

import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public abstract class Logs
{
    public static final Logger LOG = LoggerFactory.getLogger("dream");

    public static void printBuffer(ByteBuf byteBuf)
    {
        if (LOG.isInfoEnabled())
        {
            if (byteBuf != null)
            {
                int readableBytes = byteBuf.readableBytes();

                byte[] bytes = new byte[readableBytes];
                byteBuf.getBytes(0, bytes);

                LOG.info("buffer: {}", Arrays.toString(bytes));
            }
        }
    }
}
