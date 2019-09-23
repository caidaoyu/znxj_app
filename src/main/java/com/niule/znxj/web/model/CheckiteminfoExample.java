package com.niule.znxj.web.model;

import java.util.ArrayList;
import java.util.List;

public class CheckiteminfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CheckiteminfoExample() {
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

        public Criteria andItemnameIsNull() {
            addCriterion("itemname is null");
            return (Criteria) this;
        }

        public Criteria andItemnameIsNotNull() {
            addCriterion("itemname is not null");
            return (Criteria) this;
        }

        public Criteria andItemnameEqualTo(String value) {
            addCriterion("itemname =", value, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemnameNotEqualTo(String value) {
            addCriterion("itemname <>", value, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemnameGreaterThan(String value) {
            addCriterion("itemname >", value, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemnameGreaterThanOrEqualTo(String value) {
            addCriterion("itemname >=", value, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemnameLessThan(String value) {
            addCriterion("itemname <", value, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemnameLessThanOrEqualTo(String value) {
            addCriterion("itemname <=", value, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemnameLike(String value) {
            addCriterion("itemname like", value, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemnameNotLike(String value) {
            addCriterion("itemname not like", value, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemnameIn(List<String> values) {
            addCriterion("itemname in", values, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemnameNotIn(List<String> values) {
            addCriterion("itemname not in", values, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemnameBetween(String value1, String value2) {
            addCriterion("itemname between", value1, value2, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemnameNotBetween(String value1, String value2) {
            addCriterion("itemname not between", value1, value2, "itemname");
            return (Criteria) this;
        }

        public Criteria andKeywordIsNull() {
            addCriterion("keyword is null");
            return (Criteria) this;
        }

        public Criteria andKeywordIsNotNull() {
            addCriterion("keyword is not null");
            return (Criteria) this;
        }

        public Criteria andKeywordEqualTo(String value) {
            addCriterion("keyword =", value, "keyword");
            return (Criteria) this;
        }

        public Criteria andKeywordNotEqualTo(String value) {
            addCriterion("keyword <>", value, "keyword");
            return (Criteria) this;
        }

        public Criteria andKeywordGreaterThan(String value) {
            addCriterion("keyword >", value, "keyword");
            return (Criteria) this;
        }

        public Criteria andKeywordGreaterThanOrEqualTo(String value) {
            addCriterion("keyword >=", value, "keyword");
            return (Criteria) this;
        }

        public Criteria andKeywordLessThan(String value) {
            addCriterion("keyword <", value, "keyword");
            return (Criteria) this;
        }

        public Criteria andKeywordLessThanOrEqualTo(String value) {
            addCriterion("keyword <=", value, "keyword");
            return (Criteria) this;
        }

        public Criteria andKeywordLike(String value) {
            addCriterion("keyword like", value, "keyword");
            return (Criteria) this;
        }

        public Criteria andKeywordNotLike(String value) {
            addCriterion("keyword not like", value, "keyword");
            return (Criteria) this;
        }

        public Criteria andKeywordIn(List<String> values) {
            addCriterion("keyword in", values, "keyword");
            return (Criteria) this;
        }

        public Criteria andKeywordNotIn(List<String> values) {
            addCriterion("keyword not in", values, "keyword");
            return (Criteria) this;
        }

        public Criteria andKeywordBetween(String value1, String value2) {
            addCriterion("keyword between", value1, value2, "keyword");
            return (Criteria) this;
        }

        public Criteria andKeywordNotBetween(String value1, String value2) {
            addCriterion("keyword not between", value1, value2, "keyword");
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

        public Criteria andNormalminIsNull() {
            addCriterion("normalmin is null");
            return (Criteria) this;
        }

        public Criteria andNormalminIsNotNull() {
            addCriterion("normalmin is not null");
            return (Criteria) this;
        }

        public Criteria andNormalminEqualTo(Double value) {
            addCriterion("normalmin =", value, "normalmin");
            return (Criteria) this;
        }

        public Criteria andNormalminNotEqualTo(Double value) {
            addCriterion("normalmin <>", value, "normalmin");
            return (Criteria) this;
        }

        public Criteria andNormalminGreaterThan(Double value) {
            addCriterion("normalmin >", value, "normalmin");
            return (Criteria) this;
        }

        public Criteria andNormalminGreaterThanOrEqualTo(Double value) {
            addCriterion("normalmin >=", value, "normalmin");
            return (Criteria) this;
        }

        public Criteria andNormalminLessThan(Double value) {
            addCriterion("normalmin <", value, "normalmin");
            return (Criteria) this;
        }

        public Criteria andNormalminLessThanOrEqualTo(Double value) {
            addCriterion("normalmin <=", value, "normalmin");
            return (Criteria) this;
        }

        public Criteria andNormalminIn(List<Double> values) {
            addCriterion("normalmin in", values, "normalmin");
            return (Criteria) this;
        }

        public Criteria andNormalminNotIn(List<Double> values) {
            addCriterion("normalmin not in", values, "normalmin");
            return (Criteria) this;
        }

        public Criteria andNormalminBetween(Double value1, Double value2) {
            addCriterion("normalmin between", value1, value2, "normalmin");
            return (Criteria) this;
        }

        public Criteria andNormalminNotBetween(Double value1, Double value2) {
            addCriterion("normalmin not between", value1, value2, "normalmin");
            return (Criteria) this;
        }

        public Criteria andNormalmaxIsNull() {
            addCriterion("normalmax is null");
            return (Criteria) this;
        }

        public Criteria andNormalmaxIsNotNull() {
            addCriterion("normalmax is not null");
            return (Criteria) this;
        }

        public Criteria andNormalmaxEqualTo(Double value) {
            addCriterion("normalmax =", value, "normalmax");
            return (Criteria) this;
        }

        public Criteria andNormalmaxNotEqualTo(Double value) {
            addCriterion("normalmax <>", value, "normalmax");
            return (Criteria) this;
        }

        public Criteria andNormalmaxGreaterThan(Double value) {
            addCriterion("normalmax >", value, "normalmax");
            return (Criteria) this;
        }

        public Criteria andNormalmaxGreaterThanOrEqualTo(Double value) {
            addCriterion("normalmax >=", value, "normalmax");
            return (Criteria) this;
        }

        public Criteria andNormalmaxLessThan(Double value) {
            addCriterion("normalmax <", value, "normalmax");
            return (Criteria) this;
        }

        public Criteria andNormalmaxLessThanOrEqualTo(Double value) {
            addCriterion("normalmax <=", value, "normalmax");
            return (Criteria) this;
        }

        public Criteria andNormalmaxIn(List<Double> values) {
            addCriterion("normalmax in", values, "normalmax");
            return (Criteria) this;
        }

        public Criteria andNormalmaxNotIn(List<Double> values) {
            addCriterion("normalmax not in", values, "normalmax");
            return (Criteria) this;
        }

        public Criteria andNormalmaxBetween(Double value1, Double value2) {
            addCriterion("normalmax between", value1, value2, "normalmax");
            return (Criteria) this;
        }

        public Criteria andNormalmaxNotBetween(Double value1, Double value2) {
            addCriterion("normalmax not between", value1, value2, "normalmax");
            return (Criteria) this;
        }

        public Criteria andUpperwarningIsNull() {
            addCriterion("upperwarning is null");
            return (Criteria) this;
        }

        public Criteria andUpperwarningIsNotNull() {
            addCriterion("upperwarning is not null");
            return (Criteria) this;
        }

        public Criteria andUpperwarningEqualTo(Double value) {
            addCriterion("upperwarning =", value, "upperwarning");
            return (Criteria) this;
        }

        public Criteria andUpperwarningNotEqualTo(Double value) {
            addCriterion("upperwarning <>", value, "upperwarning");
            return (Criteria) this;
        }

        public Criteria andUpperwarningGreaterThan(Double value) {
            addCriterion("upperwarning >", value, "upperwarning");
            return (Criteria) this;
        }

        public Criteria andUpperwarningGreaterThanOrEqualTo(Double value) {
            addCriterion("upperwarning >=", value, "upperwarning");
            return (Criteria) this;
        }

        public Criteria andUpperwarningLessThan(Double value) {
            addCriterion("upperwarning <", value, "upperwarning");
            return (Criteria) this;
        }

        public Criteria andUpperwarningLessThanOrEqualTo(Double value) {
            addCriterion("upperwarning <=", value, "upperwarning");
            return (Criteria) this;
        }

        public Criteria andUpperwarningIn(List<Double> values) {
            addCriterion("upperwarning in", values, "upperwarning");
            return (Criteria) this;
        }

        public Criteria andUpperwarningNotIn(List<Double> values) {
            addCriterion("upperwarning not in", values, "upperwarning");
            return (Criteria) this;
        }

        public Criteria andUpperwarningBetween(Double value1, Double value2) {
            addCriterion("upperwarning between", value1, value2, "upperwarning");
            return (Criteria) this;
        }

        public Criteria andUpperwarningNotBetween(Double value1, Double value2) {
            addCriterion("upperwarning not between", value1, value2, "upperwarning");
            return (Criteria) this;
        }

        public Criteria andLowerwarningIsNull() {
            addCriterion("lowerwarning is null");
            return (Criteria) this;
        }

        public Criteria andLowerwarningIsNotNull() {
            addCriterion("lowerwarning is not null");
            return (Criteria) this;
        }

        public Criteria andLowerwarningEqualTo(Double value) {
            addCriterion("lowerwarning =", value, "lowerwarning");
            return (Criteria) this;
        }

        public Criteria andLowerwarningNotEqualTo(Double value) {
            addCriterion("lowerwarning <>", value, "lowerwarning");
            return (Criteria) this;
        }

        public Criteria andLowerwarningGreaterThan(Double value) {
            addCriterion("lowerwarning >", value, "lowerwarning");
            return (Criteria) this;
        }

        public Criteria andLowerwarningGreaterThanOrEqualTo(Double value) {
            addCriterion("lowerwarning >=", value, "lowerwarning");
            return (Criteria) this;
        }

        public Criteria andLowerwarningLessThan(Double value) {
            addCriterion("lowerwarning <", value, "lowerwarning");
            return (Criteria) this;
        }

        public Criteria andLowerwarningLessThanOrEqualTo(Double value) {
            addCriterion("lowerwarning <=", value, "lowerwarning");
            return (Criteria) this;
        }

        public Criteria andLowerwarningIn(List<Double> values) {
            addCriterion("lowerwarning in", values, "lowerwarning");
            return (Criteria) this;
        }

        public Criteria andLowerwarningNotIn(List<Double> values) {
            addCriterion("lowerwarning not in", values, "lowerwarning");
            return (Criteria) this;
        }

        public Criteria andLowerwarningBetween(Double value1, Double value2) {
            addCriterion("lowerwarning between", value1, value2, "lowerwarning");
            return (Criteria) this;
        }

        public Criteria andLowerwarningNotBetween(Double value1, Double value2) {
            addCriterion("lowerwarning not between", value1, value2, "lowerwarning");
            return (Criteria) this;
        }

        public Criteria andRecordidIsNull() {
            addCriterion("recordid is null");
            return (Criteria) this;
        }

        public Criteria andRecordidIsNotNull() {
            addCriterion("recordid is not null");
            return (Criteria) this;
        }

        public Criteria andRecordidEqualTo(Long value) {
            addCriterion("recordid =", value, "recordid");
            return (Criteria) this;
        }

        public Criteria andRecordidNotEqualTo(Long value) {
            addCriterion("recordid <>", value, "recordid");
            return (Criteria) this;
        }

        public Criteria andRecordidGreaterThan(Long value) {
            addCriterion("recordid >", value, "recordid");
            return (Criteria) this;
        }

        public Criteria andRecordidGreaterThanOrEqualTo(Long value) {
            addCriterion("recordid >=", value, "recordid");
            return (Criteria) this;
        }

        public Criteria andRecordidLessThan(Long value) {
            addCriterion("recordid <", value, "recordid");
            return (Criteria) this;
        }

        public Criteria andRecordidLessThanOrEqualTo(Long value) {
            addCriterion("recordid <=", value, "recordid");
            return (Criteria) this;
        }

        public Criteria andRecordidIn(List<Long> values) {
            addCriterion("recordid in", values, "recordid");
            return (Criteria) this;
        }

        public Criteria andRecordidNotIn(List<Long> values) {
            addCriterion("recordid not in", values, "recordid");
            return (Criteria) this;
        }

        public Criteria andRecordidBetween(Long value1, Long value2) {
            addCriterion("recordid between", value1, value2, "recordid");
            return (Criteria) this;
        }

        public Criteria andRecordidNotBetween(Long value1, Long value2) {
            addCriterion("recordid not between", value1, value2, "recordid");
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