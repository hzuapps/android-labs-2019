package edu.hzuapps.androidlabs.com1714080901132;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;


public class shiyan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shiyan);
        Button button=(Button) findViewById(R.id.openCamera);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction("android.media.action.IMAGE_CAPTURE");
                intent.addCategory("android.intent.category.DEFAULT");
                startActivity(intent);
            }
        });
    }
}
