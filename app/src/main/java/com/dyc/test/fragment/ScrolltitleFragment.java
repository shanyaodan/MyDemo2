package com.dyc.test.fragment;

import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.dyc.test.R;
import com.dyc.test.adapter.ViewPagerAdapter;
import com.dyc.test.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by win7 on 2016/8/13.
 */
public class ScrolltitleFragment extends BaseFragment {
    @Override
    public void initViews(ViewGroup rootView) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.scrolltitle;
    }

    @Override
    public void setViews(View rootView) {
        MyViewPager viewPager = (MyViewPager) rootView.findViewById(R.id.viewpager);
      final HorizontalScrollView sv = (HorizontalScrollView) rootView.findViewById(R.id.scrolltitle);
        final ViewGroup roottitle = (ViewGroup) sv.getChildAt(0);
        ArrayList<View> list = new ArrayList<>();
        list.add(new View(context));
        list.add(new View(context));
        list.add(new View(context));
        list.add(new View(context));
        list.add(new View(context));
        list.add(new View(context));
        list.add(new View(context));
        list.add(new View(context));
        list.add(new View(context));
        list.add(new View(context));
        list.add(new View(context));
        list.add(new View(context));
        list.add(new View(context));
        list.add(new View(context));
        list.add(new View(context));
        list.add(new View(context));
        list.add(new View(context));
        list.add(new View(context));
        list.add(new View(context));
        list.add(new View(context));
        list.add(new View(context));
        list.add(new View(context));
        list.add(new View(context));
        list.add(new View(context));
        list.add(new View(context));
        list.add(new View(context));
        list.add(new View(context));
        viewPager.setAdapter(new ViewPagerAdapter(list, null));

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                System.out.println("onPageScrolled:onPageScrolledonPageScrolled");
            }

            @Override

            public void onPageSelected(int position) {
                int marginLeft = 0;


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    marginLeft = (int) roottitle.getChildAt(position).getX();
                    System.out.println("marginLeft1"+marginLeft);
                } else {
                    for(int a=0;a<position;a++) {
                        marginLeft+=roottitle.getChildAt(a).getWidth ();
                    }
                }

                if(marginLeft+roottitle.getChildAt(position).getWidth()/2>sv.getWidth()/2){
                        sv.scrollTo(marginLeft+roottitle.getChildAt(position).getWidth()/2-sv.getWidth()/2,0);
                }

                for(int a=0;a<roottitle.getChildCount();a++) {
                    roottitle.getChildAt(a).setBackgroundResource(R.color.white);
                }

               roottitle.getChildAt(position).setBackgroundResource(R.color.red);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
