package edu.hzuapps.androidlabs.com1712070504104;
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


public class Com1712070504104Activity extends AppCompatActivity {
    private LinearLayout linear;
    private TextView changeText;
    private Button b1;
    private Button b2;
    private String url_image="https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1555679919&di=1245c0e9ffc7b91abeffa4d9f4b90b1a&src=http://b-ssl.duitang.com/uploads/item/201807/01/20180701121509_luutw.jpg";
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
        setContentView(R.layout.com_1712070504104_activity);
        linear=findViewById(R.id.linear);
        changeText=findViewById(R.id.text_background);
        b1=findViewById(R.id.createButton);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    Intent intent2= new Intent(Com1712070504104Activity.this,Com1712070504104Activity2.class);
                    startActivity(intent2);
                }
            }
        });
                b2=findViewById(R.id.createButton2);
        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Com1712070504104Activity.this,Com1712070504104Activity3.class);
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