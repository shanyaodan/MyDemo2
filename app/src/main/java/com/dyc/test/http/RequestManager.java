package com.dyc.test.http;

/*
 * Created by Storm Zhang, Feb 11, 2014.
 */

import android.app.ActivityManager;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.dyc.test.App;
import com.dyc.test.tools.L;


public class RequestManager {

	private static final  String TAG = "RequestManager";
	private static RequestQueue mRequestQueue;
	private static ImageLoader mImageLoader;

	private RequestManager() {
		// no instances
	}

	public static RequestQueue getRequestQueueInstance() {
		if (null == mRequestQueue) {
			synchronized (RequestManager.class) {
				if (null == mRequestQueue) {
					mRequestQueue = Volley.newRequestQueue(App.getContext());
				}
			}

		}
		return mRequestQueue;
	}

	public static ImageLoader initImageLoader() {
		int memClass = ((ActivityManager) App.getContext().getSystemService(
				Context.ACTIVITY_SERVICE)).getMemoryClass();
		// Use 1/8th of the available memory for this memory cache.
		int cacheSize = 1024 * 1024 * memClass / 8;
		return mImageLoader = new ImageLoader(mRequestQueue,
				new BitmapLruCache(cacheSize));
	}

	public static void init() {
		getRequestQueueInstance();
		initImageLoader();

	}

	public static RequestQueue getRequestQueue() {
		if (mRequestQueue != null) {
			return mRequestQueue;
		} else {
			throw new IllegalStateException("RequestQueue not initialized");
		}
	}

	public static void addRequest(Request<?> request, Object tag) {
		if (tag != null) {
			request.setTag(tag);
		}
		if (null == mRequestQueue) {
			init();
		}
		mRequestQueue.add(request);
	}

	public static void cancelAll(Object tag) {
		if (null != mRequestQueue)
			mRequestQueue.cancelAll(tag);
	}

	/**
	 * Returns instance of ImageLoader initialized with {@see FakeImageCache}
	 * which effectively means that no memory caching is used. This is useful
	 * for images that you know that will be show only once.
	 * 
	 * @return
	 */
	public static ImageLoader getImageLoader() {
		if (mImageLoader != null) {
			return mImageLoader;
		} else {
			// throw new IllegalStateException("ImageLoader not initialized");
			L.v(TAG, "ImageLoader", "getImageLoader");
			return initImageLoader();
		}
	}
	
}
