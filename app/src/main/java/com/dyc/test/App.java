package com.dyc.test;

import android.app.Application;
import android.content.Context;

import com.dyc.test.http.RequestManager;


public class App extends Application {

	public static Context sContext;

	@Override
	public void onCreate() {
		super.onCreate();
		sContext = getApplicationContext();
		RequestManager.init();
	}

	public static Context getContext() {
		return sContext;
	}

}
