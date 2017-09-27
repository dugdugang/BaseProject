package com.test.baseproject.app;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

/**
 * Author: duguang
 * Date 2017/9/22 0022.
 */

public class App extends Application {
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        initLeak();

    }

    /**
     * 内存泄露工具初始化
     */
    private void initLeak() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    /**
     * 获取applicationContext
     *
     * @return
     */
    public static Context getAppContext() {
        return appContext;
    }
}
