package com.project.linkinassistant.widget;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * Created by lzm on 2017/8/18.
 */
public class Animations {

    private final TranslateAnimation mToRight;
    private final TranslateAnimation mToLeft;

    //补间动画，view向右划出界面
    public Animations() {
        mToRight = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1,
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
        mToRight.setDuration(500);

        mToLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, -1,
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
        mToLeft.setDuration(500);
    }

    public void start() {

//        view.startAnimation(mToRight);
        mToRight.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // 动画结束在执行删除
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


}