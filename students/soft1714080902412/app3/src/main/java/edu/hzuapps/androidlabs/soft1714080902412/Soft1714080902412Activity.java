package edu.hzuapps.androidlabs.soft1714080902412;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Soft1714080902412Activity extends AppCompatActivity implements View.OnClickListener {
    private Button bt1;
    private EditText et1;
    private ImageView image1;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902412);
        bt1 = (Button) findViewById(R.id.bt1);
        et1 = (EditText) findViewById(R.id.et1);
        image1 = (ImageView) findViewById(R.id.image1);
        bt1.setOnClickListener(this);
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what==666) {
                image1.setImageBitmap((Bitmap) msg.obj);
            }
        }
    };

    @Override
    public void onClick(View view) {
        new Thread(t).start();
    }

    Thread t = new Thread() {
        public void run() {
            String path = et1.getText().toString();
            try {
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setUseCaches(false);
                conn.connect();
                InputStream inputStream = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                Message message=new Message();
                message.what=666;
                message.obj=bitmap;
                handler.sendMessage(message);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        };
}
