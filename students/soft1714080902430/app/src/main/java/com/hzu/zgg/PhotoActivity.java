package com.hzu.zgg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
public class PhotoActivity extends AppCompatActivity {

    private Button cameraButton; //拍照按钮
    private Button quitButton; //退出按钮
    private ImageView imageView; //图片显示
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        initViews();
    }
    private void initViews() {
        imageView = (ImageView) findViewById(R.id.main_image);
// 从本地取图片(在sdcard中获取文件)
        Bitmap bitmap = getLoacalBitmap("/sdcard/mhc.jpg");
        Log.e("msgTag", new File("/sdcard/mhc.jpg").exists()+"==========");
        imageView .setImageBitmap(bitmap); //设置Bitmap
        cameraButton = (Button) findViewById(R.id.main_camera);
        quitButton = (Button) findViewById(R.id.main_quit);
        MainOnClickListener mainOnClickListener = new MainOnClickListener();
        cameraButton.setOnClickListener(mainOnClickListener);
        quitButton.setOnClickListener(mainOnClickListener);
    }
    private class MainOnClickListener implements OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.main_camera:
                    startActivity(new Intent(PhotoActivity.this, CameraActivity.class));
                    break;
                case R.id.main_quit:
                    PhotoActivity.this.finish();
                    break;
                default:
                    break;
            }
        }
    }
    /**
     * 根据文件路径获取所需要文件，并将该文件封装成Bitmap对象返回
     * @param fileUrl
     * @return
     */
    public static Bitmap getLoacalBitmap(String fileUrl) {
        try {
            FileInputStream fis = new FileInputStream(fileUrl);
            return BitmapFactory.decodeStream(fis); ///把流转化为Bitmap图片
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}