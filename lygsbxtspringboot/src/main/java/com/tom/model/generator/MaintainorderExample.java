package com.tom.model.generator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MaintainorderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MaintainorderExample() {
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

        public Criteria andOrderidIsNull() {
            addCriterion("orderid is null");
            return (Criteria) this;
        }

        public Criteria andOrderidIsNotNull() {
            addCriterion("orderid is not null");
            return (Criteria) this;
        }

        public Criteria andOrderidEqualTo(String value) {
            addCriterion("orderid =", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotEqualTo(String value) {
            addCriterion("orderid <>", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThan(String value) {
            addCriterion("orderid >", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThanOrEqualTo(String value) {
            addCriterion("orderid >=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThan(String value) {
            addCriterion("orderid <", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThanOrEqualTo(String value) {
            addCriterion("orderid <=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLike(String value) {
            addCriterion("orderid like", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotLike(String value) {
            addCriterion("orderid not like", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidIn(List<String> values) {
            addCriterion("orderid in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotIn(List<String> values) {
            addCriterion("orderid not in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidBetween(String value1, String value2) {
            addCriterion("orderid between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotBetween(String value1, String value2) {
            addCriterion("orderid not between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andWxuseridIsNull() {
            addCriterion("wxuserid is null");
            return (Criteria) this;
        }

        public Criteria andWxuseridIsNotNull() {
            addCriterion("wxuserid is not null");
            return (Criteria) this;
        }

        public Criteria andWxuseridEqualTo(String value) {
            addCriterion("wxuserid =", value, "wxuserid");
            return (Criteria) this;
        }

        public Criteria andWxuseridNotEqualTo(String value) {
            addCriterion("wxuserid <>", value, "wxuserid");
            return (Criteria) this;
        }

        public Criteria andWxuseridGreaterThan(String value) {
            addCriterion("wxuserid >", value, "wxuserid");
            return (Criteria) this;
        }

        public Criteria andWxuseridGreaterThanOrEqualTo(String value) {
            addCriterion("wxuserid >=", value, "wxuserid");
            return (Criteria) this;
        }

        public Criteria andWxuseridLessThan(String value) {
            addCriterion("wxuserid <", value, "wxuserid");
            return (Criteria) this;
        }

        public Criteria andWxuseridLessThanOrEqualTo(String value) {
            addCriterion("wxuserid <=", value, "wxuserid");
            return (Criteria) this;
        }

        public Criteria andWxuseridLike(String value) {
            addCriterion("wxuserid like", value, "wxuserid");
            return (Criteria) this;
        }

        public Criteria andWxuseridNotLike(String value) {
            addCriterion("wxuserid not like", value, "wxuserid");
            return (Criteria) this;
        }

        public Criteria andWxuseridIn(List<String> values) {
            addCriterion("wxuserid in", values, "wxuserid");
            return (Criteria) this;
        }

        public Criteria andWxuseridNotIn(List<String> values) {
            addCriterion("wxuserid not in", values, "wxuserid");
            return (Criteria) this;
        }

        public Criteria andWxuseridBetween(String value1, String value2) {
            addCriterion("wxuserid between", value1, value2, "wxuserid");
            return (Criteria) this;
        }

        public Criteria andWxuseridNotBetween(String value1, String value2) {
            addCriterion("wxuserid not between", value1, value2, "wxuserid");
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

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andOfficertimeIsNull() {
            addCriterion("officertime is null");
            return (Criteria) this;
        }

        public Criteria andOfficertimeIsNotNull() {
            addCriterion("officertime is not null");
            return (Criteria) this;
        }

        public Criteria andOfficertimeEqualTo(Date value) {
            addCriterion("officertime =", value, "officertime");
            return (Criteria) this;
        }

        public Criteria andOfficertimeNotEqualTo(Date value) {
            addCriterion("officertime <>", value, "officertime");
            return (Criteria) this;
        }

        public Criteria andOfficertimeGreaterThan(Date value) {
            addCriterion("officertime >", value, "officertime");
            return (Criteria) this;
        }

        public Criteria andOfficertimeGreaterThanOrEqualTo(Date value) {
            addCriterion("officertime >=", value, "officertime");
            return (Criteria) this;
        }

        public Criteria andOfficertimeLessThan(Date value) {
            addCriterion("officertime <", value, "officertime");
            return (Criteria) this;
        }

        public Criteria andOfficertimeLessThanOrEqualTo(Date value) {
            addCriterion("officertime <=", value, "officertime");
            return (Criteria) this;
        }

        public Criteria andOfficertimeIn(List<Date> values) {
            addCriterion("officertime in", values, "officertime");
            return (Criteria) this;
        }

        public Criteria andOfficertimeNotIn(List<Date> values) {
            addCriterion("officertime not in", values, "officertime");
            return (Criteria) this;
        }

        public Criteria andOfficertimeBetween(Date value1, Date value2) {
            addCriterion("officertime between", value1, value2, "officertime");
            return (Criteria) this;
        }

        public Criteria andOfficertimeNotBetween(Date value1, Date value2) {
            addCriterion("officertime not between", value1, value2, "officertime");
            return (Criteria) this;
        }

        public Criteria andMaintaintimeIsNull() {
            addCriterion("maintaintime is null");
            return (Criteria) this;
        }

        public Criteria andMaintaintimeIsNotNull() {
            addCriterion("maintaintime is not null");
            return (Criteria) this;
        }

        public Criteria andMaintaintimeEqualTo(Date value) {
            addCriterion("maintaintime =", value, "maintaintime");
            return (Criteria) this;
        }

        public Criteria andMaintaintimeNotEqualTo(Date value) {
            addCriterion("maintaintime <>", value, "maintaintime");
            return (Criteria) this;
        }

        public Criteria andMaintaintimeGreaterThan(Date value) {
            addCriterion("maintaintime >", value, "maintaintime");
            return (Criteria) this;
        }

        public Criteria andMaintaintimeGreaterThanOrEqualTo(Date value) {
            addCriterion("maintaintime >=", value, "maintaintime");
            return (Criteria) this;
        }

        public Criteria andMaintaintimeLessThan(Date value) {
            addCriterion("maintaintime <", value, "maintaintime");
            return (Criteria) this;
        }

        public Criteria andMaintaintimeLessThanOrEqualTo(Date value) {
            addCriterion("maintaintime <=", value, "maintaintime");
            return (Criteria) this;
        }

        public Criteria andMaintaintimeIn(List<Date> values) {
            addCriterion("maintaintime in", values, "maintaintime");
            return (Criteria) this;
        }

        public Criteria andMaintaintimeNotIn(List<Date> values) {
            addCriterion("maintaintime not in", values, "maintaintime");
            return (Criteria) this;
        }

        public Criteria andMaintaintimeBetween(Date value1, Date value2) {
            addCriterion("maintaintime between", value1, value2, "maintaintime");
            return (Criteria) this;
        }

        public Criteria andMaintaintimeNotBetween(Date value1, Date value2) {
            addCriterion("maintaintime not between", value1, value2, "maintaintime");
            return (Criteria) this;
        }

        public Criteria andCommenttimeIsNull() {
            addCriterion("commenttime is null");
            return (Criteria) this;
        }

        public Criteria andCommenttimeIsNotNull() {
            addCriterion("commenttime is not null");
            return (Criteria) this;
        }

        public Criteria andCommenttimeEqualTo(Date value) {
            addCriterion("commenttime =", value, "commenttime");
            return (Criteria) this;
        }

        public Criteria andCommenttimeNotEqualTo(Date value) {
            addCriterion("commenttime <>", value, "commenttime");
            return (Criteria) this;
        }

        public Criteria andCommenttimeGreaterThan(Date value) {
            addCriterion("commenttime >", value, "commenttime");
            return (Criteria) this;
        }

        public Criteria andCommenttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("commenttime >=", value, "commenttime");
            return (Criteria) this;
        }

        public Criteria andCommenttimeLessThan(Date value) {
            addCriterion("commenttime <", value, "commenttime");
            return (Criteria) this;
        }

        public Criteria andCommenttimeLessThanOrEqualTo(Date value) {
            addCriterion("commenttime <=", value, "commenttime");
            return (Criteria) this;
        }

        public Criteria andCommenttimeIn(List<Date> values) {
            addCriterion("commenttime in", values, "commenttime");
            return (Criteria) this;
        }

        public Criteria andCommenttimeNotIn(List<Date> values) {
            addCriterion("commenttime not in", values, "commenttime");
            return (Criteria) this;
        }

        public Criteria andCommenttimeBetween(Date value1, Date value2) {
            addCriterion("commenttime between", value1, value2, "commenttime");
            return (Criteria) this;
        }

        public Criteria andCommenttimeNotBetween(Date value1, Date value2) {
            addCriterion("commenttime not between", value1, value2, "commenttime");
            return (Criteria) this;
        }

        public Criteria andIsdeleteIsNull() {
            addCriterion("isdelete is null");
            return (Criteria) this;
        }

        public Criteria andIsdeleteIsNotNull() {
            addCriterion("isdelete is not null");
            return (Criteria) this;
        }

        public Criteria andIsdeleteEqualTo(Integer value) {
            addCriterion("isdelete =", value, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteNotEqualTo(Integer value) {
            addCriterion("isdelete <>", value, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteGreaterThan(Integer value) {
            addCriterion("isdelete >", value, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteGreaterThanOrEqualTo(Integer value) {
            addCriterion("isdelete >=", value, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteLessThan(Integer value) {
            addCriterion("isdelete <", value, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteLessThanOrEqualTo(Integer value) {
            addCriterion("isdelete <=", value, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteIn(List<Integer> values) {
            addCriterion("isdelete in", values, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteNotIn(List<Integer> values) {
            addCriterion("isdelete not in", values, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteBetween(Integer value1, Integer value2) {
            addCriterion("isdelete between", value1, value2, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteNotBetween(Integer value1, Integer value2) {
            addCriterion("isdelete not between", value1, value2, "isdelete");
            return (Criteria) this;
        }

        public Criteria andMaintainidIsNull() {
            addCriterion("maintainid is null");
            return (Criteria) this;
        }

        public Criteria andMaintainidIsNotNull() {
            addCriterion("maintainid is not null");
            return (Criteria) this;
        }

        public Criteria andMaintainidEqualTo(String value) {
            addCriterion("maintainid =", value, "maintainid");
            return (Criteria) this;
        }

        public Criteria andMaintainidNotEqualTo(String value) {
            addCriterion("maintainid <>", value, "maintainid");
            return (Criteria) this;
        }

        public Criteria andMaintainidGreaterThan(String value) {
            addCriterion("maintainid >", value, "maintainid");
            return (Criteria) this;
        }

        public Criteria andMaintainidGreaterThanOrEqualTo(String value) {
            addCriterion("maintainid >=", value, "maintainid");
            return (Criteria) this;
        }

        public Criteria andMaintainidLessThan(String value) {
            addCriterion("maintainid <", value, "maintainid");
            return (Criteria) this;
        }

        public Criteria andMaintainidLessThanOrEqualTo(String value) {
            addCriterion("maintainid <=", value, "maintainid");
            return (Criteria) this;
        }

        public Criteria andMaintainidLike(String value) {
            addCriterion("maintainid like", value, "maintainid");
            return (Criteria) this;
        }

        public Criteria andMaintainidNotLike(String value) {
            addCriterion("maintainid not like", value, "maintainid");
            return (Criteria) this;
        }

        public Criteria andMaintainidIn(List<String> values) {
            addCriterion("maintainid in", values, "maintainid");
            return (Criteria) this;
        }

        public Criteria andMaintainidNotIn(List<String> values) {
            addCriterion("maintainid not in", values, "maintainid");
            return (Criteria) this;
        }

        public Criteria andMaintainidBetween(String value1, String value2) {
            addCriterion("maintainid between", value1, value2, "maintainid");
            return (Criteria) this;
        }

        public Criteria andMaintainidNotBetween(String value1, String value2) {
            addCriterion("maintainid not between", value1, value2, "maintainid");
            return (Criteria) this;
        }

        public Criteria andOfficeridIsNull() {
            addCriterion("officerid is null");
            return (Criteria) this;
        }

        public Criteria andOfficeridIsNotNull() {
            addCriterion("officerid is not null");
            return (Criteria) this;
        }

        public Criteria andOfficeridEqualTo(String value) {
            addCriterion("officerid =", value, "officerid");
            return (Criteria) this;
        }

        public Criteria andOfficeridNotEqualTo(String value) {
            addCriterion("officerid <>", value, "officerid");
            return (Criteria) this;
        }

        public Criteria andOfficeridGreaterThan(String value) {
            addCriterion("officerid >", value, "officerid");
            return (Criteria) this;
        }

        public Criteria andOfficeridGreaterThanOrEqualTo(String value) {
            addCriterion("officerid >=", value, "officerid");
            return (Criteria) this;
        }

        public Criteria andOfficeridLessThan(String value) {
            addCriterion("officerid <", value, "officerid");
            return (Criteria) this;
        }

        public Criteria andOfficeridLessThanOrEqualTo(String value) {
            addCriterion("officerid <=", value, "officerid");
            return (Criteria) this;
        }

        public Criteria andOfficeridLike(String value) {
            addCriterion("officerid like", value, "officerid");
            return (Criteria) this;
        }

        public Criteria andOfficeridNotLike(String value) {
            addCriterion("officerid not like", value, "officerid");
            return (Criteria) this;
        }

        public Criteria andOfficeridIn(List<String> values) {
            addCriterion("officerid in", values, "officerid");
            return (Criteria) this;
        }

        public Criteria andOfficeridNotIn(List<String> values) {
            addCriterion("officerid not in", values, "officerid");
            return (Criteria) this;
        }

        public Criteria andOfficeridBetween(String value1, String value2) {
            addCriterion("officerid between", value1, value2, "officerid");
            return (Criteria) this;
        }

        public Criteria andOfficeridNotBetween(String value1, String value2) {
            addCriterion("officerid not between", value1, value2, "officerid");
            return (Criteria) this;
        }

        public Criteria andChargeIsNull() {
            addCriterion("charge is null");
            return (Criteria) this;
        }

        public Criteria andChargeIsNotNull() {
            addCriterion("charge is not null");
            return (Criteria) this;
        }

        public Criteria andChargeEqualTo(Float value) {
            addCriterion("charge =", value, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeNotEqualTo(Float value) {
            addCriterion("charge <>", value, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeGreaterThan(Float value) {
            addCriterion("charge >", value, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeGreaterThanOrEqualTo(Float value) {
            addCriterion("charge >=", value, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeLessThan(Float value) {
            addCriterion("charge <", value, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeLessThanOrEqualTo(Float value) {
            addCriterion("charge <=", value, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeIn(List<Float> values) {
            addCriterion("charge in", values, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeNotIn(List<Float> values) {
            addCriterion("charge not in", values, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeBetween(Float value1, Float value2) {
            addCriterion("charge between", value1, value2, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeNotBetween(Float value1, Float value2) {
            addCriterion("charge not between", value1, value2, "charge");
            return (Criteria) this;
        }
        
        public Criteria andTitleOrContentLike(String value) {
            addCriterion("(title like \""+value+"\" or content like \""+value+"\")");
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