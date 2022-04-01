package com.dream.server.service.component;

import com.dream.container.anno.Assign;
import com.dream.container.anno.Component;
import com.dream.server.utils.Logs;
import com.dream.server.utils.PacketUtils;
import com.dream.server.param.PacketUE4;
import com.dream.service.bound.RoughingMessage;
import com.dream.service.codec.ParameterSaver;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

@Component(proxy = false)
public class CChannels
{
    private final HashMap<Integer, Channel> playerChannels = new HashMap<>();

    private final HashMap<String, ScheduledFuture<?>> schedules = new HashMap<>();

    @Assign
    private CThreadPool threadPool;

    public void add(int playerId, Channel channel)
    {
        playerChannels.put(playerId, channel);
    }

    public Channel get(int playerId)
    {
        return playerChannels.get(playerId);
    }

    public void remove(int playerId)
    {
        playerChannels.remove(playerId);
    }

    public boolean isValid(int playerId)
    {
        return playerChannels.containsKey(playerId);
    }

    public void deferredWrite(int playerId, int mark, ParameterSaver parameter, int second)
    {
        String scheduleKey = getScheduleKey(playerId, mark);

        ScheduledFuture<?> scheduledFuture = schedules.get(scheduleKey);

        if (scheduledFuture != null)
        {
            scheduledFuture.cancel(true);
        }

        ScheduledFuture<?> schedule = threadPool.schedule(() -> writeMessageAndClearSchedule(playerId, mark, parameter), second);
        schedules.put(scheduleKey, schedule);

        if (Logs.LOG.isInfoEnabled())
        {
            Logs.LOG.info("deferredWrite: {}", schedules.size());
        }
    }

    private void writeMessageAndClearSchedule(int playerId, int mark, ParameterSaver parameter)
    {
        writeMessage(playerId, mark, parameter);
        schedules.remove(getScheduleKey(playerId, mark));

        if (Logs.LOG.isInfoEnabled())
        {
            Logs.LOG.info("clear: {}", schedules.size());
        }
    }

    private String getScheduleKey(int playerId, int mark)
    {
        return playerId + "_" + mark;
    }

    public void writeMessage(int playerId, int mark, ParameterSaver parameter)
    {
        Channel channel = playerChannels.get(playerId);
        if (channel != null)
        {
            ByteBuf buffer = channel.alloc().buffer();
            PacketUE4 packetUE4 = new PacketUE4(buffer);
            parameter.save(packetUE4);
            channel.writeAndFlush(new RoughingMessage(mark, buffer));
        }
    }

    public void writeMessage(int playerId, int mark, byte[] bytes)
    {
        Channel channel = playerChannels.get(playerId);
        if (channel != null)
        {
            channel.writeAndFlush(new RoughingMessage(mark, Unpooled.wrappedBuffer(bytes)));
        }
    }

    public <T extends ParameterSaver> void writeMessage(int playerId, int mark, List<T> list)
    {
        Channel channel = playerChannels.get(playerId);
        if (channel != null)
        {
            ByteBuf buffer = channel.alloc().buffer();
            PacketUE4 packetUE4 = new PacketUE4(buffer);
            PacketUtils.saveList(list, packetUE4);
            channel.writeAndFlush(new RoughingMessage(mark, buffer));
        }
    }

    public static void writeSpec(Channel channel, int mark, ParameterSaver param)
    {
        ByteBuf buffer = channel.alloc().buffer();
        PacketUE4 packetUE4 = new PacketUE4(buffer);
        param.save(packetUE4);
        channel.writeAndFlush(new RoughingMessage(mark, buffer));
    }

    public static <T extends ParameterSaver> void writeSpec(Channel channel, int mark, List<T> param)
    {
        ByteBuf buffer = channel.alloc().buffer();
        PacketUE4 packetUE4 = new PacketUE4(buffer);
        PacketUtils.saveList(param, packetUE4);
        channel.writeAndFlush(new RoughingMessage(mark, buffer));
    }
}
