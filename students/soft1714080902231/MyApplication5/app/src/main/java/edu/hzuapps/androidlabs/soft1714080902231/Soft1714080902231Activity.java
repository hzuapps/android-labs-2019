package edu.hzuapps.androidlabs.soft1714080902231;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class Soft1714080902231Activity extends AppCompatActivity {

    ImageButton imgButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902231_activity);
        imgButton =(ImageButton)findViewById(R.id.diyiye);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Soft1714080902231Activity.this, Soft1714080902231Activity2.class);
                startActivity(intent);
            }
        });
    }
}
