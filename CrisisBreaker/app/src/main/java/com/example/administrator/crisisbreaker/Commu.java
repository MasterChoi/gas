package com.example.administrator.crisisbreaker;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by Administrator on 2015-11-11.
 */
public class Commu extends Service {
    Socket socket;
    BufferedReader socket_in;
    String data;
    NotificationManager nm;
    Notification noti;
    Setting se;
    byte th=0;
    static final int NOTI_ID = 101;
    public IBinder onBind(Intent intent){
        return null;
    }

    public void onDestroy(){
        super.onDestroy();
        try{
            Log.d("Socket close", "Socket close");
            try{
                OutputStreamWriter osw = new OutputStreamWriter(openFileOutput("thread.txt", MODE_PRIVATE));
                th=0;
                osw.write(th);
                osw.close();
            }catch (IOException e){}
            socket.close();
            socket_in.close();
        }catch (IOException e){}
    }

    public void onCreate() {
        Log.d("onCreated", "onCreated");
        super.onCreate();
        //try {Thread.sleep(8000);} catch (Exception e) {}

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

        try{
            OutputStreamWriter osw = new OutputStreamWriter(openFileOutput("thread.txt", MODE_PRIVATE));
            osw.write(th);
            osw.close();
        }catch (IOException e){}
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader (openFileInput("thread.txt")));
            if(br.read()==0)
                worker.start();
        }catch (IOException e){}
    }
    Thread worker = new Thread() {
        public void run() {
            th = 1;
            while(true) {
                try {
                    Log.d("before create socket", "before create socket");
                    socket = new Socket("52.69.56.103", 5555);
                    Log.d("after create socket", "after create socket");
                    socket_in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    Log.d("Stream creaed", "Stream creaed");
                    //while (true) {
                        Log.d("readLind ", "readLind");
                        try {
                            data = socket_in.readLine();
                            if (data.equals("A")) {
                                Log.d("good", "good and end A");
                                //Setting se = new Setting();
                                //se.nm.notify(NOTI_ID, noti);
                                nm.notify(NOTI_ID,noti);
                            } else if (data.equals("B")) {
                                Log.d("good", "good and end B");
                            }
                        }catch (SocketException e){}
                        catch (Exception e) {
                            e.printStackTrace();
                            Log.d("Inner Ex", "Inner Ex");
                            break;
                        }
                        Log.d("catch : ", "" + data);
                         socket.close();
                        socket_in.close();
                        try {Thread.sleep(1000);} catch (Exception e) {}
                    //}
                    Log.d("good", "Out of while");
                } catch (Exception e) {
                    Log.d("Outter Ex", "Outter Ex");
                    e.printStackTrace();
                }
            }
        }
    };
}
