package com.dream.server;

import com.dream.container.Initializer;
import com.dream.server.cache.EhCacheCacheDependenciesHandler;
import com.dream.container.anno.InitializeArgs;
import com.dream.server.impl.MybatisDatabaseManager;
import com.dream.server.sync.SynchronizerDependenceHandler;
import com.dream.service.ServiceContainer;
import org.apache.rocketmq.client.log.ClientLogger;

@InitializeArgs(
        databaseManager = MybatisDatabaseManager.class,
        dependenceHandlers = {
                SynchronizerDependenceHandler.class,
                EhCacheCacheDependenciesHandler.class
        },
        containers = {
                ServiceContainer.class
        }
)
public class Launcher
{
    public static void main(String[] args)
    {
        System.setProperty(ClientLogger.CLIENT_LOG_USESLF4J, "true");
        Initializer.initialize(Launcher.class, args);

        //System.out.println(DreamUtils.getResource("com/dream/container"));
    }
}
