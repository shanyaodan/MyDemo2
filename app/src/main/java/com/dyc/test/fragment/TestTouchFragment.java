package com.dyc.test.fragment;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dyc.test.R;
import com.dyc.test.base.BaseFragment;

/**
 * Created by win7 on 2016/8/1.
 */
public class TestTouchFragment extends BaseFragment {

    private ViewGroup rootViewGroup;
    @Override
    public void initViews(ViewGroup rootView) {
        rootViewGroup = (ViewGroup) rootView.findViewById(R.id.rootview);
        rootViewGroup.addView(new TouchView(context));
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_touchtest;
    }

    @Override
    public void setViews(View rootView) {

    }

    private class TouchView extends LinearLayout{


        public TouchView(Context context) {
            super(context);
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            System.out.println("onInterceptTouchEventonInterceptTouchEvent");
            return super.onInterceptTouchEvent(ev);
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent ev) {
            System.out.println("dispatchTouchEventdispatchTouchEventdispatchTouchEvent");

            return super.dispatchTouchEvent(ev);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            System.out.println("onTouchEvent");

            return super.onTouchEvent(event);
        }
    }
}
