package com.dyc.test.biz;

/**
 * 
 *  xlistView控制类 方法说明
 *  </br>
 *  addArray(ArrayList list)添加arraylist数据 
 *   </br>
 *	refreshArrayData 重新加载数据
 *  </br>
 *   getLoadState查看当前加载状况
 *  </br>
 *  notifyDatas刷新列表 用 
 *   </br>
 * clearList清理数据 
 *   </br>
 * getXlistView获取当前listView对象
 *    </br>
 * 初始化对象需要new FragmentXlistViewController并传入对于参数
 * 
 */

import java.util.ArrayList;

import android.os.Handler;
import android.text.TextUtils;

import com.dyc.test.base.BaseAdapter;
import com.dyc.test.base.BaseFragment;
import com.dyc.test.constants.Constant;
import com.dyc.test.tools.CommonUtils;
import com.dyc.test.tools.PreferencesUtils;
import com.dyc.test.xlistView.XListView;


public class FragmentXlistViewController implements XListView.IXListViewListener {
	/**
	 * 刷新
	 */
	public static final int REFRESH = 0x14;
	/**
	 * 加载更多
	 */
	public static final int LOADMORE = 0x15;

	/**
	 * 初始加载
	 */
	public static final int LOAD_INIT = 0x16;

	private ArrayList mArrayList;

	private XListView mXListView;

	private boolean isLoading;

	private BaseAdapter mBaseAdapter;

	private BaseFragment fragment;
	private final String timesubfix = "freshtime";
	private int listSign = -1;
	private Handler mHandler;

	/**
	 * 
	 * 
	 * @param fragment
	 * @param mBaseAdapter
	 * @param mXlistView
	 * @param sign
	 *            当前listView标示符，int类型，可以用 currentlistView.getHashCode();
	 * @param mHandler
	 */
	public FragmentXlistViewController(BaseFragment fragment,
			BaseAdapter mBaseAdapter, XListView mXlistView, int sign,
			Handler mHandler) {
		this.mHandler = mHandler;
		this.listSign = sign;
		this.mXListView = mXlistView;
		this.fragment = fragment;
		this.mBaseAdapter = mBaseAdapter;
		mBaseAdapter.getList();
		if (null != mBaseAdapter) {
			mXListView.setAdapter(mBaseAdapter);
		}
		mArrayList = (ArrayList) mBaseAdapter.getList();
		mXListView.setXListViewListener(this);
	}

	@Override
	public void onLoadMore() {
		if (isLoading)
			return;
		if (null != fragment) {
			isLoading = true;
			fragment.getListData(mHandler, LOADMORE, listSign);
		} else {
			stopLoad(fragment.hashCode() + timesubfix);
		}
	}

	@Override
	// 回调函数自己调用
	public void onRefresh() {
		if (isLoading)
			return;
		if (null != fragment) {
			isLoading = true;
			fragment.getListData(mHandler, REFRESH, listSign);
		}

	}

	public void setLoadState(boolean b) {
		isLoading = b;
	}

	/**
	 *
	 * @param sign
	 *            acton
	 */
	public void stopLoad(String sign) {
		mXListView.stopLoadMore();
		mXListView.stopRefresh();
		isLoading = false;
		if (!TextUtils.isEmpty(sign)) {
			String time = PreferencesUtils.getString(sign + timesubfix);
			if (TextUtils.isEmpty(time)) {
				mXListView.setRefreshTime("刚刚");
			} else {
				mXListView.setRefreshTime(time);
			}
			PreferencesUtils.putString(sign + timesubfix,
					CommonUtils.getFormatTime(Constant.FORMAT_E));
		}
	}

	public boolean getLoadState() {
		return isLoading;
	}

	/**
	 * 追加数据
	 * 
	 * @param list
	 */
	public void addArray(ArrayList list) {
		if (null != mArrayList && null != list && list.size() > 0) {
			mArrayList.addAll(list);
		}
		mBaseAdapter.notifyDataSetChanged();
	}

	/**
	 * 清理数据
	 */
	public void clearList() {
		if (null != mArrayList && null != mBaseAdapter) {
			mArrayList.clear();
			mBaseAdapter.notifyDataSetChanged();
		}
	}

	/**
	 * 刷新列表
	 * 
	 * @param list
	 */
	public void refreshArrayData(ArrayList list) {
		if (null == mArrayList) {
			mArrayList = new ArrayList();
		}

		mArrayList.clear();
		mArrayList.addAll(list);
		mBaseAdapter.notifyDataSetChanged();
	}

	public XListView getXlistView() {
		return mXListView;
	}

	public void notifyDatas() {
		if (null != mBaseAdapter)
			mBaseAdapter.notifyDataSetChanged();
	}

}
