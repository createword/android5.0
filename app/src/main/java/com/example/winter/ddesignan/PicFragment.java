package com.example.winter.ddesignan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.winter.base.BaseFragment;

/**
 * Created by WINTER on 2016/9/28.
 */
public class PicFragment extends BaseFragment {
    private ImageView img;

    @Override
    protected View createView() {
        return View.inflate(mActivity, R.layout.picframent, null);
    }

    @Override
    protected void initView(View view) {
        img = (ImageView) view.findViewById(R.id.imgtag);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mActivity,"HAHAH",Toast.LENGTH_LONG).show();
            }
        });
    }


}
