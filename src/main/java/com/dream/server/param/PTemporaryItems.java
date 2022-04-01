package com.dream.server.param;

import com.dream.server.utils.PacketUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.collections.MapUtils;
import com.dream.service.codec.Packet;

import java.util.LinkedHashMap;

@EqualsAndHashCode(callSuper = true)
@Data
public class PTemporaryItems extends ParameterArchive
{
    private LinkedHashMap<String, PItem> temporaryItems;

    public void addTemporaryItems(PTemporaryItems other)
    {
        if (other != null && MapUtils.isNotEmpty(other.temporaryItems))
        {
            getOrCreateItems().putAll(other.temporaryItems);
        }
    }

    public PItem getTemporaryItem(String uniqueId)
    {
        return MapUtils.isNotEmpty(temporaryItems) ? temporaryItems.get(uniqueId) : null;
    }

    private LinkedHashMap<String, PItem> getOrCreateItems()
    {
        if (temporaryItems == null)
        {
            temporaryItems = new LinkedHashMap<>();
        }

        return temporaryItems;
    }

    @Override
    public void load(Packet packet)
    {
        temporaryItems = PacketUtils.loadMap(packet::readString, () -> PItem.build(packet), packet);
    }

    @Override
    public void save(Packet packet)
    {
        PacketUtils.saveMap(temporaryItems, packet::writeString, packet);
    }
}
