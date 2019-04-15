package edu.hzuapps.androidlabs.com1714080901133;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Com1714080901133Activity extends AppCompatActivity {

    private  String imageUrl = "http://img.mp.itc.cn/upload/20170628/b0dabda375494e078340073efc4156c4_th.jpg";
    private ImageView img;
    private Handler handle = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Bitmap bmp=(Bitmap)msg.obj;
                    img.setImageBitmap(bmp);
                    break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_1714080901133_activity);


        ImageView jmp = findViewById(R.id.hzu);
         {

        img = (ImageView) findViewById(R.id.img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        Bitmap bmp = getURLimage(imageUrl);
                        Message msg = new Message();
                        msg.what = 0;
                        msg.obj = bmp;
                        handle.sendMessage(msg);
                    }
                }).start();
            }
        });
    }}

    private Bitmap getURLimage(String imageUrl) {
        Bitmap bmp = null;
        try {
            URL myurl = new URL(imageUrl);
            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
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
