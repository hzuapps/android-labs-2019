package package edu.hzuapps.androidlabs.soft1714080902412;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.time.Month;
import java.time.MonthDay;
import java.time.Year;

public class Soft1714080902412Activity2 extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private TextView textView1;
    private  TextView textView2;
    int year=2019;
    int month=3;
    int day=28;
    int houre=0;
    int minute=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft17140809024122);
        button=(Button)findViewById(R.id.button21);
        button.setOnClickListener(this);
       textView1=(TextView) findViewById(R.id.date);
        textView2=(TextView) findViewById(R.id.time);

    }
    public void getDate(View v) {

        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month,
                                  int day) {
                Soft1714080902412Activity2.this.year = year;
                Soft1714080902412Activity2.this. month = month;
                Soft1714080902412Activity2.this. day = day;

            }
        }, 2019, 3, 29).show();
        showDate();
    }

    // 点击事件,湖区日期
    public void getTime(View v) {
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int houre, int minute) {
                Soft1714080902412Activity2.this.houre = houre;
                Soft1714080902412Activity2.this.minute = minute;
            }
        }, 0, 0, true).show();
        showTime();
    }

    // 显示选择日期
    private void showDate() {
     textView1.setText("你选择的日期是：" + year + "年" + month + "月" + day + "日");
    }

    // 显示选择日期
    private void showTime() {
        textView2.setText("你选择的时间是：" + houre + "时" + minute + "分");
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Soft1714080902412Activity2.this, Soft1714080902412Activity.class);
        startActivity(intent);
        Soft1714080902412Activity2.this.finish();
    }
    }
