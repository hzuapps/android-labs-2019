package edu.hzuapps.androidlabs.soft1714080902321;

import android.os.Bundle;


import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.content.Intent;


public class Soft1714080902321Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902321_activity);
        Button button1=(Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Soft1714080902321Activity.this,Soft1714080902321Activity2.class);
                startActivity(intent);
            }
        });
    }
}

