package edu.hzuapps.androidlabs.Soft1714080902238;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import lun.com.myapplication.R;

public class displayActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Log.i("Activity01","onCreate()");
    }
    protected void Click(View view){
        Intent intent = new Intent(this,Soft1714080902238Activity.class);
        startActivity(intent);
    }
}
