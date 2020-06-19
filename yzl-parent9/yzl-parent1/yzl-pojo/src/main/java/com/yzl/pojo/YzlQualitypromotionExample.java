package com.yzl.pojo;

import java.util.ArrayList;
import java.util.List;

public class YzlQualitypromotionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public YzlQualitypromotionExample() {
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
     * @date 2019-03-29
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

        public Criteria andDcodeIsNull() {
            addCriterion("dcode is null");
            return (Criteria) this;
        }

        public Criteria andDcodeIsNotNull() {
            addCriterion("dcode is not null");
            return (Criteria) this;
        }

        public Criteria andDcodeEqualTo(String value) {
            addCriterion("dcode =", value, "dcode");
            return (Criteria) this;
        }

        public Criteria andDcodeNotEqualTo(String value) {
            addCriterion("dcode <>", value, "dcode");
            return (Criteria) this;
        }

        public Criteria andDcodeGreaterThan(String value) {
            addCriterion("dcode >", value, "dcode");
            return (Criteria) this;
        }

        public Criteria andDcodeGreaterThanOrEqualTo(String value) {
            addCriterion("dcode >=", value, "dcode");
            return (Criteria) this;
        }

        public Criteria andDcodeLessThan(String value) {
            addCriterion("dcode <", value, "dcode");
            return (Criteria) this;
        }

        public Criteria andDcodeLessThanOrEqualTo(String value) {
            addCriterion("dcode <=", value, "dcode");
            return (Criteria) this;
        }

        public Criteria andDcodeLike(String value) {
            addCriterion("dcode like", value, "dcode");
            return (Criteria) this;
        }

        public Criteria andDcodeNotLike(String value) {
            addCriterion("dcode not like", value, "dcode");
            return (Criteria) this;
        }

        public Criteria andDcodeIn(List<String> values) {
            addCriterion("dcode in", values, "dcode");
            return (Criteria) this;
        }

        public Criteria andDcodeNotIn(List<String> values) {
            addCriterion("dcode not in", values, "dcode");
            return (Criteria) this;
        }

        public Criteria andDcodeBetween(String value1, String value2) {
            addCriterion("dcode between", value1, value2, "dcode");
            return (Criteria) this;
        }

        public Criteria andDcodeNotBetween(String value1, String value2) {
            addCriterion("dcode not between", value1, value2, "dcode");
            return (Criteria) this;
        }

        public Criteria andEcodeIsNull() {
            addCriterion("ecode is null");
            return (Criteria) this;
        }

        public Criteria andEcodeIsNotNull() {
            addCriterion("ecode is not null");
            return (Criteria) this;
        }

        public Criteria andEcodeEqualTo(String value) {
            addCriterion("ecode =", value, "ecode");
            return (Criteria) this;
        }

        public Criteria andEcodeNotEqualTo(String value) {
            addCriterion("ecode <>", value, "ecode");
            return (Criteria) this;
        }

        public Criteria andEcodeGreaterThan(String value) {
            addCriterion("ecode >", value, "ecode");
            return (Criteria) this;
        }

        public Criteria andEcodeGreaterThanOrEqualTo(String value) {
            addCriterion("ecode >=", value, "ecode");
            return (Criteria) this;
        }

        public Criteria andEcodeLessThan(String value) {
            addCriterion("ecode <", value, "ecode");
            return (Criteria) this;
        }

        public Criteria andEcodeLessThanOrEqualTo(String value) {
            addCriterion("ecode <=", value, "ecode");
            return (Criteria) this;
        }

        public Criteria andEcodeLike(String value) {
            addCriterion("ecode like", value, "ecode");
            return (Criteria) this;
        }

        public Criteria andEcodeNotLike(String value) {
            addCriterion("ecode not like", value, "ecode");
            return (Criteria) this;
        }

        public Criteria andEcodeIn(List<String> values) {
            addCriterion("ecode in", values, "ecode");
            return (Criteria) this;
        }

        public Criteria andEcodeNotIn(List<String> values) {
            addCriterion("ecode not in", values, "ecode");
            return (Criteria) this;
        }

        public Criteria andEcodeBetween(String value1, String value2) {
            addCriterion("ecode between", value1, value2, "ecode");
            return (Criteria) this;
        }

        public Criteria andEcodeNotBetween(String value1, String value2) {
            addCriterion("ecode not between", value1, value2, "ecode");
            return (Criteria) this;
        }

        public Criteria andDesignareaIsNull() {
            addCriterion("designArea is null");
            return (Criteria) this;
        }

        public Criteria andDesignareaIsNotNull() {
            addCriterion("designArea is not null");
            return (Criteria) this;
        }

        public Criteria andDesignareaEqualTo(String value) {
            addCriterion("designArea =", value, "designarea");
            return (Criteria) this;
        }

        public Criteria andDesignareaNotEqualTo(String value) {
            addCriterion("designArea <>", value, "designarea");
            return (Criteria) this;
        }

        public Criteria andDesignareaGreaterThan(String value) {
            addCriterion("designArea >", value, "designarea");
            return (Criteria) this;
        }

        public Criteria andDesignareaGreaterThanOrEqualTo(String value) {
            addCriterion("designArea >=", value, "designarea");
            return (Criteria) this;
        }

        public Criteria andDesignareaLessThan(String value) {
            addCriterion("designArea <", value, "designarea");
            return (Criteria) this;
        }

        public Criteria andDesignareaLessThanOrEqualTo(String value) {
            addCriterion("designArea <=", value, "designarea");
            return (Criteria) this;
        }

        public Criteria andDesignareaLike(String value) {
            addCriterion("designArea like", value, "designarea");
            return (Criteria) this;
        }

        public Criteria andDesignareaNotLike(String value) {
            addCriterion("designArea not like", value, "designarea");
            return (Criteria) this;
        }

        public Criteria andDesignareaIn(List<String> values) {
            addCriterion("designArea in", values, "designarea");
            return (Criteria) this;
        }

        public Criteria andDesignareaNotIn(List<String> values) {
            addCriterion("designArea not in", values, "designarea");
            return (Criteria) this;
        }

        public Criteria andDesignareaBetween(String value1, String value2) {
            addCriterion("designArea between", value1, value2, "designarea");
            return (Criteria) this;
        }

        public Criteria andDesignareaNotBetween(String value1, String value2) {
            addCriterion("designArea not between", value1, value2, "designarea");
            return (Criteria) this;
        }

        public Criteria andIqrIsNull() {
            addCriterion("IQR is null");
            return (Criteria) this;
        }

        public Criteria andIqrIsNotNull() {
            addCriterion("IQR is not null");
            return (Criteria) this;
        }

        public Criteria andIqrEqualTo(String value) {
            addCriterion("IQR =", value, "iqr");
            return (Criteria) this;
        }

        public Criteria andIqrNotEqualTo(String value) {
            addCriterion("IQR <>", value, "iqr");
            return (Criteria) this;
        }

        public Criteria andIqrGreaterThan(String value) {
            addCriterion("IQR >", value, "iqr");
            return (Criteria) this;
        }

        public Criteria andIqrGreaterThanOrEqualTo(String value) {
            addCriterion("IQR >=", value, "iqr");
            return (Criteria) this;
        }

        public Criteria andIqrLessThan(String value) {
            addCriterion("IQR <", value, "iqr");
            return (Criteria) this;
        }

        public Criteria andIqrLessThanOrEqualTo(String value) {
            addCriterion("IQR <=", value, "iqr");
            return (Criteria) this;
        }

        public Criteria andIqrLike(String value) {
            addCriterion("IQR like", value, "iqr");
            return (Criteria) this;
        }

        public Criteria andIqrNotLike(String value) {
            addCriterion("IQR not like", value, "iqr");
            return (Criteria) this;
        }

        public Criteria andIqrIn(List<String> values) {
            addCriterion("IQR in", values, "iqr");
            return (Criteria) this;
        }

        public Criteria andIqrNotIn(List<String> values) {
            addCriterion("IQR not in", values, "iqr");
            return (Criteria) this;
        }

        public Criteria andIqrBetween(String value1, String value2) {
            addCriterion("IQR between", value1, value2, "iqr");
            return (Criteria) this;
        }

        public Criteria andIqrNotBetween(String value1, String value2) {
            addCriterion("IQR not between", value1, value2, "iqr");
            return (Criteria) this;
        }

        public Criteria andVerifyareatIsNull() {
            addCriterion("verifyAreaT is null");
            return (Criteria) this;
        }

        public Criteria andVerifyareatIsNotNull() {
            addCriterion("verifyAreaT is not null");
            return (Criteria) this;
        }

        public Criteria andVerifyareatEqualTo(String value) {
            addCriterion("verifyAreaT =", value, "verifyareat");
            return (Criteria) this;
        }

        public Criteria andVerifyareatNotEqualTo(String value) {
            addCriterion("verifyAreaT <>", value, "verifyareat");
            return (Criteria) this;
        }

        public Criteria andVerifyareatGreaterThan(String value) {
            addCriterion("verifyAreaT >", value, "verifyareat");
            return (Criteria) this;
        }

        public Criteria andVerifyareatGreaterThanOrEqualTo(String value) {
            addCriterion("verifyAreaT >=", value, "verifyareat");
            return (Criteria) this;
        }

        public Criteria andVerifyareatLessThan(String value) {
            addCriterion("verifyAreaT <", value, "verifyareat");
            return (Criteria) this;
        }

        public Criteria andVerifyareatLessThanOrEqualTo(String value) {
            addCriterion("verifyAreaT <=", value, "verifyareat");
            return (Criteria) this;
        }

        public Criteria andVerifyareatLike(String value) {
            addCriterion("verifyAreaT like", value, "verifyareat");
            return (Criteria) this;
        }

        public Criteria andVerifyareatNotLike(String value) {
            addCriterion("verifyAreaT not like", value, "verifyareat");
            return (Criteria) this;
        }

        public Criteria andVerifyareatIn(List<String> values) {
            addCriterion("verifyAreaT in", values, "verifyareat");
            return (Criteria) this;
        }

        public Criteria andVerifyareatNotIn(List<String> values) {
            addCriterion("verifyAreaT not in", values, "verifyareat");
            return (Criteria) this;
        }

        public Criteria andVerifyareatBetween(String value1, String value2) {
            addCriterion("verifyAreaT between", value1, value2, "verifyareat");
            return (Criteria) this;
        }

        public Criteria andVerifyareatNotBetween(String value1, String value2) {
            addCriterion("verifyAreaT not between", value1, value2, "verifyareat");
            return (Criteria) this;
        }

        public Criteria andVerifyareaIsNull() {
            addCriterion("verifyArea is null");
            return (Criteria) this;
        }

        public Criteria andVerifyareaIsNotNull() {
            addCriterion("verifyArea is not null");
            return (Criteria) this;
        }

        public Criteria andVerifyareaEqualTo(String value) {
            addCriterion("verifyArea =", value, "verifyarea");
            return (Criteria) this;
        }

        public Criteria andVerifyareaNotEqualTo(String value) {
            addCriterion("verifyArea <>", value, "verifyarea");
            return (Criteria) this;
        }

        public Criteria andVerifyareaGreaterThan(String value) {
            addCriterion("verifyArea >", value, "verifyarea");
            return (Criteria) this;
        }

        public Criteria andVerifyareaGreaterThanOrEqualTo(String value) {
            addCriterion("verifyArea >=", value, "verifyarea");
            return (Criteria) this;
        }

        public Criteria andVerifyareaLessThan(String value) {
            addCriterion("verifyArea <", value, "verifyarea");
            return (Criteria) this;
        }

        public Criteria andVerifyareaLessThanOrEqualTo(String value) {
            addCriterion("verifyArea <=", value, "verifyarea");
            return (Criteria) this;
        }

        public Criteria andVerifyareaLike(String value) {
            addCriterion("verifyArea like", value, "verifyarea");
            return (Criteria) this;
        }

        public Criteria andVerifyareaNotLike(String value) {
            addCriterion("verifyArea not like", value, "verifyarea");
            return (Criteria) this;
        }

        public Criteria andVerifyareaIn(List<String> values) {
            addCriterion("verifyArea in", values, "verifyarea");
            return (Criteria) this;
        }

        public Criteria andVerifyareaNotIn(List<String> values) {
            addCriterion("verifyArea not in", values, "verifyarea");
            return (Criteria) this;
        }

        public Criteria andVerifyareaBetween(String value1, String value2) {
            addCriterion("verifyArea between", value1, value2, "verifyarea");
            return (Criteria) this;
        }

        public Criteria andVerifyareaNotBetween(String value1, String value2) {
            addCriterion("verifyArea not between", value1, value2, "verifyarea");
            return (Criteria) this;
        }

        public Criteria andVqatIsNull() {
            addCriterion("VQAT is null");
            return (Criteria) this;
        }

        public Criteria andVqatIsNotNull() {
            addCriterion("VQAT is not null");
            return (Criteria) this;
        }

        public Criteria andVqatEqualTo(String value) {
            addCriterion("VQAT =", value, "vqat");
            return (Criteria) this;
        }

        public Criteria andVqatNotEqualTo(String value) {
            addCriterion("VQAT <>", value, "vqat");
            return (Criteria) this;
        }

        public Criteria andVqatGreaterThan(String value) {
            addCriterion("VQAT >", value, "vqat");
            return (Criteria) this;
        }

        public Criteria andVqatGreaterThanOrEqualTo(String value) {
            addCriterion("VQAT >=", value, "vqat");
            return (Criteria) this;
        }

        public Criteria andVqatLessThan(String value) {
            addCriterion("VQAT <", value, "vqat");
            return (Criteria) this;
        }

        public Criteria andVqatLessThanOrEqualTo(String value) {
            addCriterion("VQAT <=", value, "vqat");
            return (Criteria) this;
        }

        public Criteria andVqatLike(String value) {
            addCriterion("VQAT like", value, "vqat");
            return (Criteria) this;
        }

        public Criteria andVqatNotLike(String value) {
            addCriterion("VQAT not like", value, "vqat");
            return (Criteria) this;
        }

        public Criteria andVqatIn(List<String> values) {
            addCriterion("VQAT in", values, "vqat");
            return (Criteria) this;
        }

        public Criteria andVqatNotIn(List<String> values) {
            addCriterion("VQAT not in", values, "vqat");
            return (Criteria) this;
        }

        public Criteria andVqatBetween(String value1, String value2) {
            addCriterion("VQAT between", value1, value2, "vqat");
            return (Criteria) this;
        }

        public Criteria andVqatNotBetween(String value1, String value2) {
            addCriterion("VQAT not between", value1, value2, "vqat");
            return (Criteria) this;
        }

        public Criteria andVqaqrIsNull() {
            addCriterion("VQAQR is null");
            return (Criteria) this;
        }

        public Criteria andVqaqrIsNotNull() {
            addCriterion("VQAQR is not null");
            return (Criteria) this;
        }

        public Criteria andVqaqrEqualTo(String value) {
            addCriterion("VQAQR =", value, "vqaqr");
            return (Criteria) this;
        }

        public Criteria andVqaqrNotEqualTo(String value) {
            addCriterion("VQAQR <>", value, "vqaqr");
            return (Criteria) this;
        }

        public Criteria andVqaqrGreaterThan(String value) {
            addCriterion("VQAQR >", value, "vqaqr");
            return (Criteria) this;
        }

        public Criteria andVqaqrGreaterThanOrEqualTo(String value) {
            addCriterion("VQAQR >=", value, "vqaqr");
            return (Criteria) this;
        }

        public Criteria andVqaqrLessThan(String value) {
            addCriterion("VQAQR <", value, "vqaqr");
            return (Criteria) this;
        }

        public Criteria andVqaqrLessThanOrEqualTo(String value) {
            addCriterion("VQAQR <=", value, "vqaqr");
            return (Criteria) this;
        }

        public Criteria andVqaqrLike(String value) {
            addCriterion("VQAQR like", value, "vqaqr");
            return (Criteria) this;
        }

        public Criteria andVqaqrNotLike(String value) {
            addCriterion("VQAQR not like", value, "vqaqr");
            return (Criteria) this;
        }

        public Criteria andVqaqrIn(List<String> values) {
            addCriterion("VQAQR in", values, "vqaqr");
            return (Criteria) this;
        }

        public Criteria andVqaqrNotIn(List<String> values) {
            addCriterion("VQAQR not in", values, "vqaqr");
            return (Criteria) this;
        }

        public Criteria andVqaqrBetween(String value1, String value2) {
            addCriterion("VQAQR between", value1, value2, "vqaqr");
            return (Criteria) this;
        }

        public Criteria andVqaqrNotBetween(String value1, String value2) {
            addCriterion("VQAQR not between", value1, value2, "vqaqr");
            return (Criteria) this;
        }

        public Criteria andNurtureareatIsNull() {
            addCriterion("nurtureAreaT is null");
            return (Criteria) this;
        }

        public Criteria andNurtureareatIsNotNull() {
            addCriterion("nurtureAreaT is not null");
            return (Criteria) this;
        }

        public Criteria andNurtureareatEqualTo(String value) {
            addCriterion("nurtureAreaT =", value, "nurtureareat");
            return (Criteria) this;
        }

        public Criteria andNurtureareatNotEqualTo(String value) {
            addCriterion("nurtureAreaT <>", value, "nurtureareat");
            return (Criteria) this;
        }

        public Criteria andNurtureareatGreaterThan(String value) {
            addCriterion("nurtureAreaT >", value, "nurtureareat");
            return (Criteria) this;
        }

        public Criteria andNurtureareatGreaterThanOrEqualTo(String value) {
            addCriterion("nurtureAreaT >=", value, "nurtureareat");
            return (Criteria) this;
        }

        public Criteria andNurtureareatLessThan(String value) {
            addCriterion("nurtureAreaT <", value, "nurtureareat");
            return (Criteria) this;
        }

        public Criteria andNurtureareatLessThanOrEqualTo(String value) {
            addCriterion("nurtureAreaT <=", value, "nurtureareat");
            return (Criteria) this;
        }

        public Criteria andNurtureareatLike(String value) {
            addCriterion("nurtureAreaT like", value, "nurtureareat");
            return (Criteria) this;
        }

        public Criteria andNurtureareatNotLike(String value) {
            addCriterion("nurtureAreaT not like", value, "nurtureareat");
            return (Criteria) this;
        }

        public Criteria andNurtureareatIn(List<String> values) {
            addCriterion("nurtureAreaT in", values, "nurtureareat");
            return (Criteria) this;
        }

        public Criteria andNurtureareatNotIn(List<String> values) {
            addCriterion("nurtureAreaT not in", values, "nurtureareat");
            return (Criteria) this;
        }

        public Criteria andNurtureareatBetween(String value1, String value2) {
            addCriterion("nurtureAreaT between", value1, value2, "nurtureareat");
            return (Criteria) this;
        }

        public Criteria andNurtureareatNotBetween(String value1, String value2) {
            addCriterion("nurtureAreaT not between", value1, value2, "nurtureareat");
            return (Criteria) this;
        }

        public Criteria andNanrIsNull() {
            addCriterion("NANR is null");
            return (Criteria) this;
        }

        public Criteria andNanrIsNotNull() {
            addCriterion("NANR is not null");
            return (Criteria) this;
        }

        public Criteria andNanrEqualTo(String value) {
            addCriterion("NANR =", value, "nanr");
            return (Criteria) this;
        }

        public Criteria andNanrNotEqualTo(String value) {
            addCriterion("NANR <>", value, "nanr");
            return (Criteria) this;
        }

        public Criteria andNanrGreaterThan(String value) {
            addCriterion("NANR >", value, "nanr");
            return (Criteria) this;
        }

        public Criteria andNanrGreaterThanOrEqualTo(String value) {
            addCriterion("NANR >=", value, "nanr");
            return (Criteria) this;
        }

        public Criteria andNanrLessThan(String value) {
            addCriterion("NANR <", value, "nanr");
            return (Criteria) this;
        }

        public Criteria andNanrLessThanOrEqualTo(String value) {
            addCriterion("NANR <=", value, "nanr");
            return (Criteria) this;
        }

        public Criteria andNanrLike(String value) {
            addCriterion("NANR like", value, "nanr");
            return (Criteria) this;
        }

        public Criteria andNanrNotLike(String value) {
            addCriterion("NANR not like", value, "nanr");
            return (Criteria) this;
        }

        public Criteria andNanrIn(List<String> values) {
            addCriterion("NANR in", values, "nanr");
            return (Criteria) this;
        }

        public Criteria andNanrNotIn(List<String> values) {
            addCriterion("NANR not in", values, "nanr");
            return (Criteria) this;
        }

        public Criteria andNanrBetween(String value1, String value2) {
            addCriterion("NANR between", value1, value2, "nanr");
            return (Criteria) this;
        }

        public Criteria andNanrNotBetween(String value1, String value2) {
            addCriterion("NANR not between", value1, value2, "nanr");
            return (Criteria) this;
        }

        public Criteria andManageareatIsNull() {
            addCriterion("manageAreaT is null");
            return (Criteria) this;
        }

        public Criteria andManageareatIsNotNull() {
            addCriterion("manageAreaT is not null");
            return (Criteria) this;
        }

        public Criteria andManageareatEqualTo(String value) {
            addCriterion("manageAreaT =", value, "manageareat");
            return (Criteria) this;
        }

        public Criteria andManageareatNotEqualTo(String value) {
            addCriterion("manageAreaT <>", value, "manageareat");
            return (Criteria) this;
        }

        public Criteria andManageareatGreaterThan(String value) {
            addCriterion("manageAreaT >", value, "manageareat");
            return (Criteria) this;
        }

        public Criteria andManageareatGreaterThanOrEqualTo(String value) {
            addCriterion("manageAreaT >=", value, "manageareat");
            return (Criteria) this;
        }

        public Criteria andManageareatLessThan(String value) {
            addCriterion("manageAreaT <", value, "manageareat");
            return (Criteria) this;
        }

        public Criteria andManageareatLessThanOrEqualTo(String value) {
            addCriterion("manageAreaT <=", value, "manageareat");
            return (Criteria) this;
        }

        public Criteria andManageareatLike(String value) {
            addCriterion("manageAreaT like", value, "manageareat");
            return (Criteria) this;
        }

        public Criteria andManageareatNotLike(String value) {
            addCriterion("manageAreaT not like", value, "manageareat");
            return (Criteria) this;
        }

        public Criteria andManageareatIn(List<String> values) {
            addCriterion("manageAreaT in", values, "manageareat");
            return (Criteria) this;
        }

        public Criteria andManageareatNotIn(List<String> values) {
            addCriterion("manageAreaT not in", values, "manageareat");
            return (Criteria) this;
        }

        public Criteria andManageareatBetween(String value1, String value2) {
            addCriterion("manageAreaT between", value1, value2, "manageareat");
            return (Criteria) this;
        }

        public Criteria andManageareatNotBetween(String value1, String value2) {
            addCriterion("manageAreaT not between", value1, value2, "manageareat");
            return (Criteria) this;
        }

        public Criteria andManageareamrIsNull() {
            addCriterion("manageAreaMR is null");
            return (Criteria) this;
        }

        public Criteria andManageareamrIsNotNull() {
            addCriterion("manageAreaMR is not null");
            return (Criteria) this;
        }

        public Criteria andManageareamrEqualTo(String value) {
            addCriterion("manageAreaMR =", value, "manageareamr");
            return (Criteria) this;
        }

        public Criteria andManageareamrNotEqualTo(String value) {
            addCriterion("manageAreaMR <>", value, "manageareamr");
            return (Criteria) this;
        }

        public Criteria andManageareamrGreaterThan(String value) {
            addCriterion("manageAreaMR >", value, "manageareamr");
            return (Criteria) this;
        }

        public Criteria andManageareamrGreaterThanOrEqualTo(String value) {
            addCriterion("manageAreaMR >=", value, "manageareamr");
            return (Criteria) this;
        }

        public Criteria andManageareamrLessThan(String value) {
            addCriterion("manageAreaMR <", value, "manageareamr");
            return (Criteria) this;
        }

        public Criteria andManageareamrLessThanOrEqualTo(String value) {
            addCriterion("manageAreaMR <=", value, "manageareamr");
            return (Criteria) this;
        }

        public Criteria andManageareamrLike(String value) {
            addCriterion("manageAreaMR like", value, "manageareamr");
            return (Criteria) this;
        }

        public Criteria andManageareamrNotLike(String value) {
            addCriterion("manageAreaMR not like", value, "manageareamr");
            return (Criteria) this;
        }

        public Criteria andManageareamrIn(List<String> values) {
            addCriterion("manageAreaMR in", values, "manageareamr");
            return (Criteria) this;
        }

        public Criteria andManageareamrNotIn(List<String> values) {
            addCriterion("manageAreaMR not in", values, "manageareamr");
            return (Criteria) this;
        }

        public Criteria andManageareamrBetween(String value1, String value2) {
            addCriterion("manageAreaMR between", value1, value2, "manageareamr");
            return (Criteria) this;
        }

        public Criteria andManageareamrNotBetween(String value1, String value2) {
            addCriterion("manageAreaMR not between", value1, value2, "manageareamr");
            return (Criteria) this;
        }

        public Criteria andEudesignareaIsNull() {
            addCriterion("euDesignArea is null");
            return (Criteria) this;
        }

        public Criteria andEudesignareaIsNotNull() {
            addCriterion("euDesignArea is not null");
            return (Criteria) this;
        }

        public Criteria andEudesignareaEqualTo(String value) {
            addCriterion("euDesignArea =", value, "eudesignarea");
            return (Criteria) this;
        }

        public Criteria andEudesignareaNotEqualTo(String value) {
            addCriterion("euDesignArea <>", value, "eudesignarea");
            return (Criteria) this;
        }

        public Criteria andEudesignareaGreaterThan(String value) {
            addCriterion("euDesignArea >", value, "eudesignarea");
            return (Criteria) this;
        }

        public Criteria andEudesignareaGreaterThanOrEqualTo(String value) {
            addCriterion("euDesignArea >=", value, "eudesignarea");
            return (Criteria) this;
        }

        public Criteria andEudesignareaLessThan(String value) {
            addCriterion("euDesignArea <", value, "eudesignarea");
            return (Criteria) this;
        }

        public Criteria andEudesignareaLessThanOrEqualTo(String value) {
            addCriterion("euDesignArea <=", value, "eudesignarea");
            return (Criteria) this;
        }

        public Criteria andEudesignareaLike(String value) {
            addCriterion("euDesignArea like", value, "eudesignarea");
            return (Criteria) this;
        }

        public Criteria andEudesignareaNotLike(String value) {
            addCriterion("euDesignArea not like", value, "eudesignarea");
            return (Criteria) this;
        }

        public Criteria andEudesignareaIn(List<String> values) {
            addCriterion("euDesignArea in", values, "eudesignarea");
            return (Criteria) this;
        }

        public Criteria andEudesignareaNotIn(List<String> values) {
            addCriterion("euDesignArea not in", values, "eudesignarea");
            return (Criteria) this;
        }

        public Criteria andEudesignareaBetween(String value1, String value2) {
            addCriterion("euDesignArea between", value1, value2, "eudesignarea");
            return (Criteria) this;
        }

        public Criteria andEudesignareaNotBetween(String value1, String value2) {
            addCriterion("euDesignArea not between", value1, value2, "eudesignarea");
            return (Criteria) this;
        }

        public Criteria andEuiqrIsNull() {
            addCriterion("euIQR is null");
            return (Criteria) this;
        }

        public Criteria andEuiqrIsNotNull() {
            addCriterion("euIQR is not null");
            return (Criteria) this;
        }

        public Criteria andEuiqrEqualTo(String value) {
            addCriterion("euIQR =", value, "euiqr");
            return (Criteria) this;
        }

        public Criteria andEuiqrNotEqualTo(String value) {
            addCriterion("euIQR <>", value, "euiqr");
            return (Criteria) this;
        }

        public Criteria andEuiqrGreaterThan(String value) {
            addCriterion("euIQR >", value, "euiqr");
            return (Criteria) this;
        }

        public Criteria andEuiqrGreaterThanOrEqualTo(String value) {
            addCriterion("euIQR >=", value, "euiqr");
            return (Criteria) this;
        }

        public Criteria andEuiqrLessThan(String value) {
            addCriterion("euIQR <", value, "euiqr");
            return (Criteria) this;
        }

        public Criteria andEuiqrLessThanOrEqualTo(String value) {
            addCriterion("euIQR <=", value, "euiqr");
            return (Criteria) this;
        }

        public Criteria andEuiqrLike(String value) {
            addCriterion("euIQR like", value, "euiqr");
            return (Criteria) this;
        }

        public Criteria andEuiqrNotLike(String value) {
            addCriterion("euIQR not like", value, "euiqr");
            return (Criteria) this;
        }

        public Criteria andEuiqrIn(List<String> values) {
            addCriterion("euIQR in", values, "euiqr");
            return (Criteria) this;
        }

        public Criteria andEuiqrNotIn(List<String> values) {
            addCriterion("euIQR not in", values, "euiqr");
            return (Criteria) this;
        }

        public Criteria andEuiqrBetween(String value1, String value2) {
            addCriterion("euIQR between", value1, value2, "euiqr");
            return (Criteria) this;
        }

        public Criteria andEuiqrNotBetween(String value1, String value2) {
            addCriterion("euIQR not between", value1, value2, "euiqr");
            return (Criteria) this;
        }

        public Criteria andEuverifyareaIsNull() {
            addCriterion("euVerifyArea is null");
            return (Criteria) this;
        }

        public Criteria andEuverifyareaIsNotNull() {
            addCriterion("euVerifyArea is not null");
            return (Criteria) this;
        }

        public Criteria andEuverifyareaEqualTo(String value) {
            addCriterion("euVerifyArea =", value, "euverifyarea");
            return (Criteria) this;
        }

        public Criteria andEuverifyareaNotEqualTo(String value) {
            addCriterion("euVerifyArea <>", value, "euverifyarea");
            return (Criteria) this;
        }

        public Criteria andEuverifyareaGreaterThan(String value) {
            addCriterion("euVerifyArea >", value, "euverifyarea");
            return (Criteria) this;
        }

        public Criteria andEuverifyareaGreaterThanOrEqualTo(String value) {
            addCriterion("euVerifyArea >=", value, "euverifyarea");
            return (Criteria) this;
        }

        public Criteria andEuverifyareaLessThan(String value) {
            addCriterion("euVerifyArea <", value, "euverifyarea");
            return (Criteria) this;
        }

        public Criteria andEuverifyareaLessThanOrEqualTo(String value) {
            addCriterion("euVerifyArea <=", value, "euverifyarea");
            return (Criteria) this;
        }

        public Criteria andEuverifyareaLike(String value) {
            addCriterion("euVerifyArea like", value, "euverifyarea");
            return (Criteria) this;
        }

        public Criteria andEuverifyareaNotLike(String value) {
            addCriterion("euVerifyArea not like", value, "euverifyarea");
            return (Criteria) this;
        }

        public Criteria andEuverifyareaIn(List<String> values) {
            addCriterion("euVerifyArea in", values, "euverifyarea");
            return (Criteria) this;
        }

        public Criteria andEuverifyareaNotIn(List<String> values) {
            addCriterion("euVerifyArea not in", values, "euverifyarea");
            return (Criteria) this;
        }

        public Criteria andEuverifyareaBetween(String value1, String value2) {
            addCriterion("euVerifyArea between", value1, value2, "euverifyarea");
            return (Criteria) this;
        }

        public Criteria andEuverifyareaNotBetween(String value1, String value2) {
            addCriterion("euVerifyArea not between", value1, value2, "euverifyarea");
            return (Criteria) this;
        }

        public Criteria andEuvqaIsNull() {
            addCriterion("euVQA is null");
            return (Criteria) this;
        }

        public Criteria andEuvqaIsNotNull() {
            addCriterion("euVQA is not null");
            return (Criteria) this;
        }

        public Criteria andEuvqaEqualTo(String value) {
            addCriterion("euVQA =", value, "euvqa");
            return (Criteria) this;
        }

        public Criteria andEuvqaNotEqualTo(String value) {
            addCriterion("euVQA <>", value, "euvqa");
            return (Criteria) this;
        }

        public Criteria andEuvqaGreaterThan(String value) {
            addCriterion("euVQA >", value, "euvqa");
            return (Criteria) this;
        }

        public Criteria andEuvqaGreaterThanOrEqualTo(String value) {
            addCriterion("euVQA >=", value, "euvqa");
            return (Criteria) this;
        }

        public Criteria andEuvqaLessThan(String value) {
            addCriterion("euVQA <", value, "euvqa");
            return (Criteria) this;
        }

        public Criteria andEuvqaLessThanOrEqualTo(String value) {
            addCriterion("euVQA <=", value, "euvqa");
            return (Criteria) this;
        }

        public Criteria andEuvqaLike(String value) {
            addCriterion("euVQA like", value, "euvqa");
            return (Criteria) this;
        }

        public Criteria andEuvqaNotLike(String value) {
            addCriterion("euVQA not like", value, "euvqa");
            return (Criteria) this;
        }

        public Criteria andEuvqaIn(List<String> values) {
            addCriterion("euVQA in", values, "euvqa");
            return (Criteria) this;
        }

        public Criteria andEuvqaNotIn(List<String> values) {
            addCriterion("euVQA not in", values, "euvqa");
            return (Criteria) this;
        }

        public Criteria andEuvqaBetween(String value1, String value2) {
            addCriterion("euVQA between", value1, value2, "euvqa");
            return (Criteria) this;
        }

        public Criteria andEuvqaNotBetween(String value1, String value2) {
            addCriterion("euVQA not between", value1, value2, "euvqa");
            return (Criteria) this;
        }

        public Criteria andEunurtureareaIsNull() {
            addCriterion("euNurtureArea is null");
            return (Criteria) this;
        }

        public Criteria andEunurtureareaIsNotNull() {
            addCriterion("euNurtureArea is not null");
            return (Criteria) this;
        }

        public Criteria andEunurtureareaEqualTo(String value) {
            addCriterion("euNurtureArea =", value, "eunurturearea");
            return (Criteria) this;
        }

        public Criteria andEunurtureareaNotEqualTo(String value) {
            addCriterion("euNurtureArea <>", value, "eunurturearea");
            return (Criteria) this;
        }

        public Criteria andEunurtureareaGreaterThan(String value) {
            addCriterion("euNurtureArea >", value, "eunurturearea");
            return (Criteria) this;
        }

        public Criteria andEunurtureareaGreaterThanOrEqualTo(String value) {
            addCriterion("euNurtureArea >=", value, "eunurturearea");
            return (Criteria) this;
        }

        public Criteria andEunurtureareaLessThan(String value) {
            addCriterion("euNurtureArea <", value, "eunurturearea");
            return (Criteria) this;
        }

        public Criteria andEunurtureareaLessThanOrEqualTo(String value) {
            addCriterion("euNurtureArea <=", value, "eunurturearea");
            return (Criteria) this;
        }

        public Criteria andEunurtureareaLike(String value) {
            addCriterion("euNurtureArea like", value, "eunurturearea");
            return (Criteria) this;
        }

        public Criteria andEunurtureareaNotLike(String value) {
            addCriterion("euNurtureArea not like", value, "eunurturearea");
            return (Criteria) this;
        }

        public Criteria andEunurtureareaIn(List<String> values) {
            addCriterion("euNurtureArea in", values, "eunurturearea");
            return (Criteria) this;
        }

        public Criteria andEunurtureareaNotIn(List<String> values) {
            addCriterion("euNurtureArea not in", values, "eunurturearea");
            return (Criteria) this;
        }

        public Criteria andEunurtureareaBetween(String value1, String value2) {
            addCriterion("euNurtureArea between", value1, value2, "eunurturearea");
            return (Criteria) this;
        }

        public Criteria andEunurtureareaNotBetween(String value1, String value2) {
            addCriterion("euNurtureArea not between", value1, value2, "eunurturearea");
            return (Criteria) this;
        }

        public Criteria andEumanageareaIsNull() {
            addCriterion("euManageArea is null");
            return (Criteria) this;
        }

        public Criteria andEumanageareaIsNotNull() {
            addCriterion("euManageArea is not null");
            return (Criteria) this;
        }

        public Criteria andEumanageareaEqualTo(String value) {
            addCriterion("euManageArea =", value, "eumanagearea");
            return (Criteria) this;
        }

        public Criteria andEumanageareaNotEqualTo(String value) {
            addCriterion("euManageArea <>", value, "eumanagearea");
            return (Criteria) this;
        }

        public Criteria andEumanageareaGreaterThan(String value) {
            addCriterion("euManageArea >", value, "eumanagearea");
            return (Criteria) this;
        }

        public Criteria andEumanageareaGreaterThanOrEqualTo(String value) {
            addCriterion("euManageArea >=", value, "eumanagearea");
            return (Criteria) this;
        }

        public Criteria andEumanageareaLessThan(String value) {
            addCriterion("euManageArea <", value, "eumanagearea");
            return (Criteria) this;
        }

        public Criteria andEumanageareaLessThanOrEqualTo(String value) {
            addCriterion("euManageArea <=", value, "eumanagearea");
            return (Criteria) this;
        }

        public Criteria andEumanageareaLike(String value) {
            addCriterion("euManageArea like", value, "eumanagearea");
            return (Criteria) this;
        }

        public Criteria andEumanageareaNotLike(String value) {
            addCriterion("euManageArea not like", value, "eumanagearea");
            return (Criteria) this;
        }

        public Criteria andEumanageareaIn(List<String> values) {
            addCriterion("euManageArea in", values, "eumanagearea");
            return (Criteria) this;
        }

        public Criteria andEumanageareaNotIn(List<String> values) {
            addCriterion("euManageArea not in", values, "eumanagearea");
            return (Criteria) this;
        }

        public Criteria andEumanageareaBetween(String value1, String value2) {
            addCriterion("euManageArea between", value1, value2, "eumanagearea");
            return (Criteria) this;
        }

        public Criteria andEumanageareaNotBetween(String value1, String value2) {
            addCriterion("euManageArea not between", value1, value2, "eumanagearea");
            return (Criteria) this;
        }

        public Criteria andEuvrqrIsNull() {
            addCriterion("euVRQR is null");
            return (Criteria) this;
        }

        public Criteria andEuvrqrIsNotNull() {
            addCriterion("euVRQR is not null");
            return (Criteria) this;
        }

        public Criteria andEuvrqrEqualTo(String value) {
            addCriterion("euVRQR =", value, "euvrqr");
            return (Criteria) this;
        }

        public Criteria andEuvrqrNotEqualTo(String value) {
            addCriterion("euVRQR <>", value, "euvrqr");
            return (Criteria) this;
        }

        public Criteria andEuvrqrGreaterThan(String value) {
            addCriterion("euVRQR >", value, "euvrqr");
            return (Criteria) this;
        }

        public Criteria andEuvrqrGreaterThanOrEqualTo(String value) {
            addCriterion("euVRQR >=", value, "euvrqr");
            return (Criteria) this;
        }

        public Criteria andEuvrqrLessThan(String value) {
            addCriterion("euVRQR <", value, "euvrqr");
            return (Criteria) this;
        }

        public Criteria andEuvrqrLessThanOrEqualTo(String value) {
            addCriterion("euVRQR <=", value, "euvrqr");
            return (Criteria) this;
        }

        public Criteria andEuvrqrLike(String value) {
            addCriterion("euVRQR like", value, "euvrqr");
            return (Criteria) this;
        }

        public Criteria andEuvrqrNotLike(String value) {
            addCriterion("euVRQR not like", value, "euvrqr");
            return (Criteria) this;
        }

        public Criteria andEuvrqrIn(List<String> values) {
            addCriterion("euVRQR in", values, "euvrqr");
            return (Criteria) this;
        }

        public Criteria andEuvrqrNotIn(List<String> values) {
            addCriterion("euVRQR not in", values, "euvrqr");
            return (Criteria) this;
        }

        public Criteria andEuvrqrBetween(String value1, String value2) {
            addCriterion("euVRQR between", value1, value2, "euvrqr");
            return (Criteria) this;
        }

        public Criteria andEuvrqrNotBetween(String value1, String value2) {
            addCriterion("euVRQR not between", value1, value2, "euvrqr");
            return (Criteria) this;
        }

        public Criteria andEunurtureIsNull() {
            addCriterion("euNurture is null");
            return (Criteria) this;
        }

        public Criteria andEunurtureIsNotNull() {
            addCriterion("euNurture is not null");
            return (Criteria) this;
        }

        public Criteria andEunurtureEqualTo(String value) {
            addCriterion("euNurture =", value, "eunurture");
            return (Criteria) this;
        }

        public Criteria andEunurtureNotEqualTo(String value) {
            addCriterion("euNurture <>", value, "eunurture");
            return (Criteria) this;
        }

        public Criteria andEunurtureGreaterThan(String value) {
            addCriterion("euNurture >", value, "eunurture");
            return (Criteria) this;
        }

        public Criteria andEunurtureGreaterThanOrEqualTo(String value) {
            addCriterion("euNurture >=", value, "eunurture");
            return (Criteria) this;
        }

        public Criteria andEunurtureLessThan(String value) {
            addCriterion("euNurture <", value, "eunurture");
            return (Criteria) this;
        }

        public Criteria andEunurtureLessThanOrEqualTo(String value) {
            addCriterion("euNurture <=", value, "eunurture");
            return (Criteria) this;
        }

        public Criteria andEunurtureLike(String value) {
            addCriterion("euNurture like", value, "eunurture");
            return (Criteria) this;
        }

        public Criteria andEunurtureNotLike(String value) {
            addCriterion("euNurture not like", value, "eunurture");
            return (Criteria) this;
        }

        public Criteria andEunurtureIn(List<String> values) {
            addCriterion("euNurture in", values, "eunurture");
            return (Criteria) this;
        }

        public Criteria andEunurtureNotIn(List<String> values) {
            addCriterion("euNurture not in", values, "eunurture");
            return (Criteria) this;
        }

        public Criteria andEunurtureBetween(String value1, String value2) {
            addCriterion("euNurture between", value1, value2, "eunurture");
            return (Criteria) this;
        }

        public Criteria andEunurtureNotBetween(String value1, String value2) {
            addCriterion("euNurture not between", value1, value2, "eunurture");
            return (Criteria) this;
        }

        public Criteria andEumanageIsNull() {
            addCriterion("euManage is null");
            return (Criteria) this;
        }

        public Criteria andEumanageIsNotNull() {
            addCriterion("euManage is not null");
            return (Criteria) this;
        }

        public Criteria andEumanageEqualTo(String value) {
            addCriterion("euManage =", value, "eumanage");
            return (Criteria) this;
        }

        public Criteria andEumanageNotEqualTo(String value) {
            addCriterion("euManage <>", value, "eumanage");
            return (Criteria) this;
        }

        public Criteria andEumanageGreaterThan(String value) {
            addCriterion("euManage >", value, "eumanage");
            return (Criteria) this;
        }

        public Criteria andEumanageGreaterThanOrEqualTo(String value) {
            addCriterion("euManage >=", value, "eumanage");
            return (Criteria) this;
        }

        public Criteria andEumanageLessThan(String value) {
            addCriterion("euManage <", value, "eumanage");
            return (Criteria) this;
        }

        public Criteria andEumanageLessThanOrEqualTo(String value) {
            addCriterion("euManage <=", value, "eumanage");
            return (Criteria) this;
        }

        public Criteria andEumanageLike(String value) {
            addCriterion("euManage like", value, "eumanage");
            return (Criteria) this;
        }

        public Criteria andEumanageNotLike(String value) {
            addCriterion("euManage not like", value, "eumanage");
            return (Criteria) this;
        }

        public Criteria andEumanageIn(List<String> values) {
            addCriterion("euManage in", values, "eumanage");
            return (Criteria) this;
        }

        public Criteria andEumanageNotIn(List<String> values) {
            addCriterion("euManage not in", values, "eumanage");
            return (Criteria) this;
        }

        public Criteria andEumanageBetween(String value1, String value2) {
            addCriterion("euManage between", value1, value2, "eumanage");
            return (Criteria) this;
        }

        public Criteria andEumanageNotBetween(String value1, String value2) {
            addCriterion("euManage not between", value1, value2, "eumanage");
            return (Criteria) this;
        }

        public Criteria andOtdesignareaIsNull() {
            addCriterion("otDesignArea is null");
            return (Criteria) this;
        }

        public Criteria andOtdesignareaIsNotNull() {
            addCriterion("otDesignArea is not null");
            return (Criteria) this;
        }

        public Criteria andOtdesignareaEqualTo(String value) {
            addCriterion("otDesignArea =", value, "otdesignarea");
            return (Criteria) this;
        }

        public Criteria andOtdesignareaNotEqualTo(String value) {
            addCriterion("otDesignArea <>", value, "otdesignarea");
            return (Criteria) this;
        }

        public Criteria andOtdesignareaGreaterThan(String value) {
            addCriterion("otDesignArea >", value, "otdesignarea");
            return (Criteria) this;
        }

        public Criteria andOtdesignareaGreaterThanOrEqualTo(String value) {
            addCriterion("otDesignArea >=", value, "otdesignarea");
            return (Criteria) this;
        }

        public Criteria andOtdesignareaLessThan(String value) {
            addCriterion("otDesignArea <", value, "otdesignarea");
            return (Criteria) this;
        }

        public Criteria andOtdesignareaLessThanOrEqualTo(String value) {
            addCriterion("otDesignArea <=", value, "otdesignarea");
            return (Criteria) this;
        }

        public Criteria andOtdesignareaLike(String value) {
            addCriterion("otDesignArea like", value, "otdesignarea");
            return (Criteria) this;
        }

        public Criteria andOtdesignareaNotLike(String value) {
            addCriterion("otDesignArea not like", value, "otdesignarea");
            return (Criteria) this;
        }

        public Criteria andOtdesignareaIn(List<String> values) {
            addCriterion("otDesignArea in", values, "otdesignarea");
            return (Criteria) this;
        }

        public Criteria andOtdesignareaNotIn(List<String> values) {
            addCriterion("otDesignArea not in", values, "otdesignarea");
            return (Criteria) this;
        }

        public Criteria andOtdesignareaBetween(String value1, String value2) {
            addCriterion("otDesignArea between", value1, value2, "otdesignarea");
            return (Criteria) this;
        }

        public Criteria andOtdesignareaNotBetween(String value1, String value2) {
            addCriterion("otDesignArea not between", value1, value2, "otdesignarea");
            return (Criteria) this;
        }

        public Criteria andOtiqrIsNull() {
            addCriterion("otIQR is null");
            return (Criteria) this;
        }

        public Criteria andOtiqrIsNotNull() {
            addCriterion("otIQR is not null");
            return (Criteria) this;
        }

        public Criteria andOtiqrEqualTo(String value) {
            addCriterion("otIQR =", value, "otiqr");
            return (Criteria) this;
        }

        public Criteria andOtiqrNotEqualTo(String value) {
            addCriterion("otIQR <>", value, "otiqr");
            return (Criteria) this;
        }

        public Criteria andOtiqrGreaterThan(String value) {
            addCriterion("otIQR >", value, "otiqr");
            return (Criteria) this;
        }

        public Criteria andOtiqrGreaterThanOrEqualTo(String value) {
            addCriterion("otIQR >=", value, "otiqr");
            return (Criteria) this;
        }

        public Criteria andOtiqrLessThan(String value) {
            addCriterion("otIQR <", value, "otiqr");
            return (Criteria) this;
        }

        public Criteria andOtiqrLessThanOrEqualTo(String value) {
            addCriterion("otIQR <=", value, "otiqr");
            return (Criteria) this;
        }

        public Criteria andOtiqrLike(String value) {
            addCriterion("otIQR like", value, "otiqr");
            return (Criteria) this;
        }

        public Criteria andOtiqrNotLike(String value) {
            addCriterion("otIQR not like", value, "otiqr");
            return (Criteria) this;
        }

        public Criteria andOtiqrIn(List<String> values) {
            addCriterion("otIQR in", values, "otiqr");
            return (Criteria) this;
        }

        public Criteria andOtiqrNotIn(List<String> values) {
            addCriterion("otIQR not in", values, "otiqr");
            return (Criteria) this;
        }

        public Criteria andOtiqrBetween(String value1, String value2) {
            addCriterion("otIQR between", value1, value2, "otiqr");
            return (Criteria) this;
        }

        public Criteria andOtiqrNotBetween(String value1, String value2) {
            addCriterion("otIQR not between", value1, value2, "otiqr");
            return (Criteria) this;
        }

        public Criteria andOtverifyareaIsNull() {
            addCriterion("otVerifyArea is null");
            return (Criteria) this;
        }

        public Criteria andOtverifyareaIsNotNull() {
            addCriterion("otVerifyArea is not null");
            return (Criteria) this;
        }

        public Criteria andOtverifyareaEqualTo(String value) {
            addCriterion("otVerifyArea =", value, "otverifyarea");
            return (Criteria) this;
        }

        public Criteria andOtverifyareaNotEqualTo(String value) {
            addCriterion("otVerifyArea <>", value, "otverifyarea");
            return (Criteria) this;
        }

        public Criteria andOtverifyareaGreaterThan(String value) {
            addCriterion("otVerifyArea >", value, "otverifyarea");
            return (Criteria) this;
        }

        public Criteria andOtverifyareaGreaterThanOrEqualTo(String value) {
            addCriterion("otVerifyArea >=", value, "otverifyarea");
            return (Criteria) this;
        }

        public Criteria andOtverifyareaLessThan(String value) {
            addCriterion("otVerifyArea <", value, "otverifyarea");
            return (Criteria) this;
        }

        public Criteria andOtverifyareaLessThanOrEqualTo(String value) {
            addCriterion("otVerifyArea <=", value, "otverifyarea");
            return (Criteria) this;
        }

        public Criteria andOtverifyareaLike(String value) {
            addCriterion("otVerifyArea like", value, "otverifyarea");
            return (Criteria) this;
        }

        public Criteria andOtverifyareaNotLike(String value) {
            addCriterion("otVerifyArea not like", value, "otverifyarea");
            return (Criteria) this;
        }

        public Criteria andOtverifyareaIn(List<String> values) {
            addCriterion("otVerifyArea in", values, "otverifyarea");
            return (Criteria) this;
        }

        public Criteria andOtverifyareaNotIn(List<String> values) {
            addCriterion("otVerifyArea not in", values, "otverifyarea");
            return (Criteria) this;
        }

        public Criteria andOtverifyareaBetween(String value1, String value2) {
            addCriterion("otVerifyArea between", value1, value2, "otverifyarea");
            return (Criteria) this;
        }

        public Criteria andOtverifyareaNotBetween(String value1, String value2) {
            addCriterion("otVerifyArea not between", value1, value2, "otverifyarea");
            return (Criteria) this;
        }

        public Criteria andOtvqaIsNull() {
            addCriterion("otVQA is null");
            return (Criteria) this;
        }

        public Criteria andOtvqaIsNotNull() {
            addCriterion("otVQA is not null");
            return (Criteria) this;
        }

        public Criteria andOtvqaEqualTo(String value) {
            addCriterion("otVQA =", value, "otvqa");
            return (Criteria) this;
        }

        public Criteria andOtvqaNotEqualTo(String value) {
            addCriterion("otVQA <>", value, "otvqa");
            return (Criteria) this;
        }

        public Criteria andOtvqaGreaterThan(String value) {
            addCriterion("otVQA >", value, "otvqa");
            return (Criteria) this;
        }

        public Criteria andOtvqaGreaterThanOrEqualTo(String value) {
            addCriterion("otVQA >=", value, "otvqa");
            return (Criteria) this;
        }

        public Criteria andOtvqaLessThan(String value) {
            addCriterion("otVQA <", value, "otvqa");
            return (Criteria) this;
        }

        public Criteria andOtvqaLessThanOrEqualTo(String value) {
            addCriterion("otVQA <=", value, "otvqa");
            return (Criteria) this;
        }

        public Criteria andOtvqaLike(String value) {
            addCriterion("otVQA like", value, "otvqa");
            return (Criteria) this;
        }

        public Criteria andOtvqaNotLike(String value) {
            addCriterion("otVQA not like", value, "otvqa");
            return (Criteria) this;
        }

        public Criteria andOtvqaIn(List<String> values) {
            addCriterion("otVQA in", values, "otvqa");
            return (Criteria) this;
        }

        public Criteria andOtvqaNotIn(List<String> values) {
            addCriterion("otVQA not in", values, "otvqa");
            return (Criteria) this;
        }

        public Criteria andOtvqaBetween(String value1, String value2) {
            addCriterion("otVQA between", value1, value2, "otvqa");
            return (Criteria) this;
        }

        public Criteria andOtvqaNotBetween(String value1, String value2) {
            addCriterion("otVQA not between", value1, value2, "otvqa");
            return (Criteria) this;
        }

        public Criteria andOtnurtureareaIsNull() {
            addCriterion("otNurtureArea is null");
            return (Criteria) this;
        }

        public Criteria andOtnurtureareaIsNotNull() {
            addCriterion("otNurtureArea is not null");
            return (Criteria) this;
        }

        public Criteria andOtnurtureareaEqualTo(String value) {
            addCriterion("otNurtureArea =", value, "otnurturearea");
            return (Criteria) this;
        }

        public Criteria andOtnurtureareaNotEqualTo(String value) {
            addCriterion("otNurtureArea <>", value, "otnurturearea");
            return (Criteria) this;
        }

        public Criteria andOtnurtureareaGreaterThan(String value) {
            addCriterion("otNurtureArea >", value, "otnurturearea");
            return (Criteria) this;
        }

        public Criteria andOtnurtureareaGreaterThanOrEqualTo(String value) {
            addCriterion("otNurtureArea >=", value, "otnurturearea");
            return (Criteria) this;
        }

        public Criteria andOtnurtureareaLessThan(String value) {
            addCriterion("otNurtureArea <", value, "otnurturearea");
            return (Criteria) this;
        }

        public Criteria andOtnurtureareaLessThanOrEqualTo(String value) {
            addCriterion("otNurtureArea <=", value, "otnurturearea");
            return (Criteria) this;
        }

        public Criteria andOtnurtureareaLike(String value) {
            addCriterion("otNurtureArea like", value, "otnurturearea");
            return (Criteria) this;
        }

        public Criteria andOtnurtureareaNotLike(String value) {
            addCriterion("otNurtureArea not like", value, "otnurturearea");
            return (Criteria) this;
        }

        public Criteria andOtnurtureareaIn(List<String> values) {
            addCriterion("otNurtureArea in", values, "otnurturearea");
            return (Criteria) this;
        }

        public Criteria andOtnurtureareaNotIn(List<String> values) {
            addCriterion("otNurtureArea not in", values, "otnurturearea");
            return (Criteria) this;
        }

        public Criteria andOtnurtureareaBetween(String value1, String value2) {
            addCriterion("otNurtureArea between", value1, value2, "otnurturearea");
            return (Criteria) this;
        }

        public Criteria andOtnurtureareaNotBetween(String value1, String value2) {
            addCriterion("otNurtureArea not between", value1, value2, "otnurturearea");
            return (Criteria) this;
        }

        public Criteria andOtmanageareaIsNull() {
            addCriterion("otManageArea is null");
            return (Criteria) this;
        }

        public Criteria andOtmanageareaIsNotNull() {
            addCriterion("otManageArea is not null");
            return (Criteria) this;
        }

        public Criteria andOtmanageareaEqualTo(String value) {
            addCriterion("otManageArea =", value, "otmanagearea");
            return (Criteria) this;
        }

        public Criteria andOtmanageareaNotEqualTo(String value) {
            addCriterion("otManageArea <>", value, "otmanagearea");
            return (Criteria) this;
        }

        public Criteria andOtmanageareaGreaterThan(String value) {
            addCriterion("otManageArea >", value, "otmanagearea");
            return (Criteria) this;
        }

        public Criteria andOtmanageareaGreaterThanOrEqualTo(String value) {
            addCriterion("otManageArea >=", value, "otmanagearea");
            return (Criteria) this;
        }

        public Criteria andOtmanageareaLessThan(String value) {
            addCriterion("otManageArea <", value, "otmanagearea");
            return (Criteria) this;
        }

        public Criteria andOtmanageareaLessThanOrEqualTo(String value) {
            addCriterion("otManageArea <=", value, "otmanagearea");
            return (Criteria) this;
        }

        public Criteria andOtmanageareaLike(String value) {
            addCriterion("otManageArea like", value, "otmanagearea");
            return (Criteria) this;
        }

        public Criteria andOtmanageareaNotLike(String value) {
            addCriterion("otManageArea not like", value, "otmanagearea");
            return (Criteria) this;
        }

        public Criteria andOtmanageareaIn(List<String> values) {
            addCriterion("otManageArea in", values, "otmanagearea");
            return (Criteria) this;
        }

        public Criteria andOtmanageareaNotIn(List<String> values) {
            addCriterion("otManageArea not in", values, "otmanagearea");
            return (Criteria) this;
        }

        public Criteria andOtmanageareaBetween(String value1, String value2) {
            addCriterion("otManageArea between", value1, value2, "otmanagearea");
            return (Criteria) this;
        }

        public Criteria andOtmanageareaNotBetween(String value1, String value2) {
            addCriterion("otManageArea not between", value1, value2, "otmanagearea");
            return (Criteria) this;
        }

        public Criteria andOtvrqrIsNull() {
            addCriterion("otVRQR is null");
            return (Criteria) this;
        }

        public Criteria andOtvrqrIsNotNull() {
            addCriterion("otVRQR is not null");
            return (Criteria) this;
        }

        public Criteria andOtvrqrEqualTo(String value) {
            addCriterion("otVRQR =", value, "otvrqr");
            return (Criteria) this;
        }

        public Criteria andOtvrqrNotEqualTo(String value) {
            addCriterion("otVRQR <>", value, "otvrqr");
            return (Criteria) this;
        }

        public Criteria andOtvrqrGreaterThan(String value) {
            addCriterion("otVRQR >", value, "otvrqr");
            return (Criteria) this;
        }

        public Criteria andOtvrqrGreaterThanOrEqualTo(String value) {
            addCriterion("otVRQR >=", value, "otvrqr");
            return (Criteria) this;
        }

        public Criteria andOtvrqrLessThan(String value) {
            addCriterion("otVRQR <", value, "otvrqr");
            return (Criteria) this;
        }

        public Criteria andOtvrqrLessThanOrEqualTo(String value) {
            addCriterion("otVRQR <=", value, "otvrqr");
            return (Criteria) this;
        }

        public Criteria andOtvrqrLike(String value) {
            addCriterion("otVRQR like", value, "otvrqr");
            return (Criteria) this;
        }

        public Criteria andOtvrqrNotLike(String value) {
            addCriterion("otVRQR not like", value, "otvrqr");
            return (Criteria) this;
        }

        public Criteria andOtvrqrIn(List<String> values) {
            addCriterion("otVRQR in", values, "otvrqr");
            return (Criteria) this;
        }

        public Criteria andOtvrqrNotIn(List<String> values) {
            addCriterion("otVRQR not in", values, "otvrqr");
            return (Criteria) this;
        }

        public Criteria andOtvrqrBetween(String value1, String value2) {
            addCriterion("otVRQR between", value1, value2, "otvrqr");
            return (Criteria) this;
        }

        public Criteria andOtvrqrNotBetween(String value1, String value2) {
            addCriterion("otVRQR not between", value1, value2, "otvrqr");
            return (Criteria) this;
        }

        public Criteria andOthnurtureIsNull() {
            addCriterion("othNurture is null");
            return (Criteria) this;
        }

        public Criteria andOthnurtureIsNotNull() {
            addCriterion("othNurture is not null");
            return (Criteria) this;
        }

        public Criteria andOthnurtureEqualTo(String value) {
            addCriterion("othNurture =", value, "othnurture");
            return (Criteria) this;
        }

        public Criteria andOthnurtureNotEqualTo(String value) {
            addCriterion("othNurture <>", value, "othnurture");
            return (Criteria) this;
        }

        public Criteria andOthnurtureGreaterThan(String value) {
            addCriterion("othNurture >", value, "othnurture");
            return (Criteria) this;
        }

        public Criteria andOthnurtureGreaterThanOrEqualTo(String value) {
            addCriterion("othNurture >=", value, "othnurture");
            return (Criteria) this;
        }

        public Criteria andOthnurtureLessThan(String value) {
            addCriterion("othNurture <", value, "othnurture");
            return (Criteria) this;
        }

        public Criteria andOthnurtureLessThanOrEqualTo(String value) {
            addCriterion("othNurture <=", value, "othnurture");
            return (Criteria) this;
        }

        public Criteria andOthnurtureLike(String value) {
            addCriterion("othNurture like", value, "othnurture");
            return (Criteria) this;
        }

        public Criteria andOthnurtureNotLike(String value) {
            addCriterion("othNurture not like", value, "othnurture");
            return (Criteria) this;
        }

        public Criteria andOthnurtureIn(List<String> values) {
            addCriterion("othNurture in", values, "othnurture");
            return (Criteria) this;
        }

        public Criteria andOthnurtureNotIn(List<String> values) {
            addCriterion("othNurture not in", values, "othnurture");
            return (Criteria) this;
        }

        public Criteria andOthnurtureBetween(String value1, String value2) {
            addCriterion("othNurture between", value1, value2, "othnurture");
            return (Criteria) this;
        }

        public Criteria andOthnurtureNotBetween(String value1, String value2) {
            addCriterion("othNurture not between", value1, value2, "othnurture");
            return (Criteria) this;
        }

        public Criteria andOtmanageIsNull() {
            addCriterion("otManage is null");
            return (Criteria) this;
        }

        public Criteria andOtmanageIsNotNull() {
            addCriterion("otManage is not null");
            return (Criteria) this;
        }

        public Criteria andOtmanageEqualTo(String value) {
            addCriterion("otManage =", value, "otmanage");
            return (Criteria) this;
        }

        public Criteria andOtmanageNotEqualTo(String value) {
            addCriterion("otManage <>", value, "otmanage");
            return (Criteria) this;
        }

        public Criteria andOtmanageGreaterThan(String value) {
            addCriterion("otManage >", value, "otmanage");
            return (Criteria) this;
        }

        public Criteria andOtmanageGreaterThanOrEqualTo(String value) {
            addCriterion("otManage >=", value, "otmanage");
            return (Criteria) this;
        }

        public Criteria andOtmanageLessThan(String value) {
            addCriterion("otManage <", value, "otmanage");
            return (Criteria) this;
        }

        public Criteria andOtmanageLessThanOrEqualTo(String value) {
            addCriterion("otManage <=", value, "otmanage");
            return (Criteria) this;
        }

        public Criteria andOtmanageLike(String value) {
            addCriterion("otManage like", value, "otmanage");
            return (Criteria) this;
        }

        public Criteria andOtmanageNotLike(String value) {
            addCriterion("otManage not like", value, "otmanage");
            return (Criteria) this;
        }

        public Criteria andOtmanageIn(List<String> values) {
            addCriterion("otManage in", values, "otmanage");
            return (Criteria) this;
        }

        public Criteria andOtmanageNotIn(List<String> values) {
            addCriterion("otManage not in", values, "otmanage");
            return (Criteria) this;
        }

        public Criteria andOtmanageBetween(String value1, String value2) {
            addCriterion("otManage between", value1, value2, "otmanage");
            return (Criteria) this;
        }

        public Criteria andOtmanageNotBetween(String value1, String value2) {
            addCriterion("otManage not between", value1, value2, "otmanage");
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
     * @date 2019-03-29
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