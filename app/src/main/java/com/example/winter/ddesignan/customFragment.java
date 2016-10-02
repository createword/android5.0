package com.example.winter.ddesignan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.winter.base.BaseFragment;
import com.example.winter.customView.Layo;
import com.example.winter.customView.MTextView;
import com.example.winter.customView.MyLayout;
import com.example.winter.customView.TextViewTest;
import com.example.winter.customView.cusPaintView;
import com.example.winter.uiUtils.LruCacheUtils;

import java.io.InputStream;

/**
 * Created by WINTER on 2016/9/23.
 */
public class customFragment extends BaseFragment {
    private LinearLayout mBackgroundLayout;
    private TextViewTest mTextViewTest;

    @Override
    protected View createView() {
          View view = View.inflate(getActivity(), R.layout.cusview, null);

        mBackgroundLayout = new MyLayout(mActivity);
        mBackgroundLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT));

        mTextViewTest = new TextViewTest(mActivity);

        mBackgroundLayout.addView(mTextViewTest);

        //return new MTextView(mActivity);
        return new cusPaintView(mActivity);
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    public void onResume() {
        super.onResume();
        // 方法2
     /*   DisplayMetrics dm = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        float width = dm.widthPixels * dm.density;
        float height = dm.heightPixels * dm.density;
        Toast.makeText(mActivity, "First method:" + dm.toString() + "\n" + "Second method:" + "Y=" + width + ";X=" + height, Toast.LENGTH_LONG).show();*/
        DisplayMetrics dm = new DisplayMetrics();
        mActivity.  getWindowManager().getDefaultDisplay().getMetrics(dm);
        //应用区域
        Rect outRect1 = new Rect();
        mActivity.  getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect1);
        int statusBar = dm.heightPixels - outRect1.height();  //状态栏高度=屏幕高度-应用区域高度
        Toast.makeText(mActivity, "First method:" + statusBar, Toast.LENGTH_LONG).show();

    }
}
