package com.dream.server.service.component;

import com.dream.server.cache.EhCache;
import com.dream.server.config.CacheCategory;
import com.dream.server.database.mapper.PlayerTaskMapper;
import com.dream.server.database.model.PlayerTask;
import com.dream.server.param.PPlayerTask;
import com.dream.server.param.PPlayerTaskList;
import com.dream.server.utils.Logs;
import com.dream.container.anno.Assign;
import com.dream.container.anno.Component;
import com.dream.container.anno.Transaction;
import org.ehcache.Cache;

import java.util.HashSet;

@Component
public class CTaskUpdaterExecutor
{
    @Assign
    private PlayerTaskMapper playerTaskMapper;

    @EhCache(CacheCategory.AcceptTasks)
    private Cache<Integer, PPlayerTaskList> playerAcceptTasks;


    @Transaction(batch = true)
    public void execute(HashSet<Integer> temporary)
    {
        if (temporary != null)
        {
            for (Integer playerId : temporary)
            {
                PPlayerTaskList taskList = playerAcceptTasks.get(playerId);

                if (!taskList.isValid())
                {
                    continue;
                }

                for (PPlayerTask playerTask : taskList.getList())
                {
                    if (playerTask == null)
                    {
                        continue;
                    }

                    PlayerTask task = playerTask.toPlayerTask();
                    playerTaskMapper.updateByPrimaryKeySelective(task);
                    Logs.LOG.info("playerTask updating {}", task.getPtid());
                }
            }
        }
    }
}
