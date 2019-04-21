package edu.hzuapps.androidlabs.soft1707080412124;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import static java.security.AccessController.getContext;


public class Soft1707080412124Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1707080412124_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
                Intent intent = new Intent(Soft1707080412124Activity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });

        Button btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Soft1707080412124Activity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        if(!ConnectionUtil.isConn(getApplicationContext())){
            ConnectionUtil.setNetworkMethod(Soft1707080412124Activity.this);
        }

    }

}

