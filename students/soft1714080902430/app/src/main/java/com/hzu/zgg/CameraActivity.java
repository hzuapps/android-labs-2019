package com.hzu.zgg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CameraActivity extends AppCompatActivity {

    private String tag ="CameraActivity";
    private SurfaceView surfaceView;
    //Surface的控制器
    private SurfaceHolder surfaceHolder;
    private Camera camera;
    private Button saveButton;
    //拍照的回调接口
    private Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
        public void onPictureTaken(byte[] data, Camera camera) {
            new SavePictureTask().execute(data);
            camera.startPreview();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        initViews();
    }
    private void initViews() {
        saveButton = (Button) findViewById(R.id.camera_save);
        surfaceView = (SurfaceView) findViewById(R.id.camera_preview);
        surfaceHolder = surfaceView.getHolder();
// 给SurfaceView当前的持有者 SurfaceHolder 一个回调对象。
//用户可以实现此接口接收surface变化的消息。当用在一个SurfaceView 中时，
//它只在SurfaceHolder.Callback.surfaceCreated()和SurfaceHolder.Callback.surfaceDestroyed()之间有效。
//设置Callback的方法是SurfaceHolder.addCallback.
//实现过程一般继承SurfaceView并实现SurfaceHolder.Callback接口
        surfaceHolder.addCallback(surfaceCallback);
// 设置surface不需要自己的维护缓存区
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        saveButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                camera.takePicture(null, null, pictureCallback);
            }
        });
    }
    //SurfaceView当前的持有者的回调接口的实现
    SurfaceHolder.Callback surfaceCallback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            camera = Camera.open();
            Log.e(tag, "摄像头Open完成");
            try {
                camera.setPreviewDisplay(holder);
            } catch (IOException e) {
                camera.release();
                camera = null;
            }
        }
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {
            Camera.Parameters parameters = camera.getParameters();
            parameters.setPictureFormat(ImageFormat.JPEG);
            camera.setDisplayOrientation(0);
            camera.setParameters(parameters);
            camera.startPreview();
        }
        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    };
    class SavePictureTask extends AsyncTask<byte[], String, String> {
        @Override
        protected String doInBackground(byte[]... params) {
            File picture = new File("/sdcard/mhc.jpg");
            try {
                FileOutputStream fos = new FileOutputStream(picture.getPath());
                fos.write(params[0]);
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e(tag, "照片保存完成");
            CameraActivity.this.finish();
            return null;
        }
    }
}
