package edu.hzuapps.androidlabs.soft1714080902335activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Soft1714080902335Activity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902335);
        button=(Button)findViewById(R.id.di01);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Soft1714080902335Activity.this,SecondActivity.class);
                startActivity(intent);
            }
        });

        button=(Button)findViewById(R.id.di02);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Soft1714080902335Activity.this,ThirdActivity.class);
                startActivity(intent);
            }
        });
        button=(Button)findViewById(R.id.di03);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Soft1714080902335Activity.this,fifth.class);
                startActivity(intent);
            }
        });
    }
}