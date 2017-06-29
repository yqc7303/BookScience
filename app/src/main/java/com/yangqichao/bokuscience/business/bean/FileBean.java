package com.yangqichao.bokuscience.business.bean;

/**
 * Created by yangqc on 2017/6/28.
 */

public class FileBean {

    private String name;
    private String path;
    private String type;

    public FileBean(String name, String path, String type) {
        this.name = name;
        this.path = path;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
