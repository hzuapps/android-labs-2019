package com.example.th;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;


public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final MyImageView myImageView = (MyImageView) findViewById(R.id.zz_view);
        Button button_get = (Button) findViewById(R.id.button_load);
        button_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myImageView.setImageURL("https://raw.githubusercontent.com/alicekagiyama/png-from-pixiv/master/zz_p"+new Random().nextInt(5) +".png");
            }
        });


}}
