package com.example.winter.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.widget.TextView;

public class TextViewTest extends TextView {
    public TextViewTest(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        setText("dsdsddddd");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        //    measure(0, 0);
        Log.i("Tag", "width: " + getWidth() + ",height: " + getHeight());
        Log.i("Tag", "MeasuredWidth: " + getMeasuredWidth()
                + ",MeasuredHeight: " + getMeasuredHeight());
    }

}
