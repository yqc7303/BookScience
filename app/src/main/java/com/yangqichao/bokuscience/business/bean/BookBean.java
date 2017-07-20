package com.yangqichao.bokuscience.business.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yangqc on 2017/7/11.
 */

public class BookBean {


    /**
     * offset : 0
     * limit : 2147483647
     * total : 2
     * size : 100
     * pages : 1
     * current : 1
     * searchCount : true
     * optimizeCount : true
     * orderByField : null
     * records : [{"id":1,"title":"测试期刊122","subjectId":1,"subjectName":"内科","imgUrl":"http://112.124.115.69:8072/book/images/12d281ce66ba0b7c1a21ae5d8563f6aa.jpg","fileUrl":"2.jpg","type":1,"typeName":"书籍"},{"id":2,"title":"测试书籍","subjectId":8,"subjectName":"测试学科8","imgUrl":"http://112.124.115.69:8072/book/images/3.jpg","fileUrl":"2.jpg","type":1,"typeName":"书籍"}]
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
         * id : 1
         * title : 测试期刊122
         * subjectId : 1
         * subjectName : 内科
         * imgUrl : http://112.124.115.69:8072/book/images/12d281ce66ba0b7c1a21ae5d8563f6aa.jpg
         * fileUrl : 2.jpg
         * type : 1
         * typeName : 书籍
         */

        private int id;
        private String title;
        private int subjectId;
        private String subjectName;
        private String imgUrl;
        private String fileUrl;
        private int type;
        private String typeName;
        private int isAdd;
        private boolean isDone;

        public boolean isDone() {
            return isDone;
        }

        public void setDone(boolean done) {
            isDone = done;
        }

        public int getIsAdd() {
            return isAdd;
        }

        public void setIsAdd(int isAdd) {
            this.isAdd = isAdd;
        }

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

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }
    }
}
