package com.dream.server.param;

import com.dream.server.database.model.PlayerWeapon;
import com.dream.server.database.model.PlayerModule;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.dream.service.codec.Packet;

@JsonTypeName("eq")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PItemEquipment extends PItem
{
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

    public PlayerWeapon castToPlayerWeapon(int playerId)
    {
        PlayerWeapon playerWeapon = new PlayerWeapon();
        playerWeapon.setEquipped(false);
        playerWeapon.setEquipmentIndex(0);
        playerWeapon.setPlayerId(playerId);
        playerWeapon.setItemGuid(getItemGuid());
        playerWeapon.setAttackPower(attackPower);
        playerWeapon.setMaxHealth(getMaxHealth());
        playerWeapon.setCriticalDamage(getCriticalDamage());
        playerWeapon.setCriticalRate(getCriticalRate());
        playerWeapon.setHealthSteal(getHealthSteal());
        playerWeapon.setDefense(getDefense());
        playerWeapon.setDamageReduction(getDamageReduction());
        playerWeapon.setPenetration(getPenetration());
        playerWeapon.setPerks(perks);
        playerWeapon.setLevel(level);
        return playerWeapon;
    }

    public PlayerModule castToPlayerModule(int playerId)
    {
        PlayerModule playerModule = new PlayerModule();
        playerModule.setEquipped(false);
        playerModule.setPlayerId(playerId);
        playerModule.setModuleCategory(0);
        playerModule.setItemGuid(getItemGuid());
        playerModule.setAttackPower(getAttackPower());
        playerModule.setMaxHealth(getMaxHealth());
        playerModule.setCriticalDamage(getCriticalDamage());
        playerModule.setCriticalRate(getCriticalRate());
        playerModule.setHealthSteal(getHealthSteal());
        playerModule.setDefense(getDefense());
        playerModule.setDamageReduction(getDamageReduction());
        playerModule.setPenetration(getPenetration());
        playerModule.setPerks(perks);
        playerModule.setLevel(level);
        return playerModule;
    }

    @Override
    public void load(Packet packet)
    {
        super.load(packet);

        attackPower = packet.readFloat();
        maxHealth = packet.readFloat();
        criticalDamage = packet.readFloat();
        criticalRate = packet.readFloat();
        healthSteal = packet.readFloat();
        defense = packet.readFloat();
        damageReduction = packet.readFloat();
        defense = packet.readFloat();

        if (perks == null)
        {
            perks = new PIntArray();
        }
        perks.load(packet);

        level = packet.readByte();
    }

    @Override
    public void save(Packet packet)
    {
        super.save(packet);

        packet.writeFloat(attackPower);
        packet.writeFloat(maxHealth);
        packet.writeFloat(criticalDamage);
        packet.writeFloat(criticalRate);
        packet.writeFloat(healthSteal);
        packet.writeFloat(defense);
        packet.writeFloat(damageReduction);
        packet.writeFloat(defense);
        perks.save(packet);
        packet.writeByte(level);
    }


}
