package com.dream.server.param;

import lombok.Data;
import com.dream.service.codec.Packet;
import com.dream.service.codec.ParameterLoader;

@Data
public class LLogin implements ParameterLoader
{
    private String platform;

    private String ticket;

    @Override
    public String toString()
    {
        return "InLoginParameter{" +
                "platform='" + platform + '\'' +
                ", ticket='" + ticket + '\'' +
                '}';
    }

    @Override
    public void load(Packet packet)
    {
        platform = packet.readString();
        ticket = packet.readString();
    }
}
