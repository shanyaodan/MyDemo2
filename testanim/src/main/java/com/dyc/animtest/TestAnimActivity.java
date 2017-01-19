package com.dyc.animtest;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.animation.AnimatorCompatHelper;
import android.support.v4.animation.ValueAnimatorCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorInflater;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.PropertyValuesHolder;

/**
 * Created by dyc on 2016/12/9.
 */
public class TestAnimActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View rootView = findViewById(Window.ID_ANDROID_CONTENT);
        final ImageView img = (ImageView) rootView.findViewById(R.id.img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Animator animator= AnimatorInflater.loadAnimator(getApplicationContext(),R.animator.myanim1);
                animator.setTarget(img);
                animator.start();
//                propertyValuesHolder(img);
//                ObjectAnimator.ofFloat(view,"rotationX",(float)0,(float)360).setDuration(1000).start();

//                ObjectAnimator animator = new ObjectAnimator();
//                animator.setPropertyName("rotationX");
//                animator.setFloatValues((float) 0,(float)360);
//                animator.setDuration(1000);
//                animator.setTarget(img);
//                animator.start();
//                com.nineoldandroids.animation.ValueAnimator anim;
//                anim = com.nineoldandroids.animation.ValueAnimator.ofFloat(0.4f, 1f, 0.9f, 1f);
//                anim.setInterpolator(new LinearInterpolator());
//                anim.setDuration(500);
//                anim.setStartDelay(180);
//                anim.addUpdateListener(new com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener() {
//                    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
//                    @Override
//                    public void onAnimationUpdate(com.nineoldandroids.animation.ValueAnimator animation) {
//                     img.setScaleX((float)animation.getAnimatedFraction());
//                     img.setScaleY((float)animation.getAnimatedFraction());
//                    }
//                });
//                anim.start();

            }
        });
    }

    public void propertyValuesHolder(View view)
    {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,
                0f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f,
                0, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f,
                0, 1f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY,pvhZ).setDuration(1000).start();
    }

}
