package com.yangqichao.democenter.common.net;


import retrofit2.http.GET;
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

}
