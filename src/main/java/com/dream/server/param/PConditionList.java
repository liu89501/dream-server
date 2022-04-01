package com.dream.server.param;

import com.dream.server.utils.PacketUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.dream.service.codec.Packet;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PConditionList extends ParameterArchive
{
    private List<PCondition> conditions;

    @Override
    public void load(Packet packet)
    {
        conditions = PacketUtils.loadList(() -> PCondition.build(packet), packet);
    }

    @Override
    public void save(Packet packet)
    {
        PacketUtils.saveList(conditions, packet);
    }
}
