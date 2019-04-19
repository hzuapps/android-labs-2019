package edu.hzuapps.androidlabs.soft1714080902131;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Soft1714080902131EditActivity extends AppCompatActivity {
    public final String mFileName = "text1.txt";
    private Button save;
    private EditText et1;
    private Button bt1;
    private ImageView iv1;
    protected static final int CHANGE_UI=1;
    protected static final int ERROR=2;
    //主线程创建消息处理器
    private Handler handler=new Handler(){
      public void handleMessage(android.os.Message msg){
          if (msg.what==CHANGE_UI){
              Bitmap bitmap=(Bitmap) msg.obj;
              iv1.setImageBitmap(bitmap);
          }else if (msg.what==ERROR){
              Toast.makeText(Soft1714080902131EditActivity.this,"Error",Toast.LENGTH_SHORT).show();
          }
      }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902131_edit);
        save = findViewById(R.id.sv);
        et1 = findViewById(R.id.edaet);
        bt1 = findViewById(R.id.bg);
        iv1 = findViewById(R.id.bg1);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save(et1.getText().toString());
                Toast.makeText(Soft1714080902131EditActivity.this,"保存的事项为："+read(),Toast.LENGTH_SHORT).show();
            }
        });
    }
//    public void click(View view){
//        final String image_url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555605418635&di=7b969e7c82a3e0fadea0726abf21f0f9&imgtype=0&src=http%3A%2F%2Fd.5857.com%2Fxqxbz_160923%2Fdesk_001.jpg";
//        new Thread(){
//            public void run(){
//              //使用HttpClient的get请求连接服务器，获取图片
//              getImageByClient(image_url);
//            };
//        }.start();
//    }
//    protected void getImageByClient(String path){
//        HttpClient client=new DefaultHttpClient();
//        HttpGet
//    }
    public void click(View view){
        final String image_url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555609899286&di=144a2dc51e4033a0aec9b1055ec768d5&imgtype=0&src=http%3A%2F%2Fku.90sjimg.com%2Felement_origin_min_pic%2F17%2F12%2F01%2F84e0685eb25a1099d3dab7a1993e86c7.jpg";
        if (TextUtils.isEmpty(image_url)){
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
        }
        else{
            new Thread(){
                private HttpsURLConnection conn;
                private Bitmap bitmap;
                public void run(){
                    try{
                        URL url=new URL(image_url);
                        conn=(HttpsURLConnection) url.openConnection();
                        conn.setRequestMethod("GET");
                        conn.setConnectTimeout(5000);
//                        conn.setRequestProperty("User-Agent",)
                        int code=conn.getResponseCode();
                        if (code==200){
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
    public void save(String content){
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput(mFileName,MODE_PRIVATE);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private   String read()
    {
        FileInputStream fileInputStream= null;
        try {
            fileInputStream = openFileInput(mFileName);
            byte[] buff = new byte[1024];
            StringBuilder sb = new StringBuilder("");//实现字符串的拼接
            int len;
            while((len=fileInputStream.read(buff))>0)
            {
                sb.append(new String(buff,0,len));
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileInputStream != null)
            {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }        }
        return null;
    }
}
