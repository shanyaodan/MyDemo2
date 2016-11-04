package com.dyc.test.fragment;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;

import com.dyc.test.R;
import com.dyc.test.adapter.VolleyAdapter;
import com.dyc.test.base.BaseEntity;
import com.dyc.test.base.BaseFragment;
import com.dyc.test.biz.FragmentXlistViewController;
import com.dyc.test.biz.XListBiz;
import com.dyc.test.entity.Post;
import com.dyc.test.entity.PostEntity;
import com.dyc.test.http.API;

import java.util.ArrayList;


/**
 * Created by win7 on 2016/8/7.
 */
public class TestVolleyFragment extends BaseFragment implements
        XListBiz.LoadedDataListener {
    private XListBiz biz;

    @Override
    public void initViews(ViewGroup rootView) {
        biz = new XListBiz(new VolleyAdapter(context, null), rootView, this, 1);
        biz.setOnDataLoadedListener(this);
        biz.getList(FragmentXlistViewController.LOAD_INIT, getClass()
                .hashCode());
    }

    @Override
    public int getLayoutId() {
        return R.layout.testvolley_fragment;
    }

    @Override
    public void setViews(View rootView) {

    }

    @Override
    public void getListData(Handler handler, int type, int listSign) {
        Message msg = new Message();
        msg.arg1 = type;
        msg.setTarget(handler);

        API.getblogList(msg);
    }


    @Override
    public ArrayList onGetDataEntity(BaseEntity baseEntity) {
        return ((PostEntity) baseEntity).posts;
//        return null;
    }
}
