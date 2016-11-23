package com.zhangke.animator;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ImageView;

/**
 * Created by ZhangKe on 2016/11/23.
 */
public class DropView extends ImageView {

    private  int ms=3000;
    private Context context;
    private ObjectAnimator animator;
    private int SceenHeight=3000;
    private int SceenWidth=3000;

    public DropView(Context context) {
        super(context);
        this.context=context;
        getSceenWidthAndHeight();
        initAnmator();
    }

    public DropView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        getSceenWidthAndHeight();
        initAnmator();
    }

    public DropView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        getSceenWidthAndHeight();
        initAnmator();
    }

    private void initAnmator(){
        animator=ObjectAnimator.ofFloat(this,"y",SceenHeight);
        animator.setDuration(ms);
    }

    public void startDropAnimator(){
        animator.start();
    }

    public int getMs() {
        return ms;
    }

    public void setMs(int ms) {
        this.ms = ms;
        animator.setDuration(ms);
    }

    private void getSceenWidthAndHeight(){
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        SceenHeight=dm.heightPixels;
        SceenWidth=dm.widthPixels;
    }
}
