package edu.hzuapps.androidlabs.soft1712070504232;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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
import java.net.HttpURLConnection;
import java.net.URL;

import static android.widget.Toast.LENGTH_SHORT;

public class Soft1712070504232Activity extends Activity {
    private Button button;
    private EditText et_info;
    private Button btn_save;
    private Button btn_read;
    protected static final int CHANGE_UI = 1;
    protected static final int ERROR = 2;
    private EditText et_path;
    private ImageView iv;

    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg){
          if(msg.what == CHANGE_UI){
              Bitmap bitmap = (Bitmap)msg.obj;
              iv.setImageBitmap(bitmap);
          }else if(msg.what == ERROR){
              Toast.makeText(Soft1712070504232Activity.this,"显示图片错误",LENGTH_SHORT).show();
          }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1712070504232_activity);
        et_path = (EditText) findViewById(R.id.et_path);
        iv = (ImageView) findViewById(R.id.iv);
        button = (Button) findViewById(R.id.button1);
        et_info = (EditText) findViewById(R.id.et_info);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_read = (Button) findViewById(R.id.btn_read);

        btn_save.setOnClickListener(new ButtonListener());
        btn_read.setOnClickListener(new ButtonListener());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Soft1712070504232Activity.this, Soft1712070504232Activity2.class);
                startActivity(intent);
            }
        });

        Button take_photo = (Button) findViewById(R.id.take_photo);
        take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("android.media.action.IMAGE_CAPTURE");
                startActivity(intent);
            }
        });
    }
    public void click(View view){
        final String path = et_path.getText().toString().trim();
        if(TextUtils.isEmpty(path)){
            Toast.makeText(this,"图片路径不能为空",LENGTH_SHORT).show();
        }else{
            new Thread(){
               private HttpURLConnection conn;
               private  Bitmap bitmap;
                public void run() {
                    try{
                        URL url = new URL(path);
                        conn = (HttpURLConnection)url.openConnection();
                        conn.setRequestMethod("GET");
                        conn.setConnectTimeout(5000);
                        conn.setRequestProperty("User-Agent",
                                "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;" + "SV1;" +
                                ".NET4.0C;.NET4.0E;.NET CLR 2.0.50727;" + ".NET CLR 3.0.4506.2152;" +
                                ".NET CLR 3.5.30729;Shuame)");
                        int code = conn.getResponseCode();
                        if(code == 200){
                            InputStream is = conn.getInputStream();
                            bitmap = BitmapFactory.decodeStream(is);
                            Message msg = new Message();
                            msg.what = CHANGE_UI;
                            msg.obj = bitmap;
                            handler.sendMessage(msg);
                        }else{
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
                };
            }.start();
        }
    }

    private class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btn_save:
                String saveinfo = et_info.getText().toString().trim();
                    FileOutputStream fos;
                    try{
                        fos = openFileOutput("data.txt", Context.MODE_APPEND);
                        fos.write(saveinfo.getBytes());
                        fos.close();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft1712070504232Activity.this,"数据保存成功",LENGTH_SHORT).show();
                        break;
                case R.id.btn_read:
                    String content = "";
                    FileInputStream fis = null;
                    try {
                        fis = openFileInput("data.txt");
                        byte[] buffer = new byte[fis.available()];
                        fis.read(buffer);
                        content = new String(buffer);
                        fis.close();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft1712070504232Activity.this,"保存的数据是：" + content,LENGTH_SHORT).show();
                        break;
                    default:
                        break;

            }
        }
    }
}
