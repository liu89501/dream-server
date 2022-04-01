package com.dream.server.param;

import com.dream.service.codec.Packet;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public record PacketJdk(ByteBuffer byteBuf) implements Packet
{
    @Override
    public int readInt()
    {
        return byteBuf.getInt();
    }

    @Override
    public long readLong()
    {
        return byteBuf.getLong();
    }

    @Override
    public byte readByte()
    {
        return byteBuf.get();
    }

    @Override
    public short readShort()
    {
        return byteBuf.getShort();
    }

    @Override
    public float readFloat()
    {
        return byteBuf.getFloat();
    }

    @Override
    public double readDouble()
    {
        return byteBuf.getDouble();
    }

    @Override
    public boolean readBool()
    {
        return byteBuf.get() == 1;
    }

    @Override
    public String readString()
    {
        return readString(StandardCharsets.UTF_8);
    }

    @Override
    public String readString(Charset charset)
    {
        int length = byteBuf.getInt();
        if (length > 0)
        {
            byte[] bytes = new byte[length];
            byteBuf.get(bytes);
            return new String(bytes, charset);
        }
        return null;
    }

    @Override
    public void readBytes(byte[] bytes)
    {
        byteBuf.get(bytes);
    }

    @Override
    public int available()
    {
        return byteBuf.remaining();
    }

    @Override
    public void writeInt(int value)
    {
        byteBuf.putInt(value);
    }

    @Override
    public void writeLong(long value)
    {
        byteBuf.putLong(value);
    }

    @Override
    public void writeByte(byte value)
    {
        byteBuf.put(value);
    }

    @Override
    public void writeShort(short value)
    {
        byteBuf.putShort(value);
    }

    @Override
    public void writeFloat(float value)
    {
        byteBuf.putFloat(value);
    }

    @Override
    public void writeDouble(double value)
    {
        byteBuf.putDouble(value);
    }

    @Override
    public void writeBool(boolean value)
    {
        byteBuf.put((byte)(value ? 1 : 0));
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
            byteBuf.putInt(bytes.length);
            byteBuf.put(bytes);
        }
        else
        {
            byteBuf.putInt(0);
        }
    }

    @Override
    public void writeBytes(byte[] bytes)
    {
        byteBuf.put(bytes);
    }
}
