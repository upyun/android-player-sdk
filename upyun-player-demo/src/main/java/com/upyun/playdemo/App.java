package com.upyun.playdemo;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by vvc on 2017/8/14.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "ae9f3c2c49", true);
    }
}
