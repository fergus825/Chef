package com.nick.chef.app;

import android.app.Application;

import com.nick.chef.utils.CrashHandler;

/**
 * *********************************************************************
 * Author: Nick
 * Created on 2016/10/27  10:30
 * desc:配置全局信息
 * <p>
 * *********************************************************************
 */

public class IApp extends Application {
    //类似 一个log日志的开关
    public static boolean debugOn = false;

//    @Override
//    public void onCreate() {
//        super.onCreate();
//        // 程序异常信息报错
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(getApplicationContext());
//    }

}
