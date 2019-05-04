package edu.hzuapps.androidlabs.Soft1714080902238;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import lun.com.myapplication.R;


public class ShoppingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
    }
    protected void Click1(View view){
        Intent intent = new Intent(this,CarActivity.class);
        startActivity(intent);
    }
    protected void Click2(View view){
        Intent intent = new Intent(this,DownPicActivity.class);
        startActivity(intent);

    }
}
