package com.zhangke.animator;

import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;

/**
 * Created by zk721 on 2016/11/24.
 */
public class ClickAnimator {

    private View view;
    private Context context;

    private AnimationSet animationSet;
    private AlphaAnimation alphaAnimation;
    private ScaleAnimation scaleAnimation;
    private RotateAnimation rotateAnimation;

    private int durationMillis=200;

    public ClickAnimator(Context context, View view) {
        this.view = view;
        this.context = context;
        initAnimator();
    }

    private void initAnimator(){
        animationSet=new AnimationSet(true);
        animationSet.setDuration(durationMillis);

//        scaleAnimation=new ScaleAnimation(view.getWidth(),view.getWidth(),
//                view.getHeight(),view.getHeight());
        scaleAnimation=new ScaleAnimation(1,10,1,10,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(durationMillis);

        alphaAnimation=new AlphaAnimation(0.7f,0.0f);
        alphaAnimation.setDuration(durationMillis);

        rotateAnimation=new RotateAnimation(0,360,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(durationMillis);

        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
//        animationSet.addAnimation(rotateAnimation);
    }

    public void start(){
//        animationSet.setFillAfter(true);
        view.startAnimation(animationSet);
    }

}
