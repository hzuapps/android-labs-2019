package edu.hzuapps.androidlabs.soft1714080902203;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Soft1714080902203Activity1 extends AppCompatActivity {
    private EditText jcwu;
    private Button btn_save;
    private Button btn_read;
    Button button = null;
    private Button btn;
    private ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902203_activity1);

        //获取布局文件中的控件
        jcwu = (EditText) findViewById(R.id.jcwu);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_read = (Button) findViewById(R.id.btn_read);
        //设置按钮点击函数
        btn_save.setOnClickListener(new ButtonListener());
        btn_read.setOnClickListener(new ButtonListener());


        btn = (Button) this.findViewById(R.id.button);
        imageview = (ImageView) this.findViewById(R.id.imageView);
        button = findViewById(R.id.buttons);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Soft1714080902203Activity1.this,Soft1714080902203Activity2.class);
                startActivity(intent);
            }
        });
        //连接网络的函数
        StrictMode.setThreadPolicy(new
                StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(
                new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());

        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                btn.setEnabled(false);
                String strURL = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555611660563&di=4a380e6f6b2fc29ba4d29c496ea191e5&imgtype=0&src=http%3A%2F%2Ftvax1.sinaimg.cn%2Fcrop.0.0.1080.1080.1024%2Fe4669cc6ly8g1fkivhuujj20u00u0gov.jpg";
                try {
                    //将图片通过URL形式，用函数转换成图片显示出来
                    Bitmap bitmap = getBitmap(strURL);
                    imageview.setImageBitmap(bitmap);
                } catch (IOException e) {
                    // 检测异常
                    e.printStackTrace();
                }
            }
        });

    }

    public Bitmap getBitmap(String path) throws IOException {
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == 200) {
                InputStream inputStream = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (IOException e) {
            // 检测异常
            e.printStackTrace();
        }
        return null;
    }


    //定义Button按钮点击事件
    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_save:
                    String saveinfo = jcwu.getText().toString().trim();
                    FileOutputStream fileOutputStream;
                    try {
                        fileOutputStream=openFileOutput("data.txt", Context.MODE_APPEND);
                        fileOutputStream.write(saveinfo.getBytes());
                        fileOutputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft1714080902203Activity1.this,"以保存！",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.btn_read:
                    String content="";
                    try{
                        FileInputStream fileInputStream=openFileInput("data.txt");
                        byte[] buffer=new byte[fileInputStream.available()];
                        fileInputStream.read(buffer);
                        content=new String(buffer);
                        fileInputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }Toast.makeText(Soft1714080902203Activity1.this,"您的爱豆简介已保存："+content,Toast.LENGTH_SHORT).show();
                    break;


                default: break;
            }
        }
    }

}
