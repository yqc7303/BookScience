package com.yangqichao.bokuscience.business.bean;

/**
 * Created by yangqc on 2017/7/19.
 */

public class MessageDetail {


    /**
     * id : 11
     * title : 本院开通人卫数据库
     * imgUrl : http://112.124.115.69:8072/notification/images/1371485183456.jpg
     * content : 本院开通人卫数据库的使用，详情请联系图书管理员
     * type : 0
     * hospitalId : 0
     * gmtCreate : null
     */

    private int id;
    private String title;
    private String imgUrl;
    private String content;
    private int type;
    private int hospitalId;
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

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
