package com.yangqichao.bokuscience;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;

import com.yangqichao.bokuscience.business.bean.LoginBean;
import com.yangqichao.bokuscience.business.ui.login.LoginActivity;
import com.yangqichao.bokuscience.common.net.CommonsSubscriber;
import com.yangqichao.bokuscience.common.net.RequestBody;
import com.yangqichao.bokuscience.common.net.RequestUtil;
import com.yangqichao.commonlib.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        initView();
    }

    protected void initView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String uId = PreferenceUtils.getPrefString(SplashActivity.this, "uId", "");
                String phone = PreferenceUtils.getPrefString(SplashActivity.this, "phone", "");
                String pw = PreferenceUtils.getPrefString(SplashActivity.this, "pw", "");
                if(TextUtils.isEmpty(uId)){
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }else{
                    // TODO: 2017/6/24 是否重新获取用户信息
                    RequestBody requestBody = new RequestBody();
                    requestBody.setLoginName(phone);
                    requestBody.setPassword(pw);
                    requestBody.setAppType("0");
                    requestBody.setCid(PreferenceUtils.getPrefString(SplashActivity.this,"clientid",""));
                    RequestUtil.createApi().login(requestBody).compose(RequestUtil.<LoginBean>handleResult())
                            .subscribe(new CommonsSubscriber<LoginBean>() {
                                @Override
                                protected void onSuccess(LoginBean loginBean) {

                                    String mark = PreferenceUtils.getPrefString(SplashActivity.this,"mark","");
                                    if(!TextUtils.isEmpty(mark)){
                                        String[] split = mark.split("-");
                                        List<LoginBean.ModuleDTOSBean> beanList = new ArrayList<LoginBean.ModuleDTOSBean>();
                                        for(LoginBean.ModuleDTOSBean dtosBean:loginBean.getModuleDTOS()){
                                            dtosBean.setGone(false);
                                            for(int i = 0;i<split.length;i++){
                                                if(split[i].equals(dtosBean.getCode())){
                                                    dtosBean.setGone(true);
                                                }
                                            }
                                        }
                                        for(LoginBean.ModuleDTOSBean dtosBean:loginBean.getModuleDTOS()){
                                            if(!dtosBean.isGone()){
                                                beanList.add(dtosBean);
                                            }
                                        }
                                        if(beanList.size()==0){
                                            loginBean.setModuleDTOSUser(loginBean.getModuleDTOS());
                                        }else{
                                            loginBean.setModuleDTOSUser(beanList);
                                        }

                                    }else {
                                        loginBean.setModuleDTOSUser(loginBean.getModuleDTOS());
                                    }



                                    MainActivity.startAction(SplashActivity.this,loginBean);
                                    PreferenceUtils.setPrefString(SplashActivity.this,"hospitalId",loginBean.getHospitalId()+"");
                                    PreferenceUtils.setPrefString(SplashActivity.this,"hospitalName",loginBean.getHospitalName()+"");
                                    PreferenceUtils.setPrefString(SplashActivity.this,"deptId",loginBean.getDeptId()+"");
                                    PreferenceUtils.setPrefInt(SplashActivity.this,"publish",loginBean.getPublishFlag());
                                    PreferenceUtils.setPrefString(SplashActivity.this,"credit",loginBean.getCredit());
                                    PreferenceUtils.setPrefString(SplashActivity.this,"hospitalCode",loginBean.getHospitalCode());
                                    finish();
                                }

                                @Override
                                public void onFail(String errorCode, String message) {
                                    super.onFail(errorCode, message);
                                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                                    finish();
                                }

                                @Override
                                protected void onErrorShow(String errorMsg) {
                                    super.onErrorShow(errorMsg);
                                }
                            });

                }

            }
        }, 200);

    }
}
