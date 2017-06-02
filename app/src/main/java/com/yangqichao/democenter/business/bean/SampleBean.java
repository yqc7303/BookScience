package com.yangqichao.democenter.business.bean;

/**
 * Created by yangqc on 2017/5/22.
 */

public class SampleBean {

    private String name;
    private Class activity;


    public SampleBean(String name, Class activity) {
        this.name = name;
        this.activity = activity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getActivity() {
        return activity;
    }

    public void setActivity(Class activity) {
        this.activity = activity;
    }
}
