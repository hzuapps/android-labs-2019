package edu.hzuapps.androidlabs.soft1714080902210;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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

public class Soft1714080902210Activity_change extends AppCompatActivity {
    private Button btn;
    private ImageView img;
    private String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555581616710&di=7c402f6933c132052c3dfe2aef72540b&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F11%2F67%2F86%2F60f58PIC834.jpg";
    private Handler handle = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    System.out.println("111");
                    Bitmap bmp = (Bitmap) msg.obj;
                    img.setImageBitmap(bmp);
                    break;
            }
        };
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902210_change);
        btn = (Button) findViewById(R.id.button14);
        img = (ImageView) findViewById(R.id.image);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //新建线程加载图片信息，发送到消息队列中
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        Bitmap bmp = getURLimage(url);
                        Message msg = new Message();
                        msg.what = 0;
                        msg.obj = bmp;
                        System.out.println("000");
                        handle.sendMessage(msg);
                    }
                }).start();
            }
        });
    }
        //加载图片
        public Bitmap getURLimage (String url){
            Bitmap bmp = null;
            try {
                URL myurl = new URL(url);
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

    }

