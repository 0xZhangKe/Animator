package com.zhangke.animator;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by zk721 on 2016/11/25.
 */
public class ChangeImageWidthAnimator {

    private static ChangeImageWidthAnimator mInstance;

    private Context context;
    private ImageView imageView;
    private WrapperView wrapperView;

    private int durationMillis=1000;
    private ObjectAnimator animator;

    private int animatorSize=300;//dp

    private boolean isBig=false;

    private int originalWidth;
    private int originalHeight;

    private PropertyValuesHolder rotateAnimator;

    public static ChangeImageWidthAnimator getInstance(Context context, ImageView imageView){
        if(null == mInstance){
            mInstance = new ChangeImageWidthAnimator(context , imageView);
        }
        return mInstance;
    }

    private ChangeImageWidthAnimator(Context context, ImageView imageView) {
        this.context = context;
        this.imageView = imageView;

        originalWidth = imageView.getLayoutParams().width;
        originalHeight = imageView.getLayoutParams().height;

        animatorSize = Utils.dp2px(context,animatorSize);

        wrapperView = new WrapperView(imageView);
    }

    public void start(){
        if(isBig) {
            PropertyValuesHolder valuesHolderWidth = PropertyValuesHolder.ofInt("width",
                    originalWidth);
            PropertyValuesHolder valuesHolderHeight = PropertyValuesHolder.ofInt("height",
                    originalHeight);
            rotateAnimator = PropertyValuesHolder.ofFloat("rotation",0);
            animator = ObjectAnimator.ofPropertyValuesHolder(wrapperView,
                    valuesHolderHeight,
                    valuesHolderWidth,
                    rotateAnimator);
        }else{
            PropertyValuesHolder valuesHolderWidth = PropertyValuesHolder.ofInt("width",
                    animatorSize);
            PropertyValuesHolder valuesHolderHeight = PropertyValuesHolder.ofInt("height",
                    animatorSize);
            rotateAnimator = PropertyValuesHolder.ofFloat("rotation",90);
            animator = ObjectAnimator.ofPropertyValuesHolder(wrapperView,
                    valuesHolderHeight,
                    valuesHolderWidth,
                    rotateAnimator);
        }
        isBig = !isBig;
        animator.setDuration(durationMillis);
        animator.start();
    }

    public ChangeImageWidthAnimator setDurationMillis(int durationMillis) {
        this.durationMillis = durationMillis;
        return this;
    }

    private final class WrapperView{
        private View mTarget;

        public WrapperView(View mTarget) {
            this.mTarget = mTarget;
        }

        public int getWidth(){
            return mTarget.getLayoutParams().width;
        }

        public void setWidth(int width){
            mTarget.getLayoutParams().width = width;
            mTarget.requestLayout();
        }

        public void setHeight(int height){
            mTarget.getLayoutParams().height = height;
            mTarget.requestLayout();
        }

        public int getHeight(){
            return mTarget.getLayoutParams().height;
        }

        public float getRotation(){
            return mTarget.getRotation();
        }

        public void setRotation(float rotation){
            mTarget.setRotation(rotation);
        }
    }
}
