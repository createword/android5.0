package com.example.winter.uiUtils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;

import com.example.winter.coustomApplication.BaseApplication;
import com.example.winter.ddesignan.MainActivity;

/**
 * Created by WINTER on 2016/9/21.
 */
public class uiUtilsTool {

    public static Context getBasAppContext() {

        return BaseApplication.getApplication();
    }

    //获取屏幕真实分辨率getRealMetrics() 1920*1080包含状态栏和底部栏
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public DisplayMetrics getMetries(MainActivity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        /**
         * getRealMetrics - 屏幕的原始尺寸，即包含状态栏。
         * version >= 4.2.2
         */
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
       /* int width = metrics.widthPixels;
        int height = metrics.heightPixels;*/
        return metrics;
    }
}
