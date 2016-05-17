package com.example.administrator.crisisbreaker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by Administrator on 2015-11-19.
 */
public class Settings extends Activity implements AdapterView.OnItemSelectedListener{
    public String str;
    Spinner spinner1;
    Button b3;
    String[] items = {"강원도 원주시","강원도 강릉시","경기도 성남시","경기도 수원시"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        b3 = (Button)findViewById(R.id.button3);
        spinner1 = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this, R.array.city, android.R.layout.simple_spinner_dropdown_item);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setOnItemSelectedListener(this);
        spinner1.setAdapter(adapter1);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =
                        new Intent(Settings.this, Location.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onItemSelected(AdapterView<?> pp, View v, int pos, long id){
        str = items[pos];
        try{
            OutputStreamWriter osw = new OutputStreamWriter(openFileOutput("sett.txt", MODE_PRIVATE));
            osw.write(str);
            osw.flush();
            osw.close();
        }catch (IOException e){}
    }


}
