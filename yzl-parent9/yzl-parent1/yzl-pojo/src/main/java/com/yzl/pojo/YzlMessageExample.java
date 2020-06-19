package com.yzl.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class YzlMessageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public YzlMessageExample() {
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
     * @date 2019-05-05
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

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andRidIsNull() {
            addCriterion("rid is null");
            return (Criteria) this;
        }

        public Criteria andRidIsNotNull() {
            addCriterion("rid is not null");
            return (Criteria) this;
        }

        public Criteria andRidEqualTo(Integer value) {
            addCriterion("rid =", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidNotEqualTo(Integer value) {
            addCriterion("rid <>", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidGreaterThan(Integer value) {
            addCriterion("rid >", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidGreaterThanOrEqualTo(Integer value) {
            addCriterion("rid >=", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidLessThan(Integer value) {
            addCriterion("rid <", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidLessThanOrEqualTo(Integer value) {
            addCriterion("rid <=", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidIn(List<Integer> values) {
            addCriterion("rid in", values, "rid");
            return (Criteria) this;
        }

        public Criteria andRidNotIn(List<Integer> values) {
            addCriterion("rid not in", values, "rid");
            return (Criteria) this;
        }

        public Criteria andRidBetween(Integer value1, Integer value2) {
            addCriterion("rid between", value1, value2, "rid");
            return (Criteria) this;
        }

        public Criteria andRidNotBetween(Integer value1, Integer value2) {
            addCriterion("rid not between", value1, value2, "rid");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andStatuIsNull() {
            addCriterion("statu is null");
            return (Criteria) this;
        }

        public Criteria andStatuIsNotNull() {
            addCriterion("statu is not null");
            return (Criteria) this;
        }

        public Criteria andStatuEqualTo(String value) {
            addCriterion("statu =", value, "statu");
            return (Criteria) this;
        }

        public Criteria andStatuNotEqualTo(String value) {
            addCriterion("statu <>", value, "statu");
            return (Criteria) this;
        }

        public Criteria andStatuGreaterThan(String value) {
            addCriterion("statu >", value, "statu");
            return (Criteria) this;
        }

        public Criteria andStatuGreaterThanOrEqualTo(String value) {
            addCriterion("statu >=", value, "statu");
            return (Criteria) this;
        }

        public Criteria andStatuLessThan(String value) {
            addCriterion("statu <", value, "statu");
            return (Criteria) this;
        }

        public Criteria andStatuLessThanOrEqualTo(String value) {
            addCriterion("statu <=", value, "statu");
            return (Criteria) this;
        }

        public Criteria andStatuLike(String value) {
            addCriterion("statu like", value, "statu");
            return (Criteria) this;
        }

        public Criteria andStatuNotLike(String value) {
            addCriterion("statu not like", value, "statu");
            return (Criteria) this;
        }

        public Criteria andStatuIn(List<String> values) {
            addCriterion("statu in", values, "statu");
            return (Criteria) this;
        }

        public Criteria andStatuNotIn(List<String> values) {
            addCriterion("statu not in", values, "statu");
            return (Criteria) this;
        }

        public Criteria andStatuBetween(String value1, String value2) {
            addCriterion("statu between", value1, value2, "statu");
            return (Criteria) this;
        }

        public Criteria andStatuNotBetween(String value1, String value2) {
            addCriterion("statu not between", value1, value2, "statu");
            return (Criteria) this;
        }

        public Criteria andNumberIsNull() {
            addCriterion("number is null");
            return (Criteria) this;
        }

        public Criteria andNumberIsNotNull() {
            addCriterion("number is not null");
            return (Criteria) this;
        }

        public Criteria andNumberEqualTo(Long value) {
            addCriterion("number =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(Long value) {
            addCriterion("number <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(Long value) {
            addCriterion("number >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(Long value) {
            addCriterion("number >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(Long value) {
            addCriterion("number <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(Long value) {
            addCriterion("number <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<Long> values) {
            addCriterion("number in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<Long> values) {
            addCriterion("number not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(Long value1, Long value2) {
            addCriterion("number between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(Long value1, Long value2) {
            addCriterion("number not between", value1, value2, "number");
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
     * @date 2019-05-05
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