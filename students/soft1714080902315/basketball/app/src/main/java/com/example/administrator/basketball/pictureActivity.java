package com.example.administrator.basketball;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class pictureActivity extends ActionBarActivity {
    protected static final int CHANGE_UI=1;
    protected static final int ERROR=2;
    private EditText et_path;
    private ImageView iv;
    private Handler handler=new Handler(){
        public void handleMessage(android.os.Message msg){
            if(msg.what==CHANGE_UI){
                Bitmap bitmap=(Bitmap) msg.obj;
                iv.setImageBitmap(bitmap);
            }else if(msg.what==ERROR){
                Toast.makeText(pictureActivity.this, "显示图片错误",Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        et_path=(EditText) findViewById(R.id.et_path);
        iv=(ImageView) findViewById(R.id.iv);
    }
    public void click(View view){
        final String path=et_path.getText().toString().trim();
        if(TextUtils.isEmpty(path)){
            Toast.makeText(this,"图片路径不能为空",Toast.LENGTH_SHORT).show();
        }else{
            new Thread(){
                private HttpURLConnection conn;
                private Bitmap bitmap;
                public void run(){
                    try{
                        URL url=new URL(path);
                        conn=(HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("GET");
                        conn.setConnectTimeout(5000);
                        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3650.400 QQBrowser/10.4.3341.400");
                        int code=conn.getResponseCode();
                        if(code==200){
                            InputStream is=conn.getInputStream();
                            bitmap= BitmapFactory.decodeStream(is);
                            Message msg=new Message();
                            msg.what=CHANGE_UI;
                            msg.obj=bitmap;
                            handler.sendMessage(msg);
                        }else{
                            Message msg=new Message();
                            msg.what=ERROR;
                            handler.sendMessage(msg);
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                        Message msg=new Message();
                        msg.what=ERROR;
                        handler.sendMessage(msg);
                    }
                }
            }.start();
        }
    }
}
