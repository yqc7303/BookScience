package com.yangqichao.democenter.common.net;

import android.text.TextUtils;

import com.yangqichao.democenter.common.Constant;


/**
 * Created by yangqc on 2017/3/22.
 */

public class Response<T> {


    /**
     * code : 8001
     * msg : 操作成功
     * data : 3
     */

    private String code;
    private String msg;
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSUccess(){
        return TextUtils.equals(getCode(), Constant.HTTP_SUCCESS);
    }
}
