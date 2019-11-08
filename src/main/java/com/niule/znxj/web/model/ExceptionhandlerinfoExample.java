package com.niule.znxj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExceptionhandlerinfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExceptionhandlerinfoExample() {
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

        public Criteria andReportidIsNull() {
            addCriterion("reportid is null");
            return (Criteria) this;
        }

        public Criteria andReportidIsNotNull() {
            addCriterion("reportid is not null");
            return (Criteria) this;
        }

        public Criteria andReportidEqualTo(Long value) {
            addCriterion("reportid =", value, "reportid");
            return (Criteria) this;
        }

        public Criteria andReportidNotEqualTo(Long value) {
            addCriterion("reportid <>", value, "reportid");
            return (Criteria) this;
        }

        public Criteria andReportidGreaterThan(Long value) {
            addCriterion("reportid >", value, "reportid");
            return (Criteria) this;
        }

        public Criteria andReportidGreaterThanOrEqualTo(Long value) {
            addCriterion("reportid >=", value, "reportid");
            return (Criteria) this;
        }

        public Criteria andReportidLessThan(Long value) {
            addCriterion("reportid <", value, "reportid");
            return (Criteria) this;
        }

        public Criteria andReportidLessThanOrEqualTo(Long value) {
            addCriterion("reportid <=", value, "reportid");
            return (Criteria) this;
        }

        public Criteria andReportidIn(List<Long> values) {
            addCriterion("reportid in", values, "reportid");
            return (Criteria) this;
        }

        public Criteria andReportidNotIn(List<Long> values) {
            addCriterion("reportid not in", values, "reportid");
            return (Criteria) this;
        }

        public Criteria andReportidBetween(Long value1, Long value2) {
            addCriterion("reportid between", value1, value2, "reportid");
            return (Criteria) this;
        }

        public Criteria andReportidNotBetween(Long value1, Long value2) {
            addCriterion("reportid not between", value1, value2, "reportid");
            return (Criteria) this;
        }

        public Criteria andTaskcodeIsNull() {
            addCriterion("taskcode is null");
            return (Criteria) this;
        }

        public Criteria andTaskcodeIsNotNull() {
            addCriterion("taskcode is not null");
            return (Criteria) this;
        }

        public Criteria andTaskcodeEqualTo(String value) {
            addCriterion("taskcode =", value, "taskcode");
            return (Criteria) this;
        }

        public Criteria andTaskcodeNotEqualTo(String value) {
            addCriterion("taskcode <>", value, "taskcode");
            return (Criteria) this;
        }

        public Criteria andTaskcodeGreaterThan(String value) {
            addCriterion("taskcode >", value, "taskcode");
            return (Criteria) this;
        }

        public Criteria andTaskcodeGreaterThanOrEqualTo(String value) {
            addCriterion("taskcode >=", value, "taskcode");
            return (Criteria) this;
        }

        public Criteria andTaskcodeLessThan(String value) {
            addCriterion("taskcode <", value, "taskcode");
            return (Criteria) this;
        }

        public Criteria andTaskcodeLessThanOrEqualTo(String value) {
            addCriterion("taskcode <=", value, "taskcode");
            return (Criteria) this;
        }

        public Criteria andTaskcodeLike(String value) {
            addCriterion("taskcode like", value, "taskcode");
            return (Criteria) this;
        }

        public Criteria andTaskcodeNotLike(String value) {
            addCriterion("taskcode not like", value, "taskcode");
            return (Criteria) this;
        }

        public Criteria andTaskcodeIn(List<String> values) {
            addCriterion("taskcode in", values, "taskcode");
            return (Criteria) this;
        }

        public Criteria andTaskcodeNotIn(List<String> values) {
            addCriterion("taskcode not in", values, "taskcode");
            return (Criteria) this;
        }

        public Criteria andTaskcodeBetween(String value1, String value2) {
            addCriterion("taskcode between", value1, value2, "taskcode");
            return (Criteria) this;
        }

        public Criteria andTaskcodeNotBetween(String value1, String value2) {
            addCriterion("taskcode not between", value1, value2, "taskcode");
            return (Criteria) this;
        }

        public Criteria andCheckuseridIsNull() {
            addCriterion("checkuserid is null");
            return (Criteria) this;
        }

        public Criteria andCheckuseridIsNotNull() {
            addCriterion("checkuserid is not null");
            return (Criteria) this;
        }

        public Criteria andCheckuseridEqualTo(Long value) {
            addCriterion("checkuserid =", value, "checkuserid");
            return (Criteria) this;
        }

        public Criteria andCheckuseridNotEqualTo(Long value) {
            addCriterion("checkuserid <>", value, "checkuserid");
            return (Criteria) this;
        }

        public Criteria andCheckuseridGreaterThan(Long value) {
            addCriterion("checkuserid >", value, "checkuserid");
            return (Criteria) this;
        }

        public Criteria andCheckuseridGreaterThanOrEqualTo(Long value) {
            addCriterion("checkuserid >=", value, "checkuserid");
            return (Criteria) this;
        }

        public Criteria andCheckuseridLessThan(Long value) {
            addCriterion("checkuserid <", value, "checkuserid");
            return (Criteria) this;
        }

        public Criteria andCheckuseridLessThanOrEqualTo(Long value) {
            addCriterion("checkuserid <=", value, "checkuserid");
            return (Criteria) this;
        }

        public Criteria andCheckuseridIn(List<Long> values) {
            addCriterion("checkuserid in", values, "checkuserid");
            return (Criteria) this;
        }

        public Criteria andCheckuseridNotIn(List<Long> values) {
            addCriterion("checkuserid not in", values, "checkuserid");
            return (Criteria) this;
        }

        public Criteria andCheckuseridBetween(Long value1, Long value2) {
            addCriterion("checkuserid between", value1, value2, "checkuserid");
            return (Criteria) this;
        }

        public Criteria andCheckuseridNotBetween(Long value1, Long value2) {
            addCriterion("checkuserid not between", value1, value2, "checkuserid");
            return (Criteria) this;
        }

        public Criteria andDescontentIsNull() {
            addCriterion("descontent is null");
            return (Criteria) this;
        }

        public Criteria andDescontentIsNotNull() {
            addCriterion("descontent is not null");
            return (Criteria) this;
        }

        public Criteria andDescontentEqualTo(String value) {
            addCriterion("descontent =", value, "descontent");
            return (Criteria) this;
        }

        public Criteria andDescontentNotEqualTo(String value) {
            addCriterion("descontent <>", value, "descontent");
            return (Criteria) this;
        }

        public Criteria andDescontentGreaterThan(String value) {
            addCriterion("descontent >", value, "descontent");
            return (Criteria) this;
        }

        public Criteria andDescontentGreaterThanOrEqualTo(String value) {
            addCriterion("descontent >=", value, "descontent");
            return (Criteria) this;
        }

        public Criteria andDescontentLessThan(String value) {
            addCriterion("descontent <", value, "descontent");
            return (Criteria) this;
        }

        public Criteria andDescontentLessThanOrEqualTo(String value) {
            addCriterion("descontent <=", value, "descontent");
            return (Criteria) this;
        }

        public Criteria andDescontentLike(String value) {
            addCriterion("descontent like", value, "descontent");
            return (Criteria) this;
        }

        public Criteria andDescontentNotLike(String value) {
            addCriterion("descontent not like", value, "descontent");
            return (Criteria) this;
        }

        public Criteria andDescontentIn(List<String> values) {
            addCriterion("descontent in", values, "descontent");
            return (Criteria) this;
        }

        public Criteria andDescontentNotIn(List<String> values) {
            addCriterion("descontent not in", values, "descontent");
            return (Criteria) this;
        }

        public Criteria andDescontentBetween(String value1, String value2) {
            addCriterion("descontent between", value1, value2, "descontent");
            return (Criteria) this;
        }

        public Criteria andDescontentNotBetween(String value1, String value2) {
            addCriterion("descontent not between", value1, value2, "descontent");
            return (Criteria) this;
        }

        public Criteria andAttachmentIsNull() {
            addCriterion("attachment is null");
            return (Criteria) this;
        }

        public Criteria andAttachmentIsNotNull() {
            addCriterion("attachment is not null");
            return (Criteria) this;
        }

        public Criteria andAttachmentEqualTo(String value) {
            addCriterion("attachment =", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentNotEqualTo(String value) {
            addCriterion("attachment <>", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentGreaterThan(String value) {
            addCriterion("attachment >", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentGreaterThanOrEqualTo(String value) {
            addCriterion("attachment >=", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentLessThan(String value) {
            addCriterion("attachment <", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentLessThanOrEqualTo(String value) {
            addCriterion("attachment <=", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentLike(String value) {
            addCriterion("attachment like", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentNotLike(String value) {
            addCriterion("attachment not like", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentIn(List<String> values) {
            addCriterion("attachment in", values, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentNotIn(List<String> values) {
            addCriterion("attachment not in", values, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentBetween(String value1, String value2) {
            addCriterion("attachment between", value1, value2, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentNotBetween(String value1, String value2) {
            addCriterion("attachment not between", value1, value2, "attachment");
            return (Criteria) this;
        }

        public Criteria andOperatornameIsNull() {
            addCriterion("operatorname is null");
            return (Criteria) this;
        }

        public Criteria andOperatornameIsNotNull() {
            addCriterion("operatorname is not null");
            return (Criteria) this;
        }

        public Criteria andOperatornameEqualTo(String value) {
            addCriterion("operatorname =", value, "operatorname");
            return (Criteria) this;
        }

        public Criteria andOperatornameNotEqualTo(String value) {
            addCriterion("operatorname <>", value, "operatorname");
            return (Criteria) this;
        }

        public Criteria andOperatornameGreaterThan(String value) {
            addCriterion("operatorname >", value, "operatorname");
            return (Criteria) this;
        }

        public Criteria andOperatornameGreaterThanOrEqualTo(String value) {
            addCriterion("operatorname >=", value, "operatorname");
            return (Criteria) this;
        }

        public Criteria andOperatornameLessThan(String value) {
            addCriterion("operatorname <", value, "operatorname");
            return (Criteria) this;
        }

        public Criteria andOperatornameLessThanOrEqualTo(String value) {
            addCriterion("operatorname <=", value, "operatorname");
            return (Criteria) this;
        }

        public Criteria andOperatornameLike(String value) {
            addCriterion("operatorname like", value, "operatorname");
            return (Criteria) this;
        }

        public Criteria andOperatornameNotLike(String value) {
            addCriterion("operatorname not like", value, "operatorname");
            return (Criteria) this;
        }

        public Criteria andOperatornameIn(List<String> values) {
            addCriterion("operatorname in", values, "operatorname");
            return (Criteria) this;
        }

        public Criteria andOperatornameNotIn(List<String> values) {
            addCriterion("operatorname not in", values, "operatorname");
            return (Criteria) this;
        }

        public Criteria andOperatornameBetween(String value1, String value2) {
            addCriterion("operatorname between", value1, value2, "operatorname");
            return (Criteria) this;
        }

        public Criteria andOperatornameNotBetween(String value1, String value2) {
            addCriterion("operatorname not between", value1, value2, "operatorname");
            return (Criteria) this;
        }

        public Criteria andReporttimeIsNull() {
            addCriterion("reporttime is null");
            return (Criteria) this;
        }

        public Criteria andReporttimeIsNotNull() {
            addCriterion("reporttime is not null");
            return (Criteria) this;
        }

        public Criteria andReporttimeEqualTo(Date value) {
            addCriterion("reporttime =", value, "reporttime");
            return (Criteria) this;
        }

        public Criteria andReporttimeNotEqualTo(Date value) {
            addCriterion("reporttime <>", value, "reporttime");
            return (Criteria) this;
        }

        public Criteria andReporttimeGreaterThan(Date value) {
            addCriterion("reporttime >", value, "reporttime");
            return (Criteria) this;
        }

        public Criteria andReporttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("reporttime >=", value, "reporttime");
            return (Criteria) this;
        }

        public Criteria andReporttimeLessThan(Date value) {
            addCriterion("reporttime <", value, "reporttime");
            return (Criteria) this;
        }

        public Criteria andReporttimeLessThanOrEqualTo(Date value) {
            addCriterion("reporttime <=", value, "reporttime");
            return (Criteria) this;
        }

        public Criteria andReporttimeIn(List<Date> values) {
            addCriterion("reporttime in", values, "reporttime");
            return (Criteria) this;
        }

        public Criteria andReporttimeNotIn(List<Date> values) {
            addCriterion("reporttime not in", values, "reporttime");
            return (Criteria) this;
        }

        public Criteria andReporttimeBetween(Date value1, Date value2) {
            addCriterion("reporttime between", value1, value2, "reporttime");
            return (Criteria) this;
        }

        public Criteria andReporttimeNotBetween(Date value1, Date value2) {
            addCriterion("reporttime not between", value1, value2, "reporttime");
            return (Criteria) this;
        }

        public Criteria andConfirmtimeIsNull() {
            addCriterion("confirmtime is null");
            return (Criteria) this;
        }

        public Criteria andConfirmtimeIsNotNull() {
            addCriterion("confirmtime is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmtimeEqualTo(Date value) {
            addCriterion("confirmtime =", value, "confirmtime");
            return (Criteria) this;
        }

        public Criteria andConfirmtimeNotEqualTo(Date value) {
            addCriterion("confirmtime <>", value, "confirmtime");
            return (Criteria) this;
        }

        public Criteria andConfirmtimeGreaterThan(Date value) {
            addCriterion("confirmtime >", value, "confirmtime");
            return (Criteria) this;
        }

        public Criteria andConfirmtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("confirmtime >=", value, "confirmtime");
            return (Criteria) this;
        }

        public Criteria andConfirmtimeLessThan(Date value) {
            addCriterion("confirmtime <", value, "confirmtime");
            return (Criteria) this;
        }

        public Criteria andConfirmtimeLessThanOrEqualTo(Date value) {
            addCriterion("confirmtime <=", value, "confirmtime");
            return (Criteria) this;
        }

        public Criteria andConfirmtimeIn(List<Date> values) {
            addCriterion("confirmtime in", values, "confirmtime");
            return (Criteria) this;
        }

        public Criteria andConfirmtimeNotIn(List<Date> values) {
            addCriterion("confirmtime not in", values, "confirmtime");
            return (Criteria) this;
        }

        public Criteria andConfirmtimeBetween(Date value1, Date value2) {
            addCriterion("confirmtime between", value1, value2, "confirmtime");
            return (Criteria) this;
        }

        public Criteria andConfirmtimeNotBetween(Date value1, Date value2) {
            addCriterion("confirmtime not between", value1, value2, "confirmtime");
            return (Criteria) this;
        }

        public Criteria andAppointedtimeIsNull() {
            addCriterion("appointedtime is null");
            return (Criteria) this;
        }

        public Criteria andAppointedtimeIsNotNull() {
            addCriterion("appointedtime is not null");
            return (Criteria) this;
        }

        public Criteria andAppointedtimeEqualTo(Date value) {
            addCriterion("appointedtime =", value, "appointedtime");
            return (Criteria) this;
        }

        public Criteria andAppointedtimeNotEqualTo(Date value) {
            addCriterion("appointedtime <>", value, "appointedtime");
            return (Criteria) this;
        }

        public Criteria andAppointedtimeGreaterThan(Date value) {
            addCriterion("appointedtime >", value, "appointedtime");
            return (Criteria) this;
        }

        public Criteria andAppointedtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("appointedtime >=", value, "appointedtime");
            return (Criteria) this;
        }

        public Criteria andAppointedtimeLessThan(Date value) {
            addCriterion("appointedtime <", value, "appointedtime");
            return (Criteria) this;
        }

        public Criteria andAppointedtimeLessThanOrEqualTo(Date value) {
            addCriterion("appointedtime <=", value, "appointedtime");
            return (Criteria) this;
        }

        public Criteria andAppointedtimeIn(List<Date> values) {
            addCriterion("appointedtime in", values, "appointedtime");
            return (Criteria) this;
        }

        public Criteria andAppointedtimeNotIn(List<Date> values) {
            addCriterion("appointedtime not in", values, "appointedtime");
            return (Criteria) this;
        }

        public Criteria andAppointedtimeBetween(Date value1, Date value2) {
            addCriterion("appointedtime between", value1, value2, "appointedtime");
            return (Criteria) this;
        }

        public Criteria andAppointedtimeNotBetween(Date value1, Date value2) {
            addCriterion("appointedtime not between", value1, value2, "appointedtime");
            return (Criteria) this;
        }

        public Criteria andExceptionclosetimeIsNull() {
            addCriterion("exceptionclosetime is null");
            return (Criteria) this;
        }

        public Criteria andExceptionclosetimeIsNotNull() {
            addCriterion("exceptionclosetime is not null");
            return (Criteria) this;
        }

        public Criteria andExceptionclosetimeEqualTo(Date value) {
            addCriterion("exceptionclosetime =", value, "exceptionclosetime");
            return (Criteria) this;
        }

        public Criteria andExceptionclosetimeNotEqualTo(Date value) {
            addCriterion("exceptionclosetime <>", value, "exceptionclosetime");
            return (Criteria) this;
        }

        public Criteria andExceptionclosetimeGreaterThan(Date value) {
            addCriterion("exceptionclosetime >", value, "exceptionclosetime");
            return (Criteria) this;
        }

        public Criteria andExceptionclosetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("exceptionclosetime >=", value, "exceptionclosetime");
            return (Criteria) this;
        }

        public Criteria andExceptionclosetimeLessThan(Date value) {
            addCriterion("exceptionclosetime <", value, "exceptionclosetime");
            return (Criteria) this;
        }

        public Criteria andExceptionclosetimeLessThanOrEqualTo(Date value) {
            addCriterion("exceptionclosetime <=", value, "exceptionclosetime");
            return (Criteria) this;
        }

        public Criteria andExceptionclosetimeIn(List<Date> values) {
            addCriterion("exceptionclosetime in", values, "exceptionclosetime");
            return (Criteria) this;
        }

        public Criteria andExceptionclosetimeNotIn(List<Date> values) {
            addCriterion("exceptionclosetime not in", values, "exceptionclosetime");
            return (Criteria) this;
        }

        public Criteria andExceptionclosetimeBetween(Date value1, Date value2) {
            addCriterion("exceptionclosetime between", value1, value2, "exceptionclosetime");
            return (Criteria) this;
        }

        public Criteria andExceptionclosetimeNotBetween(Date value1, Date value2) {
            addCriterion("exceptionclosetime not between", value1, value2, "exceptionclosetime");
            return (Criteria) this;
        }

        public Criteria andExceptionstateIsNull() {
            addCriterion("exceptionstate is null");
            return (Criteria) this;
        }

        public Criteria andExceptionstateIsNotNull() {
            addCriterion("exceptionstate is not null");
            return (Criteria) this;
        }

        public Criteria andExceptionstateEqualTo(Integer value) {
            addCriterion("exceptionstate =", value, "exceptionstate");
            return (Criteria) this;
        }

        public Criteria andExceptionstateNotEqualTo(Integer value) {
            addCriterion("exceptionstate <>", value, "exceptionstate");
            return (Criteria) this;
        }

        public Criteria andExceptionstateGreaterThan(Integer value) {
            addCriterion("exceptionstate >", value, "exceptionstate");
            return (Criteria) this;
        }

        public Criteria andExceptionstateGreaterThanOrEqualTo(Integer value) {
            addCriterion("exceptionstate >=", value, "exceptionstate");
            return (Criteria) this;
        }

        public Criteria andExceptionstateLessThan(Integer value) {
            addCriterion("exceptionstate <", value, "exceptionstate");
            return (Criteria) this;
        }

        public Criteria andExceptionstateLessThanOrEqualTo(Integer value) {
            addCriterion("exceptionstate <=", value, "exceptionstate");
            return (Criteria) this;
        }

        public Criteria andExceptionstateIn(List<Integer> values) {
            addCriterion("exceptionstate in", values, "exceptionstate");
            return (Criteria) this;
        }

        public Criteria andExceptionstateNotIn(List<Integer> values) {
            addCriterion("exceptionstate not in", values, "exceptionstate");
            return (Criteria) this;
        }

        public Criteria andExceptionstateBetween(Integer value1, Integer value2) {
            addCriterion("exceptionstate between", value1, value2, "exceptionstate");
            return (Criteria) this;
        }

        public Criteria andExceptionstateNotBetween(Integer value1, Integer value2) {
            addCriterion("exceptionstate not between", value1, value2, "exceptionstate");
            return (Criteria) this;
        }
    }

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