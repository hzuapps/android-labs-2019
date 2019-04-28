package edu.hzuapps.soft1714080902437test7;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SignActivity extends Activity implements View.OnClickListener {

        private SensorManager sensorManager;
        private Vibrator vibrator;
        private static final String TAG = "SignActivity";
        private static final int SENSOR_SHAKE = 10;
        private Button button7;


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sign);
            button7 = findViewById(R.id.button7);
            button7.setOnClickListener(this);


            sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent( SignActivity.this,No2Activity.class);
        startActivity(intent);
    }
        @Override
        protected void onResume() {
            super.onResume();
            if (sensorManager != null) {// 注册监听器
                sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
                // 第一个参数是Listener，第二个参数是所得传感器类型，第三个参数值获取传感器信息的频率
            }
            else{
                Toast.makeText(SignActivity.this, "您的手机不支持此功能！", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onPause() {
            super.onPause();
            if (sensorManager != null) {// 取消监听器
                sensorManager.unregisterListener(sensorEventListener);
            }
        }

        private SensorEventListener sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                // 传感器信息改变时执行该方法
                float[] values = event.values;
                float x = values[0]; // x轴方向的重力加速度，向右为正
                float y = values[1]; // y轴方向的重力加速度，向前为正
                float z = values[2]; // z轴方向的重力加速度，向上为正
                Log.i(TAG, "x轴方向的重力加速度" + x +  "；y轴方向的重力加速度" + y +  "；z轴方向的重力加速度" + z);
                // 一般在这三个方向的重力加速度达到40就达到了摇晃手机的状态。
                int medumValue = 13;
                if (Math.abs(x) > medumValue || Math.abs(y) > medumValue || Math.abs(z) > medumValue) {
                    vibrator.vibrate(200);
                    Message msg = new Message();
                    msg.what = SENSOR_SHAKE;
                    handler.sendMessage(msg);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case SENSOR_SHAKE:
                        Toast.makeText(SignActivity.this, "签到成功！", Toast.LENGTH_SHORT).show();
                        Log.i(TAG, "签到成功！");

                        break;
                }
            }

        };


    }



