package com.example.winter.customView;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class MyLayout extends LinearLayout {
                                           
  public MyLayout(Context context) {
   super(context);
   // TODO Auto-generated constructor stub
  }
                                           
  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {
   // TODO Auto-generated method stub
   super.onLayout(changed, l, t, r, b);
   Log.i("Tag", "--------------");
   View mView=getChildAt(0);
   mView.measure(0, 0);
  }}