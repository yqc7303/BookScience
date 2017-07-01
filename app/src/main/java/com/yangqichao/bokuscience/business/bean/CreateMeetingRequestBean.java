package com.yangqichao.bokuscience.business.bean;

import java.util.List;

/**
 * Created by yangqc on 2017/6/30.
 */

public class CreateMeetingRequestBean {


    /**
     * address : string
     * content : string
     * createrId : 0
     * fileUrl : string
     * gmtStart : 2017-06-30T03:13:25.977Z
     * gps : string
     * h5Url : string
     * hospitalId : 0
     * hospitalName : string
     * meetingjoinVoList : [{"meetingId":0,"meetingTitle":"string","userId":0,"userName":"string","userTel":"string"}]
     * noticeflag : 0
     * title : string
     */

    private String address;
    private String content;
    private String createrId;
    private String fileUrl;
    private String gmtStart;
    private String gps;
    private String h5Url;
    private String hospitalId;
    private String hospitalName;
    private String noticeflag;
    private String title;
    private List<MeetingjoinVoListBean> meetingjoinVoList;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreaterId() {
        return createrId;
    }

    public void setCreaterId(String createrId) {
        this.createrId = createrId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getGmtStart() {
        return gmtStart;
    }

    public void setGmtStart(String gmtStart) {
        this.gmtStart = gmtStart;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getH5Url() {
        return h5Url;
    }

    public void setH5Url(String h5Url) {
        this.h5Url = h5Url;
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

    public String getNoticeflag() {
        return noticeflag;
    }

    public void setNoticeflag(String noticeflag) {
        this.noticeflag = noticeflag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MeetingjoinVoListBean> getMeetingjoinVoList() {
        return meetingjoinVoList;
    }

    public void setMeetingjoinVoList(List<MeetingjoinVoListBean> meetingjoinVoList) {
        this.meetingjoinVoList = meetingjoinVoList;
    }

    public static class MeetingjoinVoListBean {
        /**
         * meetingId : 0
         * meetingTitle : string
         * userId : 0
         * userName : string
         * userTel : string
         */

        private String meetingId;
        private String meetingTitle;
        private String userId;
        private String userName;
        private String userTel;

        public String getMeetingId() {
            return meetingId;
        }

        public void setMeetingId(String meetingId) {
            this.meetingId = meetingId;
        }

        public String getMeetingTitle() {
            return meetingTitle;
        }

        public void setMeetingTitle(String meetingTitle) {
            this.meetingTitle = meetingTitle;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
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
    }
}
