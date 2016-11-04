package com.dyc.test.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dyc.test.R;
import com.dyc.test.base.BaseFragment;
import com.dyc.test.tools.L;

/**
        监听开关机，发送广播
 */
public class BroadcastFragment extends BaseFragment implements View.OnClickListener {

    private static final String KEY_TO_Activity = "com.dyc.test.TO_Activity";
    private FirstBroadCast firstBroadcast;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void initViews(ViewGroup rootView) {
        Button firstsend = (Button) rootView.findViewById(R.id.sendfirst);
        IntentFilter inf = new IntentFilter(KEY_TO_Activity);
        firstBroadcast = new FirstBroadCast();
        getContext().registerReceiver(firstBroadcast, inf);
        firstsend.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_broadcast;
    }

    @Override
    public void setViews(View rootView) {

    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        getContext().unregisterReceiver(firstBroadcast);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.sendfirst) {
            getContext().sendBroadcast(new Intent(KEY_TO_Activity));
        }
    }

    class FirstBroadCast extends BroadcastReceiver {
        String TAG = "FirstBroadCast";
        @Override
        public void onReceive(Context context, Intent intent) {
            L.v(TAG, "has recive first class");
            Toast.makeText(context, "show toast has recive your send", Toast.LENGTH_SHORT).show();
        }

        }
    public static class ShutDownReciver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            L.v("ShutDownReciver","recive shutDown ");
        }
    }

    public static  class BootCompleteBroadcastReciver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"recive boot complete",Toast.LENGTH_SHORT).show();;
            L.v("BootCompleteBroadcastReciver", "has recive boot complete ");
        }
    }
}
