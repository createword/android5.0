package com.example.winter.uiUtils;

import android.app.ActivityManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;

/**
 * Created by WINTER on 2016/9/23.
 */
public class BitmapUtils {
    private int insampSize = 0;
    private LruCache<String, Bitmap> mMemoryCache;

    public Bitmap decodeBimapForResource(Resources res,  int resId, int reW, int reH) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize = calulaInSampleSize(options, reW, reH);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);

    }

    //计算为徒采样比例
    public int calulaInSampleSize(BitmapFactory.Options options, int reW, int reH) {
        int w = options.outWidth;
        int h = options.outWidth;
        if (w > reW || h > reH) {
            insampSize = Math.round((float) h / (float) reH);
        } else {
            insampSize = Math.round((float) w / (float) reW);
        }
        return insampSize;
    }

    /**
     *获取内存大小
     */
    public void acquireMemoryCache() {
        int memoryClass = ((ActivityManager) uiUtilsTool.getBasAppContext().getSystemService(uiUtilsTool.getBasAppContext().ACTIVITY_SERVICE)).getMemoryClass();
        int cacheSize = 1024 * 1024 * memoryClass / 8;
        mMemoryCache = new LruCache<>(cacheSize);

    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitMapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }

    }

    public Bitmap getBitMapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }
}
