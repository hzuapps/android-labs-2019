package edu.hzuapps.androidlabs.soft1714080902226activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Soft1714080902226Activity extends AppCompatActivity {

    private ImageView mImageView1;
    private Button button1;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902226_activity);
        mImageView1 = findViewById(R.id.fanyan);
        mImageView1.setImageResource(R.drawable.cover);

        button1 = (Button) findViewById(R.id.jinru);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(edu.hzuapps.androidlabs.soft1714080902226activity.Soft1714080902226Activity.this, Soft1714080902226Activity2.class);
                startActivity(intent);
            }
        });
    }
}
