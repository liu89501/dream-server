package com.dream.server.param;

import com.dream.server.database.model.PlayerWeapon;
import com.dream.server.utils.MiscUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.dream.service.codec.Packet;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class PPlayerWeapon extends ParameterArchive
{
    private long weaponId;

    private int itemGuid;

    private boolean equipped;

    private int equipmentIndex;

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

    public PPlayerWeapon(PlayerWeapon playerWeapon)
    {
        weaponId = playerWeapon.getWeaponId();
        itemGuid = playerWeapon.getItemGuid();
        equipped = playerWeapon.getEquipped();
        equipmentIndex = playerWeapon.getEquipmentIndex();
        attackPower = playerWeapon.getAttackPower();
        maxHealth = playerWeapon.getMaxHealth();
        criticalDamage = playerWeapon.getCriticalDamage();
        criticalRate = playerWeapon.getCriticalRate();
        healthSteal = playerWeapon.getHealthSteal();
        defense = playerWeapon.getDefense();
        damageReduction = playerWeapon.getDamageReduction();
        penetration = playerWeapon.getPenetration();
        perks = new PIntArray();
        perks.copy(playerWeapon.getPerks());
        level = playerWeapon.getLevel();
    }

    @Override
    public void load(Packet packet)
    {
        weaponId = packet.readLong();
        itemGuid = packet.readInt();
        equipped = packet.readBool();
        equipmentIndex = packet.readInt();
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
        packet.writeLong(weaponId);
        packet.writeInt(itemGuid);
        packet.writeBool(equipped);
        packet.writeInt(equipmentIndex);
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
