package edu.hzuapps.androidlabs.soft1714080902216;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Soft1714080902216imageActivity extends AppCompatActivity {
    protected static final int CHANGE_UI=1;
    protected static final int ERROR=2;
    protected static final int a=0;
    private EditText et_path;
    private ImageView iv;
    //主线创建消息处理器
    private Handler handler=new Handler(){
        public void handleMessage(android.os.Message msg){
            if (msg.what==CHANGE_UI){
                Bitmap bitmap=(Bitmap)msg.obj;
                iv.setImageBitmap(bitmap);
            }else if (msg.what==ERROR){
                Toast.makeText(Soft1714080902216imageActivity.this,"显示图片错误",a).show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902216image);
        et_path=(EditText)findViewById(R.id.et_path);
        iv=(ImageView) findViewById(R.id.iv);
    }
    public void click(View view){
        final String path=et_path.getText().toString().trim();
        if (TextUtils.isEmpty(path)){
            Toast.makeText(this,"图片路径不能为空",a).show();
        }

        else {
            //子线程请求网络，Android 4.0以后访问网络不能放在子线程中
            new Thread(){
                private HttpURLConnection conn;
                private Bitmap bitmap;
                public void run(){
                    //链接服务器get请求，获取图片
                    try{
                        //创建URL对象
                        URL url =new URL(path);
                        conn=(HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("GET");
                        conn.setConnectTimeout(5000);
                        conn.setRequestProperty("User-Agent","Mozilla/4.0 (compatib;MSIE 6.0;Window NT 5.1;"+"SV1;" +
                                ".NET4.0C;.NET4.0E;NET CLK 2.0.50727;"+".NET CLR 3.0..4506.2152;.NET CLR 3.5.30729; Shuame )");
                        int code=conn.getResponseCode();
                        if(code==200){
                            InputStream is=conn.getInputStream();
                            bitmap= BitmapFactory.decodeStream(is);
                            Message msg=new Message();
                            msg.what=CHANGE_UI;
                            msg.obj=bitmap;
                            handler.sendMessage(msg);
                        }
                        else {
                            Message msg=new Message();
                            msg.what=ERROR;
                            handler.sendMessage(msg);

                        }

                    }catch (Exception e){
                        e.printStackTrace();
                        Message msg=new Message();
                        msg.what=ERROR;

                    }
                }
            }.start();
        }
    }
}
