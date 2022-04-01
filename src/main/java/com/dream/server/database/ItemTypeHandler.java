package com.dream.server.database;

import com.dream.server.param.PItem;
import com.dream.server.param.PacketNetty;
import io.netty.buffer.Unpooled;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemTypeHandler extends BaseTypeHandler<PItem>
{
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, PItem parameter, JdbcType jdbcType) throws SQLException
    {
        PacketNetty packet = new PacketNetty(Unpooled.buffer(256));
        parameter.save(packet);

        byte[] bytes = new byte[packet.available()];
        packet.readBytes(bytes);

        ps.setBytes(i, bytes);
    }

    @Override
    public PItem getNullableResult(ResultSet rs, String columnName) throws SQLException
    {
        return getInstance(rs.getBytes(columnName));
    }

    @Override
    public PItem getNullableResult(ResultSet rs, int columnIndex) throws SQLException
    {
        return getInstance(rs.getBytes(columnIndex));
    }

    @Override
    public PItem getNullableResult(CallableStatement cs, int columnIndex) throws SQLException
    {
        return getInstance(cs.getBytes(columnIndex));
    }

    private PItem getInstance(byte[] bytes)
    {
        if (bytes == null || bytes.length == 0)
        {
            return null;
        }

        PacketNetty packet = new PacketNetty(Unpooled.wrappedBuffer(bytes));
        PItem item = PItem.build(packet);
        item.load(packet);
        return item;
    }
}
