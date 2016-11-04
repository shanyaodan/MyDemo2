package com.dyc.test.http;

/**
 * 此类暂时没用
 * @author Administrator
 *
 * @param <T>
 */

import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;

public abstract class BaseRequest<T> extends Request<T> {

	public BaseRequest(int method, String url, ErrorListener listener) {
		super(method, url, listener);
		// TODO Auto-generated constructor stub
	}

	// public BaseRequest(int method, String url, ErrorListener listener) {
	// super(method, url, listener);
	// // TODO Auto-generated constructor stub
	// }
	//
	// @Override
	// protected Response<T> parseNetworkResponse(NetworkResponse arg0) {
	// // TODO Auto-generated method stub
	// ErrorCode error = JSON.parseObject(json, ErrorCode.class);
	// return null;
	// }

}
