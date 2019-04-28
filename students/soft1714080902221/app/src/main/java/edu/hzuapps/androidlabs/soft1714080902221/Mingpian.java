package edu.hzuapps.android.soft1714080902221;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import edu.hzuapps.androidlabs.shiyan7.R;

public class Mingpian extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mingpian);
        Button button = (Button) findViewById(R.id.xiugai);
        button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Mingpian.this, Soft1714080902221Activity_shiyan7.class);
                    startActivity(intent);
                }
            });
}}