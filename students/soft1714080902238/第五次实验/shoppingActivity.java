package edu.hzuapps.androidlabs.Soft1714080902238;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import lun.com.myapplication.R;


public class shoppingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
    }
    protected void Click1(View view){
        Intent intent = new Intent(this,carActivity.class);
        startActivity(intent);
    }
}
