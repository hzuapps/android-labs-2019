package edu.hzuapps.androidlabs.soft1714080902419;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Soft1714080902419NetActivity extends AppCompatActivity {
    protected static final int CHANGE_UI=1;
    protected static final int ERROR=2;
    private EditText path;
    private ImageView iv;
    private Handler handler=new Handler(){
        public void handleMessage(android.os.Message msg){
            if(msg.what==CHANGE_UI){
                Bitmap bitmap=(Bitmap) msg.obj;
                iv.setImageBitmap(bitmap);
            }else if(msg.what==ERROR){
                Toast.makeText(Soft1714080902419NetActivity.this,"显示图片错误",Toast.LENGTH_LONG).show();
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902419_net);
        path=(EditText) findViewById(R.id.path);
        iv=(ImageView) findViewById(R.id.iv);
    }
    public void click(View view){
        final String path1=path.getText().toString().trim();
        if(TextUtils.isEmpty(path1)){
            Toast.makeText(this,"图片路径不能为空",Toast.LENGTH_LONG).show();
        }else{
            new Thread(){
                private HttpURLConnection conn;
                private Bitmap bitmap;
                public void run(){
                    try{
                        URL url=new URL(path1);
                        conn=(HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("GET");
                        conn.setConnectTimeout(5000);
                        conn.setRequestProperty("User-Agent",
                                "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;"+ "SVl;.NET4.0C;.NET4.OE;.NET CLR 2.0.50727;"+ ".NET CLR 3.0.4506.2152;.NET CLR 3.5.30729;Shuame)");
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