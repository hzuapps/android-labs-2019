package com.example.edu.hzuapps.androidlabs.soft1714080902316;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.edu.hzuapps.androidlabs.R;

public class Soft1714080902316 extends AppCompatActivity {
    private SensorManager mSensroMgr;
    private Vibrator mVibrator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSensroMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        mVibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902316);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Soft1714080902316.this, Writing.class) ;
                startActivity(intent);
            }
        });
        }
    @Override
    protected void onResume() {
        super.onResume();
        if (mSensroMgr != null) {
            mSensroMgr.registerListener(sensorEventListener, mSensroMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (mSensroMgr != null) {
            mSensroMgr.unregisterListener(sensorEventListener);
        }
    }
    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            float x = values[0];
            float y = values[1];
            float z = values[2];
            int medumValue = 19;
            if (Math.abs(x) > medumValue || Math.abs(y) > medumValue || Math.abs(z) > medumValue) {
                Intent intent = new Intent(Soft1714080902316.this, Feedback.class) ;
                startActivity(intent);
                onStop();
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

}
