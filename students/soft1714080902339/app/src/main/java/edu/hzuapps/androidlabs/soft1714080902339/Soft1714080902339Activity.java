package edu.hzuapps.androidlabs.soft1714080902339;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Soft1714080902339Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902339_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button button_1 = (Button) findViewById(R.id.button_1 );
        /**
         * CXY笔记
         * 定义匿名类，不用大动干戈去创建一个类，只定义一个小方法来使用就好了
         */
        button_1 .setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent i=new Intent(Soft1714080902339Activity.this, SecondActivity.class);
                startActivity(i);
            }
        });

        if(!ConnectionUtil.isConn(getApplicationContext())){
            ConnectionUtil.setNetworkMethod(Soft1714080902339Activity.this);
        }


    }

}
