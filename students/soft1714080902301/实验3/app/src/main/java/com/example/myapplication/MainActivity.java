package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button) findViewById(R.id.v);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    // 另一个Activity的完整名称 = edu.androidlabs.soft123456(包名小写).Soft123456Activity(类名)
                    Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(intent);
                    //startActivity(intent);

                }
        });
    }
}
