package edu.hzuapps.androidlabs.com1714080901132;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class back extends AppCompatActivity {
    protected static final int CHANGE_UI = 1;
    protected static final int ERROR = 2;
    private ImageView iv;
    private Button button1;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == CHANGE_UI) {
                Bitmap bitmap = (Bitmap) msg.obj;
                iv.setImageBitmap(bitmap);
            } else if (msg.what == ERROR) {
                Toast.makeText(back.this, "显示图片错误", Toast.LENGTH_SHORT).show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);
        iv = (ImageView) findViewById(R.id.iv);
        button1 = (Button) findViewById(R.id.enliu);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(edu.hzuapps.androidlabs.com1714080901132.back.this, second.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                startActivity(intent);

            }
        });
    }

    public void click (View view){
        final String p_url[]={"https://img.zcool.cn/community/0110945730387f32f8755a5245f85a.jpg@1280w_1l_2o_100sh.png","https://img.zcool.cn/community/0157955848b69ea801219c77902534.jpg@1280w_1l_2o_100sh.png","https://img.zcool.cn/community/016a065625c70232f87557019e87f6.jpg@1280w_1l_2o_100sh.jpg"};
        if (TextUtils.isEmpty(p_url[0])){
            Toast.makeText(this, "图片路径不能为空", Toast.LENGTH_SHORT).show();
        } else {
            //子线程请求网络，Android 4.0以后访问网络不能放在主线程中
            new Thread() {
                private HttpURLConnection conn;
                private Bitmap bitmap;

                public void run() {
                    //连接服务器get请求，获取图片
                    try {
                        //0-2的随机数
                        int rand=(int)(Math.random()*3);
                        //创建URL对象
                        URL url = new URL(p_url[rand]);
                        //根据url发送http的请求
                        conn = (HttpURLConnection) url.openConnection();
                        //设置请求的方式
                        conn.setRequestMethod("GET");
                        //设置超时时间
                        conn.setConnectTimeout(5000);
                        //设置请求头User-Agent浏览器的版本
                        conn.setRequestProperty("User-Agent", "Mozilla/4.0(compatible;MSIE 6.0;Windows NT 5.1;" + "SV1;.NET4.0C;.NET4.0E;.NET CLR 2.0.50727;" + ".NET CLR 3.0.4506.2152;.NET CLR 3.5.30729;Shuame)");
                        //得到服务器返回的响应码
                        int code = conn.getResponseCode();
                        //请求网络成功后返回码是200
                        if (code == 200) {
                            //获取输入流
                            InputStream is = conn.getInputStream();
                            //将流转换成Bitmap对象
                            bitmap = BitmapFactory.decodeStream(is);
                            //TODO: 告诉主线程一个消息：帮我更改界面，内容：bitmap
                            Message msg = new Message();
                            msg.what = CHANGE_UI;
                            msg.obj = bitmap;
                            handler.sendMessage(msg);
                        } else {
                            //返回码不是200，请求服务器失败
                            Message msg = new Message();
                            msg.what = ERROR;
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
}

