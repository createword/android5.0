package com.example.winter.sql;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.view.View;
import android.widget.EditText;

import java.util.Date;
import java.util.Map;

/**
 * Created by WINTER on 2016/9/21.
 */
public class DbUtils {
    private SQLiteDatabase sqldatabase;
    private static ContentValues cv;

    public DbUtils() {
        sqldatabase = HoardDbOpenHelper.getIntent().getWritableDatabase();

    }


    public void add(Map<String, Object> map, EditText e) {

    }



}
