package com.example.dl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {

    private TextView mFileTV;

    private TextView mThread1TV;
    private TextView mThread3CompleteTV;
    protected static int threadCount;
    private ImageView image;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image=(ImageView)findViewById(R.id.image01);

        Button button3 = (Button) findViewById(R.id.button1);
        button3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View LL) {
                //getDatasync();
                Glide.with(MainActivity.this).load("http://imgs.aixifan.com/live/1492583964371/1492583964371.jpg").into(image);
            }
        });
    }




}
