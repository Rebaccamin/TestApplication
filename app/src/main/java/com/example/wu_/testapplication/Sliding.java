package com.example.wu_.testapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import java.util.jar.Attributes;


public class Sliding extends HorizontalScrollView {
    private LinearLayout mylayout ;
    private ViewGroup mycontent;
    private ViewGroup mymenu;
    private int sreenwid;
    private int menurightmargin =50;
    private boolean once=false;
    private int menuwidth;
    public Sliding(Context context,AttributeSet attrs) {
        super(context, attrs);
        WindowManager wm= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dp=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dp);
        sreenwid=dp.widthPixels;
        //单位转化 将50dp值改为50像素
       menurightmargin=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50,context.getResources().getDisplayMetrics());
    }
protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
    if(!once){
        mylayout=(LinearLayout)getChildAt(0);
        mymenu=(ViewGroup)getChildAt(0);
        mycontent=(ViewGroup)getChildAt(0);
        menuwidth=mymenu.getLayoutParams().width=sreenwid-menurightmargin;
        mycontent.getLayoutParams().width=sreenwid;
        once=true;
    }
    super.onMeasure(widthMeasureSpec,heightMeasureSpec);

}
    //设置偏移量，将menu隐藏
    protected void onLayout(boolean changed,int a,int b,int c,int d){
        super.onLayout(changed,a,b,c,d);
   if(changed){
       this.scrollTo(menuwidth,0);
   }

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action=ev.getAction();
        switch (action){
            case MotionEvent.ACTION_UP:
                int scroll=getScrollX();
                if(scroll>menuwidth/2)
                {
                    this.smoothScrollTo(menuwidth,0);
                }
                else{
                    this.smoothScrollTo(0,0);
                }return  true;
        }

        return super.onTouchEvent(ev);

    }
}
