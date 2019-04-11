package edu.hzuapps.androidlabs.soft1714080902321;

import android.os.Bundle;

import android.widget.TextView;

import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.content.Intent;


public class Soft1714080902321Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902321_activity);
        TextView btnOpen =  findViewById(R.id.Button_1);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Soft1714080902321Activity.this,Soft1714080902321Activity2.class));
            }
        });
    }
}

