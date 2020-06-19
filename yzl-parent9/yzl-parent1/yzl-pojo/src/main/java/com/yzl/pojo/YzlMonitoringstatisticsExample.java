package com.yzl.pojo;

import java.util.ArrayList;
import java.util.List;

public class YzlMonitoringstatisticsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public YzlMonitoringstatisticsExample() {
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

        public Criteria andDidIsNull() {
            addCriterion("did is null");
            return (Criteria) this;
        }

        public Criteria andDidIsNotNull() {
            addCriterion("did is not null");
            return (Criteria) this;
        }

        public Criteria andDidEqualTo(Long value) {
            addCriterion("did =", value, "did");
            return (Criteria) this;
        }

        public Criteria andDidNotEqualTo(Long value) {
            addCriterion("did <>", value, "did");
            return (Criteria) this;
        }

        public Criteria andDidGreaterThan(Long value) {
            addCriterion("did >", value, "did");
            return (Criteria) this;
        }

        public Criteria andDidGreaterThanOrEqualTo(Long value) {
            addCriterion("did >=", value, "did");
            return (Criteria) this;
        }

        public Criteria andDidLessThan(Long value) {
            addCriterion("did <", value, "did");
            return (Criteria) this;
        }

        public Criteria andDidLessThanOrEqualTo(Long value) {
            addCriterion("did <=", value, "did");
            return (Criteria) this;
        }

        public Criteria andDidIn(List<Long> values) {
            addCriterion("did in", values, "did");
            return (Criteria) this;
        }

        public Criteria andDidNotIn(List<Long> values) {
            addCriterion("did not in", values, "did");
            return (Criteria) this;
        }

        public Criteria andDidBetween(Long value1, Long value2) {
            addCriterion("did between", value1, value2, "did");
            return (Criteria) this;
        }

        public Criteria andDidNotBetween(Long value1, Long value2) {
            addCriterion("did not between", value1, value2, "did");
            return (Criteria) this;
        }

        public Criteria andEidIsNull() {
            addCriterion("eid is null");
            return (Criteria) this;
        }

        public Criteria andEidIsNotNull() {
            addCriterion("eid is not null");
            return (Criteria) this;
        }

        public Criteria andEidEqualTo(Long value) {
            addCriterion("eid =", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidNotEqualTo(Long value) {
            addCriterion("eid <>", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidGreaterThan(Long value) {
            addCriterion("eid >", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidGreaterThanOrEqualTo(Long value) {
            addCriterion("eid >=", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidLessThan(Long value) {
            addCriterion("eid <", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidLessThanOrEqualTo(Long value) {
            addCriterion("eid <=", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidIn(List<Long> values) {
            addCriterion("eid in", values, "eid");
            return (Criteria) this;
        }

        public Criteria andEidNotIn(List<Long> values) {
            addCriterion("eid not in", values, "eid");
            return (Criteria) this;
        }

        public Criteria andEidBetween(Long value1, Long value2) {
            addCriterion("eid between", value1, value2, "eid");
            return (Criteria) this;
        }

        public Criteria andEidNotBetween(Long value1, Long value2) {
            addCriterion("eid not between", value1, value2, "eid");
            return (Criteria) this;
        }

        public Criteria andTidIsNull() {
            addCriterion("tid is null");
            return (Criteria) this;
        }

        public Criteria andTidIsNotNull() {
            addCriterion("tid is not null");
            return (Criteria) this;
        }

        public Criteria andTidEqualTo(String value) {
            addCriterion("tid =", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotEqualTo(String value) {
            addCriterion("tid <>", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidGreaterThan(String value) {
            addCriterion("tid >", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidGreaterThanOrEqualTo(String value) {
            addCriterion("tid >=", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidLessThan(String value) {
            addCriterion("tid <", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidLessThanOrEqualTo(String value) {
            addCriterion("tid <=", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidLike(String value) {
            addCriterion("tid like", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotLike(String value) {
            addCriterion("tid not like", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidIn(List<String> values) {
            addCriterion("tid in", values, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotIn(List<String> values) {
            addCriterion("tid not in", values, "tid");
            return (Criteria) this;
        }

        public Criteria andTidBetween(String value1, String value2) {
            addCriterion("tid between", value1, value2, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotBetween(String value1, String value2) {
            addCriterion("tid not between", value1, value2, "tid");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("time is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("time is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(String value) {
            addCriterion("time =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(String value) {
            addCriterion("time <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(String value) {
            addCriterion("time >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(String value) {
            addCriterion("time >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(String value) {
            addCriterion("time <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(String value) {
            addCriterion("time <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLike(String value) {
            addCriterion("time like", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotLike(String value) {
            addCriterion("time not like", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<String> values) {
            addCriterion("time in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<String> values) {
            addCriterion("time not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(String value1, String value2) {
            addCriterion("time between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(String value1, String value2) {
            addCriterion("time not between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andInspectionreportareaIsNull() {
            addCriterion("inspectionReportArea is null");
            return (Criteria) this;
        }

        public Criteria andInspectionreportareaIsNotNull() {
            addCriterion("inspectionReportArea is not null");
            return (Criteria) this;
        }

        public Criteria andInspectionreportareaEqualTo(String value) {
            addCriterion("inspectionReportArea =", value, "inspectionreportarea");
            return (Criteria) this;
        }

        public Criteria andInspectionreportareaNotEqualTo(String value) {
            addCriterion("inspectionReportArea <>", value, "inspectionreportarea");
            return (Criteria) this;
        }

        public Criteria andInspectionreportareaGreaterThan(String value) {
            addCriterion("inspectionReportArea >", value, "inspectionreportarea");
            return (Criteria) this;
        }

        public Criteria andInspectionreportareaGreaterThanOrEqualTo(String value) {
            addCriterion("inspectionReportArea >=", value, "inspectionreportarea");
            return (Criteria) this;
        }

        public Criteria andInspectionreportareaLessThan(String value) {
            addCriterion("inspectionReportArea <", value, "inspectionreportarea");
            return (Criteria) this;
        }

        public Criteria andInspectionreportareaLessThanOrEqualTo(String value) {
            addCriterion("inspectionReportArea <=", value, "inspectionreportarea");
            return (Criteria) this;
        }

        public Criteria andInspectionreportareaLike(String value) {
            addCriterion("inspectionReportArea like", value, "inspectionreportarea");
            return (Criteria) this;
        }

        public Criteria andInspectionreportareaNotLike(String value) {
            addCriterion("inspectionReportArea not like", value, "inspectionreportarea");
            return (Criteria) this;
        }

        public Criteria andInspectionreportareaIn(List<String> values) {
            addCriterion("inspectionReportArea in", values, "inspectionreportarea");
            return (Criteria) this;
        }

        public Criteria andInspectionreportareaNotIn(List<String> values) {
            addCriterion("inspectionReportArea not in", values, "inspectionreportarea");
            return (Criteria) this;
        }

        public Criteria andInspectionreportareaBetween(String value1, String value2) {
            addCriterion("inspectionReportArea between", value1, value2, "inspectionreportarea");
            return (Criteria) this;
        }

        public Criteria andInspectionreportareaNotBetween(String value1, String value2) {
            addCriterion("inspectionReportArea not between", value1, value2, "inspectionreportarea");
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

        public Criteria andCertifiedareaIsNull() {
            addCriterion("certifiedArea is null");
            return (Criteria) this;
        }

        public Criteria andCertifiedareaIsNotNull() {
            addCriterion("certifiedArea is not null");
            return (Criteria) this;
        }

        public Criteria andCertifiedareaEqualTo(String value) {
            addCriterion("certifiedArea =", value, "certifiedarea");
            return (Criteria) this;
        }

        public Criteria andCertifiedareaNotEqualTo(String value) {
            addCriterion("certifiedArea <>", value, "certifiedarea");
            return (Criteria) this;
        }

        public Criteria andCertifiedareaGreaterThan(String value) {
            addCriterion("certifiedArea >", value, "certifiedarea");
            return (Criteria) this;
        }

        public Criteria andCertifiedareaGreaterThanOrEqualTo(String value) {
            addCriterion("certifiedArea >=", value, "certifiedarea");
            return (Criteria) this;
        }

        public Criteria andCertifiedareaLessThan(String value) {
            addCriterion("certifiedArea <", value, "certifiedarea");
            return (Criteria) this;
        }

        public Criteria andCertifiedareaLessThanOrEqualTo(String value) {
            addCriterion("certifiedArea <=", value, "certifiedarea");
            return (Criteria) this;
        }

        public Criteria andCertifiedareaLike(String value) {
            addCriterion("certifiedArea like", value, "certifiedarea");
            return (Criteria) this;
        }

        public Criteria andCertifiedareaNotLike(String value) {
            addCriterion("certifiedArea not like", value, "certifiedarea");
            return (Criteria) this;
        }

        public Criteria andCertifiedareaIn(List<String> values) {
            addCriterion("certifiedArea in", values, "certifiedarea");
            return (Criteria) this;
        }

        public Criteria andCertifiedareaNotIn(List<String> values) {
            addCriterion("certifiedArea not in", values, "certifiedarea");
            return (Criteria) this;
        }

        public Criteria andCertifiedareaBetween(String value1, String value2) {
            addCriterion("certifiedArea between", value1, value2, "certifiedarea");
            return (Criteria) this;
        }

        public Criteria andCertifiedareaNotBetween(String value1, String value2) {
            addCriterion("certifiedArea not between", value1, value2, "certifiedarea");
            return (Criteria) this;
        }

        public Criteria andJobdesignareaIsNull() {
            addCriterion("jobDesignArea is null");
            return (Criteria) this;
        }

        public Criteria andJobdesignareaIsNotNull() {
            addCriterion("jobDesignArea is not null");
            return (Criteria) this;
        }

        public Criteria andJobdesignareaEqualTo(String value) {
            addCriterion("jobDesignArea =", value, "jobdesignarea");
            return (Criteria) this;
        }

        public Criteria andJobdesignareaNotEqualTo(String value) {
            addCriterion("jobDesignArea <>", value, "jobdesignarea");
            return (Criteria) this;
        }

        public Criteria andJobdesignareaGreaterThan(String value) {
            addCriterion("jobDesignArea >", value, "jobdesignarea");
            return (Criteria) this;
        }

        public Criteria andJobdesignareaGreaterThanOrEqualTo(String value) {
            addCriterion("jobDesignArea >=", value, "jobdesignarea");
            return (Criteria) this;
        }

        public Criteria andJobdesignareaLessThan(String value) {
            addCriterion("jobDesignArea <", value, "jobdesignarea");
            return (Criteria) this;
        }

        public Criteria andJobdesignareaLessThanOrEqualTo(String value) {
            addCriterion("jobDesignArea <=", value, "jobdesignarea");
            return (Criteria) this;
        }

        public Criteria andJobdesignareaLike(String value) {
            addCriterion("jobDesignArea like", value, "jobdesignarea");
            return (Criteria) this;
        }

        public Criteria andJobdesignareaNotLike(String value) {
            addCriterion("jobDesignArea not like", value, "jobdesignarea");
            return (Criteria) this;
        }

        public Criteria andJobdesignareaIn(List<String> values) {
            addCriterion("jobDesignArea in", values, "jobdesignarea");
            return (Criteria) this;
        }

        public Criteria andJobdesignareaNotIn(List<String> values) {
            addCriterion("jobDesignArea not in", values, "jobdesignarea");
            return (Criteria) this;
        }

        public Criteria andJobdesignareaBetween(String value1, String value2) {
            addCriterion("jobDesignArea between", value1, value2, "jobdesignarea");
            return (Criteria) this;
        }

        public Criteria andJobdesignareaNotBetween(String value1, String value2) {
            addCriterion("jobDesignArea not between", value1, value2, "jobdesignarea");
            return (Criteria) this;
        }

        public Criteria andDesignconstructionaccordingoperationIsNull() {
            addCriterion("designConstructionAccordingOperation is null");
            return (Criteria) this;
        }

        public Criteria andDesignconstructionaccordingoperationIsNotNull() {
            addCriterion("designConstructionAccordingOperation is not null");
            return (Criteria) this;
        }

        public Criteria andDesignconstructionaccordingoperationEqualTo(String value) {
            addCriterion("designConstructionAccordingOperation =", value, "designconstructionaccordingoperation");
            return (Criteria) this;
        }

        public Criteria andDesignconstructionaccordingoperationNotEqualTo(String value) {
            addCriterion("designConstructionAccordingOperation <>", value, "designconstructionaccordingoperation");
            return (Criteria) this;
        }

        public Criteria andDesignconstructionaccordingoperationGreaterThan(String value) {
            addCriterion("designConstructionAccordingOperation >", value, "designconstructionaccordingoperation");
            return (Criteria) this;
        }

        public Criteria andDesignconstructionaccordingoperationGreaterThanOrEqualTo(String value) {
            addCriterion("designConstructionAccordingOperation >=", value, "designconstructionaccordingoperation");
            return (Criteria) this;
        }

        public Criteria andDesignconstructionaccordingoperationLessThan(String value) {
            addCriterion("designConstructionAccordingOperation <", value, "designconstructionaccordingoperation");
            return (Criteria) this;
        }

        public Criteria andDesignconstructionaccordingoperationLessThanOrEqualTo(String value) {
            addCriterion("designConstructionAccordingOperation <=", value, "designconstructionaccordingoperation");
            return (Criteria) this;
        }

        public Criteria andDesignconstructionaccordingoperationLike(String value) {
            addCriterion("designConstructionAccordingOperation like", value, "designconstructionaccordingoperation");
            return (Criteria) this;
        }

        public Criteria andDesignconstructionaccordingoperationNotLike(String value) {
            addCriterion("designConstructionAccordingOperation not like", value, "designconstructionaccordingoperation");
            return (Criteria) this;
        }

        public Criteria andDesignconstructionaccordingoperationIn(List<String> values) {
            addCriterion("designConstructionAccordingOperation in", values, "designconstructionaccordingoperation");
            return (Criteria) this;
        }

        public Criteria andDesignconstructionaccordingoperationNotIn(List<String> values) {
            addCriterion("designConstructionAccordingOperation not in", values, "designconstructionaccordingoperation");
            return (Criteria) this;
        }

        public Criteria andDesignconstructionaccordingoperationBetween(String value1, String value2) {
            addCriterion("designConstructionAccordingOperation between", value1, value2, "designconstructionaccordingoperation");
            return (Criteria) this;
        }

        public Criteria andDesignconstructionaccordingoperationNotBetween(String value1, String value2) {
            addCriterion("designConstructionAccordingOperation not between", value1, value2, "designconstructionaccordingoperation");
            return (Criteria) this;
        }

        public Criteria andFilesizeIsNull() {
            addCriterion("fileSize is null");
            return (Criteria) this;
        }

        public Criteria andFilesizeIsNotNull() {
            addCriterion("fileSize is not null");
            return (Criteria) this;
        }

        public Criteria andFilesizeEqualTo(String value) {
            addCriterion("fileSize =", value, "filesize");
            return (Criteria) this;
        }

        public Criteria andFilesizeNotEqualTo(String value) {
            addCriterion("fileSize <>", value, "filesize");
            return (Criteria) this;
        }

        public Criteria andFilesizeGreaterThan(String value) {
            addCriterion("fileSize >", value, "filesize");
            return (Criteria) this;
        }

        public Criteria andFilesizeGreaterThanOrEqualTo(String value) {
            addCriterion("fileSize >=", value, "filesize");
            return (Criteria) this;
        }

        public Criteria andFilesizeLessThan(String value) {
            addCriterion("fileSize <", value, "filesize");
            return (Criteria) this;
        }

        public Criteria andFilesizeLessThanOrEqualTo(String value) {
            addCriterion("fileSize <=", value, "filesize");
            return (Criteria) this;
        }

        public Criteria andFilesizeLike(String value) {
            addCriterion("fileSize like", value, "filesize");
            return (Criteria) this;
        }

        public Criteria andFilesizeNotLike(String value) {
            addCriterion("fileSize not like", value, "filesize");
            return (Criteria) this;
        }

        public Criteria andFilesizeIn(List<String> values) {
            addCriterion("fileSize in", values, "filesize");
            return (Criteria) this;
        }

        public Criteria andFilesizeNotIn(List<String> values) {
            addCriterion("fileSize not in", values, "filesize");
            return (Criteria) this;
        }

        public Criteria andFilesizeBetween(String value1, String value2) {
            addCriterion("fileSize between", value1, value2, "filesize");
            return (Criteria) this;
        }

        public Criteria andFilesizeNotBetween(String value1, String value2) {
            addCriterion("fileSize not between", value1, value2, "filesize");
            return (Criteria) this;
        }

        public Criteria andCountyinspectionareaIsNull() {
            addCriterion("countyInspectionArea is null");
            return (Criteria) this;
        }

        public Criteria andCountyinspectionareaIsNotNull() {
            addCriterion("countyInspectionArea is not null");
            return (Criteria) this;
        }

        public Criteria andCountyinspectionareaEqualTo(String value) {
            addCriterion("countyInspectionArea =", value, "countyinspectionarea");
            return (Criteria) this;
        }

        public Criteria andCountyinspectionareaNotEqualTo(String value) {
            addCriterion("countyInspectionArea <>", value, "countyinspectionarea");
            return (Criteria) this;
        }

        public Criteria andCountyinspectionareaGreaterThan(String value) {
            addCriterion("countyInspectionArea >", value, "countyinspectionarea");
            return (Criteria) this;
        }

        public Criteria andCountyinspectionareaGreaterThanOrEqualTo(String value) {
            addCriterion("countyInspectionArea >=", value, "countyinspectionarea");
            return (Criteria) this;
        }

        public Criteria andCountyinspectionareaLessThan(String value) {
            addCriterion("countyInspectionArea <", value, "countyinspectionarea");
            return (Criteria) this;
        }

        public Criteria andCountyinspectionareaLessThanOrEqualTo(String value) {
            addCriterion("countyInspectionArea <=", value, "countyinspectionarea");
            return (Criteria) this;
        }

        public Criteria andCountyinspectionareaLike(String value) {
            addCriterion("countyInspectionArea like", value, "countyinspectionarea");
            return (Criteria) this;
        }

        public Criteria andCountyinspectionareaNotLike(String value) {
            addCriterion("countyInspectionArea not like", value, "countyinspectionarea");
            return (Criteria) this;
        }

        public Criteria andCountyinspectionareaIn(List<String> values) {
            addCriterion("countyInspectionArea in", values, "countyinspectionarea");
            return (Criteria) this;
        }

        public Criteria andCountyinspectionareaNotIn(List<String> values) {
            addCriterion("countyInspectionArea not in", values, "countyinspectionarea");
            return (Criteria) this;
        }

        public Criteria andCountyinspectionareaBetween(String value1, String value2) {
            addCriterion("countyInspectionArea between", value1, value2, "countyinspectionarea");
            return (Criteria) this;
        }

        public Criteria andCountyinspectionareaNotBetween(String value1, String value2) {
            addCriterion("countyInspectionArea not between", value1, value2, "countyinspectionarea");
            return (Criteria) this;
        }

        public Criteria andDesignforestareaIsNull() {
            addCriterion("designForestArea is null");
            return (Criteria) this;
        }

        public Criteria andDesignforestareaIsNotNull() {
            addCriterion("designForestArea is not null");
            return (Criteria) this;
        }

        public Criteria andDesignforestareaEqualTo(String value) {
            addCriterion("designForestArea =", value, "designforestarea");
            return (Criteria) this;
        }

        public Criteria andDesignforestareaNotEqualTo(String value) {
            addCriterion("designForestArea <>", value, "designforestarea");
            return (Criteria) this;
        }

        public Criteria andDesignforestareaGreaterThan(String value) {
            addCriterion("designForestArea >", value, "designforestarea");
            return (Criteria) this;
        }

        public Criteria andDesignforestareaGreaterThanOrEqualTo(String value) {
            addCriterion("designForestArea >=", value, "designforestarea");
            return (Criteria) this;
        }

        public Criteria andDesignforestareaLessThan(String value) {
            addCriterion("designForestArea <", value, "designforestarea");
            return (Criteria) this;
        }

        public Criteria andDesignforestareaLessThanOrEqualTo(String value) {
            addCriterion("designForestArea <=", value, "designforestarea");
            return (Criteria) this;
        }

        public Criteria andDesignforestareaLike(String value) {
            addCriterion("designForestArea like", value, "designforestarea");
            return (Criteria) this;
        }

        public Criteria andDesignforestareaNotLike(String value) {
            addCriterion("designForestArea not like", value, "designforestarea");
            return (Criteria) this;
        }

        public Criteria andDesignforestareaIn(List<String> values) {
            addCriterion("designForestArea in", values, "designforestarea");
            return (Criteria) this;
        }

        public Criteria andDesignforestareaNotIn(List<String> values) {
            addCriterion("designForestArea not in", values, "designforestarea");
            return (Criteria) this;
        }

        public Criteria andDesignforestareaBetween(String value1, String value2) {
            addCriterion("designForestArea between", value1, value2, "designforestarea");
            return (Criteria) this;
        }

        public Criteria andDesignforestareaNotBetween(String value1, String value2) {
            addCriterion("designForestArea not between", value1, value2, "designforestarea");
            return (Criteria) this;
        }

        public Criteria andVerifyforestareaIsNull() {
            addCriterion("verifyForestArea is null");
            return (Criteria) this;
        }

        public Criteria andVerifyforestareaIsNotNull() {
            addCriterion("verifyForestArea is not null");
            return (Criteria) this;
        }

        public Criteria andVerifyforestareaEqualTo(String value) {
            addCriterion("verifyForestArea =", value, "verifyforestarea");
            return (Criteria) this;
        }

        public Criteria andVerifyforestareaNotEqualTo(String value) {
            addCriterion("verifyForestArea <>", value, "verifyforestarea");
            return (Criteria) this;
        }

        public Criteria andVerifyforestareaGreaterThan(String value) {
            addCriterion("verifyForestArea >", value, "verifyforestarea");
            return (Criteria) this;
        }

        public Criteria andVerifyforestareaGreaterThanOrEqualTo(String value) {
            addCriterion("verifyForestArea >=", value, "verifyforestarea");
            return (Criteria) this;
        }

        public Criteria andVerifyforestareaLessThan(String value) {
            addCriterion("verifyForestArea <", value, "verifyforestarea");
            return (Criteria) this;
        }

        public Criteria andVerifyforestareaLessThanOrEqualTo(String value) {
            addCriterion("verifyForestArea <=", value, "verifyforestarea");
            return (Criteria) this;
        }

        public Criteria andVerifyforestareaLike(String value) {
            addCriterion("verifyForestArea like", value, "verifyforestarea");
            return (Criteria) this;
        }

        public Criteria andVerifyforestareaNotLike(String value) {
            addCriterion("verifyForestArea not like", value, "verifyforestarea");
            return (Criteria) this;
        }

        public Criteria andVerifyforestareaIn(List<String> values) {
            addCriterion("verifyForestArea in", values, "verifyforestarea");
            return (Criteria) this;
        }

        public Criteria andVerifyforestareaNotIn(List<String> values) {
            addCriterion("verifyForestArea not in", values, "verifyforestarea");
            return (Criteria) this;
        }

        public Criteria andVerifyforestareaBetween(String value1, String value2) {
            addCriterion("verifyForestArea between", value1, value2, "verifyforestarea");
            return (Criteria) this;
        }

        public Criteria andVerifyforestareaNotBetween(String value1, String value2) {
            addCriterion("verifyForestArea not between", value1, value2, "verifyforestarea");
            return (Criteria) this;
        }

        public Criteria andBareareaIsNull() {
            addCriterion("bareArea is null");
            return (Criteria) this;
        }

        public Criteria andBareareaIsNotNull() {
            addCriterion("bareArea is not null");
            return (Criteria) this;
        }

        public Criteria andBareareaEqualTo(String value) {
            addCriterion("bareArea =", value, "barearea");
            return (Criteria) this;
        }

        public Criteria andBareareaNotEqualTo(String value) {
            addCriterion("bareArea <>", value, "barearea");
            return (Criteria) this;
        }

        public Criteria andBareareaGreaterThan(String value) {
            addCriterion("bareArea >", value, "barearea");
            return (Criteria) this;
        }

        public Criteria andBareareaGreaterThanOrEqualTo(String value) {
            addCriterion("bareArea >=", value, "barearea");
            return (Criteria) this;
        }

        public Criteria andBareareaLessThan(String value) {
            addCriterion("bareArea <", value, "barearea");
            return (Criteria) this;
        }

        public Criteria andBareareaLessThanOrEqualTo(String value) {
            addCriterion("bareArea <=", value, "barearea");
            return (Criteria) this;
        }

        public Criteria andBareareaLike(String value) {
            addCriterion("bareArea like", value, "barearea");
            return (Criteria) this;
        }

        public Criteria andBareareaNotLike(String value) {
            addCriterion("bareArea not like", value, "barearea");
            return (Criteria) this;
        }

        public Criteria andBareareaIn(List<String> values) {
            addCriterion("bareArea in", values, "barearea");
            return (Criteria) this;
        }

        public Criteria andBareareaNotIn(List<String> values) {
            addCriterion("bareArea not in", values, "barearea");
            return (Criteria) this;
        }

        public Criteria andBareareaBetween(String value1, String value2) {
            addCriterion("bareArea between", value1, value2, "barearea");
            return (Criteria) this;
        }

        public Criteria andBareareaNotBetween(String value1, String value2) {
            addCriterion("bareArea not between", value1, value2, "barearea");
            return (Criteria) this;
        }

        public Criteria andManagementareaIsNull() {
            addCriterion("managementArea is null");
            return (Criteria) this;
        }

        public Criteria andManagementareaIsNotNull() {
            addCriterion("managementArea is not null");
            return (Criteria) this;
        }

        public Criteria andManagementareaEqualTo(String value) {
            addCriterion("managementArea =", value, "managementarea");
            return (Criteria) this;
        }

        public Criteria andManagementareaNotEqualTo(String value) {
            addCriterion("managementArea <>", value, "managementarea");
            return (Criteria) this;
        }

        public Criteria andManagementareaGreaterThan(String value) {
            addCriterion("managementArea >", value, "managementarea");
            return (Criteria) this;
        }

        public Criteria andManagementareaGreaterThanOrEqualTo(String value) {
            addCriterion("managementArea >=", value, "managementarea");
            return (Criteria) this;
        }

        public Criteria andManagementareaLessThan(String value) {
            addCriterion("managementArea <", value, "managementarea");
            return (Criteria) this;
        }

        public Criteria andManagementareaLessThanOrEqualTo(String value) {
            addCriterion("managementArea <=", value, "managementarea");
            return (Criteria) this;
        }

        public Criteria andManagementareaLike(String value) {
            addCriterion("managementArea like", value, "managementarea");
            return (Criteria) this;
        }

        public Criteria andManagementareaNotLike(String value) {
            addCriterion("managementArea not like", value, "managementarea");
            return (Criteria) this;
        }

        public Criteria andManagementareaIn(List<String> values) {
            addCriterion("managementArea in", values, "managementarea");
            return (Criteria) this;
        }

        public Criteria andManagementareaNotIn(List<String> values) {
            addCriterion("managementArea not in", values, "managementarea");
            return (Criteria) this;
        }

        public Criteria andManagementareaBetween(String value1, String value2) {
            addCriterion("managementArea between", value1, value2, "managementarea");
            return (Criteria) this;
        }

        public Criteria andManagementareaNotBetween(String value1, String value2) {
            addCriterion("managementArea not between", value1, value2, "managementarea");
            return (Criteria) this;
        }

        public Criteria andAreaverificationrateIsNull() {
            addCriterion("areaVerificationRate is null");
            return (Criteria) this;
        }

        public Criteria andAreaverificationrateIsNotNull() {
            addCriterion("areaVerificationRate is not null");
            return (Criteria) this;
        }

        public Criteria andAreaverificationrateEqualTo(String value) {
            addCriterion("areaVerificationRate =", value, "areaverificationrate");
            return (Criteria) this;
        }

        public Criteria andAreaverificationrateNotEqualTo(String value) {
            addCriterion("areaVerificationRate <>", value, "areaverificationrate");
            return (Criteria) this;
        }

        public Criteria andAreaverificationrateGreaterThan(String value) {
            addCriterion("areaVerificationRate >", value, "areaverificationrate");
            return (Criteria) this;
        }

        public Criteria andAreaverificationrateGreaterThanOrEqualTo(String value) {
            addCriterion("areaVerificationRate >=", value, "areaverificationrate");
            return (Criteria) this;
        }

        public Criteria andAreaverificationrateLessThan(String value) {
            addCriterion("areaVerificationRate <", value, "areaverificationrate");
            return (Criteria) this;
        }

        public Criteria andAreaverificationrateLessThanOrEqualTo(String value) {
            addCriterion("areaVerificationRate <=", value, "areaverificationrate");
            return (Criteria) this;
        }

        public Criteria andAreaverificationrateLike(String value) {
            addCriterion("areaVerificationRate like", value, "areaverificationrate");
            return (Criteria) this;
        }

        public Criteria andAreaverificationrateNotLike(String value) {
            addCriterion("areaVerificationRate not like", value, "areaverificationrate");
            return (Criteria) this;
        }

        public Criteria andAreaverificationrateIn(List<String> values) {
            addCriterion("areaVerificationRate in", values, "areaverificationrate");
            return (Criteria) this;
        }

        public Criteria andAreaverificationrateNotIn(List<String> values) {
            addCriterion("areaVerificationRate not in", values, "areaverificationrate");
            return (Criteria) this;
        }

        public Criteria andAreaverificationrateBetween(String value1, String value2) {
            addCriterion("areaVerificationRate between", value1, value2, "areaverificationrate");
            return (Criteria) this;
        }

        public Criteria andAreaverificationrateNotBetween(String value1, String value2) {
            addCriterion("areaVerificationRate not between", value1, value2, "areaverificationrate");
            return (Criteria) this;
        }

        public Criteria andVerifyareapassrateIsNull() {
            addCriterion("verifyAreaPassRate is null");
            return (Criteria) this;
        }

        public Criteria andVerifyareapassrateIsNotNull() {
            addCriterion("verifyAreaPassRate is not null");
            return (Criteria) this;
        }

        public Criteria andVerifyareapassrateEqualTo(String value) {
            addCriterion("verifyAreaPassRate =", value, "verifyareapassrate");
            return (Criteria) this;
        }

        public Criteria andVerifyareapassrateNotEqualTo(String value) {
            addCriterion("verifyAreaPassRate <>", value, "verifyareapassrate");
            return (Criteria) this;
        }

        public Criteria andVerifyareapassrateGreaterThan(String value) {
            addCriterion("verifyAreaPassRate >", value, "verifyareapassrate");
            return (Criteria) this;
        }

        public Criteria andVerifyareapassrateGreaterThanOrEqualTo(String value) {
            addCriterion("verifyAreaPassRate >=", value, "verifyareapassrate");
            return (Criteria) this;
        }

        public Criteria andVerifyareapassrateLessThan(String value) {
            addCriterion("verifyAreaPassRate <", value, "verifyareapassrate");
            return (Criteria) this;
        }

        public Criteria andVerifyareapassrateLessThanOrEqualTo(String value) {
            addCriterion("verifyAreaPassRate <=", value, "verifyareapassrate");
            return (Criteria) this;
        }

        public Criteria andVerifyareapassrateLike(String value) {
            addCriterion("verifyAreaPassRate like", value, "verifyareapassrate");
            return (Criteria) this;
        }

        public Criteria andVerifyareapassrateNotLike(String value) {
            addCriterion("verifyAreaPassRate not like", value, "verifyareapassrate");
            return (Criteria) this;
        }

        public Criteria andVerifyareapassrateIn(List<String> values) {
            addCriterion("verifyAreaPassRate in", values, "verifyareapassrate");
            return (Criteria) this;
        }

        public Criteria andVerifyareapassrateNotIn(List<String> values) {
            addCriterion("verifyAreaPassRate not in", values, "verifyareapassrate");
            return (Criteria) this;
        }

        public Criteria andVerifyareapassrateBetween(String value1, String value2) {
            addCriterion("verifyAreaPassRate between", value1, value2, "verifyareapassrate");
            return (Criteria) this;
        }

        public Criteria andVerifyareapassrateNotBetween(String value1, String value2) {
            addCriterion("verifyAreaPassRate not between", value1, value2, "verifyareapassrate");
            return (Criteria) this;
        }

        public Criteria andReportareaqualificationrateIsNull() {
            addCriterion("reportAreaQualificationRate is null");
            return (Criteria) this;
        }

        public Criteria andReportareaqualificationrateIsNotNull() {
            addCriterion("reportAreaQualificationRate is not null");
            return (Criteria) this;
        }

        public Criteria andReportareaqualificationrateEqualTo(String value) {
            addCriterion("reportAreaQualificationRate =", value, "reportareaqualificationrate");
            return (Criteria) this;
        }

        public Criteria andReportareaqualificationrateNotEqualTo(String value) {
            addCriterion("reportAreaQualificationRate <>", value, "reportareaqualificationrate");
            return (Criteria) this;
        }

        public Criteria andReportareaqualificationrateGreaterThan(String value) {
            addCriterion("reportAreaQualificationRate >", value, "reportareaqualificationrate");
            return (Criteria) this;
        }

        public Criteria andReportareaqualificationrateGreaterThanOrEqualTo(String value) {
            addCriterion("reportAreaQualificationRate >=", value, "reportareaqualificationrate");
            return (Criteria) this;
        }

        public Criteria andReportareaqualificationrateLessThan(String value) {
            addCriterion("reportAreaQualificationRate <", value, "reportareaqualificationrate");
            return (Criteria) this;
        }

        public Criteria andReportareaqualificationrateLessThanOrEqualTo(String value) {
            addCriterion("reportAreaQualificationRate <=", value, "reportareaqualificationrate");
            return (Criteria) this;
        }

        public Criteria andReportareaqualificationrateLike(String value) {
            addCriterion("reportAreaQualificationRate like", value, "reportareaqualificationrate");
            return (Criteria) this;
        }

        public Criteria andReportareaqualificationrateNotLike(String value) {
            addCriterion("reportAreaQualificationRate not like", value, "reportareaqualificationrate");
            return (Criteria) this;
        }

        public Criteria andReportareaqualificationrateIn(List<String> values) {
            addCriterion("reportAreaQualificationRate in", values, "reportareaqualificationrate");
            return (Criteria) this;
        }

        public Criteria andReportareaqualificationrateNotIn(List<String> values) {
            addCriterion("reportAreaQualificationRate not in", values, "reportareaqualificationrate");
            return (Criteria) this;
        }

        public Criteria andReportareaqualificationrateBetween(String value1, String value2) {
            addCriterion("reportAreaQualificationRate between", value1, value2, "reportareaqualificationrate");
            return (Criteria) this;
        }

        public Criteria andReportareaqualificationrateNotBetween(String value1, String value2) {
            addCriterion("reportAreaQualificationRate not between", value1, value2, "reportareaqualificationrate");
            return (Criteria) this;
        }

        public Criteria andReportretentionrateyearIsNull() {
            addCriterion("reportRetentionRateYear is null");
            return (Criteria) this;
        }

        public Criteria andReportretentionrateyearIsNotNull() {
            addCriterion("reportRetentionRateYear is not null");
            return (Criteria) this;
        }

        public Criteria andReportretentionrateyearEqualTo(String value) {
            addCriterion("reportRetentionRateYear =", value, "reportretentionrateyear");
            return (Criteria) this;
        }

        public Criteria andReportretentionrateyearNotEqualTo(String value) {
            addCriterion("reportRetentionRateYear <>", value, "reportretentionrateyear");
            return (Criteria) this;
        }

        public Criteria andReportretentionrateyearGreaterThan(String value) {
            addCriterion("reportRetentionRateYear >", value, "reportretentionrateyear");
            return (Criteria) this;
        }

        public Criteria andReportretentionrateyearGreaterThanOrEqualTo(String value) {
            addCriterion("reportRetentionRateYear >=", value, "reportretentionrateyear");
            return (Criteria) this;
        }

        public Criteria andReportretentionrateyearLessThan(String value) {
            addCriterion("reportRetentionRateYear <", value, "reportretentionrateyear");
            return (Criteria) this;
        }

        public Criteria andReportretentionrateyearLessThanOrEqualTo(String value) {
            addCriterion("reportRetentionRateYear <=", value, "reportretentionrateyear");
            return (Criteria) this;
        }

        public Criteria andReportretentionrateyearLike(String value) {
            addCriterion("reportRetentionRateYear like", value, "reportretentionrateyear");
            return (Criteria) this;
        }

        public Criteria andReportretentionrateyearNotLike(String value) {
            addCriterion("reportRetentionRateYear not like", value, "reportretentionrateyear");
            return (Criteria) this;
        }

        public Criteria andReportretentionrateyearIn(List<String> values) {
            addCriterion("reportRetentionRateYear in", values, "reportretentionrateyear");
            return (Criteria) this;
        }

        public Criteria andReportretentionrateyearNotIn(List<String> values) {
            addCriterion("reportRetentionRateYear not in", values, "reportretentionrateyear");
            return (Criteria) this;
        }

        public Criteria andReportretentionrateyearBetween(String value1, String value2) {
            addCriterion("reportRetentionRateYear between", value1, value2, "reportretentionrateyear");
            return (Criteria) this;
        }

        public Criteria andReportretentionrateyearNotBetween(String value1, String value2) {
            addCriterion("reportRetentionRateYear not between", value1, value2, "reportretentionrateyear");
            return (Criteria) this;
        }

        public Criteria andAfforestationreportseligibleyearIsNull() {
            addCriterion("afforestationReportsEligibleYear is null");
            return (Criteria) this;
        }

        public Criteria andAfforestationreportseligibleyearIsNotNull() {
            addCriterion("afforestationReportsEligibleYear is not null");
            return (Criteria) this;
        }

        public Criteria andAfforestationreportseligibleyearEqualTo(String value) {
            addCriterion("afforestationReportsEligibleYear =", value, "afforestationreportseligibleyear");
            return (Criteria) this;
        }

        public Criteria andAfforestationreportseligibleyearNotEqualTo(String value) {
            addCriterion("afforestationReportsEligibleYear <>", value, "afforestationreportseligibleyear");
            return (Criteria) this;
        }

        public Criteria andAfforestationreportseligibleyearGreaterThan(String value) {
            addCriterion("afforestationReportsEligibleYear >", value, "afforestationreportseligibleyear");
            return (Criteria) this;
        }

        public Criteria andAfforestationreportseligibleyearGreaterThanOrEqualTo(String value) {
            addCriterion("afforestationReportsEligibleYear >=", value, "afforestationreportseligibleyear");
            return (Criteria) this;
        }

        public Criteria andAfforestationreportseligibleyearLessThan(String value) {
            addCriterion("afforestationReportsEligibleYear <", value, "afforestationreportseligibleyear");
            return (Criteria) this;
        }

        public Criteria andAfforestationreportseligibleyearLessThanOrEqualTo(String value) {
            addCriterion("afforestationReportsEligibleYear <=", value, "afforestationreportseligibleyear");
            return (Criteria) this;
        }

        public Criteria andAfforestationreportseligibleyearLike(String value) {
            addCriterion("afforestationReportsEligibleYear like", value, "afforestationreportseligibleyear");
            return (Criteria) this;
        }

        public Criteria andAfforestationreportseligibleyearNotLike(String value) {
            addCriterion("afforestationReportsEligibleYear not like", value, "afforestationreportseligibleyear");
            return (Criteria) this;
        }

        public Criteria andAfforestationreportseligibleyearIn(List<String> values) {
            addCriterion("afforestationReportsEligibleYear in", values, "afforestationreportseligibleyear");
            return (Criteria) this;
        }

        public Criteria andAfforestationreportseligibleyearNotIn(List<String> values) {
            addCriterion("afforestationReportsEligibleYear not in", values, "afforestationreportseligibleyear");
            return (Criteria) this;
        }

        public Criteria andAfforestationreportseligibleyearBetween(String value1, String value2) {
            addCriterion("afforestationReportsEligibleYear between", value1, value2, "afforestationreportseligibleyear");
            return (Criteria) this;
        }

        public Criteria andAfforestationreportseligibleyearNotBetween(String value1, String value2) {
            addCriterion("afforestationReportsEligibleYear not between", value1, value2, "afforestationreportseligibleyear");
            return (Criteria) this;
        }

        public Criteria andCountyselfinspectionareaIsNull() {
            addCriterion("countySelfInspectionArea is null");
            return (Criteria) this;
        }

        public Criteria andCountyselfinspectionareaIsNotNull() {
            addCriterion("countySelfInspectionArea is not null");
            return (Criteria) this;
        }

        public Criteria andCountyselfinspectionareaEqualTo(String value) {
            addCriterion("countySelfInspectionArea =", value, "countyselfinspectionarea");
            return (Criteria) this;
        }

        public Criteria andCountyselfinspectionareaNotEqualTo(String value) {
            addCriterion("countySelfInspectionArea <>", value, "countyselfinspectionarea");
            return (Criteria) this;
        }

        public Criteria andCountyselfinspectionareaGreaterThan(String value) {
            addCriterion("countySelfInspectionArea >", value, "countyselfinspectionarea");
            return (Criteria) this;
        }

        public Criteria andCountyselfinspectionareaGreaterThanOrEqualTo(String value) {
            addCriterion("countySelfInspectionArea >=", value, "countyselfinspectionarea");
            return (Criteria) this;
        }

        public Criteria andCountyselfinspectionareaLessThan(String value) {
            addCriterion("countySelfInspectionArea <", value, "countyselfinspectionarea");
            return (Criteria) this;
        }

        public Criteria andCountyselfinspectionareaLessThanOrEqualTo(String value) {
            addCriterion("countySelfInspectionArea <=", value, "countyselfinspectionarea");
            return (Criteria) this;
        }

        public Criteria andCountyselfinspectionareaLike(String value) {
            addCriterion("countySelfInspectionArea like", value, "countyselfinspectionarea");
            return (Criteria) this;
        }

        public Criteria andCountyselfinspectionareaNotLike(String value) {
            addCriterion("countySelfInspectionArea not like", value, "countyselfinspectionarea");
            return (Criteria) this;
        }

        public Criteria andCountyselfinspectionareaIn(List<String> values) {
            addCriterion("countySelfInspectionArea in", values, "countyselfinspectionarea");
            return (Criteria) this;
        }

        public Criteria andCountyselfinspectionareaNotIn(List<String> values) {
            addCriterion("countySelfInspectionArea not in", values, "countyselfinspectionarea");
            return (Criteria) this;
        }

        public Criteria andCountyselfinspectionareaBetween(String value1, String value2) {
            addCriterion("countySelfInspectionArea between", value1, value2, "countyselfinspectionarea");
            return (Criteria) this;
        }

        public Criteria andCountyselfinspectionareaNotBetween(String value1, String value2) {
            addCriterion("countySelfInspectionArea not between", value1, value2, "countyselfinspectionarea");
            return (Criteria) this;
        }

        public Criteria andCalculatedverificationareaIsNull() {
            addCriterion("calculatedVerificationArea is null");
            return (Criteria) this;
        }

        public Criteria andCalculatedverificationareaIsNotNull() {
            addCriterion("calculatedVerificationArea is not null");
            return (Criteria) this;
        }

        public Criteria andCalculatedverificationareaEqualTo(String value) {
            addCriterion("calculatedVerificationArea =", value, "calculatedverificationarea");
            return (Criteria) this;
        }

        public Criteria andCalculatedverificationareaNotEqualTo(String value) {
            addCriterion("calculatedVerificationArea <>", value, "calculatedverificationarea");
            return (Criteria) this;
        }

        public Criteria andCalculatedverificationareaGreaterThan(String value) {
            addCriterion("calculatedVerificationArea >", value, "calculatedverificationarea");
            return (Criteria) this;
        }

        public Criteria andCalculatedverificationareaGreaterThanOrEqualTo(String value) {
            addCriterion("calculatedVerificationArea >=", value, "calculatedverificationarea");
            return (Criteria) this;
        }

        public Criteria andCalculatedverificationareaLessThan(String value) {
            addCriterion("calculatedVerificationArea <", value, "calculatedverificationarea");
            return (Criteria) this;
        }

        public Criteria andCalculatedverificationareaLessThanOrEqualTo(String value) {
            addCriterion("calculatedVerificationArea <=", value, "calculatedverificationarea");
            return (Criteria) this;
        }

        public Criteria andCalculatedverificationareaLike(String value) {
            addCriterion("calculatedVerificationArea like", value, "calculatedverificationarea");
            return (Criteria) this;
        }

        public Criteria andCalculatedverificationareaNotLike(String value) {
            addCriterion("calculatedVerificationArea not like", value, "calculatedverificationarea");
            return (Criteria) this;
        }

        public Criteria andCalculatedverificationareaIn(List<String> values) {
            addCriterion("calculatedVerificationArea in", values, "calculatedverificationarea");
            return (Criteria) this;
        }

        public Criteria andCalculatedverificationareaNotIn(List<String> values) {
            addCriterion("calculatedVerificationArea not in", values, "calculatedverificationarea");
            return (Criteria) this;
        }

        public Criteria andCalculatedverificationareaBetween(String value1, String value2) {
            addCriterion("calculatedVerificationArea between", value1, value2, "calculatedverificationarea");
            return (Criteria) this;
        }

        public Criteria andCalculatedverificationareaNotBetween(String value1, String value2) {
            addCriterion("calculatedVerificationArea not between", value1, value2, "calculatedverificationarea");
            return (Criteria) this;
        }

        public Criteria andCalculatequalifiedareaIsNull() {
            addCriterion("calculateQualifiedArea is null");
            return (Criteria) this;
        }

        public Criteria andCalculatequalifiedareaIsNotNull() {
            addCriterion("calculateQualifiedArea is not null");
            return (Criteria) this;
        }

        public Criteria andCalculatequalifiedareaEqualTo(String value) {
            addCriterion("calculateQualifiedArea =", value, "calculatequalifiedarea");
            return (Criteria) this;
        }

        public Criteria andCalculatequalifiedareaNotEqualTo(String value) {
            addCriterion("calculateQualifiedArea <>", value, "calculatequalifiedarea");
            return (Criteria) this;
        }

        public Criteria andCalculatequalifiedareaGreaterThan(String value) {
            addCriterion("calculateQualifiedArea >", value, "calculatequalifiedarea");
            return (Criteria) this;
        }

        public Criteria andCalculatequalifiedareaGreaterThanOrEqualTo(String value) {
            addCriterion("calculateQualifiedArea >=", value, "calculatequalifiedarea");
            return (Criteria) this;
        }

        public Criteria andCalculatequalifiedareaLessThan(String value) {
            addCriterion("calculateQualifiedArea <", value, "calculatequalifiedarea");
            return (Criteria) this;
        }

        public Criteria andCalculatequalifiedareaLessThanOrEqualTo(String value) {
            addCriterion("calculateQualifiedArea <=", value, "calculatequalifiedarea");
            return (Criteria) this;
        }

        public Criteria andCalculatequalifiedareaLike(String value) {
            addCriterion("calculateQualifiedArea like", value, "calculatequalifiedarea");
            return (Criteria) this;
        }

        public Criteria andCalculatequalifiedareaNotLike(String value) {
            addCriterion("calculateQualifiedArea not like", value, "calculatequalifiedarea");
            return (Criteria) this;
        }

        public Criteria andCalculatequalifiedareaIn(List<String> values) {
            addCriterion("calculateQualifiedArea in", values, "calculatequalifiedarea");
            return (Criteria) this;
        }

        public Criteria andCalculatequalifiedareaNotIn(List<String> values) {
            addCriterion("calculateQualifiedArea not in", values, "calculatequalifiedarea");
            return (Criteria) this;
        }

        public Criteria andCalculatequalifiedareaBetween(String value1, String value2) {
            addCriterion("calculateQualifiedArea between", value1, value2, "calculatequalifiedarea");
            return (Criteria) this;
        }

        public Criteria andCalculatequalifiedareaNotBetween(String value1, String value2) {
            addCriterion("calculateQualifiedArea not between", value1, value2, "calculatequalifiedarea");
            return (Criteria) this;
        }

        public Criteria andScheduledtaskIsNull() {
            addCriterion("scheduledTask is null");
            return (Criteria) this;
        }

        public Criteria andScheduledtaskIsNotNull() {
            addCriterion("scheduledTask is not null");
            return (Criteria) this;
        }

        public Criteria andScheduledtaskEqualTo(String value) {
            addCriterion("scheduledTask =", value, "scheduledtask");
            return (Criteria) this;
        }

        public Criteria andScheduledtaskNotEqualTo(String value) {
            addCriterion("scheduledTask <>", value, "scheduledtask");
            return (Criteria) this;
        }

        public Criteria andScheduledtaskGreaterThan(String value) {
            addCriterion("scheduledTask >", value, "scheduledtask");
            return (Criteria) this;
        }

        public Criteria andScheduledtaskGreaterThanOrEqualTo(String value) {
            addCriterion("scheduledTask >=", value, "scheduledtask");
            return (Criteria) this;
        }

        public Criteria andScheduledtaskLessThan(String value) {
            addCriterion("scheduledTask <", value, "scheduledtask");
            return (Criteria) this;
        }

        public Criteria andScheduledtaskLessThanOrEqualTo(String value) {
            addCriterion("scheduledTask <=", value, "scheduledtask");
            return (Criteria) this;
        }

        public Criteria andScheduledtaskLike(String value) {
            addCriterion("scheduledTask like", value, "scheduledtask");
            return (Criteria) this;
        }

        public Criteria andScheduledtaskNotLike(String value) {
            addCriterion("scheduledTask not like", value, "scheduledtask");
            return (Criteria) this;
        }

        public Criteria andScheduledtaskIn(List<String> values) {
            addCriterion("scheduledTask in", values, "scheduledtask");
            return (Criteria) this;
        }

        public Criteria andScheduledtaskNotIn(List<String> values) {
            addCriterion("scheduledTask not in", values, "scheduledtask");
            return (Criteria) this;
        }

        public Criteria andScheduledtaskBetween(String value1, String value2) {
            addCriterion("scheduledTask between", value1, value2, "scheduledtask");
            return (Criteria) this;
        }

        public Criteria andScheduledtaskNotBetween(String value1, String value2) {
            addCriterion("scheduledTask not between", value1, value2, "scheduledtask");
            return (Criteria) this;
        }

        public Criteria andTaskcompletionrateIsNull() {
            addCriterion("taskCompletionRate is null");
            return (Criteria) this;
        }

        public Criteria andTaskcompletionrateIsNotNull() {
            addCriterion("taskCompletionRate is not null");
            return (Criteria) this;
        }

        public Criteria andTaskcompletionrateEqualTo(String value) {
            addCriterion("taskCompletionRate =", value, "taskcompletionrate");
            return (Criteria) this;
        }

        public Criteria andTaskcompletionrateNotEqualTo(String value) {
            addCriterion("taskCompletionRate <>", value, "taskcompletionrate");
            return (Criteria) this;
        }

        public Criteria andTaskcompletionrateGreaterThan(String value) {
            addCriterion("taskCompletionRate >", value, "taskcompletionrate");
            return (Criteria) this;
        }

        public Criteria andTaskcompletionrateGreaterThanOrEqualTo(String value) {
            addCriterion("taskCompletionRate >=", value, "taskcompletionrate");
            return (Criteria) this;
        }

        public Criteria andTaskcompletionrateLessThan(String value) {
            addCriterion("taskCompletionRate <", value, "taskcompletionrate");
            return (Criteria) this;
        }

        public Criteria andTaskcompletionrateLessThanOrEqualTo(String value) {
            addCriterion("taskCompletionRate <=", value, "taskcompletionrate");
            return (Criteria) this;
        }

        public Criteria andTaskcompletionrateLike(String value) {
            addCriterion("taskCompletionRate like", value, "taskcompletionrate");
            return (Criteria) this;
        }

        public Criteria andTaskcompletionrateNotLike(String value) {
            addCriterion("taskCompletionRate not like", value, "taskcompletionrate");
            return (Criteria) this;
        }

        public Criteria andTaskcompletionrateIn(List<String> values) {
            addCriterion("taskCompletionRate in", values, "taskcompletionrate");
            return (Criteria) this;
        }

        public Criteria andTaskcompletionrateNotIn(List<String> values) {
            addCriterion("taskCompletionRate not in", values, "taskcompletionrate");
            return (Criteria) this;
        }

        public Criteria andTaskcompletionrateBetween(String value1, String value2) {
            addCriterion("taskCompletionRate between", value1, value2, "taskcompletionrate");
            return (Criteria) this;
        }

        public Criteria andTaskcompletionrateNotBetween(String value1, String value2) {
            addCriterion("taskCompletionRate not between", value1, value2, "taskcompletionrate");
            return (Criteria) this;
        }

        public Criteria andJobdesignrateIsNull() {
            addCriterion("jobDesignRate is null");
            return (Criteria) this;
        }

        public Criteria andJobdesignrateIsNotNull() {
            addCriterion("jobDesignRate is not null");
            return (Criteria) this;
        }

        public Criteria andJobdesignrateEqualTo(String value) {
            addCriterion("jobDesignRate =", value, "jobdesignrate");
            return (Criteria) this;
        }

        public Criteria andJobdesignrateNotEqualTo(String value) {
            addCriterion("jobDesignRate <>", value, "jobdesignrate");
            return (Criteria) this;
        }

        public Criteria andJobdesignrateGreaterThan(String value) {
            addCriterion("jobDesignRate >", value, "jobdesignrate");
            return (Criteria) this;
        }

        public Criteria andJobdesignrateGreaterThanOrEqualTo(String value) {
            addCriterion("jobDesignRate >=", value, "jobdesignrate");
            return (Criteria) this;
        }

        public Criteria andJobdesignrateLessThan(String value) {
            addCriterion("jobDesignRate <", value, "jobdesignrate");
            return (Criteria) this;
        }

        public Criteria andJobdesignrateLessThanOrEqualTo(String value) {
            addCriterion("jobDesignRate <=", value, "jobdesignrate");
            return (Criteria) this;
        }

        public Criteria andJobdesignrateLike(String value) {
            addCriterion("jobDesignRate like", value, "jobdesignrate");
            return (Criteria) this;
        }

        public Criteria andJobdesignrateNotLike(String value) {
            addCriterion("jobDesignRate not like", value, "jobdesignrate");
            return (Criteria) this;
        }

        public Criteria andJobdesignrateIn(List<String> values) {
            addCriterion("jobDesignRate in", values, "jobdesignrate");
            return (Criteria) this;
        }

        public Criteria andJobdesignrateNotIn(List<String> values) {
            addCriterion("jobDesignRate not in", values, "jobdesignrate");
            return (Criteria) this;
        }

        public Criteria andJobdesignrateBetween(String value1, String value2) {
            addCriterion("jobDesignRate between", value1, value2, "jobdesignrate");
            return (Criteria) this;
        }

        public Criteria andJobdesignrateNotBetween(String value1, String value2) {
            addCriterion("jobDesignRate not between", value1, value2, "jobdesignrate");
            return (Criteria) this;
        }

        public Criteria andAccordingdesignconstructionrateIsNull() {
            addCriterion("accordingDesignConstructionRate is null");
            return (Criteria) this;
        }

        public Criteria andAccordingdesignconstructionrateIsNotNull() {
            addCriterion("accordingDesignConstructionRate is not null");
            return (Criteria) this;
        }

        public Criteria andAccordingdesignconstructionrateEqualTo(String value) {
            addCriterion("accordingDesignConstructionRate =", value, "accordingdesignconstructionrate");
            return (Criteria) this;
        }

        public Criteria andAccordingdesignconstructionrateNotEqualTo(String value) {
            addCriterion("accordingDesignConstructionRate <>", value, "accordingdesignconstructionrate");
            return (Criteria) this;
        }

        public Criteria andAccordingdesignconstructionrateGreaterThan(String value) {
            addCriterion("accordingDesignConstructionRate >", value, "accordingdesignconstructionrate");
            return (Criteria) this;
        }

        public Criteria andAccordingdesignconstructionrateGreaterThanOrEqualTo(String value) {
            addCriterion("accordingDesignConstructionRate >=", value, "accordingdesignconstructionrate");
            return (Criteria) this;
        }

        public Criteria andAccordingdesignconstructionrateLessThan(String value) {
            addCriterion("accordingDesignConstructionRate <", value, "accordingdesignconstructionrate");
            return (Criteria) this;
        }

        public Criteria andAccordingdesignconstructionrateLessThanOrEqualTo(String value) {
            addCriterion("accordingDesignConstructionRate <=", value, "accordingdesignconstructionrate");
            return (Criteria) this;
        }

        public Criteria andAccordingdesignconstructionrateLike(String value) {
            addCriterion("accordingDesignConstructionRate like", value, "accordingdesignconstructionrate");
            return (Criteria) this;
        }

        public Criteria andAccordingdesignconstructionrateNotLike(String value) {
            addCriterion("accordingDesignConstructionRate not like", value, "accordingdesignconstructionrate");
            return (Criteria) this;
        }

        public Criteria andAccordingdesignconstructionrateIn(List<String> values) {
            addCriterion("accordingDesignConstructionRate in", values, "accordingdesignconstructionrate");
            return (Criteria) this;
        }

        public Criteria andAccordingdesignconstructionrateNotIn(List<String> values) {
            addCriterion("accordingDesignConstructionRate not in", values, "accordingdesignconstructionrate");
            return (Criteria) this;
        }

        public Criteria andAccordingdesignconstructionrateBetween(String value1, String value2) {
            addCriterion("accordingDesignConstructionRate between", value1, value2, "accordingdesignconstructionrate");
            return (Criteria) this;
        }

        public Criteria andAccordingdesignconstructionrateNotBetween(String value1, String value2) {
            addCriterion("accordingDesignConstructionRate not between", value1, value2, "accordingdesignconstructionrate");
            return (Criteria) this;
        }

        public Criteria andByinputtingrateIsNull() {
            addCriterion("byInputtingRate is null");
            return (Criteria) this;
        }

        public Criteria andByinputtingrateIsNotNull() {
            addCriterion("byInputtingRate is not null");
            return (Criteria) this;
        }

        public Criteria andByinputtingrateEqualTo(String value) {
            addCriterion("byInputtingRate =", value, "byinputtingrate");
            return (Criteria) this;
        }

        public Criteria andByinputtingrateNotEqualTo(String value) {
            addCriterion("byInputtingRate <>", value, "byinputtingrate");
            return (Criteria) this;
        }

        public Criteria andByinputtingrateGreaterThan(String value) {
            addCriterion("byInputtingRate >", value, "byinputtingrate");
            return (Criteria) this;
        }

        public Criteria andByinputtingrateGreaterThanOrEqualTo(String value) {
            addCriterion("byInputtingRate >=", value, "byinputtingrate");
            return (Criteria) this;
        }

        public Criteria andByinputtingrateLessThan(String value) {
            addCriterion("byInputtingRate <", value, "byinputtingrate");
            return (Criteria) this;
        }

        public Criteria andByinputtingrateLessThanOrEqualTo(String value) {
            addCriterion("byInputtingRate <=", value, "byinputtingrate");
            return (Criteria) this;
        }

        public Criteria andByinputtingrateLike(String value) {
            addCriterion("byInputtingRate like", value, "byinputtingrate");
            return (Criteria) this;
        }

        public Criteria andByinputtingrateNotLike(String value) {
            addCriterion("byInputtingRate not like", value, "byinputtingrate");
            return (Criteria) this;
        }

        public Criteria andByinputtingrateIn(List<String> values) {
            addCriterion("byInputtingRate in", values, "byinputtingrate");
            return (Criteria) this;
        }

        public Criteria andByinputtingrateNotIn(List<String> values) {
            addCriterion("byInputtingRate not in", values, "byinputtingrate");
            return (Criteria) this;
        }

        public Criteria andByinputtingrateBetween(String value1, String value2) {
            addCriterion("byInputtingRate between", value1, value2, "byinputtingrate");
            return (Criteria) this;
        }

        public Criteria andByinputtingrateNotBetween(String value1, String value2) {
            addCriterion("byInputtingRate not between", value1, value2, "byinputtingrate");
            return (Criteria) this;
        }

        public Criteria andSelfcheckingrateIsNull() {
            addCriterion("selfCheckingRate is null");
            return (Criteria) this;
        }

        public Criteria andSelfcheckingrateIsNotNull() {
            addCriterion("selfCheckingRate is not null");
            return (Criteria) this;
        }

        public Criteria andSelfcheckingrateEqualTo(String value) {
            addCriterion("selfCheckingRate =", value, "selfcheckingrate");
            return (Criteria) this;
        }

        public Criteria andSelfcheckingrateNotEqualTo(String value) {
            addCriterion("selfCheckingRate <>", value, "selfcheckingrate");
            return (Criteria) this;
        }

        public Criteria andSelfcheckingrateGreaterThan(String value) {
            addCriterion("selfCheckingRate >", value, "selfcheckingrate");
            return (Criteria) this;
        }

        public Criteria andSelfcheckingrateGreaterThanOrEqualTo(String value) {
            addCriterion("selfCheckingRate >=", value, "selfcheckingrate");
            return (Criteria) this;
        }

        public Criteria andSelfcheckingrateLessThan(String value) {
            addCriterion("selfCheckingRate <", value, "selfcheckingrate");
            return (Criteria) this;
        }

        public Criteria andSelfcheckingrateLessThanOrEqualTo(String value) {
            addCriterion("selfCheckingRate <=", value, "selfcheckingrate");
            return (Criteria) this;
        }

        public Criteria andSelfcheckingrateLike(String value) {
            addCriterion("selfCheckingRate like", value, "selfcheckingrate");
            return (Criteria) this;
        }

        public Criteria andSelfcheckingrateNotLike(String value) {
            addCriterion("selfCheckingRate not like", value, "selfcheckingrate");
            return (Criteria) this;
        }

        public Criteria andSelfcheckingrateIn(List<String> values) {
            addCriterion("selfCheckingRate in", values, "selfcheckingrate");
            return (Criteria) this;
        }

        public Criteria andSelfcheckingrateNotIn(List<String> values) {
            addCriterion("selfCheckingRate not in", values, "selfcheckingrate");
            return (Criteria) this;
        }

        public Criteria andSelfcheckingrateBetween(String value1, String value2) {
            addCriterion("selfCheckingRate between", value1, value2, "selfcheckingrate");
            return (Criteria) this;
        }

        public Criteria andSelfcheckingrateNotBetween(String value1, String value2) {
            addCriterion("selfCheckingRate not between", value1, value2, "selfcheckingrate");
            return (Criteria) this;
        }

        public Criteria andSelfcheckingsrateIsNull() {
            addCriterion("selfCheckingsRate is null");
            return (Criteria) this;
        }

        public Criteria andSelfcheckingsrateIsNotNull() {
            addCriterion("selfCheckingsRate is not null");
            return (Criteria) this;
        }

        public Criteria andSelfcheckingsrateEqualTo(String value) {
            addCriterion("selfCheckingsRate =", value, "selfcheckingsrate");
            return (Criteria) this;
        }

        public Criteria andSelfcheckingsrateNotEqualTo(String value) {
            addCriterion("selfCheckingsRate <>", value, "selfcheckingsrate");
            return (Criteria) this;
        }

        public Criteria andSelfcheckingsrateGreaterThan(String value) {
            addCriterion("selfCheckingsRate >", value, "selfcheckingsrate");
            return (Criteria) this;
        }

        public Criteria andSelfcheckingsrateGreaterThanOrEqualTo(String value) {
            addCriterion("selfCheckingsRate >=", value, "selfcheckingsrate");
            return (Criteria) this;
        }

        public Criteria andSelfcheckingsrateLessThan(String value) {
            addCriterion("selfCheckingsRate <", value, "selfcheckingsrate");
            return (Criteria) this;
        }

        public Criteria andSelfcheckingsrateLessThanOrEqualTo(String value) {
            addCriterion("selfCheckingsRate <=", value, "selfcheckingsrate");
            return (Criteria) this;
        }

        public Criteria andSelfcheckingsrateLike(String value) {
            addCriterion("selfCheckingsRate like", value, "selfcheckingsrate");
            return (Criteria) this;
        }

        public Criteria andSelfcheckingsrateNotLike(String value) {
            addCriterion("selfCheckingsRate not like", value, "selfcheckingsrate");
            return (Criteria) this;
        }

        public Criteria andSelfcheckingsrateIn(List<String> values) {
            addCriterion("selfCheckingsRate in", values, "selfcheckingsrate");
            return (Criteria) this;
        }

        public Criteria andSelfcheckingsrateNotIn(List<String> values) {
            addCriterion("selfCheckingsRate not in", values, "selfcheckingsrate");
            return (Criteria) this;
        }

        public Criteria andSelfcheckingsrateBetween(String value1, String value2) {
            addCriterion("selfCheckingsRate between", value1, value2, "selfcheckingsrate");
            return (Criteria) this;
        }

        public Criteria andSelfcheckingsrateNotBetween(String value1, String value2) {
            addCriterion("selfCheckingsRate not between", value1, value2, "selfcheckingsrate");
            return (Criteria) this;
        }

        public Criteria andFilerateIsNull() {
            addCriterion("fileRate is null");
            return (Criteria) this;
        }

        public Criteria andFilerateIsNotNull() {
            addCriterion("fileRate is not null");
            return (Criteria) this;
        }

        public Criteria andFilerateEqualTo(String value) {
            addCriterion("fileRate =", value, "filerate");
            return (Criteria) this;
        }

        public Criteria andFilerateNotEqualTo(String value) {
            addCriterion("fileRate <>", value, "filerate");
            return (Criteria) this;
        }

        public Criteria andFilerateGreaterThan(String value) {
            addCriterion("fileRate >", value, "filerate");
            return (Criteria) this;
        }

        public Criteria andFilerateGreaterThanOrEqualTo(String value) {
            addCriterion("fileRate >=", value, "filerate");
            return (Criteria) this;
        }

        public Criteria andFilerateLessThan(String value) {
            addCriterion("fileRate <", value, "filerate");
            return (Criteria) this;
        }

        public Criteria andFilerateLessThanOrEqualTo(String value) {
            addCriterion("fileRate <=", value, "filerate");
            return (Criteria) this;
        }

        public Criteria andFilerateLike(String value) {
            addCriterion("fileRate like", value, "filerate");
            return (Criteria) this;
        }

        public Criteria andFilerateNotLike(String value) {
            addCriterion("fileRate not like", value, "filerate");
            return (Criteria) this;
        }

        public Criteria andFilerateIn(List<String> values) {
            addCriterion("fileRate in", values, "filerate");
            return (Criteria) this;
        }

        public Criteria andFilerateNotIn(List<String> values) {
            addCriterion("fileRate not in", values, "filerate");
            return (Criteria) this;
        }

        public Criteria andFilerateBetween(String value1, String value2) {
            addCriterion("fileRate between", value1, value2, "filerate");
            return (Criteria) this;
        }

        public Criteria andFilerateNotBetween(String value1, String value2) {
            addCriterion("fileRate not between", value1, value2, "filerate");
            return (Criteria) this;
        }

        public Criteria andRaisingratesIsNull() {
            addCriterion("raisingRates is null");
            return (Criteria) this;
        }

        public Criteria andRaisingratesIsNotNull() {
            addCriterion("raisingRates is not null");
            return (Criteria) this;
        }

        public Criteria andRaisingratesEqualTo(String value) {
            addCriterion("raisingRates =", value, "raisingrates");
            return (Criteria) this;
        }

        public Criteria andRaisingratesNotEqualTo(String value) {
            addCriterion("raisingRates <>", value, "raisingrates");
            return (Criteria) this;
        }

        public Criteria andRaisingratesGreaterThan(String value) {
            addCriterion("raisingRates >", value, "raisingrates");
            return (Criteria) this;
        }

        public Criteria andRaisingratesGreaterThanOrEqualTo(String value) {
            addCriterion("raisingRates >=", value, "raisingrates");
            return (Criteria) this;
        }

        public Criteria andRaisingratesLessThan(String value) {
            addCriterion("raisingRates <", value, "raisingrates");
            return (Criteria) this;
        }

        public Criteria andRaisingratesLessThanOrEqualTo(String value) {
            addCriterion("raisingRates <=", value, "raisingrates");
            return (Criteria) this;
        }

        public Criteria andRaisingratesLike(String value) {
            addCriterion("raisingRates like", value, "raisingrates");
            return (Criteria) this;
        }

        public Criteria andRaisingratesNotLike(String value) {
            addCriterion("raisingRates not like", value, "raisingrates");
            return (Criteria) this;
        }

        public Criteria andRaisingratesIn(List<String> values) {
            addCriterion("raisingRates in", values, "raisingrates");
            return (Criteria) this;
        }

        public Criteria andRaisingratesNotIn(List<String> values) {
            addCriterion("raisingRates not in", values, "raisingrates");
            return (Criteria) this;
        }

        public Criteria andRaisingratesBetween(String value1, String value2) {
            addCriterion("raisingRates between", value1, value2, "raisingrates");
            return (Criteria) this;
        }

        public Criteria andRaisingratesNotBetween(String value1, String value2) {
            addCriterion("raisingRates not between", value1, value2, "raisingrates");
            return (Criteria) this;
        }

        public Criteria andAfforestationrateIsNull() {
            addCriterion("afforestationRate is null");
            return (Criteria) this;
        }

        public Criteria andAfforestationrateIsNotNull() {
            addCriterion("afforestationRate is not null");
            return (Criteria) this;
        }

        public Criteria andAfforestationrateEqualTo(String value) {
            addCriterion("afforestationRate =", value, "afforestationrate");
            return (Criteria) this;
        }

        public Criteria andAfforestationrateNotEqualTo(String value) {
            addCriterion("afforestationRate <>", value, "afforestationrate");
            return (Criteria) this;
        }

        public Criteria andAfforestationrateGreaterThan(String value) {
            addCriterion("afforestationRate >", value, "afforestationrate");
            return (Criteria) this;
        }

        public Criteria andAfforestationrateGreaterThanOrEqualTo(String value) {
            addCriterion("afforestationRate >=", value, "afforestationrate");
            return (Criteria) this;
        }

        public Criteria andAfforestationrateLessThan(String value) {
            addCriterion("afforestationRate <", value, "afforestationrate");
            return (Criteria) this;
        }

        public Criteria andAfforestationrateLessThanOrEqualTo(String value) {
            addCriterion("afforestationRate <=", value, "afforestationrate");
            return (Criteria) this;
        }

        public Criteria andAfforestationrateLike(String value) {
            addCriterion("afforestationRate like", value, "afforestationrate");
            return (Criteria) this;
        }

        public Criteria andAfforestationrateNotLike(String value) {
            addCriterion("afforestationRate not like", value, "afforestationrate");
            return (Criteria) this;
        }

        public Criteria andAfforestationrateIn(List<String> values) {
            addCriterion("afforestationRate in", values, "afforestationrate");
            return (Criteria) this;
        }

        public Criteria andAfforestationrateNotIn(List<String> values) {
            addCriterion("afforestationRate not in", values, "afforestationrate");
            return (Criteria) this;
        }

        public Criteria andAfforestationrateBetween(String value1, String value2) {
            addCriterion("afforestationRate between", value1, value2, "afforestationrate");
            return (Criteria) this;
        }

        public Criteria andAfforestationrateNotBetween(String value1, String value2) {
            addCriterion("afforestationRate not between", value1, value2, "afforestationrate");
            return (Criteria) this;
        }

        public Criteria andThemanagementrateIsNull() {
            addCriterion("theManagementRate is null");
            return (Criteria) this;
        }

        public Criteria andThemanagementrateIsNotNull() {
            addCriterion("theManagementRate is not null");
            return (Criteria) this;
        }

        public Criteria andThemanagementrateEqualTo(String value) {
            addCriterion("theManagementRate =", value, "themanagementrate");
            return (Criteria) this;
        }

        public Criteria andThemanagementrateNotEqualTo(String value) {
            addCriterion("theManagementRate <>", value, "themanagementrate");
            return (Criteria) this;
        }

        public Criteria andThemanagementrateGreaterThan(String value) {
            addCriterion("theManagementRate >", value, "themanagementrate");
            return (Criteria) this;
        }

        public Criteria andThemanagementrateGreaterThanOrEqualTo(String value) {
            addCriterion("theManagementRate >=", value, "themanagementrate");
            return (Criteria) this;
        }

        public Criteria andThemanagementrateLessThan(String value) {
            addCriterion("theManagementRate <", value, "themanagementrate");
            return (Criteria) this;
        }

        public Criteria andThemanagementrateLessThanOrEqualTo(String value) {
            addCriterion("theManagementRate <=", value, "themanagementrate");
            return (Criteria) this;
        }

        public Criteria andThemanagementrateLike(String value) {
            addCriterion("theManagementRate like", value, "themanagementrate");
            return (Criteria) this;
        }

        public Criteria andThemanagementrateNotLike(String value) {
            addCriterion("theManagementRate not like", value, "themanagementrate");
            return (Criteria) this;
        }

        public Criteria andThemanagementrateIn(List<String> values) {
            addCriterion("theManagementRate in", values, "themanagementrate");
            return (Criteria) this;
        }

        public Criteria andThemanagementrateNotIn(List<String> values) {
            addCriterion("theManagementRate not in", values, "themanagementrate");
            return (Criteria) this;
        }

        public Criteria andThemanagementrateBetween(String value1, String value2) {
            addCriterion("theManagementRate between", value1, value2, "themanagementrate");
            return (Criteria) this;
        }

        public Criteria andThemanagementrateNotBetween(String value1, String value2) {
            addCriterion("theManagementRate not between", value1, value2, "themanagementrate");
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