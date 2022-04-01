package com.dream.server.database.model;

import com.dream.server.param.PIntArray;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * player_module
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerModule
{
    private Long moduleId;

    private Integer playerId;

    private Integer itemGuid;

    private Boolean equipped;

    private Integer moduleCategory;

    private Float attackPower;

    private Float maxHealth;

    private Float criticalDamage;

    private Float criticalRate;

    private Float healthSteal;

    private Float defense;

    private Float damageReduction;

    private Float penetration;

    private PIntArray perks;

    private Byte level;

    public PlayerModule(Integer itemGuid, Boolean equipped, Integer moduleCategory,
                        Float attackPower, Float maxHealth, Float criticalDamage,
                        Float criticalRate, Float healthSteal, Float defense, Float damageReduction, Float penetration)
    {
        this.itemGuid = itemGuid;
        this.equipped = equipped;
        this.moduleCategory = moduleCategory;
        this.attackPower = attackPower;
        this.maxHealth = maxHealth;
        this.criticalDamage = criticalDamage;
        this.criticalRate = criticalRate;
        this.healthSteal = healthSteal;
        this.defense = defense;
        this.damageReduction = damageReduction;
        this.penetration = penetration;
        this.level = 0;
    }
}