package com.yangqichao.bokuscience.business.bean;

import java.util.List;

/**
 * Created by yangqc on 2017/7/19.
 */

public class MessageBean {


    /**
     * offset : 0
     * limit : 2147483647
     * total : 1
     * size : 10
     * pages : 1
     * current : 1
     * searchCount : true
     * optimizeCount : true
     * orderByField : null
     * records : [{"id":11,"title":"本院开通人卫数据库","imgUrl":"1371485183456.jpg","content":"本院开通人卫数据库的使用，详情请联系图书管理员","type":0,"hospitalId":0,"typeName":"系统动态","hospitalName":null,"gmtCreate":null}]
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

    public static class RecordsBean {
        /**
         * id : 11
         * title : 本院开通人卫数据库
         * imgUrl : 1371485183456.jpg
         * content : 本院开通人卫数据库的使用，详情请联系图书管理员
         * type : 0
         * hospitalId : 0
         * typeName : 系统动态
         * hospitalName : null
         * gmtCreate : null
         */

        private int id;
        private String title;
        private String imgUrl;
        private String content;
        private int type;
        private int hospitalId;
        private String typeName;
        private String hospitalName;
        private String gmtCreate;

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

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getHospitalId() {
            return hospitalId;
        }

        public void setHospitalId(int hospitalId) {
            this.hospitalId = hospitalId;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getHospitalName() {
            return hospitalName;
        }

        public void setHospitalName(String hospitalName) {
            this.hospitalName = hospitalName;
        }

        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
        }
    }
}
