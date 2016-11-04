package com.dyc.test.http;

import java.lang.reflect.Type;
import java.util.HashMap;

import android.os.Handler;
import android.text.TextUtils;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.dyc.test.base.BaseEntity;
import com.dyc.test.tools.L;
import com.google.gson.Gson;


/**
 * Wrapper for Volley requests to facilitate parsing of json responses.
 * 
 * @param <T>
 */
public class JsonRequest<T> extends Request<T> {

	private final String TAG ="JsonRequest";
	private final Listener<T> mListener;
	private final ErrorListener mErrorListener;
	private Type type;
	@SuppressWarnings("unused")
	private Handler mHandler;

	public JsonRequest(Type type, int method, String url, Handler mHandler,
			Listener<T> listener, ErrorListener errorListener) {

		super(method, url, errorListener);
		this.mListener = listener;
		this.mErrorListener = errorListener;
		this.type = type;
		this.mHandler = mHandler;
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		String json = null;
		try {
			L.v(TAG, "parseNetworkResponse", "have get datas");

			json = new String(response.data,HttpHeaderParser.parseCharset(response.headers));

			System.out.println("json.toString()json.toString():"+json);
//			System.out.println("arg0.toString()"+json.toString().substring(0, json.toString().length()/4));
//			System.out.println("arg0.toString()"+json.toString().substring(0, json.toString().length()/4));
//			System.out.println("arg0.toString()"+json.toString().substring(json.toString().length()/4,json.toString().length()*2/4 ));
//			System.out.println("arg0.toString()"+json.toString().substring(json.toString().length()*2/4,json.toString().length()*3/4 ));
//			System.out.println("arg0.toString()"+json.toString().substring(json.toString().length()*3/4,json.toString().length() ));
			Gson gson = new Gson();
			final BaseEntity entity = gson.fromJson(json, type);
			L.v(TAG, type.toString(), entity.toString());
			return (Response<T>) Response.success(entity,HttpHeaderParser.parseCacheHeaders(response));
		} catch (Exception e) {
			L.v(TAG, "parseNetworkResponse", "load fail ");
			e.printStackTrace();
			if (!TextUtils.isEmpty(json)) {
				e = new VolleyError(json);
			}
			return Response.error(new ParseError(e));
		}
	}

	@Override
	protected void deliverResponse(T response) {
		L.v(TAG, "parseNetworkResponse", "deliverResponse");
		if (null != mListener && null != response) {
			mListener.onResponse(response);
		}
	}
	
	@Override
	public void deliverError(VolleyError error) {
		L.v(TAG, "parseNetworkResponse", "load fail ");
		if (null != mErrorListener && null != error) {
			mErrorListener.onErrorResponse(error);
		}
	}
	
	/**
	 * 拼接参数
	 * 
	 * @param function
	 *            功能
	 * @param params
	 *            参数数组
	 * @return
	 */
	public HashMap<String, Object> buildParam(String function, String... params) {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("'");
		for (int i = 0; i < params.length; i++) {
			if (i != params.length - 1) {
				stringBuffer.append(params[i] + ",");
			} else {
				stringBuffer.append(params[i]);
			}
		}

		stringBuffer.append("'");
		hm.put("postType", "exec");
		hm.put("handler", "ai_perm");
		hm.put("data",
				"{fucid:'" + function.trim() + "',data:"
						+ stringBuffer.toString() + "}");
		hm.put("result", "0");
		hm.put("debug", "0");
		return hm;
	}
	

}
