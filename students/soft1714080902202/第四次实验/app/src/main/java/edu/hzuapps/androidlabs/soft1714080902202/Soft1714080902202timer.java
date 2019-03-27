package edu.hzuapps.androidlabs.soft1714080902202;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Soft1714080902202timer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902202timer);
        Button btnOpen = findViewById(R.id.button6);    //返回主页面的按钮
        Button btnOpen1 = findViewById(R.id.button);    //开始计时的按钮
        Button btnOpen2 = findViewById(R.id.button3);   //停止计时的按钮
        Button btnOpen3 = findViewById(R.id.button4);   //复位按钮
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("返回主页");
                startActivity(new Intent(Soft1714080902202timer.this,Soft1714080902202MainPage.class));
            }
        });
        btnOpen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("开始计时");
            }
        });
        btnOpen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("停止计时");
            }
        });
        btnOpen3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("复位");
            }
        });
    }
}