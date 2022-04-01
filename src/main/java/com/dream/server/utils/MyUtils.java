package com.dream.server.utils;

import com.dream.server.database.model.PlayerWeapon;
import com.dream.server.config.Constant;
import com.dream.server.database.model.PlayerModule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface MyUtils
{
    static boolean isValidCollection(Collection<?> collection)
    {
        return collection != null && collection.size() > 0;
    }

    static List<Integer> parseLearnedTalentsId(Long learnedTalent)
    {
        if (learnedTalent > 0)
        {
            ArrayList<Integer> id = new ArrayList<>();

            for (int i = 0; i < 64; i++)
            {
                if (((learnedTalent >>> i) & 1) == 1)
                {
                    id.add(i);
                }
            }

            return id;
        }

        return null;
    }

    static List<Integer> parsePerksId(Long perks)
    {
        if (perks > 0)
        {
            ArrayList<Integer> id = new ArrayList<>();
            for (int i = 48; i > 0; i-=16)
            {
                int perkId = (int) ((perks >>> i) & 0xFF);
                if (perkId > 0)
                {
                    id.add(perkId);
                }
            }
            return id;
        }
        return null;
    }

    static int getMaxExperience(int level)
    {
        return (int) Math.ceil(600 * Math.pow(1.1, level * 1.1));
    }

    static List<PlayerWeapon> defaultWeapons(int playerId)
    {
        List<PlayerWeapon> weapons = new ArrayList<>();
        for (PlayerWeapon defaultWeapon : Constant.DEFAULT_WEAPONS)
        {
            PlayerWeapon copyWeapon = new PlayerWeapon(defaultWeapon.getItemGuid(),
                    defaultWeapon.getEquipped(), defaultWeapon.getEquipmentIndex(),
                    defaultWeapon.getAttackPower(), defaultWeapon.getMaxHealth(),
                    defaultWeapon.getCriticalDamage(), defaultWeapon.getCriticalRate(),
                    defaultWeapon.getHealthSteal(), defaultWeapon.getDefense(),
                    defaultWeapon.getDamageReduction(), defaultWeapon.getPenetration());

            copyWeapon.setPlayerId(playerId);

            weapons.add(copyWeapon);
        }

        return weapons;
    }

    static List<PlayerModule> defaultModules(int playerId)
    {
        List<PlayerModule> modules = new ArrayList<>();
        for (PlayerModule defaultModule : Constant.DEFAULT_MODULES)
        {
            PlayerModule playerModule = new PlayerModule(defaultModule.getItemGuid(),
                    defaultModule.getEquipped(), defaultModule.getModuleCategory(),
                    defaultModule.getAttackPower(), defaultModule.getMaxHealth(),
                    defaultModule.getCriticalDamage(), defaultModule.getCriticalRate(),
                    defaultModule.getHealthSteal(), defaultModule.getDefense(),
                    defaultModule.getDamageReduction(), defaultModule.getPenetration());

            playerModule.setPlayerId(playerId);
            modules.add(playerModule);
        }
        return modules;
    }

    static Object getOrDefault(Object value, Object defaultVal)
    {
        return value == null ? defaultVal : value;
    }

    static <T> T getOrDefaultCast(T value, T defaultVal)
    {
        return value == null ? defaultVal : value;
    }

    static ExperienceResult calcExperience(int currentExp, int currentMaxExp, int currentLevel, int incrementExp)
    {
        int remain = currentMaxExp - currentExp;
        if (remain <= incrementExp)
        {
            incrementExp = incrementExp - remain;

            currentLevel += 1;
            currentMaxExp = getMaxExperience(currentLevel);

            if (incrementExp >= currentMaxExp)
            {
                return calcExperience(0, currentMaxExp, currentLevel, incrementExp);
            }
        }

        return new ExperienceResult(currentLevel, currentExp + incrementExp, currentMaxExp);
    }

    class ExperienceResult
    {
        public int newLevel;
        public int newExp;
        public int newMaxExp;

        public ExperienceResult(int newLevel, int newExp, int newMaxExp)
        {
            this.newLevel = newLevel;
            this.newExp = newExp;
            this.newMaxExp = newMaxExp;
        }
    }
}
