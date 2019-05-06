package edu.hzuapps.androidlabs.soft1714080902439;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import android.widget.Toast;

public class ShakeListener implements SensorEventListener {
    private static final int SPEED_SHRESHOLD=2000;
    //两次检测的时间间隔
    private static final int UPDATE_INTERVAL_TIME=70;
    //传感器管理器
    private SensorManager sensorManager;
    //传感器
    private Sensor sensor;
    //加速度感应监听器
    private OnShakeListener onShakeListener;
    //上下文
    private Context mContext;
    //上次检测时间
    private long lastUpdateTime;
    //手机上一个位置时加速度感应坐标
    private float lastX;
    private float lastY;
    private float lastZ;
    public ShakeListener(Context c){
        //获取监听对象
        mContext=c;
        start();
    }

    public void start() {
        //获得传感器管理器
        sensorManager=(SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager!=null){
            //获得加速度传感器
            sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
        //注册
        if (sensor!=null){
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_GAME);
        }else {
            Toast.makeText(mContext,"您的手机不支持该功能",Toast.LENGTH_SHORT).show();
        }
    }

    //加速度感应器感应获得变化数据
    @Override
    public void onSensorChanged(SensorEvent event) {
        //当前检测时间
        long currentUpdateTime=System.currentTimeMillis();
        //两次检测的时间间隔
        long timeInterval=currentUpdateTime-lastUpdateTime;
        //判断是否达到了检测时间间隔
        if (timeInterval<UPDATE_INTERVAL_TIME)
            return;
        //现在的时间变成last时间
        lastUpdateTime=currentUpdateTime;
        //获得x，y，z的坐标
        float x=event.values[0];
        float y=event.values[1];
        float z=event.values[2];
        //获得x，y，z的变化值
        float deltaX=x-lastX;
        float deltaY=y-lastY;
        float deltaZ=z-lastZ;
        //将现在的坐标变成last坐标
        lastX=x;
        lastY=y;
        lastZ=z;
        double speed=Math.sqrt(deltaX*deltaX+deltaY*deltaY+deltaZ*deltaZ)/timeInterval*10000;
        //达到速度阀值，发出提示
        if (speed>=SPEED_SHRESHOLD){
            onShakeListener.onShake();
        }
    }
    //摇晃监听接口
    public interface  OnShakeListener{
        public void onShake();
    }
    //停止检测
    public void stop(){
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
    public void setOnShakeListener(OnShakeListener listener){
        onShakeListener=listener;
    }
}
