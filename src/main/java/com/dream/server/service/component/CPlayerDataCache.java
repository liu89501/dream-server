package com.dream.server.service.component;

import com.dream.container.anno.Assign;
import com.dream.server.config.CacheCategory;
import com.dream.server.database.model.PlayerTask;
import com.dream.server.database.model.PlayerTaskExample;
import com.dream.server.cache.EhCache;
import com.dream.container.anno.Component;
import com.dream.server.database.mapper.PlayerMapper;
import com.dream.server.database.mapper.PlayerMaterialMapper;
import com.dream.server.database.mapper.PlayerTaskMapper;
import com.dream.server.database.model.Player;
import com.dream.server.database.model.PlayerMaterialExample;
import com.dream.server.param.ETaskState;
import com.dream.server.param.PMaterialCounter;
import com.dream.server.param.PPlayerTaskList;
import com.dream.server.sync.Sync;
import com.dream.server.sync.Synchronizer;
import org.ehcache.Cache;

import java.util.List;
import java.util.function.Supplier;

@Component
public class CPlayerDataCache
{
    @EhCache(CacheCategory.PlayerInformation)
    private Cache<Integer, Player> playerInf;


    @EhCache(CacheCategory.MaterialCounter)
    private Cache<Integer, PMaterialCounter> materialCounter;
    @Sync("MaterialCounterSync")
    private Synchronizer materialCounterSync;


    @EhCache(CacheCategory.AcceptTasks)
    private Cache<Integer, PPlayerTaskList> acceptTasks;
    @Sync("acceptTasksSync")
    private Synchronizer acceptTasksSync;


    @Assign
    private PlayerMaterialMapper materialMapper;

    @Assign
    private PlayerTaskMapper playerTaskMapper;

    @Assign
    private PlayerMapper playerMapper;


    public PPlayerTaskList getAcceptTask(int playerId)
    {
        return getOrPutCacheItem(acceptTasks, playerId, acceptTasksSync, () -> {

            PlayerTaskExample taskExample = new PlayerTaskExample();
            taskExample.createCriteria()
                    .andPlayerIdEqualTo(playerId)
                    .andTaskStateIn(List.of(ETaskState.Accepted, ETaskState.Completed));
            List<PlayerTask> playerTasks = playerTaskMapper.selectByExampleWithBLOBs(taskExample);

            PPlayerTaskList taskList = PPlayerTaskList.makeFromPlayerTasks(playerTasks);
            return taskList == null ? new PPlayerTaskList() : taskList;
        });
    }

    public void updateAcceptTaskCache(int playerId, PPlayerTaskList newCacheValue)
    {
        acceptTasks.replace(playerId, newCacheValue);
    }

    public Player getPlayerData(int playerId)
    {
        return getOrPutCacheItem(playerInf, playerId, null, () -> playerMapper.selectByPrimaryKey(playerId));
    }

    public PMaterialCounter getMaterialCounter(int playerId)
    {
        return getOrPutCacheItem(materialCounter, playerId, materialCounterSync, () -> {

            PlayerMaterialExample materialExample = new PlayerMaterialExample();
            materialExample.createCriteria()
                    .andPlayerIdEqualTo(playerId);
            return new PMaterialCounter(materialMapper.selectByExample(materialExample));
        });
    }

    private <K, V> V getOrPutCacheItem(Cache<K, V> cache, K key, Synchronizer sync, Supplier<V> creator)
    {
        V v = cache.get(key);
        if (v != null)
        {
            return v;
        }

        if (sync == null)
        {
            V queryV = creator.get();
            cache.put(key, queryV);
            return queryV;
        }

        sync.acquireLock(key);
        try
        {
            if (cache.containsKey(key))
            {
                return cache.get(key);
            }

            V queryV = creator.get();
            cache.put(key, queryV);
            return queryV;
        }
        finally
        {
            sync.releaseLock(key);
        }
    }


    public void clearAllCache(int playerId)
    {
        playerInf.remove(playerId);
        materialCounter.remove(playerId);
        acceptTasks.remove(playerId);
    }
}
