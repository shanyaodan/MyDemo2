package com.dyc.test.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dyc.test.R;
import com.dyc.test.base.BaseFragment;

/**
 * Created by win7 on 2016/11/4.
 */

public class ViewpageItemFragment extends Fragment {
    private TextView textview;
    private String text;
    private Button clickbut;

    public void initViews(ViewGroup rootView) {
         textview = (TextView) rootView.findViewById(R.id.testtext);
        clickbut = (Button) rootView.findViewById(R.id.canclebut);


    }

//    @Override
//    public int getLayoutId() {
//        return R.layout.fragment_viewpage_item;
//    }
//
//    @Override
//    public void setViews(View rootView) {
//
//    }


        @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup gv = (ViewGroup) inflater.inflate(R.layout.fragment_viewpage_item,null);

             initViews(gv);
            textview.setText(text);
             return gv;
    }

    public  void settext(String txt){
        this.text =txt;
//        if(null!=textview){
//            textview.setText(text);
//        } else {
//            System.out.println("null == textview");
//        }
    }
}
