package com.dream.server.sync;

import com.dream.container.ComponentContainer;
import com.dream.container.DependenceHandler;
import com.dream.container.InstanceDefinition;

import java.lang.reflect.Field;
import java.util.HashMap;

public class SynchronizerDependenceHandler implements DependenceHandler
{
    private final HashMap<String, Synchronizer> Syncs = new HashMap<>();

    @Override
    public boolean handle(Field dependenceField, InstanceDefinition ownerInstance) throws Exception
    {
        if (dependenceField.isAnnotationPresent(Sync.class))
        {
            Sync sync = dependenceField.getAnnotation(Sync.class);

            String category = sync.value();

            Synchronizer synchronizer = Syncs.get(category);
            if (synchronizer == null)
            {
                synchronizer = new Synchronizer(category);
                if (Syncs.put(category, synchronizer) != null)
                {
                    throw new IllegalArgumentException("sync category exist: " + category);
                }
            }

            dependenceField.set(ownerInstance.getOriginalInstance(), synchronizer);

            return true;
        }

        return false;
    }

    @Override
    public void initializeComponents(ComponentContainer componentContainer, ComponentContainer componentContainer1)
    {

    }
}
