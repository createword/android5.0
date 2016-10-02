package com.example.winter.ddesignan;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.example.winter.base.BaseFragment;
import com.example.winter.uiUtils.LruCacheUtils;

import java.io.InputStream;

/**
 * Created by WINTER on 2016/9/23.
 */
public class ShowImgFragment extends BaseFragment {
    private LruCacheUtils lruCacheUtils;
    private static String DISK_CACHE_SUBDIR = "temp";
    private static int DISJ_CACHE_SIZE = 1024 * 1024 * 10;

    @Override
    protected View createView() {
        View view = View.inflate(getActivity(), R.layout.shoimg, null);

        return view;
    }


    @Override
    protected void initView(View view) {
        {
            final String url = "http://a.hiphotos.baidu.com/baike/crop%3D0%2C97%2C785%2C517%3Bc0%3Dbaike92%2C5%2C5%2C92%2C30/sign=c9a68f56a944ad343af0ddc7ed9220cd/4a36acaf2edda3cc245c959b09e93901203f92d4.jpg";
            Button bt = (Button) view.findViewById(R.id.bt);
            final ImageView imview = (ImageView) view.findViewById(R.id.img);

            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadImage(url, imview);
                }
            });
        }

    }

    public void loadImage(String url, final ImageView img) {
        Bitmap bitmap = lruCacheUtils.getBitMapFromMemCache(url);
        if (bitmap == null) {
            InputStream inputStream = lruCacheUtils.getDiskCache(url);
            if (inputStream == null) {
                lruCacheUtils.putCache(url, new LruCacheUtils.CallBack() {
                    @Override
                    public void response(Bitmap entry) {
                        System.out.print("loadS............................" + entry);
                        img.setImageBitmap(entry);
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
            System.out.print("cache");
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
