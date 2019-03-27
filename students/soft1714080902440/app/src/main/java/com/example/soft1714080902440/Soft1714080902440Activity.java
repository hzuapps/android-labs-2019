package com.example.soft1714080902440;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Soft1714080902440Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902440_activity);
    }
        public void onClick(View v) {
            Intent intent=new Intent(Soft1714080902440Activity.this,SecordActivity.class);
            startActivity(intent);
        }
}