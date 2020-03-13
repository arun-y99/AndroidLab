package com.example.ex6;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.widget.Toast;
public class smsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try{
            if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
                Object[] pdus=(Object[]) intent.getExtras().get("pdus");
                SmsMessage[] messages=new SmsMessage[pdus.length];
                String msg="";
                for(int i=0;i<pdus.length;i++){
                    messages[i]=SmsMessage.createFromPdu((byte[])pdus[i]);
                    msg+=messages[i].getMessageBody();
                }
                String sender=messages[0].getOriginatingAddress();
                Toast.makeText(context, sender+" : "+msg, Toast.LENGTH_SHORT).show();
                notification(context,intent,sender,msg);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @SuppressLint("NewApi")
    public void notification(Context context, Intent intent, String title, String message){
        PendingIntent pd=PendingIntent.getActivity(context,0,intent,0);
        Notification n  = new Notification.Builder(context,"hello")
                .setContentTitle("New mail from " + "test@gmail.com")
                .setContentText("Subject")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentIntent(pd)
                .setAutoCancel(true)
                .addAction(R.mipmap.ic_launcher_round, "Call", pd)
                .addAction(R.mipmap.ic_launcher_round, "More", pd)
                .addAction(R.mipmap.ic_launcher_round, "And more", pd).build();

    }
}
