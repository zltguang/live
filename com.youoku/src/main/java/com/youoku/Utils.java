package com.youoku;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2016/11/11 0011.
 */

public class Utils {

    //隐藏菜单
    public static void hide(View view) {

        float fromDegress = 0f;
        float toDegress = -180f;
        RotateView(view, fromDegress, toDegress,0L);
        setClickable(view,false);

    }
    //隐藏菜单
    public static void hide(View view,long startoffset) {

        float fromDegress = 0f;
        float toDegress = -180f;
        RotateView(view, fromDegress, toDegress,startoffset);
        setClickable(view,false);
    }
    //显示菜单
    public static void show(View view){
        float fromDegress = -180f;
        float toDegress = 0f;
        RotateView(view, fromDegress, toDegress,0L);
        setClickable(view,true);
    }
    public static void setClickable(View view,Boolean clickable){
        view.setClickable(clickable);
        if(view instanceof ViewGroup){
            ViewGroup vg = (ViewGroup) view;
            for(int i = 0;i<vg.getChildCount();i++){
                View child = vg.getChildAt(i);
                child.setClickable(clickable);
            }

        }


    }

    private static void RotateView(View view, float fromDegress, float toDegress,long startoffset) {
        int pivotXType = RotateAnimation.RELATIVE_TO_SELF;
        int pivoYType =  RotateAnimation.RELATIVE_TO_SELF;
        float pivoXValue = 0.5f;
        float pivotYValue = 1f;
        RotateAnimation ra = new RotateAnimation(fromDegress,toDegress, pivotXType, pivoXValue, pivoYType, pivotYValue);
        ra.setDuration(500);
        ra.setFillAfter(true);
        ra.setStartOffset(startoffset);

        ra.setAnimationListener(listener);
        view.startAnimation(ra);
    }

    private static int startCount = 0;
    static Animation.AnimationListener listener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
            startCount++;
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            startCount--;
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };
    public static  boolean HasAnimation(){
        return startCount > 0;
    }


}
