package com.dream.server.service.component;

import com.dream.container.anno.LaunchArg;
import com.dream.server.config.ServiceMark;
import com.dream.container.anno.Assign;
import com.dream.container.anno.Component;
import com.dream.server.database.mapper.ServerInfoMapper;
import com.dream.server.param.LLaunchServer;
import com.dream.server.param.PServerNotify;
import com.dream.server.param.SPrimitive;
import com.dream.server.settings.ProjectSettings;
import com.dream.server.sync.Sync;
import com.dream.server.sync.Synchronizer;
import com.dream.service.codec.SuccessOrFailure;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ScheduledFuture;

@Component(proxy = false)
public class CServerLauncher
{
    private final HashMap<String, PServerNotify> notifies = new HashMap<>();

    @Assign
    private CThreadPool threadPool;

    @Assign
    private CChannels channels;

    @Assign
    private ServerInfoMapper serverInfoMapper;

    @Assign
    private ProjectSettings projectSettings;

    @LaunchArg("-use_dev_env")
    private boolean useDevEnvironment;

    @Sync("ServerLauncher")
    private Synchronizer sync;

    /**
     * 等待专用服务器初始化
     * @param serverUID  serverID
     * @param playerId  启动成功后要通知的玩家ID
     */
    public void launchServerProcess(String serverUID, int playerId, LLaunchServer param) throws IOException
    {
        String serverToken = projectSettings.getServerToken();
        String applicationPath = projectSettings.getServerApplicationPath();

        // 80秒后专用服务器还没反应就通知客户端启动失败
        ScheduledFuture<?> schedule = threadPool.schedule(() -> {

            channels.writeMessage(playerId, ServiceMark.SEARCH_DEDICATED_SERVER, SuccessOrFailure.FAIL);
            notifies.remove(serverUID);

        }, 60);

        notifies.put(serverUID, new PServerNotify(playerId, schedule));

        Runtime.getRuntime().exec(String.format(
                "%s %s?Game=%s -QueryPort=0 -ServerToken=%s -ServerId=%s -OwningPlayerId=%s %s",
                applicationPath, param.getMapAssetPath(), param.getModeName(), serverToken,
                serverUID, playerId, useDevEnvironment ? " -log" : ""));
    }

    /**
     * 通知的玩家
     * @param serverID  id
     */
    public void notifyOwningPlayer(String serverID, String serverAddr)
    {
        if (notifies.containsKey(serverID))
        {
            PServerNotify notifyParam = notifies.remove(serverID);

            // 取消等待的异步任务
            ScheduledFuture<?> timerHandle = notifyParam.getServerTimerHandle();

            if (timerHandle.isDone())
            {
                return;
            }

            timerHandle.cancel(true);

            SuccessOrFailure outParam = SuccessOrFailure.buildSuccess(new SPrimitive<>(serverAddr));
            channels.writeMessage(notifyParam.getPlayerId(), ServiceMark.SEARCH_DEDICATED_SERVER, outParam);
        }
    }
}
