package edu.hzuapps.androidlabs.soft1714080902331;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Soft1714080902331Activity2 extends AppCompatActivity {
    private EditText record;
    private Button button1;
    private Button button2;
    private  String imageUrl ="https://tu.jiuwa.net/pic/20180120/1516420470729055.gif";
    private ImageView mood;
    private Button getmood;
    private Handler handle = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Bitmap bmp=(Bitmap)msg.obj;
                    mood.setImageBitmap(bmp);
                    break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902331_activity2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");// HH:mm:ss
//获取当前时间
        TextView data = (TextView) findViewById(R.id.data);
        Date date = new Date(System.currentTimeMillis());
        data.setText("Today:" + simpleDateFormat.format(date));
        record = (EditText) findViewById(R.id.record);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button1.setOnClickListener(new ButtonListener());
        button2.setOnClickListener(new ButtonListener());
        getmood=(Button)findViewById(R.id.getmood);
        mood = (ImageView) findViewById(R.id.mood);
        getmood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        Bitmap bmp = getURLimage(imageUrl);
                        Message msg = new Message();
                        msg.what = 0;
                        msg.obj = bmp;
                        handle.sendMessage(msg);
                    }
                }).start();

            }
        });
    }
    private Bitmap getURLimage(String imageUrl) {
        Bitmap bmp = null;
        try {
            URL myurl = new URL(imageUrl);
            // 获得连接
            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
            conn.setConnectTimeout(6000);//设置超时
            conn.setDoInput(true);
            conn.setUseCaches(false);//不缓存
            conn.connect();
            InputStream is = conn.getInputStream();//获得图片的数据流
            bmp = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;

    }


    private class ButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button1:
                    String saveinfo = record.getText().toString().trim();
                    FileOutputStream fos;
                    try {
                        fos = openFileOutput("things.txt", Context.MODE_APPEND);
                        fos.write(saveinfo.getBytes());
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft1714080902331Activity2.this, "记录成功", 0).show();
                    break;
                case R.id.button2:
                    String content = "";
                    try {
                        FileInputStream fis = openFileInput("things.txt");
                        byte[] buffer = new byte[fis.available()];
                        fis.read(buffer);
                        content = new String(buffer);
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft1714080902331Activity2.this, "记录的事情是：" + content, 0).show();
                    break;
                default:
                    break;
            }
        }
    }
}



