package com.example.administrator.crisisbreaker;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Switch;

/**
 * Created by Administrator on 2015-11-17.
 */
public class Setting extends Activity{
    public int a;
    Switch sw;
    NotificationManager nm;
    Notification noti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        sw = (Switch)findViewById(R.id.sid);

        nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(getApplicationContext(),0,new Intent(getApplicationContext(),NotiStop.class),PendingIntent.FLAG_CANCEL_CURRENT);
        noti = new Notification.Builder(this)
                .setContentTitle("Gas leak!!!")
                .setContentText("Danger!!")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Danger")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();

        noti.defaults |= Notification.DEFAULT_ALL;
        noti.flags |= Notification.FLAG_INSISTENT;

        if (sw.isChecked()){
            a = 1;
        } else{
            a = 0;
        }
    }
    public Setting sett = new Setting();
}
