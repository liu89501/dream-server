package com.dream.server.database.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PlayerWeaponExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public PlayerWeaponExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andWeaponIdIsNull() {
            addCriterion("weapon_id is null");
            return (Criteria) this;
        }

        public Criteria andWeaponIdIsNotNull() {
            addCriterion("weapon_id is not null");
            return (Criteria) this;
        }

        public Criteria andWeaponIdEqualTo(Long value) {
            addCriterion("weapon_id =", value, "weaponId");
            return (Criteria) this;
        }

        public Criteria andWeaponIdNotEqualTo(Long value) {
            addCriterion("weapon_id <>", value, "weaponId");
            return (Criteria) this;
        }

        public Criteria andWeaponIdGreaterThan(Long value) {
            addCriterion("weapon_id >", value, "weaponId");
            return (Criteria) this;
        }

        public Criteria andWeaponIdGreaterThanOrEqualTo(Long value) {
            addCriterion("weapon_id >=", value, "weaponId");
            return (Criteria) this;
        }

        public Criteria andWeaponIdLessThan(Long value) {
            addCriterion("weapon_id <", value, "weaponId");
            return (Criteria) this;
        }

        public Criteria andWeaponIdLessThanOrEqualTo(Long value) {
            addCriterion("weapon_id <=", value, "weaponId");
            return (Criteria) this;
        }

        public Criteria andWeaponIdIn(List<Long> values) {
            addCriterion("weapon_id in", values, "weaponId");
            return (Criteria) this;
        }

        public Criteria andWeaponIdNotIn(List<Long> values) {
            addCriterion("weapon_id not in", values, "weaponId");
            return (Criteria) this;
        }

        public Criteria andWeaponIdBetween(Long value1, Long value2) {
            addCriterion("weapon_id between", value1, value2, "weaponId");
            return (Criteria) this;
        }

        public Criteria andWeaponIdNotBetween(Long value1, Long value2) {
            addCriterion("weapon_id not between", value1, value2, "weaponId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdIsNull() {
            addCriterion("player_id is null");
            return (Criteria) this;
        }

        public Criteria andPlayerIdIsNotNull() {
            addCriterion("player_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlayerIdEqualTo(Integer value) {
            addCriterion("player_id =", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdNotEqualTo(Integer value) {
            addCriterion("player_id <>", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdGreaterThan(Integer value) {
            addCriterion("player_id >", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("player_id >=", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdLessThan(Integer value) {
            addCriterion("player_id <", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdLessThanOrEqualTo(Integer value) {
            addCriterion("player_id <=", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdIn(List<Integer> values) {
            addCriterion("player_id in", values, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdNotIn(List<Integer> values) {
            addCriterion("player_id not in", values, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdBetween(Integer value1, Integer value2) {
            addCriterion("player_id between", value1, value2, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("player_id not between", value1, value2, "playerId");
            return (Criteria) this;
        }

        public Criteria andItemGuidIsNull() {
            addCriterion("item_guid is null");
            return (Criteria) this;
        }

        public Criteria andItemGuidIsNotNull() {
            addCriterion("item_guid is not null");
            return (Criteria) this;
        }

        public Criteria andItemGuidEqualTo(Integer value) {
            addCriterion("item_guid =", value, "itemGuid");
            return (Criteria) this;
        }

        public Criteria andItemGuidNotEqualTo(Integer value) {
            addCriterion("item_guid <>", value, "itemGuid");
            return (Criteria) this;
        }

        public Criteria andItemGuidGreaterThan(Integer value) {
            addCriterion("item_guid >", value, "itemGuid");
            return (Criteria) this;
        }

        public Criteria andItemGuidGreaterThanOrEqualTo(Integer value) {
            addCriterion("item_guid >=", value, "itemGuid");
            return (Criteria) this;
        }

        public Criteria andItemGuidLessThan(Integer value) {
            addCriterion("item_guid <", value, "itemGuid");
            return (Criteria) this;
        }

        public Criteria andItemGuidLessThanOrEqualTo(Integer value) {
            addCriterion("item_guid <=", value, "itemGuid");
            return (Criteria) this;
        }

        public Criteria andItemGuidIn(List<Integer> values) {
            addCriterion("item_guid in", values, "itemGuid");
            return (Criteria) this;
        }

        public Criteria andItemGuidNotIn(List<Integer> values) {
            addCriterion("item_guid not in", values, "itemGuid");
            return (Criteria) this;
        }

        public Criteria andItemGuidBetween(Integer value1, Integer value2) {
            addCriterion("item_guid between", value1, value2, "itemGuid");
            return (Criteria) this;
        }

        public Criteria andItemGuidNotBetween(Integer value1, Integer value2) {
            addCriterion("item_guid not between", value1, value2, "itemGuid");
            return (Criteria) this;
        }

        public Criteria andEquippedIsNull() {
            addCriterion("equipped is null");
            return (Criteria) this;
        }

        public Criteria andEquippedIsNotNull() {
            addCriterion("equipped is not null");
            return (Criteria) this;
        }

        public Criteria andEquippedEqualTo(Boolean value) {
            addCriterion("equipped =", value, "equipped");
            return (Criteria) this;
        }

        public Criteria andEquippedNotEqualTo(Boolean value) {
            addCriterion("equipped <>", value, "equipped");
            return (Criteria) this;
        }

        public Criteria andEquippedGreaterThan(Boolean value) {
            addCriterion("equipped >", value, "equipped");
            return (Criteria) this;
        }

        public Criteria andEquippedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("equipped >=", value, "equipped");
            return (Criteria) this;
        }

        public Criteria andEquippedLessThan(Boolean value) {
            addCriterion("equipped <", value, "equipped");
            return (Criteria) this;
        }

        public Criteria andEquippedLessThanOrEqualTo(Boolean value) {
            addCriterion("equipped <=", value, "equipped");
            return (Criteria) this;
        }

        public Criteria andEquippedIn(List<Boolean> values) {
            addCriterion("equipped in", values, "equipped");
            return (Criteria) this;
        }

        public Criteria andEquippedNotIn(List<Boolean> values) {
            addCriterion("equipped not in", values, "equipped");
            return (Criteria) this;
        }

        public Criteria andEquippedBetween(Boolean value1, Boolean value2) {
            addCriterion("equipped between", value1, value2, "equipped");
            return (Criteria) this;
        }

        public Criteria andEquippedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("equipped not between", value1, value2, "equipped");
            return (Criteria) this;
        }

        public Criteria andEquipmentIndexIsNull() {
            addCriterion("equipment_index is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentIndexIsNotNull() {
            addCriterion("equipment_index is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentIndexEqualTo(Integer value) {
            addCriterion("equipment_index =", value, "equipmentIndex");
            return (Criteria) this;
        }

        public Criteria andEquipmentIndexNotEqualTo(Integer value) {
            addCriterion("equipment_index <>", value, "equipmentIndex");
            return (Criteria) this;
        }

        public Criteria andEquipmentIndexGreaterThan(Integer value) {
            addCriterion("equipment_index >", value, "equipmentIndex");
            return (Criteria) this;
        }

        public Criteria andEquipmentIndexGreaterThanOrEqualTo(Integer value) {
            addCriterion("equipment_index >=", value, "equipmentIndex");
            return (Criteria) this;
        }

        public Criteria andEquipmentIndexLessThan(Integer value) {
            addCriterion("equipment_index <", value, "equipmentIndex");
            return (Criteria) this;
        }

        public Criteria andEquipmentIndexLessThanOrEqualTo(Integer value) {
            addCriterion("equipment_index <=", value, "equipmentIndex");
            return (Criteria) this;
        }

        public Criteria andEquipmentIndexIn(List<Integer> values) {
            addCriterion("equipment_index in", values, "equipmentIndex");
            return (Criteria) this;
        }

        public Criteria andEquipmentIndexNotIn(List<Integer> values) {
            addCriterion("equipment_index not in", values, "equipmentIndex");
            return (Criteria) this;
        }

        public Criteria andEquipmentIndexBetween(Integer value1, Integer value2) {
            addCriterion("equipment_index between", value1, value2, "equipmentIndex");
            return (Criteria) this;
        }

        public Criteria andEquipmentIndexNotBetween(Integer value1, Integer value2) {
            addCriterion("equipment_index not between", value1, value2, "equipmentIndex");
            return (Criteria) this;
        }

        public Criteria andAttackPowerIsNull() {
            addCriterion("attack_power is null");
            return (Criteria) this;
        }

        public Criteria andAttackPowerIsNotNull() {
            addCriterion("attack_power is not null");
            return (Criteria) this;
        }

        public Criteria andAttackPowerEqualTo(Double value) {
            addCriterion("attack_power =", value, "attackPower");
            return (Criteria) this;
        }

        public Criteria andAttackPowerNotEqualTo(Double value) {
            addCriterion("attack_power <>", value, "attackPower");
            return (Criteria) this;
        }

        public Criteria andAttackPowerGreaterThan(Double value) {
            addCriterion("attack_power >", value, "attackPower");
            return (Criteria) this;
        }

        public Criteria andAttackPowerGreaterThanOrEqualTo(Double value) {
            addCriterion("attack_power >=", value, "attackPower");
            return (Criteria) this;
        }

        public Criteria andAttackPowerLessThan(Double value) {
            addCriterion("attack_power <", value, "attackPower");
            return (Criteria) this;
        }

        public Criteria andAttackPowerLessThanOrEqualTo(Double value) {
            addCriterion("attack_power <=", value, "attackPower");
            return (Criteria) this;
        }

        public Criteria andAttackPowerIn(List<Double> values) {
            addCriterion("attack_power in", values, "attackPower");
            return (Criteria) this;
        }

        public Criteria andAttackPowerNotIn(List<Double> values) {
            addCriterion("attack_power not in", values, "attackPower");
            return (Criteria) this;
        }

        public Criteria andAttackPowerBetween(Double value1, Double value2) {
            addCriterion("attack_power between", value1, value2, "attackPower");
            return (Criteria) this;
        }

        public Criteria andAttackPowerNotBetween(Double value1, Double value2) {
            addCriterion("attack_power not between", value1, value2, "attackPower");
            return (Criteria) this;
        }

        public Criteria andMaxHealthIsNull() {
            addCriterion("max_health is null");
            return (Criteria) this;
        }

        public Criteria andMaxHealthIsNotNull() {
            addCriterion("max_health is not null");
            return (Criteria) this;
        }

        public Criteria andMaxHealthEqualTo(Double value) {
            addCriterion("max_health =", value, "maxHealth");
            return (Criteria) this;
        }

        public Criteria andMaxHealthNotEqualTo(Double value) {
            addCriterion("max_health <>", value, "maxHealth");
            return (Criteria) this;
        }

        public Criteria andMaxHealthGreaterThan(Double value) {
            addCriterion("max_health >", value, "maxHealth");
            return (Criteria) this;
        }

        public Criteria andMaxHealthGreaterThanOrEqualTo(Double value) {
            addCriterion("max_health >=", value, "maxHealth");
            return (Criteria) this;
        }

        public Criteria andMaxHealthLessThan(Double value) {
            addCriterion("max_health <", value, "maxHealth");
            return (Criteria) this;
        }

        public Criteria andMaxHealthLessThanOrEqualTo(Double value) {
            addCriterion("max_health <=", value, "maxHealth");
            return (Criteria) this;
        }

        public Criteria andMaxHealthIn(List<Double> values) {
            addCriterion("max_health in", values, "maxHealth");
            return (Criteria) this;
        }

        public Criteria andMaxHealthNotIn(List<Double> values) {
            addCriterion("max_health not in", values, "maxHealth");
            return (Criteria) this;
        }

        public Criteria andMaxHealthBetween(Double value1, Double value2) {
            addCriterion("max_health between", value1, value2, "maxHealth");
            return (Criteria) this;
        }

        public Criteria andMaxHealthNotBetween(Double value1, Double value2) {
            addCriterion("max_health not between", value1, value2, "maxHealth");
            return (Criteria) this;
        }

        public Criteria andCriticalDamageIsNull() {
            addCriterion("critical_damage is null");
            return (Criteria) this;
        }

        public Criteria andCriticalDamageIsNotNull() {
            addCriterion("critical_damage is not null");
            return (Criteria) this;
        }

        public Criteria andCriticalDamageEqualTo(BigDecimal value) {
            addCriterion("critical_damage =", value, "criticalDamage");
            return (Criteria) this;
        }

        public Criteria andCriticalDamageNotEqualTo(BigDecimal value) {
            addCriterion("critical_damage <>", value, "criticalDamage");
            return (Criteria) this;
        }

        public Criteria andCriticalDamageGreaterThan(BigDecimal value) {
            addCriterion("critical_damage >", value, "criticalDamage");
            return (Criteria) this;
        }

        public Criteria andCriticalDamageGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("critical_damage >=", value, "criticalDamage");
            return (Criteria) this;
        }

        public Criteria andCriticalDamageLessThan(BigDecimal value) {
            addCriterion("critical_damage <", value, "criticalDamage");
            return (Criteria) this;
        }

        public Criteria andCriticalDamageLessThanOrEqualTo(BigDecimal value) {
            addCriterion("critical_damage <=", value, "criticalDamage");
            return (Criteria) this;
        }

        public Criteria andCriticalDamageIn(List<BigDecimal> values) {
            addCriterion("critical_damage in", values, "criticalDamage");
            return (Criteria) this;
        }

        public Criteria andCriticalDamageNotIn(List<BigDecimal> values) {
            addCriterion("critical_damage not in", values, "criticalDamage");
            return (Criteria) this;
        }

        public Criteria andCriticalDamageBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("critical_damage between", value1, value2, "criticalDamage");
            return (Criteria) this;
        }

        public Criteria andCriticalDamageNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("critical_damage not between", value1, value2, "criticalDamage");
            return (Criteria) this;
        }

        public Criteria andCriticalRateIsNull() {
            addCriterion("critical_rate is null");
            return (Criteria) this;
        }

        public Criteria andCriticalRateIsNotNull() {
            addCriterion("critical_rate is not null");
            return (Criteria) this;
        }

        public Criteria andCriticalRateEqualTo(BigDecimal value) {
            addCriterion("critical_rate =", value, "criticalRate");
            return (Criteria) this;
        }

        public Criteria andCriticalRateNotEqualTo(BigDecimal value) {
            addCriterion("critical_rate <>", value, "criticalRate");
            return (Criteria) this;
        }

        public Criteria andCriticalRateGreaterThan(BigDecimal value) {
            addCriterion("critical_rate >", value, "criticalRate");
            return (Criteria) this;
        }

        public Criteria andCriticalRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("critical_rate >=", value, "criticalRate");
            return (Criteria) this;
        }

        public Criteria andCriticalRateLessThan(BigDecimal value) {
            addCriterion("critical_rate <", value, "criticalRate");
            return (Criteria) this;
        }

        public Criteria andCriticalRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("critical_rate <=", value, "criticalRate");
            return (Criteria) this;
        }

        public Criteria andCriticalRateIn(List<BigDecimal> values) {
            addCriterion("critical_rate in", values, "criticalRate");
            return (Criteria) this;
        }

        public Criteria andCriticalRateNotIn(List<BigDecimal> values) {
            addCriterion("critical_rate not in", values, "criticalRate");
            return (Criteria) this;
        }

        public Criteria andCriticalRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("critical_rate between", value1, value2, "criticalRate");
            return (Criteria) this;
        }

        public Criteria andCriticalRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("critical_rate not between", value1, value2, "criticalRate");
            return (Criteria) this;
        }

        public Criteria andHealthStealIsNull() {
            addCriterion("health_steal is null");
            return (Criteria) this;
        }

        public Criteria andHealthStealIsNotNull() {
            addCriterion("health_steal is not null");
            return (Criteria) this;
        }

        public Criteria andHealthStealEqualTo(BigDecimal value) {
            addCriterion("health_steal =", value, "healthSteal");
            return (Criteria) this;
        }

        public Criteria andHealthStealNotEqualTo(BigDecimal value) {
            addCriterion("health_steal <>", value, "healthSteal");
            return (Criteria) this;
        }

        public Criteria andHealthStealGreaterThan(BigDecimal value) {
            addCriterion("health_steal >", value, "healthSteal");
            return (Criteria) this;
        }

        public Criteria andHealthStealGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("health_steal >=", value, "healthSteal");
            return (Criteria) this;
        }

        public Criteria andHealthStealLessThan(BigDecimal value) {
            addCriterion("health_steal <", value, "healthSteal");
            return (Criteria) this;
        }

        public Criteria andHealthStealLessThanOrEqualTo(BigDecimal value) {
            addCriterion("health_steal <=", value, "healthSteal");
            return (Criteria) this;
        }

        public Criteria andHealthStealIn(List<BigDecimal> values) {
            addCriterion("health_steal in", values, "healthSteal");
            return (Criteria) this;
        }

        public Criteria andHealthStealNotIn(List<BigDecimal> values) {
            addCriterion("health_steal not in", values, "healthSteal");
            return (Criteria) this;
        }

        public Criteria andHealthStealBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("health_steal between", value1, value2, "healthSteal");
            return (Criteria) this;
        }

        public Criteria andHealthStealNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("health_steal not between", value1, value2, "healthSteal");
            return (Criteria) this;
        }

        public Criteria andDefenseIsNull() {
            addCriterion("defense is null");
            return (Criteria) this;
        }

        public Criteria andDefenseIsNotNull() {
            addCriterion("defense is not null");
            return (Criteria) this;
        }

        public Criteria andDefenseEqualTo(Double value) {
            addCriterion("defense =", value, "defense");
            return (Criteria) this;
        }

        public Criteria andDefenseNotEqualTo(Double value) {
            addCriterion("defense <>", value, "defense");
            return (Criteria) this;
        }

        public Criteria andDefenseGreaterThan(Double value) {
            addCriterion("defense >", value, "defense");
            return (Criteria) this;
        }

        public Criteria andDefenseGreaterThanOrEqualTo(Double value) {
            addCriterion("defense >=", value, "defense");
            return (Criteria) this;
        }

        public Criteria andDefenseLessThan(Double value) {
            addCriterion("defense <", value, "defense");
            return (Criteria) this;
        }

        public Criteria andDefenseLessThanOrEqualTo(Double value) {
            addCriterion("defense <=", value, "defense");
            return (Criteria) this;
        }

        public Criteria andDefenseIn(List<Double> values) {
            addCriterion("defense in", values, "defense");
            return (Criteria) this;
        }

        public Criteria andDefenseNotIn(List<Double> values) {
            addCriterion("defense not in", values, "defense");
            return (Criteria) this;
        }

        public Criteria andDefenseBetween(Double value1, Double value2) {
            addCriterion("defense between", value1, value2, "defense");
            return (Criteria) this;
        }

        public Criteria andDefenseNotBetween(Double value1, Double value2) {
            addCriterion("defense not between", value1, value2, "defense");
            return (Criteria) this;
        }

        public Criteria andDamageReductionIsNull() {
            addCriterion("damage_reduction is null");
            return (Criteria) this;
        }

        public Criteria andDamageReductionIsNotNull() {
            addCriterion("damage_reduction is not null");
            return (Criteria) this;
        }

        public Criteria andDamageReductionEqualTo(Double value) {
            addCriterion("damage_reduction =", value, "damageReduction");
            return (Criteria) this;
        }

        public Criteria andDamageReductionNotEqualTo(Double value) {
            addCriterion("damage_reduction <>", value, "damageReduction");
            return (Criteria) this;
        }

        public Criteria andDamageReductionGreaterThan(Double value) {
            addCriterion("damage_reduction >", value, "damageReduction");
            return (Criteria) this;
        }

        public Criteria andDamageReductionGreaterThanOrEqualTo(Double value) {
            addCriterion("damage_reduction >=", value, "damageReduction");
            return (Criteria) this;
        }

        public Criteria andDamageReductionLessThan(Double value) {
            addCriterion("damage_reduction <", value, "damageReduction");
            return (Criteria) this;
        }

        public Criteria andDamageReductionLessThanOrEqualTo(Double value) {
            addCriterion("damage_reduction <=", value, "damageReduction");
            return (Criteria) this;
        }

        public Criteria andDamageReductionIn(List<Double> values) {
            addCriterion("damage_reduction in", values, "damageReduction");
            return (Criteria) this;
        }

        public Criteria andDamageReductionNotIn(List<Double> values) {
            addCriterion("damage_reduction not in", values, "damageReduction");
            return (Criteria) this;
        }

        public Criteria andDamageReductionBetween(Double value1, Double value2) {
            addCriterion("damage_reduction between", value1, value2, "damageReduction");
            return (Criteria) this;
        }

        public Criteria andDamageReductionNotBetween(Double value1, Double value2) {
            addCriterion("damage_reduction not between", value1, value2, "damageReduction");
            return (Criteria) this;
        }

        public Criteria andPenetrationIsNull() {
            addCriterion("penetration is null");
            return (Criteria) this;
        }

        public Criteria andPenetrationIsNotNull() {
            addCriterion("penetration is not null");
            return (Criteria) this;
        }

        public Criteria andPenetrationEqualTo(Double value) {
            addCriterion("penetration =", value, "penetration");
            return (Criteria) this;
        }

        public Criteria andPenetrationNotEqualTo(Double value) {
            addCriterion("penetration <>", value, "penetration");
            return (Criteria) this;
        }

        public Criteria andPenetrationGreaterThan(Double value) {
            addCriterion("penetration >", value, "penetration");
            return (Criteria) this;
        }

        public Criteria andPenetrationGreaterThanOrEqualTo(Double value) {
            addCriterion("penetration >=", value, "penetration");
            return (Criteria) this;
        }

        public Criteria andPenetrationLessThan(Double value) {
            addCriterion("penetration <", value, "penetration");
            return (Criteria) this;
        }

        public Criteria andPenetrationLessThanOrEqualTo(Double value) {
            addCriterion("penetration <=", value, "penetration");
            return (Criteria) this;
        }

        public Criteria andPenetrationIn(List<Double> values) {
            addCriterion("penetration in", values, "penetration");
            return (Criteria) this;
        }

        public Criteria andPenetrationNotIn(List<Double> values) {
            addCriterion("penetration not in", values, "penetration");
            return (Criteria) this;
        }

        public Criteria andPenetrationBetween(Double value1, Double value2) {
            addCriterion("penetration between", value1, value2, "penetration");
            return (Criteria) this;
        }

        public Criteria andPenetrationNotBetween(Double value1, Double value2) {
            addCriterion("penetration not between", value1, value2, "penetration");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}