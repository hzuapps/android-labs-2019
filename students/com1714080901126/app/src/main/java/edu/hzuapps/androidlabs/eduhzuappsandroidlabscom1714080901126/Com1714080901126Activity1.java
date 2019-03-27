package edu.hzuapps.androidlabs.eduhzuappsandroidlabscom1714080901126;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Com1714080901126Activity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
            }


public void skip(View view){
                 Intent intent=new Intent();
                 intent.setClass(Com1714080901126Activity1.this, Com1714080901126Activity2.class);
                 startActivity(intent);
             }
}
