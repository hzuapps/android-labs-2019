package edu.hzuapps.androidlabs.demo2;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Soft1714080902220MainActivity extends Activity {
    TimePicker timepicker; 			// 时间拾取器
    Calendar c; 					// 日历对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c=Calendar.getInstance();                  //获取日历对象
        timepicker = (TimePicker)findViewById(R.id.timePicker1);            // 获取时间拾取组件
        timepicker.setIs24HourView(true);                            // 设置使用24小时制
        Button button1 = (Button) findViewById(R.id.set); 					// 获取“设置闹钟”按钮
        // 为“设置闹钟”按钮添加单击事件监听器
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Soft1714080902220MainActivity.this,
                        Soft1714080902220AlarmActivity.class);                            // 创建一个Intent对象
                PendingIntent pendingIntent = PendingIntent.getActivity(
                        Soft1714080902220MainActivity.this, 0, intent, 0);                    // 获取显示闹钟的PendingIntent对象
                // 获取AlarmManager对象
                AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                c.set(Calendar.HOUR_OF_DAY, timepicker.getCurrentHour());    // 设置闹钟的小时数
                c.set(Calendar.MINUTE, timepicker.getCurrentMinute());        // 设置闹钟的分钟数
                c.set(Calendar.SECOND,0);                                      // 设置闹钟的秒数
                alarm.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),
                        pendingIntent);                                // 设置一个闹钟
                Toast.makeText(Soft1714080902220MainActivity.this, "闹钟设置成功", Toast.LENGTH_SHORT)
                        .show();                                    // 显示一个消息提示
            }
        });

    }
}
