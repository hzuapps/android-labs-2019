package com.androidWork;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

public class Soft1714080902132Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902132);


        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                    // 另一个Activity的完整名称 = edu.androidlabs.soft123456(包名小写).Soft123456Activity(类名)
                    Intent intent = new Intent(Soft1714080902132Activity.this,secondActivity.class);
                    startActivity(intent);

            }
        });
    }


}
