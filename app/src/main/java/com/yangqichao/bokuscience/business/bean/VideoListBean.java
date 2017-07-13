package com.yangqichao.bokuscience.business.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yangqc on 2017/7/10.
 */

public class VideoListBean {


    /**
     * offset : 0
     * limit : 2147483647
     * total : 2
     * size : 10
     * pages : 1
     * current : 1
     * searchCount : true
     * optimizeCount : true
     * orderByField : null
     * records : [{"id":5,"title":"系统视频4","subjectId":2,"subjectName":"测试学科2","videoUrl":"http://112.124.115.69:8072/videos/file/1.mkv","content":"水电费分无绯闻绯闻","videoType":0,"videoTypeName":"系统视频","hospitalId":0,"hospitalName":"杭州医院1","createrId":1,"createrName":"admin","gmtCreate":1497691252000},{"id":4,"title":"系统视频3","subjectId":2,"subjectName":"测试学科2","videoUrl":"http://112.124.115.69:8072/videos/file/1.mkv","content":"1212121212","videoType":0,"videoTypeName":"系统视频","hospitalId":0,"hospitalName":"杭州医院1","createrId":1,"createrName":"admin","gmtCreate":1497689562000}]
     * asc : true
     * offsetCurrent : 0
     */

    private int offset;
    private int limit;
    private int total;
    private int size;
    private int pages;
    private int current;
    private boolean searchCount;
    private boolean optimizeCount;
    private Object orderByField;
    private boolean asc;
    private int offsetCurrent;
    private List<RecordsBean> records;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public boolean isSearchCount() {
        return searchCount;
    }

    public void setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
    }

    public boolean isOptimizeCount() {
        return optimizeCount;
    }

    public void setOptimizeCount(boolean optimizeCount) {
        this.optimizeCount = optimizeCount;
    }

    public Object getOrderByField() {
        return orderByField;
    }

    public void setOrderByField(Object orderByField) {
        this.orderByField = orderByField;
    }

    public boolean isAsc() {
        return asc;
    }

    public void setAsc(boolean asc) {
        this.asc = asc;
    }

    public int getOffsetCurrent() {
        return offsetCurrent;
    }

    public void setOffsetCurrent(int offsetCurrent) {
        this.offsetCurrent = offsetCurrent;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean implements Serializable{
        /**
         * id : 5
         * title : 系统视频4
         * subjectId : 2
         * subjectName : 测试学科2
         * videoUrl : http://112.124.115.69:8072/videos/file/1.mkv
         * content : 水电费分无绯闻绯闻
         * videoType : 0
         * videoTypeName : 系统视频
         * hospitalId : 0
         * hospitalName : 杭州医院1
         * createrId : 1
         * createrName : admin
         * gmtCreate : 1497691252000
         */

        private int id;
        private String title;
        private int subjectId;
        private String subjectName;
        private String videoUrl;
        private String content;
        private int videoType;
        private String videoTypeName;
        private int hospitalId;
        private String hospitalName;
        private int createrId;
        private String createrName;
        private long gmtCreate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(int subjectId) {
            this.subjectId = subjectId;
        }

        public String getSubjectName() {
            return subjectName;
        }

        public void setSubjectName(String subjectName) {
            this.subjectName = subjectName;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getVideoType() {
            return videoType;
        }

        public void setVideoType(int videoType) {
            this.videoType = videoType;
        }

        public String getVideoTypeName() {
            return videoTypeName;
        }

        public void setVideoTypeName(String videoTypeName) {
            this.videoTypeName = videoTypeName;
        }

        public int getHospitalId() {
            return hospitalId;
        }

        public void setHospitalId(int hospitalId) {
            this.hospitalId = hospitalId;
        }

        public String getHospitalName() {
            return hospitalName;
        }

        public void setHospitalName(String hospitalName) {
            this.hospitalName = hospitalName;
        }

        public int getCreaterId() {
            return createrId;
        }

        public void setCreaterId(int createrId) {
            this.createrId = createrId;
        }

        public String getCreaterName() {
            return createrName;
        }

        public void setCreaterName(String createrName) {
            this.createrName = createrName;
        }

        public long getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(long gmtCreate) {
            this.gmtCreate = gmtCreate;
        }
    }
}
