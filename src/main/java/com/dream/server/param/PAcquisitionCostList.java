package com.dream.server.param;

import com.dream.server.utils.PacketUtils;
import com.dream.service.codec.Packet;

import java.util.List;

public class PAcquisitionCostList extends ParameterArchive
{
    private List<PAcquisitionCost> costs;

    public List<PAcquisitionCost> getCosts()
    {
        return costs;
    }

    public void setCosts(List<PAcquisitionCost> costs)
    {
        this.costs = costs;
    }

    @Override
    public void load(Packet packet)
    {
        costs = PacketUtils.loadList(PAcquisitionCost::new, packet);
    }

    @Override
    public void save(Packet packet)
    {
        PacketUtils.saveList(costs, packet);
    }
}
