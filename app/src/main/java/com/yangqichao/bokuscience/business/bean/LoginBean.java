package com.yangqichao.bokuscience.business.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yangqc on 2017/6/7.
 */

public class LoginBean implements Serializable{

    /**
     * id : 12
     * name : 大炮
     * tel : 15858241307
     * password : 123456
     * hospitalId : 3
     * hospitalName : 杭州医院1
     * userType : 2
     * deptId : 16
     * deptName : 内科
     * publishFlag : 0
     * state : 1
     * gmtCreate : 1498312409000
     * birthday : -28800000
     * isBirthday : 0
     * isSpecial : 0
     * isPwd : 1
     * moduleDTOS : [{"id":11,"code":"yxsp","name":"医学视频","imgUrl":"2.jpg","contentUrl":""}]
     */

    private String id;
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
    private int birthday;
    private int isBirthday;
    private int isSpecial;
    private int isPwd;
    private List<ModuleDTOSBean> moduleDTOS;
    private String hospitalLogo;

    public String getHospitalLogo() {
        return hospitalLogo;
    }

    public void setHospitalLogo(String hospitalLogo) {
        this.hospitalLogo = hospitalLogo;
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

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public int getIsBirthday() {
        return isBirthday;
    }

    public void setIsBirthday(int isBirthday) {
        this.isBirthday = isBirthday;
    }

    public int getIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(int isSpecial) {
        this.isSpecial = isSpecial;
    }

    public int getIsPwd() {
        return isPwd;
    }

    public void setIsPwd(int isPwd) {
        this.isPwd = isPwd;
    }

    public List<ModuleDTOSBean> getModuleDTOS() {
        return moduleDTOS;
    }

    public void setModuleDTOS(List<ModuleDTOSBean> moduleDTOS) {
        this.moduleDTOS = moduleDTOS;
    }

    public static class ModuleDTOSBean implements Serializable{
        /**
         * id : 11
         * code : yxsp
         * name : 医学视频
         * imgUrl : 2.jpg
         * contentUrl :
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
