package com.dream.server.param;

import com.dream.server.utils.PacketUtils;
import com.dream.service.codec.Packet;

import java.util.List;

public class PTaskContainer extends ParameterArchive
{
    private List<PPlayerTask> tasks;

    public List<PPlayerTask> getTasks()
    {
        return tasks;
    }

    public void setTasks(List<PPlayerTask> tasks)
    {
        this.tasks = tasks;
    }

    public PTaskContainer()
    {
    }

    public PTaskContainer(List<PPlayerTask> tasks)
    {
        this.tasks = tasks;
    }

    @Override
    public void load(Packet packet)
    {
        tasks = PacketUtils.loadList(PPlayerTask::new, packet);
    }

    @Override
    public void save(Packet packet)
    {
        PacketUtils.saveList(tasks, packet);
    }
}
