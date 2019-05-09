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

public class Com1714080901133Activity2 extends AppCompatActivity {
//获取网络图片
    private  String imageUrl = "http://img4.imgtn.bdimg.com/it/u=3798182503,399038121&fm=11&gp=0.jpg";
    private ImageView img;

    //在消息队列中实现对控件的更改
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
        setContentView(R.layout.com_1714080901133_activity2);

        img = (ImageView) findViewById(R.id.img);
        new Thread(new Runnable() {

            @Override
            public void run() {
                Bitmap bmp = getURLimage(imageUrl);
                Message msg = new Message();
                msg.what = 0;
                msg.obj = bmp;
                handle.sendMessage(msg);
            }
        }).start();}

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

}
