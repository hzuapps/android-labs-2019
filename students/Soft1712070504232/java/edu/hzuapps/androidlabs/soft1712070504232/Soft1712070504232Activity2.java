package edu.hzuapps.androidlabs.soft1712070504232;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class Soft1712070504232Activity2 extends Activity{
    ShakeListener mShakeListener = null;
    Vibrator mVibrator;
    private RelativeLayout mImgUp;
    private RelativeLayout mImgDn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1712070504232_activity_2);
        init();
    }
    private  void init(){
        mVibrator = (Vibrator)getApplication().getSystemService(VIBRATOR_SERVICE);
        mImgUp = (RelativeLayout) findViewById(R.id.shakeImgUp);
        mImgDn = (RelativeLayout)findViewById(R.id.shakeImgDown);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mShakeListener = new ShakeListener(this);
        mShakeListener.setOnShakeLister(new ShakeListener.OnShakeListener() {
            public void onShake() {
                final String hint = "没有找到同一时刻摇一摇的人";
                Utils.startAnim(mImgUp,mImgDn);
                mShakeListener.stop();
                startVibrato();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),hint,Toast.LENGTH_SHORT).show();
                        mVibrator.cancel();
                        mShakeListener.start();
                    }
                },2000);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mShakeListener != null){
            mShakeListener.stop();
        }
    }
    public void startVibrato(){
        mVibrator.vibrate(new long[]{500,200,500,200},1);
    }
    public void shake_by_button(View view){
        Utils.startAnim(mImgUp,mImgDn);
    }
    public void linshi(View v){
        this.finish();
        Utils.startAnim(mImgUp,mImgDn);
    }
}
