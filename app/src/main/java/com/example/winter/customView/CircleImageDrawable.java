package com.example.winter.customView;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/**
 * Created by WINTER on 2016/10/1.
 */
public class CircleImageDrawable extends Drawable {
    private Paint mPaint;
    private Bitmap bitmap;
    private int mWidth;
    public CircleImageDrawable( Bitmap bitmap){
        this.bitmap=bitmap;
        mPaint=new Paint();
        BitmapShader bitmapShader=new BitmapShader(bitmap, Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
        mPaint.setAntiAlias(true);
        mPaint.setShader(bitmapShader);
        mWidth=Math.min(bitmap.getWidth(),bitmap.getHeight());
    }
    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(mWidth / 2, mWidth / 2, mWidth / 2, mPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
