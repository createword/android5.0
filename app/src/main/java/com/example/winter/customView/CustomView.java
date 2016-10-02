/*
package com.example.winter.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.winter.ddesignan.R;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

*/
/**
 * Created by WINTER on 2016/9/29.
 *//*

public class CustomView extends View {
    Paint paint;
    Rect mBound;
    */
/**
     * 文本
     *//*

    private String mTitleText;
    */
/**
     * 文本的颜色
     *//*

    private int mTitleTextColor;
    */
/**
     * 文本的大小
     *//*

    private int mTitleTextSize;

    public CustomView(Context context) {
        this(context, null);

    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获得自定义样式属性

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleView, defStyleAttr, 0);
        int n = array.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = array.getIndex(i);
            switch (attr) {
                case R.styleable.CustomTitleView_titleText:
                    mTitleText = array.getString(attr);
                    break;
                case R.styleable.CustomTitleView_titleTextColor:
                    mTitleTextColor = array.getColor(attr, Color.RED);
                    break;
                case R.styleable.CustomTitleView_titleTextSize:
                    mTitleTextSize = array.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;

            }
        }
        array.recycle();
        //文本的宽和高
        paint = new Paint();
        mBound = new Rect();
        paint.setTextSize(mTitleTextSize);
        paint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);//获得全包含文字的最小矩形
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width, heigh;
        int wModel = MeasureSpec.getMode(widthMeasureSpec);
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int hModel = MeasureSpec.getMode(heightMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        if (wModel == MeasureSpec.EXACTLY) {
            width = wSize;
        } else {
            paint.setTextSize(mTitleTextSize);
            paint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
            float textW = mBound.width();
            int desired = (int) (getPaddingLeft() + textW + getPaddingRight());
            width = desired;
        }
        if (hModel == MeasureSpec.EXACTLY) {
            heigh = hSize;
        } else {
            paint.setTextSize(mTitleTextSize);
            paint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
            float textHeight = mBound.height();
            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            heigh = desired;
        }
        setMeasuredDimension(width, heigh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);//画布矩形
        paint.setColor(mTitleTextColor);
        canvas.drawText(mTitleText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, paint);//绘制文字屏幕的宽度/2-矩形的宽度
    }
}
*/
