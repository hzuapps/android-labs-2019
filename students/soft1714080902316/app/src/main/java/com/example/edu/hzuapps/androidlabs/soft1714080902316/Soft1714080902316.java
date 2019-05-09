package com.example.edu.hzuapps.androidlabs.soft1714080902316;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.edu.hzuapps.androidlabs.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Soft1714080902316 extends AppCompatActivity {
    private SensorManager mSensroMgr;
    private Vibrator mVibrator;
    private Button writebutton;
    private Button loginbutton;
    private TextView userview;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String str= parseJson("  "+ msg.obj);
            userview.setText(str);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSensroMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        mVibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902316);
        writebutton = findViewById(R.id.writebutton);
        loginbutton = findViewById(R.id.loginbutton);
        userview=findViewById(R.id.userview);
        writebutton.setOnClickListener(new ButtonListener());
        loginbutton.setOnClickListener(new ButtonListener());
        }
    private class ButtonListener implements OnClickListener{
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.writebutton:
                    Intent intent = new Intent(Soft1714080902316.this, Writing.class) ;
                    startActivity(intent);
                    break;
                case R.id.loginbutton:
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                URL url = new URL("https://raw.githubusercontent.com/DengXF1225/android-labs-2019/master/students/soft1714080902316/user.json");
                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                conn.setConnectTimeout(5000);
                                conn.setRequestMethod("GET");
                                conn.connect();
                                InputStream inputStream = conn.getInputStream();
                                InputStreamReader is = new InputStreamReader(inputStream);
                                BufferedReader buffer = new BufferedReader(is);
                                if (conn.getResponseCode() == 200) {//200意味着返回的是"OK"
                                    String inputLine;
                                    StringBuffer resultData = new StringBuffer();//StringBuffer字符串拼接
                                    while ((inputLine = buffer.readLine()) != null) {
                                        resultData.append(inputLine);
                                    }
                                    String data = resultData.toString();
                                    Message msg = handler.obtainMessage();
                                    msg.obj = data;
                                    handler.sendMessage(msg);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    break;
            }
        }
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
    public String parseJson(String text){
        StringBuilder info=new StringBuilder();
        String username="";
        try{
            JSONArray person=new JSONArray(text);
            int length=person.length();
            for (int i=0;i<length;i++){
                JSONObject object=person.getJSONObject(i);
                username=object.getString("name");
                info.append(username);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return username;
    }
}