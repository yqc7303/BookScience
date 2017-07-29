package com.yangqichao.bokuscience.business.bean;

/**
 * Created by yangqc on 2017/7/29.
 */

public class ShowDialogEvent {
    boolean isshow;

    public ShowDialogEvent(boolean isshow) {
        this.isshow = isshow;
    }

    public boolean isshow() {
        return isshow;
    }

    public void setIsshow(boolean isshow) {
        this.isshow = isshow;
    }
}
