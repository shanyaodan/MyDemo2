package com.dyc.test.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.print.PrintHelper;
import android.view.View;
import android.view.ViewGroup;

import com.dyc.test.R;
import com.dyc.test.base.BaseFragment;

/**
 * Created by win7 on 2016/7/22.
 */
public class TestFramgent extends BaseFragment {
    int num = 0;
    @Override
    public void initViews(ViewGroup rootView) {

        
        rootView.findViewById(R.id.createNew).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("key","fragment"+(num++));
                BlackFragment bf = (BlackFragment) BaseFragment.getInstance((FragmentActivity) context,BlackFragment.class.getName(),bundle);
                com.dyc.test.tools.FragmentManager.setFragmentWithStrName(TestFramgent.this,bf);
            }
        });
        rootView.findViewById(R.id.popAll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        com.dyc.test.tools.FragmentManager.clearFragementStack(TestFramgent.this);


            }
        });



    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_test;
    }

    @Override
    public void setViews(View rootView) {

    }

}
