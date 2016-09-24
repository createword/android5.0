package com.example.winter.uiUtils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.LruCache;

import com.example.winter.libcore.io.DiskLruCache;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by WINTER on 2016/9/24.
 */
public class LruCacheUtils {
    private static LruCacheUtils lruCacheUtils;
    private DiskLruCache mDiskLruCache;
    private Context context;
    private LruCache<String, Bitmap> mMemoryCache;
    private int insampSize = 0;

    public static LruCacheUtils getInstance() {
        if (lruCacheUtils == null) {
            synchronized (LruCacheUtils.class) {
                if (lruCacheUtils == null) {
                    lruCacheUtils = new LruCacheUtils();
                }
            }
        }
        return lruCacheUtils;
    }

    public File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    public int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public void open(Context context, String disk_cache_subdir, int disk_cache_size) {
        try {
            this.context = context;
            int memoryClass = ((ActivityManager) uiUtilsTool.getBasAppContext().getSystemService(uiUtilsTool.getBasAppContext().ACTIVITY_SERVICE)).getMemoryClass();
            int cacheSize = 1024 * 1024 * memoryClass / 8;
            mMemoryCache = new LruCache<>(cacheSize);

            File cacheDir = getDiskCacheDir(uiUtilsTool.getBasAppContext(), "bitmap");
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            mDiskLruCache = DiskLruCache.open(cacheDir, getAppVersion(uiUtilsTool.getBasAppContext()), 1, 10 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
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

    public Bitmap decodeBimapForStrem(byte[] bytes, int reW, int reH) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
        options.inSampleSize = calulaInSampleSize(options, reW, reH);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);

    }

    public void addBitmapToMemoryCache(String url, Bitmap bitmap) {
        String key = hashKeyForDisk(url);
        if (getBitMapFromMemCache(key) == null) {
            mMemoryCache.put(url, bitmap);
        }

    }

    public Bitmap getBitMapFromMemCache(String key) {
        String keys = hashKeyForDisk(key);
        return mMemoryCache.get(keys);
    }

    //下载内存
    public void putCache(final String url, final CallBack callBack) {

        new AsyncTask<String, Void, Bitmap>() {
            Bitmap bitmap = null;

            @Override
            protected Bitmap doInBackground(String... params) {
                String key = hashKeyForDisk(params[0]);
                DiskLruCache.Editor editor = null;

                try {
                    URL url1 = new URL(params[0]);
                    HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
                    conn.setReadTimeout(5000);
                    conn.setConnectTimeout(5000);
                    ByteArrayOutputStream baos = null;
                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
                        baos = new ByteArrayOutputStream();
                        byte[] bytes = new byte[1024];
                        int len = -1;
                        while ((len = bis.read(bytes)) != -1) {
                            baos.write(bytes, 0, len);
                        }
                        baos.close();
                        bis.close();
                        conn.disconnect();
                    }
                    if (baos != null) {
                        bitmap = decodeBimapForStrem(baos.toByteArray(), 100, 100);
                        addBitmapToMemoryCache(params[0], bitmap);
                        DiskLruCache.Editor edit = mDiskLruCache.edit(key);
                        System.out.print(url1.getFile());
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, edit.newOutputStream(0));
                        edit.commit();
                    }
                } catch (IOException e) {
                    try {
                        editor.abort();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                }
                return bitmap;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                callBack.response(bitmap);
            }
        }.execute(url);
    }

    //下载内存
    public InputStream getDiskCache(final String url) {
        String key = hashKeyForDisk(url);
        try {
            DiskLruCache.Snapshot st = mDiskLruCache.get(key);
            if (st != null) {
                return st.getInputStream(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void close() {
        if (mDiskLruCache != null && mDiskLruCache.isClosed()) {
            try {
                mDiskLruCache.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void flush() {
        if (mDiskLruCache != null) {
            try {
                mDiskLruCache.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public interface CallBack<T> {
        public void response(T entry);
    }
}
