package com.yangqichao.democenter.common.net;

/**
 * Created by Administrator on 2016/8/10 0010.
 */
public class CommonsError extends Throwable {

    private String code;
    private String msg;

    public CommonsError(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
