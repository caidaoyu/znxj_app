package com.niule.znxj.web.model;

import java.util.ArrayList;
import java.util.List;

public class PermissionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PermissionExample() {
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

        public Criteria andPermissionidIsNull() {
            addCriterion("permissionid is null");
            return (Criteria) this;
        }

        public Criteria andPermissionidIsNotNull() {
            addCriterion("permissionid is not null");
            return (Criteria) this;
        }

        public Criteria andPermissionidEqualTo(Integer value) {
            addCriterion("permissionid =", value, "permissionid");
            return (Criteria) this;
        }

        public Criteria andPermissionidNotEqualTo(Integer value) {
            addCriterion("permissionid <>", value, "permissionid");
            return (Criteria) this;
        }

        public Criteria andPermissionidGreaterThan(Integer value) {
            addCriterion("permissionid >", value, "permissionid");
            return (Criteria) this;
        }

        public Criteria andPermissionidGreaterThanOrEqualTo(Integer value) {
            addCriterion("permissionid >=", value, "permissionid");
            return (Criteria) this;
        }

        public Criteria andPermissionidLessThan(Integer value) {
            addCriterion("permissionid <", value, "permissionid");
            return (Criteria) this;
        }

        public Criteria andPermissionidLessThanOrEqualTo(Integer value) {
            addCriterion("permissionid <=", value, "permissionid");
            return (Criteria) this;
        }

        public Criteria andPermissionidIn(List<Integer> values) {
            addCriterion("permissionid in", values, "permissionid");
            return (Criteria) this;
        }

        public Criteria andPermissionidNotIn(List<Integer> values) {
            addCriterion("permissionid not in", values, "permissionid");
            return (Criteria) this;
        }

        public Criteria andPermissionidBetween(Integer value1, Integer value2) {
            addCriterion("permissionid between", value1, value2, "permissionid");
            return (Criteria) this;
        }

        public Criteria andPermissionidNotBetween(Integer value1, Integer value2) {
            addCriterion("permissionid not between", value1, value2, "permissionid");
            return (Criteria) this;
        }

        public Criteria andPermissionnameIsNull() {
            addCriterion("permissionname is null");
            return (Criteria) this;
        }

        public Criteria andPermissionnameIsNotNull() {
            addCriterion("permissionname is not null");
            return (Criteria) this;
        }

        public Criteria andPermissionnameEqualTo(String value) {
            addCriterion("permissionname =", value, "permissionname");
            return (Criteria) this;
        }

        public Criteria andPermissionnameNotEqualTo(String value) {
            addCriterion("permissionname <>", value, "permissionname");
            return (Criteria) this;
        }

        public Criteria andPermissionnameGreaterThan(String value) {
            addCriterion("permissionname >", value, "permissionname");
            return (Criteria) this;
        }

        public Criteria andPermissionnameGreaterThanOrEqualTo(String value) {
            addCriterion("permissionname >=", value, "permissionname");
            return (Criteria) this;
        }

        public Criteria andPermissionnameLessThan(String value) {
            addCriterion("permissionname <", value, "permissionname");
            return (Criteria) this;
        }

        public Criteria andPermissionnameLessThanOrEqualTo(String value) {
            addCriterion("permissionname <=", value, "permissionname");
            return (Criteria) this;
        }

        public Criteria andPermissionnameLike(String value) {
            addCriterion("permissionname like", value, "permissionname");
            return (Criteria) this;
        }

        public Criteria andPermissionnameNotLike(String value) {
            addCriterion("permissionname not like", value, "permissionname");
            return (Criteria) this;
        }

        public Criteria andPermissionnameIn(List<String> values) {
            addCriterion("permissionname in", values, "permissionname");
            return (Criteria) this;
        }

        public Criteria andPermissionnameNotIn(List<String> values) {
            addCriterion("permissionname not in", values, "permissionname");
            return (Criteria) this;
        }

        public Criteria andPermissionnameBetween(String value1, String value2) {
            addCriterion("permissionname between", value1, value2, "permissionname");
            return (Criteria) this;
        }

        public Criteria andPermissionnameNotBetween(String value1, String value2) {
            addCriterion("permissionname not between", value1, value2, "permissionname");
            return (Criteria) this;
        }

        public Criteria andPermissionsignIsNull() {
            addCriterion("permissionsign is null");
            return (Criteria) this;
        }

        public Criteria andPermissionsignIsNotNull() {
            addCriterion("permissionsign is not null");
            return (Criteria) this;
        }

        public Criteria andPermissionsignEqualTo(String value) {
            addCriterion("permissionsign =", value, "permissionsign");
            return (Criteria) this;
        }

        public Criteria andPermissionsignNotEqualTo(String value) {
            addCriterion("permissionsign <>", value, "permissionsign");
            return (Criteria) this;
        }

        public Criteria andPermissionsignGreaterThan(String value) {
            addCriterion("permissionsign >", value, "permissionsign");
            return (Criteria) this;
        }

        public Criteria andPermissionsignGreaterThanOrEqualTo(String value) {
            addCriterion("permissionsign >=", value, "permissionsign");
            return (Criteria) this;
        }

        public Criteria andPermissionsignLessThan(String value) {
            addCriterion("permissionsign <", value, "permissionsign");
            return (Criteria) this;
        }

        public Criteria andPermissionsignLessThanOrEqualTo(String value) {
            addCriterion("permissionsign <=", value, "permissionsign");
            return (Criteria) this;
        }

        public Criteria andPermissionsignLike(String value) {
            addCriterion("permissionsign like", value, "permissionsign");
            return (Criteria) this;
        }

        public Criteria andPermissionsignNotLike(String value) {
            addCriterion("permissionsign not like", value, "permissionsign");
            return (Criteria) this;
        }

        public Criteria andPermissionsignIn(List<String> values) {
            addCriterion("permissionsign in", values, "permissionsign");
            return (Criteria) this;
        }

        public Criteria andPermissionsignNotIn(List<String> values) {
            addCriterion("permissionsign not in", values, "permissionsign");
            return (Criteria) this;
        }

        public Criteria andPermissionsignBetween(String value1, String value2) {
            addCriterion("permissionsign between", value1, value2, "permissionsign");
            return (Criteria) this;
        }

        public Criteria andPermissionsignNotBetween(String value1, String value2) {
            addCriterion("permissionsign not between", value1, value2, "permissionsign");
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