package edu.hzuapps.androidlabs.soft1714080902337;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myapplication.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Soft1714080902337Activity4 extends AppCompatActivity implements SensorEventListener{
    private Button btn;
    private ImageView imageview;
    //创建传感器管理器
    private SensorManager sensorManager;
    private Sensor sensor;
    //创建手机坐标
    private float lastX;
    private float lastY;
    private float lastZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902337_activity4);
//<---点击按钮加载网络图片代码
        btn = (Button) this.findViewById(R.id.button);
        imageview = (ImageView) this.findViewById(R.id.imageView);
        StrictMode.setThreadPolicy(new
                StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(
                new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                btn.setEnabled(false);
                String strURL = "https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=de279ebb828ba61ecbe3c07d205dfc6f/29381f30e924b899c613e1496e061d950a7bf690.jpg";
                try {
                    Bitmap bitmap = getBitmap(strURL);
                    imageview.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        //点击按钮加载网络图片代码--->

        //<---摇一摇代码
        //通过调用getSystemService()方法并传入SENSOR_SERVICE参数来创建类的实例
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //通过调用getDefaultSensor()方法并使用TYPE_ACCELEROMETER常量(动作检测（抖动，倾斜等）)
        // 来获取设备上特定类型的传感器
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //摇一摇代码--->
    }
    public Bitmap getBitmap(String path) throws IOException {
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == 200) {
                InputStream inputStream = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //<---摇一摇代码
    //使用onsensorchanged（）方法来监视来自抖动传感器的数据
    public void onSensorChanged(SensorEvent event) {
        //接收传感器传回来的手机坐标信息
        lastX = event.values[0];
        lastY = event.values[1];
        lastZ = event.values[2];
        //将坐标进行运算
        double shack = Math.sqrt(lastX*lastX + lastY*lastY +lastZ*lastZ);
        //用来查看当前手机坐标
        //判断摇晃程度，防止轻微摇晃
        if(shack >= 12){
            //达到点击按钮的功能
            btn.setEnabled(false);
            String strURL = "https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=de279ebb828ba61ecbe3c07d205dfc6f/29381f30e924b899c613e1496e061d950a7bf690.jpg";
            try {
                Bitmap bitmap = getBitmap(strURL);
                imageview.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    //使用onresume（）和onpause（）回调方法来注册和注销传感器事件侦听器
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener((SensorEventListener) this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        //注销传感器侦听器
        //可以在暂停时将监听器取消,减少手机耗电
        super.onPause();
        sensorManager.unregisterListener((SensorEventListener) this);
    }
    //摇一摇代码--->
}