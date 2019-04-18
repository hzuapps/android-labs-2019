package edu.hzuapps.androidlabs.soft1714080902114;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity {
        private EditText et_info;
        private Button btn_save;
        private Button btn_read;

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);
            et_info=(EditText) findViewById(R.id.et_info);
            btn_save=(Button) findViewById(R.id.btn_save);
            btn_read=(Button) findViewById(R.id.btn_read);
            btn_save.setOnClickListener(new ButtonListener());
            btn_read.setOnClickListener(new ButtonListener());
        }
        private class ButtonListener implements View.OnClickListener {
            @SuppressLint("WrongConstant")
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.btn_save:
                        String saveinfo=et_info.getText().toString().trim();
                        FileOutputStream fos;
                        try{
                            fos=openFileOutput("data.txt", Context.MODE_APPEND);
                            fos.write(saveinfo.getBytes());
                            fos.close();
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(Main2Activity.this,"笔记保存成功",0).show();
                        break;
                    case R.id.btn_read:
                        String content="";
                        try{
                            FileInputStream fis=openFileInput("data.txt");
                            byte[] buffer=new byte[((FileInputStream) fis).available()];
                            fis.read(buffer);
                            content=new String(buffer);
                            fis.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(Main2Activity.this,"保存的笔记是："+content,0).show();
                        break;
                    default:
                        break;
                }
            }
        }


}


