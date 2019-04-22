package edu.hzuapps.androidlabs.lsm;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    //    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.oneclick_registration_activity);
//    }
    private EditText et_info;
    private Button btn_sava;
    private Button btn_read;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取布局文件的控件
        et_info=(EditText)findViewById(R.id.et_info);
        btn_sava=(Button)findViewById(R.id.btn_save);
        btn_read=(Button)findViewById(R.id.btn_read);
        btn_sava.setOnClickListener(new ButtonListener());
        btn_read.setOnClickListener(new ButtonListener());
    }
    //定义Button按钮的点击事件
    private class ButtonListener implements View.OnClickListener{
        public void onClick(View V){
            switch(V.getId()){
                case R.id.btn_save:
                    String saveinfo = et_info.getText().toString().trim();
                    FileOutputStream fos;
                    try {
                        //保存数据
                        fos = openFileOutput("data.txt", Context.MODE_APPEND);
                        fos.write(saveinfo.getBytes());
                        fos.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.this,"数据保存成功", 0).show();
                    break;
                case R.id.btn_read:
                    String content="";
                    try{
                        //获取保存的数据
                        FileInputStream fis=openFileInput("data.txt");
                        byte[] buffer=new byte[fis.available()];
                        fis.read(buffer);
                        content = new String(buffer);
                        fis.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.this,"保存的数据是："+content,0).show();
                    break;
                default:break;
            }
        }
    }
}