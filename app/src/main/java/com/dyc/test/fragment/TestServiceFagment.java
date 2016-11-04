package com.dyc.test.fragment;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dyc.test.R;
import com.dyc.test.base.BaseFragment;
import com.dyc.test.entity.IMyTest;
import com.dyc.test.entity.TestEntity;

/**
 * Created by win7 on 2016/7/23.
 */
public class TestServiceFagment extends BaseFragment implements View.OnClickListener {
    private IMyTest mytest;
    private ServiceConnection sconn;

    @Override
    public void initViews(ViewGroup rootView) {
        Button startAidl = (Button) rootView.findViewById(R.id.aidlbut);
        Button unbindBinder = (Button) rootView.findViewById(R.id.unbindBut);
        Button startService = (Button) rootView.findViewById(R.id.startBut);
        Button stopAll = (Button) rootView.findViewById(R.id.stopall);

        startAidl.setOnClickListener(this);
        unbindBinder.setOnClickListener(this);
        startService.setOnClickListener(this);
        stopAll.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.test_service;
    }

    @Override
    public void setViews(View rootView) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.aidlbut:
                sconn = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        Toast.makeText(context, "success commet", Toast.LENGTH_SHORT).show();
                        mytest = IMyTest.Stub.asInterface(iBinder);
                        TestEntity te = new TestEntity();
                        te.name = "zhangsan";
                        try {
                            Toast.makeText(context, "getdata:" + mytest.getdata(te), Toast.LENGTH_SHORT).show();
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onServiceDisconnected(ComponentName componentName) {
                        Toast.makeText(context, "success onServiceDisconnected", Toast.LENGTH_SHORT).show();
                    }
                };
                context.bindService(new Intent("com.dyc.test.binderaidl"), sconn, Service.BIND_AUTO_CREATE);
                break;
            case R.id.unbindBut:
                context.unbindService(sconn);
                break;
         /*   case R.id.bindBut:*/
            case R.id.startBut:
                context.startService(new Intent("com.dyc.test.binderaidl"));
                break;
            case R.id.stopall:
                try {
                    context.unbindService(sconn);
                    context.stopService(new Intent("com.dyc.test.binderaidl"));
                } catch (Exception e) {

                }
                break;
        }


    }
}
