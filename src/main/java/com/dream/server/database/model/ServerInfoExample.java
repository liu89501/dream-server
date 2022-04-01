package com.dream.server.database.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServerInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public ServerInfoExample() {
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

        public Criteria andServerIdIsNull() {
            addCriterion("server_id is null");
            return (Criteria) this;
        }

        public Criteria andServerIdIsNotNull() {
            addCriterion("server_id is not null");
            return (Criteria) this;
        }

        public Criteria andServerIdEqualTo(String value) {
            addCriterion("server_id =", value, "serverId");
            return (Criteria) this;
        }

        public Criteria andServerIdNotEqualTo(String value) {
            addCriterion("server_id <>", value, "serverId");
            return (Criteria) this;
        }

        public Criteria andServerIdGreaterThan(String value) {
            addCriterion("server_id >", value, "serverId");
            return (Criteria) this;
        }

        public Criteria andServerIdGreaterThanOrEqualTo(String value) {
            addCriterion("server_id >=", value, "serverId");
            return (Criteria) this;
        }

        public Criteria andServerIdLessThan(String value) {
            addCriterion("server_id <", value, "serverId");
            return (Criteria) this;
        }

        public Criteria andServerIdLessThanOrEqualTo(String value) {
            addCriterion("server_id <=", value, "serverId");
            return (Criteria) this;
        }

        public Criteria andServerIdLike(String value) {
            addCriterion("server_id like", value, "serverId");
            return (Criteria) this;
        }

        public Criteria andServerIdNotLike(String value) {
            addCriterion("server_id not like", value, "serverId");
            return (Criteria) this;
        }

        public Criteria andServerIdIn(List<String> values) {
            addCriterion("server_id in", values, "serverId");
            return (Criteria) this;
        }

        public Criteria andServerIdNotIn(List<String> values) {
            addCriterion("server_id not in", values, "serverId");
            return (Criteria) this;
        }

        public Criteria andServerIdBetween(String value1, String value2) {
            addCriterion("server_id between", value1, value2, "serverId");
            return (Criteria) this;
        }

        public Criteria andServerIdNotBetween(String value1, String value2) {
            addCriterion("server_id not between", value1, value2, "serverId");
            return (Criteria) this;
        }

        public Criteria andServerAddrIsNull() {
            addCriterion("server_addr is null");
            return (Criteria) this;
        }

        public Criteria andServerAddrIsNotNull() {
            addCriterion("server_addr is not null");
            return (Criteria) this;
        }

        public Criteria andServerAddrEqualTo(String value) {
            addCriterion("server_addr =", value, "serverAddr");
            return (Criteria) this;
        }

        public Criteria andServerAddrNotEqualTo(String value) {
            addCriterion("server_addr <>", value, "serverAddr");
            return (Criteria) this;
        }

        public Criteria andServerAddrGreaterThan(String value) {
            addCriterion("server_addr >", value, "serverAddr");
            return (Criteria) this;
        }

        public Criteria andServerAddrGreaterThanOrEqualTo(String value) {
            addCriterion("server_addr >=", value, "serverAddr");
            return (Criteria) this;
        }

        public Criteria andServerAddrLessThan(String value) {
            addCriterion("server_addr <", value, "serverAddr");
            return (Criteria) this;
        }

        public Criteria andServerAddrLessThanOrEqualTo(String value) {
            addCriterion("server_addr <=", value, "serverAddr");
            return (Criteria) this;
        }

        public Criteria andServerAddrLike(String value) {
            addCriterion("server_addr like", value, "serverAddr");
            return (Criteria) this;
        }

        public Criteria andServerAddrNotLike(String value) {
            addCriterion("server_addr not like", value, "serverAddr");
            return (Criteria) this;
        }

        public Criteria andServerAddrIn(List<String> values) {
            addCriterion("server_addr in", values, "serverAddr");
            return (Criteria) this;
        }

        public Criteria andServerAddrNotIn(List<String> values) {
            addCriterion("server_addr not in", values, "serverAddr");
            return (Criteria) this;
        }

        public Criteria andServerAddrBetween(String value1, String value2) {
            addCriterion("server_addr between", value1, value2, "serverAddr");
            return (Criteria) this;
        }

        public Criteria andServerAddrNotBetween(String value1, String value2) {
            addCriterion("server_addr not between", value1, value2, "serverAddr");
            return (Criteria) this;
        }

        public Criteria andMapNameIsNull() {
            addCriterion("map_name is null");
            return (Criteria) this;
        }

        public Criteria andMapNameIsNotNull() {
            addCriterion("map_name is not null");
            return (Criteria) this;
        }

        public Criteria andMapNameEqualTo(String value) {
            addCriterion("map_name =", value, "mapName");
            return (Criteria) this;
        }

        public Criteria andMapNameNotEqualTo(String value) {
            addCriterion("map_name <>", value, "mapName");
            return (Criteria) this;
        }

        public Criteria andMapNameGreaterThan(String value) {
            addCriterion("map_name >", value, "mapName");
            return (Criteria) this;
        }

        public Criteria andMapNameGreaterThanOrEqualTo(String value) {
            addCriterion("map_name >=", value, "mapName");
            return (Criteria) this;
        }

        public Criteria andMapNameLessThan(String value) {
            addCriterion("map_name <", value, "mapName");
            return (Criteria) this;
        }

        public Criteria andMapNameLessThanOrEqualTo(String value) {
            addCriterion("map_name <=", value, "mapName");
            return (Criteria) this;
        }

        public Criteria andMapNameLike(String value) {
            addCriterion("map_name like", value, "mapName");
            return (Criteria) this;
        }

        public Criteria andMapNameNotLike(String value) {
            addCriterion("map_name not like", value, "mapName");
            return (Criteria) this;
        }

        public Criteria andMapNameIn(List<String> values) {
            addCriterion("map_name in", values, "mapName");
            return (Criteria) this;
        }

        public Criteria andMapNameNotIn(List<String> values) {
            addCriterion("map_name not in", values, "mapName");
            return (Criteria) this;
        }

        public Criteria andMapNameBetween(String value1, String value2) {
            addCriterion("map_name between", value1, value2, "mapName");
            return (Criteria) this;
        }

        public Criteria andMapNameNotBetween(String value1, String value2) {
            addCriterion("map_name not between", value1, value2, "mapName");
            return (Criteria) this;
        }

        public Criteria andGameModeIsNull() {
            addCriterion("game_mode is null");
            return (Criteria) this;
        }

        public Criteria andGameModeIsNotNull() {
            addCriterion("game_mode is not null");
            return (Criteria) this;
        }

        public Criteria andGameModeEqualTo(String value) {
            addCriterion("game_mode =", value, "gameMode");
            return (Criteria) this;
        }

        public Criteria andGameModeNotEqualTo(String value) {
            addCriterion("game_mode <>", value, "gameMode");
            return (Criteria) this;
        }

        public Criteria andGameModeGreaterThan(String value) {
            addCriterion("game_mode >", value, "gameMode");
            return (Criteria) this;
        }

        public Criteria andGameModeGreaterThanOrEqualTo(String value) {
            addCriterion("game_mode >=", value, "gameMode");
            return (Criteria) this;
        }

        public Criteria andGameModeLessThan(String value) {
            addCriterion("game_mode <", value, "gameMode");
            return (Criteria) this;
        }

        public Criteria andGameModeLessThanOrEqualTo(String value) {
            addCriterion("game_mode <=", value, "gameMode");
            return (Criteria) this;
        }

        public Criteria andGameModeLike(String value) {
            addCriterion("game_mode like", value, "gameMode");
            return (Criteria) this;
        }

        public Criteria andGameModeNotLike(String value) {
            addCriterion("game_mode not like", value, "gameMode");
            return (Criteria) this;
        }

        public Criteria andGameModeIn(List<String> values) {
            addCriterion("game_mode in", values, "gameMode");
            return (Criteria) this;
        }

        public Criteria andGameModeNotIn(List<String> values) {
            addCriterion("game_mode not in", values, "gameMode");
            return (Criteria) this;
        }

        public Criteria andGameModeBetween(String value1, String value2) {
            addCriterion("game_mode between", value1, value2, "gameMode");
            return (Criteria) this;
        }

        public Criteria andGameModeNotBetween(String value1, String value2) {
            addCriterion("game_mode not between", value1, value2, "gameMode");
            return (Criteria) this;
        }

        public Criteria andActivePlayersIsNull() {
            addCriterion("active_players is null");
            return (Criteria) this;
        }

        public Criteria andActivePlayersIsNotNull() {
            addCriterion("active_players is not null");
            return (Criteria) this;
        }

        public Criteria andActivePlayersEqualTo(Integer value) {
            addCriterion("active_players =", value, "activePlayers");
            return (Criteria) this;
        }

        public Criteria andActivePlayersNotEqualTo(Integer value) {
            addCriterion("active_players <>", value, "activePlayers");
            return (Criteria) this;
        }

        public Criteria andActivePlayersGreaterThan(Integer value) {
            addCriterion("active_players >", value, "activePlayers");
            return (Criteria) this;
        }

        public Criteria andActivePlayersGreaterThanOrEqualTo(Integer value) {
            addCriterion("active_players >=", value, "activePlayers");
            return (Criteria) this;
        }

        public Criteria andActivePlayersLessThan(Integer value) {
            addCriterion("active_players <", value, "activePlayers");
            return (Criteria) this;
        }

        public Criteria andActivePlayersLessThanOrEqualTo(Integer value) {
            addCriterion("active_players <=", value, "activePlayers");
            return (Criteria) this;
        }

        public Criteria andActivePlayersIn(List<Integer> values) {
            addCriterion("active_players in", values, "activePlayers");
            return (Criteria) this;
        }

        public Criteria andActivePlayersNotIn(List<Integer> values) {
            addCriterion("active_players not in", values, "activePlayers");
            return (Criteria) this;
        }

        public Criteria andActivePlayersBetween(Integer value1, Integer value2) {
            addCriterion("active_players between", value1, value2, "activePlayers");
            return (Criteria) this;
        }

        public Criteria andActivePlayersNotBetween(Integer value1, Integer value2) {
            addCriterion("active_players not between", value1, value2, "activePlayers");
            return (Criteria) this;
        }

        public Criteria andMaxPlayersIsNull() {
            addCriterion("max_players is null");
            return (Criteria) this;
        }

        public Criteria andMaxPlayersIsNotNull() {
            addCriterion("max_players is not null");
            return (Criteria) this;
        }

        public Criteria andMaxPlayersEqualTo(Integer value) {
            addCriterion("max_players =", value, "maxPlayers");
            return (Criteria) this;
        }

        public Criteria andMaxPlayersNotEqualTo(Integer value) {
            addCriterion("max_players <>", value, "maxPlayers");
            return (Criteria) this;
        }

        public Criteria andMaxPlayersGreaterThan(Integer value) {
            addCriterion("max_players >", value, "maxPlayers");
            return (Criteria) this;
        }

        public Criteria andMaxPlayersGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_players >=", value, "maxPlayers");
            return (Criteria) this;
        }

        public Criteria andMaxPlayersLessThan(Integer value) {
            addCriterion("max_players <", value, "maxPlayers");
            return (Criteria) this;
        }

        public Criteria andMaxPlayersLessThanOrEqualTo(Integer value) {
            addCriterion("max_players <=", value, "maxPlayers");
            return (Criteria) this;
        }

        public Criteria andMaxPlayersIn(List<Integer> values) {
            addCriterion("max_players in", values, "maxPlayers");
            return (Criteria) this;
        }

        public Criteria andMaxPlayersNotIn(List<Integer> values) {
            addCriterion("max_players not in", values, "maxPlayers");
            return (Criteria) this;
        }

        public Criteria andMaxPlayersBetween(Integer value1, Integer value2) {
            addCriterion("max_players between", value1, value2, "maxPlayers");
            return (Criteria) this;
        }

        public Criteria andMaxPlayersNotBetween(Integer value1, Integer value2) {
            addCriterion("max_players not between", value1, value2, "maxPlayers");
            return (Criteria) this;
        }

        public Criteria andActiveStateIsNull() {
            addCriterion("active_state is null");
            return (Criteria) this;
        }

        public Criteria andActiveStateIsNotNull() {
            addCriterion("active_state is not null");
            return (Criteria) this;
        }

        public Criteria andActiveStateEqualTo(Integer value) {
            addCriterion("active_state =", value, "activeState");
            return (Criteria) this;
        }

        public Criteria andActiveStateNotEqualTo(Integer value) {
            addCriterion("active_state <>", value, "activeState");
            return (Criteria) this;
        }

        public Criteria andActiveStateGreaterThan(Integer value) {
            addCriterion("active_state >", value, "activeState");
            return (Criteria) this;
        }

        public Criteria andActiveStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("active_state >=", value, "activeState");
            return (Criteria) this;
        }

        public Criteria andActiveStateLessThan(Integer value) {
            addCriterion("active_state <", value, "activeState");
            return (Criteria) this;
        }

        public Criteria andActiveStateLessThanOrEqualTo(Integer value) {
            addCriterion("active_state <=", value, "activeState");
            return (Criteria) this;
        }

        public Criteria andActiveStateIn(List<Integer> values) {
            addCriterion("active_state in", values, "activeState");
            return (Criteria) this;
        }

        public Criteria andActiveStateNotIn(List<Integer> values) {
            addCriterion("active_state not in", values, "activeState");
            return (Criteria) this;
        }

        public Criteria andActiveStateBetween(Integer value1, Integer value2) {
            addCriterion("active_state between", value1, value2, "activeState");
            return (Criteria) this;
        }

        public Criteria andActiveStateNotBetween(Integer value1, Integer value2) {
            addCriterion("active_state not between", value1, value2, "activeState");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
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