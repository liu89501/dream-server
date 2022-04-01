package com.dream.server.service;

import com.dream.container.anno.Assign;
import com.dream.server.config.ServiceMark;
import com.dream.server.database.model.Player;
import com.dream.server.param.*;
import com.dream.server.service.component.CChannels;
import com.dream.server.service.component.CGameServer;
import com.dream.server.service.component.CPlayerDataCache;
import com.dream.server.service.component.CServerLauncher;
import com.dream.server.settings.ProjectSettings;
import com.dream.server.utils.Logs;
import com.dream.server.utils.MyUtils;
import com.dream.service.anno.DedicatedServer;
import com.dream.service.anno.Mark;
import com.dream.service.anno.Service;
import com.dream.service.auth.AuthenticatedInfo;
import com.dream.service.bound.AuthenticateHandler;
import com.dream.service.codec.SuccessOrFailure;
import io.netty.channel.Channel;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.List;
import java.util.UUID;

@Service
public class GameServerService
{
    @Assign
    private ProjectSettings projectSettings;

    @Assign
    private CServerLauncher serverLauncher;

    @Assign
    private CChannels channels;

    @Assign
    private CGameServer gsComponent;

    @Assign
    private CPlayerDataCache playerDataCache;


    @Mark(value = 1, authenticate = false)
    public SuccessOrFailure serverLogin(String token, Channel channel)
    {
        if (projectSettings.getServerToken().equals(token))
        {
            // serverId 一般情况下在下一步设置
            channel.attr(AuthenticateHandler.AUTHORIZATION_KEY).set(new AuthenticatedInfo(0, null, true));

            return SuccessOrFailure.SUCCESS;
        }

        return SuccessOrFailure.FAIL;
    }

    @Mark(value = ServiceMark.SEARCH_DEDICATED_SERVER)
    public void runDedicatedServer(LLaunchServer param, AuthenticatedInfo info)
    {
        try
        {
            String serverUID = UUID.randomUUID().toString();
            serverLauncher.launchServerProcess(serverUID, info.getPlayerId(), param);
        }
        catch (Exception e)
        {
            Logs.LOG.error("runDedicatedServer error", e);

            channels.writeMessage(info.getPlayerId(), ServiceMark.SEARCH_DEDICATED_SERVER, SuccessOrFailure.FAIL);
        }
    }


    /**
     * 专用服务器进程启动完成时会通知到这里。 这个是由 getOrCreateServer 这里创建出来的专用服务器进程触发的
     * @param param     一些参数
     * @param channel   connect channel
     */
    @DedicatedServer
    @Mark(value = ServiceMark.LAUNCHED_NOTIFY_SERVER)
    public void receiveDSReadyNotify(LGameServerNotify param, Channel channel)
    {
        String serverAddress = getClientIpAddress(channel) + ':' + param.getPort();

        AuthenticatedInfo info = channel.attr(AuthenticateHandler.AUTHORIZATION_KEY).get();
        info.setServerId(param.getServerId());

        serverLauncher.notifyOwningPlayer(param.getServerId(), serverAddress);
    }

    private String getClientIpAddress(Channel channel)
    {
        String addressString = null;

        SocketAddress socketAddress = channel.remoteAddress();
        if (socketAddress instanceof InetSocketAddress inetSocketAddress)
        {
            InetAddress address = inetSocketAddress.getAddress();
            if (address.isAnyLocalAddress() || address.isLoopbackAddress())
            {
                addressString = "127.0.0.1";
            }
            else
            {
                addressString = address.getHostAddress();
            }
        }
        return addressString;
    }


    @DedicatedServer
    @Mark(ServiceMark.UPDATE_TASK_STATE)
    public void updatePlayerTaskState(LQuestActionHandle param)
    {
        List<STaskInProgress> updatedTrackingTasks = gsComponent.updatePlayerTaskState(param);
        if (MyUtils.isValidCollection(updatedTrackingTasks))
        {
            channels.writeMessage(param.getPlayerId(), ServiceMark.UPDATE_TRACKING_TASKS, updatedTrackingTasks);

            if (Logs.LOG.isInfoEnabled())
            {
                Logs.LOG.info("updatedTrackingTasks: {}", updatedTrackingTasks);
            }
        }
    }

    @DedicatedServer
    @Mark(value = 11)
    public void addPlayerRewards(LItemHandle handle)
    {
        Player playerData = playerDataCache.getPlayerData(handle.getPlayerId());
        gsComponent.handleGenerateRewards(playerData, handle.getReward(), channels.get(handle.getPlayerId()));
        channels.writeMessage(handle.getPlayerId(), ServiceMark.REWARDS_NOTIFY, handle.getReward());
    }
}
