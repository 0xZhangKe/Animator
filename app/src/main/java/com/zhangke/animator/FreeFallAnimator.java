package com.zhangke.animator;

import android.content.Context;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;

/**
 * Created by zk721 on 2016/11/24.
 */
public class FreeFallAnimator {

    private Context context;
    private View view;

    private TranslateAnimation translateAnimation;
    private int durationMillis=2000;

    public FreeFallAnimator(Context context, View view) {
        this.context = context;
        this.view = view;

        initAnimator();
    }

    private void initAnimator(){
        translateAnimation=new TranslateAnimation(0, 0,
                0, getFallHeight());
        translateAnimation.setDuration(durationMillis);
        translateAnimation.setInterpolator(new BounceInterpolator());
//        translateAnimation.setFillAfter(true);
    }

    public void start(){
        view.startAnimation(translateAnimation);
    }

    private int getFallHeight(){
        int[] position=new int[2];
        view.getLocationOnScreen(position);
        return Utils.getScreenHeight(context)-position[1]-view.getHeight();
    }

}
