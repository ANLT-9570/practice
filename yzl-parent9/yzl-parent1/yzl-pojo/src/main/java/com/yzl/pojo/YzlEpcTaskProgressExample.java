package com.yzl.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class YzlEpcTaskProgressExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public YzlEpcTaskProgressExample() {
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
     * 
     * 
     * @author wcyong
     * 
     * @date 2019-04-27
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

        public Criteria andGclbIsNull() {
            addCriterion("GCLB is null");
            return (Criteria) this;
        }

        public Criteria andGclbIsNotNull() {
            addCriterion("GCLB is not null");
            return (Criteria) this;
        }

        public Criteria andGclbEqualTo(String value) {
            addCriterion("GCLB =", value, "gclb");
            return (Criteria) this;
        }

        public Criteria andGclbNotEqualTo(String value) {
            addCriterion("GCLB <>", value, "gclb");
            return (Criteria) this;
        }

        public Criteria andGclbGreaterThan(String value) {
            addCriterion("GCLB >", value, "gclb");
            return (Criteria) this;
        }

        public Criteria andGclbGreaterThanOrEqualTo(String value) {
            addCriterion("GCLB >=", value, "gclb");
            return (Criteria) this;
        }

        public Criteria andGclbLessThan(String value) {
            addCriterion("GCLB <", value, "gclb");
            return (Criteria) this;
        }

        public Criteria andGclbLessThanOrEqualTo(String value) {
            addCriterion("GCLB <=", value, "gclb");
            return (Criteria) this;
        }

        public Criteria andGclbLike(String value) {
            addCriterion("GCLB like", value, "gclb");
            return (Criteria) this;
        }

        public Criteria andGclbNotLike(String value) {
            addCriterion("GCLB not like", value, "gclb");
            return (Criteria) this;
        }

        public Criteria andGclbIn(List<String> values) {
            addCriterion("GCLB in", values, "gclb");
            return (Criteria) this;
        }

        public Criteria andGclbNotIn(List<String> values) {
            addCriterion("GCLB not in", values, "gclb");
            return (Criteria) this;
        }

        public Criteria andGclbBetween(String value1, String value2) {
            addCriterion("GCLB between", value1, value2, "gclb");
            return (Criteria) this;
        }

        public Criteria andGclbNotBetween(String value1, String value2) {
            addCriterion("GCLB not between", value1, value2, "gclb");
            return (Criteria) this;
        }

        public Criteria andZllbIsNull() {
            addCriterion("ZLLB is null");
            return (Criteria) this;
        }

        public Criteria andZllbIsNotNull() {
            addCriterion("ZLLB is not null");
            return (Criteria) this;
        }

        public Criteria andZllbEqualTo(String value) {
            addCriterion("ZLLB =", value, "zllb");
            return (Criteria) this;
        }

        public Criteria andZllbNotEqualTo(String value) {
            addCriterion("ZLLB <>", value, "zllb");
            return (Criteria) this;
        }

        public Criteria andZllbGreaterThan(String value) {
            addCriterion("ZLLB >", value, "zllb");
            return (Criteria) this;
        }

        public Criteria andZllbGreaterThanOrEqualTo(String value) {
            addCriterion("ZLLB >=", value, "zllb");
            return (Criteria) this;
        }

        public Criteria andZllbLessThan(String value) {
            addCriterion("ZLLB <", value, "zllb");
            return (Criteria) this;
        }

        public Criteria andZllbLessThanOrEqualTo(String value) {
            addCriterion("ZLLB <=", value, "zllb");
            return (Criteria) this;
        }

        public Criteria andZllbLike(String value) {
            addCriterion("ZLLB like", value, "zllb");
            return (Criteria) this;
        }

        public Criteria andZllbNotLike(String value) {
            addCriterion("ZLLB not like", value, "zllb");
            return (Criteria) this;
        }

        public Criteria andZllbIn(List<String> values) {
            addCriterion("ZLLB in", values, "zllb");
            return (Criteria) this;
        }

        public Criteria andZllbNotIn(List<String> values) {
            addCriterion("ZLLB not in", values, "zllb");
            return (Criteria) this;
        }

        public Criteria andZllbBetween(String value1, String value2) {
            addCriterion("ZLLB between", value1, value2, "zllb");
            return (Criteria) this;
        }

        public Criteria andZllbNotBetween(String value1, String value2) {
            addCriterion("ZLLB not between", value1, value2, "zllb");
            return (Criteria) this;
        }

        public Criteria andCitycodeIsNull() {
            addCriterion("cityCode is null");
            return (Criteria) this;
        }

        public Criteria andCitycodeIsNotNull() {
            addCriterion("cityCode is not null");
            return (Criteria) this;
        }

        public Criteria andCitycodeEqualTo(String value) {
            addCriterion("cityCode =", value, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeNotEqualTo(String value) {
            addCriterion("cityCode <>", value, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeGreaterThan(String value) {
            addCriterion("cityCode >", value, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeGreaterThanOrEqualTo(String value) {
            addCriterion("cityCode >=", value, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeLessThan(String value) {
            addCriterion("cityCode <", value, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeLessThanOrEqualTo(String value) {
            addCriterion("cityCode <=", value, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeLike(String value) {
            addCriterion("cityCode like", value, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeNotLike(String value) {
            addCriterion("cityCode not like", value, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeIn(List<String> values) {
            addCriterion("cityCode in", values, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeNotIn(List<String> values) {
            addCriterion("cityCode not in", values, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeBetween(String value1, String value2) {
            addCriterion("cityCode between", value1, value2, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeNotBetween(String value1, String value2) {
            addCriterion("cityCode not between", value1, value2, "citycode");
            return (Criteria) this;
        }

        public Criteria andCountycodeIsNull() {
            addCriterion("countyCode is null");
            return (Criteria) this;
        }

        public Criteria andCountycodeIsNotNull() {
            addCriterion("countyCode is not null");
            return (Criteria) this;
        }

        public Criteria andCountycodeEqualTo(String value) {
            addCriterion("countyCode =", value, "countycode");
            return (Criteria) this;
        }

        public Criteria andCountycodeNotEqualTo(String value) {
            addCriterion("countyCode <>", value, "countycode");
            return (Criteria) this;
        }

        public Criteria andCountycodeGreaterThan(String value) {
            addCriterion("countyCode >", value, "countycode");
            return (Criteria) this;
        }

        public Criteria andCountycodeGreaterThanOrEqualTo(String value) {
            addCriterion("countyCode >=", value, "countycode");
            return (Criteria) this;
        }

        public Criteria andCountycodeLessThan(String value) {
            addCriterion("countyCode <", value, "countycode");
            return (Criteria) this;
        }

        public Criteria andCountycodeLessThanOrEqualTo(String value) {
            addCriterion("countyCode <=", value, "countycode");
            return (Criteria) this;
        }

        public Criteria andCountycodeLike(String value) {
            addCriterion("countyCode like", value, "countycode");
            return (Criteria) this;
        }

        public Criteria andCountycodeNotLike(String value) {
            addCriterion("countyCode not like", value, "countycode");
            return (Criteria) this;
        }

        public Criteria andCountycodeIn(List<String> values) {
            addCriterion("countyCode in", values, "countycode");
            return (Criteria) this;
        }

        public Criteria andCountycodeNotIn(List<String> values) {
            addCriterion("countyCode not in", values, "countycode");
            return (Criteria) this;
        }

        public Criteria andCountycodeBetween(String value1, String value2) {
            addCriterion("countyCode between", value1, value2, "countycode");
            return (Criteria) this;
        }

        public Criteria andCountycodeNotBetween(String value1, String value2) {
            addCriterion("countyCode not between", value1, value2, "countycode");
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

        public Criteria andZyndIsNull() {
            addCriterion("ZYND is null");
            return (Criteria) this;
        }

        public Criteria andZyndIsNotNull() {
            addCriterion("ZYND is not null");
            return (Criteria) this;
        }

        public Criteria andZyndEqualTo(String value) {
            addCriterion("ZYND =", value, "zynd");
            return (Criteria) this;
        }

        public Criteria andZyndNotEqualTo(String value) {
            addCriterion("ZYND <>", value, "zynd");
            return (Criteria) this;
        }

        public Criteria andZyndGreaterThan(String value) {
            addCriterion("ZYND >", value, "zynd");
            return (Criteria) this;
        }

        public Criteria andZyndGreaterThanOrEqualTo(String value) {
            addCriterion("ZYND >=", value, "zynd");
            return (Criteria) this;
        }

        public Criteria andZyndLessThan(String value) {
            addCriterion("ZYND <", value, "zynd");
            return (Criteria) this;
        }

        public Criteria andZyndLessThanOrEqualTo(String value) {
            addCriterion("ZYND <=", value, "zynd");
            return (Criteria) this;
        }

        public Criteria andZyndLike(String value) {
            addCriterion("ZYND like", value, "zynd");
            return (Criteria) this;
        }

        public Criteria andZyndNotLike(String value) {
            addCriterion("ZYND not like", value, "zynd");
            return (Criteria) this;
        }

        public Criteria andZyndIn(List<String> values) {
            addCriterion("ZYND in", values, "zynd");
            return (Criteria) this;
        }

        public Criteria andZyndNotIn(List<String> values) {
            addCriterion("ZYND not in", values, "zynd");
            return (Criteria) this;
        }

        public Criteria andZyndBetween(String value1, String value2) {
            addCriterion("ZYND between", value1, value2, "zynd");
            return (Criteria) this;
        }

        public Criteria andZyndNotBetween(String value1, String value2) {
            addCriterion("ZYND not between", value1, value2, "zynd");
            return (Criteria) this;
        }

        public Criteria andJhndIsNull() {
            addCriterion("JHND is null");
            return (Criteria) this;
        }

        public Criteria andJhndIsNotNull() {
            addCriterion("JHND is not null");
            return (Criteria) this;
        }

        public Criteria andJhndEqualTo(String value) {
            addCriterion("JHND =", value, "jhnd");
            return (Criteria) this;
        }

        public Criteria andJhndNotEqualTo(String value) {
            addCriterion("JHND <>", value, "jhnd");
            return (Criteria) this;
        }

        public Criteria andJhndGreaterThan(String value) {
            addCriterion("JHND >", value, "jhnd");
            return (Criteria) this;
        }

        public Criteria andJhndGreaterThanOrEqualTo(String value) {
            addCriterion("JHND >=", value, "jhnd");
            return (Criteria) this;
        }

        public Criteria andJhndLessThan(String value) {
            addCriterion("JHND <", value, "jhnd");
            return (Criteria) this;
        }

        public Criteria andJhndLessThanOrEqualTo(String value) {
            addCriterion("JHND <=", value, "jhnd");
            return (Criteria) this;
        }

        public Criteria andJhndLike(String value) {
            addCriterion("JHND like", value, "jhnd");
            return (Criteria) this;
        }

        public Criteria andJhndNotLike(String value) {
            addCriterion("JHND not like", value, "jhnd");
            return (Criteria) this;
        }

        public Criteria andJhndIn(List<String> values) {
            addCriterion("JHND in", values, "jhnd");
            return (Criteria) this;
        }

        public Criteria andJhndNotIn(List<String> values) {
            addCriterion("JHND not in", values, "jhnd");
            return (Criteria) this;
        }

        public Criteria andJhndBetween(String value1, String value2) {
            addCriterion("JHND between", value1, value2, "jhnd");
            return (Criteria) this;
        }

        public Criteria andJhndNotBetween(String value1, String value2) {
            addCriterion("JHND not between", value1, value2, "jhnd");
            return (Criteria) this;
        }

        public Criteria andFilesurlIsNull() {
            addCriterion("filesUrl is null");
            return (Criteria) this;
        }

        public Criteria andFilesurlIsNotNull() {
            addCriterion("filesUrl is not null");
            return (Criteria) this;
        }

        public Criteria andFilesurlEqualTo(String value) {
            addCriterion("filesUrl =", value, "filesurl");
            return (Criteria) this;
        }

        public Criteria andFilesurlNotEqualTo(String value) {
            addCriterion("filesUrl <>", value, "filesurl");
            return (Criteria) this;
        }

        public Criteria andFilesurlGreaterThan(String value) {
            addCriterion("filesUrl >", value, "filesurl");
            return (Criteria) this;
        }

        public Criteria andFilesurlGreaterThanOrEqualTo(String value) {
            addCriterion("filesUrl >=", value, "filesurl");
            return (Criteria) this;
        }

        public Criteria andFilesurlLessThan(String value) {
            addCriterion("filesUrl <", value, "filesurl");
            return (Criteria) this;
        }

        public Criteria andFilesurlLessThanOrEqualTo(String value) {
            addCriterion("filesUrl <=", value, "filesurl");
            return (Criteria) this;
        }

        public Criteria andFilesurlLike(String value) {
            addCriterion("filesUrl like", value, "filesurl");
            return (Criteria) this;
        }

        public Criteria andFilesurlNotLike(String value) {
            addCriterion("filesUrl not like", value, "filesurl");
            return (Criteria) this;
        }

        public Criteria andFilesurlIn(List<String> values) {
            addCriterion("filesUrl in", values, "filesurl");
            return (Criteria) this;
        }

        public Criteria andFilesurlNotIn(List<String> values) {
            addCriterion("filesUrl not in", values, "filesurl");
            return (Criteria) this;
        }

        public Criteria andFilesurlBetween(String value1, String value2) {
            addCriterion("filesUrl between", value1, value2, "filesurl");
            return (Criteria) this;
        }

        public Criteria andFilesurlNotBetween(String value1, String value2) {
            addCriterion("filesUrl not between", value1, value2, "filesurl");
            return (Criteria) this;
        }

        public Criteria andStatIsNull() {
            addCriterion("stat is null");
            return (Criteria) this;
        }

        public Criteria andStatIsNotNull() {
            addCriterion("stat is not null");
            return (Criteria) this;
        }

        public Criteria andStatEqualTo(String value) {
            addCriterion("stat =", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatNotEqualTo(String value) {
            addCriterion("stat <>", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatGreaterThan(String value) {
            addCriterion("stat >", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatGreaterThanOrEqualTo(String value) {
            addCriterion("stat >=", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatLessThan(String value) {
            addCriterion("stat <", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatLessThanOrEqualTo(String value) {
            addCriterion("stat <=", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatLike(String value) {
            addCriterion("stat like", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatNotLike(String value) {
            addCriterion("stat not like", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatIn(List<String> values) {
            addCriterion("stat in", values, "stat");
            return (Criteria) this;
        }

        public Criteria andStatNotIn(List<String> values) {
            addCriterion("stat not in", values, "stat");
            return (Criteria) this;
        }

        public Criteria andStatBetween(String value1, String value2) {
            addCriterion("stat between", value1, value2, "stat");
            return (Criteria) this;
        }

        public Criteria andStatNotBetween(String value1, String value2) {
            addCriterion("stat not between", value1, value2, "stat");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * 
     * 
     * @author wcyong
     * 
     * @date 2019-04-27
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