package com.dream.server.sync;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantLock;

public class Synchronizer
{
    static final ThreadLocal<SynchronizerDeferredArgs> deferredMarks = new ThreadLocal<>();
    private final ConcurrentMap<Object, Boolean> conditionSyncMarks = new ConcurrentHashMap<>();

    private final ConcurrentMap<Object, ReentrantLock> conditionLocks = new ConcurrentHashMap<>();

    private final String category;

    public Synchronizer(String category)
    {
        Objects.requireNonNull(category, "category is null");
        this.category = category;
    }

    /**
     * 获取特定的Key的同步标记
     * @param key key
     * @return  Y/N
     */
    public boolean acquireSyncMark(Object key)
    {
        Objects.requireNonNull(key, "key is null");
        return conditionSyncMarks.putIfAbsent(key, true) == null;
    }

    public void immediatelyReleaseSyncMark(Object key)
    {
        Objects.requireNonNull(key, "key is null");
        conditionSyncMarks.remove(key);
    }

    /**
     * 同一个线程上下文只支持这么操作一次,  暂时懒得实现更复杂的 感觉没啥用
     * @param key   condition key
     */
    public void deferredReleaseSyncMark(Object key)
    {
        Objects.requireNonNull(key, "key is null");
        deferredMarks.set(new SynchronizerDeferredArgs(key, this));
    }

    /**
     * 根据给定的key获取锁 没获取到会阻塞
     * @param key   unique key
     */
    public void acquireLock(Object key)
    {
        ReentrantLock reentrantLock = new ReentrantLock();
        if (conditionLocks.putIfAbsent(key, reentrantLock) == null)
        {
            reentrantLock.lock();
        }
        else
        {
            conditionLocks.get(key).lock();
        }
    }

    /**
     * 释放锁, 会唤醒所有因为未获取到锁而阻塞的线程
     * @param key unique key
     */
    public void releaseLock(Object key)
    {
        ReentrantLock reentrantLock = conditionLocks.remove(key);
        if (reentrantLock != null)
        {
            reentrantLock.unlock();
        }
    }
}
