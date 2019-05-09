package edu.hzuapps.androidlabs.soft1714080902401;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.concurrent.ExecutionException;


public class Soft1714080902401Activity extends AppCompatActivity {

    private static final String TAG = "tag";
    private ImageView mfpimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft_1714080902401);

        mfpimage=(ImageView) findViewById(R.id.fpimage);
        Glide.with(this).load("https://b-ssl.duitang.com/uploads/item/201903/17/20190317150323_opsor.thumb.700_0.jpg").error(R.drawable.errorview).into(mfpimage);

        Button begin = (Button) findViewById(R.id.Begin);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Soft1714080902401Activity.this,Soft1714080902401_01Activity.class);
                startActivity(intent);
            }
        });
    }
}

