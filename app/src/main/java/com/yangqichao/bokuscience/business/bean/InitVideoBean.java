package com.yangqichao.bokuscience.business.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yangqc on 2017/7/10.
 */

public class InitVideoBean {


    private List<VideoTypesBean> videoTypes;
    private List<SubjectListBean> subjectList;

    public List<VideoTypesBean> getVideoTypes() {
        return videoTypes;
    }

    public void setVideoTypes(List<VideoTypesBean> videoTypes) {
        this.videoTypes = videoTypes;
    }

    public List<SubjectListBean> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<SubjectListBean> subjectList) {
        this.subjectList = subjectList;
    }

    public static class VideoTypesBean implements Serializable{
        /**
         * id : null
         * subName : null
         * subType : 0
         * typeName : 系统视频
         */

        private Object id;
        private Object subName;
        private int subType;
        private String typeName;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public Object getSubName() {
            return subName;
        }

        public void setSubName(Object subName) {
            this.subName = subName;
        }

        public int getSubType() {
            return subType;
        }

        public void setSubType(int subType) {
            this.subType = subType;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }
    }

    public static class SubjectListBean implements Serializable{
        /**
         * id : 3
         * subName : 测试学科3
         * subType : 0
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
