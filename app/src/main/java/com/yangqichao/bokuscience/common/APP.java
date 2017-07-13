package com.yangqichao.bokuscience.common;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.igexin.sdk.PushManager;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.yangqichao.bokuscience.business.service.GetuiIntentService;
import com.yangqichao.bokuscience.business.service.GetuiPushService;
import com.yangqichao.commonlib.util.PreferenceUtils;

/**
 * Created by yangqc on 2017/3/23.
 */

public class APP extends Application{

    private static APP application;
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;


        // TODO: 2017/5/17 debug == false
        Bugly.init(getApplicationContext(), "aaa40ea2c9", true);

        PushManager.getInstance().initialize(this.getApplicationContext(), GetuiPushService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), GetuiIntentService.class);
    }

    public static APP getInstance() {
        return application;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);

        Beta.installTinker();
    }

    public static String getUserId(){
        return PreferenceUtils.getPrefString(getInstance(),"uId","");
    }
}
