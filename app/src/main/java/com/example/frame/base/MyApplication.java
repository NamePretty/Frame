package com.example.frame.base;

import android.app.Application;

import com.example.frame.util.Log.CrashHandler2;
import com.example.frame.util.Log.LogcatHelper;

/**
 * application
 *
 * @packageName: com.example.frame.base
 * @fileName: MyApplication
 * @date: 2019/7/2  15:22
 * @author: zdj
 */

public class MyApplication extends Application {
    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        // 初始化 log 保存本地工具类
        LogcatHelper.getInstance(this).start();
        // 初始化 抓取 异常信息
        CrashHandler2.getInstance().init(this);
    }

    public static synchronized MyApplication getInstance() {
        return myApplication;
    }

}
