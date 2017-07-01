package com.yangqichao.bokuscience.business.bean;

/**
 * Created by yangqc on 2017/7/1.
 */

public class MeetingDetailBean {


    /**
     * id : 5
     * title : 测试会议
     * content : fefwe11111
     * gmtStart : 2017-06-15 17:25:00
     * signflag : 1
     * address : 浙江省-杭州市-余杭区,文一西路1111号
     * qrcode : http://localhost:8072/app/meeting/sign/5
     * gps : 120.01794069262561,30.281816615392938
     * fileUrl : http://112.124.115.69:8072/meetings/file/datatables 服务器端数据跨页多行选中.docx
     * h5Url : www
     * noticeflag : 1
     * gmtCreate : 2017-06-15 17:25:43
     * createrId : 1
     * hospitalId : 3
     * createrName : admin
     * hospitalName : 杭州医院1
     * state : 3
     */

    private int id;
    private String title;
    private String content;
    private String gmtStart;
    private int signflag;
    private String address;
    private String qrcode;
    private String gps;
    private String fileUrl;
    private String h5Url;
    private int noticeflag;
    private String gmtCreate;
    private int createrId;
    private int hospitalId;
    private String createrName;
    private String hospitalName;
    private int state;

    private String meetingjoinNum;

    public String getMeetingjoinNum() {
        return meetingjoinNum;
    }

    public void setMeetingjoinNum(String meetingjoinNum) {
        this.meetingjoinNum = meetingjoinNum;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGmtStart() {
        return gmtStart;
    }

    public void setGmtStart(String gmtStart) {
        this.gmtStart = gmtStart;
    }

    public int getSignflag() {
        return signflag;
    }

    public void setSignflag(int signflag) {
        this.signflag = signflag;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getH5Url() {
        return h5Url;
    }

    public void setH5Url(String h5Url) {
        this.h5Url = h5Url;
    }

    public int getNoticeflag() {
        return noticeflag;
    }

    public void setNoticeflag(int noticeflag) {
        this.noticeflag = noticeflag;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public int getCreaterId() {
        return createrId;
    }

    public void setCreaterId(int createrId) {
        this.createrId = createrId;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
