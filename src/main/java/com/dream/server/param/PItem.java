package com.dream.server.param;

import com.dream.server.utils.ItemUtils;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.dream.service.codec.Packet;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "impl"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PItemEquipment.class, name = "equ"),
        @JsonSubTypes.Type(value = PItemSimple.class, name = "sim")
})
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class PItem extends ParameterArchive
{
    private int itemGuid;

    public PItem()
    {
    }

    public PItem(int itemGuid)
    {
        this.itemGuid = itemGuid;
    }

    public static PItem build(Packet packet)
    {
        int guid = packet.readInt();
        int type = ItemUtils.getItemType(guid);

        PItem item = switch (type)
                {
                    case EItemType.Weapon, EItemType.Module -> new PItemEquipment();
                    case EItemType.Experience, EItemType.Material -> new PItemSimple();
                    default -> null;
                };

        if (item != null)
        {
            item.itemGuid = guid;
        }

        return item;
    }

    @Override
    public void load(Packet packet)
    {
        // itemGuid 已经在 build 方法中加载过了 这里就不用再加载了
    }

    @Override
    public void save(Packet packet)
    {
        packet.writeInt(itemGuid);
    }
}
