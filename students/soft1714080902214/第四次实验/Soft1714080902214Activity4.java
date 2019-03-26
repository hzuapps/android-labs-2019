package edu.hzuapps.androidlabs.soft1714080902214;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class Soft1714080902214Activity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902214_activity4);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
    }
    protected void Click(View view){
        Intent intent = new Intent(this,Soft1714080902214Activity3.class);
        startActivity(intent);
    }

}
