package edu.hzuapps.androidlabs.com1713071002108;

import android.annotation.SuppressLint;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;



public class Com1713071002108Activity extends AppCompatActivity {
    private LinearLayout linear;
    private String url_image="http://pic.rmb.bdstatic.com/cb55d8f340941b564316217a41850617.jpeg";
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler()
    {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Bitmap bmp=(Bitmap)msg.obj;
                    linear.setBackground(new BitmapDrawable(bmp));
                    break;
            }
        };
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_1713071002108_activity);
        linear=findViewById(R.id.linear);
        TextView changeText = findViewById(R.id.text_background);
        Button b = findViewById(R.id.createButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Com1713071002108Activity.this,Com1713071002108Activity2.class);
                startActivity(intent);
            }
        });
        changeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bmp = getImage(url_image);
                        Message msg = new Message();
                        msg.what = 0;
                        msg.obj = bmp;
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });

    }

    private Bitmap getImage(String url_image) {
        Bitmap bmp = null;
        try {
            URL Myurl = new URL(url_image);
            HttpURLConnection conn = (HttpURLConnection) Myurl.openConnection();
            conn.setConnectTimeout(8000);
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
