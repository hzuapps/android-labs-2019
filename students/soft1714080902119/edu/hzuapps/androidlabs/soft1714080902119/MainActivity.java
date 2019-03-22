package edu.hzuapps.androidlabs.soft1714080902119;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.startbutton);
        button.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.startbutton){
            // 另一个Activity的完整名称 = edu.androidlabs.soft123456(包名小写).Soft123456Activity(类名)
            Intent intent = new Intent(MainActivity.this,Soft1714080902119Activity.class);
            startActivity(intent);
        }
    }
}
