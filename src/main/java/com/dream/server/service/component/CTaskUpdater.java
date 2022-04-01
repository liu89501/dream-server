package com.dream.server.service.component;

import com.dream.container.anno.Assign;
import com.dream.container.anno.Component;
import com.dream.container.anno.Exec;

import java.util.HashSet;
import java.util.concurrent.locks.ReentrantLock;

@Component(proxy = false)
public class CTaskUpdater
{
    @Assign
    private CThreadPool threadPool;

    @Assign
    private CTaskUpdaterExecutor executor;

    private ReentrantLock dirtyHandleLock = new ReentrantLock();

    private HashSet<Integer> acceptTaskDirty = new HashSet<>();

    @Exec
    public void exec()
    {
        threadPool.scheduleWithFixedDelay(this::handle, 60);
    }

    public void markDirty(int playerId)
    {
        dirtyHandleLock.lock();
        try
        {
            acceptTaskDirty.add(playerId);
        }
        finally
        {
            dirtyHandleLock.unlock();
        }
    }

    @SuppressWarnings("unchecked")
    public void handle()
    {
        HashSet<Integer> temporary = null;

        dirtyHandleLock.lock();
        try
        {
            if (acceptTaskDirty.size() > 0)
            {
                temporary = (HashSet<Integer>)acceptTaskDirty.clone();
                acceptTaskDirty.clear();
            }
        }
        finally
        {
            dirtyHandleLock.unlock();
        }

        if (temporary != null)
        {
            executor.execute(temporary);
        }
    }
}
