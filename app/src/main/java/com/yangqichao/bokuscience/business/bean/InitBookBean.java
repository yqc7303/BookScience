package com.yangqichao.bokuscience.business.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yangqc on 2017/7/11.
 */

public class InitBookBean {


    /**
     * subjects : [{"id":1,"subName":"内科","subType":1,"typeName":null},{"id":5,"subName":"测试学科5","subType":1,"typeName":null},{"id":7,"subName":"测试学科7","subType":1,"typeName":null},{"id":8,"subName":"测试学科8","subType":1,"typeName":null},{"id":10,"subName":"测试学科10","subType":1,"typeName":null},{"id":11,"subName":"测试学科11","subType":1,"typeName":null}]
     * type : {"0":"期刊","1":"书籍"}
     */

    private TypeBean type;
    private List<SubjectsBean> subjects;

    public TypeBean getType() {
        return type;
    }

    public void setType(TypeBean type) {
        this.type = type;
    }

    public List<SubjectsBean> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectsBean> subjects) {
        this.subjects = subjects;
    }

    public static class TypeBean {
        /**
         * 0 : 期刊
         * 1 : 书籍
         */

        @SerializedName("0")
        private String _$0;
        @SerializedName("1")
        private String _$1;

        public String get_$0() {
            return _$0;
        }

        public void set_$0(String _$0) {
            this._$0 = _$0;
        }

        public String get_$1() {
            return _$1;
        }

        public void set_$1(String _$1) {
            this._$1 = _$1;
        }
    }

    public static class SubjectsBean {
        /**
         * id : 1
         * subName : 内科
         * subType : 1
         * typeName : null
         */

        private int id;
        private String subName;
        private int subType;
        private Object typeName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSubName() {
            return subName;
        }

        public void setSubName(String subName) {
            this.subName = subName;
        }

        public int getSubType() {
            return subType;
        }

        public void setSubType(int subType) {
            this.subType = subType;
        }

        public Object getTypeName() {
            return typeName;
        }

        public void setTypeName(Object typeName) {
            this.typeName = typeName;
        }
    }
}
