package com.dyc.test.base;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.dyc.test.R;

import java.util.HashMap;

public abstract class BaseActivity extends AppCompatActivity {

    public String TAG = ((Object) this).getClass().getSimpleName();

    private View waitinglayout, errorlayout;
    // private GifView loadinggif;
    private HashMap<String, Integer> watinglayoutMap = new HashMap<String, Integer>();
    private int waitingcount;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        watinglayoutMap.clear();
        // 支持透明状态栏和透明导航栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
//		MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
//		MobclickAgent.onPause(this);
    }

    /*************************************** loading 界面控制 ***************************************/
    /*************************************** 此功能 需要在每个界面嵌入 commloadinglayout.xml ***************************************/
    protected void initView() {
        // 等待进度条
        // 失败提示
        waitinglayout = findViewById(R.id.waitinglayout);
        // loadinggif = (GifView) findViewById(R.id.loading_gif);
        errorlayout = findViewById(R.id.errorlayout);
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
    public synchronized void showWaiting(String info) {
        waitingcount+=1;
        waitinglayout.setVisibility(View.VISIBLE);
//		if (null != waitinglayout) {
//			int count = 0;
//			if (null != watinglayoutMap.get(info)) {
//				count = watinglayoutMap.get(info);
//			}
//			watinglayoutMap.put(info, count + 1);
//			waitinglayout.setVisibility(View.VISIBLE);
//		}

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
     * 消除等待
     */
    public synchronized void clearWaiting(String info) {
        waitingcount-=1;
        if(waitingcount <= 0) {
            waitingcount = 0;
            waitinglayout.setVisibility(View.GONE);
        }
//		int count = 0;
//		if (null != watinglayoutMap.get(info)) {
//			count = watinglayoutMap.get(info) - 1;
//		}
//		if (count <= 0) {
//			count = 0;
//		}
//		watinglayoutMap.put(info, count);
//		if (count == 0) {
//			if (null != waitinglayout) {
//				waitinglayout.setVisibility(View.GONE);
//			}
//		}
    }

    public void showErrorLayout(int StringId) {
        if (null != errorlayout) {
            errorlayout.setVisibility(View.VISIBLE);
        }

    }

    public void showErrorLayout() {

    }

    public void dismissErrorLayout() {
        if (null != errorlayout) {
            errorlayout.setVisibility(View.GONE);
        }
    }

    /*************************************** getListData ***************************************/

    public void getListData(int type) {

    }

    /**
     * 结合xlistView 使用
     *
     * @param handler
     * @param type
     *            FragmentXlistViewController type REFRESH ，LOADMORE，LOAD_INIT
     * @param listSign
     *            当前listView标示符，int类型，可以用 currentlistView.getHashCode();
     *
     */
    public void getListData(Handler handler, int type, int listSign) {

    }
}
