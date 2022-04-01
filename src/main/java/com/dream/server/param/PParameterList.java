package com.dream.server.param;

import com.dream.server.utils.PacketUtils;
import org.apache.commons.collections.CollectionUtils;
import com.dream.service.codec.Packet;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public abstract class PParameterList<T extends ParameterArchive> extends ParameterArchive
{
    protected List<T> list;

    private Constructor<T> parameterConstructor;

    @SuppressWarnings("unchecked")
    public PParameterList()
    {
        ParameterizedType parameterizedType = (ParameterizedType)getClass().getGenericSuperclass();
        Class<T> parameterClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];

        int modifiers = parameterClass.getModifiers();
        if (Modifier.isInterface(modifiers) || Modifier.isAbstract(modifiers))
        {
            throw new IllegalArgumentException("泛型类型不支持定义成抽象类或接口");
        }

        try
        {
            parameterConstructor = parameterClass.getConstructor();
        }
        catch (NoSuchMethodException e)
        {
            throw new IllegalArgumentException(e);
        }
    }

    public PParameterList(List<T> list)
    {
        this.list = list;
    }

    public List<T> getList()
    {
        return list;
    }

    public boolean isNotEmpty()
    {
        return CollectionUtils.isNotEmpty(list);
    }

    public void add(T t)
    {
        if (list == null)
        {
            list = new ArrayList<>();
        }
        list.add(t);
    }

    public void remove(int index)
    {
        if (isValid())
        {
            list.remove(index);
        }
    }

    public void remove(T t)
    {
        if (isValid())
        {
            list.remove(t);
        }
    }

    public Stream<T> stream()
    {
        return list.stream();
    }

    public boolean isValid()
    {
        return list != null && list.size() > 0;
    }

    public int size()
    {
        return isValid() ? list.size() : 0;
    }

    @Override
    public void load(Packet packet)
    {
        list = PacketUtils.loadList(this::newInstance, packet);
    }

    @Override
    public void save(Packet packet)
    {
        PacketUtils.saveList(list, packet);
    }

    private T newInstance()
    {
        try
        {
            return parameterConstructor.newInstance();
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException(e);
        }
    }
}
