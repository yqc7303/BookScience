package com.yangqichao.bokuscience.common.net;


import com.yangqichao.bokuscience.business.bean.LoginBean;
import com.yangqichao.bokuscience.business.bean.LevelBean;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by yangqc on 2017/3/22.
 */
public interface API {

//    @POST("app/login")
//    Observable<Response<LoginBean>> login(@Body RequestBody requestBody);
//
//    @GET("app/searchWordQr/{id}/{userId}")
//    Observable<Response<String>> searchWordQr(@Path("id") String id,
//                                              @Path("userId") String userId);

    @GET("app/searchPatentAppPic")
    Observable<Response<String>> searchPatentAppPic();


    @GET("app/check/{tel}")
    Observable<Response<String>> check(@Path("tel") String tel);

    @GET("app/resetpassword/{tel}/{checkcode}")
    Observable<Response<String>> resetpassword(@Path("tel") String tel,@Path("checkcode") String code);

    @POST("app/login")
    Observable<Response<LoginBean>> login(@Body RequestBody requestBody);

    @GET("app/province/get")
    Observable<Response<List<LevelBean>>> get();

    @GET("app/orgsub/get/{pid}")
    Observable<Response<List<LevelBean>>> getLevel(@Path("pid") int pid);

//    @POST("app/registe/add")
//    Observable<Response<>> add(RequestBody requestBody);




}
