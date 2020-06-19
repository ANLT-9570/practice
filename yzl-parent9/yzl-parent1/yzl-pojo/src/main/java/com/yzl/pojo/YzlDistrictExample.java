package com.yzl.pojo;

import java.util.ArrayList;
import java.util.List;

public class YzlDistrictExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public YzlDistrictExample() {
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
     * @date 2018-12-23
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

        public Criteria andDcodeIsNull() {
            addCriterion("dcode is null");
            return (Criteria) this;
        }

        public Criteria andDcodeIsNotNull() {
            addCriterion("dcode is not null");
            return (Criteria) this;
        }

        public Criteria andDcodeEqualTo(Integer value) {
            addCriterion("dcode =", value, "dcode");
            return (Criteria) this;
        }

        public Criteria andDcodeNotEqualTo(Integer value) {
            addCriterion("dcode <>", value, "dcode");
            return (Criteria) this;
        }

        public Criteria andDcodeGreaterThan(Integer value) {
            addCriterion("dcode >", value, "dcode");
            return (Criteria) this;
        }

        public Criteria andDcodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("dcode >=", value, "dcode");
            return (Criteria) this;
        }

        public Criteria andDcodeLessThan(Integer value) {
            addCriterion("dcode <", value, "dcode");
            return (Criteria) this;
        }

        public Criteria andDcodeLessThanOrEqualTo(Integer value) {
            addCriterion("dcode <=", value, "dcode");
            return (Criteria) this;
        }

        public Criteria andDcodeIn(List<Integer> values) {
            addCriterion("dcode in", values, "dcode");
            return (Criteria) this;
        }

        public Criteria andDcodeNotIn(List<Integer> values) {
            addCriterion("dcode not in", values, "dcode");
            return (Criteria) this;
        }

        public Criteria andDcodeBetween(Integer value1, Integer value2) {
            addCriterion("dcode between", value1, value2, "dcode");
            return (Criteria) this;
        }

        public Criteria andDcodeNotBetween(Integer value1, Integer value2) {
            addCriterion("dcode not between", value1, value2, "dcode");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCountyIsNull() {
            addCriterion("county is null");
            return (Criteria) this;
        }

        public Criteria andCountyIsNotNull() {
            addCriterion("county is not null");
            return (Criteria) this;
        }

        public Criteria andCountyEqualTo(String value) {
            addCriterion("county =", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotEqualTo(String value) {
            addCriterion("county <>", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyGreaterThan(String value) {
            addCriterion("county >", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyGreaterThanOrEqualTo(String value) {
            addCriterion("county >=", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyLessThan(String value) {
            addCriterion("county <", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyLessThanOrEqualTo(String value) {
            addCriterion("county <=", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyLike(String value) {
            addCriterion("county like", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotLike(String value) {
            addCriterion("county not like", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyIn(List<String> values) {
            addCriterion("county in", values, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotIn(List<String> values) {
            addCriterion("county not in", values, "county");
            return (Criteria) this;
        }

        public Criteria andCountyBetween(String value1, String value2) {
            addCriterion("county between", value1, value2, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotBetween(String value1, String value2) {
            addCriterion("county not between", value1, value2, "county");
            return (Criteria) this;
        }

        public Criteria andPcodeIsNull() {
            addCriterion("pcode is null");
            return (Criteria) this;
        }

        public Criteria andPcodeIsNotNull() {
            addCriterion("pcode is not null");
            return (Criteria) this;
        }

        public Criteria andPcodeEqualTo(Integer value) {
            addCriterion("pcode =", value, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeNotEqualTo(Integer value) {
            addCriterion("pcode <>", value, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeGreaterThan(Integer value) {
            addCriterion("pcode >", value, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("pcode >=", value, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeLessThan(Integer value) {
            addCriterion("pcode <", value, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeLessThanOrEqualTo(Integer value) {
            addCriterion("pcode <=", value, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeIn(List<Integer> values) {
            addCriterion("pcode in", values, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeNotIn(List<Integer> values) {
            addCriterion("pcode not in", values, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeBetween(Integer value1, Integer value2) {
            addCriterion("pcode between", value1, value2, "pcode");
            return (Criteria) this;
        }

        public Criteria andPcodeNotBetween(Integer value1, Integer value2) {
            addCriterion("pcode not between", value1, value2, "pcode");
            return (Criteria) this;
        }

        public Criteria andShortcodeIsNull() {
            addCriterion("shortcode is null");
            return (Criteria) this;
        }

        public Criteria andShortcodeIsNotNull() {
            addCriterion("shortcode is not null");
            return (Criteria) this;
        }

        public Criteria andShortcodeEqualTo(String value) {
            addCriterion("shortcode =", value, "shortcode");
            return (Criteria) this;
        }

        public Criteria andShortcodeNotEqualTo(String value) {
            addCriterion("shortcode <>", value, "shortcode");
            return (Criteria) this;
        }

        public Criteria andShortcodeGreaterThan(String value) {
            addCriterion("shortcode >", value, "shortcode");
            return (Criteria) this;
        }

        public Criteria andShortcodeGreaterThanOrEqualTo(String value) {
            addCriterion("shortcode >=", value, "shortcode");
            return (Criteria) this;
        }

        public Criteria andShortcodeLessThan(String value) {
            addCriterion("shortcode <", value, "shortcode");
            return (Criteria) this;
        }

        public Criteria andShortcodeLessThanOrEqualTo(String value) {
            addCriterion("shortcode <=", value, "shortcode");
            return (Criteria) this;
        }

        public Criteria andShortcodeLike(String value) {
            addCriterion("shortcode like", value, "shortcode");
            return (Criteria) this;
        }

        public Criteria andShortcodeNotLike(String value) {
            addCriterion("shortcode not like", value, "shortcode");
            return (Criteria) this;
        }

        public Criteria andShortcodeIn(List<String> values) {
            addCriterion("shortcode in", values, "shortcode");
            return (Criteria) this;
        }

        public Criteria andShortcodeNotIn(List<String> values) {
            addCriterion("shortcode not in", values, "shortcode");
            return (Criteria) this;
        }

        public Criteria andShortcodeBetween(String value1, String value2) {
            addCriterion("shortcode between", value1, value2, "shortcode");
            return (Criteria) this;
        }

        public Criteria andShortcodeNotBetween(String value1, String value2) {
            addCriterion("shortcode not between", value1, value2, "shortcode");
            return (Criteria) this;
        }

        public Criteria andCitycodeIsNull() {
            addCriterion("citycode is null");
            return (Criteria) this;
        }

        public Criteria andCitycodeIsNotNull() {
            addCriterion("citycode is not null");
            return (Criteria) this;
        }

        public Criteria andCitycodeEqualTo(String value) {
            addCriterion("citycode =", value, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeNotEqualTo(String value) {
            addCriterion("citycode <>", value, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeGreaterThan(String value) {
            addCriterion("citycode >", value, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeGreaterThanOrEqualTo(String value) {
            addCriterion("citycode >=", value, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeLessThan(String value) {
            addCriterion("citycode <", value, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeLessThanOrEqualTo(String value) {
            addCriterion("citycode <=", value, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeLike(String value) {
            addCriterion("citycode like", value, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeNotLike(String value) {
            addCriterion("citycode not like", value, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeIn(List<String> values) {
            addCriterion("citycode in", values, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeNotIn(List<String> values) {
            addCriterion("citycode not in", values, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeBetween(String value1, String value2) {
            addCriterion("citycode between", value1, value2, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeNotBetween(String value1, String value2) {
            addCriterion("citycode not between", value1, value2, "citycode");
            return (Criteria) this;
        }

        public Criteria andBurgIsNull() {
            addCriterion("burg is null");
            return (Criteria) this;
        }

        public Criteria andBurgIsNotNull() {
            addCriterion("burg is not null");
            return (Criteria) this;
        }

        public Criteria andBurgEqualTo(String value) {
            addCriterion("burg =", value, "burg");
            return (Criteria) this;
        }

        public Criteria andBurgNotEqualTo(String value) {
            addCriterion("burg <>", value, "burg");
            return (Criteria) this;
        }

        public Criteria andBurgGreaterThan(String value) {
            addCriterion("burg >", value, "burg");
            return (Criteria) this;
        }

        public Criteria andBurgGreaterThanOrEqualTo(String value) {
            addCriterion("burg >=", value, "burg");
            return (Criteria) this;
        }

        public Criteria andBurgLessThan(String value) {
            addCriterion("burg <", value, "burg");
            return (Criteria) this;
        }

        public Criteria andBurgLessThanOrEqualTo(String value) {
            addCriterion("burg <=", value, "burg");
            return (Criteria) this;
        }

        public Criteria andBurgLike(String value) {
            addCriterion("burg like", value, "burg");
            return (Criteria) this;
        }

        public Criteria andBurgNotLike(String value) {
            addCriterion("burg not like", value, "burg");
            return (Criteria) this;
        }

        public Criteria andBurgIn(List<String> values) {
            addCriterion("burg in", values, "burg");
            return (Criteria) this;
        }

        public Criteria andBurgNotIn(List<String> values) {
            addCriterion("burg not in", values, "burg");
            return (Criteria) this;
        }

        public Criteria andBurgBetween(String value1, String value2) {
            addCriterion("burg between", value1, value2, "burg");
            return (Criteria) this;
        }

        public Criteria andBurgNotBetween(String value1, String value2) {
            addCriterion("burg not between", value1, value2, "burg");
            return (Criteria) this;
        }

        public Criteria andVillageIsNull() {
            addCriterion("village is null");
            return (Criteria) this;
        }

        public Criteria andVillageIsNotNull() {
            addCriterion("village is not null");
            return (Criteria) this;
        }

        public Criteria andVillageEqualTo(String value) {
            addCriterion("village =", value, "village");
            return (Criteria) this;
        }

        public Criteria andVillageNotEqualTo(String value) {
            addCriterion("village <>", value, "village");
            return (Criteria) this;
        }

        public Criteria andVillageGreaterThan(String value) {
            addCriterion("village >", value, "village");
            return (Criteria) this;
        }

        public Criteria andVillageGreaterThanOrEqualTo(String value) {
            addCriterion("village >=", value, "village");
            return (Criteria) this;
        }

        public Criteria andVillageLessThan(String value) {
            addCriterion("village <", value, "village");
            return (Criteria) this;
        }

        public Criteria andVillageLessThanOrEqualTo(String value) {
            addCriterion("village <=", value, "village");
            return (Criteria) this;
        }

        public Criteria andVillageLike(String value) {
            addCriterion("village like", value, "village");
            return (Criteria) this;
        }

        public Criteria andVillageNotLike(String value) {
            addCriterion("village not like", value, "village");
            return (Criteria) this;
        }

        public Criteria andVillageIn(List<String> values) {
            addCriterion("village in", values, "village");
            return (Criteria) this;
        }

        public Criteria andVillageNotIn(List<String> values) {
            addCriterion("village not in", values, "village");
            return (Criteria) this;
        }

        public Criteria andVillageBetween(String value1, String value2) {
            addCriterion("village between", value1, value2, "village");
            return (Criteria) this;
        }

        public Criteria andVillageNotBetween(String value1, String value2) {
            addCriterion("village not between", value1, value2, "village");
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

        public Criteria andFlagIsNull() {
            addCriterion("flag is null");
            return (Criteria) this;
        }

        public Criteria andFlagIsNotNull() {
            addCriterion("flag is not null");
            return (Criteria) this;
        }

        public Criteria andFlagEqualTo(String value) {
            addCriterion("flag =", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotEqualTo(String value) {
            addCriterion("flag <>", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThan(String value) {
            addCriterion("flag >", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThanOrEqualTo(String value) {
            addCriterion("flag >=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThan(String value) {
            addCriterion("flag <", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThanOrEqualTo(String value) {
            addCriterion("flag <=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLike(String value) {
            addCriterion("flag like", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotLike(String value) {
            addCriterion("flag not like", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagIn(List<String> values) {
            addCriterion("flag in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotIn(List<String> values) {
            addCriterion("flag not in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagBetween(String value1, String value2) {
            addCriterion("flag between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotBetween(String value1, String value2) {
            addCriterion("flag not between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andAnumberIsNull() {
            addCriterion("Anumber is null");
            return (Criteria) this;
        }

        public Criteria andAnumberIsNotNull() {
            addCriterion("Anumber is not null");
            return (Criteria) this;
        }

        public Criteria andAnumberEqualTo(String value) {
            addCriterion("Anumber =", value, "anumber");
            return (Criteria) this;
        }

        public Criteria andAnumberNotEqualTo(String value) {
            addCriterion("Anumber <>", value, "anumber");
            return (Criteria) this;
        }

        public Criteria andAnumberGreaterThan(String value) {
            addCriterion("Anumber >", value, "anumber");
            return (Criteria) this;
        }

        public Criteria andAnumberGreaterThanOrEqualTo(String value) {
            addCriterion("Anumber >=", value, "anumber");
            return (Criteria) this;
        }

        public Criteria andAnumberLessThan(String value) {
            addCriterion("Anumber <", value, "anumber");
            return (Criteria) this;
        }

        public Criteria andAnumberLessThanOrEqualTo(String value) {
            addCriterion("Anumber <=", value, "anumber");
            return (Criteria) this;
        }

        public Criteria andAnumberLike(String value) {
            addCriterion("Anumber like", value, "anumber");
            return (Criteria) this;
        }

        public Criteria andAnumberNotLike(String value) {
            addCriterion("Anumber not like", value, "anumber");
            return (Criteria) this;
        }

        public Criteria andAnumberIn(List<String> values) {
            addCriterion("Anumber in", values, "anumber");
            return (Criteria) this;
        }

        public Criteria andAnumberNotIn(List<String> values) {
            addCriterion("Anumber not in", values, "anumber");
            return (Criteria) this;
        }

        public Criteria andAnumberBetween(String value1, String value2) {
            addCriterion("Anumber between", value1, value2, "anumber");
            return (Criteria) this;
        }

        public Criteria andAnumberNotBetween(String value1, String value2) {
            addCriterion("Anumber not between", value1, value2, "anumber");
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
     * @date 2018-12-23
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