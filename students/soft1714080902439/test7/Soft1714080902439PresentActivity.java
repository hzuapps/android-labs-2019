package edu.hzuapps.androidlabs.soft1714080902439;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Map;

import edu.huzapps.androidlabs.soft1714080902439.R;

public class Soft1714080902439PresentActivity extends AppCompatActivity {
    ShakeListener mShakeListener=null;
    //手机震动
    Vibrator mVibrator;
    //摇一摇音效
    private SoundPool soundPool;
    private LinearLayout mImgUp;
    private LinearLayout mImgDn;
    private Map<Integer,Integer> loadSound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置只竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.soft_1714080902439_present_activity);
        //初始化数据
        init();
        //调用工具类方法把assets目录下的声音存放在map中，返回一个HashMap
        loadSound=Utils.loadSound(soundPool,this);
    }

    private void init() {
        mVibrator=(Vibrator) getApplication().getSystemService(VIBRATOR_SERVICE);
        mImgUp=(LinearLayout) findViewById(R.id.shakeImgUp);
        mImgDn=(LinearLayout) findViewById(R.id.shakeImgDown);
        soundPool=new SoundPool(2, AudioManager.STREAM_SYSTEM,5);
    }

    @Override
    protected void onResume(){
        super.onResume();
        //创建加速度监听器的对象
        mShakeListener=new ShakeListener(this);
        //加速度传感器，达到速度阀值，播放动画
        mShakeListener.setOnShakeListener(new ShakeListener.OnShakeListener() {
            @Override
            public void onShake() {

                Utils.startAnim(mImgUp,mImgDn);//  开始摇一摇动画
                mShakeListener.stop();//停止加速度传感器
                soundPool.play(loadSound.get(0),(float) 1,(float) 1,0,0,(float) 1.2);   //摇一摇播放map中存放的第一个声音
                startVibrato();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //摇一摇结束后播放map中存放的第二个声音
                        soundPool.play(loadSound.get(1),(float) 1,(float) 1,0,0,(float) 1.0);
                        Toast.makeText(getApplicationContext(),"失败",10).show();
                        mVibrator.cancel(); //震动关闭
                        mShakeListener.start(); //再次开始检测加速度传感器值
                    }
                },2000);
            }
        });
    }

    @Override
    protected void onPause(){
        super.onPause();
        if (mShakeListener!=null){
            mShakeListener.stop();
        }
    }
    public void startVibrato(){
        //定义震动
        //第一个参数是节奏数组
        mVibrator.vibrate(new long[] {500,200,500,200},-1);
    }
    public void shake_back(View v){
        //标题栏的返回按钮
        this.finish();
    }
    public void linshi(View v){
        //标题栏
        Utils.startAnim(mImgUp,mImgDn);
    }
}
