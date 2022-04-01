package com.dream.server.database.model;

import java.util.ArrayList;
import java.util.List;

public class PlayerTaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public PlayerTaskExample() {
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

        public Criteria andPtidIsNull() {
            addCriterion("player_task.ptid is null");
            return (Criteria) this;
        }

        public Criteria andPtidIsNotNull() {
            addCriterion("player_task.ptid is not null");
            return (Criteria) this;
        }

        public Criteria andGroupIdEqualTo(Integer value) {
            addCriterion("tt.group_id =", value, "group_id");
            return (Criteria) this;
        }

        public Criteria andPtidEqualTo(Long value) {
            addCriterion("player_task.ptid =", value, "ptid");
            return (Criteria) this;
        }

        public Criteria andPtidNotEqualTo(Long value) {
            addCriterion("player_task.ptid <>", value, "ptid");
            return (Criteria) this;
        }

        public Criteria andPtidGreaterThan(Long value) {
            addCriterion("player_task.ptid >", value, "ptid");
            return (Criteria) this;
        }

        public Criteria andPtidGreaterThanOrEqualTo(Long value) {
            addCriterion("player_task.ptid >=", value, "ptid");
            return (Criteria) this;
        }

        public Criteria andPtidLessThan(Long value) {
            addCriterion("player_task.ptid <", value, "ptid");
            return (Criteria) this;
        }

        public Criteria andPtidLessThanOrEqualTo(Long value) {
            addCriterion("player_task.ptid <=", value, "ptid");
            return (Criteria) this;
        }

        public Criteria andPtidIn(List<Long> values) {
            addCriterion("player_task.ptid in", values, "ptid");
            return (Criteria) this;
        }

        public Criteria andPtidNotIn(List<Long> values) {
            addCriterion("player_task.ptid not in", values, "ptid");
            return (Criteria) this;
        }

        public Criteria andPtidBetween(Long value1, Long value2) {
            addCriterion("player_task.ptid between", value1, value2, "ptid");
            return (Criteria) this;
        }

        public Criteria andPtidNotBetween(Long value1, Long value2) {
            addCriterion("player_task.ptid not between", value1, value2, "ptid");
            return (Criteria) this;
        }

        public Criteria andTaskStateIsNull() {
            addCriterion("player_task.task_state is null");
            return (Criteria) this;
        }

        public Criteria andTaskStateIsNotNull() {
            addCriterion("player_task.task_state is not null");
            return (Criteria) this;
        }

        public Criteria andTaskStateEqualTo(Integer value) {
            addCriterion("player_task.task_state =", value, "taskState");
            return (Criteria) this;
        }

        public Criteria andTaskStateNotEqualTo(Integer value) {
            addCriterion("player_task.task_state <>", value, "taskState");
            return (Criteria) this;
        }

        public Criteria andTaskStateGreaterThan(Integer value) {
            addCriterion("player_task.task_state >", value, "taskState");
            return (Criteria) this;
        }

        public Criteria andTaskStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("player_task.task_state >=", value, "taskState");
            return (Criteria) this;
        }

        public Criteria andTaskStateLessThan(Integer value) {
            addCriterion("player_task.task_state <", value, "taskState");
            return (Criteria) this;
        }

        public Criteria andTaskStateLessThanOrEqualTo(Integer value) {
            addCriterion("player_task.task_state <=", value, "taskState");
            return (Criteria) this;
        }

        public Criteria andTaskStateIn(List<Integer> values) {
            addCriterion("player_task.task_state in", values, "taskState");
            return (Criteria) this;
        }

        public Criteria andTaskStateNotIn(List<Integer> values) {
            addCriterion("player_task.task_state not in", values, "taskState");
            return (Criteria) this;
        }

        public Criteria andTaskStateBetween(Integer value1, Integer value2) {
            addCriterion("player_task.task_state between", value1, value2, "taskState");
            return (Criteria) this;
        }

        public Criteria andTaskStateNotBetween(Integer value1, Integer value2) {
            addCriterion("player_task.task_state not between", value1, value2, "taskState");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNull() {
            addCriterion("player_task.task_id is null");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNotNull() {
            addCriterion("player_task.task_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaskIdEqualTo(Integer value) {
            addCriterion("player_task.task_id =", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotEqualTo(Integer value) {
            addCriterion("player_task.task_id <>", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThan(Integer value) {
            addCriterion("player_task.task_id >", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("player_task.task_id >=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThan(Integer value) {
            addCriterion("player_task.task_id <", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThanOrEqualTo(Integer value) {
            addCriterion("player_task.task_id <=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIn(List<Integer> values) {
            addCriterion("player_task.task_id in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotIn(List<Integer> values) {
            addCriterion("player_task.task_id not in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdBetween(Integer value1, Integer value2) {
            addCriterion("player_task.task_id between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotBetween(Integer value1, Integer value2) {
            addCriterion("player_task.task_id not between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdIsNull() {
            addCriterion("player_task.player_id is null");
            return (Criteria) this;
        }

        public Criteria andPlayerIdIsNotNull() {
            addCriterion("player_task.player_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlayerIdEqualTo(Integer value) {
            addCriterion("player_task.player_id =", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdNotEqualTo(Integer value) {
            addCriterion("player_task.player_id <>", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdGreaterThan(Integer value) {
            addCriterion("player_task.player_id >", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("player_task.player_id >=", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdLessThan(Integer value) {
            addCriterion("player_task.player_id <", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdLessThanOrEqualTo(Integer value) {
            addCriterion("player_task.player_id <=", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdIn(List<Integer> values) {
            addCriterion("player_task.player_id in", values, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdNotIn(List<Integer> values) {
            addCriterion("player_task.player_id not in", values, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdBetween(Integer value1, Integer value2) {
            addCriterion("player_task.player_id between", value1, value2, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("player_task.player_id not between", value1, value2, "playerId");
            return (Criteria) this;
        }

        public Criteria andTaskTrackingIsNull() {
            addCriterion("player_task.task_tracking is null");
            return (Criteria) this;
        }

        public Criteria andTaskTrackingIsNotNull() {
            addCriterion("player_task.task_tracking is not null");
            return (Criteria) this;
        }

        public Criteria andTaskTrackingEqualTo(Boolean value) {
            addCriterion("player_task.task_tracking =", value, "taskTracking");
            return (Criteria) this;
        }

        public Criteria andTaskTrackingNotEqualTo(Boolean value) {
            addCriterion("player_task.task_tracking <>", value, "taskTracking");
            return (Criteria) this;
        }

        public Criteria andTaskTrackingGreaterThan(Boolean value) {
            addCriterion("player_task.task_tracking >", value, "taskTracking");
            return (Criteria) this;
        }

        public Criteria andTaskTrackingGreaterThanOrEqualTo(Boolean value) {
            addCriterion("player_task.task_tracking >=", value, "taskTracking");
            return (Criteria) this;
        }

        public Criteria andTaskTrackingLessThan(Boolean value) {
            addCriterion("player_task.task_tracking <", value, "taskTracking");
            return (Criteria) this;
        }

        public Criteria andTaskTrackingLessThanOrEqualTo(Boolean value) {
            addCriterion("player_task.task_tracking <=", value, "taskTracking");
            return (Criteria) this;
        }

        public Criteria andTaskTrackingIn(List<Boolean> values) {
            addCriterion("player_task.task_tracking in", values, "taskTracking");
            return (Criteria) this;
        }

        public Criteria andTaskTrackingNotIn(List<Boolean> values) {
            addCriterion("player_task.task_tracking not in", values, "taskTracking");
            return (Criteria) this;
        }

        public Criteria andTaskTrackingBetween(Boolean value1, Boolean value2) {
            addCriterion("player_task.task_tracking between", value1, value2, "taskTracking");
            return (Criteria) this;
        }

        public Criteria andTaskTrackingNotBetween(Boolean value1, Boolean value2) {
            addCriterion("player_task.task_tracking not between", value1, value2, "taskTracking");
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