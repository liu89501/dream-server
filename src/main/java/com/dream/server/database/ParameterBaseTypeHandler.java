package com.dream.server.database;

import com.dream.server.param.PacketNetty;
import com.dream.server.param.ParameterArchive;
import com.dream.server.utils.MiscUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ParameterBaseTypeHandler<T extends ParameterArchive> extends BaseTypeHandler<T>
{
    private Constructor<T> constructor;

    private int defaultBufferCapacity;

    private boolean requireNotReturnNull;

    protected ParameterBaseTypeHandler(int defaultBufferCapacity, boolean requireNotReturnNull)
    {
        this.defaultBufferCapacity = defaultBufferCapacity;
        this.requireNotReturnNull = requireNotReturnNull;

        Class<T> superClassArgType = MiscUtils.getSuperClassArgType(getClass());
        try
        {
            constructor = superClassArgType.getConstructor();
        }
        catch (NoSuchMethodException e)
        {
            throw new IllegalArgumentException(e);
        }
    }

    public T createInstance()
    {
        try
        {
            return constructor.newInstance();
        }
        catch (InstantiationException | IllegalAccessException | InvocationTargetException e)
        {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException
    {
        ByteBuf buffer = Unpooled.buffer(defaultBufferCapacity);
        PacketNetty packet = new PacketNetty(buffer);
        parameter.save(packet);

        byte[] bytes = new byte[packet.available()];
        packet.readBytes(bytes);

        ps.setBytes(i, bytes);
    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException
    {
        return getInstance(rs.getBytes(columnName));
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException
    {
        return getInstance(rs.getBytes(columnIndex));
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException
    {
        return getInstance(cs.getBytes(columnIndex));
    }

    private T getInstance(byte[] bytes)
    {
        if (bytes == null || bytes.length == 1)
        {
            return requireNotReturnNull ? createInstance() : null;
        }

        PacketNetty packet = new PacketNetty(Unpooled.wrappedBuffer(bytes));
        T instance = createInstance();
        instance.load(packet);
        return instance;
    }
}
