package edu.hzuapps.androidlabs.soft1714080902329;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;



public class Soft1714080902329Activity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902329);
        textView =(TextView) findViewById(R.id.brother1);


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Soft1714080902329Activity.this, Brother.class);
                startActivity(intent);
            }


        });
    }}

