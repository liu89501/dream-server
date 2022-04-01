package com.dream.server.param;

import com.dream.server.utils.PacketUtils;
import com.dream.server.database.model.PlayerMaterial;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.dream.service.codec.Packet;

import java.util.LinkedHashMap;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PMaterialCounter extends ParameterArchive
{
    /**
     * 必须是有序的，不然解析会出问题
     */
    private LinkedHashMap<Integer, Integer> counter;

    public PMaterialCounter(List<PlayerMaterial> materials)
    {
        if (materials != null)
        {
            counter = new LinkedHashMap<>();
            for (PlayerMaterial material : materials)
            {
                counter.put(material.getItemGuid(), material.getNum());
            }
        }
    }

    public int getNum(int itemGuid)
    {
        if (counter != null)
        {
            return counter.getOrDefault(itemGuid, 0);
        }

        return 0;
    }

    public int add(int itemGuid, int amount)
    {
        if (counter != null)
        {
            Integer value = counter.get(itemGuid);
            if (value != null)
            {
                int newNum = value + amount;
                counter.put(itemGuid, newNum);
                return newNum;
            }
        }

        return 0;
    }

    public int subtract(int itemGuid, int amount)
    {
        return add(itemGuid, -amount);
    }

    public List<PPlayerMaterial> toMaterials()
    {
        if (counter == null)
        {
            return null;
        }

        return counter.entrySet()
                .stream()
                .map(e -> new PPlayerMaterial(e.getKey(), e.getValue()))
                .toList();
    }

    @Override
    public void load(Packet packet)
    {
        counter = PacketUtils.loadMap0(packet::readInt, packet::readInt, packet);
    }

    @Override
    public void save(Packet packet)
    {
        PacketUtils.saveMap0(counter, packet::writeInt, packet::writeInt, packet);
    }
}
