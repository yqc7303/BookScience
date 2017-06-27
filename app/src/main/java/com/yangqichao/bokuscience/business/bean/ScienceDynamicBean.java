package com.yangqichao.bokuscience.business.bean;

/**
 * Created by yangqc on 2017/6/25.
 */

public class ScienceDynamicBean {


    /**
     * id : 1
     * title : 测试学术动态1
     * imgUrl : alipay.jpg
     * content : 绯闻绯闻绯闻绯闻分为非111111111111
     * type : 0
     * hospitalId : 0
     * gmtCreate : 2017-06-21 01:31:34
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
