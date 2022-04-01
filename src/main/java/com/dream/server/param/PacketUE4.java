package com.dream.server.param;

import com.dream.server.settings.StaticSettings;
import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class PacketUE4 extends PacketNetty
{
    private static Charset UE4_Use_Charset;

    public PacketUE4(ByteBuf buf)
    {
        super(buf);

        if (UE4_Use_Charset == null)
        {
            UE4_Use_Charset = StaticSettings.UE4_Use_Charset;

            if (UE4_Use_Charset == null)
            {
                UE4_Use_Charset = StandardCharsets.UTF_8;
            }
        }
    }

    /**
     * bool 按四个字节整数算
     * @return  bool
     */
    @Override
    public boolean readBool()
    {
        return (buf.readIntLE() & 0xFF) == 1;
    }

    @Override
    public void writeBool(boolean value)
    {
        buf.writeIntLE(value ? 1 : 0);
    }

    @Override
    public String readString()
    {
        return readString(UE4_Use_Charset);
    }

    @Override
    public String readString(Charset charset)
    {
        int stringLength = buf.readIntLE();
        if (stringLength > 0)
        {
            byte[] bytes = new byte[stringLength];
            buf.readBytes(bytes);

            // C++ 传过来的字符串都是以0为结尾的，这在java中是不必要的，所以这里不要最后一个字节
            return new String(bytes, 0, bytes.length - 1, charset);
        }
        return null;
    }

    @Override
    public void writeString(String value)
    {
        writeString(value, UE4_Use_Charset);
    }

    @Override
    public void writeString(String value, Charset charset)
    {
        if (value == null)
        {
            buf.writeIntLE(0);
        }
        else
        {
            byte[] bytes = value.getBytes(charset);
            buf.writeIntLE(bytes.length + 1);
            buf.writeBytes(bytes);
            buf.writeByte(0); // 字符串结尾写个0
        }
    }
}
