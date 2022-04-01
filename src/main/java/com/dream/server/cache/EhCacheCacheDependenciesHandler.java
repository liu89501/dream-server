package com.dream.server.cache;

import com.dream.container.ComponentContainer;
import com.dream.container.DependenceHandler;
import com.dream.container.InstanceDefinition;
import com.dream.container.utils.DreamUtils;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

public class EhCacheCacheDependenciesHandler implements DependenceHandler
{
    private final CacheManager cacheManager;

    public EhCacheCacheDependenciesHandler()
    {
        cacheManager = CacheManagerBuilder.newCacheManager(new XmlConfiguration(DreamUtils.getResource("ehcache.xml")));
        cacheManager.init();
    }

    @Override
    public boolean handle(Field dependenceField, InstanceDefinition ownerInstance) throws Exception
    {
        if (!dependenceField.isAnnotationPresent(EhCache.class))
        {
            return false;
        }

        if (!Cache.class.isAssignableFrom(dependenceField.getType()))
        {
            return false;
        }

        EhCache ehCache = dependenceField.getAnnotation(EhCache.class);

        ParameterizedType parameterizedType = (ParameterizedType) dependenceField.getGenericType();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();

        Class<?> keyType = (Class<?>) actualTypeArguments[0];
        Class<?> valueType = (Class<?>) actualTypeArguments[1];
        Cache<?, ?> cache = cacheManager.getCache(ehCache.value(), keyType, valueType);

        Objects.requireNonNull(cache, "cache 获取失败: " + ehCache.value());

        dependenceField.set(ownerInstance.getOriginalInstance(), cache);

        return true;
    }

    @Override
    public void initializeComponents(ComponentContainer componentContainer, ComponentContainer componentContainer1)
    {

    }
}
