package com.example.winter.ddesignan;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.winter.sql.DbUtils;
import com.example.winter.sql.HoardDbOpenHelper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by WINTER on 2016/9/21.
 */
public class DataAct extends Activity implements View.OnClickListener {
    private Button savebtn, deletebtn;
    private EditText ettext;
    private ImageView c_img;
    private VideoView v_video;
    private String val;
    File phoneFile, videoFile;
    DbUtils dbUtils = new DbUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcontent);
        val = getIntent().getStringExtra("flag");
        savebtn = (Button) findViewById(R.id.save);
        deletebtn = (Button) findViewById(R.id.delete);
        ettext = (EditText) findViewById(R.id.ettext);
        c_img = (ImageView) findViewById(R.id.c_img);
        v_video = (VideoView) findViewById(R.id.c_video);
        savebtn.setOnClickListener(this);
        deletebtn.setOnClickListener(this);
        initView();
    }

    public void initView() {
        if (val.equals("1")) { // 文字
            c_img.setVisibility(View.GONE);
            v_video.setVisibility(View.GONE);
        }
        if (val.equals("2")) {
            c_img.setVisibility(View.VISIBLE);
            v_video.setVisibility(View.GONE);
            Intent iimg = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            phoneFile = new File(Environment.getExternalStorageDirectory()
                    .getAbsoluteFile() + "/" + getTime() + ".jpg");
            iimg.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(phoneFile));
            startActivityForResult(iimg, 1);
        }
        if (val.equals("3")) {
            c_img.setVisibility(View.GONE);
            v_video.setVisibility(View.VISIBLE);
            Intent video = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            videoFile = new File(Environment.getExternalStorageDirectory()
                    .getAbsoluteFile() + "/" + getTime() + ".mp4");
            video.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(videoFile));
            startActivityForResult(video, 2);
        }
    }

    @Override
    public void onClick(View v) {
       // File file = new File("ff");
        //  SQLiteDatabase  mdb = SQLiteDatabase.openOrCreateDatabase(file, null);
        SQLiteDatabase mdb = HoardDbOpenHelper.getIntent().getWritableDatabase();

        ContentValues cv = new ContentValues();
        switch (v.getId()) {
            case R.id.save:
                cv.put(HoardDbOpenHelper.CONTENT, ettext.getText().toString());
                cv.put(HoardDbOpenHelper.TIME, getTime());
                cv.put(HoardDbOpenHelper.PATH, phoneFile + "");
                cv.put(HoardDbOpenHelper.VIDEO, videoFile + "");
                mdb.insert(HoardDbOpenHelper.TABLE_NAME, null, cv);
                finish();
                break;
            case R.id.delete:
                break;

        }
    }

    public String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        return format.format(new Date());
    }
}
