package edu.hzuapps.androidlabs.com1714080901119;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Com1714080901119Activity2 extends AppCompatActivity {
    private Button b1;
    private Button b2;
    private EditText e1;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.com_1714080901119_activity2);
            setContentView(R.layout.com_1714080901119_activity2);
            TextView denglu = findViewById(R.id.cai2);
            denglu.setOnClickListener (new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Com1714080901119Activity2.this,Com1714080901119Activity3.class));
                }
            });
            b1=(Button) findViewById(R.id.b1);
            b2=(Button) findViewById(R.id.b2);
            e1=(EditText) findViewById(R.id.e1);
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String a1=e1.getText().toString().trim();
                    FileOutputStream c1;
                    try{
                        c1=openFileOutput("data.txt", Context.MODE_APPEND);
                        c1.write(a1.getBytes());
                        c1.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(Com1714080901119Activity2.this, "数据保存成功！", Toast.LENGTH_SHORT).show();
                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String wen="";
                    try{
                        FileInputStream c2=openFileInput("data.txt");
                        byte[] buffer=new byte[c2.available()];
                        c2.read(buffer);
                        wen=new String(buffer);
                        c2.close();
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(Com1714080901119Activity2.this, "Ta："+wen, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }


