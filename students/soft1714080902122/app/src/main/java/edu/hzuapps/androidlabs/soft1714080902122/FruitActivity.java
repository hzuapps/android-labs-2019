package edu.hzuapps.androidlabs.soft1714080902122;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FruitActivity extends AppCompatActivity {
    ImageView imageView;
    Button button;

    String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556301647244&di=9c79955dc92213782893a8ac3f964205&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201709%2F11%2F20170911201516_eTyN5.jpeg";
    //String textURL = "http://192.168.1.104:8080/add.jsp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);


    button = (Button) findViewById(R.id.mgbl);
    imageView = (ImageView) findViewById(R.id.image_view);

        button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new Thread(new Runnable() {
                @Override
                public void run() {
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


    //在消息队列中实现对控件的更改
    private Handler handle = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    System.out.println("111");
                    Bitmap bmp=(Bitmap)msg.obj;
                    imageView.setImageBitmap(bmp);
                    break;
            }
        };
    };

    //加载图片
    public Bitmap getURLimage(String url) {
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
            bmp = BitmapFactory.decodeStream(is);//读取图像数据
            //读取文本数据
            //byte[] buffer = new byte[100];
            //inputStream.read(buffer);
            //text = new String(buffer);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;


}
}
