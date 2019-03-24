package com.example.plane;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {
    private Button btnStartAty1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStartAty1 = (Button) findViewById(R.id.btnStartAty1);
        btnStartAty1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Activity02.class);
                startActivity(i);
            }
        });
        System.out.println("onCreate");
    }
}