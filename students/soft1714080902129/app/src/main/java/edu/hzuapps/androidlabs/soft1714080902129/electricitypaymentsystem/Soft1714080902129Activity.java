package edu.hzuapps.androidlabs.soft1714080902129.electricitypaymentsystem;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class Soft1714080902129Activity extends AppCompatActivity {

    private LinearLayout bg;
    private Button button;
    private String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557248715893&di=e64b82e55e4f27b1712bab3afc0825be&imgtype=0&src=http%3A%2F%2Fwww.51pptmoban.com%2Fd%2Ffile%2F2012%2F11%2F01%2Fa1f289fb0f6233b02486d2fba8c6f1da.jpg";


    private Handler handler=new Handler()
    {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Bitmap bmp=(Bitmap)msg.obj;
                    bg.setBackground(new BitmapDrawable(bmp));
                    break;
            }
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_soft1714080902129);
        super.onCreate(savedInstanceState);
        //edit=(EditText) findViewById(R.id.input_message);
        CalendarView calendarView = findViewById(R.id.calendar);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Intent intent = new Intent(Soft1714080902129Activity.this, Soft1714080902129Activity3.class);
                startActivity(intent);
            }
        });
        bg = findViewById(R.id.bg);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bitmap = getImage(url);
                        Message msg = new Message();
                        msg.what = 0;
                        msg.obj = bitmap;
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });
    }
    //从网络下载图片
    private Bitmap getImage(String url_image) {
        Bitmap bmp = null;
        try {
            URL Myurl = new URL(url_image);
            HttpURLConnection conn = (HttpURLConnection) Myurl.openConnection();
            conn.setConnectTimeout(6000);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.connect();
            InputStream is = conn.getInputStream();
            bmp = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;

    }
}
