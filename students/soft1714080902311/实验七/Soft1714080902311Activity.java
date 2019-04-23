package edu.hzuapps.androidlabs.soft1714080902311;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Soft1714080902311Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902311_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button imageButton2 = (Button) findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent i=new Intent(Soft1714080902311Activity.this, SecondActivity.class);
                startActivity(i);
            }
        });
        if(!ConnectionUtil.isConn(getApplicationContext())){
            ConnectionUtil.setNetworkMethod(Soft1714080902311Activity.this);
        }

    }



}
