package com.dyc.test.fragment;

import android.support.design.widget.TabLayout;
import android.view.View;
import android.view.ViewGroup;

import com.dyc.test.R;
import com.dyc.test.base.BaseFragment;

/**
 * Created by win7 on 2016/11/3.
 */

public class TablayoutFragment  extends BaseFragment{

    @Override
    public void initViews(ViewGroup rootView) {
    TabLayout tbl= (TabLayout) rootView.findViewById(R.id.tablayout);

    }

    @Override
    public int getLayoutId() {
        return R.layout.tablayout_framgent ;
    }

    @Override
    public void setViews(View rootView) {

    }
}
