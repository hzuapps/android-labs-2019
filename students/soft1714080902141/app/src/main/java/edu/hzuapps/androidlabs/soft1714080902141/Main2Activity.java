package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Main2Activity extends AppCompatActivity {


    public Button button1;
    public Button button2;
    public Button button3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
            button1=findViewById(R.id.button1);
            button2=findViewById(R.id.button2);
            button3=findViewById(R.id.button3);

//
            button1.setOnClickListener(new View.OnClickListener() {
               @Override
                           public void onClick(View v) {
                    String saveinfo = "菜鸟";
                     FileOutputStream fos;
                    FileInputStream fis;
                     String content = "";

                     try {
                         fos = openFileOutput("data.txt", MODE_PRIVATE);
                         fos.write(saveinfo.getBytes());
                         fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                     }
                     try {

                          fis = openFileInput("data.txt");
                        byte[] buffer = new byte[fis.available()];
                        fis.read(buffer);

                         content = new String(buffer);
                         fis.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                     }
                     Toast.makeText(Main2Activity.this, "保存成功，游戏难度为：" + content , Toast.LENGTH_LONG).show();
                }
            });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String saveinfo = "菜鸡";
                FileOutputStream fos;
                FileInputStream fis;
                String content = "";

                try {
                    fos = openFileOutput("data.txt", MODE_PRIVATE);
                    fos.write(saveinfo.getBytes());
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {

                    fis = openFileInput("data.txt");
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);

                    content = new String(buffer);
                    fis.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                Toast.makeText(Main2Activity.this, "保存成功，游戏难度为：" + content , Toast.LENGTH_LONG).show();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String saveinfo = "菜鸭";
                FileOutputStream fos;
                FileInputStream fis;
                String content = "";

                try {
                    fos = openFileOutput("data.txt", MODE_PRIVATE);
                    fos.write(saveinfo.getBytes());
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {

                    fis = openFileInput("data.txt");
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);

                    content = new String(buffer);
                    fis.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                Toast.makeText(Main2Activity.this, "保存成功，游戏难度为：" + content , Toast.LENGTH_LONG).show();
            }
        });
    }
}
