package com.yangqichao.bokuscience.common.net;

/**
 * Created by yangqc on 2017/3/23.
 */

public class RequestBody {


    /**
     * appType : 0
     * cid : string
     * loginName : string
     * password : string
     */

    private int appType;
    private String cid;
    private String loginName;
    private String password;
    /**
     * birthday : 2017-06-12T02:02:16.658Z
     * checkCode : string
     * deptId : 0
     * deptName : string
     * hospitalId : 0
     * hospitalName : string
     * name : string
     * tel : string
     */

    private String birthday;
    private String checkCode;
    private int deptId;
    private String deptName;
    private int hospitalId;
    private String hospitalName;
    private String name;
    private String tel;

    public int getAppType() {
        return appType;
    }

    public void setAppType(int appType) {
        this.appType = appType;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
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
}
