package com.dream.server.param;

import com.dream.server.database.model.PlayerModule;
import com.dream.server.utils.MiscUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.dream.service.codec.Packet;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class PPlayerModule extends ParameterArchive
{
    private long moduleId;

    private int itemGuid;

    private boolean equipped;

    private byte category;

    private float attackPower;

    private float maxHealth;

    private float criticalDamage;

    private float criticalRate;

    private float healthSteal;

    private float defense;

    private float damageReduction;

    private float penetration;

    private PIntArray perks;

    private byte level;

    public PPlayerModule(PlayerModule playerModule)
    {
        moduleId = playerModule.getModuleId();
        itemGuid = playerModule.getItemGuid();
        equipped = playerModule.getEquipped();
        category = playerModule.getModuleCategory().byteValue();
        attackPower = playerModule.getAttackPower();
        maxHealth = playerModule.getMaxHealth();
        criticalDamage = playerModule.getCriticalDamage();
        criticalRate = playerModule.getCriticalRate();
        healthSteal = playerModule.getHealthSteal();
        defense = playerModule.getDefense();
        damageReduction = playerModule.getDamageReduction();
        penetration = playerModule.getPenetration();
        level = playerModule.getLevel();
        perks = new PIntArray();
        perks.copy(playerModule.getPerks());
    }

    @Override
    public void load(Packet packet)
    {
        moduleId = packet.readLong();
        itemGuid = packet.readInt();
        equipped = packet.readBool();
        category = packet.readByte();
        attackPower = packet.readFloat();
        maxHealth = packet.readFloat();
        criticalDamage = packet.readFloat();
        criticalRate = packet.readFloat();
        healthSteal = packet.readFloat();
        defense = packet.readFloat();
        damageReduction = packet.readFloat();
        penetration = packet.readFloat();

        perks = MiscUtils.getOrCreate(perks, PIntArray::new);
        perks.load(packet);

        level = packet.readByte();
    }

    @Override
    public void save(Packet packet)
    {
        packet.writeLong(moduleId);
        packet.writeInt(itemGuid);
        packet.writeBool(equipped);
        packet.writeByte(category);
        packet.writeFloat(attackPower);
        packet.writeFloat(maxHealth);
        packet.writeFloat(criticalDamage);
        packet.writeFloat(criticalRate);
        packet.writeFloat(healthSteal);
        packet.writeFloat(defense);
        packet.writeFloat(damageReduction);
        packet.writeFloat(penetration);
        perks.save(packet);
        packet.writeByte(level);
    }
}
