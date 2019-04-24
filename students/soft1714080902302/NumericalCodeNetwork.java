package com.example.soft1714080902302.second_activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.soft1714080902302.Controller.ConnectionNet;
import com.example.soft1714080902302.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NumericalCodeNetwork extends AppCompatActivity {
    protected static final int CHANGE_UI=1;
    protected static final int ERROR=2;

    private ImageView iv;
    //主线程创建消息处理器
    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg){
            if(msg.what == CHANGE_UI){
                Bitmap bitmap = (Bitmap) msg.obj;
                iv.setImageBitmap(bitmap);
            }else if (msg.what == ERROR){
                Toast.makeText(NumericalCodeNetwork.this,"显示图片错误",Toast.LENGTH_SHORT).show();
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numerical_code_network);
        //获取图片存放位置
        iv = (ImageView) findViewById(R.id.iv);
        //判断网络
        boolean conn = ConnectionNet.isConn(NumericalCodeNetwork.this);
        if (!conn) {
            ConnectionNet.setNetworkMethod(NumericalCodeNetwork.this);
        }
    }
    //网络图片
    public void click(View view){
        //final String path = et.getText().toString().trim();
        final String path = "https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=97349b74ebf81a4c323fe49bb6430b3c/4034970a304e251f0ecdcdf8a786c9177f3e53f3.jpg";
            new Thread() {
                private HttpURLConnection conn;
                private Bitmap bitmap;
                public void run() {
                    try {
                        URL url = new URL(path);
                        conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("GET");
                        conn.setConnectTimeout(5000);
                        conn.setRequestProperty("User-Agent", "Mozilla/4.0(compatible;MSIE 6.0;Windows NT 5.1;" + "SV1;.NET4.0C;.NET4.0E;.NET CLR 2.0.50727;" + ".NET CLR 3.0.4506.2152;.NET CLR 3.5.30729;Shuame)");
                        int code = conn.getResponseCode();
                        if (code == 200) {
                            InputStream is = conn.getInputStream();
                            bitmap = BitmapFactory.decodeStream(is);
                            Message msg = new Message();
                            msg.what = CHANGE_UI;
                            msg.obj= bitmap;
                            handler.sendMessage(msg);
                        }else{
                            Message msg = new Message();
                            msg.what=ERROR;
                            handler.sendMessage(msg);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Message msg = new Message();
                        msg.what = ERROR;
                        handler.sendMessage(msg);
                    }
                }
            }.start();
        }
    }


