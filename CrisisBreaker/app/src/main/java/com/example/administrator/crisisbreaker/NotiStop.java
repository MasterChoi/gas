package com.example.administrator.crisisbreaker;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by Administrator on 2015-11-16.
 */
public class NotiStop extends Activity{
    NotificationManager nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nm.cancel(Commu.NOTI_ID);
    }
}
