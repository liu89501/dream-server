package com.dream.server.utils;

import com.dream.server.param.PacketUE4;
import com.dream.service.codec.Packet;
import com.dream.service.codec.ParameterLoader;
import com.dream.service.codec.ParameterSaver;
import com.dream.service.utils.SerializeUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.apache.commons.collections.MapUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface PacketUtils
{
    static <T extends ParameterLoader> void decodeFromBytes(T decodeObj, byte[] bytes)
    {
        ByteBuf byteBuf = Unpooled.wrappedBuffer(bytes);
        decodeObj.load(new PacketUE4(byteBuf));
    }

    static <T extends ParameterSaver> byte[] encodeToBytes(T obj)
    {
        ByteBuf buffer = Unpooled.buffer();
        obj.save(new PacketUE4(buffer));

        byte[] bytes = new byte[buffer.readableBytes()];
        buffer.readBytes(bytes);

        return bytes;
    }

    static byte[] loadBytes(Packet packet)
    {
        int length = packet.readInt();

        if (length > 0)
        {
            byte[] bytes = new byte[length];
            packet.readBytes(bytes);
            return bytes;
        }

        return null;
    }

    static <T extends ParameterLoader> List<T> loadList(Supplier<T> constructor, Packet packet)
    {
        return loadList0(constructor, e -> e.load(packet), packet);
    }

    static <T> List<T> loadList0(Supplier<T> constructor, Consumer<T> consumer, Packet packet)
    {
        int length = SerializeUtils.getListSize(packet);

        if (length > 0)
        {
            ArrayList<T> list = new ArrayList<>(length);

            for (int i = 0; i < length; i++)
            {
                T t = constructor.get();
                consumer.accept(t);
                list.add(t);
            }

            return list;
        }

        return null;
    }

    static <K, V extends ParameterLoader> LinkedHashMap<K, V> loadMap(Supplier<K> keyBuilder, Supplier<V> valBuilder, Packet packet)
    {
        return loadMap0(keyBuilder, () ->
        {
            V v = valBuilder.get();
            v.load(packet);
            return v;

        }, packet);
    }

    static <K, V> LinkedHashMap<K, V> loadMap0(Supplier<K> keyBuilder, Supplier<V> valBuilder, Packet packet)
    {
        int size = SerializeUtils.getListSize(packet);

        if (size > 0)
        {
            LinkedHashMap<K, V> map = new LinkedHashMap<>();

            for (int i = 0; i < size; i++)
            {
                K key = keyBuilder.get();
                V value = valBuilder.get();
                map.put(key, value);
            }

            return map;
        }

        return null;
    }

    static void saveBytes(byte[] bytes, Packet packet)
    {
        packet.writeInt(bytes == null ? 0 : bytes.length);
        packet.writeBytes(bytes);
    }

    static <T extends ParameterSaver> void saveList(List<T> list, Packet packet)
    {
        saveList0(list, e -> e.save(packet), packet);
    }

    static <T> void saveList0(List<T> list, Consumer<T> consumer, Packet packet)
    {
        boolean validCollection = MyUtils.isValidCollection(list);

        packet.writeInt(validCollection ? list.size() : 0);

        if (validCollection)
        {
            for (T t : list)
            {
                consumer.accept(t);
            }
        }
    }

    static <K, V extends ParameterSaver> void saveMap(LinkedHashMap<K, V> map, Consumer<K> keyHandler, Packet packet)
    {
        saveMap0(map, keyHandler, e -> e.save(packet), packet);
    }

    static <K, V> void saveMap0(LinkedHashMap<K, V> map, Consumer<K> keyHandler, Consumer<V> valHandler, Packet packet)
    {
        boolean valid = MapUtils.isNotEmpty(map);

        packet.writeInt(valid ? map.size() : 0);

        if (valid)
        {
            map.forEach((k, v) ->
            {
                keyHandler.accept(k);
                valHandler.accept(v);
            });
        }
    }
}
