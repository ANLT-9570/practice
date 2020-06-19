package com.yzl.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class YzlTaskProgressSheetExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public YzlTaskProgressSheetExample() {
        oredCriteria = new ArrayList<Criteria>();
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

    /**
     * 任务进度工作表
     * 
     * @author wcyong
     * 
     * @date 2018-12-19
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTaskprogressIsNull() {
            addCriterion("taskProgress is null");
            return (Criteria) this;
        }

        public Criteria andTaskprogressIsNotNull() {
            addCriterion("taskProgress is not null");
            return (Criteria) this;
        }

        public Criteria andTaskprogressEqualTo(Float value) {
            addCriterion("taskProgress =", value, "taskprogress");
            return (Criteria) this;
        }

        public Criteria andTaskprogressNotEqualTo(Float value) {
            addCriterion("taskProgress <>", value, "taskprogress");
            return (Criteria) this;
        }

        public Criteria andTaskprogressGreaterThan(Float value) {
            addCriterion("taskProgress >", value, "taskprogress");
            return (Criteria) this;
        }

        public Criteria andTaskprogressGreaterThanOrEqualTo(Float value) {
            addCriterion("taskProgress >=", value, "taskprogress");
            return (Criteria) this;
        }

        public Criteria andTaskprogressLessThan(Float value) {
            addCriterion("taskProgress <", value, "taskprogress");
            return (Criteria) this;
        }

        public Criteria andTaskprogressLessThanOrEqualTo(Float value) {
            addCriterion("taskProgress <=", value, "taskprogress");
            return (Criteria) this;
        }

        public Criteria andTaskprogressIn(List<Float> values) {
            addCriterion("taskProgress in", values, "taskprogress");
            return (Criteria) this;
        }

        public Criteria andTaskprogressNotIn(List<Float> values) {
            addCriterion("taskProgress not in", values, "taskprogress");
            return (Criteria) this;
        }

        public Criteria andTaskprogressBetween(Float value1, Float value2) {
            addCriterion("taskProgress between", value1, value2, "taskprogress");
            return (Criteria) this;
        }

        public Criteria andTaskprogressNotBetween(Float value1, Float value2) {
            addCriterion("taskProgress not between", value1, value2, "taskprogress");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createTime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andEpcodeIsNull() {
            addCriterion("epcode is null");
            return (Criteria) this;
        }

        public Criteria andEpcodeIsNotNull() {
            addCriterion("epcode is not null");
            return (Criteria) this;
        }

        public Criteria andEpcodeEqualTo(Integer value) {
            addCriterion("epcode =", value, "epcode");
            return (Criteria) this;
        }

        public Criteria andEpcodeNotEqualTo(Integer value) {
            addCriterion("epcode <>", value, "epcode");
            return (Criteria) this;
        }

        public Criteria andEpcodeGreaterThan(Integer value) {
            addCriterion("epcode >", value, "epcode");
            return (Criteria) this;
        }

        public Criteria andEpcodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("epcode >=", value, "epcode");
            return (Criteria) this;
        }

        public Criteria andEpcodeLessThan(Integer value) {
            addCriterion("epcode <", value, "epcode");
            return (Criteria) this;
        }

        public Criteria andEpcodeLessThanOrEqualTo(Integer value) {
            addCriterion("epcode <=", value, "epcode");
            return (Criteria) this;
        }

        public Criteria andEpcodeIn(List<Integer> values) {
            addCriterion("epcode in", values, "epcode");
            return (Criteria) this;
        }

        public Criteria andEpcodeNotIn(List<Integer> values) {
            addCriterion("epcode not in", values, "epcode");
            return (Criteria) this;
        }

        public Criteria andEpcodeBetween(Integer value1, Integer value2) {
            addCriterion("epcode between", value1, value2, "epcode");
            return (Criteria) this;
        }

        public Criteria andEpcodeNotBetween(Integer value1, Integer value2) {
            addCriterion("epcode not between", value1, value2, "epcode");
            return (Criteria) this;
        }

        public Criteria andTpcodeIsNull() {
            addCriterion("tpcode is null");
            return (Criteria) this;
        }

        public Criteria andTpcodeIsNotNull() {
            addCriterion("tpcode is not null");
            return (Criteria) this;
        }

        public Criteria andTpcodeEqualTo(Integer value) {
            addCriterion("tpcode =", value, "tpcode");
            return (Criteria) this;
        }

        public Criteria andTpcodeNotEqualTo(Integer value) {
            addCriterion("tpcode <>", value, "tpcode");
            return (Criteria) this;
        }

        public Criteria andTpcodeGreaterThan(Integer value) {
            addCriterion("tpcode >", value, "tpcode");
            return (Criteria) this;
        }

        public Criteria andTpcodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("tpcode >=", value, "tpcode");
            return (Criteria) this;
        }

        public Criteria andTpcodeLessThan(Integer value) {
            addCriterion("tpcode <", value, "tpcode");
            return (Criteria) this;
        }

        public Criteria andTpcodeLessThanOrEqualTo(Integer value) {
            addCriterion("tpcode <=", value, "tpcode");
            return (Criteria) this;
        }

        public Criteria andTpcodeIn(List<Integer> values) {
            addCriterion("tpcode in", values, "tpcode");
            return (Criteria) this;
        }

        public Criteria andTpcodeNotIn(List<Integer> values) {
            addCriterion("tpcode not in", values, "tpcode");
            return (Criteria) this;
        }

        public Criteria andTpcodeBetween(Integer value1, Integer value2) {
            addCriterion("tpcode between", value1, value2, "tpcode");
            return (Criteria) this;
        }

        public Criteria andTpcodeNotBetween(Integer value1, Integer value2) {
            addCriterion("tpcode not between", value1, value2, "tpcode");
            return (Criteria) this;
        }

        public Criteria andDpcodeIsNull() {
            addCriterion("dpcode is null");
            return (Criteria) this;
        }

        public Criteria andDpcodeIsNotNull() {
            addCriterion("dpcode is not null");
            return (Criteria) this;
        }

        public Criteria andDpcodeEqualTo(Integer value) {
            addCriterion("dpcode =", value, "dpcode");
            return (Criteria) this;
        }

        public Criteria andDpcodeNotEqualTo(Integer value) {
            addCriterion("dpcode <>", value, "dpcode");
            return (Criteria) this;
        }

        public Criteria andDpcodeGreaterThan(Integer value) {
            addCriterion("dpcode >", value, "dpcode");
            return (Criteria) this;
        }

        public Criteria andDpcodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("dpcode >=", value, "dpcode");
            return (Criteria) this;
        }

        public Criteria andDpcodeLessThan(Integer value) {
            addCriterion("dpcode <", value, "dpcode");
            return (Criteria) this;
        }

        public Criteria andDpcodeLessThanOrEqualTo(Integer value) {
            addCriterion("dpcode <=", value, "dpcode");
            return (Criteria) this;
        }

        public Criteria andDpcodeIn(List<Integer> values) {
            addCriterion("dpcode in", values, "dpcode");
            return (Criteria) this;
        }

        public Criteria andDpcodeNotIn(List<Integer> values) {
            addCriterion("dpcode not in", values, "dpcode");
            return (Criteria) this;
        }

        public Criteria andDpcodeBetween(Integer value1, Integer value2) {
            addCriterion("dpcode between", value1, value2, "dpcode");
            return (Criteria) this;
        }

        public Criteria andDpcodeNotBetween(Integer value1, Integer value2) {
            addCriterion("dpcode not between", value1, value2, "dpcode");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("creator like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("creator not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andModifierIsNull() {
            addCriterion("modifier is null");
            return (Criteria) this;
        }

        public Criteria andModifierIsNotNull() {
            addCriterion("modifier is not null");
            return (Criteria) this;
        }

        public Criteria andModifierEqualTo(String value) {
            addCriterion("modifier =", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotEqualTo(String value) {
            addCriterion("modifier <>", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThan(String value) {
            addCriterion("modifier >", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThanOrEqualTo(String value) {
            addCriterion("modifier >=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThan(String value) {
            addCriterion("modifier <", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThanOrEqualTo(String value) {
            addCriterion("modifier <=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLike(String value) {
            addCriterion("modifier like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotLike(String value) {
            addCriterion("modifier not like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierIn(List<String> values) {
            addCriterion("modifier in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotIn(List<String> values) {
            addCriterion("modifier not in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierBetween(String value1, String value2) {
            addCriterion("modifier between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotBetween(String value1, String value2) {
            addCriterion("modifier not between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updateTime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updateTime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updateTime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updateTime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updateTime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updateTime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updateTime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updateTime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updateTime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updateTime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updateTime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updateTime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andMarkIsNull() {
            addCriterion("mark is null");
            return (Criteria) this;
        }

        public Criteria andMarkIsNotNull() {
            addCriterion("mark is not null");
            return (Criteria) this;
        }

        public Criteria andMarkEqualTo(String value) {
            addCriterion("mark =", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotEqualTo(String value) {
            addCriterion("mark <>", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkGreaterThan(String value) {
            addCriterion("mark >", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkGreaterThanOrEqualTo(String value) {
            addCriterion("mark >=", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkLessThan(String value) {
            addCriterion("mark <", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkLessThanOrEqualTo(String value) {
            addCriterion("mark <=", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkLike(String value) {
            addCriterion("mark like", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotLike(String value) {
            addCriterion("mark not like", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkIn(List<String> values) {
            addCriterion("mark in", values, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotIn(List<String> values) {
            addCriterion("mark not in", values, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkBetween(String value1, String value2) {
            addCriterion("mark between", value1, value2, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotBetween(String value1, String value2) {
            addCriterion("mark not between", value1, value2, "mark");
            return (Criteria) this;
        }

        public Criteria andSendbackIsNull() {
            addCriterion("sendBack is null");
            return (Criteria) this;
        }

        public Criteria andSendbackIsNotNull() {
            addCriterion("sendBack is not null");
            return (Criteria) this;
        }

        public Criteria andSendbackEqualTo(Integer value) {
            addCriterion("sendBack =", value, "sendback");
            return (Criteria) this;
        }

        public Criteria andSendbackNotEqualTo(Integer value) {
            addCriterion("sendBack <>", value, "sendback");
            return (Criteria) this;
        }

        public Criteria andSendbackGreaterThan(Integer value) {
            addCriterion("sendBack >", value, "sendback");
            return (Criteria) this;
        }

        public Criteria andSendbackGreaterThanOrEqualTo(Integer value) {
            addCriterion("sendBack >=", value, "sendback");
            return (Criteria) this;
        }

        public Criteria andSendbackLessThan(Integer value) {
            addCriterion("sendBack <", value, "sendback");
            return (Criteria) this;
        }

        public Criteria andSendbackLessThanOrEqualTo(Integer value) {
            addCriterion("sendBack <=", value, "sendback");
            return (Criteria) this;
        }

        public Criteria andSendbackIn(List<Integer> values) {
            addCriterion("sendBack in", values, "sendback");
            return (Criteria) this;
        }

        public Criteria andSendbackNotIn(List<Integer> values) {
            addCriterion("sendBack not in", values, "sendback");
            return (Criteria) this;
        }

        public Criteria andSendbackBetween(Integer value1, Integer value2) {
            addCriterion("sendBack between", value1, value2, "sendback");
            return (Criteria) this;
        }

        public Criteria andSendbackNotBetween(Integer value1, Integer value2) {
            addCriterion("sendBack not between", value1, value2, "sendback");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * 任务进度工作表
     * 
     * @author wcyong
     * 
     * @date 2018-12-19
     */
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