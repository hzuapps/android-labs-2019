package com.example.edu.hzuapps.androidlabs.soft1714080902316;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.edu.hzuapps.androidlabs.R;

public class Feedback extends AppCompatActivity {
private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        button = findViewById(R.id.button3);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
}
}
