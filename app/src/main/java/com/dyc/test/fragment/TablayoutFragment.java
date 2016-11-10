package com.dyc.test.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dyc.test.R;
import com.dyc.test.adapter.FragmentAdapter;
import com.dyc.test.adapter.ViewPagerAdapter;
import com.dyc.test.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by win7 on 2016/11/3.
 */

public class TablayoutFragment  extends BaseFragment{
    private String[] titles = new String[]{"推荐", "热点", "赣州", "社会","订阅", "娱乐", "科技", "汽车","体育", "财经", "美女"};
    @Override
    public void initViews(ViewGroup rootView) {
      final TabLayout tbl= (TabLayout) rootView.findViewById(R.id.tablayout);
        ViewPager vp = (ViewPager) rootView.findViewById(R.id.viewpager);
        ArrayList<Fragment> fragments = new ArrayList<>();
        final ArrayList<String> datas = new ArrayList<>();
        Button tabbut = (Button) rootView.findViewById(R.id.button);
        for(int a=0;a<titles.length;a++) {
           String dataitem = titles[a];
            datas.add(dataitem);
            ViewpageItemFragment item = new ViewpageItemFragment();
            item.settext(titles[a]);
           fragments.add(item);
        }
        final FragmentAdapter adap= new FragmentAdapter(getChildFragmentManager(),titles,datas,fragments);
        vp.setAdapter(adap);
        tbl.setupWithViewPager(vp,true);
        tabbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int num =    tbl.getSelectedTabPosition();
                datas.clear();
                for(int a=0;a<titles.length;a++) {
                    datas.add(titles[num]);
                }
                adap.notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.tablayout_framgent ;
    }

    @Override
    public void setViews(View rootView) {

    }
}
