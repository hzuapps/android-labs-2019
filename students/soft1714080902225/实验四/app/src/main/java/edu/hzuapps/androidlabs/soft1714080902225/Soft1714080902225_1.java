package edu.hzuapps.androidlabs.soft1714080902225;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Soft1714080902225_1 extends AppCompatActivity {
    Button love;
    Button detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902225_1_activity);
        detail = (Button) findViewById(R.id.detail);
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Soft1714080902225_1.this, Soft1714080902225_2.class);
                startActivity(intent);

            }
        });
        View button = findViewById(R.id.love);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Soft1714080902225_1.this,Soft1714080902225_3.class);
                startActivity(intent);
            }
        });
            }

    }
