package com.niule.znxj.web.model;

import java.util.ArrayList;
import java.util.List;

public class SendemailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SendemailExample() {
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

        public Criteria andTaskidIsNull() {
            addCriterion("taskid is null");
            return (Criteria) this;
        }

        public Criteria andTaskidIsNotNull() {
            addCriterion("taskid is not null");
            return (Criteria) this;
        }

        public Criteria andTaskidEqualTo(Long value) {
            addCriterion("taskid =", value, "taskid");
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

        public Criteria andContactidIsNull() {
            addCriterion("contactid is null");
            return (Criteria) this;
        }

        public Criteria andContactidIsNotNull() {
            addCriterion("contactid is not null");
            return (Criteria) this;
        }

        public Criteria andContactidEqualTo(String value) {
            addCriterion("contactid =", value, "contactid");
            return (Criteria) this;
        }

        public Criteria andContactidNotEqualTo(String value) {
            addCriterion("contactid <>", value, "contactid");
            return (Criteria) this;
        }

        public Criteria andContactidGreaterThan(String value) {
            addCriterion("contactid >", value, "contactid");
            return (Criteria) this;
        }

        public Criteria andContactidGreaterThanOrEqualTo(String value) {
            addCriterion("contactid >=", value, "contactid");
            return (Criteria) this;
        }

        public Criteria andContactidLessThan(String value) {
            addCriterion("contactid <", value, "contactid");
            return (Criteria) this;
        }

        public Criteria andContactidLessThanOrEqualTo(String value) {
            addCriterion("contactid <=", value, "contactid");
            return (Criteria) this;
        }

        public Criteria andContactidLike(String value) {
            addCriterion("contactid like", value, "contactid");
            return (Criteria) this;
        }

        public Criteria andContactidNotLike(String value) {
            addCriterion("contactid not like", value, "contactid");
            return (Criteria) this;
        }

        public Criteria andContactidIn(List<String> values) {
            addCriterion("contactid in", values, "contactid");
            return (Criteria) this;
        }

        public Criteria andContactidNotIn(List<String> values) {
            addCriterion("contactid not in", values, "contactid");
            return (Criteria) this;
        }

        public Criteria andContactidBetween(String value1, String value2) {
            addCriterion("contactid between", value1, value2, "contactid");
            return (Criteria) this;
        }

        public Criteria andContactidNotBetween(String value1, String value2) {
            addCriterion("contactid not between", value1, value2, "contactid");
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("s.type =", value, "type");
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

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andPwdIsNull() {
            addCriterion("pwd is null");
            return (Criteria) this;
        }

        public Criteria andPwdIsNotNull() {
            addCriterion("pwd is not null");
            return (Criteria) this;
        }

        public Criteria andPwdEqualTo(String value) {
            addCriterion("pwd =", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotEqualTo(String value) {
            addCriterion("pwd <>", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdGreaterThan(String value) {
            addCriterion("pwd >", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdGreaterThanOrEqualTo(String value) {
            addCriterion("pwd >=", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdLessThan(String value) {
            addCriterion("pwd <", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdLessThanOrEqualTo(String value) {
            addCriterion("pwd <=", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdLike(String value) {
            addCriterion("pwd like", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotLike(String value) {
            addCriterion("pwd not like", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdIn(List<String> values) {
            addCriterion("pwd in", values, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotIn(List<String> values) {
            addCriterion("pwd not in", values, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdBetween(String value1, String value2) {
            addCriterion("pwd between", value1, value2, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotBetween(String value1, String value2) {
            addCriterion("pwd not between", value1, value2, "pwd");
            return (Criteria) this;
        }

        public Criteria andSmtpAddressIsNull() {
            addCriterion("smtp_address is null");
            return (Criteria) this;
        }

        public Criteria andSmtpAddressIsNotNull() {
            addCriterion("smtp_address is not null");
            return (Criteria) this;
        }

        public Criteria andSmtpAddressEqualTo(String value) {
            addCriterion("smtp_address =", value, "smtpAddress");
            return (Criteria) this;
        }

        public Criteria andSmtpAddressNotEqualTo(String value) {
            addCriterion("smtp_address <>", value, "smtpAddress");
            return (Criteria) this;
        }

        public Criteria andSmtpAddressGreaterThan(String value) {
            addCriterion("smtp_address >", value, "smtpAddress");
            return (Criteria) this;
        }

        public Criteria andSmtpAddressGreaterThanOrEqualTo(String value) {
            addCriterion("smtp_address >=", value, "smtpAddress");
            return (Criteria) this;
        }

        public Criteria andSmtpAddressLessThan(String value) {
            addCriterion("smtp_address <", value, "smtpAddress");
            return (Criteria) this;
        }

        public Criteria andSmtpAddressLessThanOrEqualTo(String value) {
            addCriterion("smtp_address <=", value, "smtpAddress");
            return (Criteria) this;
        }

        public Criteria andSmtpAddressLike(String value) {
            addCriterion("smtp_address like", value, "smtpAddress");
            return (Criteria) this;
        }

        public Criteria andSmtpAddressNotLike(String value) {
            addCriterion("smtp_address not like", value, "smtpAddress");
            return (Criteria) this;
        }

        public Criteria andSmtpAddressIn(List<String> values) {
            addCriterion("smtp_address in", values, "smtpAddress");
            return (Criteria) this;
        }

        public Criteria andSmtpAddressNotIn(List<String> values) {
            addCriterion("smtp_address not in", values, "smtpAddress");
            return (Criteria) this;
        }

        public Criteria andSmtpAddressBetween(String value1, String value2) {
            addCriterion("smtp_address between", value1, value2, "smtpAddress");
            return (Criteria) this;
        }

        public Criteria andSmtpAddressNotBetween(String value1, String value2) {
            addCriterion("smtp_address not between", value1, value2, "smtpAddress");
            return (Criteria) this;
        }

        public Criteria andSmtpPortIsNull() {
            addCriterion("smtp_port is null");
            return (Criteria) this;
        }

        public Criteria andSmtpPortIsNotNull() {
            addCriterion("smtp_port is not null");
            return (Criteria) this;
        }

        public Criteria andSmtpPortEqualTo(String value) {
            addCriterion("smtp_port =", value, "smtpPort");
            return (Criteria) this;
        }

        public Criteria andSmtpPortNotEqualTo(String value) {
            addCriterion("smtp_port <>", value, "smtpPort");
            return (Criteria) this;
        }

        public Criteria andSmtpPortGreaterThan(String value) {
            addCriterion("smtp_port >", value, "smtpPort");
            return (Criteria) this;
        }

        public Criteria andSmtpPortGreaterThanOrEqualTo(String value) {
            addCriterion("smtp_port >=", value, "smtpPort");
            return (Criteria) this;
        }

        public Criteria andSmtpPortLessThan(String value) {
            addCriterion("smtp_port <", value, "smtpPort");
            return (Criteria) this;
        }

        public Criteria andSmtpPortLessThanOrEqualTo(String value) {
            addCriterion("smtp_port <=", value, "smtpPort");
            return (Criteria) this;
        }

        public Criteria andSmtpPortLike(String value) {
            addCriterion("smtp_port like", value, "smtpPort");
            return (Criteria) this;
        }

        public Criteria andSmtpPortNotLike(String value) {
            addCriterion("smtp_port not like", value, "smtpPort");
            return (Criteria) this;
        }

        public Criteria andSmtpPortIn(List<String> values) {
            addCriterion("smtp_port in", values, "smtpPort");
            return (Criteria) this;
        }

        public Criteria andSmtpPortNotIn(List<String> values) {
            addCriterion("smtp_port not in", values, "smtpPort");
            return (Criteria) this;
        }

        public Criteria andSmtpPortBetween(String value1, String value2) {
            addCriterion("smtp_port between", value1, value2, "smtpPort");
            return (Criteria) this;
        }

        public Criteria andSmtpPortNotBetween(String value1, String value2) {
            addCriterion("smtp_port not between", value1, value2, "smtpPort");
            return (Criteria) this;
        }

        public Criteria andSendexceptionIsNull() {
            addCriterion("sendException is null");
            return (Criteria) this;
        }

        public Criteria andSendexceptionIsNotNull() {
            addCriterion("sendException is not null");
            return (Criteria) this;
        }

        public Criteria andSendexceptionEqualTo(Integer value) {
            addCriterion("sendException =", value, "sendexception");
            return (Criteria) this;
        }

        public Criteria andSendexceptionNotEqualTo(Integer value) {
            addCriterion("sendException <>", value, "sendexception");
            return (Criteria) this;
        }

        public Criteria andSendexceptionGreaterThan(Integer value) {
            addCriterion("sendException >", value, "sendexception");
            return (Criteria) this;
        }

        public Criteria andSendexceptionGreaterThanOrEqualTo(Integer value) {
            addCriterion("sendException >=", value, "sendexception");
            return (Criteria) this;
        }

        public Criteria andSendexceptionLessThan(Integer value) {
            addCriterion("sendException <", value, "sendexception");
            return (Criteria) this;
        }

        public Criteria andSendexceptionLessThanOrEqualTo(Integer value) {
            addCriterion("sendException <=", value, "sendexception");
            return (Criteria) this;
        }

        public Criteria andSendexceptionIn(List<Integer> values) {
            addCriterion("sendException in", values, "sendexception");
            return (Criteria) this;
        }

        public Criteria andSendexceptionNotIn(List<Integer> values) {
            addCriterion("sendException not in", values, "sendexception");
            return (Criteria) this;
        }

        public Criteria andSendexceptionBetween(Integer value1, Integer value2) {
            addCriterion("sendException between", value1, value2, "sendexception");
            return (Criteria) this;
        }

        public Criteria andSendexceptionNotBetween(Integer value1, Integer value2) {
            addCriterion("sendException not between", value1, value2, "sendexception");
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