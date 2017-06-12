package com.yangqichao.bokuscience.business.bean;

import java.util.List;

/**
 * Created by yangqc on 2017/6/7.
 */

public class LoginBean {


    /**
     * user : {"id":3,"name":"测试用户","tel":"csapp","password":"123456","hospitalId":3,"hospitalName":"杭州医院1","userType":2,"deptId":16,"deptName":"内科","publishFlag":1,"state":1,"gmtCreate":1496563025000,"cid":null,"appType":null,"birthday":1498665600000,"isSpecial":0,"isBirthday":0}
     * modules : [{"id":11,"code":"yxsp","name":"模块测试10","imgUrl":"2.jpg","contentUrl":"http://www.baidu.com"}]
     */

    private UserBean user;
    private List<ModulesBean> modules;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public List<ModulesBean> getModules() {
        return modules;
    }

    public void setModules(List<ModulesBean> modules) {
        this.modules = modules;
    }

    public static class UserBean {
        /**
         * id : 3
         * name : 测试用户
         * tel : csapp
         * password : 123456
         * hospitalId : 3
         * hospitalName : 杭州医院1
         * userType : 2
         * deptId : 16
         * deptName : 内科
         * publishFlag : 1
         * state : 1
         * gmtCreate : 1496563025000
         * cid : null
         * appType : null
         * birthday : 1498665600000
         * isSpecial : 0
         * isBirthday : 0
         */

        private int id;
        private String name;
        private String tel;
        private String password;
        private int hospitalId;
        private String hospitalName;
        private int userType;
        private int deptId;
        private String deptName;
        private int publishFlag;
        private int state;
        private long gmtCreate;
        private Object cid;
        private Object appType;
        private long birthday;
        private int isSpecial;
        private int isBirthday;

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
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

        public int getPublishFlag() {
            return publishFlag;
        }

        public void setPublishFlag(int publishFlag) {
            this.publishFlag = publishFlag;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public long getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(long gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public Object getCid() {
            return cid;
        }

        public void setCid(Object cid) {
            this.cid = cid;
        }

        public Object getAppType() {
            return appType;
        }

        public void setAppType(Object appType) {
            this.appType = appType;
        }

        public long getBirthday() {
            return birthday;
        }

        public void setBirthday(long birthday) {
            this.birthday = birthday;
        }

        public int getIsSpecial() {
            return isSpecial;
        }

        public void setIsSpecial(int isSpecial) {
            this.isSpecial = isSpecial;
        }

        public int getIsBirthday() {
            return isBirthday;
        }

        public void setIsBirthday(int isBirthday) {
            this.isBirthday = isBirthday;
        }
    }

    public static class ModulesBean {
        /**
         * id : 11
         * code : yxsp
         * name : 模块测试10
         * imgUrl : 2.jpg
         * contentUrl : http://www.baidu.com
         */

        private int id;
        private String code;
        private String name;
        private String imgUrl;
        private String contentUrl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getContentUrl() {
            return contentUrl;
        }

        public void setContentUrl(String contentUrl) {
            this.contentUrl = contentUrl;
        }
    }
}
