package com.yangqichao.bokuscience.business.bean;

import java.util.List;

/**
 * Created by yangqc on 2017/7/12.
 */

public class MyBookBean {


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
     * records : [{"id":5,"userId":12,"bookDTO":{"id":5,"title":"epub","subjectId":1,"subjectName":"内科","imgUrl":"1080_1101期刊书籍.png","fileUrl":"http://www.jianshu.com/","type":null}},{"id":6,"userId":12,"bookDTO":{"id":6,"title":"epub","subjectId":1,"subjectName":"内科","imgUrl":"1080_1101期刊书籍.png","fileUrl":"http://www.jianshu.com/","type":null}}]
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
         * id : 5
         * userId : 12
         * bookDTO : {"id":5,"title":"epub","subjectId":1,"subjectName":"内科","imgUrl":"1080_1101期刊书籍.png","fileUrl":"http://www.jianshu.com/","type":null}
         */

        private int id;
        private int userId;
        private BookDTOBean bookDTO;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public BookDTOBean getBookDTO() {
            return bookDTO;
        }

        public void setBookDTO(BookDTOBean bookDTO) {
            this.bookDTO = bookDTO;
        }

        public static class BookDTOBean {
            /**
             * id : 5
             * title : epub
             * subjectId : 1
             * subjectName : 内科
             * imgUrl : 1080_1101期刊书籍.png
             * fileUrl : http://www.jianshu.com/
             * type : null
             */

            private int id;
            private String title;
            private int subjectId;
            private String subjectName;
            private String imgUrl;
            private String fileUrl;
            private Object type;

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

            public Object getType() {
                return type;
            }

            public void setType(Object type) {
                this.type = type;
            }
        }
    }
}
