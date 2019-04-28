package com.edu.hzuapps.androidlabs.soft1714080902307.Module.Function.CatGame;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.edu.hzuapps.androidlabs.soft1714080902307.R;


public class SleepCatActivity extends AppCompatActivity implements SensorEventListener {
    private TextView tv_shake;
    private SensorManager mSensroMgr;
    private Vibrator mVibrator;
    private ImageButton imageButton0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cat_sleep);

        ImageButton imageButton0 = findViewById(R.id.cat_sleep);

        imageButton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalDialog();
            }
        });
        mSensroMgr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }
    private void showNormalDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(SleepCatActivity.this);

        normalDialog.setTitle("提示");
        normalDialog.setMessage("隔着玻璃呢，摸不到哦");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });

        normalDialog.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensroMgr.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensroMgr.registerListener(this,
                mSensroMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            // values[0]:X轴，values[1]：Y轴，values[2]：Z轴
            float[] values = event.values;
            if ((Math.abs(values[0]) > 15 || Math.abs(values[1]) > 15
                    || Math.abs(values[2]) > 15)) {
                Intent intent3 = new Intent();
                intent3.setClass(SleepCatActivity.this, AwakeCatActivity.class);
                startActivity(intent3);
                finish();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //当传感器精度改变时回调该方法，一般无需处理
    }


}