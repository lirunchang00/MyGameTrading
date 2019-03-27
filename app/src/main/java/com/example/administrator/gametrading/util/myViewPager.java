package com.example.administrator.gametrading.util;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class myViewPager extends ViewPager {
    private boolean enabled;

    public myViewPager(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        this.enabled=false;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.enabled) {
            Log.e("onTouchEvent", "onTouchEvent");
            return super.onTouchEvent(event);
        }

        return false;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.enabled) {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }

    public void setPagingEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
