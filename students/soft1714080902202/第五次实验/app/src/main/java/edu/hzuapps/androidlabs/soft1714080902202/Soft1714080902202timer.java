package edu.hzuapps.androidlabs.soft1714080902202;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.TimeZone;


public class Soft1714080902202timer extends AppCompatActivity {
    Calendar cal;
    String year;
    String month;
    String day;
    String hour;
    String minute;
    String second;
    String my_time_1;
    String my_time_2;
    Chronometer ch;
    String fileName= "data.txt";
    FileOutputStream fos;
    private long recordingTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

        year = String.valueOf(cal.get(Calendar.YEAR));
        month = String.valueOf(cal.get(Calendar.MONTH)+1);
        day = String.valueOf(cal.get(Calendar.DATE));
        if (cal.get(Calendar.AM_PM) == 0)
            hour = String.valueOf(cal.get(Calendar.HOUR));
        else
            hour = String.valueOf(cal.get(Calendar.HOUR)+12);
        minute = String.valueOf(cal.get(Calendar.MINUTE));
        second = String.valueOf(cal.get(Calendar.SECOND));

        my_time_1 = year + "-" + month + "-" + day;
        if(minute.length()>=0&&minute.length()<=1) {
            my_time_2 = hour + ":" + '0'+minute + ":" + second;
        }
        else {
            my_time_2 = hour + ":" +minute + ":" + second;
        }//获取系统时间


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902202timer);
        ch=findViewById(R.id.chronometer);
        Button btnOpen = findViewById(R.id.button6);    //返回主页面的按钮
        Button btnOpen1 = findViewById(R.id.button);    //开始计时的按钮
        Button btnOpen2 = findViewById(R.id.button3);   //停止计时的按钮
        Button btnOpen3 = findViewById(R.id.button4);   //复位按钮
        Button btnOpen4=findViewById(R.id.button8); //查看操作日志的按钮
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                System.out.println("返回主页");
                String content=my_time_1+" "+my_time_2+"  "+"返回主页";
                try{
                    fos=openFileOutput(fileName,MODE_PRIVATE);
                    fos.write(content.getBytes());
                    fos.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
                startActivity(new Intent(Soft1714080902202timer.this,Soft1714080902202MainPage.class));
            }
        });
        btnOpen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("开始计时");
                String content=my_time_1+" "+my_time_2+"  "+"开始计时";
                try{
                    fos=openFileOutput(fileName,MODE_PRIVATE);
                    fos.write(content.getBytes());
                    fos.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
                ch.setBase(SystemClock.elapsedRealtime() - recordingTime);
                ch.start();
            }
        });
        btnOpen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("暂停计时");
                String content=my_time_1+" "+my_time_2+"  "+"暂停计时";
                try{
                    fos=openFileOutput(fileName,MODE_PRIVATE);
                    fos.write(content.getBytes());
                    fos.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
                ch.stop();
                recordingTime = SystemClock.elapsedRealtime()
                                 - ch.getBase();
            }
        });
        btnOpen3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("复位");
                String content=my_time_1+" "+my_time_2+"  "+"复位";
                try{
                    fos=openFileOutput(fileName,MODE_PRIVATE);
                    fos.write(content.getBytes());
                    fos.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
                recordingTime = 0;
                ch.setBase(SystemClock.elapsedRealtime());
                ch.stop();
            }
        });
        btnOpen4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("查看日志");
                String content="";
                FileInputStream fis;
                try{
                    fis=openFileInput("data.txt");
                    byte[]buffer=new byte[fis.available()];
                    fis.read(buffer);
                    content=new String(buffer);
                }catch (Exception e){
                    e.printStackTrace();
                }
                Toast.makeText(Soft1714080902202timer.this,"日志："+content, 0).show();
            }
        });
    }
}