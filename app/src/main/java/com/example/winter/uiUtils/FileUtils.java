package com.example.winter.uiUtils;

import android.os.Environment;

import java.io.File;

/**
 * Created by WINTER on 2016/9/23.
 */
public class FileUtils {

    public static final String CACHE = "cache";
    public static final String ICON = "icon";
    public static final String ROOT = "AAb";

    /**
     * 获取图片的缓存的路径
     *
     * @return
     */
    public static File getIconDir() {
        return getDir(ICON);

    }

    /**
     * 获取缓存路径
     *
     * @return
     */
    public static File getCacheDir() {
        return getDir(CACHE);
    }

    public static File getDir(String cache) {
        StringBuilder path = new StringBuilder();
        if (isSDAvailable()) {
            path.append(Environment.getExternalStorageDirectory().getAbsolutePath());
            path.append(File.separator);// '/'
            path.append(ROOT);// /mnt/sdcard/GooglePlay
            path.append(File.separator);
            path.append(cache);// /mnt/sdcard/GooglePlay/cache

        } else {
            File filesDir = uiUtilsTool.getBasAppContext().getCacheDir(); // cache
            // getFileDir
            // file
            path.append(filesDir.getAbsolutePath());// /data/data/com.itheima.googleplay/cache
            path.append(File.separator);/// data/data/com.itheima.googleplay/cache/
            path.append(cache);/// data/data/com.itheima.googleplay/cache/cache
        }
        File file = new File(path.toString());
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();// 创建文件夹
        }

        return file;

    }

    /**
     * 保存数据获取路径
     *
     * @param name
     * @return
     */
    public static String getDirPath(String name) {
        StringBuilder path = new StringBuilder();
        if (isSDAvailable()) {
            path.append(Environment.getExternalStorageDirectory().getAbsolutePath());
            path.append(File.separator);// '/'
            path.append(ROOT);// /mnt/sdcard/GooglePlay
            path.append(File.separator);
            path.append("DataFile");// /mnt/sdcard/GooglePlay/cache

        } else {
            File filesDir = uiUtilsTool.getBasAppContext().getCacheDir(); // cache
            // getFileDir
            // file
            path.append(filesDir.getAbsolutePath());// /data/data/com.itheima.googleplay/cache
            path.append(File.separator);/// data/data/com.itheima.googleplay/cache/
            path.append("DataFile");/// data/data/com.itheima.googleplay/cache/cache

        }
        File file = new File(path.toString());
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();// 创建文件夹
        }

        return path.toString() + name;

    }

    private static boolean isSDAvailable() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

}


