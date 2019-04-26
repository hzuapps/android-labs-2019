package edu.hzuapps.androidlabs.soft1714080902306;

import android.content.Context;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.widget.LinearLayout;

import edu.hzuapps.androidlabs.R;

public class ShakeActivity extends AppCompatActivity {


    private LinearLayout layout;
    private SensorManager manager;
    private Sensor sensor;
    private int what = 0;
    //    获取震动服务对象
    private Vibrator vibrator;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==0) {
                layout.setBackgroundResource(R.drawable.jiangping05);
            }else if (msg.what==1) {
                layout.setBackgroundResource(R.drawable.jiangping03);
            }else if(msg.what==2){
                layout.setBackgroundResource(R.drawable.jiangping05);
            }else if (msg.what==3){
                layout.setBackgroundResource(R.drawable.jiangping02);
            }else if (msg.what==4){
                layout.setBackgroundResource(R.drawable.jiangping05);
            }else if (msg.what==5){
                layout.setBackgroundResource(R.drawable.jiangping04);
            }else if (msg.what==6){
                layout.setBackgroundResource(R.drawable.jiangping01);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);
        layout = (LinearLayout) findViewById(R.id.change_layout);
//        1.获取传感器服务管理对象
        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        2.获取传感器对象
        sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        3.注册传感器监听器
        manager.registerListener(listener,sensor,manager.SENSOR_DELAY_NORMAL);
//        震动服务对象
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    //    创建监听器对象
    SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            float x = values[0];
            float y = values[1];
            float z = values[2];

            int coll = 15;   //作为一个标准值
//            判断在什么情况下要切换图片
            if (Math.abs(x)>coll|Math.abs(y)>coll|Math.abs(z)>coll) {
                /**
                 * 300:摇晃了300毫秒之后，开始震动
                 * 500：震动持续的时间，震动持续了500毫秒。
                 * */
                long[]pattern = {300,500};
                vibrator.vibrate(pattern,-1);
                what++;
                if (what>6) {
                    what = 0;
                }
                handler.sendEmptyMessage(what);

            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    //    4.注销传感器监听器
    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(listener);
    }
}



