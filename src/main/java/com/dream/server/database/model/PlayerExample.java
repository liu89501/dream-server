package com.dream.server.database.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlayerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public PlayerExample() {
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

        public Criteria andPlayerIdIsNull() {
            addCriterion("game_player.player_id is null");
            return (Criteria) this;
        }

        public Criteria andPlayerIdIsNotNull() {
            addCriterion("game_player.player_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlayerIdEqualTo(Integer value) {
            addCriterion("game_player.player_id =", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdNotEqualTo(Integer value) {
            addCriterion("game_player.player_id <>", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdGreaterThan(Integer value) {
            addCriterion("game_player.player_id >", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("game_player.player_id >=", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdLessThan(Integer value) {
            addCriterion("game_player.player_id <", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdLessThanOrEqualTo(Integer value) {
            addCriterion("game_player.player_id <=", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdIn(List<Integer> values) {
            addCriterion("game_player.player_id in", values, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdNotIn(List<Integer> values) {
            addCriterion("game_player.player_id not in", values, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdBetween(Integer value1, Integer value2) {
            addCriterion("game_player.player_id between", value1, value2, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("game_player.player_id not between", value1, value2, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlatformAccountIdIsNull() {
            addCriterion("game_player.platform_account_id is null");
            return (Criteria) this;
        }

        public Criteria andPlatformAccountIdIsNotNull() {
            addCriterion("game_player.platform_account_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformAccountIdEqualTo(String value) {
            addCriterion("game_player.platform_account_id =", value, "platformAccountId");
            return (Criteria) this;
        }

        public Criteria andPlatformAccountIdNotEqualTo(String value) {
            addCriterion("game_player.platform_account_id <>", value, "platformAccountId");
            return (Criteria) this;
        }

        public Criteria andPlatformAccountIdGreaterThan(String value) {
            addCriterion("game_player.platform_account_id >", value, "platformAccountId");
            return (Criteria) this;
        }

        public Criteria andPlatformAccountIdGreaterThanOrEqualTo(String value) {
            addCriterion("game_player.platform_account_id >=", value, "platformAccountId");
            return (Criteria) this;
        }

        public Criteria andPlatformAccountIdLessThan(String value) {
            addCriterion("game_player.platform_account_id <", value, "platformAccountId");
            return (Criteria) this;
        }

        public Criteria andPlatformAccountIdLessThanOrEqualTo(String value) {
            addCriterion("game_player.platform_account_id <=", value, "platformAccountId");
            return (Criteria) this;
        }

        public Criteria andPlatformAccountIdLike(String value) {
            addCriterion("game_player.platform_account_id like", value, "platformAccountId");
            return (Criteria) this;
        }

        public Criteria andPlatformAccountIdNotLike(String value) {
            addCriterion("game_player.platform_account_id not like", value, "platformAccountId");
            return (Criteria) this;
        }

        public Criteria andPlatformAccountIdIn(List<String> values) {
            addCriterion("game_player.platform_account_id in", values, "platformAccountId");
            return (Criteria) this;
        }

        public Criteria andPlatformAccountIdNotIn(List<String> values) {
            addCriterion("game_player.platform_account_id not in", values, "platformAccountId");
            return (Criteria) this;
        }

        public Criteria andPlatformAccountIdBetween(String value1, String value2) {
            addCriterion("game_player.platform_account_id between", value1, value2, "platformAccountId");
            return (Criteria) this;
        }

        public Criteria andPlatformAccountIdNotBetween(String value1, String value2) {
            addCriterion("game_player.platform_account_id not between", value1, value2, "platformAccountId");
            return (Criteria) this;
        }

        public Criteria andPlayerLevelIsNull() {
            addCriterion("game_player.player_level is null");
            return (Criteria) this;
        }

        public Criteria andPlayerLevelIsNotNull() {
            addCriterion("game_player.player_level is not null");
            return (Criteria) this;
        }

        public Criteria andPlayerLevelEqualTo(Integer value) {
            addCriterion("game_player.player_level =", value, "playerLevel");
            return (Criteria) this;
        }

        public Criteria andPlayerLevelNotEqualTo(Integer value) {
            addCriterion("game_player.player_level <>", value, "playerLevel");
            return (Criteria) this;
        }

        public Criteria andPlayerLevelGreaterThan(Integer value) {
            addCriterion("game_player.player_level >", value, "playerLevel");
            return (Criteria) this;
        }

        public Criteria andPlayerLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("game_player.player_level >=", value, "playerLevel");
            return (Criteria) this;
        }

        public Criteria andPlayerLevelLessThan(Integer value) {
            addCriterion("game_player.player_level <", value, "playerLevel");
            return (Criteria) this;
        }

        public Criteria andPlayerLevelLessThanOrEqualTo(Integer value) {
            addCriterion("game_player.player_level <=", value, "playerLevel");
            return (Criteria) this;
        }

        public Criteria andPlayerLevelIn(List<Integer> values) {
            addCriterion("game_player.player_level in", values, "playerLevel");
            return (Criteria) this;
        }

        public Criteria andPlayerLevelNotIn(List<Integer> values) {
            addCriterion("game_player.player_level not in", values, "playerLevel");
            return (Criteria) this;
        }

        public Criteria andPlayerLevelBetween(Integer value1, Integer value2) {
            addCriterion("game_player.player_level between", value1, value2, "playerLevel");
            return (Criteria) this;
        }

        public Criteria andPlayerLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("game_player.player_level not between", value1, value2, "playerLevel");
            return (Criteria) this;
        }

        public Criteria andPlayerExpIsNull() {
            addCriterion("game_player.player_exp is null");
            return (Criteria) this;
        }

        public Criteria andPlayerExpIsNotNull() {
            addCriterion("game_player.player_exp is not null");
            return (Criteria) this;
        }

        public Criteria andPlayerExpEqualTo(Integer value) {
            addCriterion("game_player.player_exp =", value, "playerExp");
            return (Criteria) this;
        }

        public Criteria andPlayerExpNotEqualTo(Integer value) {
            addCriterion("game_player.player_exp <>", value, "playerExp");
            return (Criteria) this;
        }

        public Criteria andPlayerExpGreaterThan(Integer value) {
            addCriterion("game_player.player_exp >", value, "playerExp");
            return (Criteria) this;
        }

        public Criteria andPlayerExpGreaterThanOrEqualTo(Integer value) {
            addCriterion("game_player.player_exp >=", value, "playerExp");
            return (Criteria) this;
        }

        public Criteria andPlayerExpLessThan(Integer value) {
            addCriterion("game_player.player_exp <", value, "playerExp");
            return (Criteria) this;
        }

        public Criteria andPlayerExpLessThanOrEqualTo(Integer value) {
            addCriterion("game_player.player_exp <=", value, "playerExp");
            return (Criteria) this;
        }

        public Criteria andPlayerExpIn(List<Integer> values) {
            addCriterion("game_player.player_exp in", values, "playerExp");
            return (Criteria) this;
        }

        public Criteria andPlayerExpNotIn(List<Integer> values) {
            addCriterion("game_player.player_exp not in", values, "playerExp");
            return (Criteria) this;
        }

        public Criteria andPlayerExpBetween(Integer value1, Integer value2) {
            addCriterion("game_player.player_exp between", value1, value2, "playerExp");
            return (Criteria) this;
        }

        public Criteria andPlayerExpNotBetween(Integer value1, Integer value2) {
            addCriterion("game_player.player_exp not between", value1, value2, "playerExp");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateIsNull() {
            addCriterion("game_player.last_login_date is null");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateIsNotNull() {
            addCriterion("game_player.last_login_date is not null");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateEqualTo(Date value) {
            addCriterion("game_player.last_login_date =", value, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateNotEqualTo(Date value) {
            addCriterion("game_player.last_login_date <>", value, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateGreaterThan(Date value) {
            addCriterion("game_player.last_login_date >", value, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateGreaterThanOrEqualTo(Date value) {
            addCriterion("game_player.last_login_date >=", value, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateLessThan(Date value) {
            addCriterion("game_player.last_login_date <", value, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateLessThanOrEqualTo(Date value) {
            addCriterion("game_player.last_login_date <=", value, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateIn(List<Date> values) {
            addCriterion("game_player.last_login_date in", values, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateNotIn(List<Date> values) {
            addCriterion("game_player.last_login_date not in", values, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateBetween(Date value1, Date value2) {
            addCriterion("game_player.last_login_date between", value1, value2, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateNotBetween(Date value1, Date value2) {
            addCriterion("game_player.last_login_date not between", value1, value2, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLearnedTalentsIsNull() {
            addCriterion("game_player.learned_talents is null");
            return (Criteria) this;
        }

        public Criteria andLearnedTalentsIsNotNull() {
            addCriterion("game_player.learned_talents is not null");
            return (Criteria) this;
        }

        public Criteria andLearnedTalentsEqualTo(Long value) {
            addCriterion("game_player.learned_talents =", value, "learnedTalents");
            return (Criteria) this;
        }

        public Criteria andLearnedTalentsNotEqualTo(Long value) {
            addCriterion("game_player.learned_talents <>", value, "learnedTalents");
            return (Criteria) this;
        }

        public Criteria andLearnedTalentsGreaterThan(Long value) {
            addCriterion("game_player.learned_talents >", value, "learnedTalents");
            return (Criteria) this;
        }

        public Criteria andLearnedTalentsGreaterThanOrEqualTo(Long value) {
            addCriterion("game_player.learned_talents >=", value, "learnedTalents");
            return (Criteria) this;
        }

        public Criteria andLearnedTalentsLessThan(Long value) {
            addCriterion("game_player.learned_talents <", value, "learnedTalents");
            return (Criteria) this;
        }

        public Criteria andLearnedTalentsLessThanOrEqualTo(Long value) {
            addCriterion("game_player.learned_talents <=", value, "learnedTalents");
            return (Criteria) this;
        }

        public Criteria andLearnedTalentsIn(List<Long> values) {
            addCriterion("game_player.learned_talents in", values, "learnedTalents");
            return (Criteria) this;
        }

        public Criteria andLearnedTalentsNotIn(List<Long> values) {
            addCriterion("game_player.learned_talents not in", values, "learnedTalents");
            return (Criteria) this;
        }

        public Criteria andLearnedTalentsBetween(Long value1, Long value2) {
            addCriterion("game_player.learned_talents between", value1, value2, "learnedTalents");
            return (Criteria) this;
        }

        public Criteria andLearnedTalentsNotBetween(Long value1, Long value2) {
            addCriterion("game_player.learned_talents not between", value1, value2, "learnedTalents");
            return (Criteria) this;
        }

        public Criteria andMaxExpIsNull() {
            addCriterion("game_player.max_exp is null");
            return (Criteria) this;
        }

        public Criteria andMaxExpIsNotNull() {
            addCriterion("game_player.max_exp is not null");
            return (Criteria) this;
        }

        public Criteria andMaxExpEqualTo(Integer value) {
            addCriterion("game_player.max_exp =", value, "maxExp");
            return (Criteria) this;
        }

        public Criteria andMaxExpNotEqualTo(Integer value) {
            addCriterion("game_player.max_exp <>", value, "maxExp");
            return (Criteria) this;
        }

        public Criteria andMaxExpGreaterThan(Integer value) {
            addCriterion("game_player.max_exp >", value, "maxExp");
            return (Criteria) this;
        }

        public Criteria andMaxExpGreaterThanOrEqualTo(Integer value) {
            addCriterion("game_player.max_exp >=", value, "maxExp");
            return (Criteria) this;
        }

        public Criteria andMaxExpLessThan(Integer value) {
            addCriterion("game_player.max_exp <", value, "maxExp");
            return (Criteria) this;
        }

        public Criteria andMaxExpLessThanOrEqualTo(Integer value) {
            addCriterion("game_player.max_exp <=", value, "maxExp");
            return (Criteria) this;
        }

        public Criteria andMaxExpIn(List<Integer> values) {
            addCriterion("game_player.max_exp in", values, "maxExp");
            return (Criteria) this;
        }

        public Criteria andMaxExpNotIn(List<Integer> values) {
            addCriterion("game_player.max_exp not in", values, "maxExp");
            return (Criteria) this;
        }

        public Criteria andMaxExpBetween(Integer value1, Integer value2) {
            addCriterion("game_player.max_exp between", value1, value2, "maxExp");
            return (Criteria) this;
        }

        public Criteria andMaxExpNotBetween(Integer value1, Integer value2) {
            addCriterion("game_player.max_exp not between", value1, value2, "maxExp");
            return (Criteria) this;
        }

        public Criteria andLoginHostIsNull() {
            addCriterion("game_player.login_host is null");
            return (Criteria) this;
        }

        public Criteria andLoginHostIsNotNull() {
            addCriterion("game_player.login_host is not null");
            return (Criteria) this;
        }

        public Criteria andLoginHostEqualTo(String value) {
            addCriterion("game_player.login_host =", value, "loginHost");
            return (Criteria) this;
        }

        public Criteria andLoginHostNotEqualTo(String value) {
            addCriterion("game_player.login_host <>", value, "loginHost");
            return (Criteria) this;
        }

        public Criteria andLoginHostGreaterThan(String value) {
            addCriterion("game_player.login_host >", value, "loginHost");
            return (Criteria) this;
        }

        public Criteria andLoginHostGreaterThanOrEqualTo(String value) {
            addCriterion("game_player.login_host >=", value, "loginHost");
            return (Criteria) this;
        }

        public Criteria andLoginHostLessThan(String value) {
            addCriterion("game_player.login_host <", value, "loginHost");
            return (Criteria) this;
        }

        public Criteria andLoginHostLessThanOrEqualTo(String value) {
            addCriterion("game_player.login_host <=", value, "loginHost");
            return (Criteria) this;
        }

        public Criteria andLoginHostLike(String value) {
            addCriterion("game_player.login_host like", value, "loginHost");
            return (Criteria) this;
        }

        public Criteria andLoginHostNotLike(String value) {
            addCriterion("game_player.login_host not like", value, "loginHost");
            return (Criteria) this;
        }

        public Criteria andLoginHostIn(List<String> values) {
            addCriterion("game_player.login_host in", values, "loginHost");
            return (Criteria) this;
        }

        public Criteria andLoginHostNotIn(List<String> values) {
            addCriterion("game_player.login_host not in", values, "loginHost");
            return (Criteria) this;
        }

        public Criteria andLoginHostBetween(String value1, String value2) {
            addCriterion("game_player.login_host between", value1, value2, "loginHost");
            return (Criteria) this;
        }

        public Criteria andLoginHostNotBetween(String value1, String value2) {
            addCriterion("game_player.login_host not between", value1, value2, "loginHost");
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