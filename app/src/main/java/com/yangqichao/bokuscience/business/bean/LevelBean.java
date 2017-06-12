package com.yangqichao.bokuscience.business.bean;

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

    private int id;
    private String orgCode;
    private String orgName;
    private int parentId;
    private int level;
    private String logoUrl;
    private String moduleIds;
    private Object videosIds;
    private Object orgProvince;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
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
}
