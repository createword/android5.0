package com.example.winter.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by WINTER on 2016/9/30.
 */
public class MTextView extends TextView {
    public MTextView(Context context) {
        super(context);
        setText("dsdsddddd");
    }

    public MTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        measure(0,0);

        Log.i("Tag", "width: " + getWidth() + ",height: " + getHeight());
        Log.i("Tag", "MeasuredWidth: " + getMeasuredWidth()
                + ",MeasuredHeight: " + getMeasuredHeight());
    }
}
