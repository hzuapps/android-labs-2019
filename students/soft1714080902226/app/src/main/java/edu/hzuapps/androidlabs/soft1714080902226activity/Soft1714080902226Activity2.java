package edu.hzuapps.androidlabs.soft1714080902226activity;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Soft1714080902226Activity2 extends AppCompatActivity {

    private ImageView mImageView;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_soft17140809022262);
        mImageView = findViewById(R.id.diyiye);
        mImageView.setImageResource(R.drawable.tu2);
    }
}
