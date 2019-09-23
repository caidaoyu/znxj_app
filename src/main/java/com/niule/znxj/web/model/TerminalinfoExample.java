package com.niule.znxj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TerminalinfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TerminalinfoExample() {
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

        public Criteria andHardwaremodelIsNull() {
            addCriterion("hardwaremodel is null");
            return (Criteria) this;
        }

        public Criteria andHardwaremodelIsNotNull() {
            addCriterion("hardwaremodel is not null");
            return (Criteria) this;
        }

        public Criteria andHardwaremodelEqualTo(String value) {
            addCriterion("hardwaremodel =", value, "hardwaremodel");
            return (Criteria) this;
        }

        public Criteria andHardwaremodelNotEqualTo(String value) {
            addCriterion("hardwaremodel <>", value, "hardwaremodel");
            return (Criteria) this;
        }

        public Criteria andHardwaremodelGreaterThan(String value) {
            addCriterion("hardwaremodel >", value, "hardwaremodel");
            return (Criteria) this;
        }

        public Criteria andHardwaremodelGreaterThanOrEqualTo(String value) {
            addCriterion("hardwaremodel >=", value, "hardwaremodel");
            return (Criteria) this;
        }

        public Criteria andHardwaremodelLessThan(String value) {
            addCriterion("hardwaremodel <", value, "hardwaremodel");
            return (Criteria) this;
        }

        public Criteria andHardwaremodelLessThanOrEqualTo(String value) {
            addCriterion("hardwaremodel <=", value, "hardwaremodel");
            return (Criteria) this;
        }

        public Criteria andHardwaremodelLike(String value) {
            addCriterion("hardwaremodel like", value, "hardwaremodel");
            return (Criteria) this;
        }

        public Criteria andHardwaremodelNotLike(String value) {
            addCriterion("hardwaremodel not like", value, "hardwaremodel");
            return (Criteria) this;
        }

        public Criteria andHardwaremodelIn(List<String> values) {
            addCriterion("hardwaremodel in", values, "hardwaremodel");
            return (Criteria) this;
        }

        public Criteria andHardwaremodelNotIn(List<String> values) {
            addCriterion("hardwaremodel not in", values, "hardwaremodel");
            return (Criteria) this;
        }

        public Criteria andHardwaremodelBetween(String value1, String value2) {
            addCriterion("hardwaremodel between", value1, value2, "hardwaremodel");
            return (Criteria) this;
        }

        public Criteria andHardwaremodelNotBetween(String value1, String value2) {
            addCriterion("hardwaremodel not between", value1, value2, "hardwaremodel");
            return (Criteria) this;
        }

        public Criteria andSoftversionIsNull() {
            addCriterion("softversion is null");
            return (Criteria) this;
        }

        public Criteria andSoftversionIsNotNull() {
            addCriterion("softversion is not null");
            return (Criteria) this;
        }

        public Criteria andSoftversionEqualTo(String value) {
            addCriterion("softversion =", value, "softversion");
            return (Criteria) this;
        }

        public Criteria andSoftversionNotEqualTo(String value) {
            addCriterion("softversion <>", value, "softversion");
            return (Criteria) this;
        }

        public Criteria andSoftversionGreaterThan(String value) {
            addCriterion("softversion >", value, "softversion");
            return (Criteria) this;
        }

        public Criteria andSoftversionGreaterThanOrEqualTo(String value) {
            addCriterion("softversion >=", value, "softversion");
            return (Criteria) this;
        }

        public Criteria andSoftversionLessThan(String value) {
            addCriterion("softversion <", value, "softversion");
            return (Criteria) this;
        }

        public Criteria andSoftversionLessThanOrEqualTo(String value) {
            addCriterion("softversion <=", value, "softversion");
            return (Criteria) this;
        }

        public Criteria andSoftversionLike(String value) {
            addCriterion("softversion like", value, "softversion");
            return (Criteria) this;
        }

        public Criteria andSoftversionNotLike(String value) {
            addCriterion("softversion not like", value, "softversion");
            return (Criteria) this;
        }

        public Criteria andSoftversionIn(List<String> values) {
            addCriterion("softversion in", values, "softversion");
            return (Criteria) this;
        }

        public Criteria andSoftversionNotIn(List<String> values) {
            addCriterion("softversion not in", values, "softversion");
            return (Criteria) this;
        }

        public Criteria andSoftversionBetween(String value1, String value2) {
            addCriterion("softversion between", value1, value2, "softversion");
            return (Criteria) this;
        }

        public Criteria andSoftversionNotBetween(String value1, String value2) {
            addCriterion("softversion not between", value1, value2, "softversion");
            return (Criteria) this;
        }

        public Criteria andDesccontentIsNull() {
            addCriterion("desccontent is null");
            return (Criteria) this;
        }

        public Criteria andDesccontentIsNotNull() {
            addCriterion("desccontent is not null");
            return (Criteria) this;
        }

        public Criteria andDesccontentEqualTo(String value) {
            addCriterion("desccontent =", value, "desccontent");
            return (Criteria) this;
        }

        public Criteria andDesccontentNotEqualTo(String value) {
            addCriterion("desccontent <>", value, "desccontent");
            return (Criteria) this;
        }

        public Criteria andDesccontentGreaterThan(String value) {
            addCriterion("desccontent >", value, "desccontent");
            return (Criteria) this;
        }

        public Criteria andDesccontentGreaterThanOrEqualTo(String value) {
            addCriterion("desccontent >=", value, "desccontent");
            return (Criteria) this;
        }

        public Criteria andDesccontentLessThan(String value) {
            addCriterion("desccontent <", value, "desccontent");
            return (Criteria) this;
        }

        public Criteria andDesccontentLessThanOrEqualTo(String value) {
            addCriterion("desccontent <=", value, "desccontent");
            return (Criteria) this;
        }

        public Criteria andDesccontentLike(String value) {
            addCriterion("desccontent like", value, "desccontent");
            return (Criteria) this;
        }

        public Criteria andDesccontentNotLike(String value) {
            addCriterion("desccontent not like", value, "desccontent");
            return (Criteria) this;
        }

        public Criteria andDesccontentIn(List<String> values) {
            addCriterion("desccontent in", values, "desccontent");
            return (Criteria) this;
        }

        public Criteria andDesccontentNotIn(List<String> values) {
            addCriterion("desccontent not in", values, "desccontent");
            return (Criteria) this;
        }

        public Criteria andDesccontentBetween(String value1, String value2) {
            addCriterion("desccontent between", value1, value2, "desccontent");
            return (Criteria) this;
        }

        public Criteria andDesccontentNotBetween(String value1, String value2) {
            addCriterion("desccontent not between", value1, value2, "desccontent");
            return (Criteria) this;
        }

        public Criteria andDepartmentIsNull() {
            addCriterion("department is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIsNotNull() {
            addCriterion("department is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentEqualTo(String value) {
            addCriterion("department =", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotEqualTo(String value) {
            addCriterion("department <>", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentGreaterThan(String value) {
            addCriterion("department >", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentGreaterThanOrEqualTo(String value) {
            addCriterion("department >=", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentLessThan(String value) {
            addCriterion("department <", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentLessThanOrEqualTo(String value) {
            addCriterion("department <=", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentLike(String value) {
            addCriterion("department like", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotLike(String value) {
            addCriterion("department not like", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentIn(List<String> values) {
            addCriterion("department in", values, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotIn(List<String> values) {
            addCriterion("department not in", values, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentBetween(String value1, String value2) {
            addCriterion("department between", value1, value2, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotBetween(String value1, String value2) {
            addCriterion("department not between", value1, value2, "department");
            return (Criteria) this;
        }

        public Criteria andAuthcodeIsNull() {
            addCriterion("authcode is null");
            return (Criteria) this;
        }

        public Criteria andAuthcodeIsNotNull() {
            addCriterion("authcode is not null");
            return (Criteria) this;
        }

        public Criteria andAuthcodeEqualTo(String value) {
            addCriterion("authcode =", value, "authcode");
            return (Criteria) this;
        }

        public Criteria andAuthcodeNotEqualTo(String value) {
            addCriterion("authcode <>", value, "authcode");
            return (Criteria) this;
        }

        public Criteria andAuthcodeGreaterThan(String value) {
            addCriterion("authcode >", value, "authcode");
            return (Criteria) this;
        }

        public Criteria andAuthcodeGreaterThanOrEqualTo(String value) {
            addCriterion("authcode >=", value, "authcode");
            return (Criteria) this;
        }

        public Criteria andAuthcodeLessThan(String value) {
            addCriterion("authcode <", value, "authcode");
            return (Criteria) this;
        }

        public Criteria andAuthcodeLessThanOrEqualTo(String value) {
            addCriterion("authcode <=", value, "authcode");
            return (Criteria) this;
        }

        public Criteria andAuthcodeLike(String value) {
            addCriterion("authcode like", value, "authcode");
            return (Criteria) this;
        }

        public Criteria andAuthcodeNotLike(String value) {
            addCriterion("authcode not like", value, "authcode");
            return (Criteria) this;
        }

        public Criteria andAuthcodeIn(List<String> values) {
            addCriterion("authcode in", values, "authcode");
            return (Criteria) this;
        }

        public Criteria andAuthcodeNotIn(List<String> values) {
            addCriterion("authcode not in", values, "authcode");
            return (Criteria) this;
        }

        public Criteria andAuthcodeBetween(String value1, String value2) {
            addCriterion("authcode between", value1, value2, "authcode");
            return (Criteria) this;
        }

        public Criteria andAuthcodeNotBetween(String value1, String value2) {
            addCriterion("authcode not between", value1, value2, "authcode");
            return (Criteria) this;
        }

        public Criteria andEnabletimeIsNull() {
            addCriterion("enabletime is null");
            return (Criteria) this;
        }

        public Criteria andEnabletimeIsNotNull() {
            addCriterion("enabletime is not null");
            return (Criteria) this;
        }

        public Criteria andEnabletimeEqualTo(Date value) {
            addCriterion("enabletime =", value, "enabletime");
            return (Criteria) this;
        }

        public Criteria andEnabletimeNotEqualTo(Date value) {
            addCriterion("enabletime <>", value, "enabletime");
            return (Criteria) this;
        }

        public Criteria andEnabletimeGreaterThan(Date value) {
            addCriterion("enabletime >", value, "enabletime");
            return (Criteria) this;
        }

        public Criteria andEnabletimeGreaterThanOrEqualTo(Date value) {
            addCriterion("enabletime >=", value, "enabletime");
            return (Criteria) this;
        }

        public Criteria andEnabletimeLessThan(Date value) {
            addCriterion("enabletime <", value, "enabletime");
            return (Criteria) this;
        }

        public Criteria andEnabletimeLessThanOrEqualTo(Date value) {
            addCriterion("enabletime <=", value, "enabletime");
            return (Criteria) this;
        }

        public Criteria andEnabletimeIn(List<Date> values) {
            addCriterion("enabletime in", values, "enabletime");
            return (Criteria) this;
        }

        public Criteria andEnabletimeNotIn(List<Date> values) {
            addCriterion("enabletime not in", values, "enabletime");
            return (Criteria) this;
        }

        public Criteria andEnabletimeBetween(Date value1, Date value2) {
            addCriterion("enabletime between", value1, value2, "enabletime");
            return (Criteria) this;
        }

        public Criteria andEnabletimeNotBetween(Date value1, Date value2) {
            addCriterion("enabletime not between", value1, value2, "enabletime");
            return (Criteria) this;
        }

        public Criteria andUnenabletimeIsNull() {
            addCriterion("unenabletime is null");
            return (Criteria) this;
        }

        public Criteria andUnenabletimeIsNotNull() {
            addCriterion("unenabletime is not null");
            return (Criteria) this;
        }

        public Criteria andUnenabletimeEqualTo(Date value) {
            addCriterion("unenabletime =", value, "unenabletime");
            return (Criteria) this;
        }

        public Criteria andUnenabletimeNotEqualTo(Date value) {
            addCriterion("unenabletime <>", value, "unenabletime");
            return (Criteria) this;
        }

        public Criteria andUnenabletimeGreaterThan(Date value) {
            addCriterion("unenabletime >", value, "unenabletime");
            return (Criteria) this;
        }

        public Criteria andUnenabletimeGreaterThanOrEqualTo(Date value) {
            addCriterion("unenabletime >=", value, "unenabletime");
            return (Criteria) this;
        }

        public Criteria andUnenabletimeLessThan(Date value) {
            addCriterion("unenabletime <", value, "unenabletime");
            return (Criteria) this;
        }

        public Criteria andUnenabletimeLessThanOrEqualTo(Date value) {
            addCriterion("unenabletime <=", value, "unenabletime");
            return (Criteria) this;
        }

        public Criteria andUnenabletimeIn(List<Date> values) {
            addCriterion("unenabletime in", values, "unenabletime");
            return (Criteria) this;
        }

        public Criteria andUnenabletimeNotIn(List<Date> values) {
            addCriterion("unenabletime not in", values, "unenabletime");
            return (Criteria) this;
        }

        public Criteria andUnenabletimeBetween(Date value1, Date value2) {
            addCriterion("unenabletime between", value1, value2, "unenabletime");
            return (Criteria) this;
        }

        public Criteria andUnenabletimeNotBetween(Date value1, Date value2) {
            addCriterion("unenabletime not between", value1, value2, "unenabletime");
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