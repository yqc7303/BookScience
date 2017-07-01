package com.yangqichao.bokuscience.business.bean;

import java.util.List;

/**
 * Created by yangqc on 2017/7/1.
 */

public class MyMeetingBean {


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
     * records : [{"id":16,"meetingId":6,"meetingTitle":"测试会议2","userId":12,"userName":"大炮","userTel":"15858241307","gmtCreate":"2017-07-01 14:45:21","gmtSign":null,"signflag":0,"meetingState":0,"showSign":"","signflagName":null,"meetingStateName":"未开始"},{"id":8,"meetingId":5,"meetingTitle":"测试会议","userId":12,"userName":"大炮","userTel":"15858241307","gmtCreate":"2017-07-01 14:43:44","gmtSign":null,"signflag":0,"meetingState":3,"showSign":"","signflagName":null,"meetingStateName":"已取消"}]
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
         * id : 16
         * meetingId : 6
         * meetingTitle : 测试会议2
         * userId : 12
         * userName : 大炮
         * userTel : 15858241307
         * gmtCreate : 2017-07-01 14:45:21
         * gmtSign : null
         * signflag : 0
         * meetingState : 0
         * showSign :
         * signflagName : null
         * meetingStateName : 未开始
         */

        private int id;
        private int meetingId;
        private String meetingTitle;
        private int userId;
        private String userName;
        private String userTel;
        private String gmtCreate;
        private Object gmtSign;
        private int signflag;
        private int meetingState;
        private String showSign;
        private Object signflagName;
        private String meetingStateName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMeetingId() {
            return meetingId;
        }

        public void setMeetingId(int meetingId) {
            this.meetingId = meetingId;
        }

        public String getMeetingTitle() {
            return meetingTitle;
        }

        public void setMeetingTitle(String meetingTitle) {
            this.meetingTitle = meetingTitle;
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

        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public Object getGmtSign() {
            return gmtSign;
        }

        public void setGmtSign(Object gmtSign) {
            this.gmtSign = gmtSign;
        }

        public int getSignflag() {
            return signflag;
        }

        public void setSignflag(int signflag) {
            this.signflag = signflag;
        }

        public int getMeetingState() {
            return meetingState;
        }

        public void setMeetingState(int meetingState) {
            this.meetingState = meetingState;
        }

        public String getShowSign() {
            return showSign;
        }

        public void setShowSign(String showSign) {
            this.showSign = showSign;
        }

        public Object getSignflagName() {
            return signflagName;
        }

        public void setSignflagName(Object signflagName) {
            this.signflagName = signflagName;
        }

        public String getMeetingStateName() {
            return meetingStateName;
        }

        public void setMeetingStateName(String meetingStateName) {
            this.meetingStateName = meetingStateName;
        }
    }
}
