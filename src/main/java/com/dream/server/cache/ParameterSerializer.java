package com.dream.server.cache;

import com.dream.server.param.PacketJdk;
import com.dream.server.param.ParameterArchive;
import org.ehcache.spi.serialization.Serializer;
import org.ehcache.spi.serialization.SerializerException;

import java.nio.ByteBuffer;

public abstract class ParameterSerializer<T extends ParameterArchive> implements Serializer<T>
{
    protected abstract T newInstance();

    @Override
    public ByteBuffer serialize(T t) throws SerializerException
    {
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        t.save(new PacketJdk(byteBuffer));
        byteBuffer.flip();
        return byteBuffer;
    }

    @Override
    public T read(ByteBuffer byteBuffer) throws ClassNotFoundException, SerializerException
    {
        T instance = newInstance();
        instance.load(new PacketJdk(byteBuffer));
        return instance;
    }

    @Override
    public boolean equals(T t, ByteBuffer byteBuffer) throws ClassNotFoundException, SerializerException
    {
        return byteBuffer.equals(serialize(t));
    }
}
