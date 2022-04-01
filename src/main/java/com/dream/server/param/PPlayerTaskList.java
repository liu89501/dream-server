package com.dream.server.param;

import com.dream.server.database.model.PlayerTask;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class PPlayerTaskList extends PParameterList<PPlayerTask>
{
    public PPlayerTaskList(List<PPlayerTask> tasks)
    {
        super(tasks);
    }

    public static PPlayerTaskList makeFromPlayerTasks(List<PlayerTask> playerTasks)
    {
        if (CollectionUtils.isEmpty(playerTasks))
        {
            return null;
        }

        List<PPlayerTask> taskList = playerTasks.stream()
                .map(PPlayerTask::new)
                .collect(Collectors.toCollection(ArrayList::new));

        return new PPlayerTaskList(taskList);
    }
}
