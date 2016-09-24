package com.example.winter.ddesignan;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.winter.uiUtils.BitmapUtils;
import com.example.winter.uiUtils.LruCacheUtils;

import java.io.InputStream;

/**
 * Created by WINTER on 2016/9/23.
 */
public class ShowImgFragment extends Fragment {
    BitmapUtils bitmapUtils = new BitmapUtils();

    private LruCacheUtils lruCacheUtils;
    private static String DISK_CACHE_SUBDIR = "temp";
    private static int DISJ_CACHE_SIZE = 1024 * 1024 * 10;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.shoimg, null);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final String url = "http://img3.imgtn.bdimg.com/it/u=1710405152,1210183381&fm=21&gp=0.jpg";
        Button bt = (Button) view.findViewById(R.id.bt);
        final ImageView imview = (ImageView) view.findViewById(R.id.img);

        bitmapUtils.acquireMemoryCache();//获取系统内存
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImage(url, imview);
            }
        });
    }

    public void loadImage(String url, final ImageView img) {
        Bitmap bitmap = lruCacheUtils.getBitMapFromMemCache(url);
        if (bitmap == null) {
            InputStream inputStream = lruCacheUtils.getDiskCache(url);
            if (inputStream == null) {
                lruCacheUtils.putCache(url, new LruCacheUtils.CallBack() {
                    @Override
                    public void response(Object entry) {
                        img.setImageBitmap((Bitmap) entry);
                    }
                });
            } else {
                //disk cache
                bitmap = BitmapFactory.decodeStream(inputStream);
                lruCacheUtils.addBitmapToMemoryCache(url, bitmap);
                img.setImageBitmap(bitmap);
            }
        } else {
            //cache memory
            img.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        lruCacheUtils = LruCacheUtils.getInstance();
        lruCacheUtils.open(getActivity(), DISK_CACHE_SUBDIR, DISJ_CACHE_SIZE);
    }

    @Override
    public void onStop() {
        super.onStop();
        lruCacheUtils.close();
    }

    @Override
    public void onPause() {
        super.onPause();
        lruCacheUtils.flush();
    }
}
