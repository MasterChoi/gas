package com.example.administrator.crisisbreaker;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2015-11-14.
 */
public class BrTest extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //Toast.makeText(null, "Receive Class", Toast.LENGTH_LONG).show();
        try{
            String action = intent.getAction();
            //if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())){
            if(action.equals("android.intent.action.BOOT_COMPLETED")){
                context.startService(new Intent(context,Commu.class));
            } else if(Intent.ACTION_REBOOT.equals(intent.getAction())){
                context.startService(new Intent(context,Commu.class));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
