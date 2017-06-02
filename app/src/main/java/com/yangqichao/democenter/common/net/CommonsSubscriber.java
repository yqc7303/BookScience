package com.yangqichao.democenter.common.net;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.yangqichao.democenter.common.APP;
import com.yangqichao.democenter.common.Constant;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/***
 * CommonsSubscriber
 *
 * @param <T>
 */
public abstract class CommonsSubscriber<T> extends Subscriber<T> {
    public static final String TAG = "CommonsSubscriber";

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onNext(T t) {
        if (t instanceof Response) {
            Response response = (Response) t;
            if (response.getCode().equals(Constant.HTTP_SUCCESS)) {
                onSuccess(t);
                tips(response.getMsg(), response.getCode());
            }else {
                onFail(response.getCode(), response.getMsg());
            }
        } else {
            onSuccess(t);
        }

    }

    public void tips(String tips, String returnNo) {
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: " + e.getMessage());
        e.printStackTrace();
        String errorMsg = "";
        if (e instanceof CommonsError) {
            CommonsError error = (CommonsError) e;
            errorMsg = error.getMsg();
            onFail(error.getCode(), error.getMsg());
        } else {
            if (e instanceof SocketTimeoutException) {
                errorMsg = "网络连接超时，请检查网络";
            } else if (e instanceof ConnectException) {
                errorMsg = "服务器连接异常,请稍后再试";
            } else if (e instanceof RuntimeException) {
//                errorMsg = "程序发生错误，请联系开发人员";
                errorMsg = null;
            } else if (e instanceof HttpException) {
                HttpException ex = (HttpException) e;
                errorMsg = ex.getMessage();
            } else {
                errorMsg = "请重试";
            }
        }
        if (!TextUtils.isEmpty(errorMsg)) {
            Toast.makeText(APP.getInstance(), errorMsg, Toast.LENGTH_SHORT).show();
        }

    }

    protected abstract void onSuccess(T t);

    public void onFail(String errorCode, String message) {
        Log.e(TAG, "_onError: errorCode = " + errorCode + "  , message  = " + message);
        if (!TextUtils.isEmpty(message)) {
            Toast.makeText(APP.getInstance(), message, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onCompleted() {

    }
}
