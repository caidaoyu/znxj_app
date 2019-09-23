package com.niule.znxj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskreportinfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TaskreportinfoExample() {
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
            addCriterion("ter.id =", value, "id");
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

        public Criteria andTaskidIsNull() {
            addCriterion("taskid is null");
            return (Criteria) this;
        }

        public Criteria andTaskidIsNotNull() {
            addCriterion("taskid is not null");
            return (Criteria) this;
        }

        public Criteria andTaskidEqualTo(Long value) {
            addCriterion("ter.taskid =", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidNotEqualTo(Long value) {
            addCriterion("taskid <>", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidGreaterThan(Long value) {
            addCriterion("taskid >", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidGreaterThanOrEqualTo(Long value) {
            addCriterion("taskid >=", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidLessThan(Long value) {
            addCriterion("taskid <", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidLessThanOrEqualTo(Long value) {
            addCriterion("taskid <=", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidIn(List<Long> values) {
            addCriterion("taskid in", values, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidNotIn(List<Long> values) {
            addCriterion("taskid not in", values, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidBetween(Long value1, Long value2) {
            addCriterion("taskid between", value1, value2, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidNotBetween(Long value1, Long value2) {
            addCriterion("taskid not between", value1, value2, "taskid");
            return (Criteria) this;
        }

        public Criteria andDonetimeIsNull() {
            addCriterion("donetime is null");
            return (Criteria) this;
        }

        public Criteria andDonetimeIsNotNull() {
            addCriterion("donetime is not null");
            return (Criteria) this;
        }

        public Criteria andDonetimeEqualTo(String value) {
            addCriterion("date_format(ter.donetime, '%Y-%m-%d' ) =", value, "donetime");
            return (Criteria) this;
        }

        public Criteria andDonetimeNotEqualTo(Date value) {
            addCriterion("donetime <>", value, "donetime");
            return (Criteria) this;
        }

        public Criteria andDonetimeGreaterThan(Date value) {
            addCriterion("donetime >", value, "donetime");
            return (Criteria) this;
        }

        public Criteria andDonetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("donetime >=", value, "donetime");
            return (Criteria) this;
        }

        public Criteria andDonetimeLessThan(Date value) {
            addCriterion("donetime <", value, "donetime");
            return (Criteria) this;
        }

        public Criteria andDonetimeLessThanOrEqualTo(Date value) {
            addCriterion("donetime <=", value, "donetime");
            return (Criteria) this;
        }

        public Criteria andDonetimeIn(List<Date> values) {
            addCriterion("donetime in", values, "donetime");
            return (Criteria) this;
        }

        public Criteria andDonetimeNotIn(List<Date> values) {
            addCriterion("donetime not in", values, "donetime");
            return (Criteria) this;
        }

        public Criteria andDonetimeBetween(Date value1, Date value2) {
            addCriterion("donetime between", value1, value2, "donetime");
            return (Criteria) this;
        }
        public Criteria andDonetimeBetween2(String value1, String value2) {
            addCriterion("date_format(ter.donetime, '%Y-%m-%d' ) between", value1, value2, "donetime");
            return (Criteria) this;
        }
        public Criteria andDonetimeBetween3(String value1, String value2) {
            addCriterion("date_format(donetime, '%Y-%m-%d' ) between", value1, value2, "donetime");
            return (Criteria) this;
        }
        public Criteria andexecutetimeBetween(Date value1, Date value2) {
            addCriterion("temp.executetime between", value1, value2, "executetime");
            return (Criteria) this;
        }

        public Criteria andExecutetimeEqualTo(String value) {
            addCriterion("date_format(executetime, '%Y-%m-%d' )  ", value, "executetime");
            return (Criteria) this;
        }
        public Criteria andTempTaskidEqualTo(Long value) {
            addCriterion("temp.taskid =", value, "taskid");
            return (Criteria) this;
        }
        public Criteria andDonetimeNotBetween(Date value1, Date value2) {
            addCriterion("donetime not between", value1, value2, "donetime");
            return (Criteria) this;
        }

        public Criteria andUploadedtimeIsNull() {
            addCriterion("uploadedtime is null");
            return (Criteria) this;
        }

        public Criteria andUploadedtimeIsNotNull() {
            addCriterion("uploadedtime is not null");
            return (Criteria) this;
        }

        public Criteria andUploadedtimeEqualTo(Date value) {
            addCriterion("uploadedtime =", value, "uploadedtime");
            return (Criteria) this;
        }

        public Criteria andUploadedtimeNotEqualTo(Date value) {
            addCriterion("uploadedtime <>", value, "uploadedtime");
            return (Criteria) this;
        }

        public Criteria andUploadedtimeGreaterThan(Date value) {
            addCriterion("uploadedtime >", value, "uploadedtime");
            return (Criteria) this;
        }

        public Criteria andUploadedtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("uploadedtime >=", value, "uploadedtime");
            return (Criteria) this;
        }

        public Criteria andUploadedtimeLessThan(Date value) {
            addCriterion("uploadedtime <", value, "uploadedtime");
            return (Criteria) this;
        }

        public Criteria andUploadedtimeLessThanOrEqualTo(Date value) {
            addCriterion("uploadedtime <=", value, "uploadedtime");
            return (Criteria) this;
        }

        public Criteria andUploadedtimeIn(List<Date> values) {
            addCriterion("uploadedtime in", values, "uploadedtime");
            return (Criteria) this;
        }

        public Criteria andUploadedtimeNotIn(List<Date> values) {
            addCriterion("uploadedtime not in", values, "uploadedtime");
            return (Criteria) this;
        }

        public Criteria andUploadedtimeBetween(Date value1, Date value2) {
            addCriterion("uploadedtime between", value1, value2, "uploadedtime");
            return (Criteria) this;
        }

        public Criteria andUploadedtimeNotBetween(Date value1, Date value2) {
            addCriterion("uploadedtime not between", value1, value2, "uploadedtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeIsNull() {
            addCriterion("uploadtime is null");
            return (Criteria) this;
        }

        public Criteria andUploadtimeIsNotNull() {
            addCriterion("uploadtime is not null");
            return (Criteria) this;
        }

        public Criteria andUploadtimeEqualTo(Date value) {
            addCriterion("uploadtime =", value, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeNotEqualTo(Date value) {
            addCriterion("uploadtime <>", value, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeGreaterThan(Date value) {
            addCriterion("uploadtime >", value, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("uploadtime >=", value, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeLessThan(Date value) {
            addCriterion("uploadtime <", value, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeLessThanOrEqualTo(Date value) {
            addCriterion("uploadtime <=", value, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeIn(List<Date> values) {
            addCriterion("uploadtime in", values, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeNotIn(List<Date> values) {
            addCriterion("uploadtime not in", values, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeBetween(Date value1, Date value2) {
            addCriterion("uploadtime between", value1, value2, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeNotBetween(Date value1, Date value2) {
            addCriterion("uploadtime not between", value1, value2, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("userid is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userid is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(Long value) {
            addCriterion("userid =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(Long value) {
            addCriterion("userid <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(Long value) {
            addCriterion("userid >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(Long value) {
            addCriterion("userid >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(Long value) {
            addCriterion("userid <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(Long value) {
            addCriterion("userid <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<Long> values) {
            addCriterion("userid in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<Long> values) {
            addCriterion("userid not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(Long value1, Long value2) {
            addCriterion("userid between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(Long value1, Long value2) {
            addCriterion("userid not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andTermimalidIsNull() {
            addCriterion("termimalid is null");
            return (Criteria) this;
        }

        public Criteria andTermimalidIsNotNull() {
            addCriterion("termimalid is not null");
            return (Criteria) this;
        }

        public Criteria andTermimalidEqualTo(Long value) {
            addCriterion("termimalid =", value, "termimalid");
            return (Criteria) this;
        }

        public Criteria andTermimalidNotEqualTo(Long value) {
            addCriterion("termimalid <>", value, "termimalid");
            return (Criteria) this;
        }

        public Criteria andTermimalidGreaterThan(Long value) {
            addCriterion("termimalid >", value, "termimalid");
            return (Criteria) this;
        }

        public Criteria andTermimalidGreaterThanOrEqualTo(Long value) {
            addCriterion("termimalid >=", value, "termimalid");
            return (Criteria) this;
        }

        public Criteria andTermimalidLessThan(Long value) {
            addCriterion("termimalid <", value, "termimalid");
            return (Criteria) this;
        }

        public Criteria andTermimalidLessThanOrEqualTo(Long value) {
            addCriterion("termimalid <=", value, "termimalid");
            return (Criteria) this;
        }

        public Criteria andTermimalidIn(List<Long> values) {
            addCriterion("termimalid in", values, "termimalid");
            return (Criteria) this;
        }

        public Criteria andTermimalidNotIn(List<Long> values) {
            addCriterion("termimalid not in", values, "termimalid");
            return (Criteria) this;
        }

        public Criteria andTermimalidBetween(Long value1, Long value2) {
            addCriterion("termimalid between", value1, value2, "termimalid");
            return (Criteria) this;
        }

        public Criteria andTermimalidNotBetween(Long value1, Long value2) {
            addCriterion("termimalid not between", value1, value2, "termimalid");
            return (Criteria) this;
        }

        public Criteria andTasktypeIsNull() {
            addCriterion("tasktype is null");
            return (Criteria) this;
        }

        public Criteria andTasktypeIsNotNull() {
            addCriterion("tasktype is not null");
            return (Criteria) this;
        }

        public Criteria andTasktypeEqualTo(Integer value) {
            addCriterion("tasktype =", value, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeNotEqualTo(Integer value) {
            addCriterion("tasktype <>", value, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeGreaterThan(Integer value) {
            addCriterion("tasktype >", value, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("tasktype >=", value, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeLessThan(Integer value) {
            addCriterion("tasktype <", value, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeLessThanOrEqualTo(Integer value) {
            addCriterion("tasktype <=", value, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeIn(List<Integer> values) {
            addCriterion("tasktype in", values, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeNotIn(List<Integer> values) {
            addCriterion("tasktype not in", values, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeBetween(Integer value1, Integer value2) {
            addCriterion("tasktype between", value1, value2, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeNotBetween(Integer value1, Integer value2) {
            addCriterion("tasktype not between", value1, value2, "tasktype");
            return (Criteria) this;
        }

        public Criteria andReportstateIsNull() {
            addCriterion("reportstate is null");
            return (Criteria) this;
        }

        public Criteria andReportstateIsNotNull() {
            addCriterion("reportstate is not null");
            return (Criteria) this;
        }

        public Criteria andReportstateEqualTo(Integer value) {
            addCriterion("reportstate =", value, "reportstate");
            return (Criteria) this;
        }

        public Criteria andReportstateNotEqualTo(Integer value) {
            addCriterion("reportstate <>", value, "reportstate");
            return (Criteria) this;
        }

        public Criteria andReportstateGreaterThan(Integer value) {
            addCriterion("reportstate >", value, "reportstate");
            return (Criteria) this;
        }

        public Criteria andReportstateGreaterThanOrEqualTo(Integer value) {
            addCriterion("reportstate >=", value, "reportstate");
            return (Criteria) this;
        }

        public Criteria andReportstateLessThan(Integer value) {
            addCriterion("reportstate <", value, "reportstate");
            return (Criteria) this;
        }

        public Criteria andReportstateLessThanOrEqualTo(Integer value) {
            addCriterion("reportstate <=", value, "reportstate");
            return (Criteria) this;
        }

        public Criteria andReportstateIn(List<Integer> values) {
            addCriterion("reportstate in", values, "reportstate");
            return (Criteria) this;
        }

        public Criteria andReportstateNotIn(List<Integer> values) {
            addCriterion("reportstate not in", values, "reportstate");
            return (Criteria) this;
        }

        public Criteria andReportstateBetween(Integer value1, Integer value2) {
            addCriterion("reportstate between", value1, value2, "reportstate");
            return (Criteria) this;
        }

        public Criteria andReportstateNotBetween(Integer value1, Integer value2) {
            addCriterion("reportstate not between", value1, value2, "reportstate");
            return (Criteria) this;
        }

        public Criteria andWorkerIsNull() {
            addCriterion("worker is null");
            return (Criteria) this;
        }

        public Criteria andWorkerIsNotNull() {
            addCriterion("worker is not null");
            return (Criteria) this;
        }

        public Criteria andWorkerEqualTo(String value) {
            addCriterion("worker =", value, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkerNotEqualTo(String value) {
            addCriterion("worker <>", value, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkerGreaterThan(String value) {
            addCriterion("worker >", value, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkerGreaterThanOrEqualTo(String value) {
            addCriterion("worker >=", value, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkerLessThan(String value) {
            addCriterion("worker <", value, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkerLessThanOrEqualTo(String value) {
            addCriterion("worker <=", value, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkerLike(String value) {
            addCriterion("worker like", value, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkerNotLike(String value) {
            addCriterion("worker not like", value, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkerIn(List<String> values) {
            addCriterion("worker in", values, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkerNotIn(List<String> values) {
            addCriterion("worker not in", values, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkerBetween(String value1, String value2) {
            addCriterion("worker between", value1, value2, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkerNotBetween(String value1, String value2) {
            addCriterion("worker not between", value1, value2, "worker");
            return (Criteria) this;
        }

        public Criteria andOperationstateIsNull() {
            addCriterion("operationstate is null");
            return (Criteria) this;
        }

        public Criteria andOperationstateIsNotNull() {
            addCriterion("operationstate is not null");
            return (Criteria) this;
        }

        public Criteria andOperationstateEqualTo(Integer value) {
            addCriterion("operationstate =", value, "operationstate");
            return (Criteria) this;
        }

        public Criteria andOperationstateNotEqualTo(Integer value) {
            addCriterion("operationstate <>", value, "operationstate");
            return (Criteria) this;
        }

        public Criteria andOperationstateGreaterThan(Integer value) {
            addCriterion("operationstate >", value, "operationstate");
            return (Criteria) this;
        }

        public Criteria andOperationstateGreaterThanOrEqualTo(Integer value) {
            addCriterion("operationstate >=", value, "operationstate");
            return (Criteria) this;
        }

        public Criteria andOperationstateLessThan(Integer value) {
            addCriterion("operationstate <", value, "operationstate");
            return (Criteria) this;
        }

        public Criteria andOperationstateLessThanOrEqualTo(Integer value) {
            addCriterion("operationstate <=", value, "operationstate");
            return (Criteria) this;
        }

        public Criteria andOperationstateIn(List<Integer> values) {
            addCriterion("operationstate in", values, "operationstate");
            return (Criteria) this;
        }

        public Criteria andOperationstateNotIn(List<Integer> values) {
            addCriterion("operationstate not in", values, "operationstate");
            return (Criteria) this;
        }

        public Criteria andOperationstateBetween(Integer value1, Integer value2) {
            addCriterion("operationstate between", value1, value2, "operationstate");
            return (Criteria) this;
        }

        public Criteria andOperationstateNotBetween(Integer value1, Integer value2) {
            addCriterion("operationstate not between", value1, value2, "operationstate");
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
        public Criteria andTaskcode2EqualTo(String value) {
            addCriterion("ter.taskcode =", value, "taskcode");
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
        public Criteria andTaskcode2Like(String value) {
            addCriterion("ter.taskcode like", value, "taskcode");
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

        public Criteria andStarttimeIsNull() {
            addCriterion("starttime is null");
            return (Criteria) this;
        }

        public Criteria andStarttimeIsNotNull() {
            addCriterion("starttime is not null");
            return (Criteria) this;
        }

        public Criteria andStarttimeEqualTo(Date value) {
            addCriterion("starttime =", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotEqualTo(Date value) {
            addCriterion("starttime <>", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThan(Date value) {
            addCriterion("starttime >", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("starttime >=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThan(Date value) {
            addCriterion("starttime <", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThanOrEqualTo(Date value) {
            addCriterion("starttime <=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeIn(List<Date> values) {
            addCriterion("starttime in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotIn(List<Date> values) {
            addCriterion("starttime not in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeBetween(Date value1, Date value2) {
            addCriterion("starttime between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotBetween(Date value1, Date value2) {
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

        public Criteria andEndtimeEqualTo(Date value) {
            addCriterion("endtime =", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotEqualTo(Date value) {
            addCriterion("endtime <>", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThan(Date value) {
            addCriterion("endtime >", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("endtime >=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThan(Date value) {
            addCriterion("endtime <", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThanOrEqualTo(Date value) {
            addCriterion("endtime <=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIn(List<Date> values) {
            addCriterion("endtime in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotIn(List<Date> values) {
            addCriterion("endtime not in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeBetween(Date value1, Date value2) {
            addCriterion("endtime between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotBetween(Date value1, Date value2) {
            addCriterion("endtime not between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andExamtimeIsNull() {
            addCriterion("examtime is null");
            return (Criteria) this;
        }

        public Criteria andExamtimeIsNotNull() {
            addCriterion("examtime is not null");
            return (Criteria) this;
        }

        public Criteria andExamtimeEqualTo(Date value) {
            addCriterion("examtime =", value, "examtime");
            return (Criteria) this;
        }
        public Criteria andExamtimeLike(String value) {
            addCriterion("ter.examtime like", value, "examtime");
            return (Criteria) this;
        }
        public Criteria andExamtimeNotEqualTo(Date value) {
            addCriterion("examtime <>", value, "examtime");
            return (Criteria) this;
        }

        public Criteria andExamtimeGreaterThan(Date value) {
            addCriterion("examtime >", value, "examtime");
            return (Criteria) this;
        }

        public Criteria andExamtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("examtime >=", value, "examtime");
            return (Criteria) this;
        }

        public Criteria andExamtimeLessThan(Date value) {
            addCriterion("examtime <", value, "examtime");
            return (Criteria) this;
        }

        public Criteria andExamtimeLessThanOrEqualTo(Date value) {
            addCriterion("examtime <=", value, "examtime");
            return (Criteria) this;
        }

        public Criteria andExamtimeIn(List<Date> values) {
            addCriterion("examtime in", values, "examtime");
            return (Criteria) this;
        }

        public Criteria andExamtimeNotIn(List<Date> values) {
            addCriterion("examtime not in", values, "examtime");
            return (Criteria) this;
        }

        public Criteria andExamtimeBetween(Date value1, Date value2) {
            addCriterion("examtime between", value1, value2, "examtime");
            return (Criteria) this;
        }

        public Criteria andExamtimeNotBetween(Date value1, Date value2) {
            addCriterion("examtime not between", value1, value2, "examtime");
            return (Criteria) this;
        }

        public Criteria andExamstateIsNull() {
            addCriterion("examstate is null");
            return (Criteria) this;
        }

        public Criteria andExamstateIsNotNull() {
            addCriterion("examstate is not null");
            return (Criteria) this;
        }

        public Criteria andExamstateEqualTo(Integer value) {
            addCriterion("ter.examstate =", value, "examstate");
            return (Criteria) this;
        }

        public Criteria andExamstateNotEqualTo(Integer value) {
            addCriterion("examstate <>", value, "examstate");
            return (Criteria) this;
        }

        public Criteria andExamstateGreaterThan(Integer value) {
            addCriterion("examstate >", value, "examstate");
            return (Criteria) this;
        }

        public Criteria andExamstateGreaterThanOrEqualTo(Integer value) {
            addCriterion("examstate >=", value, "examstate");
            return (Criteria) this;
        }

        public Criteria andExamstateLessThan(Integer value) {
            addCriterion("examstate <", value, "examstate");
            return (Criteria) this;
        }

        public Criteria andExamstateLessThanOrEqualTo(Integer value) {
            addCriterion("examstate <=", value, "examstate");
            return (Criteria) this;
        }

        public Criteria andExamstateIn(List<Integer> values) {
            addCriterion("examstate in", values, "examstate");
            return (Criteria) this;
        }

        public Criteria andExamstateNotIn(List<Integer> values) {
            addCriterion("examstate not in", values, "examstate");
            return (Criteria) this;
        }

        public Criteria andExamstateBetween(Integer value1, Integer value2) {
            addCriterion("examstate between", value1, value2, "examstate");
            return (Criteria) this;
        }

        public Criteria andExamstateNotBetween(Integer value1, Integer value2) {
            addCriterion("examstate not between", value1, value2, "examstate");
            return (Criteria) this;
        }

        public Criteria andExamuserIsNull() {
            addCriterion("examuser is null");
            return (Criteria) this;
        }

        public Criteria andExamuserIsNotNull() {
            addCriterion("examuser is not null");
            return (Criteria) this;
        }

        public Criteria               andExamuserEqualTo(String value) {
            addCriterion("examuser =", value, "examuser");
            return (Criteria) this;
        }

        public Criteria andExamuserNotEqualTo(String value) {
            addCriterion("examuser <>", value, "examuser");
            return (Criteria) this;
        }

        public Criteria andExamuserGreaterThan(String value) {
            addCriterion("examuser >", value, "examuser");
            return (Criteria) this;
        }

        public Criteria andExamuserGreaterThanOrEqualTo(String value) {
            addCriterion("examuser >=", value, "examuser");
            return (Criteria) this;
        }

        public Criteria andExamuserLessThan(String value) {
            addCriterion("examuser <", value, "examuser");
            return (Criteria) this;
        }

        public Criteria andExamuserLessThanOrEqualTo(String value) {
            addCriterion("examuser <=", value, "examuser");
            return (Criteria) this;
        }

        public Criteria andExamuserLike(String value) {
            addCriterion("examuser like", value, "examuser");
            return (Criteria) this;
        }

        public Criteria andExamuserNotLike(String value) {
            addCriterion("examuser not like", value, "examuser");
            return (Criteria) this;
        }

        public Criteria andExamuserIn(List<String> values) {
            addCriterion("examuser in", values, "examuser");
            return (Criteria) this;
        }

        public Criteria andExamuserNotIn(List<String> values) {
            addCriterion("examuser not in", values, "examuser");
            return (Criteria) this;
        }

        public Criteria andExamuserBetween(String value1, String value2) {
            addCriterion("examuser between", value1, value2, "examuser");
            return (Criteria) this;
        }

        public Criteria andExamuserNotBetween(String value1, String value2) {
            addCriterion("examuser not between", value1, value2, "examuser");
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