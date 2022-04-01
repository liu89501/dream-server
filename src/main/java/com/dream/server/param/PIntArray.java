package com.dream.server.param;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.dream.service.codec.Packet;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PIntArray extends ParameterArchive
{
    private int[] value;

    public void copy(PIntArray other)
    {
        if (other == null || other.value == null)
        {
            return;
        }

        if (value == null || value.length < other.value.length)
        {
            value = new int[other.value.length];
        }

        System.arraycopy(other.value, 0, value, 0, value.length);
    }

    @Override
    public void load(Packet packet)
    {
        int length = packet.readInt();

        if (length > 0)
        {
            value = new int[length];
            for (int i = 0; i < length; i++)
            {
                value[i] = packet.readInt();
            }
        }
    }

    @Override
    public void save(Packet packet)
    {
        boolean isEmpty = value == null || value.length == 0;

        packet.writeInt(isEmpty ? 0 : value.length);

        if (!isEmpty)
        {
            for (int val : value)
            {
                packet.writeInt(val);
            }
        }
    }
}
