package com.niule.znxj.web.model;

import java.util.ArrayList;
import java.util.List;

public class AreainfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AreainfoExample() {
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

        public Criteria andLongitudeIsNull() {
            addCriterion("longitude is null");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNotNull() {
            addCriterion("longitude is not null");
            return (Criteria) this;
        }

        public Criteria andLongitudeEqualTo(String value) {
            addCriterion("longitude =", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotEqualTo(String value) {
            addCriterion("longitude <>", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThan(String value) {
            addCriterion("longitude >", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThanOrEqualTo(String value) {
            addCriterion("longitude >=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThan(String value) {
            addCriterion("longitude <", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThanOrEqualTo(String value) {
            addCriterion("longitude <=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLike(String value) {
            addCriterion("longitude like", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotLike(String value) {
            addCriterion("longitude not like", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIn(List<String> values) {
            addCriterion("longitude in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotIn(List<String> values) {
            addCriterion("longitude not in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeBetween(String value1, String value2) {
            addCriterion("longitude between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotBetween(String value1, String value2) {
            addCriterion("longitude not between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNull() {
            addCriterion("latitude is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNotNull() {
            addCriterion("latitude is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeEqualTo(String value) {
            addCriterion("latitude =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(String value) {
            addCriterion("latitude <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(String value) {
            addCriterion("latitude >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(String value) {
            addCriterion("latitude >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(String value) {
            addCriterion("latitude <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(String value) {
            addCriterion("latitude <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLike(String value) {
            addCriterion("latitude like", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotLike(String value) {
            addCriterion("latitude not like", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<String> values) {
            addCriterion("latitude in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<String> values) {
            addCriterion("latitude not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(String value1, String value2) {
            addCriterion("latitude between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(String value1, String value2) {
            addCriterion("latitude not between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andPlantIsNull() {
            addCriterion("plant is null");
            return (Criteria) this;
        }

        public Criteria andPlantIsNotNull() {
            addCriterion("plant is not null");
            return (Criteria) this;
        }

        public Criteria andPlantEqualTo(Long value) {
            addCriterion("plant =", value, "plant");
            return (Criteria) this;
        }

        public Criteria andPlantNotEqualTo(Long value) {
            addCriterion("plant <>", value, "plant");
            return (Criteria) this;
        }

        public Criteria andPlantGreaterThan(Long value) {
            addCriterion("plant >", value, "plant");
            return (Criteria) this;
        }

        public Criteria andPlantGreaterThanOrEqualTo(Long value) {
            addCriterion("plant >=", value, "plant");
            return (Criteria) this;
        }

        public Criteria andPlantLessThan(Long value) {
            addCriterion("plant <", value, "plant");
            return (Criteria) this;
        }

        public Criteria andPlantLessThanOrEqualTo(Long value) {
            addCriterion("plant <=", value, "plant");
            return (Criteria) this;
        }

        public Criteria andPlantIn(List<Long> values) {
            addCriterion("plant in", values, "plant");
            return (Criteria) this;
        }

        public Criteria andPlantNotIn(List<Long> values) {
            addCriterion("plant not in", values, "plant");
            return (Criteria) this;
        }

        public Criteria andPlantBetween(Long value1, Long value2) {
            addCriterion("plant between", value1, value2, "plant");
            return (Criteria) this;
        }

        public Criteria andPlantNotBetween(Long value1, Long value2) {
            addCriterion("plant not between", value1, value2, "plant");
            return (Criteria) this;
        }

        public Criteria andNfctagIsNull() {
            addCriterion("nfctag is null");
            return (Criteria) this;
        }

        public Criteria andNfctagIsNotNull() {
            addCriterion("nfctag is not null");
            return (Criteria) this;
        }

        public Criteria andNfctagEqualTo(Long value) {
            addCriterion("nfctag =", value, "nfctag");
            return (Criteria) this;
        }

        public Criteria andNfctagNotEqualTo(Long value) {
            addCriterion("nfctag <>", value, "nfctag");
            return (Criteria) this;
        }

        public Criteria andNfctagGreaterThan(Long value) {
            addCriterion("nfctag >", value, "nfctag");
            return (Criteria) this;
        }

        public Criteria andNfctagGreaterThanOrEqualTo(Long value) {
            addCriterion("nfctag >=", value, "nfctag");
            return (Criteria) this;
        }

        public Criteria andNfctagLessThan(Long value) {
            addCriterion("nfctag <", value, "nfctag");
            return (Criteria) this;
        }

        public Criteria andNfctagLessThanOrEqualTo(Long value) {
            addCriterion("nfctag <=", value, "nfctag");
            return (Criteria) this;
        }

        public Criteria andNfctagIn(List<Long> values) {
            addCriterion("nfctag in", values, "nfctag");
            return (Criteria) this;
        }

        public Criteria andNfctagNotIn(List<Long> values) {
            addCriterion("nfctag not in", values, "nfctag");
            return (Criteria) this;
        }

        public Criteria andNfctagBetween(Long value1, Long value2) {
            addCriterion("nfctag between", value1, value2, "nfctag");
            return (Criteria) this;
        }

        public Criteria andNfctagNotBetween(Long value1, Long value2) {
            addCriterion("nfctag not between", value1, value2, "nfctag");
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

        public Criteria andRadiusnumberIsNull() {
            addCriterion("radiusnumber is null");
            return (Criteria) this;
        }

        public Criteria andRadiusnumberIsNotNull() {
            addCriterion("radiusnumber is not null");
            return (Criteria) this;
        }

        public Criteria andRadiusnumberEqualTo(Double value) {
            addCriterion("radiusnumber =", value, "radiusnumber");
            return (Criteria) this;
        }

        public Criteria andRadiusnumberNotEqualTo(Double value) {
            addCriterion("radiusnumber <>", value, "radiusnumber");
            return (Criteria) this;
        }

        public Criteria andRadiusnumberGreaterThan(Double value) {
            addCriterion("radiusnumber >", value, "radiusnumber");
            return (Criteria) this;
        }

        public Criteria andRadiusnumberGreaterThanOrEqualTo(Double value) {
            addCriterion("radiusnumber >=", value, "radiusnumber");
            return (Criteria) this;
        }

        public Criteria andRadiusnumberLessThan(Double value) {
            addCriterion("radiusnumber <", value, "radiusnumber");
            return (Criteria) this;
        }

        public Criteria andRadiusnumberLessThanOrEqualTo(Double value) {
            addCriterion("radiusnumber <=", value, "radiusnumber");
            return (Criteria) this;
        }

        public Criteria andRadiusnumberIn(List<Double> values) {
            addCriterion("radiusnumber in", values, "radiusnumber");
            return (Criteria) this;
        }

        public Criteria andRadiusnumberNotIn(List<Double> values) {
            addCriterion("radiusnumber not in", values, "radiusnumber");
            return (Criteria) this;
        }

        public Criteria andRadiusnumberBetween(Double value1, Double value2) {
            addCriterion("radiusnumber between", value1, value2, "radiusnumber");
            return (Criteria) this;
        }

        public Criteria andRadiusnumberNotBetween(Double value1, Double value2) {
            addCriterion("radiusnumber not between", value1, value2, "radiusnumber");
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