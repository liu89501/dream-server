package com.dream.server.mq;

import com.dream.container.Container;
import com.dream.container.InitializeTemporaryParams;
import com.dream.container.InstanceDefinition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerMessageContainer implements Container
{
    private Map<Integer, PlayerMessageDesc> instances = new HashMap<>();

    @Override
    public boolean canHosting(Class<?> aClass)
    {
        return aClass.isAnnotationPresent(MessageHandler.class);
    }

    @Override
    public void add(Class<?> aClass) throws Exception
    {

    }

    @Override
    public List<InstanceDefinition> getInstances()
    {
        return null;
    }

    @Override
    public void initializeContainerParam(InitializeTemporaryParams initializeTemporaryParams)
    {
    }

    public PlayerMessageDesc getPlayerMessageDesc(int mark)
    {
        return instances.get(mark);
    }
}
