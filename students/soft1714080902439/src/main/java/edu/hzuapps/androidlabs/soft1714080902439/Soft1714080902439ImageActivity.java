package edu.hzuapps.androidlabs.soft1714080902439;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soft171408092439.R;

public class Soft1714080902439ImageActivity extends AppCompatActivity{
    private TextView textView;
    //private  Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902439_image_activity);
        textView = (TextView) findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Toast text=Toast.makeText(edu.hzuapps.androidlabs.soft1714080902439.Soft1714080902439ImageActivity.this,"123",Toast.LENGTH_SHORT);
                // 另一个Activity的完整名称 = edu.androidlabs.soft123456(包名小写).Soft123456Activity(类名)
                Intent intent = new Intent(edu.hzuapps.androidlabs.soft1714080902439.Soft1714080902439ImageActivity.this,edu.hzuapps.androidlabs.soft1714080902439.Soft1714080902439Activity.class);
                startActivity(intent);
                //text.show();

            }
        });
    }
}
