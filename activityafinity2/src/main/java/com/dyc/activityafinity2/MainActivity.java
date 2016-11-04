package com.dyc.activityafinity2;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("oncsreat66666666666666666666666666666666666666666666666666666");
        findViewById(R.id.startb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(getPackageManager().getLaunchIntentForPackage("com.dyc.activityAfinity"));
                ComponentName cn = new ComponentName("com.dyc.activityAfinity","com.dyc.activityAfinity.ActivityB");
                Intent intnt = new Intent();
                intnt.setComponent(cn);
//                intnt.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                startActivity(intnt);
            }
        });


    }




}
