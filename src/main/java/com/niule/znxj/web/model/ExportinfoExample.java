package com.niule.znxj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExportinfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExportinfoExample() {
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

        public Criteria andExportnameIsNull() {
            addCriterion("exportName is null");
            return (Criteria) this;
        }

        public Criteria andExportnameIsNotNull() {
            addCriterion("exportName is not null");
            return (Criteria) this;
        }

        public Criteria andExportnameEqualTo(String value) {
            addCriterion("exportName =", value, "exportname");
            return (Criteria) this;
        }

        public Criteria andExportnameNotEqualTo(String value) {
            addCriterion("exportName <>", value, "exportname");
            return (Criteria) this;
        }

        public Criteria andExportnameGreaterThan(String value) {
            addCriterion("exportName >", value, "exportname");
            return (Criteria) this;
        }

        public Criteria andExportnameGreaterThanOrEqualTo(String value) {
            addCriterion("exportName >=", value, "exportname");
            return (Criteria) this;
        }

        public Criteria andExportnameLessThan(String value) {
            addCriterion("exportName <", value, "exportname");
            return (Criteria) this;
        }

        public Criteria andExportnameLessThanOrEqualTo(String value) {
            addCriterion("exportName <=", value, "exportname");
            return (Criteria) this;
        }

        public Criteria andExportnameLike(String value) {
            addCriterion("exportName like", value, "exportname");
            return (Criteria) this;
        }

        public Criteria andExportnameNotLike(String value) {
            addCriterion("exportName not like", value, "exportname");
            return (Criteria) this;
        }

        public Criteria andExportnameIn(List<String> values) {
            addCriterion("exportName in", values, "exportname");
            return (Criteria) this;
        }

        public Criteria andExportnameNotIn(List<String> values) {
            addCriterion("exportName not in", values, "exportname");
            return (Criteria) this;
        }

        public Criteria andExportnameBetween(String value1, String value2) {
            addCriterion("exportName between", value1, value2, "exportname");
            return (Criteria) this;
        }

        public Criteria andExportnameNotBetween(String value1, String value2) {
            addCriterion("exportName not between", value1, value2, "exportname");
            return (Criteria) this;
        }

        public Criteria andTasktypeIsNull() {
            addCriterion("taskType is null");
            return (Criteria) this;
        }

        public Criteria andTasktypeIsNotNull() {
            addCriterion("taskType is not null");
            return (Criteria) this;
        }

        public Criteria andTasktypeEqualTo(String value) {
            addCriterion("taskType =", value, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeNotEqualTo(String value) {
            addCriterion("taskType <>", value, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeGreaterThan(String value) {
            addCriterion("taskType >", value, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeGreaterThanOrEqualTo(String value) {
            addCriterion("taskType >=", value, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeLessThan(String value) {
            addCriterion("taskType <", value, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeLessThanOrEqualTo(String value) {
            addCriterion("taskType <=", value, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeLike(String value) {
            addCriterion("taskType like", value, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeNotLike(String value) {
            addCriterion("taskType not like", value, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeIn(List<String> values) {
            addCriterion("taskType in", values, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeNotIn(List<String> values) {
            addCriterion("taskType not in", values, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeBetween(String value1, String value2) {
            addCriterion("taskType between", value1, value2, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeNotBetween(String value1, String value2) {
            addCriterion("taskType not between", value1, value2, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTaskidIsNull() {
            addCriterion("taskId is null");
            return (Criteria) this;
        }

        public Criteria andTaskidIsNotNull() {
            addCriterion("taskId is not null");
            return (Criteria) this;
        }

        public Criteria andTaskidEqualTo(Integer value) {
            addCriterion("taskId =", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidNotEqualTo(Integer value) {
            addCriterion("taskId <>", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidGreaterThan(Integer value) {
            addCriterion("taskId >", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidGreaterThanOrEqualTo(Integer value) {
            addCriterion("taskId >=", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidLessThan(Integer value) {
            addCriterion("taskId <", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidLessThanOrEqualTo(Integer value) {
            addCriterion("taskId <=", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidIn(List<Integer> values) {
            addCriterion("taskId in", values, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidNotIn(List<Integer> values) {
            addCriterion("taskId not in", values, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidBetween(Integer value1, Integer value2) {
            addCriterion("taskId between", value1, value2, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidNotBetween(Integer value1, Integer value2) {
            addCriterion("taskId not between", value1, value2, "taskid");
            return (Criteria) this;
        }

        public Criteria andComputingtimeIsNull() {
            addCriterion("computingTime is null");
            return (Criteria) this;
        }

        public Criteria andComputingtimeIsNotNull() {
            addCriterion("computingTime is not null");
            return (Criteria) this;
        }

        public Criteria andComputingtimeEqualTo(Integer value) {
            addCriterion("computingTime =", value, "computingtime");
            return (Criteria) this;
        }

        public Criteria andComputingtimeNotEqualTo(Integer value) {
            addCriterion("computingTime <>", value, "computingtime");
            return (Criteria) this;
        }

        public Criteria andComputingtimeGreaterThan(Integer value) {
            addCriterion("computingTime >", value, "computingtime");
            return (Criteria) this;
        }

        public Criteria andComputingtimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("computingTime >=", value, "computingtime");
            return (Criteria) this;
        }

        public Criteria andComputingtimeLessThan(Integer value) {
            addCriterion("computingTime <", value, "computingtime");
            return (Criteria) this;
        }

        public Criteria andComputingtimeLessThanOrEqualTo(Integer value) {
            addCriterion("computingTime <=", value, "computingtime");
            return (Criteria) this;
        }

        public Criteria andComputingtimeIn(List<Integer> values) {
            addCriterion("computingTime in", values, "computingtime");
            return (Criteria) this;
        }

        public Criteria andComputingtimeNotIn(List<Integer> values) {
            addCriterion("computingTime not in", values, "computingtime");
            return (Criteria) this;
        }

        public Criteria andComputingtimeBetween(Integer value1, Integer value2) {
            addCriterion("computingTime between", value1, value2, "computingtime");
            return (Criteria) this;
        }

        public Criteria andComputingtimeNotBetween(Integer value1, Integer value2) {
            addCriterion("computingTime not between", value1, value2, "computingtime");
            return (Criteria) this;
        }

        public Criteria andConsumingIsNull() {
            addCriterion("consuming is null");
            return (Criteria) this;
        }

        public Criteria andConsumingIsNotNull() {
            addCriterion("consuming is not null");
            return (Criteria) this;
        }

        public Criteria andConsumingEqualTo(Integer value) {
            addCriterion("consuming =", value, "consuming");
            return (Criteria) this;
        }

        public Criteria andConsumingNotEqualTo(Integer value) {
            addCriterion("consuming <>", value, "consuming");
            return (Criteria) this;
        }

        public Criteria andConsumingGreaterThan(Integer value) {
            addCriterion("consuming >", value, "consuming");
            return (Criteria) this;
        }

        public Criteria andConsumingGreaterThanOrEqualTo(Integer value) {
            addCriterion("consuming >=", value, "consuming");
            return (Criteria) this;
        }

        public Criteria andConsumingLessThan(Integer value) {
            addCriterion("consuming <", value, "consuming");
            return (Criteria) this;
        }

        public Criteria andConsumingLessThanOrEqualTo(Integer value) {
            addCriterion("consuming <=", value, "consuming");
            return (Criteria) this;
        }

        public Criteria andConsumingIn(List<Integer> values) {
            addCriterion("consuming in", values, "consuming");
            return (Criteria) this;
        }

        public Criteria andConsumingNotIn(List<Integer> values) {
            addCriterion("consuming not in", values, "consuming");
            return (Criteria) this;
        }

        public Criteria andConsumingBetween(Integer value1, Integer value2) {
            addCriterion("consuming between", value1, value2, "consuming");
            return (Criteria) this;
        }

        public Criteria andConsumingNotBetween(Integer value1, Integer value2) {
            addCriterion("consuming not between", value1, value2, "consuming");
            return (Criteria) this;
        }

        public Criteria andUpdatetimestampIsNull() {
            addCriterion("updateTimeStamp is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimestampIsNotNull() {
            addCriterion("updateTimeStamp is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimestampEqualTo(Date value) {
            addCriterion("updateTimeStamp =", value, "updatetimestamp");
            return (Criteria) this;
        }

        public Criteria andUpdatetimestampNotEqualTo(Date value) {
            addCriterion("updateTimeStamp <>", value, "updatetimestamp");
            return (Criteria) this;
        }

        public Criteria andUpdatetimestampGreaterThan(Date value) {
            addCriterion("updateTimeStamp >", value, "updatetimestamp");
            return (Criteria) this;
        }

        public Criteria andUpdatetimestampGreaterThanOrEqualTo(Date value) {
            addCriterion("updateTimeStamp >=", value, "updatetimestamp");
            return (Criteria) this;
        }

        public Criteria andUpdatetimestampLessThan(Date value) {
            addCriterion("updateTimeStamp <", value, "updatetimestamp");
            return (Criteria) this;
        }

        public Criteria andUpdatetimestampLessThanOrEqualTo(Date value) {
            addCriterion("updateTimeStamp <=", value, "updatetimestamp");
            return (Criteria) this;
        }

        public Criteria andUpdatetimestampIn(List<Date> values) {
            addCriterion("updateTimeStamp in", values, "updatetimestamp");
            return (Criteria) this;
        }

        public Criteria andUpdatetimestampNotIn(List<Date> values) {
            addCriterion("updateTimeStamp not in", values, "updatetimestamp");
            return (Criteria) this;
        }

        public Criteria andUpdatetimestampBetween(Date value1, Date value2) {
            addCriterion("updateTimeStamp between", value1, value2, "updatetimestamp");
            return (Criteria) this;
        }

        public Criteria andUpdatetimestampNotBetween(Date value1, Date value2) {
            addCriterion("updateTimeStamp not between", value1, value2, "updatetimestamp");
            return (Criteria) this;
        }

        public Criteria andCreatetimestampIsNull() {
            addCriterion("createTimeStamp is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimestampIsNotNull() {
            addCriterion("createTimeStamp is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimestampEqualTo(Date value) {
            addCriterion("createTimeStamp =", value, "createtimestamp");
            return (Criteria) this;
        }

        public Criteria andCreatetimestampNotEqualTo(Date value) {
            addCriterion("createTimeStamp <>", value, "createtimestamp");
            return (Criteria) this;
        }

        public Criteria andCreatetimestampGreaterThan(Date value) {
            addCriterion("createTimeStamp >", value, "createtimestamp");
            return (Criteria) this;
        }

        public Criteria andCreatetimestampGreaterThanOrEqualTo(Date value) {
            addCriterion("createTimeStamp >=", value, "createtimestamp");
            return (Criteria) this;
        }

        public Criteria andCreatetimestampLessThan(Date value) {
            addCriterion("createTimeStamp <", value, "createtimestamp");
            return (Criteria) this;
        }

        public Criteria andCreatetimestampLessThanOrEqualTo(Date value) {
            addCriterion("createTimeStamp <=", value, "createtimestamp");
            return (Criteria) this;
        }

        public Criteria andCreatetimestampIn(List<Date> values) {
            addCriterion("createTimeStamp in", values, "createtimestamp");
            return (Criteria) this;
        }

        public Criteria andCreatetimestampNotIn(List<Date> values) {
            addCriterion("createTimeStamp not in", values, "createtimestamp");
            return (Criteria) this;
        }

        public Criteria andCreatetimestampBetween(Date value1, Date value2) {
            addCriterion("createTimeStamp between", value1, value2, "createtimestamp");
            return (Criteria) this;
        }

        public Criteria andCreatetimestampNotBetween(Date value1, Date value2) {
            addCriterion("createTimeStamp not between", value1, value2, "createtimestamp");
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