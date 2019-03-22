package com.example.tuku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Main2Activity extends AppCompatActivity {

    private View startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void onClick(View v) {
        if(v.equals(startButton)) {
            // 另一个Activity的完整名称 = edu.androidlabs.soft123456(包名小写).Soft123456Activity(类名)
            Intent intent = new Intent("soft1714080902227Activity");
            startActivity(intent);
        }
    }

}
