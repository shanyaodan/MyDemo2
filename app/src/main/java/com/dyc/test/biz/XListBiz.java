package com.dyc.test.biz;

/**
 * listView数据处理类，
 * 使用说明 
 * 1.初始化 biz,传入对应参数
 * 2.(必须)设置监听时间解析数据setOnDataLoadedListener
 * 3.发送请求getList
 */

import java.util.ArrayList;

import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.dyc.test.R;
import com.dyc.test.base.BaseAdapter;
import com.dyc.test.base.BaseEntity;
import com.dyc.test.base.BaseFragment;
import com.dyc.test.http.API;
import com.dyc.test.xlistView.XListView;


public class XListBiz {
	private XListView listView;
	private BaseFragment baseFragment;
	private ViewGroup rootView;
	private View waitinglayout, errorlayout;
	private FragmentXlistViewController mListViewController;
	private int sign;
	private ArrayList mArrayList;
	private LoadedDataListener loadedDataListener;
	Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case API.REQUEST_BEGIN:
				if (msg.arg1 == FragmentXlistViewController.LOAD_INIT) {
//					showAnimloadingView();
					showWaiting();
				} else {
					showWaiting();
				}
				break;

			case API.REQUEST_SUCCESS:
				if (msg.arg1 == FragmentXlistViewController.LOADMORE) {
					setListData((BaseEntity) msg.obj);
				} else if (msg.arg1 == FragmentXlistViewController.REFRESH
						|| msg.arg1 == FragmentXlistViewController.LOAD_INIT) {
					mArrayList.clear();

					setListData((BaseEntity) msg.obj);
				}
				clearWaiting();
//				DismissAnimloadingView();
				break;
			case API.REQUEST_FAIL:
				mListViewController.stopLoad((this.hashCode() + sign) + "");

				clearWaiting();
//				DismissAnimloadingView();
				break;
			case API.REQUEST_NO_NETWORK:
				mListViewController.stopLoad((this.hashCode() + sign) + "");
				clearWaiting();
//				DismissAnimloadingView();
				break;
			default:
				break;
			}
		}
	};

	/**
	 * listView数据处理类
	 * 
	 * @param adapter
	 * @param rootview
	 * @param baseFragment
	 * @param sign
	 *            当前listView标示符，int类型，可以用 currentlistView.getHashCode();
	 */
	public XListBiz(BaseAdapter adapter, ViewGroup rootview,
					BaseFragment baseFragment, int sign) {
		this.rootView = rootview;
		this.listView = (XListView) rootview.findViewById(R.id.xlistview);

		waitinglayout = rootview.findViewById(R.id.waitinglayout);
		// loadinggif = (GifView) rootview.findViewById(R.id.loading_gif);
		errorlayout = rootview.findViewById(R.id.errorlayout);

		this.baseFragment = baseFragment;
		mArrayList = new ArrayList();
		listView.setPullLoadEnable(true);
		listView.setPullRefreshEnable(true);
		adapter.setList(mArrayList);
		mListViewController = new FragmentXlistViewController(baseFragment,
				adapter, listView, sign, mHandler);
		this.sign = sign;
	}

	public void notifyDataSetChange() {
		if (null != mListViewController) {
			mListViewController.notifyDatas();
		}
	}

	/**
	 * 显示等待
	 */
	public void showWaiting() {
		dissmissallLayout();
		if (null != waitinglayout) {
			waitinglayout.setVisibility(View.VISIBLE);
		}

	}

	/*************************************** xlistView load控制 ***************************************/

	/**
	 * 消除等待
	 */
	public void clearWaiting() {

		if (null != waitinglayout) {
			waitinglayout.setVisibility(View.GONE);
		}

	}

	public void showErrorLayout() {
		dissmissallLayout();
		if (null != errorlayout) {
			errorlayout.setVisibility(View.VISIBLE);
		}
	}

	public void dismissErrorLayout() {
		if (null != errorlayout) {
			errorlayout.setVisibility(View.GONE);
		}
	}

//	protected void showAnimloadingView() {
//		dissmissallLayout();
//		if (null != loadinggif) {
//			loadinggif.startAnim();
//		}
//	}

//	protected void DismissAnimloadingView() {
//		if (null != loadinggif) {
//			loadinggif.stopAnim();
//		}
//	}

	private void dissmissallLayout() {
		if (null != waitinglayout) {
			waitinglayout.setVisibility(View.VISIBLE);
		}
		if (null != errorlayout) {
			errorlayout.setVisibility(View.VISIBLE);
		}
//		if (null != loadinggif) {
//			loadinggif.setVisibility(View.VISIBLE);
//		}
	}

	public XListView getListView() {

		return listView;

	}

	/*************************************** setListData ***************************************/

	private void setListData(BaseEntity entity) {
		ArrayList arrayList = loadedDataListener.onGetDataEntity(entity);
		// ArrayList<AskEntity> askItems = null;
		// if (null != entity && null != entity.content) {
		// ContentPaserEntity<ArrayList<AskEntity>> parser =
		// (ContentPaserEntity<ArrayList<AskEntity>>) entity.content;
		// if (null != parser.list) {
		// askItems = parser.list;
		// checkNextPage(parser.pageNo, parser.pageCount);
		// }
		// }
		mListViewController.addArray(arrayList);
		mListViewController.stopLoad((this.hashCode() + sign) + "");
	}

	/**
	 * 
	 * @param type
	 *            FragmentXlistViewController type REFRESH ，LOADMORE，LOAD_INIT
	 * 
	 * @param listSign
	 *            当前listView标示符，int类型，可以用 currentlistView.getHashCode();
	 * 
	 */
	public void getList(int type, int listSign) {

		baseFragment.getListData(mHandler, type, listSign);
	}

	private boolean checkNextPage(String currentPage, String pageCount) {
		if (TextUtils.isEmpty(pageCount)) {
			listView.setPullLoadEnable(false);
			return false;
		}
		if (TextUtils.isEmpty(currentPage)) {
			listView.setPullLoadEnable(false);
			return false;
		}
		if (TextUtils.isDigitsOnly(pageCount)
				&& TextUtils.isDigitsOnly(currentPage)) {
			int current = Integer.parseInt(currentPage);
			int count = Integer.parseInt(pageCount);
			if (current < count) {
				return true;
			}

		}
		listView.setPullLoadEnable(false);

		return false;

	}

	public ArrayList getList() {
		return mArrayList;
	}

	public void setOnDataLoadedListener(LoadedDataListener loadedDataListener) {
		this.loadedDataListener = loadedDataListener;
	}

	public interface LoadedDataListener {
		ArrayList onGetDataEntity(BaseEntity baseEntity);
	}

}
