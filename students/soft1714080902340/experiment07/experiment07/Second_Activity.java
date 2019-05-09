package com.cap.experiment07;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class Second_Activity extends AppCompatActivity {
    private VideoView mVideo;
    private Button Turn_back;

    File file = new File(Environment.getExternalStorageDirectory().getPath()+"/video.mp4" );//设置录像存储路径
    Uri uri = Uri.fromFile(file);//文件转成Uri格式
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_);
        Turn_back=(Button)findViewById(R.id.Return_Back);
        mVideo=(VideoView)findViewById(R.id.V_video);
        initVideoPath();
        Turn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.setClass(Second_Activity.this, MainActivity.class);//也可以这样写intent.setClass(MainActivity.this, OtherActivity.class);
                startActivity(intent);
            }
        });

    }
    private void initVideoPath() {
        if (ContextCompat.checkSelfPermission(Second_Activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Second_Activity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        Toast.makeText(this, "have found video", Toast.LENGTH_SHORT).show();
        mVideo.setVideoURI(uri);
        mVideo.setVideoPath(file.getAbsolutePath());
        MediaController mediaController=new MediaController(this);
        mVideo.setMediaController(mediaController);
        mediaController.setMediaPlayer(mVideo);
        mVideo.requestFocus();
        mVideo.start();
    }
}
