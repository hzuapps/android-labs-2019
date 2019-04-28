package edu.hzuapps.androidlabs.soft1714080902225;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


public class Soft1714080902225_1<view> extends AppCompatActivity {
    private SensorManager sensorManager;
    private Vibrator vibrator;
    Button love;
    Button detail;
    private EditText et_info;
    private Button btn_save;
    private Button btn_read;

    private ImageView up;
    private ImageView down;
    //上一次晃动手机的时间
    private long lastTime;
    private SoundPool soundPool;
    private int sound1;

    private SensorEventListener listener = new SensorEventListener() {
        //当手机的加速度发生变化时调用
        @Override
        public void onSensorChanged(SensorEvent event) {
            //获取手机在不同方向上加速度的变化
            float valuesX = Math.abs(event.values[0]);
            float valuesY = Math.abs(event.values[1]);
            float valuesZ = Math.abs(event.values[2]);

            if (valuesX > 17 || valuesY > 17 || valuesZ > 17) {
                startAnimation();
                playSound();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };


    private void playSound() {
        //1.声音的id
        //2.3.表示左右声道的音量
        //4.优先级
        //5.是否循环
        //6.声音播放速率
        soundPool.play(sound1, 1, 1, 0, 0, 1);
    }

    private void startAnimation() {
        //如果两次晃动手机的时间小于1秒，则只执行一次动画
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - lastTime < 1000) {
            return;
        }
        lastTime = currentTimeMillis;
        AnimationSet upSet = new AnimationSet(true);
        TranslateAnimation upUp = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF,
                0, TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, -1);
        upUp.setDuration(1000);
        TranslateAnimation upDown = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF,
                0, TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 1);
        upDown.setDuration(1000);
        upDown.setStartOffset(1000);
        upSet.addAnimation(upUp);
        upSet.addAnimation(upDown);
        up.startAnimation(upSet);
        AnimationSet downSet = new AnimationSet(true);
        TranslateAnimation downUp = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF,
                0, TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 1);
        downUp.setDuration(1000);
        TranslateAnimation downDown = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF,
                0, TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, -1);
        downDown.setDuration(1000);
        downDown.setStartOffset(1000);
        downSet.addAnimation(downUp);
        downSet.addAnimation(downDown);
        down.startAnimation(downSet);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902225_1_activity);
        up = ((ImageView) findViewById(R.id.up));

        initSensor();
        initSoundPool();
        //获取手机震动服务
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
    }

    private void initSensor() {
    }

    /**
     * 初始化声音池
     */
    private void initSoundPool() {
        if (Build.VERSION.SDK_INT > 20) {
            SoundPool.Builder builder = new SoundPool.Builder();
            //1.最大并发流数
            builder.setMaxStreams(3);
            AudioAttributes.Builder aaBuilder = new AudioAttributes.Builder();
            aaBuilder.setLegacyStreamType(AudioManager.STREAM_MUSIC);
            builder.setAudioAttributes(aaBuilder.build());
            soundPool = builder.build();
        } else {
            soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        }



        try {
//            InputStream is = this.getAssets().open("test.json");//eclipse
            InputStream is = Soft1714080902225_1.this.getClass().getClassLoader().getResourceAsStream("assets/" + "text.json");//android studio
            BufferedReader bufr = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = bufr.readLine()) != null) {
                builder.append(line);
            }
            is.close();
            bufr.close();
            try {
                JSONObject root = new JSONObject(builder.toString());
                Log.d("info", "cat=" + root.getString("cat"));
                JSONArray array = root.getJSONArray("languages");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject lan = array.getJSONObject(i);
                    Log.d("info", "-----------------------");
                    Log.d("info", "id=" + lan.getInt("id"));
                    Log.d("info", "ide=" + lan.getString("ide"));
                    Log.d("info", "name=" + lan.getString("name"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONObject root = new JSONObject();
            root.put("cat", "it");

            JSONObject lan1 = new JSONObject();
            lan1.put("id", 1);
            lan1.put("ide", "Eclipse");
            lan1.put("name", "Java");

            JSONObject lan2 = new JSONObject();
            lan2.put("id", 2);
            lan2.put("ide", "XCode");
            lan2.put("name", "Swift");

            JSONObject lan3 = new JSONObject();
            lan3.put("id", 3);
            lan3.put("ide", "Visual Studio");
            lan3.put("name", "C#");

            JSONArray array = new JSONArray();
            array.put(lan1);
            array.put(lan2);
            array.put(lan3);

            root.put("languages", array);

            Log.d("info", root.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }


        et_info = (EditText) findViewById(R.id.et_info);
        Button btn_save = (Button) findViewById(R.id.btn_save);
        btn_read = (Button) findViewById(R.id.btn_read);
        btn_save.setOnClickListener((OnClickListener) new ButtonListener());
        btn_read.setOnClickListener((OnClickListener) new ButtonListener());

        detail = (Button) findViewById(R.id.detail);
        detail.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Soft1714080902225_1.this, Soft1714080902225_2.class);
                startActivity(intent);
            }
        });
        View button = findViewById(R.id.love);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Soft1714080902225_1.this, Soft1714080902225_3.class);
                startActivity(intent);
            }
        });
    }

    public Button getbtn_save() {
        return btn_save;
    }

    public void setBtn_save(Button btn_save) {
        this.btn_save = btn_save;
    }

    private class ButtonListener implements OnClickListener {
        private view v;

        @SuppressLint("WrongConstant")
        public void onclick(View view) {
            switch (view.getId()) {
                case R.id.btn_save:
                    String saveinfo = et_info.getText().toString().trim();
                    FileOutputStream fos;
                    try {
                        fos = openFileOutput("data.txt", Context.MODE_APPEND);
                        fos.write(saveinfo.getBytes());
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Toast.makeText(Soft1714080902225_1.this, "数据保存成功", 0).show();

                    break;
                case R.id.btn_read:
                    String content = "";
                    try {
                        FileInputStream fis = openFileInput("data.txt");
                        byte[] buffer = new byte[fis.available()];
                        fis.read(buffer);
                        content = new String(buffer);
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft1714080902225_1.this, "保存的数据是：" + content, 0).show();
                    break;
                default:
                    break;
            }
        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_GAME);
    }
    //获取 SensorManager 负责管理传感器
    //SensorManager mSensorManager = ((SensorManager) getSystemService(SENSOR_SERVICE));
    //if (mSensorManager != null) {
    //获取加速度传感器
    //  mAccelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    // if (mAccelerometerSensor != null) {
    //     mSensorManager.registerListener(this, mAccelerometerSensor, SensorManager.SENSOR_DELAY_UI);
    // }
    // }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除对加速度传感器的监听
        sensorManager.unregisterListener(listener);
        sensorManager.unregisterListener(listener);
        if (soundPool != null) {
            //声音池释放资源
            soundPool.release();
        }

    }



}
