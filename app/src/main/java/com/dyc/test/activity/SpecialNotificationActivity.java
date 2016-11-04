package com.dyc.test.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.dyc.test.R;

/**
 * Created by win7 on 2016/9/18.
 */
public class SpecialNotificationActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main);
    }
}
