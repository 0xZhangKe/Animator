package com.zhangke.animator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private RelativeLayout rl_drop_view_container;
    private TextView tv_fuck_dogs;
    private View click_ball;
    private TextView tv_newton_ball;

    private ArrayList<DropView> dogList=new ArrayList<>();

    private int dropViewWidth=20;//dp
    private int dropViewHeight=20;//dp

    private int screenWidth=2000;
    private int screenPadding=30;//dp
    private LayoutParams layoutParams;

    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){
        rl_drop_view_container = (RelativeLayout) findViewById(R.id.rl_drop_view_container);
        tv_fuck_dogs = (TextView) findViewById(R.id.tv_fuck_dogs);
        click_ball = findViewById(R.id.click_ball);
        tv_newton_ball = (TextView) findViewById(R.id.tv_newton_ball);

        tv_fuck_dogs.setOnClickListener(this);
        click_ball.setOnClickListener(this);
        tv_newton_ball.setOnClickListener(this);

        dropViewWidth=Utils.dp2px(this,dropViewWidth);
        dropViewHeight=Utils.dp2px(this,dropViewHeight);
        screenWidth=Utils.getScreenWidth(this);
        screenPadding=Utils.dp2px(this,screenPadding);
        random=new Random(System.currentTimeMillis());

        for(int i=0;i<20;i++) {
            final DropView dropView = new DropView(this);
            layoutParams=new LayoutParams(dropViewWidth, dropViewHeight);
            layoutParams.setMargins(random.nextInt(screenWidth-screenPadding*2)+screenPadding, 0,0,0);
            dropView.setLayoutParams(layoutParams);
            dropView.setVisibility(View.GONE);
            rl_drop_view_container.addView(dropView);
            dogList.add(dropView);
        }

    }

    private void fuckDogs(){
        for(final DropView dropView : dogList) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dropView.startDropAnimator();
                            dropView.setVisibility(View.VISIBLE);
                        }
                    });
                }
            }, random.nextInt(2000));
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tv_fuck_dogs:
                fuckDogs();
                break;
            case R.id.click_ball:
                new ClickAnimator(MainActivity.this, click_ball).start();
                break;
            case R.id.tv_newton_ball:
                new FreeFallAnimator(this,click_ball).start();
                break;
            default:
                break;
        }
    }
}
