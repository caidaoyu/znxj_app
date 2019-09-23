package com.niule.znxj.web.model;

import java.util.ArrayList;
import java.util.List;

public class ReportcontentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ReportcontentExample() {
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

        public Criteria andAreanameIsNull() {
            addCriterion("areaname is null");
            return (Criteria) this;
        }

        public Criteria andAreanameIsNotNull() {
            addCriterion("areaname is not null");
            return (Criteria) this;
        }

        public Criteria andAreanameEqualTo(String value) {
            addCriterion("areaname =", value, "areaname");
            return (Criteria) this;
        }

        public Criteria andAreanameNotEqualTo(String value) {
            addCriterion("areaname <>", value, "areaname");
            return (Criteria) this;
        }

        public Criteria andAreanameGreaterThan(String value) {
            addCriterion("areaname >", value, "areaname");
            return (Criteria) this;
        }

        public Criteria andAreanameGreaterThanOrEqualTo(String value) {
            addCriterion("areaname >=", value, "areaname");
            return (Criteria) this;
        }

        public Criteria andAreanameLessThan(String value) {
            addCriterion("areaname <", value, "areaname");
            return (Criteria) this;
        }

        public Criteria andAreanameLessThanOrEqualTo(String value) {
            addCriterion("areaname <=", value, "areaname");
            return (Criteria) this;
        }

        public Criteria andAreanameLike(String value) {
            addCriterion("areaname like", value, "areaname");
            return (Criteria) this;
        }

        public Criteria andAreanameNotLike(String value) {
            addCriterion("areaname not like", value, "areaname");
            return (Criteria) this;
        }

        public Criteria andAreanameIn(List<String> values) {
            addCriterion("areaname in", values, "areaname");
            return (Criteria) this;
        }

        public Criteria andAreanameNotIn(List<String> values) {
            addCriterion("areaname not in", values, "areaname");
            return (Criteria) this;
        }

        public Criteria andAreanameBetween(String value1, String value2) {
            addCriterion("areaname between", value1, value2, "areaname");
            return (Criteria) this;
        }

        public Criteria andAreanameNotBetween(String value1, String value2) {
            addCriterion("areaname not between", value1, value2, "areaname");
            return (Criteria) this;
        }

        public Criteria andEquipnameIsNull() {
            addCriterion("equipname is null");
            return (Criteria) this;
        }

        public Criteria andEquipnameIsNotNull() {
            addCriterion("equipname is not null");
            return (Criteria) this;
        }

        public Criteria andEquipnameEqualTo(String value) {
            addCriterion("equipname =", value, "equipname");
            return (Criteria) this;
        }

        public Criteria andEquipnameNotEqualTo(String value) {
            addCriterion("equipname <>", value, "equipname");
            return (Criteria) this;
        }

        public Criteria andEquipnameGreaterThan(String value) {
            addCriterion("equipname >", value, "equipname");
            return (Criteria) this;
        }

        public Criteria andEquipnameGreaterThanOrEqualTo(String value) {
            addCriterion("equipname >=", value, "equipname");
            return (Criteria) this;
        }

        public Criteria andEquipnameLessThan(String value) {
            addCriterion("equipname <", value, "equipname");
            return (Criteria) this;
        }

        public Criteria andEquipnameLessThanOrEqualTo(String value) {
            addCriterion("equipname <=", value, "equipname");
            return (Criteria) this;
        }

        public Criteria andEquipnameLike(String value) {
            addCriterion("equipname like", value, "equipname");
            return (Criteria) this;
        }

        public Criteria andEquipnameNotLike(String value) {
            addCriterion("equipname not like", value, "equipname");
            return (Criteria) this;
        }

        public Criteria andEquipnameIn(List<String> values) {
            addCriterion("equipname in", values, "equipname");
            return (Criteria) this;
        }

        public Criteria andEquipnameNotIn(List<String> values) {
            addCriterion("equipname not in", values, "equipname");
            return (Criteria) this;
        }

        public Criteria andEquipnameBetween(String value1, String value2) {
            addCriterion("equipname between", value1, value2, "equipname");
            return (Criteria) this;
        }

        public Criteria andEquipnameNotBetween(String value1, String value2) {
            addCriterion("equipname not between", value1, value2, "equipname");
            return (Criteria) this;
        }

        public Criteria andChecknameIsNull() {
            addCriterion("checkname is null");
            return (Criteria) this;
        }

        public Criteria andChecknameIsNotNull() {
            addCriterion("checkname is not null");
            return (Criteria) this;
        }

        public Criteria andChecknameEqualTo(String value) {
            addCriterion("checkname =", value, "checkname");
            return (Criteria) this;
        }

        public Criteria andChecknameNotEqualTo(String value) {
            addCriterion("checkname <>", value, "checkname");
            return (Criteria) this;
        }

        public Criteria andChecknameGreaterThan(String value) {
            addCriterion("checkname >", value, "checkname");
            return (Criteria) this;
        }

        public Criteria andChecknameGreaterThanOrEqualTo(String value) {
            addCriterion("checkname >=", value, "checkname");
            return (Criteria) this;
        }

        public Criteria andChecknameLessThan(String value) {
            addCriterion("checkname <", value, "checkname");
            return (Criteria) this;
        }

        public Criteria andChecknameLessThanOrEqualTo(String value) {
            addCriterion("checkname <=", value, "checkname");
            return (Criteria) this;
        }

        public Criteria andChecknameLike(String value) {
            addCriterion("checkname like", value, "checkname");
            return (Criteria) this;
        }

        public Criteria andChecknameNotLike(String value) {
            addCriterion("checkname not like", value, "checkname");
            return (Criteria) this;
        }

        public Criteria andChecknameIn(List<String> values) {
            addCriterion("checkname in", values, "checkname");
            return (Criteria) this;
        }

        public Criteria andChecknameNotIn(List<String> values) {
            addCriterion("checkname not in", values, "checkname");
            return (Criteria) this;
        }

        public Criteria andChecknameBetween(String value1, String value2) {
            addCriterion("checkname between", value1, value2, "checkname");
            return (Criteria) this;
        }

        public Criteria andChecknameNotBetween(String value1, String value2) {
            addCriterion("checkname not between", value1, value2, "checkname");
            return (Criteria) this;
        }

        public Criteria andChecktypeIsNull() {
            addCriterion("checktype is null");
            return (Criteria) this;
        }

        public Criteria andChecktypeIsNotNull() {
            addCriterion("checktype is not null");
            return (Criteria) this;
        }

        public Criteria andChecktypeEqualTo(String value) {
            addCriterion("checktype =", value, "checktype");
            return (Criteria) this;
        }

        public Criteria andChecktypeNotEqualTo(String value) {
            addCriterion("checktype <>", value, "checktype");
            return (Criteria) this;
        }

        public Criteria andChecktypeGreaterThan(String value) {
            addCriterion("checktype >", value, "checktype");
            return (Criteria) this;
        }

        public Criteria andChecktypeGreaterThanOrEqualTo(String value) {
            addCriterion("checktype >=", value, "checktype");
            return (Criteria) this;
        }

        public Criteria andChecktypeLessThan(String value) {
            addCriterion("checktype <", value, "checktype");
            return (Criteria) this;
        }

        public Criteria andChecktypeLessThanOrEqualTo(String value) {
            addCriterion("checktype <=", value, "checktype");
            return (Criteria) this;
        }

        public Criteria andChecktypeLike(String value) {
            addCriterion("checktype like", value, "checktype");
            return (Criteria) this;
        }

        public Criteria andChecktypeNotLike(String value) {
            addCriterion("checktype not like", value, "checktype");
            return (Criteria) this;
        }

        public Criteria andChecktypeIn(List<String> values) {
            addCriterion("checktype in", values, "checktype");
            return (Criteria) this;
        }

        public Criteria andChecktypeNotIn(List<String> values) {
            addCriterion("checktype not in", values, "checktype");
            return (Criteria) this;
        }

        public Criteria andChecktypeBetween(String value1, String value2) {
            addCriterion("checktype between", value1, value2, "checktype");
            return (Criteria) this;
        }

        public Criteria andChecktypeNotBetween(String value1, String value2) {
            addCriterion("checktype not between", value1, value2, "checktype");
            return (Criteria) this;
        }

        public Criteria andNumvalueIsNull() {
            addCriterion("numvalue is null");
            return (Criteria) this;
        }

        public Criteria andNumvalueIsNotNull() {
            addCriterion("numvalue is not null");
            return (Criteria) this;
        }

        public Criteria andNumvalueEqualTo(String value) {
            addCriterion("numvalue =", value, "numvalue");
            return (Criteria) this;
        }

        public Criteria andNumvalueNotEqualTo(String value) {
            addCriterion("numvalue <>", value, "numvalue");
            return (Criteria) this;
        }

        public Criteria andNumvalueGreaterThan(String value) {
            addCriterion("numvalue >", value, "numvalue");
            return (Criteria) this;
        }

        public Criteria andNumvalueGreaterThanOrEqualTo(String value) {
            addCriterion("numvalue >=", value, "numvalue");
            return (Criteria) this;
        }

        public Criteria andNumvalueLessThan(String value) {
            addCriterion("numvalue <", value, "numvalue");
            return (Criteria) this;
        }

        public Criteria andNumvalueLessThanOrEqualTo(String value) {
            addCriterion("numvalue <=", value, "numvalue");
            return (Criteria) this;
        }

        public Criteria andNumvalueLike(String value) {
            addCriterion("numvalue like", value, "numvalue");
            return (Criteria) this;
        }

        public Criteria andNumvalueNotLike(String value) {
            addCriterion("numvalue not like", value, "numvalue");
            return (Criteria) this;
        }

        public Criteria andNumvalueIn(List<String> values) {
            addCriterion("numvalue in", values, "numvalue");
            return (Criteria) this;
        }

        public Criteria andNumvalueNotIn(List<String> values) {
            addCriterion("numvalue not in", values, "numvalue");
            return (Criteria) this;
        }

        public Criteria andNumvalueBetween(String value1, String value2) {
            addCriterion("numvalue between", value1, value2, "numvalue");
            return (Criteria) this;
        }

        public Criteria andNumvalueNotBetween(String value1, String value2) {
            addCriterion("numvalue not between", value1, value2, "numvalue");
            return (Criteria) this;
        }

        public Criteria andRecordnameIsNull() {
            addCriterion("recordname is null");
            return (Criteria) this;
        }

        public Criteria andRecordnameIsNotNull() {
            addCriterion("recordname is not null");
            return (Criteria) this;
        }

        public Criteria andRecordnameEqualTo(String value) {
            addCriterion("recordname =", value, "recordname");
            return (Criteria) this;
        }

        public Criteria andRecordnameNotEqualTo(String value) {
            addCriterion("recordname <>", value, "recordname");
            return (Criteria) this;
        }

        public Criteria andRecordnameGreaterThan(String value) {
            addCriterion("recordname >", value, "recordname");
            return (Criteria) this;
        }

        public Criteria andRecordnameGreaterThanOrEqualTo(String value) {
            addCriterion("recordname >=", value, "recordname");
            return (Criteria) this;
        }

        public Criteria andRecordnameLessThan(String value) {
            addCriterion("recordname <", value, "recordname");
            return (Criteria) this;
        }

        public Criteria andRecordnameLessThanOrEqualTo(String value) {
            addCriterion("recordname <=", value, "recordname");
            return (Criteria) this;
        }

        public Criteria andRecordnameLike(String value) {
            addCriterion("recordname like", value, "recordname");
            return (Criteria) this;
        }

        public Criteria andRecordnameNotLike(String value) {
            addCriterion("recordname not like", value, "recordname");
            return (Criteria) this;
        }

        public Criteria andRecordnameIn(List<String> values) {
            addCriterion("recordname in", values, "recordname");
            return (Criteria) this;
        }

        public Criteria andRecordnameNotIn(List<String> values) {
            addCriterion("recordname not in", values, "recordname");
            return (Criteria) this;
        }

        public Criteria andRecordnameBetween(String value1, String value2) {
            addCriterion("recordname between", value1, value2, "recordname");
            return (Criteria) this;
        }

        public Criteria andRecordnameNotBetween(String value1, String value2) {
            addCriterion("recordname not between", value1, value2, "recordname");
            return (Criteria) this;
        }

        public Criteria andUnitnameIsNull() {
            addCriterion("unitname is null");
            return (Criteria) this;
        }

        public Criteria andUnitnameIsNotNull() {
            addCriterion("unitname is not null");
            return (Criteria) this;
        }

        public Criteria andUnitnameEqualTo(String value) {
            addCriterion("unitname =", value, "unitname");
            return (Criteria) this;
        }

        public Criteria andUnitnameNotEqualTo(String value) {
            addCriterion("unitname <>", value, "unitname");
            return (Criteria) this;
        }

        public Criteria andUnitnameGreaterThan(String value) {
            addCriterion("unitname >", value, "unitname");
            return (Criteria) this;
        }

        public Criteria andUnitnameGreaterThanOrEqualTo(String value) {
            addCriterion("unitname >=", value, "unitname");
            return (Criteria) this;
        }

        public Criteria andUnitnameLessThan(String value) {
            addCriterion("unitname <", value, "unitname");
            return (Criteria) this;
        }

        public Criteria andUnitnameLessThanOrEqualTo(String value) {
            addCriterion("unitname <=", value, "unitname");
            return (Criteria) this;
        }

        public Criteria andUnitnameLike(String value) {
            addCriterion("unitname like", value, "unitname");
            return (Criteria) this;
        }

        public Criteria andUnitnameNotLike(String value) {
            addCriterion("unitname not like", value, "unitname");
            return (Criteria) this;
        }

        public Criteria andUnitnameIn(List<String> values) {
            addCriterion("unitname in", values, "unitname");
            return (Criteria) this;
        }

        public Criteria andUnitnameNotIn(List<String> values) {
            addCriterion("unitname not in", values, "unitname");
            return (Criteria) this;
        }

        public Criteria andUnitnameBetween(String value1, String value2) {
            addCriterion("unitname between", value1, value2, "unitname");
            return (Criteria) this;
        }

        public Criteria andUnitnameNotBetween(String value1, String value2) {
            addCriterion("unitname not between", value1, value2, "unitname");
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

        public Criteria andReportstateEqualTo(String value) {
            addCriterion("reportstate =", value, "reportstate");
            return (Criteria) this;
        }

        public Criteria andReportstateWithAliasEqualTo(String value) {
            addCriterion("c.reportstate =", value, "reportstate");
            return (Criteria) this;
        }

        public Criteria andReportstateNotEqualTo(String value) {
            addCriterion("reportstate <>", value, "reportstate");
            return (Criteria) this;
        }

        public Criteria andReportstateGreaterThan(String value) {
            addCriterion("reportstate >", value, "reportstate");
            return (Criteria) this;
        }

        public Criteria andReportstateGreaterThanOrEqualTo(String value) {
            addCriterion("reportstate >=", value, "reportstate");
            return (Criteria) this;
        }

        public Criteria andReportstateLessThan(String value) {
            addCriterion("reportstate <", value, "reportstate");
            return (Criteria) this;
        }

        public Criteria andReportstateLessThanOrEqualTo(String value) {
            addCriterion("reportstate <=", value, "reportstate");
            return (Criteria) this;
        }

        public Criteria andReportstateLike(String value) {
            addCriterion("reportstate like", value, "reportstate");
            return (Criteria) this;
        }

        public Criteria andReportstateNotLike(String value) {
            addCriterion("reportstate not like", value, "reportstate");
            return (Criteria) this;
        }

        public Criteria andReportstateIn(List<String> values) {
            addCriterion("reportstate in", values, "reportstate");
            return (Criteria) this;
        }

        public Criteria andReportstateNotIn(List<String> values) {
            addCriterion("reportstate not in", values, "reportstate");
            return (Criteria) this;
        }

        public Criteria andReportstateBetween(String value1, String value2) {
            addCriterion("reportstate between", value1, value2, "reportstate");
            return (Criteria) this;
        }

        public Criteria andReportstateNotBetween(String value1, String value2) {
            addCriterion("reportstate not between", value1, value2, "reportstate");
            return (Criteria) this;
        }

        public Criteria andErrcontentIsNull() {
            addCriterion("errcontent is null");
            return (Criteria) this;
        }

        public Criteria andErrcontentIsNotNull() {
            addCriterion("errcontent is not null");
            return (Criteria) this;
        }

        public Criteria andErrcontentEqualTo(String value) {
            addCriterion("errcontent =", value, "errcontent");
            return (Criteria) this;
        }

        public Criteria andErrcontentNotEqualTo(String value) {
            addCriterion("errcontent <>", value, "errcontent");
            return (Criteria) this;
        }

        public Criteria andErrcontentGreaterThan(String value) {
            addCriterion("errcontent >", value, "errcontent");
            return (Criteria) this;
        }

        public Criteria andErrcontentGreaterThanOrEqualTo(String value) {
            addCriterion("errcontent >=", value, "errcontent");
            return (Criteria) this;
        }

        public Criteria andErrcontentLessThan(String value) {
            addCriterion("errcontent <", value, "errcontent");
            return (Criteria) this;
        }

        public Criteria andErrcontentLessThanOrEqualTo(String value) {
            addCriterion("errcontent <=", value, "errcontent");
            return (Criteria) this;
        }

        public Criteria andErrcontentLike(String value) {
            addCriterion("errcontent like", value, "errcontent");
            return (Criteria) this;
        }

        public Criteria andErrcontentNotLike(String value) {
            addCriterion("errcontent not like", value, "errcontent");
            return (Criteria) this;
        }

        public Criteria andErrcontentIn(List<String> values) {
            addCriterion("errcontent in", values, "errcontent");
            return (Criteria) this;
        }

        public Criteria andErrcontentNotIn(List<String> values) {
            addCriterion("errcontent not in", values, "errcontent");
            return (Criteria) this;
        }

        public Criteria andErrcontentBetween(String value1, String value2) {
            addCriterion("errcontent between", value1, value2, "errcontent");
            return (Criteria) this;
        }

        public Criteria andErrcontentNotBetween(String value1, String value2) {
            addCriterion("errcontent not between", value1, value2, "errcontent");
            return (Criteria) this;
        }

        public Criteria andAreaskipIsNull() {
            addCriterion("areaskip is null");
            return (Criteria) this;
        }

        public Criteria andAreaskipIsNotNull() {
            addCriterion("areaskip is not null");
            return (Criteria) this;
        }

        public Criteria andAreaskipEqualTo(Integer value) {
            addCriterion("areaskip =", value, "areaskip");
            return (Criteria) this;
        }

        public Criteria andAreaskipNotEqualTo(Integer value) {
            addCriterion("areaskip <>", value, "areaskip");
            return (Criteria) this;
        }

        public Criteria andAreaskipGreaterThan(Integer value) {
            addCriterion("areaskip >", value, "areaskip");
            return (Criteria) this;
        }

        public Criteria andAreaskipGreaterThanOrEqualTo(Integer value) {
            addCriterion("areaskip >=", value, "areaskip");
            return (Criteria) this;
        }

        public Criteria andAreaskipLessThan(Integer value) {
            addCriterion("areaskip <", value, "areaskip");
            return (Criteria) this;
        }

        public Criteria andAreaskipLessThanOrEqualTo(Integer value) {
            addCriterion("areaskip <=", value, "areaskip");
            return (Criteria) this;
        }

        public Criteria andAreaskipIn(List<Integer> values) {
            addCriterion("areaskip in", values, "areaskip");
            return (Criteria) this;
        }

        public Criteria andAreaskipNotIn(List<Integer> values) {
            addCriterion("areaskip not in", values, "areaskip");
            return (Criteria) this;
        }

        public Criteria andAreaskipBetween(Integer value1, Integer value2) {
            addCriterion("areaskip between", value1, value2, "areaskip");
            return (Criteria) this;
        }

        public Criteria andAreaskipNotBetween(Integer value1, Integer value2) {
            addCriterion("areaskip not between", value1, value2, "areaskip");
            return (Criteria) this;
        }

        public Criteria andEquipmentskipIsNull() {
            addCriterion("equipmentskip is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentskipIsNotNull() {
            addCriterion("equipmentskip is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentskipEqualTo(Integer value) {
            addCriterion("equipmentskip =", value, "equipmentskip");
            return (Criteria) this;
        }

        public Criteria andEquipmentskipNotEqualTo(Integer value) {
            addCriterion("equipmentskip <>", value, "equipmentskip");
            return (Criteria) this;
        }

        public Criteria andEquipmentskipGreaterThan(Integer value) {
            addCriterion("equipmentskip >", value, "equipmentskip");
            return (Criteria) this;
        }

        public Criteria andEquipmentskipGreaterThanOrEqualTo(Integer value) {
            addCriterion("equipmentskip >=", value, "equipmentskip");
            return (Criteria) this;
        }

        public Criteria andEquipmentskipLessThan(Integer value) {
            addCriterion("equipmentskip <", value, "equipmentskip");
            return (Criteria) this;
        }

        public Criteria andEquipmentskipLessThanOrEqualTo(Integer value) {
            addCriterion("equipmentskip <=", value, "equipmentskip");
            return (Criteria) this;
        }

        public Criteria andEquipmentskipIn(List<Integer> values) {
            addCriterion("equipmentskip in", values, "equipmentskip");
            return (Criteria) this;
        }

        public Criteria andEquipmentskipNotIn(List<Integer> values) {
            addCriterion("equipmentskip not in", values, "equipmentskip");
            return (Criteria) this;
        }

        public Criteria andEquipmentskipBetween(Integer value1, Integer value2) {
            addCriterion("equipmentskip between", value1, value2, "equipmentskip");
            return (Criteria) this;
        }

        public Criteria andEquipmentskipNotBetween(Integer value1, Integer value2) {
            addCriterion("equipmentskip not between", value1, value2, "equipmentskip");
            return (Criteria) this;
        }

        public Criteria andAreaskipdescIsNull() {
            addCriterion("areaskipdesc is null");
            return (Criteria) this;
        }

        public Criteria andAreaskipdescIsNotNull() {
            addCriterion("areaskipdesc is not null");
            return (Criteria) this;
        }

        public Criteria andAreaskipdescEqualTo(String value) {
            addCriterion("areaskipdesc =", value, "areaskipdesc");
            return (Criteria) this;
        }

        public Criteria andAreaskipdescNotEqualTo(String value) {
            addCriterion("areaskipdesc <>", value, "areaskipdesc");
            return (Criteria) this;
        }

        public Criteria andAreaskipdescGreaterThan(String value) {
            addCriterion("areaskipdesc >", value, "areaskipdesc");
            return (Criteria) this;
        }

        public Criteria andAreaskipdescGreaterThanOrEqualTo(String value) {
            addCriterion("areaskipdesc >=", value, "areaskipdesc");
            return (Criteria) this;
        }

        public Criteria andAreaskipdescLessThan(String value) {
            addCriterion("areaskipdesc <", value, "areaskipdesc");
            return (Criteria) this;
        }

        public Criteria andAreaskipdescLessThanOrEqualTo(String value) {
            addCriterion("areaskipdesc <=", value, "areaskipdesc");
            return (Criteria) this;
        }

        public Criteria andAreaskipdescLike(String value) {
            addCriterion("areaskipdesc like", value, "areaskipdesc");
            return (Criteria) this;
        }

        public Criteria andAreaskipdescNotLike(String value) {
            addCriterion("areaskipdesc not like", value, "areaskipdesc");
            return (Criteria) this;
        }

        public Criteria andAreaskipdescIn(List<String> values) {
            addCriterion("areaskipdesc in", values, "areaskipdesc");
            return (Criteria) this;
        }

        public Criteria andAreaskipdescNotIn(List<String> values) {
            addCriterion("areaskipdesc not in", values, "areaskipdesc");
            return (Criteria) this;
        }

        public Criteria andAreaskipdescBetween(String value1, String value2) {
            addCriterion("areaskipdesc between", value1, value2, "areaskipdesc");
            return (Criteria) this;
        }

        public Criteria andAreaskipdescNotBetween(String value1, String value2) {
            addCriterion("areaskipdesc not between", value1, value2, "areaskipdesc");
            return (Criteria) this;
        }

        public Criteria andEquipmentskipdescIsNull() {
            addCriterion("equipmentskipdesc is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentskipdescIsNotNull() {
            addCriterion("equipmentskipdesc is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentskipdescEqualTo(String value) {
            addCriterion("equipmentskipdesc =", value, "equipmentskipdesc");
            return (Criteria) this;
        }

        public Criteria andEquipmentskipdescNotEqualTo(String value) {
            addCriterion("equipmentskipdesc <>", value, "equipmentskipdesc");
            return (Criteria) this;
        }

        public Criteria andEquipmentskipdescGreaterThan(String value) {
            addCriterion("equipmentskipdesc >", value, "equipmentskipdesc");
            return (Criteria) this;
        }

        public Criteria andEquipmentskipdescGreaterThanOrEqualTo(String value) {
            addCriterion("equipmentskipdesc >=", value, "equipmentskipdesc");
            return (Criteria) this;
        }

        public Criteria andEquipmentskipdescLessThan(String value) {
            addCriterion("equipmentskipdesc <", value, "equipmentskipdesc");
            return (Criteria) this;
        }

        public Criteria andEquipmentskipdescLessThanOrEqualTo(String value) {
            addCriterion("equipmentskipdesc <=", value, "equipmentskipdesc");
            return (Criteria) this;
        }

        public Criteria andEquipmentskipdescLike(String value) {
            addCriterion("equipmentskipdesc like", value, "equipmentskipdesc");
            return (Criteria) this;
        }

        public Criteria andEquipmentskipdescNotLike(String value) {
            addCriterion("equipmentskipdesc not like", value, "equipmentskipdesc");
            return (Criteria) this;
        }

        public Criteria andEquipmentskipdescIn(List<String> values) {
            addCriterion("equipmentskipdesc in", values, "equipmentskipdesc");
            return (Criteria) this;
        }

        public Criteria andEquipmentskipdescNotIn(List<String> values) {
            addCriterion("equipmentskipdesc not in", values, "equipmentskipdesc");
            return (Criteria) this;
        }

        public Criteria andEquipmentskipdescBetween(String value1, String value2) {
            addCriterion("equipmentskipdesc between", value1, value2, "equipmentskipdesc");
            return (Criteria) this;
        }

        public Criteria andEquipmentskipdescNotBetween(String value1, String value2) {
            addCriterion("equipmentskipdesc not between", value1, value2, "equipmentskipdesc");
            return (Criteria) this;
        }

        public Criteria andOperationtimeIsNull() {
            addCriterion("operationtime is null");
            return (Criteria) this;
        }

        public Criteria andOperationtimeIsNotNull() {
            addCriterion("operationtime is not null");
            return (Criteria) this;
        }

        public Criteria andOperationtimeEqualTo(String value) {
            addCriterion("operationtime =", value, "operationtime");
            return (Criteria) this;
        }

        public Criteria andOperationtimeNotEqualTo(String value) {
            addCriterion("operationtime <>", value, "operationtime");
            return (Criteria) this;
        }

        public Criteria andOperationtimeGreaterThan(String value) {
            addCriterion("operationtime >", value, "operationtime");
            return (Criteria) this;
        }

        public Criteria andOperationtimeGreaterThanOrEqualTo(String value) {
            addCriterion("operationtime >=", value, "operationtime");
            return (Criteria) this;
        }

        public Criteria andOperationtimeLessThan(String value) {
            addCriterion("operationtime <", value, "operationtime");
            return (Criteria) this;
        }

        public Criteria andOperationtimeLessThanOrEqualTo(String value) {
            addCriterion("operationtime <=", value, "operationtime");
            return (Criteria) this;
        }

        public Criteria andOperationtimeLike(String value) {
            addCriterion("operationtime like", value, "operationtime");
            return (Criteria) this;
        }

        public Criteria andOperationtimeNotLike(String value) {
            addCriterion("operationtime not like", value, "operationtime");
            return (Criteria) this;
        }

        public Criteria andOperationtimeIn(List<String> values) {
            addCriterion("operationtime in", values, "operationtime");
            return (Criteria) this;
        }

        public Criteria andOperationtimeNotIn(List<String> values) {
            addCriterion("operationtime not in", values, "operationtime");
            return (Criteria) this;
        }

        public Criteria andOperationtimeBetween(String value1, String value2) {
            addCriterion("operationtime between", value1, value2, "operationtime");
            return (Criteria) this;
        }

        public Criteria andOperationtimeNotBetween(String value1, String value2) {
            addCriterion("operationtime not between", value1, value2, "operationtime");
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

        public Criteria andLowerwarningIsNull() {
            addCriterion("lowerwarning is null");
            return (Criteria) this;
        }

        public Criteria andLowerwarningIsNotNull() {
            addCriterion("lowerwarning is not null");
            return (Criteria) this;
        }

        public Criteria andLowerwarningEqualTo(String value) {
            addCriterion("lowerwarning =", value, "lowerwarning");
            return (Criteria) this;
        }

        public Criteria andLowerwarningNotEqualTo(String value) {
            addCriterion("lowerwarning <>", value, "lowerwarning");
            return (Criteria) this;
        }

        public Criteria andLowerwarningGreaterThan(String value) {
            addCriterion("lowerwarning >", value, "lowerwarning");
            return (Criteria) this;
        }

        public Criteria andLowerwarningGreaterThanOrEqualTo(String value) {
            addCriterion("lowerwarning >=", value, "lowerwarning");
            return (Criteria) this;
        }

        public Criteria andLowerwarningLessThan(String value) {
            addCriterion("lowerwarning <", value, "lowerwarning");
            return (Criteria) this;
        }

        public Criteria andLowerwarningLessThanOrEqualTo(String value) {
            addCriterion("lowerwarning <=", value, "lowerwarning");
            return (Criteria) this;
        }

        public Criteria andLowerwarningLike(String value) {
            addCriterion("lowerwarning like", value, "lowerwarning");
            return (Criteria) this;
        }

        public Criteria andLowerwarningNotLike(String value) {
            addCriterion("lowerwarning not like", value, "lowerwarning");
            return (Criteria) this;
        }

        public Criteria andLowerwarningIn(List<String> values) {
            addCriterion("lowerwarning in", values, "lowerwarning");
            return (Criteria) this;
        }

        public Criteria andLowerwarningNotIn(List<String> values) {
            addCriterion("lowerwarning not in", values, "lowerwarning");
            return (Criteria) this;
        }

        public Criteria andLowerwarningBetween(String value1, String value2) {
            addCriterion("lowerwarning between", value1, value2, "lowerwarning");
            return (Criteria) this;
        }

        public Criteria andLowerwarningNotBetween(String value1, String value2) {
            addCriterion("lowerwarning not between", value1, value2, "lowerwarning");
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

        public Criteria andNormalmaxEqualTo(String value) {
            addCriterion("normalmax =", value, "normalmax");
            return (Criteria) this;
        }

        public Criteria andNormalmaxNotEqualTo(String value) {
            addCriterion("normalmax <>", value, "normalmax");
            return (Criteria) this;
        }

        public Criteria andNormalmaxGreaterThan(String value) {
            addCriterion("normalmax >", value, "normalmax");
            return (Criteria) this;
        }

        public Criteria andNormalmaxGreaterThanOrEqualTo(String value) {
            addCriterion("normalmax >=", value, "normalmax");
            return (Criteria) this;
        }

        public Criteria andNormalmaxLessThan(String value) {
            addCriterion("normalmax <", value, "normalmax");
            return (Criteria) this;
        }

        public Criteria andNormalmaxLessThanOrEqualTo(String value) {
            addCriterion("normalmax <=", value, "normalmax");
            return (Criteria) this;
        }

        public Criteria andNormalmaxLike(String value) {
            addCriterion("normalmax like", value, "normalmax");
            return (Criteria) this;
        }

        public Criteria andNormalmaxNotLike(String value) {
            addCriterion("normalmax not like", value, "normalmax");
            return (Criteria) this;
        }

        public Criteria andNormalmaxIn(List<String> values) {
            addCriterion("normalmax in", values, "normalmax");
            return (Criteria) this;
        }

        public Criteria andNormalmaxNotIn(List<String> values) {
            addCriterion("normalmax not in", values, "normalmax");
            return (Criteria) this;
        }

        public Criteria andNormalmaxBetween(String value1, String value2) {
            addCriterion("normalmax between", value1, value2, "normalmax");
            return (Criteria) this;
        }

        public Criteria andNormalmaxNotBetween(String value1, String value2) {
            addCriterion("normalmax not between", value1, value2, "normalmax");
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

        public Criteria andNormalminEqualTo(String value) {
            addCriterion("normalmin =", value, "normalmin");
            return (Criteria) this;
        }

        public Criteria andNormalminNotEqualTo(String value) {
            addCriterion("normalmin <>", value, "normalmin");
            return (Criteria) this;
        }

        public Criteria andNormalminGreaterThan(String value) {
            addCriterion("normalmin >", value, "normalmin");
            return (Criteria) this;
        }

        public Criteria andNormalminGreaterThanOrEqualTo(String value) {
            addCriterion("normalmin >=", value, "normalmin");
            return (Criteria) this;
        }

        public Criteria andNormalminLessThan(String value) {
            addCriterion("normalmin <", value, "normalmin");
            return (Criteria) this;
        }

        public Criteria andNormalminLessThanOrEqualTo(String value) {
            addCriterion("normalmin <=", value, "normalmin");
            return (Criteria) this;
        }

        public Criteria andNormalminLike(String value) {
            addCriterion("normalmin like", value, "normalmin");
            return (Criteria) this;
        }

        public Criteria andNormalminNotLike(String value) {
            addCriterion("normalmin not like", value, "normalmin");
            return (Criteria) this;
        }

        public Criteria andNormalminIn(List<String> values) {
            addCriterion("normalmin in", values, "normalmin");
            return (Criteria) this;
        }

        public Criteria andNormalminNotIn(List<String> values) {
            addCriterion("normalmin not in", values, "normalmin");
            return (Criteria) this;
        }

        public Criteria andNormalminBetween(String value1, String value2) {
            addCriterion("normalmin between", value1, value2, "normalmin");
            return (Criteria) this;
        }

        public Criteria andNormalminNotBetween(String value1, String value2) {
            addCriterion("normalmin not between", value1, value2, "normalmin");
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

        public Criteria andUpperwarningEqualTo(String value) {
            addCriterion("upperwarning =", value, "upperwarning");
            return (Criteria) this;
        }

        public Criteria andUpperwarningNotEqualTo(String value) {
            addCriterion("upperwarning <>", value, "upperwarning");
            return (Criteria) this;
        }

        public Criteria andUpperwarningGreaterThan(String value) {
            addCriterion("upperwarning >", value, "upperwarning");
            return (Criteria) this;
        }

        public Criteria andUpperwarningGreaterThanOrEqualTo(String value) {
            addCriterion("upperwarning >=", value, "upperwarning");
            return (Criteria) this;
        }

        public Criteria andUpperwarningLessThan(String value) {
            addCriterion("upperwarning <", value, "upperwarning");
            return (Criteria) this;
        }

        public Criteria andUpperwarningLessThanOrEqualTo(String value) {
            addCriterion("upperwarning <=", value, "upperwarning");
            return (Criteria) this;
        }

        public Criteria andUpperwarningLike(String value) {
            addCriterion("upperwarning like", value, "upperwarning");
            return (Criteria) this;
        }

        public Criteria andUpperwarningNotLike(String value) {
            addCriterion("upperwarning not like", value, "upperwarning");
            return (Criteria) this;
        }

        public Criteria andUpperwarningIn(List<String> values) {
            addCriterion("upperwarning in", values, "upperwarning");
            return (Criteria) this;
        }

        public Criteria andUpperwarningNotIn(List<String> values) {
            addCriterion("upperwarning not in", values, "upperwarning");
            return (Criteria) this;
        }

        public Criteria andUpperwarningBetween(String value1, String value2) {
            addCriterion("upperwarning between", value1, value2, "upperwarning");
            return (Criteria) this;
        }

        public Criteria andUpperwarningNotBetween(String value1, String value2) {
            addCriterion("upperwarning not between", value1, value2, "upperwarning");
            return (Criteria) this;
        }

        public Criteria andVideoIsNull() {
            addCriterion("video is null");
            return (Criteria) this;
        }

        public Criteria andVideoIsNotNull() {
            addCriterion("video is not null");
            return (Criteria) this;
        }

        public Criteria andVideoEqualTo(String value) {
            addCriterion("video =", value, "video");
            return (Criteria) this;
        }

        public Criteria andVideoNotEqualTo(String value) {
            addCriterion("video <>", value, "video");
            return (Criteria) this;
        }

        public Criteria andVideoGreaterThan(String value) {
            addCriterion("video >", value, "video");
            return (Criteria) this;
        }

        public Criteria andVideoGreaterThanOrEqualTo(String value) {
            addCriterion("video >=", value, "video");
            return (Criteria) this;
        }

        public Criteria andVideoLessThan(String value) {
            addCriterion("video <", value, "video");
            return (Criteria) this;
        }

        public Criteria andVideoLessThanOrEqualTo(String value) {
            addCriterion("video <=", value, "video");
            return (Criteria) this;
        }

        public Criteria andVideoLike(String value) {
            addCriterion("video like", value, "video");
            return (Criteria) this;
        }

        public Criteria andVideoNotLike(String value) {
            addCriterion("video not like", value, "video");
            return (Criteria) this;
        }

        public Criteria andVideoIn(List<String> values) {
            addCriterion("video in", values, "video");
            return (Criteria) this;
        }

        public Criteria andVideoNotIn(List<String> values) {
            addCriterion("video not in", values, "video");
            return (Criteria) this;
        }

        public Criteria andVideoBetween(String value1, String value2) {
            addCriterion("video between", value1, value2, "video");
            return (Criteria) this;
        }

        public Criteria andVideoNotBetween(String value1, String value2) {
            addCriterion("video not between", value1, value2, "video");
            return (Criteria) this;
        }

        public Criteria andAudioIsNull() {
            addCriterion("audio is null");
            return (Criteria) this;
        }

        public Criteria andAudioIsNotNull() {
            addCriterion("audio is not null");
            return (Criteria) this;
        }

        public Criteria andAudioEqualTo(String value) {
            addCriterion("audio =", value, "audio");
            return (Criteria) this;
        }

        public Criteria andAudioNotEqualTo(String value) {
            addCriterion("audio <>", value, "audio");
            return (Criteria) this;
        }

        public Criteria andAudioGreaterThan(String value) {
            addCriterion("audio >", value, "audio");
            return (Criteria) this;
        }

        public Criteria andAudioGreaterThanOrEqualTo(String value) {
            addCriterion("audio >=", value, "audio");
            return (Criteria) this;
        }

        public Criteria andAudioLessThan(String value) {
            addCriterion("audio <", value, "audio");
            return (Criteria) this;
        }

        public Criteria andAudioLessThanOrEqualTo(String value) {
            addCriterion("audio <=", value, "audio");
            return (Criteria) this;
        }

        public Criteria andAudioLike(String value) {
            addCriterion("audio like", value, "audio");
            return (Criteria) this;
        }

        public Criteria andAudioNotLike(String value) {
            addCriterion("audio not like", value, "audio");
            return (Criteria) this;
        }

        public Criteria andAudioIn(List<String> values) {
            addCriterion("audio in", values, "audio");
            return (Criteria) this;
        }

        public Criteria andAudioNotIn(List<String> values) {
            addCriterion("audio not in", values, "audio");
            return (Criteria) this;
        }

        public Criteria andAudioBetween(String value1, String value2) {
            addCriterion("audio between", value1, value2, "audio");
            return (Criteria) this;
        }

        public Criteria andAudioNotBetween(String value1, String value2) {
            addCriterion("audio not between", value1, value2, "audio");
            return (Criteria) this;
        }

        public Criteria andImgIsNull() {
            addCriterion("img is null");
            return (Criteria) this;
        }

        public Criteria andImgIsNotNull() {
            addCriterion("img is not null");
            return (Criteria) this;
        }

        public Criteria andImgEqualTo(String value) {
            addCriterion("img =", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotEqualTo(String value) {
            addCriterion("img <>", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThan(String value) {
            addCriterion("img >", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThanOrEqualTo(String value) {
            addCriterion("img >=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThan(String value) {
            addCriterion("img <", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThanOrEqualTo(String value) {
            addCriterion("img <=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLike(String value) {
            addCriterion("img like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotLike(String value) {
            addCriterion("img not like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgIn(List<String> values) {
            addCriterion("img in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotIn(List<String> values) {
            addCriterion("img not in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgBetween(String value1, String value2) {
            addCriterion("img between", value1, value2, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotBetween(String value1, String value2) {
            addCriterion("img not between", value1, value2, "img");
            return (Criteria) this;
        }

        public Criteria andCheckvalueIsNull() {
            addCriterion("checkvalue is null");
            return (Criteria) this;
        }

        public Criteria andCheckvalueIsNotNull() {
            addCriterion("checkvalue is not null");
            return (Criteria) this;
        }

        public Criteria andCheckvalueEqualTo(String value) {
            addCriterion("checkvalue =", value, "checkvalue");
            return (Criteria) this;
        }

        public Criteria andCheckvalueNotEqualTo(String value) {
            addCriterion("checkvalue <>", value, "checkvalue");
            return (Criteria) this;
        }

        public Criteria andCheckvalueGreaterThan(String value) {
            addCriterion("checkvalue >", value, "checkvalue");
            return (Criteria) this;
        }

        public Criteria andCheckvalueGreaterThanOrEqualTo(String value) {
            addCriterion("checkvalue >=", value, "checkvalue");
            return (Criteria) this;
        }

        public Criteria andCheckvalueLessThan(String value) {
            addCriterion("checkvalue <", value, "checkvalue");
            return (Criteria) this;
        }

        public Criteria andCheckvalueLessThanOrEqualTo(String value) {
            addCriterion("checkvalue <=", value, "checkvalue");
            return (Criteria) this;
        }

        public Criteria andCheckvalueLike(String value) {
            addCriterion("checkvalue like", value, "checkvalue");
            return (Criteria) this;
        }

        public Criteria andCheckvalueNotLike(String value) {
            addCriterion("checkvalue not like", value, "checkvalue");
            return (Criteria) this;
        }

        public Criteria andCheckvalueIn(List<String> values) {
            addCriterion("checkvalue in", values, "checkvalue");
            return (Criteria) this;
        }

        public Criteria andCheckvalueNotIn(List<String> values) {
            addCriterion("checkvalue not in", values, "checkvalue");
            return (Criteria) this;
        }

        public Criteria andCheckvalueBetween(String value1, String value2) {
            addCriterion("checkvalue between", value1, value2, "checkvalue");
            return (Criteria) this;
        }

        public Criteria andCheckvalueNotBetween(String value1, String value2) {
            addCriterion("checkvalue not between", value1, value2, "checkvalue");
            return (Criteria) this;
        }

        public Criteria andEnumitemIsNull() {
            addCriterion("enumitem is null");
            return (Criteria) this;
        }

        public Criteria andEnumitemIsNotNull() {
            addCriterion("enumitem is not null");
            return (Criteria) this;
        }

        public Criteria andEnumitemEqualTo(String value) {
            addCriterion("enumitem =", value, "enumitem");
            return (Criteria) this;
        }

        public Criteria andEnumitemNotEqualTo(String value) {
            addCriterion("enumitem <>", value, "enumitem");
            return (Criteria) this;
        }

        public Criteria andEnumitemGreaterThan(String value) {
            addCriterion("enumitem >", value, "enumitem");
            return (Criteria) this;
        }

        public Criteria andEnumitemGreaterThanOrEqualTo(String value) {
            addCriterion("enumitem >=", value, "enumitem");
            return (Criteria) this;
        }

        public Criteria andEnumitemLessThan(String value) {
            addCriterion("enumitem <", value, "enumitem");
            return (Criteria) this;
        }

        public Criteria andEnumitemLessThanOrEqualTo(String value) {
            addCriterion("enumitem <=", value, "enumitem");
            return (Criteria) this;
        }

        public Criteria andEnumitemLike(String value) {
            addCriterion("enumitem like", value, "enumitem");
            return (Criteria) this;
        }

        public Criteria andEnumitemNotLike(String value) {
            addCriterion("enumitem not like", value, "enumitem");
            return (Criteria) this;
        }

        public Criteria andEnumitemIn(List<String> values) {
            addCriterion("enumitem in", values, "enumitem");
            return (Criteria) this;
        }

        public Criteria andEnumitemNotIn(List<String> values) {
            addCriterion("enumitem not in", values, "enumitem");
            return (Criteria) this;
        }

        public Criteria andEnumitemBetween(String value1, String value2) {
            addCriterion("enumitem between", value1, value2, "enumitem");
            return (Criteria) this;
        }

        public Criteria andEnumitemNotBetween(String value1, String value2) {
            addCriterion("enumitem not between", value1, value2, "enumitem");
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