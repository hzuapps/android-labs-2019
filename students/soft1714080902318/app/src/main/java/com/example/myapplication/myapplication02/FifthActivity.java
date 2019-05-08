package com.example.myapplication.myapplication02;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.myapplication.*;

public class FifthActivity extends AppCompatActivity {

    private LinearLayout layout;
    private SensorManager manager;
    private Sensor sensor;
    private int what = 0;

    private Vibrator vibrator;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                layout.setBackgroundResource(R.drawable.alicessr);
            } else if (msg.what == 1) {
                layout.setBackgroundResource(R.drawable.bluereimuur);
            } else if (msg.what == 2) {
                layout.setBackgroundResource(R.drawable.kaguyassr);
            } else if (msg.what == 3) {
                layout.setBackgroundResource(R.drawable.koishissr);
            } else if (msg.what == 4) {
                layout.setBackgroundResource(R.drawable.sanaessr);
            } else if (msg.what == 5) {
                layout.setBackgroundResource(R.drawable.tenshissr);
            } else if (msg.what == 6) {
                layout.setBackgroundResource(R.drawable.utsuhossr);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);
        layout = (LinearLayout) findViewById(R.id.fifth_activity);
        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        manager.registerListener(listener,sensor,manager.SENSOR_DELAY_NORMAL);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            float x = values[0];
            float y = values[1];
            float z = values[2];

            int coll = 15;
            if (Math.abs(x)>coll|Math.abs(y)>coll|Math.abs(z)>coll){
                long[] pattern = {300,500};
                vibrator.vibrate(pattern,-1);
                what++;
                if (what > 6){
                    what = 0;
                }
                handler.sendEmptyMessage(what);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onPause(){
        super.onPause();
        manager.unregisterListener(listener);
    }
}
