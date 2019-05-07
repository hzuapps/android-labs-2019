package edu.hzuapps.androidlabs.soft1714080902229;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.File;

public class AlarmActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    int i=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(listener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        if(ContextCompat.checkSelfPermission(AlarmActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(AlarmActivity.this,new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},1
            );
        }
        else{
            initMediaPlayer();
        }
        mediaPlayer.start();
    }
    private void initMediaPlayer(){
        try{
            File file = new File(Environment.getExternalStorageDirectory(),"sing.mp3");
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults){
        switch (requestCode){
            case 1:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initMediaPlayer();
                }else {
                    Toast.makeText(this,"拒绝权限将无法使用权限", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }
   /* @Override
    protected void onDestroy(){
        super.onDestroy();
        if(sensorManager!=null){
            sensorManager.unregisterListener(listener);
        }
    }*/


    private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            //int i=0;
            float xValue = Math.abs(event.values[0]);
            float yValue = Math.abs(event.values[1]);
            float zValue = Math.abs(event.values[2]);
            if(xValue>30 || yValue>30 || zValue>30 ){

                Toast.makeText(AlarmActivity.this,"你摇了！加油",Toast.LENGTH_SHORT).show();
                i++;
            }
            // i++;
            if(i==30){
                //Toast.makeText(AlarmActivity.this,"不摇动了",Toast.LENGTH_SHORT).show();
                // onDestroy();
                sensorManager.unregisterListener(listener);
                mediaPlayer.reset();

            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
