package com.dream.server.param;

import com.dream.server.utils.MyUtils;
import com.dream.server.utils.PacketUtils;
import com.dream.service.codec.Packet;

import java.util.ArrayList;
import java.util.List;

public class PItemList extends ParameterArchive
{
    private List<PItem> items;

    public PItemList()
    {
        items = new ArrayList<>();
    }

    public PItemList(List<PItem> items)
    {
        this.items = items;
    }

    public PItemList(PItem item)
    {
        this();
        add(item);
    }

    public List<PItem> getItems()
    {
        return items;
    }

    public void setItems(List<PItem> items)
    {
        this.items = items;
    }

    public void add(PItem item)
    {
        items.add(item);
    }

    public void addItems(PItemList list)
    {
        items.addAll(list.items);
    }

    public static PItemList TiledItems(List<PItemList> itemList)
    {
        if (!MyUtils.isValidCollection(itemList))
        {
            return null;
        }

        List<PItem> items = new ArrayList<>();

        for (PItemList item : itemList)
        {
            items.addAll(item.items);
        }

        return new PItemList(items);
    }

    @Override
    public void load(Packet packet)
    {
        items = PacketUtils.loadList(() -> PItem.build(packet), packet);
    }

    @Override
    public void save(Packet packet)
    {
        PacketUtils.saveList(items, packet);
    }
}
