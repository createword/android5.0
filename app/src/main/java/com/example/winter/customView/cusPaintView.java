package com.example.winter.customView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.example.winter.ddesignan.R;

/**
 * Created by WINTER on 2016/10/1.
 */
public class cusPaintView extends View {
    private Paint mPaint = null;
    private Bitmap mBitmap;
    private int mW, mH;

    public cusPaintView(Context context) {
        this(context, null);
    }

    public cusPaintView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public cusPaintView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.dwq);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //获取空间的宽和高
        mW = w;
        mH = h;
        //缩放位图与控件一致   这里的空间就是画图的空间1080 1386
        mBitmap = Bitmap.createScaledBitmap(mBitmap, getWidth(), getHeight(), true);
//这里的getwidth（）  与mw一致
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        canvas.scale(1.0f, 1.0f);//为1表示不缩放
        //   canvas.scale(0.8f,0.8f);//为1表示不缩放
        canvas.drawBitmap(mBitmap, 0, 0, null);
        //  canvas.drawRect(getWidth() / 2 - 100, getWidth() / 2 - 100, getWidth() / 2 + 100, getWidth() / 2 + 100, mPaint);
        canvas.restore();
    }
}
