package edu.hzuapps.androidlabs.soft1714080902337;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;


public class Soft1714080902337Activity2 extends AppCompatActivity {

    private EditText Text1;
    private EditText Text2;
    private Button btn_save;
    private Button btn_read;
    ///////////////////////////////////////////////////////////////
    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902337_activity2);

        Text1=(EditText) findViewById(R.id.Text1);
        Text2=(EditText) findViewById(R.id.Text2);
        btn_save=(Button)    findViewById(R.id.btn_save);
        btn_read=(Button)    findViewById(R.id.btn_read);
        btn_save.setOnClickListener(new ButtonListener());
        btn_read.setOnClickListener(new ButtonListener());
    }

    private class ButtonListener implements View.OnClickListener{
        @SuppressLint("WrongConstant")
        public void onClick(View v) {
            switch (v.getId()){
                //保存数据部分
                case R.id.btn_save:
                    String text1=Text1.getText().toString().trim();
                    String text2=Text2.getText().toString().trim();
                    FileOutputStream fos;
                    try {
                        fos=openFileOutput("data.txt", Context.MODE_APPEND);
                        fos.write(text2.getBytes());
                        fos.write(text1.getBytes());
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft1714080902337Activity2.this,"文本保存成功",0).show();
                    break;
                //读取数据部分
                case R.id.btn_read:
                    String content="";
                    try{
                        FileInputStream fis=openFileInput("data.txt");
                        byte[] buffer=new byte[fis.available()];
                        fis.read(buffer);
                        content=new String(buffer);
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft1714080902337Activity2.this,"保存的文本是："+content,0).show();
                    break;
                default:
                    break;
            }
        }
    }
    }
