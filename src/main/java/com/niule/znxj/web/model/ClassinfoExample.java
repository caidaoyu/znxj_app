package com.niule.znxj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClassinfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ClassinfoExample() {
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

        public Criteria andClassdescIsNull() {
            addCriterion("classdesc is null");
            return (Criteria) this;
        }

        public Criteria andClassdescIsNotNull() {
            addCriterion("classdesc is not null");
            return (Criteria) this;
        }

        public Criteria andClassdescEqualTo(String value) {
            addCriterion("classdesc =", value, "classdesc");
            return (Criteria) this;
        }

        public Criteria andClassdescNotEqualTo(String value) {
            addCriterion("classdesc <>", value, "classdesc");
            return (Criteria) this;
        }

        public Criteria andClassdescGreaterThan(String value) {
            addCriterion("classdesc >", value, "classdesc");
            return (Criteria) this;
        }

        public Criteria andClassdescGreaterThanOrEqualTo(String value) {
            addCriterion("classdesc >=", value, "classdesc");
            return (Criteria) this;
        }

        public Criteria andClassdescLessThan(String value) {
            addCriterion("classdesc <", value, "classdesc");
            return (Criteria) this;
        }

        public Criteria andClassdescLessThanOrEqualTo(String value) {
            addCriterion("classdesc <=", value, "classdesc");
            return (Criteria) this;
        }

        public Criteria andClassdescLike(String value) {
            addCriterion("classdesc like", value, "classdesc");
            return (Criteria) this;
        }

        public Criteria andClassdescNotLike(String value) {
            addCriterion("classdesc not like", value, "classdesc");
            return (Criteria) this;
        }

        public Criteria andClassdescIn(List<String> values) {
            addCriterion("classdesc in", values, "classdesc");
            return (Criteria) this;
        }

        public Criteria andClassdescNotIn(List<String> values) {
            addCriterion("classdesc not in", values, "classdesc");
            return (Criteria) this;
        }

        public Criteria andClassdescBetween(String value1, String value2) {
            addCriterion("classdesc between", value1, value2, "classdesc");
            return (Criteria) this;
        }

        public Criteria andClassdescNotBetween(String value1, String value2) {
            addCriterion("classdesc not between", value1, value2, "classdesc");
            return (Criteria) this;
        }

        public Criteria andWorkstarttimeIsNull() {
            addCriterion("workstarttime is null");
            return (Criteria) this;
        }

        public Criteria andWorkstarttimeIsNotNull() {
            addCriterion("workstarttime is not null");
            return (Criteria) this;
        }

        public Criteria andWorkstarttimeEqualTo(Date value) {
            addCriterion("workstarttime =", value, "workstarttime");
            return (Criteria) this;
        }

        public Criteria andWorkstarttimeNotEqualTo(Date value) {
            addCriterion("workstarttime <>", value, "workstarttime");
            return (Criteria) this;
        }

        public Criteria andWorkstarttimeGreaterThan(Date value) {
            addCriterion("workstarttime >", value, "workstarttime");
            return (Criteria) this;
        }

        public Criteria andWorkstarttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("workstarttime >=", value, "workstarttime");
            return (Criteria) this;
        }

        public Criteria andWorkstarttimeLessThan(Date value) {
            addCriterion("workstarttime <", value, "workstarttime");
            return (Criteria) this;
        }

        public Criteria andWorkstarttimeLessThanOrEqualTo(Date value) {
            addCriterion("workstarttime <=", value, "workstarttime");
            return (Criteria) this;
        }

        public Criteria andWorkstarttimeIn(List<Date> values) {
            addCriterion("workstarttime in", values, "workstarttime");
            return (Criteria) this;
        }

        public Criteria andWorkstarttimeNotIn(List<Date> values) {
            addCriterion("workstarttime not in", values, "workstarttime");
            return (Criteria) this;
        }

        public Criteria andWorkstarttimeBetween(Date value1, Date value2) {
            addCriterion("workstarttime between", value1, value2, "workstarttime");
            return (Criteria) this;
        }

        public Criteria andWorkstarttimeNotBetween(Date value1, Date value2) {
            addCriterion("workstarttime not between", value1, value2, "workstarttime");
            return (Criteria) this;
        }

        public Criteria andWorkendtimeIsNull() {
            addCriterion("workendtime is null");
            return (Criteria) this;
        }

        public Criteria andWorkendtimeIsNotNull() {
            addCriterion("workendtime is not null");
            return (Criteria) this;
        }

        public Criteria andWorkendtimeEqualTo(Date value) {
            addCriterion("workendtime =", value, "workendtime");
            return (Criteria) this;
        }

        public Criteria andWorkendtimeNotEqualTo(Date value) {
            addCriterion("workendtime <>", value, "workendtime");
            return (Criteria) this;
        }

        public Criteria andWorkendtimeGreaterThan(Date value) {
            addCriterion("workendtime >", value, "workendtime");
            return (Criteria) this;
        }

        public Criteria andWorkendtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("workendtime >=", value, "workendtime");
            return (Criteria) this;
        }

        public Criteria andWorkendtimeLessThan(Date value) {
            addCriterion("workendtime <", value, "workendtime");
            return (Criteria) this;
        }

        public Criteria andWorkendtimeLessThanOrEqualTo(Date value) {
            addCriterion("workendtime <=", value, "workendtime");
            return (Criteria) this;
        }

        public Criteria andWorkendtimeIn(List<Date> values) {
            addCriterion("workendtime in", values, "workendtime");
            return (Criteria) this;
        }

        public Criteria andWorkendtimeNotIn(List<Date> values) {
            addCriterion("workendtime not in", values, "workendtime");
            return (Criteria) this;
        }

        public Criteria andWorkendtimeBetween(Date value1, Date value2) {
            addCriterion("workendtime between", value1, value2, "workendtime");
            return (Criteria) this;
        }

        public Criteria andWorkendtimeNotBetween(Date value1, Date value2) {
            addCriterion("workendtime not between", value1, value2, "workendtime");
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

        public Criteria andSiteareaidIsNull() {
            addCriterion("siteareaid is null");
            return (Criteria) this;
        }

        public Criteria andSiteareaidIsNotNull() {
            addCriterion("siteareaid is not null");
            return (Criteria) this;
        }

        public Criteria andSiteareaidEqualTo(Long value) {
            addCriterion("siteareaid =", value, "siteareaid");
            return (Criteria) this;
        }

        public Criteria andSiteareaidNotEqualTo(Long value) {
            addCriterion("siteareaid <>", value, "siteareaid");
            return (Criteria) this;
        }

        public Criteria andSiteareaidGreaterThan(Long value) {
            addCriterion("siteareaid >", value, "siteareaid");
            return (Criteria) this;
        }

        public Criteria andSiteareaidGreaterThanOrEqualTo(Long value) {
            addCriterion("siteareaid >=", value, "siteareaid");
            return (Criteria) this;
        }

        public Criteria andSiteareaidLessThan(Long value) {
            addCriterion("siteareaid <", value, "siteareaid");
            return (Criteria) this;
        }

        public Criteria andSiteareaidLessThanOrEqualTo(Long value) {
            addCriterion("siteareaid <=", value, "siteareaid");
            return (Criteria) this;
        }

        public Criteria andSiteareaidIn(List<Long> values) {
            addCriterion("siteareaid in", values, "siteareaid");
            return (Criteria) this;
        }

        public Criteria andSiteareaidNotIn(List<Long> values) {
            addCriterion("siteareaid not in", values, "siteareaid");
            return (Criteria) this;
        }

        public Criteria andSiteareaidBetween(Long value1, Long value2) {
            addCriterion("siteareaid between", value1, value2, "siteareaid");
            return (Criteria) this;
        }

        public Criteria andSiteareaidNotBetween(Long value1, Long value2) {
            addCriterion("siteareaid not between", value1, value2, "siteareaid");
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