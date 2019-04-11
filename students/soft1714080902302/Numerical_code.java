package com.example.soft1714080902302.first_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.soft1714080902302.R;
import com.example.soft1714080902302.second_activity.Activity_numerical_code_local;
import com.example.soft1714080902302.second_activity.Activity_numerical_code_network;


public class Numerical_code extends AppCompatActivity {
    private Button local_button;
    private Button network_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numerical_code);


        local_button = (Button) findViewById(R.id.local_button);
        network_button = (Button) findViewById(R.id.network_button);
        local_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Numerical_code.this, Activity_numerical_code_local.class);
                startActivity(intent);
            }
        });
        network_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(Numerical_code.this, Activity_numerical_code_network.class);
                startActivity(intent1);
            }
        });
    }

}
