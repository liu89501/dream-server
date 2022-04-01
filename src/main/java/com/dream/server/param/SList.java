package com.dream.server.param;

import com.dream.server.utils.PacketUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.dream.service.codec.Packet;
import com.dream.service.codec.ParameterSaver;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SList<T extends ParameterSaver> implements ParameterSaver
{
    private List<T> params;

    @Override
    public void save(Packet packet)
    {
        PacketUtils.saveList(params, packet);
    }
}
