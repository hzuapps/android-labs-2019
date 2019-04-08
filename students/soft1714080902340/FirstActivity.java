package com.cap.pomodoros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pomodoros_layout); //调用setContentView()方法来给当前的活动加载一个布局
    }
}
