package com.yangqichao.democenter.common.net;

import android.util.Log;

import com.yangqichao.commonlib.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by yangqc on 2017/3/23.
 */

public class RequestUtil {
    /**
     * gradle.properties
     * debug_base_url = "http://zl.medbooks.com.cn/";
     * release_base_url = "http://112.124.115.69:8071/";
     */
    public static final String BASE_URL = BuildConfig.BaseUrl;

    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static API createApi(){
        return retrofit.create(API.class);
    }

    public static <T> Observable.Transformer<Response<T>,T> handleResult(){
        return  new Observable.Transformer<Response<T>,T>(){

            @Override
            public Observable<T> call(Observable<Response<T>> responseObservable) {
                return responseObservable.flatMap(new Func1<Response<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(Response<T> tResponse) {
                        if (tResponse.isSUccess()) {
                            return createData(tResponse.getData());
                        } else {
                            return Observable.error(new CommonsError(tResponse.getCode(), tResponse.getMsg()));
                        }
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });

    }

    private static OkHttpClient getOkHttpClient() {
        //日志显示级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                if (BuildConfig.DEBUG) {
                    Log.i("httpLog", "message:" + message);
                }

            }
        });
        loggingInterceptor.setLevel(level);
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient
                .Builder();
        //OkHttp进行添加拦截器loggingInterceptor
        httpClientBuilder.addInterceptor(loggingInterceptor);
        return httpClientBuilder.build();
    }
}
