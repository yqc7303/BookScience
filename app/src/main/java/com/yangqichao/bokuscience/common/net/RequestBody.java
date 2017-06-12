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
}
