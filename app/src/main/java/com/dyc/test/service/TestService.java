package com.dyc.test.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.widget.Toast;

import com.dyc.test.entity.IMyTest;
import com.dyc.test.entity.TestEntity;
import com.dyc.test.tools.L;


public class TestService extends Service {
    private static final String TAG = "TestService";
    private Sub sub;

    public TestService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sub = new Sub();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getBaseContext(), "startService by startService()", Toast.LENGTH_SHORT).show();
        return Service.START_REDELIVER_INTENT;

        /*START_NOT_STICKY：当Service因为内存不足而被系统kill后，接下来未来的某个时间内，即使系统内存足够可用，系统也不会尝试重新创建此Service。除非程序中Client明确再次调用startService(...)启动此Service。

        START_STICKY：当Service因为内存不足而被系统kill后，接下来未来的某个时间内，当系统内存足够可用的情况下，系统将会尝试重新创建此Service，一旦创建成功后将回调onStartCommand(...)方法，但其中的Intent将是null，pendingintent除外。

        START_REDELIVER_INTENT：与START_STICKY唯一不同的是，回调onStartCommand(...)方法时，其中的Intent将是非空，将是最后一次调用startService(...)中的intent。

        START_STICKY_COMPATIBILITY：compatibility version of {@link #START_STICKY} that does not guarantee that {@link #onStartCommand} will be called again after being killed。此值一般不会使用，所以注意前面三种情形就好。

        以上的描述中，”当Service因为内存不足而被系统kill后“一定要非常注意，因为此函数的返回值设定只是针对此种情况才有意义的，换言之，当认为的kill掉Service进程，此函数返回值无论怎么设定，接下来未来的某个时间内，即使系统内存足够可用，Service也不会重启。

        小米手机针对此处做了变更：

        另外，需要注意的是，小米手机针对此处做了一定的修改。在“自启动管理”中有一个自启动应用列表，默认情况下，只有少应用（如微信、QQ、YY、360等）默认是可以自启动的，其他应用默认都是禁止的。用户可以手动添加自启动应用，添加后的应用中如果Started Service onStartCommand(...)回调返回值是START_STICKY或START_REDELIVER_INTENT，当用户在小米手机上长按Home键结束App后，接下来未来的某个时间内，当系统内存足够可用时，Service依然可以按照上述规定重启。当然，如果用户在 设置 >> 应用 >> 强制kill掉App进程，此时Service是不会重启的。
        */
    }


    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Toast.makeText(getBaseContext(), "onRebind started", Toast.LENGTH_LONG).show();

    }


    @Override
    public IBinder onBind(Intent intent) {
        return sub;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(getBaseContext(), "onUnbind success", Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);
    }

    public class Sub extends IMyTest.Stub {
        @Override
        public String getdata(final TestEntity ent) throws RemoteException {
            L.v(TAG, "has revice:" + ent.name);
            L.v(TAG, "hasr evice:" + ent.value);
            try {

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(), "receve main data" + ent.name, Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {
            }
            return ent.name;
        }
    }

    ;

}
