package com.example.winter.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import com.example.winter.uiUtils.FileUtils;
import com.example.winter.uiUtils.uiUtilsTool;

import java.io.File;

/**
 * Created by WINTER on 2016/9/21.
 */
public class HoardDbOpenHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "notes";
    public static final String CONTENT = "content";
    public static final String PATH = "path";
    public static final String VIDEO = "video";
    public static final String ID = "_id";
    public static final String TIME = "time";

    private static HoardDbOpenHelper hoardDbOpenHelper;

    public static HoardDbOpenHelper getIntent() {
        if (hoardDbOpenHelper == null) {
            synchronized (HoardDbOpenHelper.class) {
                if (hoardDbOpenHelper == null) {
                    hoardDbOpenHelper = new HoardDbOpenHelper(uiUtilsTool.getBasAppContext());
                }
            }

        }
        return hoardDbOpenHelper;
    }

    public HoardDbOpenHelper(Context context) {
        super(context, getMyDatabaseName("DATA.db"), null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + CONTENT
                + " TEXT NOT NULL," + PATH + " TEXT NOT NULL," + VIDEO
                + " TEXT NOT NULL," + TIME + " TEXT NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static String getMyDatabaseName(String databasename) {

        boolean isSdcardEnable = false;
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {//SDCard是否插入
            isSdcardEnable = true;
        }
        String dbPath = null;
        if (isSdcardEnable) {
            dbPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/AAWW/database/";
        } else {//未插入SDCard，建在内存中

        }
        File dbp = new File(dbPath);
        if (!dbp.exists()) {
            dbp.mkdirs();
        }
        databasename = dbPath + databasename;
        return databasename;
    }
}
