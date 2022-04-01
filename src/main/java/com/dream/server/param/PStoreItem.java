package com.dream.server.param;

import com.dream.server.database.model.GameStoreItemWithBLOBs;
import com.dream.server.utils.MiscUtils;
import com.dream.service.codec.Packet;

public class PStoreItem extends ParameterArchive
{
    private long storeItemId;
    private PAcquisitionCostList itemCostData;
    private PItem itemData;

    public PStoreItem(GameStoreItemWithBLOBs storeItem)
    {
        storeItemId = storeItem.getGsiId();
        itemCostData = storeItem.getItemCostData();
        itemData = storeItem.getItemData();
    }

    @Override
    public void load(Packet packet)
    {
        storeItemId = packet.readLong();
        itemCostData = MiscUtils.getOrCreate(itemCostData, PAcquisitionCostList::new);
        itemCostData.load(packet);
        itemData = PItem.build(packet);
        itemData.save(packet);
    }

    @Override
    public void save(Packet packet)
    {
        packet.writeLong(storeItemId);
        itemCostData.save(packet);
        itemData.save(packet);
    }
}
