package com.example.winter.ddesignan;

import android.app.ActivityManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.winter.base.BaseFragment;
import com.example.winter.uiUtils.BitmapUtils;
import com.example.winter.uiUtils.uiUtilsTool;

/**
 * Created by WINTER on 2016/9/23.
 */
public class DesignFragment extends BaseFragment {
    BitmapUtils bitmapUtils = new BitmapUtils();
    Bitmap bmps;

    @Override
    protected View createView() {
        View view = View.inflate(getActivity(), R.layout.design, null);
        return view;
    }

    @Override
    protected void initView(View view) {
        Button bt = (Button) view.findViewById(R.id.bt);
        final ImageView imview = (ImageView) view.findViewById(R.id.img);

        bitmapUtils.acquireMemoryCache();//获取系统内存
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = String.valueOf(R.mipmap.ss);
                Bitmap bmp = bitmapUtils.getBitMapFromMemCache(key);
                if (bmp == null) {
                    bmps = bitmapUtils.decodeBimapForResource(getResources(), R.mipmap.ss, 1920, 1000);
                    bitmapUtils.addBitmapToMemoryCache(key, bmps);
                } else {
                    Toast.makeText(getActivity(), "LruCache 已有位图", Toast.LENGTH_LONG).show();
                }
                imview.setImageBitmap(bmps);
            }
        });
    }


}
