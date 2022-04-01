package com.dream.server.service.component;

import com.dream.container.anno.Component;

import java.util.concurrent.*;

@Component(proxy = false)
public class CThreadPool
{
    private final ExecutorService executePool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private final ScheduledExecutorService schedulePool = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());

    public void execute(Runnable runnable)
    {
        executePool.execute(runnable);
    }

    public ScheduledFuture<?> schedule(Runnable runnable, int second)
    {
        return schedulePool.schedule(runnable, second, TimeUnit.SECONDS);
    }

    /** 这个是从任务开始时算时间 */
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long period)
    {
        return schedulePool.scheduleAtFixedRate(runnable, period, period, TimeUnit.SECONDS);
    }

    /** 这个是从任务结束时算时间 */
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long period)
    {
        return schedulePool.scheduleWithFixedDelay(runnable, period, period, TimeUnit.SECONDS);
    }
}
