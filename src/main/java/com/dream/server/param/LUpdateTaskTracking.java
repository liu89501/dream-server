package com.dream.server.param;

import lombok.Data;
import com.dream.service.codec.Packet;
import com.dream.service.codec.ParameterLoader;

@Data
public class LUpdateTaskTracking implements ParameterLoader
{
    private long ptid;

    private boolean tracking;

    @Override
    public void load(Packet packet)
    {
        ptid = packet.readLong();
        tracking = packet.readBool();
    }
}
