package com.dyc.test.fragment;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.dyc.test.R;
import com.dyc.test.base.BaseFragment;
import com.dyc.test.tools.L;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by win7 on 2016/7/19.
 */
public class MessageFragment extends BaseFragment {

    public static  EditText interceptNum;
    @Override
    public void initViews(final ViewGroup rootView) {
        interceptNum = (EditText) rootView.findViewById(R.id.interceptNum);
        rootView.findViewById(R.id.sendButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = (EditText) rootView.findViewById(R.id.send_content);
                EditText phoneText= (EditText) rootView.findViewById(R.id.phoneNum);
                    sendSMS(phoneText.getText().toString(),editText.getText().toString());

          rootView.findViewById(R.id.getGrant).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  System.out.println("################################################################");
                startActivity(getAppDetailSettingIntent(context));
              }
          });
            }
        });
    }
    private Intent getAppDetailSettingIntent(Context context) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", context.getPackageName(),null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings","com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        return localIntent;
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    public void setViews(View rootView) {




    }


    /**
     */
    public void sendSMS(String phoneNumber,String message){

        //处理返回的发送状态
        String SENT_SMS_ACTION = "SENT_SMS_ACTION";
        Intent sentIntent = new Intent(SENT_SMS_ACTION);
        PendingIntent sentPI = PendingIntent.getBroadcast(context, 0, sentIntent,
                0);
// register the Broadcast Receivers
        context.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context _context, Intent _intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(context,
                                "短信正在发送", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        break;
                }
            }
        }, new IntentFilter(SENT_SMS_ACTION));

    //处理返回的接收状态
        String DELIVERED_SMS_ACTION = "DELIVERED_SMS_ACTION";
        // create the deilverIntent parameter
        Intent deliverIntent = new Intent(DELIVERED_SMS_ACTION);
        PendingIntent deliverPI = PendingIntent.getBroadcast(context, 0,
                deliverIntent, 0);
        context.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context _context, Intent _intent) {
                Toast.makeText(context,
                        "短信已经成功发出", Toast.LENGTH_SHORT)
                        .show();
            }
        }, new IntentFilter(DELIVERED_SMS_ACTION));
        //获取短信管理器
        SmsManager smsManager = SmsManager.getDefault();
        //拆分短信内容（手机短信长度限制）
        List<String> divideContents = smsManager.divideMessage(message);
        for (String text : divideContents) {
            smsManager.sendTextMessage(phoneNumber, null, text, sentPI, deliverPI);
        }
    }

    /**
     * 拦截短信 不能用，不知道为什么
     */
    //继承BroadcastReceiver
    public static class AutoSMS extends BroadcastReceiver
    {

        private String TAG="AutSMS";
        //广播消息类型
        public static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
        //覆盖onReceive方法
        @Override
        public void onReceive(Context context, Intent intent)
        {
            // TODO Auto-generated method stub
            L.i("AutoSMS", "引发接收事件");
            //StringBuilder body=new StringBuilder("");//短信内容
            //StringBuilder sender=new StringBuilder("");//发件人
            //先判断广播消息
            String action = intent.getAction();
            if (SMS_RECEIVED_ACTION.equals(action))
            {
                //获取intent参数
                Bundle bundle=intent.getExtras();
                //判断bundle内容
                if (bundle!=null)
                {
                    //取pdus内容,转换为Object[]
                    Object[] pdus=(Object[])bundle.get("pdus");
                    //解析短信
                    SmsMessage[] messages = new SmsMessage[pdus.length];
                    for(int i=0;i<messages.length;i++)
                    {
                        byte[] pdu=(byte[])pdus[i];
                        messages[i]=SmsMessage.createFromPdu(pdu);
                    }
                    //解析完内容后分析具体参数
                    for(SmsMessage msg:messages)
                    {
                        //获取短信内容
                        String content=msg.getMessageBody();
                        String sender=msg.getOriginatingAddress();
                        Date date = new Date(msg.getTimestampMillis());
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String sendTime = sdf.format(date);
                        if (interceptNum.getText().toString().equals(sender))
                        {
                            Toast.makeText(context, "收到"+sender+"的短信"+"内容:"+content, Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(context, "收到:"+sender+"内容:"+content+"时间:"+sendTime.toString(), Toast.LENGTH_LONG).show();
                            abortBroadcast();
                        }
                    }

                }
            }//if 判断广播消息结束
        }

    }


}
