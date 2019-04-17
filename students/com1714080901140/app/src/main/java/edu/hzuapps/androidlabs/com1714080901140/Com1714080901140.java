package edu.hzuapps.androidlabs.com1714080901140;

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

public class Com1714080901140 extends AppCompatActivity {

    protected static final int CHANGE_UI=1;
    protected static final int ERROR=2;
    private EditText ppath;
    private ImageView tupian;

    private Handler handler=new Handler(){
        public void handleMessage(android.os.Message msg){
            if(msg.what==CHANGE_UI){
                Bitmap bitmap=(Bitmap)msg.obj;
                tupian.setImageBitmap(bitmap);
            }else if(msg.what==ERROR){
                Toast.makeText(Com1714080901140.this,"显示图片出错",Toast.LENGTH_SHORT).show();
            }
        };
    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com1714080901140);
        ppath=(EditText) findViewById(R.id.ppath);
        tupian=(ImageView) findViewById(R.id.tupian);
    }
    public void click(View v){
        final String path=ppath.getText().toString().trim();
        if(TextUtils.isEmpty(path)){
            Toast.makeText(this, "路径不为空", Toast.LENGTH_SHORT).show();
        }else {
            new Thread(){
                private HttpURLConnection conn;
                private Bitmap bitmap;
                public void run(){
                    try{
                        URL url=new URL(path);
                        conn=(HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("GET");
                        conn.setConnectTimeout(5000);
                        conn.setRequestProperty("User-Agent","Mozilla/4.0(compatible;MSIE 6.0;Windows NT 5.1;"+"SV1;.NET4.OC;.NET4.OE;.NET CLR 2.0.50727;"+".NET CLR 3.0.4506.2152;.NET CLR 3.5.30729;Shuame)");
                        int code=conn.getResponseCode();
                        if(code==200){
                            InputStream is=conn.getInputStream();
                            bitmap= BitmapFactory.decodeStream(is);
                            Message msg=new Message();
                            msg.what=CHANGE_UI;
                            msg.obj=bitmap;
                            handler.sendMessage(msg);
                        }else {
                            Message msg=new Message();
                            msg.what=ERROR;
                            handler.sendMessage(msg);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        Message msg=new Message();
                        msg.what=ERROR;
                        handler.sendMessage(msg);
                    }
                };
            }.start();
        }
    }
}
