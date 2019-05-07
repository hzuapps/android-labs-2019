package com.example.administrator.basketball;


import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;

public class MovieRecorder extends Activity implements OnClickListener {
    // 程序中的两个按钮，录制和停止
    ImageButton record, stop;
    // 系统的视频文件
    File videoFile;
    MediaRecorder mRecorder;
    // 显示视频预览的SurfaceView
    SurfaceView sView;
    // 记录是否正在进行录制
    private boolean isRecording = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置横屏显示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // 设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 选择支持半透明模式,在有surfaceview的activity中使用。
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        // 获取程序界面中的两个按钮
        record = (ImageButton) findViewById(R.id.record);
        stop = (ImageButton) findViewById(R.id.stop);
        // 让stop按钮不可用。
        stop.setEnabled(false);
        // 为两个按钮的单击事件绑定监听器
        record.setOnClickListener(this);
        stop.setOnClickListener(this);
        // 获取程序界面中的SurfaceView
        sView = (SurfaceView) this.findViewById(R.id.sView);
        // 设置分辨率
        sView.getHolder().setFixedSize(1280, 720);
        // 设置该组件让屏幕不会自动关闭
        sView.getHolder().setKeepScreenOn(true);
    }

    @Override
    public void onClick(View source) {
        switch (source.getId()) {
            // 单击录制按钮
            case R.id.record:
                if (!Environment.getExternalStorageState().equals(
                        android.os.Environment.MEDIA_MOUNTED)) {
                    Toast.makeText(MovieRecorder.this, "SD卡不存在，请插入SD卡！",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    // 创建保存录制视频的视频文件，视频结果会保存在sd卡的根目录20190506.3gp
                    videoFile = new File(Environment.getExternalStorageDirectory()
                            .getCanonicalFile() + "/20190506.3gp");
                    // 创建MediaPlayer对象
                    mRecorder = new MediaRecorder();
                    mRecorder.reset();
                    // 设置从麦克风采集声音(或来自录像机的声音AudioSource.CAMCORDER)
                    mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    // 设置从摄像头采集图像
                    mRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
                    // 设置视频文件的输出格式
                    // 必须在设置声音编码格式、图像编码格式之前设置
                    mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    // 设置声音编码的格式
                    mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    // 设置图像编码的格式
                    mRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
                    mRecorder.setVideoSize(1280, 720);
                    // 每秒 4帧
                    mRecorder.setVideoFrameRate(20);
                    mRecorder.setVideoEncodingBitRate(8*1024*1024);
                    mRecorder.setOutputFile(videoFile.getAbsolutePath());
                    // 指定使用SurfaceView来预览视频
                    mRecorder.setPreviewDisplay(sView.getHolder().getSurface()); // ①
                    mRecorder.prepare();
                    // 开始录制
                    mRecorder.start();

                    // 让record按钮不可用。
                    record.setEnabled(false);
                    // 让stop按钮可用。
                    stop.setEnabled(true);
                    isRecording = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            // 单击停止按钮
            case R.id.stop:
                // 如果正在进行录制
                if (isRecording) {
                    // 停止录制
                    mRecorder.stop();
                    // 释放资源
                    mRecorder.release();
                    mRecorder = null;
                    // 让record按钮可用。
                    record.setEnabled(true);
                    // 让stop按钮不可用。
                    stop.setEnabled(false);
                }
                break;
        }
    }
}
