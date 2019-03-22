package com.example.shiyan2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import edu.hzuapps.androidlabs.soft1714080901113.com1714080901113Activity;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.p1);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, SecordActivity.class);
                startActivity(intent);
            }
        });
    }
}
