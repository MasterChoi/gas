package com.example.administrator.crisisbreaker;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity extends Activity {
    Intent intent;
    ImageView imageView;
    byte th=0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("CrisisBreaker");
        imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.intro);
        intent = new Intent(this,Commu.class);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
                Intent intent =
                        new Intent(MainActivity.this, main.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onDestroy() {
        Toast.makeText(this, "APP Destroy", Toast.LENGTH_LONG).show();
        super.onDestroy();
        try{
            OutputStreamWriter osw = new OutputStreamWriter(openFileOutput("thread.txt", MODE_PRIVATE));
            th=0;
            osw.write(th);
            osw.close();
        }catch (IOException e){}
    }
}
