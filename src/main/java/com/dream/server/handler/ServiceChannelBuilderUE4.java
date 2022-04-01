package com.dream.server.handler;

import com.dream.container.anno.Component;
import com.dream.service.ServiceChannelBuilder;
import io.netty.channel.ChannelPipeline;

@Component(proxy = false, instant = true)
public class ServiceChannelBuilderUE4 implements ServiceChannelBuilder
{
    @Override
    public void buildChannel(ChannelPipeline channelPipeline)
    {
        channelPipeline.addLast(new UE4PacketDecoder());
        channelPipeline.addLast(new UE4PacketEncoder());
    }
}
