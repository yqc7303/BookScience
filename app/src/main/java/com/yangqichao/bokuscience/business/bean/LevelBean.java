package com.yangqichao.bokuscience.business.bean;

import java.util.List;

/**
 * Created by yangqc on 2017/6/8.
 */

public class LevelBean {


    /**
     * id : 2
     * orgCode : 0101
     * orgName : 浙江省
     * parentId : 1
     * level : 2
     * logoUrl : 1.jpg
     * moduleIds : 1
     * videosIds : null
     * orgProvince : null
     */

    private String id;
    private String orgCode;
    private String orgName;
    private String parentId;
    private String level;
    private String logoUrl;
    private String moduleIds;
    private Object videosIds;
    private Object orgProvince;
    private String seleteNum;

    private List<GetKeShiPerson.RecordsBean> keShiPerson;


    public List<GetKeShiPerson.RecordsBean> getKeShiPerson() {
        return keShiPerson;
    }

    public void setKeShiPerson(List<GetKeShiPerson.RecordsBean> keShiPerson) {
        this.keShiPerson = keShiPerson;
    }

    public String getSeleteNum() {
        return seleteNum;
    }

    public void setSeleteNum(String seleteNum) {
        this.seleteNum = seleteNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getModuleIds() {
        return moduleIds;
    }

    public void setModuleIds(String moduleIds) {
        this.moduleIds = moduleIds;
    }

    public Object getVideosIds() {
        return videosIds;
    }

    public void setVideosIds(Object videosIds) {
        this.videosIds = videosIds;
    }

    public Object getOrgProvince() {
        return orgProvince;
    }

    public void setOrgProvince(Object orgProvince) {
        this.orgProvince = orgProvince;
    }

    class KeShiPerson{

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
