package com.yangqichao.bokuscience.business.bean;

import java.util.List;

/**
 * Created by yangqc on 2017/7/4.
 */

public class ShareItemBean {


    /**
     * offset : 0
     * limit : 2147483647
     * total : 11
     * size : 100
     * pages : 1
     * current : 1
     * searchCount : true
     * optimizeCount : true
     * orderByField : id
     * records : [{"id":11,"userId":12,"userName":"大炮","userTel":"15858241307","documentUrl":null,"shareUrl":null,"gmtShare":1499221359000,"hospitalId":3,"deptId":18,"deptName":"外科","shareContent":"不传附件","shareImg":null,"shareTitle":null},{"id":10,"userId":12,"userName":"大炮","userTel":"15858241307","documentUrl":null,"shareUrl":null,"gmtShare":1499162561000,"hospitalId":3,"deptId":18,"deptName":"外科","shareContent":"我是测试","shareImg":null,"shareTitle":null},{"id":9,"userId":12,"userName":"大炮","userTel":"15858241307","documentUrl":null,"shareUrl":null,"gmtShare":1499162541000,"hospitalId":3,"deptId":18,"deptName":"外科","shareContent":"你饿饿饿d","shareImg":null,"shareTitle":null},{"id":8,"userId":12,"userName":"大炮","userTel":"15858241307","documentUrl":null,"shareUrl":null,"gmtShare":1499162540000,"hospitalId":3,"deptId":18,"deptName":"外科","shareContent":"你饿饿饿d","shareImg":null,"shareTitle":null},{"id":7,"userId":12,"userName":"大炮","userTel":"15858241307","documentUrl":null,"shareUrl":null,"gmtShare":1499162468000,"hospitalId":3,"deptId":18,"deptName":"外科","shareContent":"百度首页","shareImg":null,"shareTitle":null},{"id":6,"userId":12,"userName":"大炮","userTel":"15858241307","documentUrl":null,"shareUrl":"https://www.baidu.com","gmtShare":1499159248000,"hospitalId":3,"deptId":18,"deptName":"外科","shareContent":"百度首页","shareImg":"","shareTitle":"百度一下，你就知道"},{"id":5,"userId":1,"userName":"admin","userTel":"admin","documentUrl":null,"shareUrl":"https://www.baidu.com","gmtShare":1499136488000,"hospitalId":3,"deptId":0,"deptName":"admin","shareContent":"百度首页","shareImg":"","shareTitle":"百度一下，你就知道"},{"id":4,"userId":1,"userName":"admin","userTel":"admin","documentUrl":null,"shareUrl":"https://www.baidu.com","gmtShare":1499135810000,"hospitalId":3,"deptId":0,"deptName":"admin","shareContent":"百度首页","shareImg":"","shareTitle":"百度一下，你就知道"},{"id":3,"userId":3,"userName":"测试用户","userTel":"18130153901","documentUrl":null,"shareUrl":"http://www.baidu.com","gmtShare":1498731199000,"hospitalId":3,"deptId":16,"deptName":"内科","shareContent":"1133131313","shareImg":"http://news.baidu.com","shareTitle":"百度一下，你就知道"},{"id":2,"userId":3,"userName":"测试用户","userTel":"18130153901","documentUrl":null,"shareUrl":"http://www.baidu.com","gmtShare":1498729785000,"hospitalId":3,"deptId":16,"deptName":"内科","shareContent":"1133131313","shareImg":"http://news.baidu.com","shareTitle":"百度一下，你就知道"},{"id":1,"userId":1,"userName":"admin","userTel":"admin","documentUrl":"http://112.124.115.69:8072/sharemsg/file/1","shareUrl":"1","gmtShare":1498566625000,"hospitalId":3,"deptId":0,"deptName":"admin","shareContent":null,"shareImg":null,"shareTitle":null}]
     * asc : false
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
    private String orderByField;
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

    public String getOrderByField() {
        return orderByField;
    }

    public void setOrderByField(String orderByField) {
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
         * userId : 12
         * userName : 大炮
         * userTel : 15858241307
         * documentUrl : null
         * shareUrl : null
         * gmtShare : 1499221359000
         * hospitalId : 3
         * deptId : 18
         * deptName : 外科
         * shareContent : 不传附件
         * shareImg : null
         * shareTitle : null
         */

        private int id;
        private int userId;
        private String userName;
        private String userTel;
        private String documentUrl;
        private String shareUrl;
        private long gmtShare;
        private int hospitalId;
        private int deptId;
        private String deptName;
        private String shareContent;
        private String shareImg;
        private String shareTitle;

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

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserTel() {
            return userTel;
        }

        public void setUserTel(String userTel) {
            this.userTel = userTel;
        }

        public String getDocumentUrl() {
            return documentUrl;
        }

        public void setDocumentUrl(String documentUrl) {
            this.documentUrl = documentUrl;
        }

        public String getShareUrl() {
            return shareUrl;
        }

        public void setShareUrl(String shareUrl) {
            this.shareUrl = shareUrl;
        }

        public long getGmtShare() {
            return gmtShare;
        }

        public void setGmtShare(long gmtShare) {
            this.gmtShare = gmtShare;
        }

        public int getHospitalId() {
            return hospitalId;
        }

        public void setHospitalId(int hospitalId) {
            this.hospitalId = hospitalId;
        }

        public int getDeptId() {
            return deptId;
        }

        public void setDeptId(int deptId) {
            this.deptId = deptId;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getShareContent() {
            return shareContent;
        }

        public void setShareContent(String shareContent) {
            this.shareContent = shareContent;
        }

        public String getShareImg() {
            return shareImg;
        }

        public void setShareImg(String shareImg) {
            this.shareImg = shareImg;
        }

        public String getShareTitle() {
            return shareTitle;
        }

        public void setShareTitle(String shareTitle) {
            this.shareTitle = shareTitle;
        }
    }
}