package com.dyc.test.activity;
import com.dyc.test.R;;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewByIds();
		initViewWithData();
	}

	public void findViewByIds() {
		
		
	}

	public void initViewWithData() {
		//initViewWithDatacontent
	}
	
	
	

}
