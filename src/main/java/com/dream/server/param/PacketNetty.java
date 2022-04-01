package com.dream.server.param;

import com.dream.service.codec.Packet;
import com.dream.server.utils.Logs;
import io.netty.buffer.ByteBuf;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class PacketNetty implements Packet
{
    protected ByteBuf buf;
    
    public PacketNetty(ByteBuf buf)
    {
        this.buf = buf;
    }

    @Override
    public int readInt()
    {
        return readable(4) ? buf.readIntLE() : 0;
    }

    @Override
    public long readLong()
    {
        return readable(8) ? buf.readLongLE() : 0;
    }

    @Override
    public byte readByte()
    {
        return readable(1) ? buf.readByte() : 0;
    }

    @Override
    public short readShort()
    {
        return readable(2) ? buf.readShortLE() : 0;
    }

    @Override
    public float readFloat()
    {
        return readable(4) ? buf.readFloatLE() : 0;
    }

    @Override
    public double readDouble()
    {
        return readable(8) ? buf.readDoubleLE() : 0;
    }

    @Override
    public boolean readBool()
    {
        return readable(1) && buf.readByte() == 1;
    }

    @Override
    public String readString()
    {
        return readString(StandardCharsets.UTF_8);
    }

    @Override
    public String readString(Charset charset)
    {
        int length = readInt();
        if (length > 0)
        {
            byte[] bytes = new byte[length];
            buf.readBytes(bytes);
            return new String(bytes, charset);
        }
        return null;
    }

    @Override
    public void readBytes(byte[] bytes)
    {
        if (readable(bytes.length))
        {
            buf.readBytes(bytes);
        }
    }

    @Override
    public int available()
    {
        return buf.readableBytes();
    }

    @Override
    public void writeInt(int value)
    {
        buf.writeIntLE(value);
    }

    @Override
    public void writeLong(long value)
    {
        buf.writeLongLE(value);
    }

    @Override
    public void writeByte(byte value)
    {
        buf.writeByte(value);
    }

    @Override
    public void writeShort(short value)
    {
        buf.writeShortLE(value);
    }

    @Override
    public void writeFloat(float value)
    {
        buf.writeFloatLE(value);
    }

    @Override
    public void writeDouble(double value)
    {
        buf.writeDoubleLE(value);
    }

    @Override
    public void writeBool(boolean value)
    {
        buf.writeByte(value ? 1 : 0);
    }

    @Override
    public void writeString(String value)
    {
        writeString(value, StandardCharsets.UTF_8);
    }

    @Override
    public void writeString(String value, Charset charset)
    {
        if (value != null && value.length() > 0)
        {
            byte[] bytes = value.getBytes(charset);
            buf.writeIntLE(bytes.length);
            buf.writeBytes(bytes);
        }
        else
        {
            buf.writeIntLE(0);
        }
    }

    @Override
    public void writeBytes(byte[] bytes)
    {
        buf.writeBytes(bytes);
    }

    private boolean readable(int bytesLength)
    {
        if (buf.readerIndex() > buf.writerIndex() - bytesLength)
        {
            Logs.LOG.info("read out bound");
            return false;
        }

        return true;
    }
}
