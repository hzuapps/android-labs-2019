package edu.hzuapps.androidlabs.com1714080901132;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.IDNA;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.myapplication.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main2Activity extends AppCompatActivity {
    protected static final int CHANGE_UI=1;
    protected static final int ERROR=2;
    private EditText et_path;
    private ImageView iv;
    private Handler handler=new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == CHANGE_UI) {
                Bitmap bitmap = (Bitmap) msg.obj;
                iv.setImageBitmap(bitmap);
            } else if (msg.what == ERROR) {
                Toast.makeText(Main2Activity.this,"显示图片错误",Toast.LENGTH_SHORT).show();
            }
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        et_path=(EditText) findViewById(R.id.et_path);
        iv=(ImageView) findViewById(R.id.iv);
    }
    public void click(View view){
        final  String path=et_path.getText().toString().trim();
        if (TextUtils.isEmpty(path)){
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
                        conn.setRequestProperty("User-Agent","Mozilla/4.0(compatible;MSIE 6.0;Windows NT 5.1;"+"SV1;.NET4.0C;.NET4.0E;.NET CLR2.0.50727;"+".NET CLR3.0.4506.2152;.NET CLR 3.5.30729;Shuame)");
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
                };
            }.start();
        }
    }
}

