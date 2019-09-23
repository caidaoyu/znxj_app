package com.niule.znxj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskplaninfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TaskplaninfoExample() {
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andIdentifyingidIsNull() {
            addCriterion("identifyingid is null");
            return (Criteria) this;
        }

        public Criteria andIdentifyingidIsNotNull() {
            addCriterion("identifyingid is not null");
            return (Criteria) this;
        }

        public Criteria andIdentifyingidEqualTo(String value) {
            addCriterion("identifyingid =", value, "identifyingid");
            return (Criteria) this;
        }

        public Criteria andIdentifyingidNotEqualTo(String value) {
            addCriterion("identifyingid <>", value, "identifyingid");
            return (Criteria) this;
        }

        public Criteria andIdentifyingidGreaterThan(String value) {
            addCriterion("identifyingid >", value, "identifyingid");
            return (Criteria) this;
        }

        public Criteria andIdentifyingidGreaterThanOrEqualTo(String value) {
            addCriterion("identifyingid >=", value, "identifyingid");
            return (Criteria) this;
        }

        public Criteria andIdentifyingidLessThan(String value) {
            addCriterion("identifyingid <", value, "identifyingid");
            return (Criteria) this;
        }

        public Criteria andIdentifyingidLessThanOrEqualTo(String value) {
            addCriterion("identifyingid <=", value, "identifyingid");
            return (Criteria) this;
        }

        public Criteria andIdentifyingidLike(String value) {
            addCriterion("identifyingid like", value, "identifyingid");
            return (Criteria) this;
        }

        public Criteria andIdentifyingidNotLike(String value) {
            addCriterion("identifyingid not like", value, "identifyingid");
            return (Criteria) this;
        }

        public Criteria andIdentifyingidIn(List<String> values) {
            addCriterion("identifyingid in", values, "identifyingid");
            return (Criteria) this;
        }

        public Criteria andIdentifyingidNotIn(List<String> values) {
            addCriterion("identifyingid not in", values, "identifyingid");
            return (Criteria) this;
        }

        public Criteria andIdentifyingidBetween(String value1, String value2) {
            addCriterion("identifyingid between", value1, value2, "identifyingid");
            return (Criteria) this;
        }

        public Criteria andIdentifyingidNotBetween(String value1, String value2) {
            addCriterion("identifyingid not between", value1, value2, "identifyingid");
            return (Criteria) this;
        }

        public Criteria andCustomidIsNull() {
            addCriterion("customid is null");
            return (Criteria) this;
        }

        public Criteria andCustomidIsNotNull() {
            addCriterion("customid is not null");
            return (Criteria) this;
        }

        public Criteria andCustomidEqualTo(String value) {
            addCriterion("customid =", value, "customid");
            return (Criteria) this;
        }

        public Criteria andCustomidNotEqualTo(String value) {
            addCriterion("customid <>", value, "customid");
            return (Criteria) this;
        }

        public Criteria andCustomidGreaterThan(String value) {
            addCriterion("customid >", value, "customid");
            return (Criteria) this;
        }

        public Criteria andCustomidGreaterThanOrEqualTo(String value) {
            addCriterion("customid >=", value, "customid");
            return (Criteria) this;
        }

        public Criteria andCustomidLessThan(String value) {
            addCriterion("customid <", value, "customid");
            return (Criteria) this;
        }

        public Criteria andCustomidLessThanOrEqualTo(String value) {
            addCriterion("customid <=", value, "customid");
            return (Criteria) this;
        }

        public Criteria andCustomidLike(String value) {
            addCriterion("customid like", value, "customid");
            return (Criteria) this;
        }

        public Criteria andCustomidNotLike(String value) {
            addCriterion("customid not like", value, "customid");
            return (Criteria) this;
        }

        public Criteria andCustomidIn(List<String> values) {
            addCriterion("customid in", values, "customid");
            return (Criteria) this;
        }

        public Criteria andCustomidNotIn(List<String> values) {
            addCriterion("customid not in", values, "customid");
            return (Criteria) this;
        }

        public Criteria andCustomidBetween(String value1, String value2) {
            addCriterion("customid between", value1, value2, "customid");
            return (Criteria) this;
        }

        public Criteria andCustomidNotBetween(String value1, String value2) {
            addCriterion("customid not between", value1, value2, "customid");
            return (Criteria) this;
        }

        public Criteria andTaskdescIsNull() {
            addCriterion("taskdesc is null");
            return (Criteria) this;
        }

        public Criteria andTaskdescIsNotNull() {
            addCriterion("taskdesc is not null");
            return (Criteria) this;
        }

        public Criteria andTaskdescEqualTo(String value) {
            addCriterion("taskdesc =", value, "taskdesc");
            return (Criteria) this;
        }

        public Criteria andTaskdescNotEqualTo(String value) {
            addCriterion("taskdesc <>", value, "taskdesc");
            return (Criteria) this;
        }

        public Criteria andTaskdescGreaterThan(String value) {
            addCriterion("taskdesc >", value, "taskdesc");
            return (Criteria) this;
        }

        public Criteria andTaskdescGreaterThanOrEqualTo(String value) {
            addCriterion("taskdesc >=", value, "taskdesc");
            return (Criteria) this;
        }

        public Criteria andTaskdescLessThan(String value) {
            addCriterion("taskdesc <", value, "taskdesc");
            return (Criteria) this;
        }

        public Criteria andTaskdescLessThanOrEqualTo(String value) {
            addCriterion("taskdesc <=", value, "taskdesc");
            return (Criteria) this;
        }

        public Criteria andTaskdescLike(String value) {
            addCriterion("taskdesc like", value, "taskdesc");
            return (Criteria) this;
        }

        public Criteria andTaskdescNotLike(String value) {
            addCriterion("taskdesc not like", value, "taskdesc");
            return (Criteria) this;
        }

        public Criteria andTaskdescIn(List<String> values) {
            addCriterion("taskdesc in", values, "taskdesc");
            return (Criteria) this;
        }

        public Criteria andTaskdescNotIn(List<String> values) {
            addCriterion("taskdesc not in", values, "taskdesc");
            return (Criteria) this;
        }

        public Criteria andTaskdescBetween(String value1, String value2) {
            addCriterion("taskdesc between", value1, value2, "taskdesc");
            return (Criteria) this;
        }

        public Criteria andTaskdescNotBetween(String value1, String value2) {
            addCriterion("taskdesc not between", value1, value2, "taskdesc");
            return (Criteria) this;
        }

        public Criteria andIssequentiallyIsNull() {
            addCriterion("issequentially is null");
            return (Criteria) this;
        }

        public Criteria andIssequentiallyIsNotNull() {
            addCriterion("issequentially is not null");
            return (Criteria) this;
        }

        public Criteria andIssequentiallyEqualTo(Integer value) {
            addCriterion("issequentially =", value, "issequentially");
            return (Criteria) this;
        }

        public Criteria andIssequentiallyNotEqualTo(Integer value) {
            addCriterion("issequentially <>", value, "issequentially");
            return (Criteria) this;
        }

        public Criteria andIssequentiallyGreaterThan(Integer value) {
            addCriterion("issequentially >", value, "issequentially");
            return (Criteria) this;
        }

        public Criteria andIssequentiallyGreaterThanOrEqualTo(Integer value) {
            addCriterion("issequentially >=", value, "issequentially");
            return (Criteria) this;
        }

        public Criteria andIssequentiallyLessThan(Integer value) {
            addCriterion("issequentially <", value, "issequentially");
            return (Criteria) this;
        }

        public Criteria andIssequentiallyLessThanOrEqualTo(Integer value) {
            addCriterion("issequentially <=", value, "issequentially");
            return (Criteria) this;
        }

        public Criteria andIssequentiallyIn(List<Integer> values) {
            addCriterion("issequentially in", values, "issequentially");
            return (Criteria) this;
        }

        public Criteria andIssequentiallyNotIn(List<Integer> values) {
            addCriterion("issequentially not in", values, "issequentially");
            return (Criteria) this;
        }

        public Criteria andIssequentiallyBetween(Integer value1, Integer value2) {
            addCriterion("issequentially between", value1, value2, "issequentially");
            return (Criteria) this;
        }

        public Criteria andIssequentiallyNotBetween(Integer value1, Integer value2) {
            addCriterion("issequentially not between", value1, value2, "issequentially");
            return (Criteria) this;
        }

        public Criteria andCreateuserIsNull() {
            addCriterion("createuser is null");
            return (Criteria) this;
        }

        public Criteria andCreateuserIsNotNull() {
            addCriterion("createuser is not null");
            return (Criteria) this;
        }

        public Criteria andCreateuserEqualTo(String value) {
            addCriterion("createuser =", value, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserNotEqualTo(String value) {
            addCriterion("createuser <>", value, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserGreaterThan(String value) {
            addCriterion("createuser >", value, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserGreaterThanOrEqualTo(String value) {
            addCriterion("createuser >=", value, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserLessThan(String value) {
            addCriterion("createuser <", value, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserLessThanOrEqualTo(String value) {
            addCriterion("createuser <=", value, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserLike(String value) {
            addCriterion("createuser like", value, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserNotLike(String value) {
            addCriterion("createuser not like", value, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserIn(List<String> values) {
            addCriterion("createuser in", values, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserNotIn(List<String> values) {
            addCriterion("createuser not in", values, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserBetween(String value1, String value2) {
            addCriterion("createuser between", value1, value2, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserNotBetween(String value1, String value2) {
            addCriterion("createuser not between", value1, value2, "createuser");
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

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andAuditstatusIsNull() {
            addCriterion("auditstatus is null");
            return (Criteria) this;
        }

        public Criteria andAuditstatusIsNotNull() {
            addCriterion("auditstatus is not null");
            return (Criteria) this;
        }

        public Criteria andAuditstatusEqualTo(Integer value) {
            addCriterion("auditstatus =", value, "auditstatus");
            return (Criteria) this;
        }

        public Criteria andAuditstatusNotEqualTo(Integer value) {
            addCriterion("auditstatus <>", value, "auditstatus");
            return (Criteria) this;
        }

        public Criteria andAuditstatusGreaterThan(Integer value) {
            addCriterion("auditstatus >", value, "auditstatus");
            return (Criteria) this;
        }

        public Criteria andAuditstatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("auditstatus >=", value, "auditstatus");
            return (Criteria) this;
        }

        public Criteria andAuditstatusLessThan(Integer value) {
            addCriterion("auditstatus <", value, "auditstatus");
            return (Criteria) this;
        }

        public Criteria andAuditstatusLessThanOrEqualTo(Integer value) {
            addCriterion("auditstatus <=", value, "auditstatus");
            return (Criteria) this;
        }

        public Criteria andAuditstatusIn(List<Integer> values) {
            addCriterion("auditstatus in", values, "auditstatus");
            return (Criteria) this;
        }

        public Criteria andAuditstatusNotIn(List<Integer> values) {
            addCriterion("auditstatus not in", values, "auditstatus");
            return (Criteria) this;
        }

        public Criteria andAuditstatusBetween(Integer value1, Integer value2) {
            addCriterion("auditstatus between", value1, value2, "auditstatus");
            return (Criteria) this;
        }

        public Criteria andAuditstatusNotBetween(Integer value1, Integer value2) {
            addCriterion("auditstatus not between", value1, value2, "auditstatus");
            return (Criteria) this;
        }

        public Criteria andRevieweduserIsNull() {
            addCriterion("revieweduser is null");
            return (Criteria) this;
        }

        public Criteria andRevieweduserIsNotNull() {
            addCriterion("revieweduser is not null");
            return (Criteria) this;
        }

        public Criteria andRevieweduserEqualTo(String value) {
            addCriterion("revieweduser =", value, "revieweduser");
            return (Criteria) this;
        }

        public Criteria andRevieweduserNotEqualTo(String value) {
            addCriterion("revieweduser <>", value, "revieweduser");
            return (Criteria) this;
        }

        public Criteria andRevieweduserGreaterThan(String value) {
            addCriterion("revieweduser >", value, "revieweduser");
            return (Criteria) this;
        }

        public Criteria andRevieweduserGreaterThanOrEqualTo(String value) {
            addCriterion("revieweduser >=", value, "revieweduser");
            return (Criteria) this;
        }

        public Criteria andRevieweduserLessThan(String value) {
            addCriterion("revieweduser <", value, "revieweduser");
            return (Criteria) this;
        }

        public Criteria andRevieweduserLessThanOrEqualTo(String value) {
            addCriterion("revieweduser <=", value, "revieweduser");
            return (Criteria) this;
        }

        public Criteria andRevieweduserLike(String value) {
            addCriterion("revieweduser like", value, "revieweduser");
            return (Criteria) this;
        }

        public Criteria andRevieweduserNotLike(String value) {
            addCriterion("revieweduser not like", value, "revieweduser");
            return (Criteria) this;
        }

        public Criteria andRevieweduserIn(List<String> values) {
            addCriterion("revieweduser in", values, "revieweduser");
            return (Criteria) this;
        }

        public Criteria andRevieweduserNotIn(List<String> values) {
            addCriterion("revieweduser not in", values, "revieweduser");
            return (Criteria) this;
        }

        public Criteria andRevieweduserBetween(String value1, String value2) {
            addCriterion("revieweduser between", value1, value2, "revieweduser");
            return (Criteria) this;
        }

        public Criteria andRevieweduserNotBetween(String value1, String value2) {
            addCriterion("revieweduser not between", value1, value2, "revieweduser");
            return (Criteria) this;
        }

        public Criteria andReviewedtimeIsNull() {
            addCriterion("reviewedtime is null");
            return (Criteria) this;
        }

        public Criteria andReviewedtimeIsNotNull() {
            addCriterion("reviewedtime is not null");
            return (Criteria) this;
        }

        public Criteria andReviewedtimeEqualTo(Date value) {
            addCriterion("reviewedtime =", value, "reviewedtime");
            return (Criteria) this;
        }

        public Criteria andReviewedtimeNotEqualTo(Date value) {
            addCriterion("reviewedtime <>", value, "reviewedtime");
            return (Criteria) this;
        }

        public Criteria andReviewedtimeGreaterThan(Date value) {
            addCriterion("reviewedtime >", value, "reviewedtime");
            return (Criteria) this;
        }

        public Criteria andReviewedtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("reviewedtime >=", value, "reviewedtime");
            return (Criteria) this;
        }

        public Criteria andReviewedtimeLessThan(Date value) {
            addCriterion("reviewedtime <", value, "reviewedtime");
            return (Criteria) this;
        }

        public Criteria andReviewedtimeLessThanOrEqualTo(Date value) {
            addCriterion("reviewedtime <=", value, "reviewedtime");
            return (Criteria) this;
        }

        public Criteria andReviewedtimeIn(List<Date> values) {
            addCriterion("reviewedtime in", values, "reviewedtime");
            return (Criteria) this;
        }

        public Criteria andReviewedtimeNotIn(List<Date> values) {
            addCriterion("reviewedtime not in", values, "reviewedtime");
            return (Criteria) this;
        }

        public Criteria andReviewedtimeBetween(Date value1, Date value2) {
            addCriterion("reviewedtime between", value1, value2, "reviewedtime");
            return (Criteria) this;
        }

        public Criteria andReviewedtimeNotBetween(Date value1, Date value2) {
            addCriterion("reviewedtime not between", value1, value2, "reviewedtime");
            return (Criteria) this;
        }

        public Criteria andApproveuserIsNull() {
            addCriterion("approveuser is null");
            return (Criteria) this;
        }

        public Criteria andApproveuserIsNotNull() {
            addCriterion("approveuser is not null");
            return (Criteria) this;
        }

        public Criteria andApproveuserEqualTo(String value) {
            addCriterion("approveuser =", value, "approveuser");
            return (Criteria) this;
        }

        public Criteria andApproveuserNotEqualTo(String value) {
            addCriterion("approveuser <>", value, "approveuser");
            return (Criteria) this;
        }

        public Criteria andApproveuserGreaterThan(String value) {
            addCriterion("approveuser >", value, "approveuser");
            return (Criteria) this;
        }

        public Criteria andApproveuserGreaterThanOrEqualTo(String value) {
            addCriterion("approveuser >=", value, "approveuser");
            return (Criteria) this;
        }

        public Criteria andApproveuserLessThan(String value) {
            addCriterion("approveuser <", value, "approveuser");
            return (Criteria) this;
        }

        public Criteria andApproveuserLessThanOrEqualTo(String value) {
            addCriterion("approveuser <=", value, "approveuser");
            return (Criteria) this;
        }

        public Criteria andApproveuserLike(String value) {
            addCriterion("approveuser like", value, "approveuser");
            return (Criteria) this;
        }

        public Criteria andApproveuserNotLike(String value) {
            addCriterion("approveuser not like", value, "approveuser");
            return (Criteria) this;
        }

        public Criteria andApproveuserIn(List<String> values) {
            addCriterion("approveuser in", values, "approveuser");
            return (Criteria) this;
        }

        public Criteria andApproveuserNotIn(List<String> values) {
            addCriterion("approveuser not in", values, "approveuser");
            return (Criteria) this;
        }

        public Criteria andApproveuserBetween(String value1, String value2) {
            addCriterion("approveuser between", value1, value2, "approveuser");
            return (Criteria) this;
        }

        public Criteria andApproveuserNotBetween(String value1, String value2) {
            addCriterion("approveuser not between", value1, value2, "approveuser");
            return (Criteria) this;
        }

        public Criteria andApprovetimeIsNull() {
            addCriterion("approvetime is null");
            return (Criteria) this;
        }

        public Criteria andApprovetimeIsNotNull() {
            addCriterion("approvetime is not null");
            return (Criteria) this;
        }

        public Criteria andApprovetimeEqualTo(Date value) {
            addCriterion("approvetime =", value, "approvetime");
            return (Criteria) this;
        }

        public Criteria andApprovetimeNotEqualTo(Date value) {
            addCriterion("approvetime <>", value, "approvetime");
            return (Criteria) this;
        }

        public Criteria andApprovetimeGreaterThan(Date value) {
            addCriterion("approvetime >", value, "approvetime");
            return (Criteria) this;
        }

        public Criteria andApprovetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("approvetime >=", value, "approvetime");
            return (Criteria) this;
        }

        public Criteria andApprovetimeLessThan(Date value) {
            addCriterion("approvetime <", value, "approvetime");
            return (Criteria) this;
        }

        public Criteria andApprovetimeLessThanOrEqualTo(Date value) {
            addCriterion("approvetime <=", value, "approvetime");
            return (Criteria) this;
        }

        public Criteria andApprovetimeIn(List<Date> values) {
            addCriterion("approvetime in", values, "approvetime");
            return (Criteria) this;
        }

        public Criteria andApprovetimeNotIn(List<Date> values) {
            addCriterion("approvetime not in", values, "approvetime");
            return (Criteria) this;
        }

        public Criteria andApprovetimeBetween(Date value1, Date value2) {
            addCriterion("approvetime between", value1, value2, "approvetime");
            return (Criteria) this;
        }

        public Criteria andApprovetimeNotBetween(Date value1, Date value2) {
            addCriterion("approvetime not between", value1, value2, "approvetime");
            return (Criteria) this;
        }

        public Criteria andTaskversionIsNull() {
            addCriterion("taskversion is null");
            return (Criteria) this;
        }

        public Criteria andTaskversionIsNotNull() {
            addCriterion("taskversion is not null");
            return (Criteria) this;
        }

        public Criteria andTaskversionEqualTo(String value) {
            addCriterion("taskversion =", value, "taskversion");
            return (Criteria) this;
        }

        public Criteria andTaskversionNotEqualTo(String value) {
            addCriterion("taskversion <>", value, "taskversion");
            return (Criteria) this;
        }

        public Criteria andTaskversionGreaterThan(String value) {
            addCriterion("taskversion >", value, "taskversion");
            return (Criteria) this;
        }

        public Criteria andTaskversionGreaterThanOrEqualTo(String value) {
            addCriterion("taskversion >=", value, "taskversion");
            return (Criteria) this;
        }

        public Criteria andTaskversionLessThan(String value) {
            addCriterion("taskversion <", value, "taskversion");
            return (Criteria) this;
        }

        public Criteria andTaskversionLessThanOrEqualTo(String value) {
            addCriterion("taskversion <=", value, "taskversion");
            return (Criteria) this;
        }

        public Criteria andTaskversionLike(String value) {
            addCriterion("taskversion like", value, "taskversion");
            return (Criteria) this;
        }

        public Criteria andTaskversionNotLike(String value) {
            addCriterion("taskversion not like", value, "taskversion");
            return (Criteria) this;
        }

        public Criteria andTaskversionIn(List<String> values) {
            addCriterion("taskversion in", values, "taskversion");
            return (Criteria) this;
        }

        public Criteria andTaskversionNotIn(List<String> values) {
            addCriterion("taskversion not in", values, "taskversion");
            return (Criteria) this;
        }

        public Criteria andTaskversionBetween(String value1, String value2) {
            addCriterion("taskversion between", value1, value2, "taskversion");
            return (Criteria) this;
        }

        public Criteria andTaskversionNotBetween(String value1, String value2) {
            addCriterion("taskversion not between", value1, value2, "taskversion");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updatetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updatetime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updatetime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updatetime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andClassidIsNull() {
            addCriterion("classid is null");
            return (Criteria) this;
        }

        public Criteria andClassidIsNotNull() {
            addCriterion("classid is not null");
            return (Criteria) this;
        }

        public Criteria andClassidEqualTo(Long value) {
            addCriterion("classid =", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidNotEqualTo(Long value) {
            addCriterion("classid <>", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidGreaterThan(Long value) {
            addCriterion("classid >", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidGreaterThanOrEqualTo(Long value) {
            addCriterion("classid >=", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidLessThan(Long value) {
            addCriterion("classid <", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidLessThanOrEqualTo(Long value) {
            addCriterion("classid <=", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidIn(List<Long> values) {
            addCriterion("classid in", values, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidNotIn(List<Long> values) {
            addCriterion("classid not in", values, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidBetween(Long value1, Long value2) {
            addCriterion("classid between", value1, value2, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidNotBetween(Long value1, Long value2) {
            addCriterion("classid not between", value1, value2, "classid");
            return (Criteria) this;
        }

        public Criteria andDirectoridIsNull() {
            addCriterion("directorid is null");
            return (Criteria) this;
        }

        public Criteria andDirectoridIsNotNull() {
            addCriterion("directorid is not null");
            return (Criteria) this;
        }

        public Criteria andDirectoridEqualTo(Long value) {
            addCriterion("directorid =", value, "directorid");
            return (Criteria) this;
        }

        public Criteria andDirectoridNotEqualTo(Long value) {
            addCriterion("directorid <>", value, "directorid");
            return (Criteria) this;
        }

        public Criteria andDirectoridGreaterThan(Long value) {
            addCriterion("directorid >", value, "directorid");
            return (Criteria) this;
        }

        public Criteria andDirectoridGreaterThanOrEqualTo(Long value) {
            addCriterion("directorid >=", value, "directorid");
            return (Criteria) this;
        }

        public Criteria andDirectoridLessThan(Long value) {
            addCriterion("directorid <", value, "directorid");
            return (Criteria) this;
        }

        public Criteria andDirectoridLessThanOrEqualTo(Long value) {
            addCriterion("directorid <=", value, "directorid");
            return (Criteria) this;
        }

        public Criteria andDirectoridIn(List<Long> values) {
            addCriterion("directorid in", values, "directorid");
            return (Criteria) this;
        }

        public Criteria andDirectoridNotIn(List<Long> values) {
            addCriterion("directorid not in", values, "directorid");
            return (Criteria) this;
        }

        public Criteria andDirectoridBetween(Long value1, Long value2) {
            addCriterion("directorid between", value1, value2, "directorid");
            return (Criteria) this;
        }

        public Criteria andDirectoridNotBetween(Long value1, Long value2) {
            addCriterion("directorid not between", value1, value2, "directorid");
            return (Criteria) this;
        }

        public Criteria andMaxdurationIsNull() {
            addCriterion("maxduration is null");
            return (Criteria) this;
        }

        public Criteria andMaxdurationIsNotNull() {
            addCriterion("maxduration is not null");
            return (Criteria) this;
        }

        public Criteria andMaxdurationEqualTo(Integer value) {
            addCriterion("maxduration =", value, "maxduration");
            return (Criteria) this;
        }

        public Criteria andMaxdurationNotEqualTo(Integer value) {
            addCriterion("maxduration <>", value, "maxduration");
            return (Criteria) this;
        }

        public Criteria andMaxdurationGreaterThan(Integer value) {
            addCriterion("maxduration >", value, "maxduration");
            return (Criteria) this;
        }

        public Criteria andMaxdurationGreaterThanOrEqualTo(Integer value) {
            addCriterion("maxduration >=", value, "maxduration");
            return (Criteria) this;
        }

        public Criteria andMaxdurationLessThan(Integer value) {
            addCriterion("maxduration <", value, "maxduration");
            return (Criteria) this;
        }

        public Criteria andMaxdurationLessThanOrEqualTo(Integer value) {
            addCriterion("maxduration <=", value, "maxduration");
            return (Criteria) this;
        }

        public Criteria andMaxdurationIn(List<Integer> values) {
            addCriterion("maxduration in", values, "maxduration");
            return (Criteria) this;
        }

        public Criteria andMaxdurationNotIn(List<Integer> values) {
            addCriterion("maxduration not in", values, "maxduration");
            return (Criteria) this;
        }

        public Criteria andMaxdurationBetween(Integer value1, Integer value2) {
            addCriterion("maxduration between", value1, value2, "maxduration");
            return (Criteria) this;
        }

        public Criteria andMaxdurationNotBetween(Integer value1, Integer value2) {
            addCriterion("maxduration not between", value1, value2, "maxduration");
            return (Criteria) this;
        }

        public Criteria andIssingletimeIsNull() {
            addCriterion("issingletime is null");
            return (Criteria) this;
        }

        public Criteria andIssingletimeIsNotNull() {
            addCriterion("issingletime is not null");
            return (Criteria) this;
        }

        public Criteria andIssingletimeEqualTo(Integer value) {
            addCriterion("issingletime =", value, "issingletime");
            return (Criteria) this;
        }

        public Criteria andIssingletimeNotEqualTo(Integer value) {
            addCriterion("issingletime <>", value, "issingletime");
            return (Criteria) this;
        }

        public Criteria andIssingletimeGreaterThan(Integer value) {
            addCriterion("issingletime >", value, "issingletime");
            return (Criteria) this;
        }

        public Criteria andIssingletimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("issingletime >=", value, "issingletime");
            return (Criteria) this;
        }

        public Criteria andIssingletimeLessThan(Integer value) {
            addCriterion("issingletime <", value, "issingletime");
            return (Criteria) this;
        }

        public Criteria andIssingletimeLessThanOrEqualTo(Integer value) {
            addCriterion("issingletime <=", value, "issingletime");
            return (Criteria) this;
        }

        public Criteria andIssingletimeIn(List<Integer> values) {
            addCriterion("issingletime in", values, "issingletime");
            return (Criteria) this;
        }

        public Criteria andIssingletimeNotIn(List<Integer> values) {
            addCriterion("issingletime not in", values, "issingletime");
            return (Criteria) this;
        }

        public Criteria andIssingletimeBetween(Integer value1, Integer value2) {
            addCriterion("issingletime between", value1, value2, "issingletime");
            return (Criteria) this;
        }

        public Criteria andIssingletimeNotBetween(Integer value1, Integer value2) {
            addCriterion("issingletime not between", value1, value2, "issingletime");
            return (Criteria) this;
        }

        public Criteria andStarttimeIsNull() {
            addCriterion("starttime is null");
            return (Criteria) this;
        }

        public Criteria andStarttimeIsNotNull() {
            addCriterion("starttime is not null");
            return (Criteria) this;
        }

        public Criteria andStarttimeEqualTo(String value) {
            addCriterion("starttime =", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotEqualTo(String value) {
            addCriterion("starttime <>", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThan(String value) {
            addCriterion("starttime >", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThanOrEqualTo(String value) {
            addCriterion("starttime >=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThan(String value) {
            addCriterion("starttime <", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThanOrEqualTo(String value) {
            addCriterion("starttime <=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLike(String value) {
            addCriterion("starttime like", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotLike(String value) {
            addCriterion("starttime not like", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeIn(List<String> values) {
            addCriterion("starttime in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotIn(List<String> values) {
            addCriterion("starttime not in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeBetween(String value1, String value2) {
            addCriterion("starttime between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotBetween(String value1, String value2) {
            addCriterion("starttime not between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNull() {
            addCriterion("endtime is null");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNotNull() {
            addCriterion("endtime is not null");
            return (Criteria) this;
        }

        public Criteria andEndtimeEqualTo(String value) {
            addCriterion("endtime =", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotEqualTo(String value) {
            addCriterion("endtime <>", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThan(String value) {
            addCriterion("endtime >", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThanOrEqualTo(String value) {
            addCriterion("endtime >=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThan(String value) {
            addCriterion("endtime <", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThanOrEqualTo(String value) {
            addCriterion("endtime <=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLike(String value) {
            addCriterion("endtime like", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotLike(String value) {
            addCriterion("endtime not like", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIn(List<String> values) {
            addCriterion("endtime in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotIn(List<String> values) {
            addCriterion("endtime not in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeBetween(String value1, String value2) {
            addCriterion("endtime between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotBetween(String value1, String value2) {
            addCriterion("endtime not between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andImplementtimeIsNull() {
            addCriterion("implementtime is null");
            return (Criteria) this;
        }

        public Criteria andImplementtimeIsNotNull() {
            addCriterion("implementtime is not null");
            return (Criteria) this;
        }

        public Criteria andImplementtimeEqualTo(String value) {
            addCriterion("implementtime =", value, "implementtime");
            return (Criteria) this;
        }

        public Criteria andImplementtimeNotEqualTo(String value) {
            addCriterion("implementtime <>", value, "implementtime");
            return (Criteria) this;
        }

        public Criteria andImplementtimeGreaterThan(String value) {
            addCriterion("implementtime >", value, "implementtime");
            return (Criteria) this;
        }

        public Criteria andImplementtimeGreaterThanOrEqualTo(String value) {
            addCriterion("implementtime >=", value, "implementtime");
            return (Criteria) this;
        }

        public Criteria andImplementtimeLessThan(String value) {
            addCriterion("implementtime <", value, "implementtime");
            return (Criteria) this;
        }

        public Criteria andImplementtimeLessThanOrEqualTo(String value) {
            addCriterion("implementtime <=", value, "implementtime");
            return (Criteria) this;
        }

        public Criteria andImplementtimeLike(String value) {
            addCriterion("implementtime like", value, "implementtime");
            return (Criteria) this;
        }

        public Criteria andImplementtimeNotLike(String value) {
            addCriterion("implementtime not like", value, "implementtime");
            return (Criteria) this;
        }

        public Criteria andImplementtimeIn(List<String> values) {
            addCriterion("implementtime in", values, "implementtime");
            return (Criteria) this;
        }

        public Criteria andImplementtimeNotIn(List<String> values) {
            addCriterion("implementtime not in", values, "implementtime");
            return (Criteria) this;
        }

        public Criteria andImplementtimeBetween(String value1, String value2) {
            addCriterion("implementtime between", value1, value2, "implementtime");
            return (Criteria) this;
        }

        public Criteria andImplementtimeNotBetween(String value1, String value2) {
            addCriterion("implementtime not between", value1, value2, "implementtime");
            return (Criteria) this;
        }

        public Criteria andWeeklytimeIsNull() {
            addCriterion("weeklytime is null");
            return (Criteria) this;
        }

        public Criteria andWeeklytimeIsNotNull() {
            addCriterion("weeklytime is not null");
            return (Criteria) this;
        }

        public Criteria andWeeklytimeEqualTo(String value) {
            addCriterion("weeklytime =", value, "weeklytime");
            return (Criteria) this;
        }

        public Criteria andWeeklytimeNotEqualTo(String value) {
            addCriterion("weeklytime <>", value, "weeklytime");
            return (Criteria) this;
        }

        public Criteria andWeeklytimeGreaterThan(String value) {
            addCriterion("weeklytime >", value, "weeklytime");
            return (Criteria) this;
        }

        public Criteria andWeeklytimeGreaterThanOrEqualTo(String value) {
            addCriterion("weeklytime >=", value, "weeklytime");
            return (Criteria) this;
        }

        public Criteria andWeeklytimeLessThan(String value) {
            addCriterion("weeklytime <", value, "weeklytime");
            return (Criteria) this;
        }

        public Criteria andWeeklytimeLessThanOrEqualTo(String value) {
            addCriterion("weeklytime <=", value, "weeklytime");
            return (Criteria) this;
        }

        public Criteria andWeeklytimeLike(String value) {
            addCriterion("weeklytime like", value, "weeklytime");
            return (Criteria) this;
        }

        public Criteria andWeeklytimeNotLike(String value) {
            addCriterion("weeklytime not like", value, "weeklytime");
            return (Criteria) this;
        }

        public Criteria andWeeklytimeIn(List<String> values) {
            addCriterion("weeklytime in", values, "weeklytime");
            return (Criteria) this;
        }

        public Criteria andWeeklytimeNotIn(List<String> values) {
            addCriterion("weeklytime not in", values, "weeklytime");
            return (Criteria) this;
        }

        public Criteria andWeeklytimeBetween(String value1, String value2) {
            addCriterion("weeklytime between", value1, value2, "weeklytime");
            return (Criteria) this;
        }

        public Criteria andWeeklytimeNotBetween(String value1, String value2) {
            addCriterion("weeklytime not between", value1, value2, "weeklytime");
            return (Criteria) this;
        }

        public Criteria andSiteidIsNull() {
            addCriterion("siteid is null");
            return (Criteria) this;
        }

        public Criteria andSiteidIsNotNull() {
            addCriterion("siteid is not null");
            return (Criteria) this;
        }

        public Criteria andSiteidEqualTo(Long value) {
            addCriterion("siteid =", value, "siteid");
            return (Criteria) this;
        }

        public Criteria andSiteidNotEqualTo(Long value) {
            addCriterion("siteid <>", value, "siteid");
            return (Criteria) this;
        }

        public Criteria andSiteidGreaterThan(Long value) {
            addCriterion("siteid >", value, "siteid");
            return (Criteria) this;
        }

        public Criteria andSiteidGreaterThanOrEqualTo(Long value) {
            addCriterion("siteid >=", value, "siteid");
            return (Criteria) this;
        }

        public Criteria andSiteidLessThan(Long value) {
            addCriterion("siteid <", value, "siteid");
            return (Criteria) this;
        }

        public Criteria andSiteidLessThanOrEqualTo(Long value) {
            addCriterion("siteid <=", value, "siteid");
            return (Criteria) this;
        }

        public Criteria andSiteidIn(List<Long> values) {
            addCriterion("siteid in", values, "siteid");
            return (Criteria) this;
        }

        public Criteria andSiteidNotIn(List<Long> values) {
            addCriterion("siteid not in", values, "siteid");
            return (Criteria) this;
        }

        public Criteria andSiteidBetween(Long value1, Long value2) {
            addCriterion("siteid between", value1, value2, "siteid");
            return (Criteria) this;
        }

        public Criteria andSiteidNotBetween(Long value1, Long value2) {
            addCriterion("siteid not between", value1, value2, "siteid");
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