package com.example.winter.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by WINTER on 2016/10/1.
 */
public class Layo extends View {
    private Paint mPaint;
    int mViewWidth;
    int mViewHeight;

    public Layo(Context context) {
        this(context, null);
    }

    public Layo(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public Layo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        /*
         * 获取控件宽高
         */
        mViewWidth = w;
        mViewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
/*
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        canvas.drawRect(getWidth() / 2 - 100, getWidth() / 2 - 100, getWidth() / 2 + 100, getWidth() / 2 + 100, mPaint);//画布矩形
        //     canvas.drawRect(mViewWidth / 2 - 200, mViewHeight / 2 - 200, mViewWidth / 2 + 200, mViewHeight / 2 + 200, mPaint);
      //  canvas.save();
         *//*
     * 保存画布并绘制一个蓝色的矩形
     *//*
     //   canvas.saveLayer(getWidth() / 2 - 50, getWidth() / 2 - 50, getWidth() / 2 + 50, getWidth() / 2 + 50, null, Canvas.ALL_SAVE_FLAG);

        canvas.save();
        mPaint.setColor(Color.YELLOW);

       canvas.rotate(30);

        canvas.drawRect(getWidth() / 2 - 50, getWidth() / 2 - 50, getWidth() / 2 + 50, getWidth() / 2 + 50, mPaint);//画布矩形
          canvas.restore();*/


//save之后可以进行动画操作  restore恢复到保存之前的 如果不保存不restore 任何操作只会按照旋转后的布局进行操作位置也会发生变化
        int px = getMeasuredWidth();
        int py = getMeasuredWidth();
// Drw background
        canvas.drawRect(0, 0, px, py, mPaint);
        canvas.save();
        canvas.rotate(90, px / 2, py / 2);

// Draw up arrow
        mPaint.setColor(Color.BLUE);
        canvas.drawLine(px / 2, 0, 0, py / 2, mPaint);
        canvas.drawLine(px / 2, 0, px, py / 2, mPaint);
        canvas.drawLine(px / 2, 0, px / 2, py, mPaint);
        canvas.restore();


        canvas.drawCircle(px - 10, py - 10, 10, mPaint);

    }

}
