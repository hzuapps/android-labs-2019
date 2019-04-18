package edu.hzuapps.androidlabs.soft1714080902436;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Soft1714080902436Activity extends AppCompatActivity {

    public static final String transmit = "transmit";

    protected static final int CHANGE_UI = 1;
    protected static final int ERROR = 2;
    private ImageView iv;
    //主线程创建消息处理器
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == CHANGE_UI) {
                Bitmap bitmap = (Bitmap) msg.obj;
                iv.setImageBitmap(bitmap);
            } else if (msg.what == ERROR) {
                Toast.makeText(Soft1714080902436Activity.this, "显示图片错误", Toast.LENGTH_SHORT).show();
            }
        }
    };


//插入记录
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902436_activity);
        iv = (ImageView) findViewById(R.id.iv);
        Button gobutton1 = (Button) findViewById(R.id.gobutton1);
        gobutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit_message1;
                edit_message1 = (EditText) findViewById(R.id.edit_message1);
                String edit_message2 = edit_message1.getText().toString();
                    TextView tv=(TextView)findViewById(R.id.textView6);
                    String cn=edit_message1.getText().toString();
                    tv.setText(cn);
                Intent intent1 = new Intent(Soft1714080902436Activity.this, Soft1714080902436ConfirmActivity.class);
                intent1.putExtra("transmit", edit_message2);
                startActivity(intent1);
            }
        });
        Button gobutton2 = (Button) findViewById(R.id.gobutton2);
        gobutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //获取图片
    public void click(View view) {
        final String image_url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555611714842&di=65e5d5c3eed503da94408e5fad2b3e25&imgtype=0&src=http%3A%2F%2Fwww.xz7.com%2Fup%2F2017-7%2F15010491818447462.JPEG";
        if (TextUtils.isEmpty(image_url)) {
            Toast.makeText(this, "图片路径不能为空", Toast.LENGTH_SHORT).show();
        } else {
            //子线程请求网络，Android 4.0以后访问网络不能放在主线程中
            new Thread() {
                private HttpURLConnection conn;
                private Bitmap bitmap;

                public void run() {
                    //连接服务器get请求，获取图片
                    try {
                        //创建URL对象
                        URL url = new URL(image_url);
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


