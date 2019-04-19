package edu.hzuapps.androidlabs.com1714080901115;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
public class Com1714080901115Activity3 extends AppCompatActivity implements View.OnClickListener {
    private ImageView image_download;
    private Button btn_download;
    private Handler handler;
    private String path = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555686247339&di=c63fe1059478a27a539b20a91a353a58&imgtype=0&src=http%3A%2F%2Fpic.baike.soso.com%2Fp%2F20140225%2F20140225143401-1079932810.jpg";
    private int Length;
    private Bitmap mBitmap;
    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_1714080901115_activity3);
        initialView();
        btn_download.setOnClickListener(this);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        Bitmap bitmap = (Bitmap) msg.obj;
                        if (bitmap != null) {
                            image_download.setImageBitmap(bitmap);
                        }
                        break;
                }
            }
        };
    }
    public void initialView() {
        btn_download = findViewById(R.id.btn_download);
        image_download = findViewById(R.id.image_download);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_download:
                new Thread(new GetPictThread(handler, path)).start();
                break;
        }
    }
    public class GetPictThread implements Runnable {
        public Handler handler;
        public String path;
        public GetPictThread(Handler handler, String path) {
            this.handler = handler;
            this.path = path;
        }
        @Override
        public void run() {
            try {
                URL url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                int requestCode = connection.getResponseCode();
                System.out.println(requestCode);
                if (requestCode == HttpURLConnection.HTTP_OK) {
                    Length = connection.getContentLength();
                    InputStream is = new BufferedInputStream(connection.getInputStream());
                    byte[] arr = streamToArr(is);
                    mBitmap = BitmapFactory.decodeByteArray(arr, 0, arr.length);
                    Message message = Message.obtain();
                    message.obj = mBitmap;
                    handler.sendMessage(message);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public byte[] streamToArr(InputStream inputStream) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.close();
            inputStream.close();
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}