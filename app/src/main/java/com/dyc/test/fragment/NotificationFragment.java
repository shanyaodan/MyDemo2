package com.dyc.test.fragment;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.View;
import android.view.ViewGroup;

import com.dyc.test.R;
import com.dyc.test.activity.NotifcationTestActivity;
import com.dyc.test.activity.SpecialNotificationActivity;
import com.dyc.test.base.BaseFragment;

/**
 * Created by win7 on 2016/9/12.
 */
public class NotificationFragment extends BaseFragment {
    @Override
    public void initViews(ViewGroup rootView) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.notification_fragment;
    }

    @Override
    public void setViews(View rootView) {
        rootView.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
// Instantiate a Builder object.
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context).setContentTitle("test special notificaiton");
                builder.setContentText("哈哈哈哈").setSmallIcon(R.mipmap.ic_launcher).setAutoCancel(true);
// Creates an Intent for the Activity
                Intent notifyIntent =
                        new Intent(context, SpecialNotificationActivity.class);
// Sets the Activity to start in a new, empty task
                notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
// Creates the PendingIntent
                PendingIntent notifyPendingIntent =
                        PendingIntent.getActivity(
                                context,
                                0,
                                notifyIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );

// Puts the PendingIntent into the notification builder
                builder.setContentIntent(notifyPendingIntent);
// Notifications are issued by sending them to the
// NotificationManager system service.
                NotificationManager mNotificationManager =
                        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
// Builds an anonymous Notification object from the builder, and
// passes it to the NotificationManager
                mNotificationManager.notify(1, builder.build());
            }
        });


        rootView.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent resultIntent = new Intent(context, NotifcationTestActivity.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
// Adds the back stack
                stackBuilder.addParentStack(NotifcationTestActivity.class);
// Adds the Intent to the top of the stack
                stackBuilder.addNextIntent(resultIntent);
// Gets a PendingIntent containing the entire back stack
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(context).setContentTitle("New Message")
                        .setContentText("You've received new messages.")
                        .setSmallIcon(R.mipmap.ic_launcher);
                builder.setContentIntent(resultPendingIntent);
                NotificationManager mNotificationManager =
                        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(1, builder.build());
            }
        });

    }
}
