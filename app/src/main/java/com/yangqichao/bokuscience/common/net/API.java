package com.yangqichao.bokuscience.common.net;


import com.yangqichao.bokuscience.business.bean.GetKeShiPerson;
import com.yangqichao.bokuscience.business.bean.LevelBean;
import com.yangqichao.bokuscience.business.bean.LoginBean;
import com.yangqichao.bokuscience.business.bean.MeetingDetailBean;
import com.yangqichao.bokuscience.business.bean.MyMeetingBean;
import com.yangqichao.bokuscience.business.bean.RegisteBean;
import com.yangqichao.bokuscience.business.bean.ScienceDynamicBean;

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


    @GET("app/user/check/{tel}")
    Observable<Response<String>> check(@Path("tel") String tel);

    @POST("app/user/login")
    Observable<Response<LoginBean>> login(@Body RequestBody requestBody);

    @GET("app/province/get")
    Observable<Response<List<LevelBean>>> get();

    @GET("app/orgsub/get/{pid}")
    Observable<Response<List<LevelBean>>> getLevel(@Path("pid") String pid);

//    @POST("app/registe/add")
//    Observable<Response<>> add(RequestBody requestBody);

    @POST("/app/user/registe")
    Observable<Response<RegisteBean>> registe(@Body RequestBody requestBody);

    @GET("/app/user/resetpassword/{tel}/{checkcode}")
    Observable<Response<String>> resetpassword(@Path("tel") String tel,@Path("checkcode") String checkcode);

    @GET("/app/notifications/getbyuser/{userId}")
    Observable<Response<List<ScienceDynamicBean>>> getbyuser(@Path("userId") String userId);

    @POST("/app/sharemsg/insertInfo")
    Observable<Response<String>> insertInfo(@Body RequestBody requestBody);

    @POST("/app/org/users")
    Observable<Response<GetKeShiPerson>> users(@Body RequestBody requestBody);

    @POST("/app/mymeetings/select")
    Observable<Response<MyMeetingBean>> select(@Body RequestBody requestBody);


    @GET("/app/meeting/get/{id}")
    Observable<Response<MeetingDetailBean>> meetingDetail(@Path("id") int id);

    @GET("/app/meeting/sign/{meetingId}/{userId}/{gps}")
    Observable<Response<String>> sign(@Path("meetingId") String meetingId,@Path("userId") String userId,@Path("gps") String gps);


}
