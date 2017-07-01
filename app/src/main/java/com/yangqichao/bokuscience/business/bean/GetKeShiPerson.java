package com.yangqichao.bokuscience.business.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yangqc on 2017/6/30.
 */

public class GetKeShiPerson implements Serializable{


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
     * records : [{"id":12,"name":"大炮","tel":"15858241307","password":"123456","hospitalId":3,"hospitalName":"杭州医院1","userType":2,"userTypeName":null,"deptId":18,"deptName":"外科","publishFlag":0,"publishFlagName":null,"state":1,"stateName":"有效","gmtCreate":1498312409000,"showGmt":"2017-06-24 21:53:29","cid":null,"appType":0,"birthday":-28800000,"showBirthday":null,"isSpecial":0,"isSpecialName":null,"isBirthday":null,"isPwd":null,"isPwdName":null,"orgId":null}]
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

    public static class RecordsBean implements Serializable {
        /**
         * id : 12
         * name : 大炮
         * tel : 15858241307
         * password : 123456
         * hospitalId : 3
         * hospitalName : 杭州医院1
         * userType : 2
         * userTypeName : null
         * deptId : 18
         * deptName : 外科
         * publishFlag : 0
         * publishFlagName : null
         * state : 1
         * stateName : 有效
         * gmtCreate : 1498312409000
         * showGmt : 2017-06-24 21:53:29
         * cid : null
         * appType : 0
         * birthday : -28800000
         * showBirthday : null
         * isSpecial : 0
         * isSpecialName : null
         * isBirthday : null
         * isPwd : null
         * isPwdName : null
         * orgId : null
         */

        private String id;
        private String name;
        private String tel;
        private String password;
        private String hospitalId;
        private String hospitalName;
        private String userType;
        private Object userTypeName;
        private String deptId;
        private String deptName;
        private String publishFlag;
        private Object publishFlagName;
        private String state;
        private String stateName;
        private String gmtCreate;
        private String showGmt;
        private Object cid;
        private String appType;
        private String birthday;
        private Object showBirthday;
        private String isSpecial;
        private Object isSpecialName;
        private Object isBirthday;
        private Object isPwd;
        private Object isPwdName;
        private Object orgId;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getHospitalId() {
            return hospitalId;
        }

        public void setHospitalId(String hospitalId) {
            this.hospitalId = hospitalId;
        }

        public String getHospitalName() {
            return hospitalName;
        }

        public void setHospitalName(String hospitalName) {
            this.hospitalName = hospitalName;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public Object getUserTypeName() {
            return userTypeName;
        }

        public void setUserTypeName(Object userTypeName) {
            this.userTypeName = userTypeName;
        }

        public String getDeptId() {
            return deptId;
        }

        public void setDeptId(String deptId) {
            this.deptId = deptId;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getPublishFlag() {
            return publishFlag;
        }

        public void setPublishFlag(String publishFlag) {
            this.publishFlag = publishFlag;
        }

        public Object getPublishFlagName() {
            return publishFlagName;
        }

        public void setPublishFlagName(Object publishFlagName) {
            this.publishFlagName = publishFlagName;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getStateName() {
            return stateName;
        }

        public void setStateName(String stateName) {
            this.stateName = stateName;
        }

        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public String getShowGmt() {
            return showGmt;
        }

        public void setShowGmt(String showGmt) {
            this.showGmt = showGmt;
        }

        public Object getCid() {
            return cid;
        }

        public void setCid(Object cid) {
            this.cid = cid;
        }

        public String getAppType() {
            return appType;
        }

        public void setAppType(String appType) {
            this.appType = appType;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public Object getShowBirthday() {
            return showBirthday;
        }

        public void setShowBirthday(Object showBirthday) {
            this.showBirthday = showBirthday;
        }

        public String getIsSpecial() {
            return isSpecial;
        }

        public void setIsSpecial(String isSpecial) {
            this.isSpecial = isSpecial;
        }

        public Object getIsSpecialName() {
            return isSpecialName;
        }

        public void setIsSpecialName(Object isSpecialName) {
            this.isSpecialName = isSpecialName;
        }

        public Object getIsBirthday() {
            return isBirthday;
        }

        public void setIsBirthday(Object isBirthday) {
            this.isBirthday = isBirthday;
        }

        public Object getIsPwd() {
            return isPwd;
        }

        public void setIsPwd(Object isPwd) {
            this.isPwd = isPwd;
        }

        public Object getIsPwdName() {
            return isPwdName;
        }

        public void setIsPwdName(Object isPwdName) {
            this.isPwdName = isPwdName;
        }

        public Object getOrgId() {
            return orgId;
        }

        public void setOrgId(Object orgId) {
            this.orgId = orgId;
        }
    }
}
