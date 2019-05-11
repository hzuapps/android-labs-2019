package edu.hzuapps.androidlabs.com1714080901136;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import static android.media.audiofx.AudioEffect.ERROR;

public class Com136Activity2 extends Activity {
    private Button clean;
    private Button save;
    private Button read;
    private EditText edit_1;
    private EditText edit_2;
    private EditText edit_3;
    private EditText et_path;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com1362);
       clean= findViewById(R.id.clean);
      save= findViewById(R.id.sava);
       read= findViewById(R.id.read);
        edit_1=findViewById(R.id.biaoti);
        edit_2=findViewById(R.id.riqi);
        edit_3=findViewById(R.id.beizhu);
        et_path=findViewById(R.id.et5);
        iv= findViewById(R.id.iv);
        save.setOnClickListener(new ButtonListener());
        clean.setOnClickListener(new ButtonListener());
        read.setOnClickListener(new ButtonListener());
    }
private class ButtonListener implements View.OnClickListener{
    @SuppressLint("WrongConstant")
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sava:
                String biaoti = edit_1.getText().toString().trim();
                String riqi = edit_2.getText().toString().trim();
                String beizhu = edit_3.getText().toString().trim();
                String sava_info="-------------------------------------------------"+"\n"+"标题："+biaoti+"\n"+"日期："+riqi+"\n"+"备注："+beizhu+"\n";
                FileOutputStream fos;
                try {
                    fos = openFileOutput("data.txt", Context.MODE_APPEND);
                    fos.write(sava_info.getBytes());
                    fos.close();
                }catch(Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(Com136Activity2.this, "数据保存成功", 0).show();
            case R.id.read:
                String content = "";
                try {
                    FileInputStream fis = openFileInput("data.txt");
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    content = new String(buffer);
                    fis.close();
                  Intent intent=new Intent(Com136Activity2.this,Com136Activity.class);
                    intent.putExtra("data",content);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
            case R.id.clean:
                Intent intent1 = new Intent(Com136Activity2.this,Com136Activity2.class);
                startActivity(intent1);
        }}
    }
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler()
    {
        @SuppressLint("WrongConstant")
        public void handleMessage(android.os.Message msg) {
            if(msg.what==1){
                Bitmap bitmap=(Bitmap)msg.obj;
                iv.setImageBitmap(bitmap);
            }else if(msg.what==ERROR){
            Toast.makeText(Com136Activity2.this,"显示配图错误",0).show();
            }
        }
    };
    @SuppressLint("WrongConstant")
    public void liulan(View v) {
       final String path =et_path.getText().toString().trim();
     if(TextUtils.isEmpty(path)){
         Toast.makeText(this,"图片路径不能为空",0).show();
     }else {
         new Thread() {
             private HttpURLConnection conn;
             private Bitmap bitmap;

             public void run() {
                 try {
                     URL url = new URL(path);
                     conn = (HttpURLConnection) url.openConnection();
                     conn.setRequestMethod("GET");
                     conn.setConnectTimeout(5000);
                     conn.setDoInput(true);
                     conn.setUseCaches(false);
                     int code = conn.getResponseCode();
                     if (code == 200) {
                         InputStream is = conn.getInputStream();
                         bitmap = BitmapFactory.decodeStream(is);
                         Message msg = new Message();
                         msg.what = 1;
                         msg.obj = bitmap;
                         handler.sendMessage(msg);
                     } else {
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







