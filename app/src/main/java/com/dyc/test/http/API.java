package com.dyc.test.http;


import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dyc.test.App;
import com.dyc.test.base.BaseEntity;
import com.dyc.test.constants.Constant;
import com.dyc.test.entity.Post;
import com.dyc.test.entity.PostEntity;
import com.dyc.test.tools.CommonUtils;
import com.dyc.test.tools.L;
import com.dyc.test.tools.NetWorkUtils;
import com.dyc.test.tools.PreferencesUtils;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class API {
    private static final String TAG = "API";
    /**
     * 请求成功
     */
    public static final int REQUEST_SUCCESS = 1;
    /**
     * 请求失败
     */
    public static final int REQUEST_FAIL = 2;
    /**
     * 请求开始
     */
    public static final int REQUEST_BEGIN = 3;
    /**
     * 没有网络
     */
    public static final int REQUEST_NO_NETWORK = 4;



    public static void getblogList(Message message) {
        java.lang.reflect.Type type = new TypeToken<PostEntity>(){}.getType();
        HashMap maps=new HashMap();
        maps.put("json",1);
        excuteHttpGet(message,"getblogList",type,maps);

    }

    /**
     * Get方法
     *
     * @param tag
     */
    private static void excuteHttpGet(Message msg, final String tag,
                                      java.lang.reflect.Type type, HashMap<String, Object> param) {
        String url =encodeParams(param);
        excuteHttp(msg, tag, Request.Method.GET, type, url, null);
    }

    private static void excuteHttpPost(Message msg, final String tag,
                                       java.lang.reflect.Type type, HashMap<String, String> hashMap) {
        String url = Constant.BASEURL;
        excuteHttp(msg, tag, Request.Method.POST, type, url, hashMap);
    }

    /**
     * @param msg
     * @param url
     * @param tag
     * @param method
     * @param type
     */
    public static <T> void excuteHttp(final Message msg, final String tag,
                                      int method, java.lang.reflect.Type type, String url,
                                      final HashMap<String, String> postData) {

        L.v(TAG, tag, url);
        L.v(TAG, tag, null == postData ? "null" : postData.toString());

        if (!NetWorkUtils.isNetworkAvailable(App.sContext,true)) {
            final Handler handler = msg.getTarget();
            Message message = new Message();
            message.what = REQUEST_NO_NETWORK;
            message.arg1 = msg.arg1;
            message.setData(msg.getData());
            handler.sendMessage(message);
            return;
        }

        final Handler handler = msg.getTarget();
        Message message = new Message();
        message.what = REQUEST_BEGIN;
        message.arg1 = msg.arg1;
        message.setData(msg.getData());
        handler.sendMessage(message);
        JsonRequest<T> request = new JsonRequest<T>(type, method, url, handler,
                new Response.Listener<T>() {
                    @Override
                    public void onResponse(final T arg0) {
                        L.v(TAG, tag, arg0.toString());
                        L.v(TAG, tag, (arg0 instanceof BaseEntity));
                        int intnum = REQUEST_FAIL;
                        if (null != arg0 && (arg0 instanceof BaseEntity)) {
                            if (("1".equals(((BaseEntity) arg0).status)
                                    || "0".equals(((BaseEntity) arg0).status) || "ok"
                                    .equals(((BaseEntity) arg0).status))
                                    && !"-5".equals(((BaseEntity) arg0).status)) {
                                intnum = REQUEST_SUCCESS;
                            }
                        }
                        Message message = msg;
                        message.obj = arg0;
                        message.what = intnum;
                        // 暂时没用
                        message.arg2 = tag.hashCode();
                        // 发送给对应handler
                        handler.sendMessage(message);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError arg0) {
                L.v(TAG, "onErrorResponse", arg0.getMessage());
                Message message = msg;
                message.obj = arg0;
                message.what = REQUEST_FAIL;
                handler.sendMessage(message);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return postData;
            }

//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                // Map<String, String> headers = super.getHeaders();
                // if (null == headers) {
//                Map<String, String> headers = new HashMap<String, String>();
//                // headers.put("Accept-Encoding", "zip");
//                if (!TextUtils.isEmpty(PreferencesUtils
//                        .getString(Constant.huaxiaUserCookies))) {
//                    headers.put("cookieid", PreferencesUtils
//                            .getString(Constant.huaxiaUserCookies));
//                }
//                headers.put("User-Agent", System.getProperty("http.agent"));
//                return headers;
//            }

        };
        // request.setRetryPolicy(new DefaultRetryPolicy(60 * 1000, 1, 1.0f));
        RequestManager.addRequest(request, tag);
    }

    public static String encodeParams(HashMap<String, Object> maps) {
        L.v(TAG, "encodeParams", maps.toString());
        String url = Constant.BASEURL;
        if (null != maps && maps.size() > 0) {
            Iterator<String> iter = maps.keySet().iterator();

            while (iter.hasNext()) {
                String key = iter.next();
                String value = String.valueOf(maps.get(key));
                if (!TextUtils.isEmpty(value) && !TextUtils.isEmpty(key))
                    url += key + "=" + Uri.encode(value) + "&";
            }
            if (!TextUtils.isEmpty(url) && url.endsWith("&")) {
                url = url.substring(0, url.lastIndexOf("&"));
            }
        }
        return url;

    }

    /**
     * 拼接参数
     *
     * @param function 功能
     * @param params   参数数组
     * @return
     */
    private static HashMap<String, String> buildParam(String function,
                                                      String... params) {
        HashMap<String, String> hm = new HashMap<String, String>();

        return hm;
    }

}