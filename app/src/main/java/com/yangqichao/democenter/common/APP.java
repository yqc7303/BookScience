package com.yangqichao.democenter.common;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;

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
}
