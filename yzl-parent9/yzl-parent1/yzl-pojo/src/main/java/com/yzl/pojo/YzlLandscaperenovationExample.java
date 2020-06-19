package com.yzl.pojo;

import java.util.ArrayList;
import java.util.List;

public class YzlLandscaperenovationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public YzlLandscaperenovationExample() {
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

        public Criteria andNewdesignareaIsNull() {
            addCriterion("newDesignArea is null");
            return (Criteria) this;
        }

        public Criteria andNewdesignareaIsNotNull() {
            addCriterion("newDesignArea is not null");
            return (Criteria) this;
        }

        public Criteria andNewdesignareaEqualTo(String value) {
            addCriterion("newDesignArea =", value, "newdesignarea");
            return (Criteria) this;
        }

        public Criteria andNewdesignareaNotEqualTo(String value) {
            addCriterion("newDesignArea <>", value, "newdesignarea");
            return (Criteria) this;
        }

        public Criteria andNewdesignareaGreaterThan(String value) {
            addCriterion("newDesignArea >", value, "newdesignarea");
            return (Criteria) this;
        }

        public Criteria andNewdesignareaGreaterThanOrEqualTo(String value) {
            addCriterion("newDesignArea >=", value, "newdesignarea");
            return (Criteria) this;
        }

        public Criteria andNewdesignareaLessThan(String value) {
            addCriterion("newDesignArea <", value, "newdesignarea");
            return (Criteria) this;
        }

        public Criteria andNewdesignareaLessThanOrEqualTo(String value) {
            addCriterion("newDesignArea <=", value, "newdesignarea");
            return (Criteria) this;
        }

        public Criteria andNewdesignareaLike(String value) {
            addCriterion("newDesignArea like", value, "newdesignarea");
            return (Criteria) this;
        }

        public Criteria andNewdesignareaNotLike(String value) {
            addCriterion("newDesignArea not like", value, "newdesignarea");
            return (Criteria) this;
        }

        public Criteria andNewdesignareaIn(List<String> values) {
            addCriterion("newDesignArea in", values, "newdesignarea");
            return (Criteria) this;
        }

        public Criteria andNewdesignareaNotIn(List<String> values) {
            addCriterion("newDesignArea not in", values, "newdesignarea");
            return (Criteria) this;
        }

        public Criteria andNewdesignareaBetween(String value1, String value2) {
            addCriterion("newDesignArea between", value1, value2, "newdesignarea");
            return (Criteria) this;
        }

        public Criteria andNewdesignareaNotBetween(String value1, String value2) {
            addCriterion("newDesignArea not between", value1, value2, "newdesignarea");
            return (Criteria) this;
        }

        public Criteria andNewiqrIsNull() {
            addCriterion("newIQR is null");
            return (Criteria) this;
        }

        public Criteria andNewiqrIsNotNull() {
            addCriterion("newIQR is not null");
            return (Criteria) this;
        }

        public Criteria andNewiqrEqualTo(String value) {
            addCriterion("newIQR =", value, "newiqr");
            return (Criteria) this;
        }

        public Criteria andNewiqrNotEqualTo(String value) {
            addCriterion("newIQR <>", value, "newiqr");
            return (Criteria) this;
        }

        public Criteria andNewiqrGreaterThan(String value) {
            addCriterion("newIQR >", value, "newiqr");
            return (Criteria) this;
        }

        public Criteria andNewiqrGreaterThanOrEqualTo(String value) {
            addCriterion("newIQR >=", value, "newiqr");
            return (Criteria) this;
        }

        public Criteria andNewiqrLessThan(String value) {
            addCriterion("newIQR <", value, "newiqr");
            return (Criteria) this;
        }

        public Criteria andNewiqrLessThanOrEqualTo(String value) {
            addCriterion("newIQR <=", value, "newiqr");
            return (Criteria) this;
        }

        public Criteria andNewiqrLike(String value) {
            addCriterion("newIQR like", value, "newiqr");
            return (Criteria) this;
        }

        public Criteria andNewiqrNotLike(String value) {
            addCriterion("newIQR not like", value, "newiqr");
            return (Criteria) this;
        }

        public Criteria andNewiqrIn(List<String> values) {
            addCriterion("newIQR in", values, "newiqr");
            return (Criteria) this;
        }

        public Criteria andNewiqrNotIn(List<String> values) {
            addCriterion("newIQR not in", values, "newiqr");
            return (Criteria) this;
        }

        public Criteria andNewiqrBetween(String value1, String value2) {
            addCriterion("newIQR between", value1, value2, "newiqr");
            return (Criteria) this;
        }

        public Criteria andNewiqrNotBetween(String value1, String value2) {
            addCriterion("newIQR not between", value1, value2, "newiqr");
            return (Criteria) this;
        }

        public Criteria andNewverifyareaIsNull() {
            addCriterion("newVerifyArea is null");
            return (Criteria) this;
        }

        public Criteria andNewverifyareaIsNotNull() {
            addCriterion("newVerifyArea is not null");
            return (Criteria) this;
        }

        public Criteria andNewverifyareaEqualTo(String value) {
            addCriterion("newVerifyArea =", value, "newverifyarea");
            return (Criteria) this;
        }

        public Criteria andNewverifyareaNotEqualTo(String value) {
            addCriterion("newVerifyArea <>", value, "newverifyarea");
            return (Criteria) this;
        }

        public Criteria andNewverifyareaGreaterThan(String value) {
            addCriterion("newVerifyArea >", value, "newverifyarea");
            return (Criteria) this;
        }

        public Criteria andNewverifyareaGreaterThanOrEqualTo(String value) {
            addCriterion("newVerifyArea >=", value, "newverifyarea");
            return (Criteria) this;
        }

        public Criteria andNewverifyareaLessThan(String value) {
            addCriterion("newVerifyArea <", value, "newverifyarea");
            return (Criteria) this;
        }

        public Criteria andNewverifyareaLessThanOrEqualTo(String value) {
            addCriterion("newVerifyArea <=", value, "newverifyarea");
            return (Criteria) this;
        }

        public Criteria andNewverifyareaLike(String value) {
            addCriterion("newVerifyArea like", value, "newverifyarea");
            return (Criteria) this;
        }

        public Criteria andNewverifyareaNotLike(String value) {
            addCriterion("newVerifyArea not like", value, "newverifyarea");
            return (Criteria) this;
        }

        public Criteria andNewverifyareaIn(List<String> values) {
            addCriterion("newVerifyArea in", values, "newverifyarea");
            return (Criteria) this;
        }

        public Criteria andNewverifyareaNotIn(List<String> values) {
            addCriterion("newVerifyArea not in", values, "newverifyarea");
            return (Criteria) this;
        }

        public Criteria andNewverifyareaBetween(String value1, String value2) {
            addCriterion("newVerifyArea between", value1, value2, "newverifyarea");
            return (Criteria) this;
        }

        public Criteria andNewverifyareaNotBetween(String value1, String value2) {
            addCriterion("newVerifyArea not between", value1, value2, "newverifyarea");
            return (Criteria) this;
        }

        public Criteria andNewvqaIsNull() {
            addCriterion("newVQA is null");
            return (Criteria) this;
        }

        public Criteria andNewvqaIsNotNull() {
            addCriterion("newVQA is not null");
            return (Criteria) this;
        }

        public Criteria andNewvqaEqualTo(String value) {
            addCriterion("newVQA =", value, "newvqa");
            return (Criteria) this;
        }

        public Criteria andNewvqaNotEqualTo(String value) {
            addCriterion("newVQA <>", value, "newvqa");
            return (Criteria) this;
        }

        public Criteria andNewvqaGreaterThan(String value) {
            addCriterion("newVQA >", value, "newvqa");
            return (Criteria) this;
        }

        public Criteria andNewvqaGreaterThanOrEqualTo(String value) {
            addCriterion("newVQA >=", value, "newvqa");
            return (Criteria) this;
        }

        public Criteria andNewvqaLessThan(String value) {
            addCriterion("newVQA <", value, "newvqa");
            return (Criteria) this;
        }

        public Criteria andNewvqaLessThanOrEqualTo(String value) {
            addCriterion("newVQA <=", value, "newvqa");
            return (Criteria) this;
        }

        public Criteria andNewvqaLike(String value) {
            addCriterion("newVQA like", value, "newvqa");
            return (Criteria) this;
        }

        public Criteria andNewvqaNotLike(String value) {
            addCriterion("newVQA not like", value, "newvqa");
            return (Criteria) this;
        }

        public Criteria andNewvqaIn(List<String> values) {
            addCriterion("newVQA in", values, "newvqa");
            return (Criteria) this;
        }

        public Criteria andNewvqaNotIn(List<String> values) {
            addCriterion("newVQA not in", values, "newvqa");
            return (Criteria) this;
        }

        public Criteria andNewvqaBetween(String value1, String value2) {
            addCriterion("newVQA between", value1, value2, "newvqa");
            return (Criteria) this;
        }

        public Criteria andNewvqaNotBetween(String value1, String value2) {
            addCriterion("newVQA not between", value1, value2, "newvqa");
            return (Criteria) this;
        }

        public Criteria andNewnurtureareaIsNull() {
            addCriterion("newNurtureArea is null");
            return (Criteria) this;
        }

        public Criteria andNewnurtureareaIsNotNull() {
            addCriterion("newNurtureArea is not null");
            return (Criteria) this;
        }

        public Criteria andNewnurtureareaEqualTo(String value) {
            addCriterion("newNurtureArea =", value, "newnurturearea");
            return (Criteria) this;
        }

        public Criteria andNewnurtureareaNotEqualTo(String value) {
            addCriterion("newNurtureArea <>", value, "newnurturearea");
            return (Criteria) this;
        }

        public Criteria andNewnurtureareaGreaterThan(String value) {
            addCriterion("newNurtureArea >", value, "newnurturearea");
            return (Criteria) this;
        }

        public Criteria andNewnurtureareaGreaterThanOrEqualTo(String value) {
            addCriterion("newNurtureArea >=", value, "newnurturearea");
            return (Criteria) this;
        }

        public Criteria andNewnurtureareaLessThan(String value) {
            addCriterion("newNurtureArea <", value, "newnurturearea");
            return (Criteria) this;
        }

        public Criteria andNewnurtureareaLessThanOrEqualTo(String value) {
            addCriterion("newNurtureArea <=", value, "newnurturearea");
            return (Criteria) this;
        }

        public Criteria andNewnurtureareaLike(String value) {
            addCriterion("newNurtureArea like", value, "newnurturearea");
            return (Criteria) this;
        }

        public Criteria andNewnurtureareaNotLike(String value) {
            addCriterion("newNurtureArea not like", value, "newnurturearea");
            return (Criteria) this;
        }

        public Criteria andNewnurtureareaIn(List<String> values) {
            addCriterion("newNurtureArea in", values, "newnurturearea");
            return (Criteria) this;
        }

        public Criteria andNewnurtureareaNotIn(List<String> values) {
            addCriterion("newNurtureArea not in", values, "newnurturearea");
            return (Criteria) this;
        }

        public Criteria andNewnurtureareaBetween(String value1, String value2) {
            addCriterion("newNurtureArea between", value1, value2, "newnurturearea");
            return (Criteria) this;
        }

        public Criteria andNewnurtureareaNotBetween(String value1, String value2) {
            addCriterion("newNurtureArea not between", value1, value2, "newnurturearea");
            return (Criteria) this;
        }

        public Criteria andNewmanageareaIsNull() {
            addCriterion("newManageArea is null");
            return (Criteria) this;
        }

        public Criteria andNewmanageareaIsNotNull() {
            addCriterion("newManageArea is not null");
            return (Criteria) this;
        }

        public Criteria andNewmanageareaEqualTo(String value) {
            addCriterion("newManageArea =", value, "newmanagearea");
            return (Criteria) this;
        }

        public Criteria andNewmanageareaNotEqualTo(String value) {
            addCriterion("newManageArea <>", value, "newmanagearea");
            return (Criteria) this;
        }

        public Criteria andNewmanageareaGreaterThan(String value) {
            addCriterion("newManageArea >", value, "newmanagearea");
            return (Criteria) this;
        }

        public Criteria andNewmanageareaGreaterThanOrEqualTo(String value) {
            addCriterion("newManageArea >=", value, "newmanagearea");
            return (Criteria) this;
        }

        public Criteria andNewmanageareaLessThan(String value) {
            addCriterion("newManageArea <", value, "newmanagearea");
            return (Criteria) this;
        }

        public Criteria andNewmanageareaLessThanOrEqualTo(String value) {
            addCriterion("newManageArea <=", value, "newmanagearea");
            return (Criteria) this;
        }

        public Criteria andNewmanageareaLike(String value) {
            addCriterion("newManageArea like", value, "newmanagearea");
            return (Criteria) this;
        }

        public Criteria andNewmanageareaNotLike(String value) {
            addCriterion("newManageArea not like", value, "newmanagearea");
            return (Criteria) this;
        }

        public Criteria andNewmanageareaIn(List<String> values) {
            addCriterion("newManageArea in", values, "newmanagearea");
            return (Criteria) this;
        }

        public Criteria andNewmanageareaNotIn(List<String> values) {
            addCriterion("newManageArea not in", values, "newmanagearea");
            return (Criteria) this;
        }

        public Criteria andNewmanageareaBetween(String value1, String value2) {
            addCriterion("newManageArea between", value1, value2, "newmanagearea");
            return (Criteria) this;
        }

        public Criteria andNewmanageareaNotBetween(String value1, String value2) {
            addCriterion("newManageArea not between", value1, value2, "newmanagearea");
            return (Criteria) this;
        }

        public Criteria andNewvrqrIsNull() {
            addCriterion("newVRQR is null");
            return (Criteria) this;
        }

        public Criteria andNewvrqrIsNotNull() {
            addCriterion("newVRQR is not null");
            return (Criteria) this;
        }

        public Criteria andNewvrqrEqualTo(String value) {
            addCriterion("newVRQR =", value, "newvrqr");
            return (Criteria) this;
        }

        public Criteria andNewvrqrNotEqualTo(String value) {
            addCriterion("newVRQR <>", value, "newvrqr");
            return (Criteria) this;
        }

        public Criteria andNewvrqrGreaterThan(String value) {
            addCriterion("newVRQR >", value, "newvrqr");
            return (Criteria) this;
        }

        public Criteria andNewvrqrGreaterThanOrEqualTo(String value) {
            addCriterion("newVRQR >=", value, "newvrqr");
            return (Criteria) this;
        }

        public Criteria andNewvrqrLessThan(String value) {
            addCriterion("newVRQR <", value, "newvrqr");
            return (Criteria) this;
        }

        public Criteria andNewvrqrLessThanOrEqualTo(String value) {
            addCriterion("newVRQR <=", value, "newvrqr");
            return (Criteria) this;
        }

        public Criteria andNewvrqrLike(String value) {
            addCriterion("newVRQR like", value, "newvrqr");
            return (Criteria) this;
        }

        public Criteria andNewvrqrNotLike(String value) {
            addCriterion("newVRQR not like", value, "newvrqr");
            return (Criteria) this;
        }

        public Criteria andNewvrqrIn(List<String> values) {
            addCriterion("newVRQR in", values, "newvrqr");
            return (Criteria) this;
        }

        public Criteria andNewvrqrNotIn(List<String> values) {
            addCriterion("newVRQR not in", values, "newvrqr");
            return (Criteria) this;
        }

        public Criteria andNewvrqrBetween(String value1, String value2) {
            addCriterion("newVRQR between", value1, value2, "newvrqr");
            return (Criteria) this;
        }

        public Criteria andNewvrqrNotBetween(String value1, String value2) {
            addCriterion("newVRQR not between", value1, value2, "newvrqr");
            return (Criteria) this;
        }

        public Criteria andNewnurtureIsNull() {
            addCriterion("newNurture is null");
            return (Criteria) this;
        }

        public Criteria andNewnurtureIsNotNull() {
            addCriterion("newNurture is not null");
            return (Criteria) this;
        }

        public Criteria andNewnurtureEqualTo(String value) {
            addCriterion("newNurture =", value, "newnurture");
            return (Criteria) this;
        }

        public Criteria andNewnurtureNotEqualTo(String value) {
            addCriterion("newNurture <>", value, "newnurture");
            return (Criteria) this;
        }

        public Criteria andNewnurtureGreaterThan(String value) {
            addCriterion("newNurture >", value, "newnurture");
            return (Criteria) this;
        }

        public Criteria andNewnurtureGreaterThanOrEqualTo(String value) {
            addCriterion("newNurture >=", value, "newnurture");
            return (Criteria) this;
        }

        public Criteria andNewnurtureLessThan(String value) {
            addCriterion("newNurture <", value, "newnurture");
            return (Criteria) this;
        }

        public Criteria andNewnurtureLessThanOrEqualTo(String value) {
            addCriterion("newNurture <=", value, "newnurture");
            return (Criteria) this;
        }

        public Criteria andNewnurtureLike(String value) {
            addCriterion("newNurture like", value, "newnurture");
            return (Criteria) this;
        }

        public Criteria andNewnurtureNotLike(String value) {
            addCriterion("newNurture not like", value, "newnurture");
            return (Criteria) this;
        }

        public Criteria andNewnurtureIn(List<String> values) {
            addCriterion("newNurture in", values, "newnurture");
            return (Criteria) this;
        }

        public Criteria andNewnurtureNotIn(List<String> values) {
            addCriterion("newNurture not in", values, "newnurture");
            return (Criteria) this;
        }

        public Criteria andNewnurtureBetween(String value1, String value2) {
            addCriterion("newNurture between", value1, value2, "newnurture");
            return (Criteria) this;
        }

        public Criteria andNewnurtureNotBetween(String value1, String value2) {
            addCriterion("newNurture not between", value1, value2, "newnurture");
            return (Criteria) this;
        }

        public Criteria andNewmanageIsNull() {
            addCriterion("newManage is null");
            return (Criteria) this;
        }

        public Criteria andNewmanageIsNotNull() {
            addCriterion("newManage is not null");
            return (Criteria) this;
        }

        public Criteria andNewmanageEqualTo(String value) {
            addCriterion("newManage =", value, "newmanage");
            return (Criteria) this;
        }

        public Criteria andNewmanageNotEqualTo(String value) {
            addCriterion("newManage <>", value, "newmanage");
            return (Criteria) this;
        }

        public Criteria andNewmanageGreaterThan(String value) {
            addCriterion("newManage >", value, "newmanage");
            return (Criteria) this;
        }

        public Criteria andNewmanageGreaterThanOrEqualTo(String value) {
            addCriterion("newManage >=", value, "newmanage");
            return (Criteria) this;
        }

        public Criteria andNewmanageLessThan(String value) {
            addCriterion("newManage <", value, "newmanage");
            return (Criteria) this;
        }

        public Criteria andNewmanageLessThanOrEqualTo(String value) {
            addCriterion("newManage <=", value, "newmanage");
            return (Criteria) this;
        }

        public Criteria andNewmanageLike(String value) {
            addCriterion("newManage like", value, "newmanage");
            return (Criteria) this;
        }

        public Criteria andNewmanageNotLike(String value) {
            addCriterion("newManage not like", value, "newmanage");
            return (Criteria) this;
        }

        public Criteria andNewmanageIn(List<String> values) {
            addCriterion("newManage in", values, "newmanage");
            return (Criteria) this;
        }

        public Criteria andNewmanageNotIn(List<String> values) {
            addCriterion("newManage not in", values, "newmanage");
            return (Criteria) this;
        }

        public Criteria andNewmanageBetween(String value1, String value2) {
            addCriterion("newManage between", value1, value2, "newmanage");
            return (Criteria) this;
        }

        public Criteria andNewmanageNotBetween(String value1, String value2) {
            addCriterion("newManage not between", value1, value2, "newmanage");
            return (Criteria) this;
        }

        public Criteria andPatchdesignareaIsNull() {
            addCriterion("patchDesignArea is null");
            return (Criteria) this;
        }

        public Criteria andPatchdesignareaIsNotNull() {
            addCriterion("patchDesignArea is not null");
            return (Criteria) this;
        }

        public Criteria andPatchdesignareaEqualTo(String value) {
            addCriterion("patchDesignArea =", value, "patchdesignarea");
            return (Criteria) this;
        }

        public Criteria andPatchdesignareaNotEqualTo(String value) {
            addCriterion("patchDesignArea <>", value, "patchdesignarea");
            return (Criteria) this;
        }

        public Criteria andPatchdesignareaGreaterThan(String value) {
            addCriterion("patchDesignArea >", value, "patchdesignarea");
            return (Criteria) this;
        }

        public Criteria andPatchdesignareaGreaterThanOrEqualTo(String value) {
            addCriterion("patchDesignArea >=", value, "patchdesignarea");
            return (Criteria) this;
        }

        public Criteria andPatchdesignareaLessThan(String value) {
            addCriterion("patchDesignArea <", value, "patchdesignarea");
            return (Criteria) this;
        }

        public Criteria andPatchdesignareaLessThanOrEqualTo(String value) {
            addCriterion("patchDesignArea <=", value, "patchdesignarea");
            return (Criteria) this;
        }

        public Criteria andPatchdesignareaLike(String value) {
            addCriterion("patchDesignArea like", value, "patchdesignarea");
            return (Criteria) this;
        }

        public Criteria andPatchdesignareaNotLike(String value) {
            addCriterion("patchDesignArea not like", value, "patchdesignarea");
            return (Criteria) this;
        }

        public Criteria andPatchdesignareaIn(List<String> values) {
            addCriterion("patchDesignArea in", values, "patchdesignarea");
            return (Criteria) this;
        }

        public Criteria andPatchdesignareaNotIn(List<String> values) {
            addCriterion("patchDesignArea not in", values, "patchdesignarea");
            return (Criteria) this;
        }

        public Criteria andPatchdesignareaBetween(String value1, String value2) {
            addCriterion("patchDesignArea between", value1, value2, "patchdesignarea");
            return (Criteria) this;
        }

        public Criteria andPatchdesignareaNotBetween(String value1, String value2) {
            addCriterion("patchDesignArea not between", value1, value2, "patchdesignarea");
            return (Criteria) this;
        }

        public Criteria andPatchiqrIsNull() {
            addCriterion("patchIQR is null");
            return (Criteria) this;
        }

        public Criteria andPatchiqrIsNotNull() {
            addCriterion("patchIQR is not null");
            return (Criteria) this;
        }

        public Criteria andPatchiqrEqualTo(String value) {
            addCriterion("patchIQR =", value, "patchiqr");
            return (Criteria) this;
        }

        public Criteria andPatchiqrNotEqualTo(String value) {
            addCriterion("patchIQR <>", value, "patchiqr");
            return (Criteria) this;
        }

        public Criteria andPatchiqrGreaterThan(String value) {
            addCriterion("patchIQR >", value, "patchiqr");
            return (Criteria) this;
        }

        public Criteria andPatchiqrGreaterThanOrEqualTo(String value) {
            addCriterion("patchIQR >=", value, "patchiqr");
            return (Criteria) this;
        }

        public Criteria andPatchiqrLessThan(String value) {
            addCriterion("patchIQR <", value, "patchiqr");
            return (Criteria) this;
        }

        public Criteria andPatchiqrLessThanOrEqualTo(String value) {
            addCriterion("patchIQR <=", value, "patchiqr");
            return (Criteria) this;
        }

        public Criteria andPatchiqrLike(String value) {
            addCriterion("patchIQR like", value, "patchiqr");
            return (Criteria) this;
        }

        public Criteria andPatchiqrNotLike(String value) {
            addCriterion("patchIQR not like", value, "patchiqr");
            return (Criteria) this;
        }

        public Criteria andPatchiqrIn(List<String> values) {
            addCriterion("patchIQR in", values, "patchiqr");
            return (Criteria) this;
        }

        public Criteria andPatchiqrNotIn(List<String> values) {
            addCriterion("patchIQR not in", values, "patchiqr");
            return (Criteria) this;
        }

        public Criteria andPatchiqrBetween(String value1, String value2) {
            addCriterion("patchIQR between", value1, value2, "patchiqr");
            return (Criteria) this;
        }

        public Criteria andPatchiqrNotBetween(String value1, String value2) {
            addCriterion("patchIQR not between", value1, value2, "patchiqr");
            return (Criteria) this;
        }

        public Criteria andPatchverifyareaIsNull() {
            addCriterion("patchVerifyArea is null");
            return (Criteria) this;
        }

        public Criteria andPatchverifyareaIsNotNull() {
            addCriterion("patchVerifyArea is not null");
            return (Criteria) this;
        }

        public Criteria andPatchverifyareaEqualTo(String value) {
            addCriterion("patchVerifyArea =", value, "patchverifyarea");
            return (Criteria) this;
        }

        public Criteria andPatchverifyareaNotEqualTo(String value) {
            addCriterion("patchVerifyArea <>", value, "patchverifyarea");
            return (Criteria) this;
        }

        public Criteria andPatchverifyareaGreaterThan(String value) {
            addCriterion("patchVerifyArea >", value, "patchverifyarea");
            return (Criteria) this;
        }

        public Criteria andPatchverifyareaGreaterThanOrEqualTo(String value) {
            addCriterion("patchVerifyArea >=", value, "patchverifyarea");
            return (Criteria) this;
        }

        public Criteria andPatchverifyareaLessThan(String value) {
            addCriterion("patchVerifyArea <", value, "patchverifyarea");
            return (Criteria) this;
        }

        public Criteria andPatchverifyareaLessThanOrEqualTo(String value) {
            addCriterion("patchVerifyArea <=", value, "patchverifyarea");
            return (Criteria) this;
        }

        public Criteria andPatchverifyareaLike(String value) {
            addCriterion("patchVerifyArea like", value, "patchverifyarea");
            return (Criteria) this;
        }

        public Criteria andPatchverifyareaNotLike(String value) {
            addCriterion("patchVerifyArea not like", value, "patchverifyarea");
            return (Criteria) this;
        }

        public Criteria andPatchverifyareaIn(List<String> values) {
            addCriterion("patchVerifyArea in", values, "patchverifyarea");
            return (Criteria) this;
        }

        public Criteria andPatchverifyareaNotIn(List<String> values) {
            addCriterion("patchVerifyArea not in", values, "patchverifyarea");
            return (Criteria) this;
        }

        public Criteria andPatchverifyareaBetween(String value1, String value2) {
            addCriterion("patchVerifyArea between", value1, value2, "patchverifyarea");
            return (Criteria) this;
        }

        public Criteria andPatchverifyareaNotBetween(String value1, String value2) {
            addCriterion("patchVerifyArea not between", value1, value2, "patchverifyarea");
            return (Criteria) this;
        }

        public Criteria andPatchvqaIsNull() {
            addCriterion("patchVQA is null");
            return (Criteria) this;
        }

        public Criteria andPatchvqaIsNotNull() {
            addCriterion("patchVQA is not null");
            return (Criteria) this;
        }

        public Criteria andPatchvqaEqualTo(String value) {
            addCriterion("patchVQA =", value, "patchvqa");
            return (Criteria) this;
        }

        public Criteria andPatchvqaNotEqualTo(String value) {
            addCriterion("patchVQA <>", value, "patchvqa");
            return (Criteria) this;
        }

        public Criteria andPatchvqaGreaterThan(String value) {
            addCriterion("patchVQA >", value, "patchvqa");
            return (Criteria) this;
        }

        public Criteria andPatchvqaGreaterThanOrEqualTo(String value) {
            addCriterion("patchVQA >=", value, "patchvqa");
            return (Criteria) this;
        }

        public Criteria andPatchvqaLessThan(String value) {
            addCriterion("patchVQA <", value, "patchvqa");
            return (Criteria) this;
        }

        public Criteria andPatchvqaLessThanOrEqualTo(String value) {
            addCriterion("patchVQA <=", value, "patchvqa");
            return (Criteria) this;
        }

        public Criteria andPatchvqaLike(String value) {
            addCriterion("patchVQA like", value, "patchvqa");
            return (Criteria) this;
        }

        public Criteria andPatchvqaNotLike(String value) {
            addCriterion("patchVQA not like", value, "patchvqa");
            return (Criteria) this;
        }

        public Criteria andPatchvqaIn(List<String> values) {
            addCriterion("patchVQA in", values, "patchvqa");
            return (Criteria) this;
        }

        public Criteria andPatchvqaNotIn(List<String> values) {
            addCriterion("patchVQA not in", values, "patchvqa");
            return (Criteria) this;
        }

        public Criteria andPatchvqaBetween(String value1, String value2) {
            addCriterion("patchVQA between", value1, value2, "patchvqa");
            return (Criteria) this;
        }

        public Criteria andPatchvqaNotBetween(String value1, String value2) {
            addCriterion("patchVQA not between", value1, value2, "patchvqa");
            return (Criteria) this;
        }

        public Criteria andPatchnurtureareaIsNull() {
            addCriterion("patchNurtureArea is null");
            return (Criteria) this;
        }

        public Criteria andPatchnurtureareaIsNotNull() {
            addCriterion("patchNurtureArea is not null");
            return (Criteria) this;
        }

        public Criteria andPatchnurtureareaEqualTo(String value) {
            addCriterion("patchNurtureArea =", value, "patchnurturearea");
            return (Criteria) this;
        }

        public Criteria andPatchnurtureareaNotEqualTo(String value) {
            addCriterion("patchNurtureArea <>", value, "patchnurturearea");
            return (Criteria) this;
        }

        public Criteria andPatchnurtureareaGreaterThan(String value) {
            addCriterion("patchNurtureArea >", value, "patchnurturearea");
            return (Criteria) this;
        }

        public Criteria andPatchnurtureareaGreaterThanOrEqualTo(String value) {
            addCriterion("patchNurtureArea >=", value, "patchnurturearea");
            return (Criteria) this;
        }

        public Criteria andPatchnurtureareaLessThan(String value) {
            addCriterion("patchNurtureArea <", value, "patchnurturearea");
            return (Criteria) this;
        }

        public Criteria andPatchnurtureareaLessThanOrEqualTo(String value) {
            addCriterion("patchNurtureArea <=", value, "patchnurturearea");
            return (Criteria) this;
        }

        public Criteria andPatchnurtureareaLike(String value) {
            addCriterion("patchNurtureArea like", value, "patchnurturearea");
            return (Criteria) this;
        }

        public Criteria andPatchnurtureareaNotLike(String value) {
            addCriterion("patchNurtureArea not like", value, "patchnurturearea");
            return (Criteria) this;
        }

        public Criteria andPatchnurtureareaIn(List<String> values) {
            addCriterion("patchNurtureArea in", values, "patchnurturearea");
            return (Criteria) this;
        }

        public Criteria andPatchnurtureareaNotIn(List<String> values) {
            addCriterion("patchNurtureArea not in", values, "patchnurturearea");
            return (Criteria) this;
        }

        public Criteria andPatchnurtureareaBetween(String value1, String value2) {
            addCriterion("patchNurtureArea between", value1, value2, "patchnurturearea");
            return (Criteria) this;
        }

        public Criteria andPatchnurtureareaNotBetween(String value1, String value2) {
            addCriterion("patchNurtureArea not between", value1, value2, "patchnurturearea");
            return (Criteria) this;
        }

        public Criteria andPatchmanageareaIsNull() {
            addCriterion("patchManageArea is null");
            return (Criteria) this;
        }

        public Criteria andPatchmanageareaIsNotNull() {
            addCriterion("patchManageArea is not null");
            return (Criteria) this;
        }

        public Criteria andPatchmanageareaEqualTo(String value) {
            addCriterion("patchManageArea =", value, "patchmanagearea");
            return (Criteria) this;
        }

        public Criteria andPatchmanageareaNotEqualTo(String value) {
            addCriterion("patchManageArea <>", value, "patchmanagearea");
            return (Criteria) this;
        }

        public Criteria andPatchmanageareaGreaterThan(String value) {
            addCriterion("patchManageArea >", value, "patchmanagearea");
            return (Criteria) this;
        }

        public Criteria andPatchmanageareaGreaterThanOrEqualTo(String value) {
            addCriterion("patchManageArea >=", value, "patchmanagearea");
            return (Criteria) this;
        }

        public Criteria andPatchmanageareaLessThan(String value) {
            addCriterion("patchManageArea <", value, "patchmanagearea");
            return (Criteria) this;
        }

        public Criteria andPatchmanageareaLessThanOrEqualTo(String value) {
            addCriterion("patchManageArea <=", value, "patchmanagearea");
            return (Criteria) this;
        }

        public Criteria andPatchmanageareaLike(String value) {
            addCriterion("patchManageArea like", value, "patchmanagearea");
            return (Criteria) this;
        }

        public Criteria andPatchmanageareaNotLike(String value) {
            addCriterion("patchManageArea not like", value, "patchmanagearea");
            return (Criteria) this;
        }

        public Criteria andPatchmanageareaIn(List<String> values) {
            addCriterion("patchManageArea in", values, "patchmanagearea");
            return (Criteria) this;
        }

        public Criteria andPatchmanageareaNotIn(List<String> values) {
            addCriterion("patchManageArea not in", values, "patchmanagearea");
            return (Criteria) this;
        }

        public Criteria andPatchmanageareaBetween(String value1, String value2) {
            addCriterion("patchManageArea between", value1, value2, "patchmanagearea");
            return (Criteria) this;
        }

        public Criteria andPatchmanageareaNotBetween(String value1, String value2) {
            addCriterion("patchManageArea not between", value1, value2, "patchmanagearea");
            return (Criteria) this;
        }

        public Criteria andPatchvrqrIsNull() {
            addCriterion("patchVRQR is null");
            return (Criteria) this;
        }

        public Criteria andPatchvrqrIsNotNull() {
            addCriterion("patchVRQR is not null");
            return (Criteria) this;
        }

        public Criteria andPatchvrqrEqualTo(String value) {
            addCriterion("patchVRQR =", value, "patchvrqr");
            return (Criteria) this;
        }

        public Criteria andPatchvrqrNotEqualTo(String value) {
            addCriterion("patchVRQR <>", value, "patchvrqr");
            return (Criteria) this;
        }

        public Criteria andPatchvrqrGreaterThan(String value) {
            addCriterion("patchVRQR >", value, "patchvrqr");
            return (Criteria) this;
        }

        public Criteria andPatchvrqrGreaterThanOrEqualTo(String value) {
            addCriterion("patchVRQR >=", value, "patchvrqr");
            return (Criteria) this;
        }

        public Criteria andPatchvrqrLessThan(String value) {
            addCriterion("patchVRQR <", value, "patchvrqr");
            return (Criteria) this;
        }

        public Criteria andPatchvrqrLessThanOrEqualTo(String value) {
            addCriterion("patchVRQR <=", value, "patchvrqr");
            return (Criteria) this;
        }

        public Criteria andPatchvrqrLike(String value) {
            addCriterion("patchVRQR like", value, "patchvrqr");
            return (Criteria) this;
        }

        public Criteria andPatchvrqrNotLike(String value) {
            addCriterion("patchVRQR not like", value, "patchvrqr");
            return (Criteria) this;
        }

        public Criteria andPatchvrqrIn(List<String> values) {
            addCriterion("patchVRQR in", values, "patchvrqr");
            return (Criteria) this;
        }

        public Criteria andPatchvrqrNotIn(List<String> values) {
            addCriterion("patchVRQR not in", values, "patchvrqr");
            return (Criteria) this;
        }

        public Criteria andPatchvrqrBetween(String value1, String value2) {
            addCriterion("patchVRQR between", value1, value2, "patchvrqr");
            return (Criteria) this;
        }

        public Criteria andPatchvrqrNotBetween(String value1, String value2) {
            addCriterion("patchVRQR not between", value1, value2, "patchvrqr");
            return (Criteria) this;
        }

        public Criteria andPatchnurtureIsNull() {
            addCriterion("patchNurture is null");
            return (Criteria) this;
        }

        public Criteria andPatchnurtureIsNotNull() {
            addCriterion("patchNurture is not null");
            return (Criteria) this;
        }

        public Criteria andPatchnurtureEqualTo(String value) {
            addCriterion("patchNurture =", value, "patchnurture");
            return (Criteria) this;
        }

        public Criteria andPatchnurtureNotEqualTo(String value) {
            addCriterion("patchNurture <>", value, "patchnurture");
            return (Criteria) this;
        }

        public Criteria andPatchnurtureGreaterThan(String value) {
            addCriterion("patchNurture >", value, "patchnurture");
            return (Criteria) this;
        }

        public Criteria andPatchnurtureGreaterThanOrEqualTo(String value) {
            addCriterion("patchNurture >=", value, "patchnurture");
            return (Criteria) this;
        }

        public Criteria andPatchnurtureLessThan(String value) {
            addCriterion("patchNurture <", value, "patchnurture");
            return (Criteria) this;
        }

        public Criteria andPatchnurtureLessThanOrEqualTo(String value) {
            addCriterion("patchNurture <=", value, "patchnurture");
            return (Criteria) this;
        }

        public Criteria andPatchnurtureLike(String value) {
            addCriterion("patchNurture like", value, "patchnurture");
            return (Criteria) this;
        }

        public Criteria andPatchnurtureNotLike(String value) {
            addCriterion("patchNurture not like", value, "patchnurture");
            return (Criteria) this;
        }

        public Criteria andPatchnurtureIn(List<String> values) {
            addCriterion("patchNurture in", values, "patchnurture");
            return (Criteria) this;
        }

        public Criteria andPatchnurtureNotIn(List<String> values) {
            addCriterion("patchNurture not in", values, "patchnurture");
            return (Criteria) this;
        }

        public Criteria andPatchnurtureBetween(String value1, String value2) {
            addCriterion("patchNurture between", value1, value2, "patchnurture");
            return (Criteria) this;
        }

        public Criteria andPatchnurtureNotBetween(String value1, String value2) {
            addCriterion("patchNurture not between", value1, value2, "patchnurture");
            return (Criteria) this;
        }

        public Criteria andPatchmanageIsNull() {
            addCriterion("patchManage is null");
            return (Criteria) this;
        }

        public Criteria andPatchmanageIsNotNull() {
            addCriterion("patchManage is not null");
            return (Criteria) this;
        }

        public Criteria andPatchmanageEqualTo(String value) {
            addCriterion("patchManage =", value, "patchmanage");
            return (Criteria) this;
        }

        public Criteria andPatchmanageNotEqualTo(String value) {
            addCriterion("patchManage <>", value, "patchmanage");
            return (Criteria) this;
        }

        public Criteria andPatchmanageGreaterThan(String value) {
            addCriterion("patchManage >", value, "patchmanage");
            return (Criteria) this;
        }

        public Criteria andPatchmanageGreaterThanOrEqualTo(String value) {
            addCriterion("patchManage >=", value, "patchmanage");
            return (Criteria) this;
        }

        public Criteria andPatchmanageLessThan(String value) {
            addCriterion("patchManage <", value, "patchmanage");
            return (Criteria) this;
        }

        public Criteria andPatchmanageLessThanOrEqualTo(String value) {
            addCriterion("patchManage <=", value, "patchmanage");
            return (Criteria) this;
        }

        public Criteria andPatchmanageLike(String value) {
            addCriterion("patchManage like", value, "patchmanage");
            return (Criteria) this;
        }

        public Criteria andPatchmanageNotLike(String value) {
            addCriterion("patchManage not like", value, "patchmanage");
            return (Criteria) this;
        }

        public Criteria andPatchmanageIn(List<String> values) {
            addCriterion("patchManage in", values, "patchmanage");
            return (Criteria) this;
        }

        public Criteria andPatchmanageNotIn(List<String> values) {
            addCriterion("patchManage not in", values, "patchmanage");
            return (Criteria) this;
        }

        public Criteria andPatchmanageBetween(String value1, String value2) {
            addCriterion("patchManage between", value1, value2, "patchmanage");
            return (Criteria) this;
        }

        public Criteria andPatchmanageNotBetween(String value1, String value2) {
            addCriterion("patchManage not between", value1, value2, "patchmanage");
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