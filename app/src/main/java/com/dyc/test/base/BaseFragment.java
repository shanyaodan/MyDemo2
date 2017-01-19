package com.dyc.test.base;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dyc.test.R;
import com.dyc.test.tools.L;

/**
 * Created by Administrator on 2015/6/26.
 */
public abstract class BaseFragment extends Fragment {




    private String TAG = "BaseFragment";
    protected View waitinglayout, errorlayout, backLayout;

    protected ViewGroup rootView;
    protected Context context;
    private int waitingcount = 0;
    private int errorcount = 0;


    public abstract void initViews(ViewGroup rootView);

    public abstract int getLayoutId();

    public abstract void setViews(View rootView);


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        System.out.println("onAttach");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("onResume");
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = getActivity();
        if (null == rootView) {
            rootView = (ViewGroup) inflater.inflate(getLayoutId(), container,false);
            initViews(rootView);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            if (null != rootView.findViewById(R.id.titleroot)) {
//
//                rootView.findViewById(R.id.titleroot).setPadding(0,
//                        getStatusBarHeight(), 0, 0);
//            }
        }
        System.out.println("onCreateView");
        System.out.println("rootViewrootView=："+(null == rootView));
        setViews(rootView);
        return rootView;
    }



    @Override
    public void onDestroyView() {
        if (rootView != null) {
            ViewGroup parentViewGroup = (ViewGroup) rootView.getParent();
            if (parentViewGroup != null) {
                parentViewGroup.removeAllViews();
            }
            rootView = null;
        }
        super.onDestroyView();
    }



    public static BaseFragment getInstance(FragmentActivity context,
                                           String fragmentName) {

        return getInstance(context, fragmentName, null);
    }

    public static BaseFragment getInstance(FragmentActivity context,
                                           String fragmentName, Bundle bundle) {
        BaseFragment instance = null;
        instance = (BaseFragment) Fragment.instantiate(context, fragmentName,
                bundle);
        instance.context = context;
        return instance;
    }

    /*************************************** loading 界面控制 ***************************************/
    /***************************************
     * 此功能 需要在每个界面嵌入 commloadinglayout.xml
     ***************************************/
    protected void initView() {
        // 等待进度条
        // 失败提示
        waitinglayout = rootView.findViewById(R.id.waitinglayout);
        // loadinggif = (GifView) findViewById(R.id.loading_gif);
        errorlayout = rootView.findViewById(R.id.errorlayout);
//        backLayout = rootView.findViewById(R.id.backlayout);
//        titleText = (TextView) rootView.findViewById(R.id.titletext);
//        if (null != backLayout) {
//            backLayout.setOnClickListener(new OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    com.xingfuhuaxia.app.fragment.FragmentManager
//                            .popFragment(getActivity());
//                }
//            });
//
//        }
    }

    protected void setTitle(String title) {
        if (null != title) {
//            titleText.setText(title);
        }

    }

    protected void setTitle(int titleId) {
//        titleText.setText(titleId);
    }

    protected void showAnimloadingView() {
        // if (null != loadinggif) {
        // loadinggif.startAnim();
        // }
    }

    protected void DismissAnimloadingView() {
        // if (null != loadinggif) {
        // loadinggif.stopAnim();
        // }
    }

    /**
     * 添加actionbar返回按钮
     */
    protected void initActionBar() {

    }


    /**
     * 显示等待
     */
    public void showWaiting() {
        showWaiting("default");

    }

    public void clearWaiting() {
        clearWaiting("default");

    }

    /**
     * 显示等待
     */
    public synchronized void showWaiting(String info) {
        waitingcount += 1;
        if (null != waitinglayout && waitingcount > 0) {
            waitinglayout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 消除等待
     */
    public synchronized void clearWaiting(String info) {
        waitingcount -= 1;
        if (waitingcount <= 0) {
            if (null != waitinglayout) {
                waitinglayout.setVisibility(View.GONE);
            }
        }
    }

    public void showErrorLayout(int StringId) {
        errorcount+=1;
        if (null != errorlayout&&errorcount>0) {
            errorlayout.setVisibility(View.VISIBLE);
        }

    }

    public void dismissErrorLayout() {
        errorcount-=1;
        if (null != errorlayout&&errorcount<=0) {
            errorlayout.setVisibility(View.GONE);
        }
    }

    public void showErrorLayout() {

    }


    /***************************************
     * getListData
     ***************************************/

    public void getListData(int type) {

    }

    /**
     * 结合xlistView 使用
     *
     * @param handler
     * @param type     FragmentXlistViewController type REFRESH ，LOADMORE，LOAD_INIT
     * @param listSign 当前listView标示符，int类型，可以用 currentlistView.getHashCode();
     */
    public void getListData(Handler handler, int type, int listSign) {

    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height",
                "dimen", "android");
        if (resourceId > 0) {
            result = Resources.getSystem().getDimensionPixelSize(resourceId);
        }
        L.v(TAG, "getStatusBarHeight", result);
        return result;
    }

}
