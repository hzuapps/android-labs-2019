package edu.hzuapps.androidlabs.soft1709081602603;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ViewImageActivity extends AppCompatImageView {

    public static final int Getimage = 1;
    public static final int Neterror = 2;
    public static final int Servererror = 3;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Getimage:
                    Bitmap bitmap = (Bitmap) msg.obj;
                    setImageBitmap(bitmap);
                    break;
                case Neterror:
                    Toast.makeText(getContext(), "网络连接失败", Toast.LENGTH_SHORT).show();
                    break;
                case Servererror:
                    Toast.makeText(getContext(), "服务器错误", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    public ViewImageActivity(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ViewImageActivity(Context context) {
        super(context);
    }

    public ViewImageActivity(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setImageURL(final String path) {
        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(10000);
                    int code = connection.getResponseCode();
                    if (code == 200) {
                        InputStream inputStream = connection.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        Message msg = Message.obtain();
                        msg.obj = bitmap;
                        msg.what = Getimage;
                        handler.sendMessage(msg);
                        inputStream.close();
                    } else {
                        handler.sendEmptyMessage(Servererror);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    handler.sendEmptyMessage(Neterror);
                }
            }
        }.start();
    }
}