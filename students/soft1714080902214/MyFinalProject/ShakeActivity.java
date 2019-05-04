package edu.hzuapps.androidlabs.soft1714080902214.myfinalproject;

import java.util.Map;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by windowsPC on 2019/5/3.
 */

public class ShakeActivity extends Activity {
    ShakeListener mShakeListener = null;
    Vibrator mVibrator;
    private RelativeLayout mImgUp;
    private RelativeLayout mImgDn;
    private SoundPool sndPool;
    private Map<Integer, Integer> loadSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);
        init();
        loadSound = Utils.loadSound(sndPool, this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mShakeListener = new ShakeListener(this);

        mShakeListener.setOnShakeListener(new ShakeListener.OnShakeListener() {
            public void onShake() {
                Utils.startAnim(mImgUp, mImgDn);
                mShakeListener.stop();
                sndPool.play(loadSound.get(0), (float) 1, (float) 1, 0, 0,
                        (float) 1.2);
                startVibrato();
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        sndPool.play(loadSound.get(1), (float) 1, (float) 1, 0,
                                0, (float) 1.0);
                        Toast.makeText(getApplicationContext(),
                                "抱歉，暂时没有找到\n在同一时刻摇一摇的人。\n再试一次吧！", 10).show();
                        mVibrator.cancel();// 震动关闭
                        mShakeListener.start();
                    }
                }, 2000);
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (mShakeListener != null) {
            mShakeListener.stop();
        }
    }
    private void init() {
        mVibrator = (Vibrator) getApplication().getSystemService(
                VIBRATOR_SERVICE);
        mImgUp = (RelativeLayout) findViewById(R.id.shakeImgUp);
        mImgDn = (RelativeLayout) findViewById(R.id.shakeImgDown);
        sndPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 5);
    }

    public void startVibrato() {
        mVibrator.vibrate(new long[] { 500, 200, 500, 200 }, -1);
    }
    public void shake_activity_back(View v) { // 标题栏 返回按钮
        this.finish();
    }
    public void linshi(View v) { // 标题栏
        Utils.startAnim(mImgUp, mImgDn);
    }

}
