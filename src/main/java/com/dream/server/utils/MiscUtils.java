package com.dream.server.utils;

import java.lang.reflect.ParameterizedType;
import java.util.function.Supplier;

public interface MiscUtils
{
    static String getFieldMethodName(String prefix, String fieldName)
    {
        return prefix + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    static <T> T getOrCreate(T instance, Supplier<T> creator)
    {
        return instance != null ? instance : creator.get();
    }

    static String parseIp(int ip)
    {
        return (ip & 0xFF) + "." + (ip >>> 8 & 0xFF) + "." + (ip >>> 16 & 0xFF) + "." + (ip >>> 24 & 0xFF);
    }

    @SuppressWarnings("unchecked")
    static <T> Class<T> getSuperClassArgType(Class<?> clazz)
    {
        ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();

        if (parameterizedType == null)
        {
            throw new IllegalArgumentException("获取泛型类型失败");
        }

        return (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }
}
