package com.tom.model.generator;

import java.util.ArrayList;
import java.util.List;

public class BuildingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BuildingExample() {
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

        public Criteria andBuildidIsNull() {
            addCriterion("buildid is null");
            return (Criteria) this;
        }

        public Criteria andBuildidIsNotNull() {
            addCriterion("buildid is not null");
            return (Criteria) this;
        }

        public Criteria andBuildidEqualTo(String value) {
            addCriterion("buildid =", value, "buildid");
            return (Criteria) this;
        }

        public Criteria andBuildidNotEqualTo(String value) {
            addCriterion("buildid <>", value, "buildid");
            return (Criteria) this;
        }

        public Criteria andBuildidGreaterThan(String value) {
            addCriterion("buildid >", value, "buildid");
            return (Criteria) this;
        }

        public Criteria andBuildidGreaterThanOrEqualTo(String value) {
            addCriterion("buildid >=", value, "buildid");
            return (Criteria) this;
        }

        public Criteria andBuildidLessThan(String value) {
            addCriterion("buildid <", value, "buildid");
            return (Criteria) this;
        }

        public Criteria andBuildidLessThanOrEqualTo(String value) {
            addCriterion("buildid <=", value, "buildid");
            return (Criteria) this;
        }

        public Criteria andBuildidLike(String value) {
            addCriterion("buildid like", value, "buildid");
            return (Criteria) this;
        }

        public Criteria andBuildidNotLike(String value) {
            addCriterion("buildid not like", value, "buildid");
            return (Criteria) this;
        }

        public Criteria andBuildidIn(List<String> values) {
            addCriterion("buildid in", values, "buildid");
            return (Criteria) this;
        }

        public Criteria andBuildidNotIn(List<String> values) {
            addCriterion("buildid not in", values, "buildid");
            return (Criteria) this;
        }

        public Criteria andBuildidBetween(String value1, String value2) {
            addCriterion("buildid between", value1, value2, "buildid");
            return (Criteria) this;
        }

        public Criteria andBuildidNotBetween(String value1, String value2) {
            addCriterion("buildid not between", value1, value2, "buildid");
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

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andFirstpictureIsNull() {
            addCriterion("firstpicture is null");
            return (Criteria) this;
        }

        public Criteria andFirstpictureIsNotNull() {
            addCriterion("firstpicture is not null");
            return (Criteria) this;
        }

        public Criteria andFirstpictureEqualTo(String value) {
            addCriterion("firstpicture =", value, "firstpicture");
            return (Criteria) this;
        }

        public Criteria andFirstpictureNotEqualTo(String value) {
            addCriterion("firstpicture <>", value, "firstpicture");
            return (Criteria) this;
        }

        public Criteria andFirstpictureGreaterThan(String value) {
            addCriterion("firstpicture >", value, "firstpicture");
            return (Criteria) this;
        }

        public Criteria andFirstpictureGreaterThanOrEqualTo(String value) {
            addCriterion("firstpicture >=", value, "firstpicture");
            return (Criteria) this;
        }

        public Criteria andFirstpictureLessThan(String value) {
            addCriterion("firstpicture <", value, "firstpicture");
            return (Criteria) this;
        }

        public Criteria andFirstpictureLessThanOrEqualTo(String value) {
            addCriterion("firstpicture <=", value, "firstpicture");
            return (Criteria) this;
        }

        public Criteria andFirstpictureLike(String value) {
            addCriterion("firstpicture like", value, "firstpicture");
            return (Criteria) this;
        }

        public Criteria andFirstpictureNotLike(String value) {
            addCriterion("firstpicture not like", value, "firstpicture");
            return (Criteria) this;
        }

        public Criteria andFirstpictureIn(List<String> values) {
            addCriterion("firstpicture in", values, "firstpicture");
            return (Criteria) this;
        }

        public Criteria andFirstpictureNotIn(List<String> values) {
            addCriterion("firstpicture not in", values, "firstpicture");
            return (Criteria) this;
        }

        public Criteria andFirstpictureBetween(String value1, String value2) {
            addCriterion("firstpicture between", value1, value2, "firstpicture");
            return (Criteria) this;
        }

        public Criteria andFirstpictureNotBetween(String value1, String value2) {
            addCriterion("firstpicture not between", value1, value2, "firstpicture");
            return (Criteria) this;
        }

        public Criteria andAllstarsIsNull() {
            addCriterion("allstars is null");
            return (Criteria) this;
        }

        public Criteria andAllstarsIsNotNull() {
            addCriterion("allstars is not null");
            return (Criteria) this;
        }

        public Criteria andAllstarsEqualTo(Integer value) {
            addCriterion("allstars =", value, "allstars");
            return (Criteria) this;
        }

        public Criteria andAllstarsNotEqualTo(Integer value) {
            addCriterion("allstars <>", value, "allstars");
            return (Criteria) this;
        }

        public Criteria andAllstarsGreaterThan(Integer value) {
            addCriterion("allstars >", value, "allstars");
            return (Criteria) this;
        }

        public Criteria andAllstarsGreaterThanOrEqualTo(Integer value) {
            addCriterion("allstars >=", value, "allstars");
            return (Criteria) this;
        }

        public Criteria andAllstarsLessThan(Integer value) {
            addCriterion("allstars <", value, "allstars");
            return (Criteria) this;
        }

        public Criteria andAllstarsLessThanOrEqualTo(Integer value) {
            addCriterion("allstars <=", value, "allstars");
            return (Criteria) this;
        }

        public Criteria andAllstarsIn(List<Integer> values) {
            addCriterion("allstars in", values, "allstars");
            return (Criteria) this;
        }

        public Criteria andAllstarsNotIn(List<Integer> values) {
            addCriterion("allstars not in", values, "allstars");
            return (Criteria) this;
        }

        public Criteria andAllstarsBetween(Integer value1, Integer value2) {
            addCriterion("allstars between", value1, value2, "allstars");
            return (Criteria) this;
        }

        public Criteria andAllstarsNotBetween(Integer value1, Integer value2) {
            addCriterion("allstars not between", value1, value2, "allstars");
            return (Criteria) this;
        }

        public Criteria andCommentcountIsNull() {
            addCriterion("commentcount is null");
            return (Criteria) this;
        }

        public Criteria andCommentcountIsNotNull() {
            addCriterion("commentcount is not null");
            return (Criteria) this;
        }

        public Criteria andCommentcountEqualTo(Integer value) {
            addCriterion("commentcount =", value, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountNotEqualTo(Integer value) {
            addCriterion("commentcount <>", value, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountGreaterThan(Integer value) {
            addCriterion("commentcount >", value, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountGreaterThanOrEqualTo(Integer value) {
            addCriterion("commentcount >=", value, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountLessThan(Integer value) {
            addCriterion("commentcount <", value, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountLessThanOrEqualTo(Integer value) {
            addCriterion("commentcount <=", value, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountIn(List<Integer> values) {
            addCriterion("commentcount in", values, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountNotIn(List<Integer> values) {
            addCriterion("commentcount not in", values, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountBetween(Integer value1, Integer value2) {
            addCriterion("commentcount between", value1, value2, "commentcount");
            return (Criteria) this;
        }

        public Criteria andCommentcountNotBetween(Integer value1, Integer value2) {
            addCriterion("commentcount not between", value1, value2, "commentcount");
            return (Criteria) this;
        }

        public Criteria andAveragestarsIsNull() {
            addCriterion("averagestars is null");
            return (Criteria) this;
        }

        public Criteria andAveragestarsIsNotNull() {
            addCriterion("averagestars is not null");
            return (Criteria) this;
        }

        public Criteria andAveragestarsEqualTo(Float value) {
            addCriterion("averagestars =", value, "averagestars");
            return (Criteria) this;
        }

        public Criteria andAveragestarsNotEqualTo(Float value) {
            addCriterion("averagestars <>", value, "averagestars");
            return (Criteria) this;
        }

        public Criteria andAveragestarsGreaterThan(Float value) {
            addCriterion("averagestars >", value, "averagestars");
            return (Criteria) this;
        }

        public Criteria andAveragestarsGreaterThanOrEqualTo(Float value) {
            addCriterion("averagestars >=", value, "averagestars");
            return (Criteria) this;
        }

        public Criteria andAveragestarsLessThan(Float value) {
            addCriterion("averagestars <", value, "averagestars");
            return (Criteria) this;
        }

        public Criteria andAveragestarsLessThanOrEqualTo(Float value) {
            addCriterion("averagestars <=", value, "averagestars");
            return (Criteria) this;
        }

        public Criteria andAveragestarsIn(List<Float> values) {
            addCriterion("averagestars in", values, "averagestars");
            return (Criteria) this;
        }

        public Criteria andAveragestarsNotIn(List<Float> values) {
            addCriterion("averagestars not in", values, "averagestars");
            return (Criteria) this;
        }

        public Criteria andAveragestarsBetween(Float value1, Float value2) {
            addCriterion("averagestars between", value1, value2, "averagestars");
            return (Criteria) this;
        }

        public Criteria andAveragestarsNotBetween(Float value1, Float value2) {
            addCriterion("averagestars not between", value1, value2, "averagestars");
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