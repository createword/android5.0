package com.example.winter.coustomApplication;

import android.app.Application;
import android.content.Context;

/**
 * Created by WINTER on 2016/9/21.
 */
public class BaseApplication extends Application {
    public static BaseApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }
    // 全局变量getContext
    public static Context getApplication() {
        return mApplication;

    }
}
