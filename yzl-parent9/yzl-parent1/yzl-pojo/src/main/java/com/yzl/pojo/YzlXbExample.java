package com.yzl.pojo;

import java.util.ArrayList;
import java.util.List;

public class YzlXbExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public YzlXbExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andBsmIsNull() {
            addCriterion("BSM is null");
            return (Criteria) this;
        }

        public Criteria andBsmIsNotNull() {
            addCriterion("BSM is not null");
            return (Criteria) this;
        }

        public Criteria andBsmEqualTo(String value) {
            addCriterion("BSM =", value, "bsm");
            return (Criteria) this;
        }

        public Criteria andBsmNotEqualTo(String value) {
            addCriterion("BSM <>", value, "bsm");
            return (Criteria) this;
        }

        public Criteria andBsmGreaterThan(String value) {
            addCriterion("BSM >", value, "bsm");
            return (Criteria) this;
        }

        public Criteria andBsmGreaterThanOrEqualTo(String value) {
            addCriterion("BSM >=", value, "bsm");
            return (Criteria) this;
        }

        public Criteria andBsmLessThan(String value) {
            addCriterion("BSM <", value, "bsm");
            return (Criteria) this;
        }

        public Criteria andBsmLessThanOrEqualTo(String value) {
            addCriterion("BSM <=", value, "bsm");
            return (Criteria) this;
        }

        public Criteria andBsmLike(String value) {
            addCriterion("BSM like", value, "bsm");
            return (Criteria) this;
        }

        public Criteria andBsmNotLike(String value) {
            addCriterion("BSM not like", value, "bsm");
            return (Criteria) this;
        }

        public Criteria andBsmIn(List<String> values) {
            addCriterion("BSM in", values, "bsm");
            return (Criteria) this;
        }

        public Criteria andBsmNotIn(List<String> values) {
            addCriterion("BSM not in", values, "bsm");
            return (Criteria) this;
        }

        public Criteria andBsmBetween(String value1, String value2) {
            addCriterion("BSM between", value1, value2, "bsm");
            return (Criteria) this;
        }

        public Criteria andBsmNotBetween(String value1, String value2) {
            addCriterion("BSM not between", value1, value2, "bsm");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("CITY is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("CITY is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("CITY =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("CITY <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("CITY >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("CITY >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("CITY <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("CITY <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("CITY like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("CITY not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("CITY in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("CITY not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("CITY between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("CITY not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCountyIsNull() {
            addCriterion("COUNTY is null");
            return (Criteria) this;
        }

        public Criteria andCountyIsNotNull() {
            addCriterion("COUNTY is not null");
            return (Criteria) this;
        }

        public Criteria andCountyEqualTo(String value) {
            addCriterion("COUNTY =", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotEqualTo(String value) {
            addCriterion("COUNTY <>", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyGreaterThan(String value) {
            addCriterion("COUNTY >", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyGreaterThanOrEqualTo(String value) {
            addCriterion("COUNTY >=", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyLessThan(String value) {
            addCriterion("COUNTY <", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyLessThanOrEqualTo(String value) {
            addCriterion("COUNTY <=", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyLike(String value) {
            addCriterion("COUNTY like", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotLike(String value) {
            addCriterion("COUNTY not like", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyIn(List<String> values) {
            addCriterion("COUNTY in", values, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotIn(List<String> values) {
            addCriterion("COUNTY not in", values, "county");
            return (Criteria) this;
        }

        public Criteria andCountyBetween(String value1, String value2) {
            addCriterion("COUNTY between", value1, value2, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotBetween(String value1, String value2) {
            addCriterion("COUNTY not between", value1, value2, "county");
            return (Criteria) this;
        }

        public Criteria andTownIsNull() {
            addCriterion("TOWN is null");
            return (Criteria) this;
        }

        public Criteria andTownIsNotNull() {
            addCriterion("TOWN is not null");
            return (Criteria) this;
        }

        public Criteria andTownEqualTo(String value) {
            addCriterion("TOWN =", value, "town");
            return (Criteria) this;
        }

        public Criteria andTownNotEqualTo(String value) {
            addCriterion("TOWN <>", value, "town");
            return (Criteria) this;
        }

        public Criteria andTownGreaterThan(String value) {
            addCriterion("TOWN >", value, "town");
            return (Criteria) this;
        }

        public Criteria andTownGreaterThanOrEqualTo(String value) {
            addCriterion("TOWN >=", value, "town");
            return (Criteria) this;
        }

        public Criteria andTownLessThan(String value) {
            addCriterion("TOWN <", value, "town");
            return (Criteria) this;
        }

        public Criteria andTownLessThanOrEqualTo(String value) {
            addCriterion("TOWN <=", value, "town");
            return (Criteria) this;
        }

        public Criteria andTownLike(String value) {
            addCriterion("TOWN like", value, "town");
            return (Criteria) this;
        }

        public Criteria andTownNotLike(String value) {
            addCriterion("TOWN not like", value, "town");
            return (Criteria) this;
        }

        public Criteria andTownIn(List<String> values) {
            addCriterion("TOWN in", values, "town");
            return (Criteria) this;
        }

        public Criteria andTownNotIn(List<String> values) {
            addCriterion("TOWN not in", values, "town");
            return (Criteria) this;
        }

        public Criteria andTownBetween(String value1, String value2) {
            addCriterion("TOWN between", value1, value2, "town");
            return (Criteria) this;
        }

        public Criteria andTownNotBetween(String value1, String value2) {
            addCriterion("TOWN not between", value1, value2, "town");
            return (Criteria) this;
        }

        public Criteria andVillageIsNull() {
            addCriterion("VILLAGE is null");
            return (Criteria) this;
        }

        public Criteria andVillageIsNotNull() {
            addCriterion("VILLAGE is not null");
            return (Criteria) this;
        }

        public Criteria andVillageEqualTo(String value) {
            addCriterion("VILLAGE =", value, "village");
            return (Criteria) this;
        }

        public Criteria andVillageNotEqualTo(String value) {
            addCriterion("VILLAGE <>", value, "village");
            return (Criteria) this;
        }

        public Criteria andVillageGreaterThan(String value) {
            addCriterion("VILLAGE >", value, "village");
            return (Criteria) this;
        }

        public Criteria andVillageGreaterThanOrEqualTo(String value) {
            addCriterion("VILLAGE >=", value, "village");
            return (Criteria) this;
        }

        public Criteria andVillageLessThan(String value) {
            addCriterion("VILLAGE <", value, "village");
            return (Criteria) this;
        }

        public Criteria andVillageLessThanOrEqualTo(String value) {
            addCriterion("VILLAGE <=", value, "village");
            return (Criteria) this;
        }

        public Criteria andVillageLike(String value) {
            addCriterion("VILLAGE like", value, "village");
            return (Criteria) this;
        }

        public Criteria andVillageNotLike(String value) {
            addCriterion("VILLAGE not like", value, "village");
            return (Criteria) this;
        }

        public Criteria andVillageIn(List<String> values) {
            addCriterion("VILLAGE in", values, "village");
            return (Criteria) this;
        }

        public Criteria andVillageNotIn(List<String> values) {
            addCriterion("VILLAGE not in", values, "village");
            return (Criteria) this;
        }

        public Criteria andVillageBetween(String value1, String value2) {
            addCriterion("VILLAGE between", value1, value2, "village");
            return (Criteria) this;
        }

        public Criteria andVillageNotBetween(String value1, String value2) {
            addCriterion("VILLAGE not between", value1, value2, "village");
            return (Criteria) this;
        }

        public Criteria andLinBanIsNull() {
            addCriterion("LIN_BAN is null");
            return (Criteria) this;
        }

        public Criteria andLinBanIsNotNull() {
            addCriterion("LIN_BAN is not null");
            return (Criteria) this;
        }

        public Criteria andLinBanEqualTo(String value) {
            addCriterion("LIN_BAN =", value, "linBan");
            return (Criteria) this;
        }

        public Criteria andLinBanNotEqualTo(String value) {
            addCriterion("LIN_BAN <>", value, "linBan");
            return (Criteria) this;
        }

        public Criteria andLinBanGreaterThan(String value) {
            addCriterion("LIN_BAN >", value, "linBan");
            return (Criteria) this;
        }

        public Criteria andLinBanGreaterThanOrEqualTo(String value) {
            addCriterion("LIN_BAN >=", value, "linBan");
            return (Criteria) this;
        }

        public Criteria andLinBanLessThan(String value) {
            addCriterion("LIN_BAN <", value, "linBan");
            return (Criteria) this;
        }

        public Criteria andLinBanLessThanOrEqualTo(String value) {
            addCriterion("LIN_BAN <=", value, "linBan");
            return (Criteria) this;
        }

        public Criteria andLinBanLike(String value) {
            addCriterion("LIN_BAN like", value, "linBan");
            return (Criteria) this;
        }

        public Criteria andLinBanNotLike(String value) {
            addCriterion("LIN_BAN not like", value, "linBan");
            return (Criteria) this;
        }

        public Criteria andLinBanIn(List<String> values) {
            addCriterion("LIN_BAN in", values, "linBan");
            return (Criteria) this;
        }

        public Criteria andLinBanNotIn(List<String> values) {
            addCriterion("LIN_BAN not in", values, "linBan");
            return (Criteria) this;
        }

        public Criteria andLinBanBetween(String value1, String value2) {
            addCriterion("LIN_BAN between", value1, value2, "linBan");
            return (Criteria) this;
        }

        public Criteria andLinBanNotBetween(String value1, String value2) {
            addCriterion("LIN_BAN not between", value1, value2, "linBan");
            return (Criteria) this;
        }

        public Criteria andJyingBanIsNull() {
            addCriterion("JYING_BAN is null");
            return (Criteria) this;
        }

        public Criteria andJyingBanIsNotNull() {
            addCriterion("JYING_BAN is not null");
            return (Criteria) this;
        }

        public Criteria andJyingBanEqualTo(String value) {
            addCriterion("JYING_BAN =", value, "jyingBan");
            return (Criteria) this;
        }

        public Criteria andJyingBanNotEqualTo(String value) {
            addCriterion("JYING_BAN <>", value, "jyingBan");
            return (Criteria) this;
        }

        public Criteria andJyingBanGreaterThan(String value) {
            addCriterion("JYING_BAN >", value, "jyingBan");
            return (Criteria) this;
        }

        public Criteria andJyingBanGreaterThanOrEqualTo(String value) {
            addCriterion("JYING_BAN >=", value, "jyingBan");
            return (Criteria) this;
        }

        public Criteria andJyingBanLessThan(String value) {
            addCriterion("JYING_BAN <", value, "jyingBan");
            return (Criteria) this;
        }

        public Criteria andJyingBanLessThanOrEqualTo(String value) {
            addCriterion("JYING_BAN <=", value, "jyingBan");
            return (Criteria) this;
        }

        public Criteria andJyingBanLike(String value) {
            addCriterion("JYING_BAN like", value, "jyingBan");
            return (Criteria) this;
        }

        public Criteria andJyingBanNotLike(String value) {
            addCriterion("JYING_BAN not like", value, "jyingBan");
            return (Criteria) this;
        }

        public Criteria andJyingBanIn(List<String> values) {
            addCriterion("JYING_BAN in", values, "jyingBan");
            return (Criteria) this;
        }

        public Criteria andJyingBanNotIn(List<String> values) {
            addCriterion("JYING_BAN not in", values, "jyingBan");
            return (Criteria) this;
        }

        public Criteria andJyingBanBetween(String value1, String value2) {
            addCriterion("JYING_BAN between", value1, value2, "jyingBan");
            return (Criteria) this;
        }

        public Criteria andJyingBanNotBetween(String value1, String value2) {
            addCriterion("JYING_BAN not between", value1, value2, "jyingBan");
            return (Criteria) this;
        }

        public Criteria andXiaoBanIsNull() {
            addCriterion("XIAO_BAN is null");
            return (Criteria) this;
        }

        public Criteria andXiaoBanIsNotNull() {
            addCriterion("XIAO_BAN is not null");
            return (Criteria) this;
        }

        public Criteria andXiaoBanEqualTo(String value) {
            addCriterion("XIAO_BAN =", value, "xiaoBan");
            return (Criteria) this;
        }

        public Criteria andXiaoBanNotEqualTo(String value) {
            addCriterion("XIAO_BAN <>", value, "xiaoBan");
            return (Criteria) this;
        }

        public Criteria andXiaoBanGreaterThan(String value) {
            addCriterion("XIAO_BAN >", value, "xiaoBan");
            return (Criteria) this;
        }

        public Criteria andXiaoBanGreaterThanOrEqualTo(String value) {
            addCriterion("XIAO_BAN >=", value, "xiaoBan");
            return (Criteria) this;
        }

        public Criteria andXiaoBanLessThan(String value) {
            addCriterion("XIAO_BAN <", value, "xiaoBan");
            return (Criteria) this;
        }

        public Criteria andXiaoBanLessThanOrEqualTo(String value) {
            addCriterion("XIAO_BAN <=", value, "xiaoBan");
            return (Criteria) this;
        }

        public Criteria andXiaoBanLike(String value) {
            addCriterion("XIAO_BAN like", value, "xiaoBan");
            return (Criteria) this;
        }

        public Criteria andXiaoBanNotLike(String value) {
            addCriterion("XIAO_BAN not like", value, "xiaoBan");
            return (Criteria) this;
        }

        public Criteria andXiaoBanIn(List<String> values) {
            addCriterion("XIAO_BAN in", values, "xiaoBan");
            return (Criteria) this;
        }

        public Criteria andXiaoBanNotIn(List<String> values) {
            addCriterion("XIAO_BAN not in", values, "xiaoBan");
            return (Criteria) this;
        }

        public Criteria andXiaoBanBetween(String value1, String value2) {
            addCriterion("XIAO_BAN between", value1, value2, "xiaoBan");
            return (Criteria) this;
        }

        public Criteria andXiaoBanNotBetween(String value1, String value2) {
            addCriterion("XIAO_BAN not between", value1, value2, "xiaoBan");
            return (Criteria) this;
        }

        public Criteria andZyXbIsNull() {
            addCriterion("ZY_XB is null");
            return (Criteria) this;
        }

        public Criteria andZyXbIsNotNull() {
            addCriterion("ZY_XB is not null");
            return (Criteria) this;
        }

        public Criteria andZyXbEqualTo(String value) {
            addCriterion("ZY_XB =", value, "zyXb");
            return (Criteria) this;
        }

        public Criteria andZyXbNotEqualTo(String value) {
            addCriterion("ZY_XB <>", value, "zyXb");
            return (Criteria) this;
        }

        public Criteria andZyXbGreaterThan(String value) {
            addCriterion("ZY_XB >", value, "zyXb");
            return (Criteria) this;
        }

        public Criteria andZyXbGreaterThanOrEqualTo(String value) {
            addCriterion("ZY_XB >=", value, "zyXb");
            return (Criteria) this;
        }

        public Criteria andZyXbLessThan(String value) {
            addCriterion("ZY_XB <", value, "zyXb");
            return (Criteria) this;
        }

        public Criteria andZyXbLessThanOrEqualTo(String value) {
            addCriterion("ZY_XB <=", value, "zyXb");
            return (Criteria) this;
        }

        public Criteria andZyXbLike(String value) {
            addCriterion("ZY_XB like", value, "zyXb");
            return (Criteria) this;
        }

        public Criteria andZyXbNotLike(String value) {
            addCriterion("ZY_XB not like", value, "zyXb");
            return (Criteria) this;
        }

        public Criteria andZyXbIn(List<String> values) {
            addCriterion("ZY_XB in", values, "zyXb");
            return (Criteria) this;
        }

        public Criteria andZyXbNotIn(List<String> values) {
            addCriterion("ZY_XB not in", values, "zyXb");
            return (Criteria) this;
        }

        public Criteria andZyXbBetween(String value1, String value2) {
            addCriterion("ZY_XB between", value1, value2, "zyXb");
            return (Criteria) this;
        }

        public Criteria andZyXbNotBetween(String value1, String value2) {
            addCriterion("ZY_XB not between", value1, value2, "zyXb");
            return (Criteria) this;
        }

        public Criteria andYdhIsNull() {
            addCriterion("YDH is null");
            return (Criteria) this;
        }

        public Criteria andYdhIsNotNull() {
            addCriterion("YDH is not null");
            return (Criteria) this;
        }

        public Criteria andYdhEqualTo(String value) {
            addCriterion("YDH =", value, "ydh");
            return (Criteria) this;
        }

        public Criteria andYdhNotEqualTo(String value) {
            addCriterion("YDH <>", value, "ydh");
            return (Criteria) this;
        }

        public Criteria andYdhGreaterThan(String value) {
            addCriterion("YDH >", value, "ydh");
            return (Criteria) this;
        }

        public Criteria andYdhGreaterThanOrEqualTo(String value) {
            addCriterion("YDH >=", value, "ydh");
            return (Criteria) this;
        }

        public Criteria andYdhLessThan(String value) {
            addCriterion("YDH <", value, "ydh");
            return (Criteria) this;
        }

        public Criteria andYdhLessThanOrEqualTo(String value) {
            addCriterion("YDH <=", value, "ydh");
            return (Criteria) this;
        }

        public Criteria andYdhLike(String value) {
            addCriterion("YDH like", value, "ydh");
            return (Criteria) this;
        }

        public Criteria andYdhNotLike(String value) {
            addCriterion("YDH not like", value, "ydh");
            return (Criteria) this;
        }

        public Criteria andYdhIn(List<String> values) {
            addCriterion("YDH in", values, "ydh");
            return (Criteria) this;
        }

        public Criteria andYdhNotIn(List<String> values) {
            addCriterion("YDH not in", values, "ydh");
            return (Criteria) this;
        }

        public Criteria andYdhBetween(String value1, String value2) {
            addCriterion("YDH between", value1, value2, "ydh");
            return (Criteria) this;
        }

        public Criteria andYdhNotBetween(String value1, String value2) {
            addCriterion("YDH not between", value1, value2, "ydh");
            return (Criteria) this;
        }

        public Criteria andTfhIsNull() {
            addCriterion("TFH is null");
            return (Criteria) this;
        }

        public Criteria andTfhIsNotNull() {
            addCriterion("TFH is not null");
            return (Criteria) this;
        }

        public Criteria andTfhEqualTo(String value) {
            addCriterion("TFH =", value, "tfh");
            return (Criteria) this;
        }

        public Criteria andTfhNotEqualTo(String value) {
            addCriterion("TFH <>", value, "tfh");
            return (Criteria) this;
        }

        public Criteria andTfhGreaterThan(String value) {
            addCriterion("TFH >", value, "tfh");
            return (Criteria) this;
        }

        public Criteria andTfhGreaterThanOrEqualTo(String value) {
            addCriterion("TFH >=", value, "tfh");
            return (Criteria) this;
        }

        public Criteria andTfhLessThan(String value) {
            addCriterion("TFH <", value, "tfh");
            return (Criteria) this;
        }

        public Criteria andTfhLessThanOrEqualTo(String value) {
            addCriterion("TFH <=", value, "tfh");
            return (Criteria) this;
        }

        public Criteria andTfhLike(String value) {
            addCriterion("TFH like", value, "tfh");
            return (Criteria) this;
        }

        public Criteria andTfhNotLike(String value) {
            addCriterion("TFH not like", value, "tfh");
            return (Criteria) this;
        }

        public Criteria andTfhIn(List<String> values) {
            addCriterion("TFH in", values, "tfh");
            return (Criteria) this;
        }

        public Criteria andTfhNotIn(List<String> values) {
            addCriterion("TFH not in", values, "tfh");
            return (Criteria) this;
        }

        public Criteria andTfhBetween(String value1, String value2) {
            addCriterion("TFH between", value1, value2, "tfh");
            return (Criteria) this;
        }

        public Criteria andTfhNotBetween(String value1, String value2) {
            addCriterion("TFH not between", value1, value2, "tfh");
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

        public Criteria andZlqDileiIsNull() {
            addCriterion("ZLQ_DILEI is null");
            return (Criteria) this;
        }

        public Criteria andZlqDileiIsNotNull() {
            addCriterion("ZLQ_DILEI is not null");
            return (Criteria) this;
        }

        public Criteria andZlqDileiEqualTo(String value) {
            addCriterion("ZLQ_DILEI =", value, "zlqDilei");
            return (Criteria) this;
        }

        public Criteria andZlqDileiNotEqualTo(String value) {
            addCriterion("ZLQ_DILEI <>", value, "zlqDilei");
            return (Criteria) this;
        }

        public Criteria andZlqDileiGreaterThan(String value) {
            addCriterion("ZLQ_DILEI >", value, "zlqDilei");
            return (Criteria) this;
        }

        public Criteria andZlqDileiGreaterThanOrEqualTo(String value) {
            addCriterion("ZLQ_DILEI >=", value, "zlqDilei");
            return (Criteria) this;
        }

        public Criteria andZlqDileiLessThan(String value) {
            addCriterion("ZLQ_DILEI <", value, "zlqDilei");
            return (Criteria) this;
        }

        public Criteria andZlqDileiLessThanOrEqualTo(String value) {
            addCriterion("ZLQ_DILEI <=", value, "zlqDilei");
            return (Criteria) this;
        }

        public Criteria andZlqDileiLike(String value) {
            addCriterion("ZLQ_DILEI like", value, "zlqDilei");
            return (Criteria) this;
        }

        public Criteria andZlqDileiNotLike(String value) {
            addCriterion("ZLQ_DILEI not like", value, "zlqDilei");
            return (Criteria) this;
        }

        public Criteria andZlqDileiIn(List<String> values) {
            addCriterion("ZLQ_DILEI in", values, "zlqDilei");
            return (Criteria) this;
        }

        public Criteria andZlqDileiNotIn(List<String> values) {
            addCriterion("ZLQ_DILEI not in", values, "zlqDilei");
            return (Criteria) this;
        }

        public Criteria andZlqDileiBetween(String value1, String value2) {
            addCriterion("ZLQ_DILEI between", value1, value2, "zlqDilei");
            return (Criteria) this;
        }

        public Criteria andZlqDileiNotBetween(String value1, String value2) {
            addCriterion("ZLQ_DILEI not between", value1, value2, "zlqDilei");
            return (Criteria) this;
        }

        public Criteria andDileiIsNull() {
            addCriterion("DILEI is null");
            return (Criteria) this;
        }

        public Criteria andDileiIsNotNull() {
            addCriterion("DILEI is not null");
            return (Criteria) this;
        }

        public Criteria andDileiEqualTo(String value) {
            addCriterion("DILEI =", value, "dilei");
            return (Criteria) this;
        }

        public Criteria andDileiNotEqualTo(String value) {
            addCriterion("DILEI <>", value, "dilei");
            return (Criteria) this;
        }

        public Criteria andDileiGreaterThan(String value) {
            addCriterion("DILEI >", value, "dilei");
            return (Criteria) this;
        }

        public Criteria andDileiGreaterThanOrEqualTo(String value) {
            addCriterion("DILEI >=", value, "dilei");
            return (Criteria) this;
        }

        public Criteria andDileiLessThan(String value) {
            addCriterion("DILEI <", value, "dilei");
            return (Criteria) this;
        }

        public Criteria andDileiLessThanOrEqualTo(String value) {
            addCriterion("DILEI <=", value, "dilei");
            return (Criteria) this;
        }

        public Criteria andDileiLike(String value) {
            addCriterion("DILEI like", value, "dilei");
            return (Criteria) this;
        }

        public Criteria andDileiNotLike(String value) {
            addCriterion("DILEI not like", value, "dilei");
            return (Criteria) this;
        }

        public Criteria andDileiIn(List<String> values) {
            addCriterion("DILEI in", values, "dilei");
            return (Criteria) this;
        }

        public Criteria andDileiNotIn(List<String> values) {
            addCriterion("DILEI not in", values, "dilei");
            return (Criteria) this;
        }

        public Criteria andDileiBetween(String value1, String value2) {
            addCriterion("DILEI between", value1, value2, "dilei");
            return (Criteria) this;
        }

        public Criteria andDileiNotBetween(String value1, String value2) {
            addCriterion("DILEI not between", value1, value2, "dilei");
            return (Criteria) this;
        }

        public Criteria andFylxIsNull() {
            addCriterion("FYLX is null");
            return (Criteria) this;
        }

        public Criteria andFylxIsNotNull() {
            addCriterion("FYLX is not null");
            return (Criteria) this;
        }

        public Criteria andFylxEqualTo(String value) {
            addCriterion("FYLX =", value, "fylx");
            return (Criteria) this;
        }

        public Criteria andFylxNotEqualTo(String value) {
            addCriterion("FYLX <>", value, "fylx");
            return (Criteria) this;
        }

        public Criteria andFylxGreaterThan(String value) {
            addCriterion("FYLX >", value, "fylx");
            return (Criteria) this;
        }

        public Criteria andFylxGreaterThanOrEqualTo(String value) {
            addCriterion("FYLX >=", value, "fylx");
            return (Criteria) this;
        }

        public Criteria andFylxLessThan(String value) {
            addCriterion("FYLX <", value, "fylx");
            return (Criteria) this;
        }

        public Criteria andFylxLessThanOrEqualTo(String value) {
            addCriterion("FYLX <=", value, "fylx");
            return (Criteria) this;
        }

        public Criteria andFylxLike(String value) {
            addCriterion("FYLX like", value, "fylx");
            return (Criteria) this;
        }

        public Criteria andFylxNotLike(String value) {
            addCriterion("FYLX not like", value, "fylx");
            return (Criteria) this;
        }

        public Criteria andFylxIn(List<String> values) {
            addCriterion("FYLX in", values, "fylx");
            return (Criteria) this;
        }

        public Criteria andFylxNotIn(List<String> values) {
            addCriterion("FYLX not in", values, "fylx");
            return (Criteria) this;
        }

        public Criteria andFylxBetween(String value1, String value2) {
            addCriterion("FYLX between", value1, value2, "fylx");
            return (Criteria) this;
        }

        public Criteria andFylxNotBetween(String value1, String value2) {
            addCriterion("FYLX not between", value1, value2, "fylx");
            return (Criteria) this;
        }

        public Criteria andFyfsIsNull() {
            addCriterion("FYFS is null");
            return (Criteria) this;
        }

        public Criteria andFyfsIsNotNull() {
            addCriterion("FYFS is not null");
            return (Criteria) this;
        }

        public Criteria andFyfsEqualTo(String value) {
            addCriterion("FYFS =", value, "fyfs");
            return (Criteria) this;
        }

        public Criteria andFyfsNotEqualTo(String value) {
            addCriterion("FYFS <>", value, "fyfs");
            return (Criteria) this;
        }

        public Criteria andFyfsGreaterThan(String value) {
            addCriterion("FYFS >", value, "fyfs");
            return (Criteria) this;
        }

        public Criteria andFyfsGreaterThanOrEqualTo(String value) {
            addCriterion("FYFS >=", value, "fyfs");
            return (Criteria) this;
        }

        public Criteria andFyfsLessThan(String value) {
            addCriterion("FYFS <", value, "fyfs");
            return (Criteria) this;
        }

        public Criteria andFyfsLessThanOrEqualTo(String value) {
            addCriterion("FYFS <=", value, "fyfs");
            return (Criteria) this;
        }

        public Criteria andFyfsLike(String value) {
            addCriterion("FYFS like", value, "fyfs");
            return (Criteria) this;
        }

        public Criteria andFyfsNotLike(String value) {
            addCriterion("FYFS not like", value, "fyfs");
            return (Criteria) this;
        }

        public Criteria andFyfsIn(List<String> values) {
            addCriterion("FYFS in", values, "fyfs");
            return (Criteria) this;
        }

        public Criteria andFyfsNotIn(List<String> values) {
            addCriterion("FYFS not in", values, "fyfs");
            return (Criteria) this;
        }

        public Criteria andFyfsBetween(String value1, String value2) {
            addCriterion("FYFS between", value1, value2, "fyfs");
            return (Criteria) this;
        }

        public Criteria andFyfsNotBetween(String value1, String value2) {
            addCriterion("FYFS not between", value1, value2, "fyfs");
            return (Criteria) this;
        }

        public Criteria andLdqsIsNull() {
            addCriterion("LDQS is null");
            return (Criteria) this;
        }

        public Criteria andLdqsIsNotNull() {
            addCriterion("LDQS is not null");
            return (Criteria) this;
        }

        public Criteria andLdqsEqualTo(String value) {
            addCriterion("LDQS =", value, "ldqs");
            return (Criteria) this;
        }

        public Criteria andLdqsNotEqualTo(String value) {
            addCriterion("LDQS <>", value, "ldqs");
            return (Criteria) this;
        }

        public Criteria andLdqsGreaterThan(String value) {
            addCriterion("LDQS >", value, "ldqs");
            return (Criteria) this;
        }

        public Criteria andLdqsGreaterThanOrEqualTo(String value) {
            addCriterion("LDQS >=", value, "ldqs");
            return (Criteria) this;
        }

        public Criteria andLdqsLessThan(String value) {
            addCriterion("LDQS <", value, "ldqs");
            return (Criteria) this;
        }

        public Criteria andLdqsLessThanOrEqualTo(String value) {
            addCriterion("LDQS <=", value, "ldqs");
            return (Criteria) this;
        }

        public Criteria andLdqsLike(String value) {
            addCriterion("LDQS like", value, "ldqs");
            return (Criteria) this;
        }

        public Criteria andLdqsNotLike(String value) {
            addCriterion("LDQS not like", value, "ldqs");
            return (Criteria) this;
        }

        public Criteria andLdqsIn(List<String> values) {
            addCriterion("LDQS in", values, "ldqs");
            return (Criteria) this;
        }

        public Criteria andLdqsNotIn(List<String> values) {
            addCriterion("LDQS not in", values, "ldqs");
            return (Criteria) this;
        }

        public Criteria andLdqsBetween(String value1, String value2) {
            addCriterion("LDQS between", value1, value2, "ldqs");
            return (Criteria) this;
        }

        public Criteria andLdqsNotBetween(String value1, String value2) {
            addCriterion("LDQS not between", value1, value2, "ldqs");
            return (Criteria) this;
        }

        public Criteria andLmqsIsNull() {
            addCriterion("LMQS is null");
            return (Criteria) this;
        }

        public Criteria andLmqsIsNotNull() {
            addCriterion("LMQS is not null");
            return (Criteria) this;
        }

        public Criteria andLmqsEqualTo(String value) {
            addCriterion("LMQS =", value, "lmqs");
            return (Criteria) this;
        }

        public Criteria andLmqsNotEqualTo(String value) {
            addCriterion("LMQS <>", value, "lmqs");
            return (Criteria) this;
        }

        public Criteria andLmqsGreaterThan(String value) {
            addCriterion("LMQS >", value, "lmqs");
            return (Criteria) this;
        }

        public Criteria andLmqsGreaterThanOrEqualTo(String value) {
            addCriterion("LMQS >=", value, "lmqs");
            return (Criteria) this;
        }

        public Criteria andLmqsLessThan(String value) {
            addCriterion("LMQS <", value, "lmqs");
            return (Criteria) this;
        }

        public Criteria andLmqsLessThanOrEqualTo(String value) {
            addCriterion("LMQS <=", value, "lmqs");
            return (Criteria) this;
        }

        public Criteria andLmqsLike(String value) {
            addCriterion("LMQS like", value, "lmqs");
            return (Criteria) this;
        }

        public Criteria andLmqsNotLike(String value) {
            addCriterion("LMQS not like", value, "lmqs");
            return (Criteria) this;
        }

        public Criteria andLmqsIn(List<String> values) {
            addCriterion("LMQS in", values, "lmqs");
            return (Criteria) this;
        }

        public Criteria andLmqsNotIn(List<String> values) {
            addCriterion("LMQS not in", values, "lmqs");
            return (Criteria) this;
        }

        public Criteria andLmqsBetween(String value1, String value2) {
            addCriterion("LMQS between", value1, value2, "lmqs");
            return (Criteria) this;
        }

        public Criteria andLmqsNotBetween(String value1, String value2) {
            addCriterion("LMQS not between", value1, value2, "lmqs");
            return (Criteria) this;
        }

        public Criteria andLzIsNull() {
            addCriterion("LZ is null");
            return (Criteria) this;
        }

        public Criteria andLzIsNotNull() {
            addCriterion("LZ is not null");
            return (Criteria) this;
        }

        public Criteria andLzEqualTo(String value) {
            addCriterion("LZ =", value, "lz");
            return (Criteria) this;
        }

        public Criteria andLzNotEqualTo(String value) {
            addCriterion("LZ <>", value, "lz");
            return (Criteria) this;
        }

        public Criteria andLzGreaterThan(String value) {
            addCriterion("LZ >", value, "lz");
            return (Criteria) this;
        }

        public Criteria andLzGreaterThanOrEqualTo(String value) {
            addCriterion("LZ >=", value, "lz");
            return (Criteria) this;
        }

        public Criteria andLzLessThan(String value) {
            addCriterion("LZ <", value, "lz");
            return (Criteria) this;
        }

        public Criteria andLzLessThanOrEqualTo(String value) {
            addCriterion("LZ <=", value, "lz");
            return (Criteria) this;
        }

        public Criteria andLzLike(String value) {
            addCriterion("LZ like", value, "lz");
            return (Criteria) this;
        }

        public Criteria andLzNotLike(String value) {
            addCriterion("LZ not like", value, "lz");
            return (Criteria) this;
        }

        public Criteria andLzIn(List<String> values) {
            addCriterion("LZ in", values, "lz");
            return (Criteria) this;
        }

        public Criteria andLzNotIn(List<String> values) {
            addCriterion("LZ not in", values, "lz");
            return (Criteria) this;
        }

        public Criteria andLzBetween(String value1, String value2) {
            addCriterion("LZ between", value1, value2, "lz");
            return (Criteria) this;
        }

        public Criteria andLzNotBetween(String value1, String value2) {
            addCriterion("LZ not between", value1, value2, "lz");
            return (Criteria) this;
        }

        public Criteria andSz1IsNull() {
            addCriterion("SZ1 is null");
            return (Criteria) this;
        }

        public Criteria andSz1IsNotNull() {
            addCriterion("SZ1 is not null");
            return (Criteria) this;
        }

        public Criteria andSz1EqualTo(String value) {
            addCriterion("SZ1 =", value, "sz1");
            return (Criteria) this;
        }

        public Criteria andSz1NotEqualTo(String value) {
            addCriterion("SZ1 <>", value, "sz1");
            return (Criteria) this;
        }

        public Criteria andSz1GreaterThan(String value) {
            addCriterion("SZ1 >", value, "sz1");
            return (Criteria) this;
        }

        public Criteria andSz1GreaterThanOrEqualTo(String value) {
            addCriterion("SZ1 >=", value, "sz1");
            return (Criteria) this;
        }

        public Criteria andSz1LessThan(String value) {
            addCriterion("SZ1 <", value, "sz1");
            return (Criteria) this;
        }

        public Criteria andSz1LessThanOrEqualTo(String value) {
            addCriterion("SZ1 <=", value, "sz1");
            return (Criteria) this;
        }

        public Criteria andSz1Like(String value) {
            addCriterion("SZ1 like", value, "sz1");
            return (Criteria) this;
        }

        public Criteria andSz1NotLike(String value) {
            addCriterion("SZ1 not like", value, "sz1");
            return (Criteria) this;
        }

        public Criteria andSz1In(List<String> values) {
            addCriterion("SZ1 in", values, "sz1");
            return (Criteria) this;
        }

        public Criteria andSz1NotIn(List<String> values) {
            addCriterion("SZ1 not in", values, "sz1");
            return (Criteria) this;
        }

        public Criteria andSz1Between(String value1, String value2) {
            addCriterion("SZ1 between", value1, value2, "sz1");
            return (Criteria) this;
        }

        public Criteria andSz1NotBetween(String value1, String value2) {
            addCriterion("SZ1 not between", value1, value2, "sz1");
            return (Criteria) this;
        }

        public Criteria andSz2IsNull() {
            addCriterion("SZ2 is null");
            return (Criteria) this;
        }

        public Criteria andSz2IsNotNull() {
            addCriterion("SZ2 is not null");
            return (Criteria) this;
        }

        public Criteria andSz2EqualTo(String value) {
            addCriterion("SZ2 =", value, "sz2");
            return (Criteria) this;
        }

        public Criteria andSz2NotEqualTo(String value) {
            addCriterion("SZ2 <>", value, "sz2");
            return (Criteria) this;
        }

        public Criteria andSz2GreaterThan(String value) {
            addCriterion("SZ2 >", value, "sz2");
            return (Criteria) this;
        }

        public Criteria andSz2GreaterThanOrEqualTo(String value) {
            addCriterion("SZ2 >=", value, "sz2");
            return (Criteria) this;
        }

        public Criteria andSz2LessThan(String value) {
            addCriterion("SZ2 <", value, "sz2");
            return (Criteria) this;
        }

        public Criteria andSz2LessThanOrEqualTo(String value) {
            addCriterion("SZ2 <=", value, "sz2");
            return (Criteria) this;
        }

        public Criteria andSz2Like(String value) {
            addCriterion("SZ2 like", value, "sz2");
            return (Criteria) this;
        }

        public Criteria andSz2NotLike(String value) {
            addCriterion("SZ2 not like", value, "sz2");
            return (Criteria) this;
        }

        public Criteria andSz2In(List<String> values) {
            addCriterion("SZ2 in", values, "sz2");
            return (Criteria) this;
        }

        public Criteria andSz2NotIn(List<String> values) {
            addCriterion("SZ2 not in", values, "sz2");
            return (Criteria) this;
        }

        public Criteria andSz2Between(String value1, String value2) {
            addCriterion("SZ2 between", value1, value2, "sz2");
            return (Criteria) this;
        }

        public Criteria andSz2NotBetween(String value1, String value2) {
            addCriterion("SZ2 not between", value1, value2, "sz2");
            return (Criteria) this;
        }

        public Criteria andBiliIsNull() {
            addCriterion("BILI is null");
            return (Criteria) this;
        }

        public Criteria andBiliIsNotNull() {
            addCriterion("BILI is not null");
            return (Criteria) this;
        }

        public Criteria andBiliEqualTo(String value) {
            addCriterion("BILI =", value, "bili");
            return (Criteria) this;
        }

        public Criteria andBiliNotEqualTo(String value) {
            addCriterion("BILI <>", value, "bili");
            return (Criteria) this;
        }

        public Criteria andBiliGreaterThan(String value) {
            addCriterion("BILI >", value, "bili");
            return (Criteria) this;
        }

        public Criteria andBiliGreaterThanOrEqualTo(String value) {
            addCriterion("BILI >=", value, "bili");
            return (Criteria) this;
        }

        public Criteria andBiliLessThan(String value) {
            addCriterion("BILI <", value, "bili");
            return (Criteria) this;
        }

        public Criteria andBiliLessThanOrEqualTo(String value) {
            addCriterion("BILI <=", value, "bili");
            return (Criteria) this;
        }

        public Criteria andBiliLike(String value) {
            addCriterion("BILI like", value, "bili");
            return (Criteria) this;
        }

        public Criteria andBiliNotLike(String value) {
            addCriterion("BILI not like", value, "bili");
            return (Criteria) this;
        }

        public Criteria andBiliIn(List<String> values) {
            addCriterion("BILI in", values, "bili");
            return (Criteria) this;
        }

        public Criteria andBiliNotIn(List<String> values) {
            addCriterion("BILI not in", values, "bili");
            return (Criteria) this;
        }

        public Criteria andBiliBetween(String value1, String value2) {
            addCriterion("BILI between", value1, value2, "bili");
            return (Criteria) this;
        }

        public Criteria andBiliNotBetween(String value1, String value2) {
            addCriterion("BILI not between", value1, value2, "bili");
            return (Criteria) this;
        }

        public Criteria andZblxIsNull() {
            addCriterion("ZBLX is null");
            return (Criteria) this;
        }

        public Criteria andZblxIsNotNull() {
            addCriterion("ZBLX is not null");
            return (Criteria) this;
        }

        public Criteria andZblxEqualTo(String value) {
            addCriterion("ZBLX =", value, "zblx");
            return (Criteria) this;
        }

        public Criteria andZblxNotEqualTo(String value) {
            addCriterion("ZBLX <>", value, "zblx");
            return (Criteria) this;
        }

        public Criteria andZblxGreaterThan(String value) {
            addCriterion("ZBLX >", value, "zblx");
            return (Criteria) this;
        }

        public Criteria andZblxGreaterThanOrEqualTo(String value) {
            addCriterion("ZBLX >=", value, "zblx");
            return (Criteria) this;
        }

        public Criteria andZblxLessThan(String value) {
            addCriterion("ZBLX <", value, "zblx");
            return (Criteria) this;
        }

        public Criteria andZblxLessThanOrEqualTo(String value) {
            addCriterion("ZBLX <=", value, "zblx");
            return (Criteria) this;
        }

        public Criteria andZblxLike(String value) {
            addCriterion("ZBLX like", value, "zblx");
            return (Criteria) this;
        }

        public Criteria andZblxNotLike(String value) {
            addCriterion("ZBLX not like", value, "zblx");
            return (Criteria) this;
        }

        public Criteria andZblxIn(List<String> values) {
            addCriterion("ZBLX in", values, "zblx");
            return (Criteria) this;
        }

        public Criteria andZblxNotIn(List<String> values) {
            addCriterion("ZBLX not in", values, "zblx");
            return (Criteria) this;
        }

        public Criteria andZblxBetween(String value1, String value2) {
            addCriterion("ZBLX between", value1, value2, "zblx");
            return (Criteria) this;
        }

        public Criteria andZblxNotBetween(String value1, String value2) {
            addCriterion("ZBLX not between", value1, value2, "zblx");
            return (Criteria) this;
        }

        public Criteria andPoDuIsNull() {
            addCriterion("PO_DU is null");
            return (Criteria) this;
        }

        public Criteria andPoDuIsNotNull() {
            addCriterion("PO_DU is not null");
            return (Criteria) this;
        }

        public Criteria andPoDuEqualTo(String value) {
            addCriterion("PO_DU =", value, "poDu");
            return (Criteria) this;
        }

        public Criteria andPoDuNotEqualTo(String value) {
            addCriterion("PO_DU <>", value, "poDu");
            return (Criteria) this;
        }

        public Criteria andPoDuGreaterThan(String value) {
            addCriterion("PO_DU >", value, "poDu");
            return (Criteria) this;
        }

        public Criteria andPoDuGreaterThanOrEqualTo(String value) {
            addCriterion("PO_DU >=", value, "poDu");
            return (Criteria) this;
        }

        public Criteria andPoDuLessThan(String value) {
            addCriterion("PO_DU <", value, "poDu");
            return (Criteria) this;
        }

        public Criteria andPoDuLessThanOrEqualTo(String value) {
            addCriterion("PO_DU <=", value, "poDu");
            return (Criteria) this;
        }

        public Criteria andPoDuLike(String value) {
            addCriterion("PO_DU like", value, "poDu");
            return (Criteria) this;
        }

        public Criteria andPoDuNotLike(String value) {
            addCriterion("PO_DU not like", value, "poDu");
            return (Criteria) this;
        }

        public Criteria andPoDuIn(List<String> values) {
            addCriterion("PO_DU in", values, "poDu");
            return (Criteria) this;
        }

        public Criteria andPoDuNotIn(List<String> values) {
            addCriterion("PO_DU not in", values, "poDu");
            return (Criteria) this;
        }

        public Criteria andPoDuBetween(String value1, String value2) {
            addCriterion("PO_DU between", value1, value2, "poDu");
            return (Criteria) this;
        }

        public Criteria andPoDuNotBetween(String value1, String value2) {
            addCriterion("PO_DU not between", value1, value2, "poDu");
            return (Criteria) this;
        }

        public Criteria andXtjsbmjIsNull() {
            addCriterion("XTJSBMJ is null");
            return (Criteria) this;
        }

        public Criteria andXtjsbmjIsNotNull() {
            addCriterion("XTJSBMJ is not null");
            return (Criteria) this;
        }

        public Criteria andXtjsbmjEqualTo(String value) {
            addCriterion("XTJSBMJ =", value, "xtjsbmj");
            return (Criteria) this;
        }

        public Criteria andXtjsbmjNotEqualTo(String value) {
            addCriterion("XTJSBMJ <>", value, "xtjsbmj");
            return (Criteria) this;
        }

        public Criteria andXtjsbmjGreaterThan(String value) {
            addCriterion("XTJSBMJ >", value, "xtjsbmj");
            return (Criteria) this;
        }

        public Criteria andXtjsbmjGreaterThanOrEqualTo(String value) {
            addCriterion("XTJSBMJ >=", value, "xtjsbmj");
            return (Criteria) this;
        }

        public Criteria andXtjsbmjLessThan(String value) {
            addCriterion("XTJSBMJ <", value, "xtjsbmj");
            return (Criteria) this;
        }

        public Criteria andXtjsbmjLessThanOrEqualTo(String value) {
            addCriterion("XTJSBMJ <=", value, "xtjsbmj");
            return (Criteria) this;
        }

        public Criteria andXtjsbmjLike(String value) {
            addCriterion("XTJSBMJ like", value, "xtjsbmj");
            return (Criteria) this;
        }

        public Criteria andXtjsbmjNotLike(String value) {
            addCriterion("XTJSBMJ not like", value, "xtjsbmj");
            return (Criteria) this;
        }

        public Criteria andXtjsbmjIn(List<String> values) {
            addCriterion("XTJSBMJ in", values, "xtjsbmj");
            return (Criteria) this;
        }

        public Criteria andXtjsbmjNotIn(List<String> values) {
            addCriterion("XTJSBMJ not in", values, "xtjsbmj");
            return (Criteria) this;
        }

        public Criteria andXtjsbmjBetween(String value1, String value2) {
            addCriterion("XTJSBMJ between", value1, value2, "xtjsbmj");
            return (Criteria) this;
        }

        public Criteria andXtjsbmjNotBetween(String value1, String value2) {
            addCriterion("XTJSBMJ not between", value1, value2, "xtjsbmj");
            return (Criteria) this;
        }

        public Criteria andChlIsNull() {
            addCriterion("CHL is null");
            return (Criteria) this;
        }

        public Criteria andChlIsNotNull() {
            addCriterion("CHL is not null");
            return (Criteria) this;
        }

        public Criteria andChlEqualTo(String value) {
            addCriterion("CHL =", value, "chl");
            return (Criteria) this;
        }

        public Criteria andChlNotEqualTo(String value) {
            addCriterion("CHL <>", value, "chl");
            return (Criteria) this;
        }

        public Criteria andChlGreaterThan(String value) {
            addCriterion("CHL >", value, "chl");
            return (Criteria) this;
        }

        public Criteria andChlGreaterThanOrEqualTo(String value) {
            addCriterion("CHL >=", value, "chl");
            return (Criteria) this;
        }

        public Criteria andChlLessThan(String value) {
            addCriterion("CHL <", value, "chl");
            return (Criteria) this;
        }

        public Criteria andChlLessThanOrEqualTo(String value) {
            addCriterion("CHL <=", value, "chl");
            return (Criteria) this;
        }

        public Criteria andChlLike(String value) {
            addCriterion("CHL like", value, "chl");
            return (Criteria) this;
        }

        public Criteria andChlNotLike(String value) {
            addCriterion("CHL not like", value, "chl");
            return (Criteria) this;
        }

        public Criteria andChlIn(List<String> values) {
            addCriterion("CHL in", values, "chl");
            return (Criteria) this;
        }

        public Criteria andChlNotIn(List<String> values) {
            addCriterion("CHL not in", values, "chl");
            return (Criteria) this;
        }

        public Criteria andChlBetween(String value1, String value2) {
            addCriterion("CHL between", value1, value2, "chl");
            return (Criteria) this;
        }

        public Criteria andChlNotBetween(String value1, String value2) {
            addCriterion("CHL not between", value1, value2, "chl");
            return (Criteria) this;
        }

        public Criteria andChlDjIsNull() {
            addCriterion("CHL_DJ is null");
            return (Criteria) this;
        }

        public Criteria andChlDjIsNotNull() {
            addCriterion("CHL_DJ is not null");
            return (Criteria) this;
        }

        public Criteria andChlDjEqualTo(String value) {
            addCriterion("CHL_DJ =", value, "chlDj");
            return (Criteria) this;
        }

        public Criteria andChlDjNotEqualTo(String value) {
            addCriterion("CHL_DJ <>", value, "chlDj");
            return (Criteria) this;
        }

        public Criteria andChlDjGreaterThan(String value) {
            addCriterion("CHL_DJ >", value, "chlDj");
            return (Criteria) this;
        }

        public Criteria andChlDjGreaterThanOrEqualTo(String value) {
            addCriterion("CHL_DJ >=", value, "chlDj");
            return (Criteria) this;
        }

        public Criteria andChlDjLessThan(String value) {
            addCriterion("CHL_DJ <", value, "chlDj");
            return (Criteria) this;
        }

        public Criteria andChlDjLessThanOrEqualTo(String value) {
            addCriterion("CHL_DJ <=", value, "chlDj");
            return (Criteria) this;
        }

        public Criteria andChlDjLike(String value) {
            addCriterion("CHL_DJ like", value, "chlDj");
            return (Criteria) this;
        }

        public Criteria andChlDjNotLike(String value) {
            addCriterion("CHL_DJ not like", value, "chlDj");
            return (Criteria) this;
        }

        public Criteria andChlDjIn(List<String> values) {
            addCriterion("CHL_DJ in", values, "chlDj");
            return (Criteria) this;
        }

        public Criteria andChlDjNotIn(List<String> values) {
            addCriterion("CHL_DJ not in", values, "chlDj");
            return (Criteria) this;
        }

        public Criteria andChlDjBetween(String value1, String value2) {
            addCriterion("CHL_DJ between", value1, value2, "chlDj");
            return (Criteria) this;
        }

        public Criteria andChlDjNotBetween(String value1, String value2) {
            addCriterion("CHL_DJ not between", value1, value2, "chlDj");
            return (Criteria) this;
        }

        public Criteria andYbdIsNull() {
            addCriterion("YBD is null");
            return (Criteria) this;
        }

        public Criteria andYbdIsNotNull() {
            addCriterion("YBD is not null");
            return (Criteria) this;
        }

        public Criteria andYbdEqualTo(String value) {
            addCriterion("YBD =", value, "ybd");
            return (Criteria) this;
        }

        public Criteria andYbdNotEqualTo(String value) {
            addCriterion("YBD <>", value, "ybd");
            return (Criteria) this;
        }

        public Criteria andYbdGreaterThan(String value) {
            addCriterion("YBD >", value, "ybd");
            return (Criteria) this;
        }

        public Criteria andYbdGreaterThanOrEqualTo(String value) {
            addCriterion("YBD >=", value, "ybd");
            return (Criteria) this;
        }

        public Criteria andYbdLessThan(String value) {
            addCriterion("YBD <", value, "ybd");
            return (Criteria) this;
        }

        public Criteria andYbdLessThanOrEqualTo(String value) {
            addCriterion("YBD <=", value, "ybd");
            return (Criteria) this;
        }

        public Criteria andYbdLike(String value) {
            addCriterion("YBD like", value, "ybd");
            return (Criteria) this;
        }

        public Criteria andYbdNotLike(String value) {
            addCriterion("YBD not like", value, "ybd");
            return (Criteria) this;
        }

        public Criteria andYbdIn(List<String> values) {
            addCriterion("YBD in", values, "ybd");
            return (Criteria) this;
        }

        public Criteria andYbdNotIn(List<String> values) {
            addCriterion("YBD not in", values, "ybd");
            return (Criteria) this;
        }

        public Criteria andYbdBetween(String value1, String value2) {
            addCriterion("YBD between", value1, value2, "ybd");
            return (Criteria) this;
        }

        public Criteria andYbdNotBetween(String value1, String value2) {
            addCriterion("YBD not between", value1, value2, "ybd");
            return (Criteria) this;
        }

        public Criteria andHsmjIsNull() {
            addCriterion("HSMJ is null");
            return (Criteria) this;
        }

        public Criteria andHsmjIsNotNull() {
            addCriterion("HSMJ is not null");
            return (Criteria) this;
        }

        public Criteria andHsmjEqualTo(String value) {
            addCriterion("HSMJ =", value, "hsmj");
            return (Criteria) this;
        }

        public Criteria andHsmjNotEqualTo(String value) {
            addCriterion("HSMJ <>", value, "hsmj");
            return (Criteria) this;
        }

        public Criteria andHsmjGreaterThan(String value) {
            addCriterion("HSMJ >", value, "hsmj");
            return (Criteria) this;
        }

        public Criteria andHsmjGreaterThanOrEqualTo(String value) {
            addCriterion("HSMJ >=", value, "hsmj");
            return (Criteria) this;
        }

        public Criteria andHsmjLessThan(String value) {
            addCriterion("HSMJ <", value, "hsmj");
            return (Criteria) this;
        }

        public Criteria andHsmjLessThanOrEqualTo(String value) {
            addCriterion("HSMJ <=", value, "hsmj");
            return (Criteria) this;
        }

        public Criteria andHsmjLike(String value) {
            addCriterion("HSMJ like", value, "hsmj");
            return (Criteria) this;
        }

        public Criteria andHsmjNotLike(String value) {
            addCriterion("HSMJ not like", value, "hsmj");
            return (Criteria) this;
        }

        public Criteria andHsmjIn(List<String> values) {
            addCriterion("HSMJ in", values, "hsmj");
            return (Criteria) this;
        }

        public Criteria andHsmjNotIn(List<String> values) {
            addCriterion("HSMJ not in", values, "hsmj");
            return (Criteria) this;
        }

        public Criteria andHsmjBetween(String value1, String value2) {
            addCriterion("HSMJ between", value1, value2, "hsmj");
            return (Criteria) this;
        }

        public Criteria andHsmjNotBetween(String value1, String value2) {
            addCriterion("HSMJ not between", value1, value2, "hsmj");
            return (Criteria) this;
        }

        public Criteria andBhsmjIsNull() {
            addCriterion("BHSMJ is null");
            return (Criteria) this;
        }

        public Criteria andBhsmjIsNotNull() {
            addCriterion("BHSMJ is not null");
            return (Criteria) this;
        }

        public Criteria andBhsmjEqualTo(String value) {
            addCriterion("BHSMJ =", value, "bhsmj");
            return (Criteria) this;
        }

        public Criteria andBhsmjNotEqualTo(String value) {
            addCriterion("BHSMJ <>", value, "bhsmj");
            return (Criteria) this;
        }

        public Criteria andBhsmjGreaterThan(String value) {
            addCriterion("BHSMJ >", value, "bhsmj");
            return (Criteria) this;
        }

        public Criteria andBhsmjGreaterThanOrEqualTo(String value) {
            addCriterion("BHSMJ >=", value, "bhsmj");
            return (Criteria) this;
        }

        public Criteria andBhsmjLessThan(String value) {
            addCriterion("BHSMJ <", value, "bhsmj");
            return (Criteria) this;
        }

        public Criteria andBhsmjLessThanOrEqualTo(String value) {
            addCriterion("BHSMJ <=", value, "bhsmj");
            return (Criteria) this;
        }

        public Criteria andBhsmjLike(String value) {
            addCriterion("BHSMJ like", value, "bhsmj");
            return (Criteria) this;
        }

        public Criteria andBhsmjNotLike(String value) {
            addCriterion("BHSMJ not like", value, "bhsmj");
            return (Criteria) this;
        }

        public Criteria andBhsmjIn(List<String> values) {
            addCriterion("BHSMJ in", values, "bhsmj");
            return (Criteria) this;
        }

        public Criteria andBhsmjNotIn(List<String> values) {
            addCriterion("BHSMJ not in", values, "bhsmj");
            return (Criteria) this;
        }

        public Criteria andBhsmjBetween(String value1, String value2) {
            addCriterion("BHSMJ between", value1, value2, "bhsmj");
            return (Criteria) this;
        }

        public Criteria andBhsmjNotBetween(String value1, String value2) {
            addCriterion("BHSMJ not between", value1, value2, "bhsmj");
            return (Criteria) this;
        }

        public Criteria andHgmjIsNull() {
            addCriterion("HGMJ is null");
            return (Criteria) this;
        }

        public Criteria andHgmjIsNotNull() {
            addCriterion("HGMJ is not null");
            return (Criteria) this;
        }

        public Criteria andHgmjEqualTo(String value) {
            addCriterion("HGMJ =", value, "hgmj");
            return (Criteria) this;
        }

        public Criteria andHgmjNotEqualTo(String value) {
            addCriterion("HGMJ <>", value, "hgmj");
            return (Criteria) this;
        }

        public Criteria andHgmjGreaterThan(String value) {
            addCriterion("HGMJ >", value, "hgmj");
            return (Criteria) this;
        }

        public Criteria andHgmjGreaterThanOrEqualTo(String value) {
            addCriterion("HGMJ >=", value, "hgmj");
            return (Criteria) this;
        }

        public Criteria andHgmjLessThan(String value) {
            addCriterion("HGMJ <", value, "hgmj");
            return (Criteria) this;
        }

        public Criteria andHgmjLessThanOrEqualTo(String value) {
            addCriterion("HGMJ <=", value, "hgmj");
            return (Criteria) this;
        }

        public Criteria andHgmjLike(String value) {
            addCriterion("HGMJ like", value, "hgmj");
            return (Criteria) this;
        }

        public Criteria andHgmjNotLike(String value) {
            addCriterion("HGMJ not like", value, "hgmj");
            return (Criteria) this;
        }

        public Criteria andHgmjIn(List<String> values) {
            addCriterion("HGMJ in", values, "hgmj");
            return (Criteria) this;
        }

        public Criteria andHgmjNotIn(List<String> values) {
            addCriterion("HGMJ not in", values, "hgmj");
            return (Criteria) this;
        }

        public Criteria andHgmjBetween(String value1, String value2) {
            addCriterion("HGMJ between", value1, value2, "hgmj");
            return (Criteria) this;
        }

        public Criteria andHgmjNotBetween(String value1, String value2) {
            addCriterion("HGMJ not between", value1, value2, "hgmj");
            return (Criteria) this;
        }

        public Criteria andClmjIsNull() {
            addCriterion("CLMJ is null");
            return (Criteria) this;
        }

        public Criteria andClmjIsNotNull() {
            addCriterion("CLMJ is not null");
            return (Criteria) this;
        }

        public Criteria andClmjEqualTo(String value) {
            addCriterion("CLMJ =", value, "clmj");
            return (Criteria) this;
        }

        public Criteria andClmjNotEqualTo(String value) {
            addCriterion("CLMJ <>", value, "clmj");
            return (Criteria) this;
        }

        public Criteria andClmjGreaterThan(String value) {
            addCriterion("CLMJ >", value, "clmj");
            return (Criteria) this;
        }

        public Criteria andClmjGreaterThanOrEqualTo(String value) {
            addCriterion("CLMJ >=", value, "clmj");
            return (Criteria) this;
        }

        public Criteria andClmjLessThan(String value) {
            addCriterion("CLMJ <", value, "clmj");
            return (Criteria) this;
        }

        public Criteria andClmjLessThanOrEqualTo(String value) {
            addCriterion("CLMJ <=", value, "clmj");
            return (Criteria) this;
        }

        public Criteria andClmjLike(String value) {
            addCriterion("CLMJ like", value, "clmj");
            return (Criteria) this;
        }

        public Criteria andClmjNotLike(String value) {
            addCriterion("CLMJ not like", value, "clmj");
            return (Criteria) this;
        }

        public Criteria andClmjIn(List<String> values) {
            addCriterion("CLMJ in", values, "clmj");
            return (Criteria) this;
        }

        public Criteria andClmjNotIn(List<String> values) {
            addCriterion("CLMJ not in", values, "clmj");
            return (Criteria) this;
        }

        public Criteria andClmjBetween(String value1, String value2) {
            addCriterion("CLMJ between", value1, value2, "clmj");
            return (Criteria) this;
        }

        public Criteria andClmjNotBetween(String value1, String value2) {
            addCriterion("CLMJ not between", value1, value2, "clmj");
            return (Criteria) this;
        }

        public Criteria andDbzmjIsNull() {
            addCriterion("DBZMJ is null");
            return (Criteria) this;
        }

        public Criteria andDbzmjIsNotNull() {
            addCriterion("DBZMJ is not null");
            return (Criteria) this;
        }

        public Criteria andDbzmjEqualTo(String value) {
            addCriterion("DBZMJ =", value, "dbzmj");
            return (Criteria) this;
        }

        public Criteria andDbzmjNotEqualTo(String value) {
            addCriterion("DBZMJ <>", value, "dbzmj");
            return (Criteria) this;
        }

        public Criteria andDbzmjGreaterThan(String value) {
            addCriterion("DBZMJ >", value, "dbzmj");
            return (Criteria) this;
        }

        public Criteria andDbzmjGreaterThanOrEqualTo(String value) {
            addCriterion("DBZMJ >=", value, "dbzmj");
            return (Criteria) this;
        }

        public Criteria andDbzmjLessThan(String value) {
            addCriterion("DBZMJ <", value, "dbzmj");
            return (Criteria) this;
        }

        public Criteria andDbzmjLessThanOrEqualTo(String value) {
            addCriterion("DBZMJ <=", value, "dbzmj");
            return (Criteria) this;
        }

        public Criteria andDbzmjLike(String value) {
            addCriterion("DBZMJ like", value, "dbzmj");
            return (Criteria) this;
        }

        public Criteria andDbzmjNotLike(String value) {
            addCriterion("DBZMJ not like", value, "dbzmj");
            return (Criteria) this;
        }

        public Criteria andDbzmjIn(List<String> values) {
            addCriterion("DBZMJ in", values, "dbzmj");
            return (Criteria) this;
        }

        public Criteria andDbzmjNotIn(List<String> values) {
            addCriterion("DBZMJ not in", values, "dbzmj");
            return (Criteria) this;
        }

        public Criteria andDbzmjBetween(String value1, String value2) {
            addCriterion("DBZMJ between", value1, value2, "dbzmj");
            return (Criteria) this;
        }

        public Criteria andDbzmjNotBetween(String value1, String value2) {
            addCriterion("DBZMJ not between", value1, value2, "dbzmj");
            return (Criteria) this;
        }

        public Criteria andBhgmjIsNull() {
            addCriterion("BHGMJ is null");
            return (Criteria) this;
        }

        public Criteria andBhgmjIsNotNull() {
            addCriterion("BHGMJ is not null");
            return (Criteria) this;
        }

        public Criteria andBhgmjEqualTo(String value) {
            addCriterion("BHGMJ =", value, "bhgmj");
            return (Criteria) this;
        }

        public Criteria andBhgmjNotEqualTo(String value) {
            addCriterion("BHGMJ <>", value, "bhgmj");
            return (Criteria) this;
        }

        public Criteria andBhgmjGreaterThan(String value) {
            addCriterion("BHGMJ >", value, "bhgmj");
            return (Criteria) this;
        }

        public Criteria andBhgmjGreaterThanOrEqualTo(String value) {
            addCriterion("BHGMJ >=", value, "bhgmj");
            return (Criteria) this;
        }

        public Criteria andBhgmjLessThan(String value) {
            addCriterion("BHGMJ <", value, "bhgmj");
            return (Criteria) this;
        }

        public Criteria andBhgmjLessThanOrEqualTo(String value) {
            addCriterion("BHGMJ <=", value, "bhgmj");
            return (Criteria) this;
        }

        public Criteria andBhgmjLike(String value) {
            addCriterion("BHGMJ like", value, "bhgmj");
            return (Criteria) this;
        }

        public Criteria andBhgmjNotLike(String value) {
            addCriterion("BHGMJ not like", value, "bhgmj");
            return (Criteria) this;
        }

        public Criteria andBhgmjIn(List<String> values) {
            addCriterion("BHGMJ in", values, "bhgmj");
            return (Criteria) this;
        }

        public Criteria andBhgmjNotIn(List<String> values) {
            addCriterion("BHGMJ not in", values, "bhgmj");
            return (Criteria) this;
        }

        public Criteria andBhgmjBetween(String value1, String value2) {
            addCriterion("BHGMJ between", value1, value2, "bhgmj");
            return (Criteria) this;
        }

        public Criteria andBhgmjNotBetween(String value1, String value2) {
            addCriterion("BHGMJ not between", value1, value2, "bhgmj");
            return (Criteria) this;
        }

        public Criteria andSsmjIsNull() {
            addCriterion("SSMJ is null");
            return (Criteria) this;
        }

        public Criteria andSsmjIsNotNull() {
            addCriterion("SSMJ is not null");
            return (Criteria) this;
        }

        public Criteria andSsmjEqualTo(String value) {
            addCriterion("SSMJ =", value, "ssmj");
            return (Criteria) this;
        }

        public Criteria andSsmjNotEqualTo(String value) {
            addCriterion("SSMJ <>", value, "ssmj");
            return (Criteria) this;
        }

        public Criteria andSsmjGreaterThan(String value) {
            addCriterion("SSMJ >", value, "ssmj");
            return (Criteria) this;
        }

        public Criteria andSsmjGreaterThanOrEqualTo(String value) {
            addCriterion("SSMJ >=", value, "ssmj");
            return (Criteria) this;
        }

        public Criteria andSsmjLessThan(String value) {
            addCriterion("SSMJ <", value, "ssmj");
            return (Criteria) this;
        }

        public Criteria andSsmjLessThanOrEqualTo(String value) {
            addCriterion("SSMJ <=", value, "ssmj");
            return (Criteria) this;
        }

        public Criteria andSsmjLike(String value) {
            addCriterion("SSMJ like", value, "ssmj");
            return (Criteria) this;
        }

        public Criteria andSsmjNotLike(String value) {
            addCriterion("SSMJ not like", value, "ssmj");
            return (Criteria) this;
        }

        public Criteria andSsmjIn(List<String> values) {
            addCriterion("SSMJ in", values, "ssmj");
            return (Criteria) this;
        }

        public Criteria andSsmjNotIn(List<String> values) {
            addCriterion("SSMJ not in", values, "ssmj");
            return (Criteria) this;
        }

        public Criteria andSsmjBetween(String value1, String value2) {
            addCriterion("SSMJ between", value1, value2, "ssmj");
            return (Criteria) this;
        }

        public Criteria andSsmjNotBetween(String value1, String value2) {
            addCriterion("SSMJ not between", value1, value2, "ssmj");
            return (Criteria) this;
        }

        public Criteria andBhsyyIsNull() {
            addCriterion("BHSYY is null");
            return (Criteria) this;
        }

        public Criteria andBhsyyIsNotNull() {
            addCriterion("BHSYY is not null");
            return (Criteria) this;
        }

        public Criteria andBhsyyEqualTo(String value) {
            addCriterion("BHSYY =", value, "bhsyy");
            return (Criteria) this;
        }

        public Criteria andBhsyyNotEqualTo(String value) {
            addCriterion("BHSYY <>", value, "bhsyy");
            return (Criteria) this;
        }

        public Criteria andBhsyyGreaterThan(String value) {
            addCriterion("BHSYY >", value, "bhsyy");
            return (Criteria) this;
        }

        public Criteria andBhsyyGreaterThanOrEqualTo(String value) {
            addCriterion("BHSYY >=", value, "bhsyy");
            return (Criteria) this;
        }

        public Criteria andBhsyyLessThan(String value) {
            addCriterion("BHSYY <", value, "bhsyy");
            return (Criteria) this;
        }

        public Criteria andBhsyyLessThanOrEqualTo(String value) {
            addCriterion("BHSYY <=", value, "bhsyy");
            return (Criteria) this;
        }

        public Criteria andBhsyyLike(String value) {
            addCriterion("BHSYY like", value, "bhsyy");
            return (Criteria) this;
        }

        public Criteria andBhsyyNotLike(String value) {
            addCriterion("BHSYY not like", value, "bhsyy");
            return (Criteria) this;
        }

        public Criteria andBhsyyIn(List<String> values) {
            addCriterion("BHSYY in", values, "bhsyy");
            return (Criteria) this;
        }

        public Criteria andBhsyyNotIn(List<String> values) {
            addCriterion("BHSYY not in", values, "bhsyy");
            return (Criteria) this;
        }

        public Criteria andBhsyyBetween(String value1, String value2) {
            addCriterion("BHSYY between", value1, value2, "bhsyy");
            return (Criteria) this;
        }

        public Criteria andBhsyyNotBetween(String value1, String value2) {
            addCriterion("BHSYY not between", value1, value2, "bhsyy");
            return (Criteria) this;
        }

        public Criteria andWclyyIsNull() {
            addCriterion("WCLYY is null");
            return (Criteria) this;
        }

        public Criteria andWclyyIsNotNull() {
            addCriterion("WCLYY is not null");
            return (Criteria) this;
        }

        public Criteria andWclyyEqualTo(String value) {
            addCriterion("WCLYY =", value, "wclyy");
            return (Criteria) this;
        }

        public Criteria andWclyyNotEqualTo(String value) {
            addCriterion("WCLYY <>", value, "wclyy");
            return (Criteria) this;
        }

        public Criteria andWclyyGreaterThan(String value) {
            addCriterion("WCLYY >", value, "wclyy");
            return (Criteria) this;
        }

        public Criteria andWclyyGreaterThanOrEqualTo(String value) {
            addCriterion("WCLYY >=", value, "wclyy");
            return (Criteria) this;
        }

        public Criteria andWclyyLessThan(String value) {
            addCriterion("WCLYY <", value, "wclyy");
            return (Criteria) this;
        }

        public Criteria andWclyyLessThanOrEqualTo(String value) {
            addCriterion("WCLYY <=", value, "wclyy");
            return (Criteria) this;
        }

        public Criteria andWclyyLike(String value) {
            addCriterion("WCLYY like", value, "wclyy");
            return (Criteria) this;
        }

        public Criteria andWclyyNotLike(String value) {
            addCriterion("WCLYY not like", value, "wclyy");
            return (Criteria) this;
        }

        public Criteria andWclyyIn(List<String> values) {
            addCriterion("WCLYY in", values, "wclyy");
            return (Criteria) this;
        }

        public Criteria andWclyyNotIn(List<String> values) {
            addCriterion("WCLYY not in", values, "wclyy");
            return (Criteria) this;
        }

        public Criteria andWclyyBetween(String value1, String value2) {
            addCriterion("WCLYY between", value1, value2, "wclyy");
            return (Criteria) this;
        }

        public Criteria andWclyyNotBetween(String value1, String value2) {
            addCriterion("WCLYY not between", value1, value2, "wclyy");
            return (Criteria) this;
        }

        public Criteria andDbzyyIsNull() {
            addCriterion("DBZYY is null");
            return (Criteria) this;
        }

        public Criteria andDbzyyIsNotNull() {
            addCriterion("DBZYY is not null");
            return (Criteria) this;
        }

        public Criteria andDbzyyEqualTo(String value) {
            addCriterion("DBZYY =", value, "dbzyy");
            return (Criteria) this;
        }

        public Criteria andDbzyyNotEqualTo(String value) {
            addCriterion("DBZYY <>", value, "dbzyy");
            return (Criteria) this;
        }

        public Criteria andDbzyyGreaterThan(String value) {
            addCriterion("DBZYY >", value, "dbzyy");
            return (Criteria) this;
        }

        public Criteria andDbzyyGreaterThanOrEqualTo(String value) {
            addCriterion("DBZYY >=", value, "dbzyy");
            return (Criteria) this;
        }

        public Criteria andDbzyyLessThan(String value) {
            addCriterion("DBZYY <", value, "dbzyy");
            return (Criteria) this;
        }

        public Criteria andDbzyyLessThanOrEqualTo(String value) {
            addCriterion("DBZYY <=", value, "dbzyy");
            return (Criteria) this;
        }

        public Criteria andDbzyyLike(String value) {
            addCriterion("DBZYY like", value, "dbzyy");
            return (Criteria) this;
        }

        public Criteria andDbzyyNotLike(String value) {
            addCriterion("DBZYY not like", value, "dbzyy");
            return (Criteria) this;
        }

        public Criteria andDbzyyIn(List<String> values) {
            addCriterion("DBZYY in", values, "dbzyy");
            return (Criteria) this;
        }

        public Criteria andDbzyyNotIn(List<String> values) {
            addCriterion("DBZYY not in", values, "dbzyy");
            return (Criteria) this;
        }

        public Criteria andDbzyyBetween(String value1, String value2) {
            addCriterion("DBZYY between", value1, value2, "dbzyy");
            return (Criteria) this;
        }

        public Criteria andDbzyyNotBetween(String value1, String value2) {
            addCriterion("DBZYY not between", value1, value2, "dbzyy");
            return (Criteria) this;
        }

        public Criteria andBhgyyIsNull() {
            addCriterion("BHGYY is null");
            return (Criteria) this;
        }

        public Criteria andBhgyyIsNotNull() {
            addCriterion("BHGYY is not null");
            return (Criteria) this;
        }

        public Criteria andBhgyyEqualTo(String value) {
            addCriterion("BHGYY =", value, "bhgyy");
            return (Criteria) this;
        }

        public Criteria andBhgyyNotEqualTo(String value) {
            addCriterion("BHGYY <>", value, "bhgyy");
            return (Criteria) this;
        }

        public Criteria andBhgyyGreaterThan(String value) {
            addCriterion("BHGYY >", value, "bhgyy");
            return (Criteria) this;
        }

        public Criteria andBhgyyGreaterThanOrEqualTo(String value) {
            addCriterion("BHGYY >=", value, "bhgyy");
            return (Criteria) this;
        }

        public Criteria andBhgyyLessThan(String value) {
            addCriterion("BHGYY <", value, "bhgyy");
            return (Criteria) this;
        }

        public Criteria andBhgyyLessThanOrEqualTo(String value) {
            addCriterion("BHGYY <=", value, "bhgyy");
            return (Criteria) this;
        }

        public Criteria andBhgyyLike(String value) {
            addCriterion("BHGYY like", value, "bhgyy");
            return (Criteria) this;
        }

        public Criteria andBhgyyNotLike(String value) {
            addCriterion("BHGYY not like", value, "bhgyy");
            return (Criteria) this;
        }

        public Criteria andBhgyyIn(List<String> values) {
            addCriterion("BHGYY in", values, "bhgyy");
            return (Criteria) this;
        }

        public Criteria andBhgyyNotIn(List<String> values) {
            addCriterion("BHGYY not in", values, "bhgyy");
            return (Criteria) this;
        }

        public Criteria andBhgyyBetween(String value1, String value2) {
            addCriterion("BHGYY between", value1, value2, "bhgyy");
            return (Criteria) this;
        }

        public Criteria andBhgyyNotBetween(String value1, String value2) {
            addCriterion("BHGYY not between", value1, value2, "bhgyy");
            return (Criteria) this;
        }

        public Criteria andSsyyIsNull() {
            addCriterion("SSYY is null");
            return (Criteria) this;
        }

        public Criteria andSsyyIsNotNull() {
            addCriterion("SSYY is not null");
            return (Criteria) this;
        }

        public Criteria andSsyyEqualTo(String value) {
            addCriterion("SSYY =", value, "ssyy");
            return (Criteria) this;
        }

        public Criteria andSsyyNotEqualTo(String value) {
            addCriterion("SSYY <>", value, "ssyy");
            return (Criteria) this;
        }

        public Criteria andSsyyGreaterThan(String value) {
            addCriterion("SSYY >", value, "ssyy");
            return (Criteria) this;
        }

        public Criteria andSsyyGreaterThanOrEqualTo(String value) {
            addCriterion("SSYY >=", value, "ssyy");
            return (Criteria) this;
        }

        public Criteria andSsyyLessThan(String value) {
            addCriterion("SSYY <", value, "ssyy");
            return (Criteria) this;
        }

        public Criteria andSsyyLessThanOrEqualTo(String value) {
            addCriterion("SSYY <=", value, "ssyy");
            return (Criteria) this;
        }

        public Criteria andSsyyLike(String value) {
            addCriterion("SSYY like", value, "ssyy");
            return (Criteria) this;
        }

        public Criteria andSsyyNotLike(String value) {
            addCriterion("SSYY not like", value, "ssyy");
            return (Criteria) this;
        }

        public Criteria andSsyyIn(List<String> values) {
            addCriterion("SSYY in", values, "ssyy");
            return (Criteria) this;
        }

        public Criteria andSsyyNotIn(List<String> values) {
            addCriterion("SSYY not in", values, "ssyy");
            return (Criteria) this;
        }

        public Criteria andSsyyBetween(String value1, String value2) {
            addCriterion("SSYY between", value1, value2, "ssyy");
            return (Criteria) this;
        }

        public Criteria andSsyyNotBetween(String value1, String value2) {
            addCriterion("SSYY not between", value1, value2, "ssyy");
            return (Criteria) this;
        }

        public Criteria andSfsjIsNull() {
            addCriterion("SFSJ is null");
            return (Criteria) this;
        }

        public Criteria andSfsjIsNotNull() {
            addCriterion("SFSJ is not null");
            return (Criteria) this;
        }

        public Criteria andSfsjEqualTo(String value) {
            addCriterion("SFSJ =", value, "sfsj");
            return (Criteria) this;
        }

        public Criteria andSfsjNotEqualTo(String value) {
            addCriterion("SFSJ <>", value, "sfsj");
            return (Criteria) this;
        }

        public Criteria andSfsjGreaterThan(String value) {
            addCriterion("SFSJ >", value, "sfsj");
            return (Criteria) this;
        }

        public Criteria andSfsjGreaterThanOrEqualTo(String value) {
            addCriterion("SFSJ >=", value, "sfsj");
            return (Criteria) this;
        }

        public Criteria andSfsjLessThan(String value) {
            addCriterion("SFSJ <", value, "sfsj");
            return (Criteria) this;
        }

        public Criteria andSfsjLessThanOrEqualTo(String value) {
            addCriterion("SFSJ <=", value, "sfsj");
            return (Criteria) this;
        }

        public Criteria andSfsjLike(String value) {
            addCriterion("SFSJ like", value, "sfsj");
            return (Criteria) this;
        }

        public Criteria andSfsjNotLike(String value) {
            addCriterion("SFSJ not like", value, "sfsj");
            return (Criteria) this;
        }

        public Criteria andSfsjIn(List<String> values) {
            addCriterion("SFSJ in", values, "sfsj");
            return (Criteria) this;
        }

        public Criteria andSfsjNotIn(List<String> values) {
            addCriterion("SFSJ not in", values, "sfsj");
            return (Criteria) this;
        }

        public Criteria andSfsjBetween(String value1, String value2) {
            addCriterion("SFSJ between", value1, value2, "sfsj");
            return (Criteria) this;
        }

        public Criteria andSfsjNotBetween(String value1, String value2) {
            addCriterion("SFSJ not between", value1, value2, "sfsj");
            return (Criteria) this;
        }

        public Criteria andSfspIsNull() {
            addCriterion("SFSP is null");
            return (Criteria) this;
        }

        public Criteria andSfspIsNotNull() {
            addCriterion("SFSP is not null");
            return (Criteria) this;
        }

        public Criteria andSfspEqualTo(String value) {
            addCriterion("SFSP =", value, "sfsp");
            return (Criteria) this;
        }

        public Criteria andSfspNotEqualTo(String value) {
            addCriterion("SFSP <>", value, "sfsp");
            return (Criteria) this;
        }

        public Criteria andSfspGreaterThan(String value) {
            addCriterion("SFSP >", value, "sfsp");
            return (Criteria) this;
        }

        public Criteria andSfspGreaterThanOrEqualTo(String value) {
            addCriterion("SFSP >=", value, "sfsp");
            return (Criteria) this;
        }

        public Criteria andSfspLessThan(String value) {
            addCriterion("SFSP <", value, "sfsp");
            return (Criteria) this;
        }

        public Criteria andSfspLessThanOrEqualTo(String value) {
            addCriterion("SFSP <=", value, "sfsp");
            return (Criteria) this;
        }

        public Criteria andSfspLike(String value) {
            addCriterion("SFSP like", value, "sfsp");
            return (Criteria) this;
        }

        public Criteria andSfspNotLike(String value) {
            addCriterion("SFSP not like", value, "sfsp");
            return (Criteria) this;
        }

        public Criteria andSfspIn(List<String> values) {
            addCriterion("SFSP in", values, "sfsp");
            return (Criteria) this;
        }

        public Criteria andSfspNotIn(List<String> values) {
            addCriterion("SFSP not in", values, "sfsp");
            return (Criteria) this;
        }

        public Criteria andSfspBetween(String value1, String value2) {
            addCriterion("SFSP between", value1, value2, "sfsp");
            return (Criteria) this;
        }

        public Criteria andSfspNotBetween(String value1, String value2) {
            addCriterion("SFSP not between", value1, value2, "sfsp");
            return (Criteria) this;
        }

        public Criteria andSjdwzzIsNull() {
            addCriterion("SJDWZZ is null");
            return (Criteria) this;
        }

        public Criteria andSjdwzzIsNotNull() {
            addCriterion("SJDWZZ is not null");
            return (Criteria) this;
        }

        public Criteria andSjdwzzEqualTo(String value) {
            addCriterion("SJDWZZ =", value, "sjdwzz");
            return (Criteria) this;
        }

        public Criteria andSjdwzzNotEqualTo(String value) {
            addCriterion("SJDWZZ <>", value, "sjdwzz");
            return (Criteria) this;
        }

        public Criteria andSjdwzzGreaterThan(String value) {
            addCriterion("SJDWZZ >", value, "sjdwzz");
            return (Criteria) this;
        }

        public Criteria andSjdwzzGreaterThanOrEqualTo(String value) {
            addCriterion("SJDWZZ >=", value, "sjdwzz");
            return (Criteria) this;
        }

        public Criteria andSjdwzzLessThan(String value) {
            addCriterion("SJDWZZ <", value, "sjdwzz");
            return (Criteria) this;
        }

        public Criteria andSjdwzzLessThanOrEqualTo(String value) {
            addCriterion("SJDWZZ <=", value, "sjdwzz");
            return (Criteria) this;
        }

        public Criteria andSjdwzzLike(String value) {
            addCriterion("SJDWZZ like", value, "sjdwzz");
            return (Criteria) this;
        }

        public Criteria andSjdwzzNotLike(String value) {
            addCriterion("SJDWZZ not like", value, "sjdwzz");
            return (Criteria) this;
        }

        public Criteria andSjdwzzIn(List<String> values) {
            addCriterion("SJDWZZ in", values, "sjdwzz");
            return (Criteria) this;
        }

        public Criteria andSjdwzzNotIn(List<String> values) {
            addCriterion("SJDWZZ not in", values, "sjdwzz");
            return (Criteria) this;
        }

        public Criteria andSjdwzzBetween(String value1, String value2) {
            addCriterion("SJDWZZ between", value1, value2, "sjdwzz");
            return (Criteria) this;
        }

        public Criteria andSjdwzzNotBetween(String value1, String value2) {
            addCriterion("SJDWZZ not between", value1, value2, "sjdwzz");
            return (Criteria) this;
        }

        public Criteria andSfasjssIsNull() {
            addCriterion("SFASJSS is null");
            return (Criteria) this;
        }

        public Criteria andSfasjssIsNotNull() {
            addCriterion("SFASJSS is not null");
            return (Criteria) this;
        }

        public Criteria andSfasjssEqualTo(String value) {
            addCriterion("SFASJSS =", value, "sfasjss");
            return (Criteria) this;
        }

        public Criteria andSfasjssNotEqualTo(String value) {
            addCriterion("SFASJSS <>", value, "sfasjss");
            return (Criteria) this;
        }

        public Criteria andSfasjssGreaterThan(String value) {
            addCriterion("SFASJSS >", value, "sfasjss");
            return (Criteria) this;
        }

        public Criteria andSfasjssGreaterThanOrEqualTo(String value) {
            addCriterion("SFASJSS >=", value, "sfasjss");
            return (Criteria) this;
        }

        public Criteria andSfasjssLessThan(String value) {
            addCriterion("SFASJSS <", value, "sfasjss");
            return (Criteria) this;
        }

        public Criteria andSfasjssLessThanOrEqualTo(String value) {
            addCriterion("SFASJSS <=", value, "sfasjss");
            return (Criteria) this;
        }

        public Criteria andSfasjssLike(String value) {
            addCriterion("SFASJSS like", value, "sfasjss");
            return (Criteria) this;
        }

        public Criteria andSfasjssNotLike(String value) {
            addCriterion("SFASJSS not like", value, "sfasjss");
            return (Criteria) this;
        }

        public Criteria andSfasjssIn(List<String> values) {
            addCriterion("SFASJSS in", values, "sfasjss");
            return (Criteria) this;
        }

        public Criteria andSfasjssNotIn(List<String> values) {
            addCriterion("SFASJSS not in", values, "sfasjss");
            return (Criteria) this;
        }

        public Criteria andSfasjssBetween(String value1, String value2) {
            addCriterion("SFASJSS between", value1, value2, "sfasjss");
            return (Criteria) this;
        }

        public Criteria andSfasjssNotBetween(String value1, String value2) {
            addCriterion("SFASJSS not between", value1, value2, "sfasjss");
            return (Criteria) this;
        }

        public Criteria andSfydaIsNull() {
            addCriterion("SFYDA is null");
            return (Criteria) this;
        }

        public Criteria andSfydaIsNotNull() {
            addCriterion("SFYDA is not null");
            return (Criteria) this;
        }

        public Criteria andSfydaEqualTo(String value) {
            addCriterion("SFYDA =", value, "sfyda");
            return (Criteria) this;
        }

        public Criteria andSfydaNotEqualTo(String value) {
            addCriterion("SFYDA <>", value, "sfyda");
            return (Criteria) this;
        }

        public Criteria andSfydaGreaterThan(String value) {
            addCriterion("SFYDA >", value, "sfyda");
            return (Criteria) this;
        }

        public Criteria andSfydaGreaterThanOrEqualTo(String value) {
            addCriterion("SFYDA >=", value, "sfyda");
            return (Criteria) this;
        }

        public Criteria andSfydaLessThan(String value) {
            addCriterion("SFYDA <", value, "sfyda");
            return (Criteria) this;
        }

        public Criteria andSfydaLessThanOrEqualTo(String value) {
            addCriterion("SFYDA <=", value, "sfyda");
            return (Criteria) this;
        }

        public Criteria andSfydaLike(String value) {
            addCriterion("SFYDA like", value, "sfyda");
            return (Criteria) this;
        }

        public Criteria andSfydaNotLike(String value) {
            addCriterion("SFYDA not like", value, "sfyda");
            return (Criteria) this;
        }

        public Criteria andSfydaIn(List<String> values) {
            addCriterion("SFYDA in", values, "sfyda");
            return (Criteria) this;
        }

        public Criteria andSfydaNotIn(List<String> values) {
            addCriterion("SFYDA not in", values, "sfyda");
            return (Criteria) this;
        }

        public Criteria andSfydaBetween(String value1, String value2) {
            addCriterion("SFYDA between", value1, value2, "sfyda");
            return (Criteria) this;
        }

        public Criteria andSfydaNotBetween(String value1, String value2) {
            addCriterion("SFYDA not between", value1, value2, "sfyda");
            return (Criteria) this;
        }

        public Criteria andSffyIsNull() {
            addCriterion("SFFY is null");
            return (Criteria) this;
        }

        public Criteria andSffyIsNotNull() {
            addCriterion("SFFY is not null");
            return (Criteria) this;
        }

        public Criteria andSffyEqualTo(String value) {
            addCriterion("SFFY =", value, "sffy");
            return (Criteria) this;
        }

        public Criteria andSffyNotEqualTo(String value) {
            addCriterion("SFFY <>", value, "sffy");
            return (Criteria) this;
        }

        public Criteria andSffyGreaterThan(String value) {
            addCriterion("SFFY >", value, "sffy");
            return (Criteria) this;
        }

        public Criteria andSffyGreaterThanOrEqualTo(String value) {
            addCriterion("SFFY >=", value, "sffy");
            return (Criteria) this;
        }

        public Criteria andSffyLessThan(String value) {
            addCriterion("SFFY <", value, "sffy");
            return (Criteria) this;
        }

        public Criteria andSffyLessThanOrEqualTo(String value) {
            addCriterion("SFFY <=", value, "sffy");
            return (Criteria) this;
        }

        public Criteria andSffyLike(String value) {
            addCriterion("SFFY like", value, "sffy");
            return (Criteria) this;
        }

        public Criteria andSffyNotLike(String value) {
            addCriterion("SFFY not like", value, "sffy");
            return (Criteria) this;
        }

        public Criteria andSffyIn(List<String> values) {
            addCriterion("SFFY in", values, "sffy");
            return (Criteria) this;
        }

        public Criteria andSffyNotIn(List<String> values) {
            addCriterion("SFFY not in", values, "sffy");
            return (Criteria) this;
        }

        public Criteria andSffyBetween(String value1, String value2) {
            addCriterion("SFFY between", value1, value2, "sffy");
            return (Criteria) this;
        }

        public Criteria andSffyNotBetween(String value1, String value2) {
            addCriterion("SFFY not between", value1, value2, "sffy");
            return (Criteria) this;
        }

        public Criteria andSfghIsNull() {
            addCriterion("SFGH is null");
            return (Criteria) this;
        }

        public Criteria andSfghIsNotNull() {
            addCriterion("SFGH is not null");
            return (Criteria) this;
        }

        public Criteria andSfghEqualTo(String value) {
            addCriterion("SFGH =", value, "sfgh");
            return (Criteria) this;
        }

        public Criteria andSfghNotEqualTo(String value) {
            addCriterion("SFGH <>", value, "sfgh");
            return (Criteria) this;
        }

        public Criteria andSfghGreaterThan(String value) {
            addCriterion("SFGH >", value, "sfgh");
            return (Criteria) this;
        }

        public Criteria andSfghGreaterThanOrEqualTo(String value) {
            addCriterion("SFGH >=", value, "sfgh");
            return (Criteria) this;
        }

        public Criteria andSfghLessThan(String value) {
            addCriterion("SFGH <", value, "sfgh");
            return (Criteria) this;
        }

        public Criteria andSfghLessThanOrEqualTo(String value) {
            addCriterion("SFGH <=", value, "sfgh");
            return (Criteria) this;
        }

        public Criteria andSfghLike(String value) {
            addCriterion("SFGH like", value, "sfgh");
            return (Criteria) this;
        }

        public Criteria andSfghNotLike(String value) {
            addCriterion("SFGH not like", value, "sfgh");
            return (Criteria) this;
        }

        public Criteria andSfghIn(List<String> values) {
            addCriterion("SFGH in", values, "sfgh");
            return (Criteria) this;
        }

        public Criteria andSfghNotIn(List<String> values) {
            addCriterion("SFGH not in", values, "sfgh");
            return (Criteria) this;
        }

        public Criteria andSfghBetween(String value1, String value2) {
            addCriterion("SFGH between", value1, value2, "sfgh");
            return (Criteria) this;
        }

        public Criteria andSfghNotBetween(String value1, String value2) {
            addCriterion("SFGH not between", value1, value2, "sfgh");
            return (Criteria) this;
        }

        public Criteria andSfzjIsNull() {
            addCriterion("SFZJ is null");
            return (Criteria) this;
        }

        public Criteria andSfzjIsNotNull() {
            addCriterion("SFZJ is not null");
            return (Criteria) this;
        }

        public Criteria andSfzjEqualTo(String value) {
            addCriterion("SFZJ =", value, "sfzj");
            return (Criteria) this;
        }

        public Criteria andSfzjNotEqualTo(String value) {
            addCriterion("SFZJ <>", value, "sfzj");
            return (Criteria) this;
        }

        public Criteria andSfzjGreaterThan(String value) {
            addCriterion("SFZJ >", value, "sfzj");
            return (Criteria) this;
        }

        public Criteria andSfzjGreaterThanOrEqualTo(String value) {
            addCriterion("SFZJ >=", value, "sfzj");
            return (Criteria) this;
        }

        public Criteria andSfzjLessThan(String value) {
            addCriterion("SFZJ <", value, "sfzj");
            return (Criteria) this;
        }

        public Criteria andSfzjLessThanOrEqualTo(String value) {
            addCriterion("SFZJ <=", value, "sfzj");
            return (Criteria) this;
        }

        public Criteria andSfzjLike(String value) {
            addCriterion("SFZJ like", value, "sfzj");
            return (Criteria) this;
        }

        public Criteria andSfzjNotLike(String value) {
            addCriterion("SFZJ not like", value, "sfzj");
            return (Criteria) this;
        }

        public Criteria andSfzjIn(List<String> values) {
            addCriterion("SFZJ in", values, "sfzj");
            return (Criteria) this;
        }

        public Criteria andSfzjNotIn(List<String> values) {
            addCriterion("SFZJ not in", values, "sfzj");
            return (Criteria) this;
        }

        public Criteria andSfzjBetween(String value1, String value2) {
            addCriterion("SFZJ between", value1, value2, "sfzj");
            return (Criteria) this;
        }

        public Criteria andSfzjNotBetween(String value1, String value2) {
            addCriterion("SFZJ not between", value1, value2, "sfzj");
            return (Criteria) this;
        }

        public Criteria andSfyhtIsNull() {
            addCriterion("SFYHT is null");
            return (Criteria) this;
        }

        public Criteria andSfyhtIsNotNull() {
            addCriterion("SFYHT is not null");
            return (Criteria) this;
        }

        public Criteria andSfyhtEqualTo(String value) {
            addCriterion("SFYHT =", value, "sfyht");
            return (Criteria) this;
        }

        public Criteria andSfyhtNotEqualTo(String value) {
            addCriterion("SFYHT <>", value, "sfyht");
            return (Criteria) this;
        }

        public Criteria andSfyhtGreaterThan(String value) {
            addCriterion("SFYHT >", value, "sfyht");
            return (Criteria) this;
        }

        public Criteria andSfyhtGreaterThanOrEqualTo(String value) {
            addCriterion("SFYHT >=", value, "sfyht");
            return (Criteria) this;
        }

        public Criteria andSfyhtLessThan(String value) {
            addCriterion("SFYHT <", value, "sfyht");
            return (Criteria) this;
        }

        public Criteria andSfyhtLessThanOrEqualTo(String value) {
            addCriterion("SFYHT <=", value, "sfyht");
            return (Criteria) this;
        }

        public Criteria andSfyhtLike(String value) {
            addCriterion("SFYHT like", value, "sfyht");
            return (Criteria) this;
        }

        public Criteria andSfyhtNotLike(String value) {
            addCriterion("SFYHT not like", value, "sfyht");
            return (Criteria) this;
        }

        public Criteria andSfyhtIn(List<String> values) {
            addCriterion("SFYHT in", values, "sfyht");
            return (Criteria) this;
        }

        public Criteria andSfyhtNotIn(List<String> values) {
            addCriterion("SFYHT not in", values, "sfyht");
            return (Criteria) this;
        }

        public Criteria andSfyhtBetween(String value1, String value2) {
            addCriterion("SFYHT between", value1, value2, "sfyht");
            return (Criteria) this;
        }

        public Criteria andSfyhtNotBetween(String value1, String value2) {
            addCriterion("SFYHT not between", value1, value2, "sfyht");
            return (Criteria) this;
        }

        public Criteria andSfylqzIsNull() {
            addCriterion("SFYLQZ is null");
            return (Criteria) this;
        }

        public Criteria andSfylqzIsNotNull() {
            addCriterion("SFYLQZ is not null");
            return (Criteria) this;
        }

        public Criteria andSfylqzEqualTo(String value) {
            addCriterion("SFYLQZ =", value, "sfylqz");
            return (Criteria) this;
        }

        public Criteria andSfylqzNotEqualTo(String value) {
            addCriterion("SFYLQZ <>", value, "sfylqz");
            return (Criteria) this;
        }

        public Criteria andSfylqzGreaterThan(String value) {
            addCriterion("SFYLQZ >", value, "sfylqz");
            return (Criteria) this;
        }

        public Criteria andSfylqzGreaterThanOrEqualTo(String value) {
            addCriterion("SFYLQZ >=", value, "sfylqz");
            return (Criteria) this;
        }

        public Criteria andSfylqzLessThan(String value) {
            addCriterion("SFYLQZ <", value, "sfylqz");
            return (Criteria) this;
        }

        public Criteria andSfylqzLessThanOrEqualTo(String value) {
            addCriterion("SFYLQZ <=", value, "sfylqz");
            return (Criteria) this;
        }

        public Criteria andSfylqzLike(String value) {
            addCriterion("SFYLQZ like", value, "sfylqz");
            return (Criteria) this;
        }

        public Criteria andSfylqzNotLike(String value) {
            addCriterion("SFYLQZ not like", value, "sfylqz");
            return (Criteria) this;
        }

        public Criteria andSfylqzIn(List<String> values) {
            addCriterion("SFYLQZ in", values, "sfylqz");
            return (Criteria) this;
        }

        public Criteria andSfylqzNotIn(List<String> values) {
            addCriterion("SFYLQZ not in", values, "sfylqz");
            return (Criteria) this;
        }

        public Criteria andSfylqzBetween(String value1, String value2) {
            addCriterion("SFYLQZ between", value1, value2, "sfylqz");
            return (Criteria) this;
        }

        public Criteria andSfylqzNotBetween(String value1, String value2) {
            addCriterion("SFYLQZ not between", value1, value2, "sfylqz");
            return (Criteria) this;
        }

        public Criteria andYlfsIsNull() {
            addCriterion("YLFS is null");
            return (Criteria) this;
        }

        public Criteria andYlfsIsNotNull() {
            addCriterion("YLFS is not null");
            return (Criteria) this;
        }

        public Criteria andYlfsEqualTo(String value) {
            addCriterion("YLFS =", value, "ylfs");
            return (Criteria) this;
        }

        public Criteria andYlfsNotEqualTo(String value) {
            addCriterion("YLFS <>", value, "ylfs");
            return (Criteria) this;
        }

        public Criteria andYlfsGreaterThan(String value) {
            addCriterion("YLFS >", value, "ylfs");
            return (Criteria) this;
        }

        public Criteria andYlfsGreaterThanOrEqualTo(String value) {
            addCriterion("YLFS >=", value, "ylfs");
            return (Criteria) this;
        }

        public Criteria andYlfsLessThan(String value) {
            addCriterion("YLFS <", value, "ylfs");
            return (Criteria) this;
        }

        public Criteria andYlfsLessThanOrEqualTo(String value) {
            addCriterion("YLFS <=", value, "ylfs");
            return (Criteria) this;
        }

        public Criteria andYlfsLike(String value) {
            addCriterion("YLFS like", value, "ylfs");
            return (Criteria) this;
        }

        public Criteria andYlfsNotLike(String value) {
            addCriterion("YLFS not like", value, "ylfs");
            return (Criteria) this;
        }

        public Criteria andYlfsIn(List<String> values) {
            addCriterion("YLFS in", values, "ylfs");
            return (Criteria) this;
        }

        public Criteria andYlfsNotIn(List<String> values) {
            addCriterion("YLFS not in", values, "ylfs");
            return (Criteria) this;
        }

        public Criteria andYlfsBetween(String value1, String value2) {
            addCriterion("YLFS between", value1, value2, "ylfs");
            return (Criteria) this;
        }

        public Criteria andYlfsNotBetween(String value1, String value2) {
            addCriterion("YLFS not between", value1, value2, "ylfs");
            return (Criteria) this;
        }

        public Criteria andSjylmjIsNull() {
            addCriterion("SJYLMJ is null");
            return (Criteria) this;
        }

        public Criteria andSjylmjIsNotNull() {
            addCriterion("SJYLMJ is not null");
            return (Criteria) this;
        }

        public Criteria andSjylmjEqualTo(String value) {
            addCriterion("SJYLMJ =", value, "sjylmj");
            return (Criteria) this;
        }

        public Criteria andSjylmjNotEqualTo(String value) {
            addCriterion("SJYLMJ <>", value, "sjylmj");
            return (Criteria) this;
        }

        public Criteria andSjylmjGreaterThan(String value) {
            addCriterion("SJYLMJ >", value, "sjylmj");
            return (Criteria) this;
        }

        public Criteria andSjylmjGreaterThanOrEqualTo(String value) {
            addCriterion("SJYLMJ >=", value, "sjylmj");
            return (Criteria) this;
        }

        public Criteria andSjylmjLessThan(String value) {
            addCriterion("SJYLMJ <", value, "sjylmj");
            return (Criteria) this;
        }

        public Criteria andSjylmjLessThanOrEqualTo(String value) {
            addCriterion("SJYLMJ <=", value, "sjylmj");
            return (Criteria) this;
        }

        public Criteria andSjylmjLike(String value) {
            addCriterion("SJYLMJ like", value, "sjylmj");
            return (Criteria) this;
        }

        public Criteria andSjylmjNotLike(String value) {
            addCriterion("SJYLMJ not like", value, "sjylmj");
            return (Criteria) this;
        }

        public Criteria andSjylmjIn(List<String> values) {
            addCriterion("SJYLMJ in", values, "sjylmj");
            return (Criteria) this;
        }

        public Criteria andSjylmjNotIn(List<String> values) {
            addCriterion("SJYLMJ not in", values, "sjylmj");
            return (Criteria) this;
        }

        public Criteria andSjylmjBetween(String value1, String value2) {
            addCriterion("SJYLMJ between", value1, value2, "sjylmj");
            return (Criteria) this;
        }

        public Criteria andSjylmjNotBetween(String value1, String value2) {
            addCriterion("SJYLMJ not between", value1, value2, "sjylmj");
            return (Criteria) this;
        }

        public Criteria andHsylmjIsNull() {
            addCriterion("HSYLMJ is null");
            return (Criteria) this;
        }

        public Criteria andHsylmjIsNotNull() {
            addCriterion("HSYLMJ is not null");
            return (Criteria) this;
        }

        public Criteria andHsylmjEqualTo(String value) {
            addCriterion("HSYLMJ =", value, "hsylmj");
            return (Criteria) this;
        }

        public Criteria andHsylmjNotEqualTo(String value) {
            addCriterion("HSYLMJ <>", value, "hsylmj");
            return (Criteria) this;
        }

        public Criteria andHsylmjGreaterThan(String value) {
            addCriterion("HSYLMJ >", value, "hsylmj");
            return (Criteria) this;
        }

        public Criteria andHsylmjGreaterThanOrEqualTo(String value) {
            addCriterion("HSYLMJ >=", value, "hsylmj");
            return (Criteria) this;
        }

        public Criteria andHsylmjLessThan(String value) {
            addCriterion("HSYLMJ <", value, "hsylmj");
            return (Criteria) this;
        }

        public Criteria andHsylmjLessThanOrEqualTo(String value) {
            addCriterion("HSYLMJ <=", value, "hsylmj");
            return (Criteria) this;
        }

        public Criteria andHsylmjLike(String value) {
            addCriterion("HSYLMJ like", value, "hsylmj");
            return (Criteria) this;
        }

        public Criteria andHsylmjNotLike(String value) {
            addCriterion("HSYLMJ not like", value, "hsylmj");
            return (Criteria) this;
        }

        public Criteria andHsylmjIn(List<String> values) {
            addCriterion("HSYLMJ in", values, "hsylmj");
            return (Criteria) this;
        }

        public Criteria andHsylmjNotIn(List<String> values) {
            addCriterion("HSYLMJ not in", values, "hsylmj");
            return (Criteria) this;
        }

        public Criteria andHsylmjBetween(String value1, String value2) {
            addCriterion("HSYLMJ between", value1, value2, "hsylmj");
            return (Criteria) this;
        }

        public Criteria andHsylmjNotBetween(String value1, String value2) {
            addCriterion("HSYLMJ not between", value1, value2, "hsylmj");
            return (Criteria) this;
        }

        public Criteria andZlztlxIsNull() {
            addCriterion("ZLZTLX is null");
            return (Criteria) this;
        }

        public Criteria andZlztlxIsNotNull() {
            addCriterion("ZLZTLX is not null");
            return (Criteria) this;
        }

        public Criteria andZlztlxEqualTo(String value) {
            addCriterion("ZLZTLX =", value, "zlztlx");
            return (Criteria) this;
        }

        public Criteria andZlztlxNotEqualTo(String value) {
            addCriterion("ZLZTLX <>", value, "zlztlx");
            return (Criteria) this;
        }

        public Criteria andZlztlxGreaterThan(String value) {
            addCriterion("ZLZTLX >", value, "zlztlx");
            return (Criteria) this;
        }

        public Criteria andZlztlxGreaterThanOrEqualTo(String value) {
            addCriterion("ZLZTLX >=", value, "zlztlx");
            return (Criteria) this;
        }

        public Criteria andZlztlxLessThan(String value) {
            addCriterion("ZLZTLX <", value, "zlztlx");
            return (Criteria) this;
        }

        public Criteria andZlztlxLessThanOrEqualTo(String value) {
            addCriterion("ZLZTLX <=", value, "zlztlx");
            return (Criteria) this;
        }

        public Criteria andZlztlxLike(String value) {
            addCriterion("ZLZTLX like", value, "zlztlx");
            return (Criteria) this;
        }

        public Criteria andZlztlxNotLike(String value) {
            addCriterion("ZLZTLX not like", value, "zlztlx");
            return (Criteria) this;
        }

        public Criteria andZlztlxIn(List<String> values) {
            addCriterion("ZLZTLX in", values, "zlztlx");
            return (Criteria) this;
        }

        public Criteria andZlztlxNotIn(List<String> values) {
            addCriterion("ZLZTLX not in", values, "zlztlx");
            return (Criteria) this;
        }

        public Criteria andZlztlxBetween(String value1, String value2) {
            addCriterion("ZLZTLX between", value1, value2, "zlztlx");
            return (Criteria) this;
        }

        public Criteria andZlztlxNotBetween(String value1, String value2) {
            addCriterion("ZLZTLX not between", value1, value2, "zlztlx");
            return (Criteria) this;
        }

        public Criteria andBz1IsNull() {
            addCriterion("BZ1 is null");
            return (Criteria) this;
        }

        public Criteria andBz1IsNotNull() {
            addCriterion("BZ1 is not null");
            return (Criteria) this;
        }

        public Criteria andBz1EqualTo(String value) {
            addCriterion("BZ1 =", value, "bz1");
            return (Criteria) this;
        }

        public Criteria andBz1NotEqualTo(String value) {
            addCriterion("BZ1 <>", value, "bz1");
            return (Criteria) this;
        }

        public Criteria andBz1GreaterThan(String value) {
            addCriterion("BZ1 >", value, "bz1");
            return (Criteria) this;
        }

        public Criteria andBz1GreaterThanOrEqualTo(String value) {
            addCriterion("BZ1 >=", value, "bz1");
            return (Criteria) this;
        }

        public Criteria andBz1LessThan(String value) {
            addCriterion("BZ1 <", value, "bz1");
            return (Criteria) this;
        }

        public Criteria andBz1LessThanOrEqualTo(String value) {
            addCriterion("BZ1 <=", value, "bz1");
            return (Criteria) this;
        }

        public Criteria andBz1Like(String value) {
            addCriterion("BZ1 like", value, "bz1");
            return (Criteria) this;
        }

        public Criteria andBz1NotLike(String value) {
            addCriterion("BZ1 not like", value, "bz1");
            return (Criteria) this;
        }

        public Criteria andBz1In(List<String> values) {
            addCriterion("BZ1 in", values, "bz1");
            return (Criteria) this;
        }

        public Criteria andBz1NotIn(List<String> values) {
            addCriterion("BZ1 not in", values, "bz1");
            return (Criteria) this;
        }

        public Criteria andBz1Between(String value1, String value2) {
            addCriterion("BZ1 between", value1, value2, "bz1");
            return (Criteria) this;
        }

        public Criteria andBz1NotBetween(String value1, String value2) {
            addCriterion("BZ1 not between", value1, value2, "bz1");
            return (Criteria) this;
        }

        public Criteria andBz2IsNull() {
            addCriterion("BZ2 is null");
            return (Criteria) this;
        }

        public Criteria andBz2IsNotNull() {
            addCriterion("BZ2 is not null");
            return (Criteria) this;
        }

        public Criteria andBz2EqualTo(String value) {
            addCriterion("BZ2 =", value, "bz2");
            return (Criteria) this;
        }

        public Criteria andBz2NotEqualTo(String value) {
            addCriterion("BZ2 <>", value, "bz2");
            return (Criteria) this;
        }

        public Criteria andBz2GreaterThan(String value) {
            addCriterion("BZ2 >", value, "bz2");
            return (Criteria) this;
        }

        public Criteria andBz2GreaterThanOrEqualTo(String value) {
            addCriterion("BZ2 >=", value, "bz2");
            return (Criteria) this;
        }

        public Criteria andBz2LessThan(String value) {
            addCriterion("BZ2 <", value, "bz2");
            return (Criteria) this;
        }

        public Criteria andBz2LessThanOrEqualTo(String value) {
            addCriterion("BZ2 <=", value, "bz2");
            return (Criteria) this;
        }

        public Criteria andBz2Like(String value) {
            addCriterion("BZ2 like", value, "bz2");
            return (Criteria) this;
        }

        public Criteria andBz2NotLike(String value) {
            addCriterion("BZ2 not like", value, "bz2");
            return (Criteria) this;
        }

        public Criteria andBz2In(List<String> values) {
            addCriterion("BZ2 in", values, "bz2");
            return (Criteria) this;
        }

        public Criteria andBz2NotIn(List<String> values) {
            addCriterion("BZ2 not in", values, "bz2");
            return (Criteria) this;
        }

        public Criteria andBz2Between(String value1, String value2) {
            addCriterion("BZ2 between", value1, value2, "bz2");
            return (Criteria) this;
        }

        public Criteria andBz2NotBetween(String value1, String value2) {
            addCriterion("BZ2 not between", value1, value2, "bz2");
            return (Criteria) this;
        }

        public Criteria andBz3IsNull() {
            addCriterion("BZ3 is null");
            return (Criteria) this;
        }

        public Criteria andBz3IsNotNull() {
            addCriterion("BZ3 is not null");
            return (Criteria) this;
        }

        public Criteria andBz3EqualTo(String value) {
            addCriterion("BZ3 =", value, "bz3");
            return (Criteria) this;
        }

        public Criteria andBz3NotEqualTo(String value) {
            addCriterion("BZ3 <>", value, "bz3");
            return (Criteria) this;
        }

        public Criteria andBz3GreaterThan(String value) {
            addCriterion("BZ3 >", value, "bz3");
            return (Criteria) this;
        }

        public Criteria andBz3GreaterThanOrEqualTo(String value) {
            addCriterion("BZ3 >=", value, "bz3");
            return (Criteria) this;
        }

        public Criteria andBz3LessThan(String value) {
            addCriterion("BZ3 <", value, "bz3");
            return (Criteria) this;
        }

        public Criteria andBz3LessThanOrEqualTo(String value) {
            addCriterion("BZ3 <=", value, "bz3");
            return (Criteria) this;
        }

        public Criteria andBz3Like(String value) {
            addCriterion("BZ3 like", value, "bz3");
            return (Criteria) this;
        }

        public Criteria andBz3NotLike(String value) {
            addCriterion("BZ3 not like", value, "bz3");
            return (Criteria) this;
        }

        public Criteria andBz3In(List<String> values) {
            addCriterion("BZ3 in", values, "bz3");
            return (Criteria) this;
        }

        public Criteria andBz3NotIn(List<String> values) {
            addCriterion("BZ3 not in", values, "bz3");
            return (Criteria) this;
        }

        public Criteria andBz3Between(String value1, String value2) {
            addCriterion("BZ3 between", value1, value2, "bz3");
            return (Criteria) this;
        }

        public Criteria andBz3NotBetween(String value1, String value2) {
            addCriterion("BZ3 not between", value1, value2, "bz3");
            return (Criteria) this;
        }

        public Criteria andHcryIsNull() {
            addCriterion("HCRY is null");
            return (Criteria) this;
        }

        public Criteria andHcryIsNotNull() {
            addCriterion("HCRY is not null");
            return (Criteria) this;
        }

        public Criteria andHcryEqualTo(String value) {
            addCriterion("HCRY =", value, "hcry");
            return (Criteria) this;
        }

        public Criteria andHcryNotEqualTo(String value) {
            addCriterion("HCRY <>", value, "hcry");
            return (Criteria) this;
        }

        public Criteria andHcryGreaterThan(String value) {
            addCriterion("HCRY >", value, "hcry");
            return (Criteria) this;
        }

        public Criteria andHcryGreaterThanOrEqualTo(String value) {
            addCriterion("HCRY >=", value, "hcry");
            return (Criteria) this;
        }

        public Criteria andHcryLessThan(String value) {
            addCriterion("HCRY <", value, "hcry");
            return (Criteria) this;
        }

        public Criteria andHcryLessThanOrEqualTo(String value) {
            addCriterion("HCRY <=", value, "hcry");
            return (Criteria) this;
        }

        public Criteria andHcryLike(String value) {
            addCriterion("HCRY like", value, "hcry");
            return (Criteria) this;
        }

        public Criteria andHcryNotLike(String value) {
            addCriterion("HCRY not like", value, "hcry");
            return (Criteria) this;
        }

        public Criteria andHcryIn(List<String> values) {
            addCriterion("HCRY in", values, "hcry");
            return (Criteria) this;
        }

        public Criteria andHcryNotIn(List<String> values) {
            addCriterion("HCRY not in", values, "hcry");
            return (Criteria) this;
        }

        public Criteria andHcryBetween(String value1, String value2) {
            addCriterion("HCRY between", value1, value2, "hcry");
            return (Criteria) this;
        }

        public Criteria andHcryNotBetween(String value1, String value2) {
            addCriterion("HCRY not between", value1, value2, "hcry");
            return (Criteria) this;
        }

        public Criteria andHcrqIsNull() {
            addCriterion("HCRQ is null");
            return (Criteria) this;
        }

        public Criteria andHcrqIsNotNull() {
            addCriterion("HCRQ is not null");
            return (Criteria) this;
        }

        public Criteria andHcrqEqualTo(String value) {
            addCriterion("HCRQ =", value, "hcrq");
            return (Criteria) this;
        }

        public Criteria andHcrqNotEqualTo(String value) {
            addCriterion("HCRQ <>", value, "hcrq");
            return (Criteria) this;
        }

        public Criteria andHcrqGreaterThan(String value) {
            addCriterion("HCRQ >", value, "hcrq");
            return (Criteria) this;
        }

        public Criteria andHcrqGreaterThanOrEqualTo(String value) {
            addCriterion("HCRQ >=", value, "hcrq");
            return (Criteria) this;
        }

        public Criteria andHcrqLessThan(String value) {
            addCriterion("HCRQ <", value, "hcrq");
            return (Criteria) this;
        }

        public Criteria andHcrqLessThanOrEqualTo(String value) {
            addCriterion("HCRQ <=", value, "hcrq");
            return (Criteria) this;
        }

        public Criteria andHcrqLike(String value) {
            addCriterion("HCRQ like", value, "hcrq");
            return (Criteria) this;
        }

        public Criteria andHcrqNotLike(String value) {
            addCriterion("HCRQ not like", value, "hcrq");
            return (Criteria) this;
        }

        public Criteria andHcrqIn(List<String> values) {
            addCriterion("HCRQ in", values, "hcrq");
            return (Criteria) this;
        }

        public Criteria andHcrqNotIn(List<String> values) {
            addCriterion("HCRQ not in", values, "hcrq");
            return (Criteria) this;
        }

        public Criteria andHcrqBetween(String value1, String value2) {
            addCriterion("HCRQ between", value1, value2, "hcrq");
            return (Criteria) this;
        }

        public Criteria andHcrqNotBetween(String value1, String value2) {
            addCriterion("HCRQ not between", value1, value2, "hcrq");
            return (Criteria) this;
        }

        public Criteria andPhotoIsNull() {
            addCriterion("PHOTO is null");
            return (Criteria) this;
        }

        public Criteria andPhotoIsNotNull() {
            addCriterion("PHOTO is not null");
            return (Criteria) this;
        }

        public Criteria andPhotoEqualTo(String value) {
            addCriterion("PHOTO =", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotEqualTo(String value) {
            addCriterion("PHOTO <>", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoGreaterThan(String value) {
            addCriterion("PHOTO >", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoGreaterThanOrEqualTo(String value) {
            addCriterion("PHOTO >=", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoLessThan(String value) {
            addCriterion("PHOTO <", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoLessThanOrEqualTo(String value) {
            addCriterion("PHOTO <=", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoLike(String value) {
            addCriterion("PHOTO like", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotLike(String value) {
            addCriterion("PHOTO not like", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoIn(List<String> values) {
            addCriterion("PHOTO in", values, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotIn(List<String> values) {
            addCriterion("PHOTO not in", values, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoBetween(String value1, String value2) {
            addCriterion("PHOTO between", value1, value2, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotBetween(String value1, String value2) {
            addCriterion("PHOTO not between", value1, value2, "photo");
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