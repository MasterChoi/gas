package com.example.administrator.crisisbreaker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2015-11-19.
 */
public class Location extends Activity {
    Button btn1;
    Button b4;
    TextView tv1;
    String str;
    byte[] arrayValue = null;
    String da;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);
        btn1=(Button)findViewById(R.id.outputNum);
        tv1=(TextView)findViewById(R.id.numText);
        b4 = (Button)findViewById(R.id.button4);
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput("sett.txt")));
            da = br.readLine();
            br.close();
        }catch (IOException e){}
        Toast.makeText(this, da, Toast.LENGTH_LONG).show();
        if(da.equals("강원도 원주시")){
            str="01023328598";
            tv1.setText("참빛원주도시가스 \n 010-2332-8598");
        }
        else if (da.equals("강원도 강릉시")){
            str="01093253855";
            tv1.setText("한국가스안전공사 강원영동지사 \n 010-9325-3855");
        }
        else if(da.equals("경기도 성남시")){
            str="01087619554";
            tv1.setText("대한안전도시가스 \n 010-8761-9554 ");
        }
        else if(da.equals("경기도 수원시")){
            str="01093253855";
            tv1.setText("수원시도시가스공사설비 \n 010-9325-3855");
        }
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("tel:/"+str));
                startActivity(mIntent);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =
                        new Intent(Location.this, Settings.class);
                startActivity(intent);
            }
        });
    }
}
