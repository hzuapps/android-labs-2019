package com.example.myandroid_2_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.logging.Logger;

public class Soft1714080902416Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_soft1714080902416);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        Button button = findViewById(R.id.button);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Soft1714080902416Activity.this, MainActivity.class);
                startActivity(intent);
            }
        };

        Button button1 = findViewById(R.id.button1);
        View.OnClickListener onClickListener1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Soft1714080902416Activity.this, NewCreateActivity.class);
                startActivity(intent1);
            }
        };

        Button button2 = findViewById(R.id.button2);
        View.OnClickListener onClickListener2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Soft1714080902416Activity.this, GetInternetActivity.class);
                startActivity(intent2);
            }
        };

        Button camera=findViewById(R.id.camera);
        View.OnClickListener onClickListener3=new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent3 = new Intent(Soft1714080902416Activity.this,CameraActivity.class);
                startActivity(intent3);

            }
        };


        button.setOnClickListener(onClickListener);
        button1.setOnClickListener(onClickListener1);
        button2.setOnClickListener(onClickListener2);
        camera.setOnClickListener(onClickListener3);
    }
}
