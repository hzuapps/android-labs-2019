package edu.hzuapps.androidlabs.soft1714080902337;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

public class Soft1714080902337Activity2 extends AppCompatActivity {
    Button button = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902337_activity2);
        button = findViewById(R.id.bb1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Soft1714080902337Activity2.this,Soft1714080902337Activity3.class);
                startActivity(intent);
            }
        });
        button = findViewById(R.id.bb2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Soft1714080902337Activity2.this,Soft1714080902337Activity4.class);
                startActivity(intent);
            }
        });
    }
}
