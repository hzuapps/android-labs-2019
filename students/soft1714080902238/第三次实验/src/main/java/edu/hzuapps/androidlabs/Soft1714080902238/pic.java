package edu.hzuapps.androidlabs.Soft1714080902238;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import lun.com.myapplication.R;

/**
 * Created by 75661 on 2019/3/18.
 */

public class pic extends Activity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft1714080902238activity);
        Log.i("Activity02", "onCreate()");
    }
    protected void onStart() {
        super.onStart();
        Log.i("Activity02", "onStart()");
    }
    protected void onRestart() {
        super.onRestart();
        Log.i("Activity02", "onRestart()");
    }
    protected void onResume() {
        super.onResume();
        Log.i("Activity02", "onResume()");
    }
    protected void onPause() {
        super.onPause();
        Log.i("Activity02", "onPause()");
    }
    protected void onStop() {
        super.onStop();
        Log.i("Activity02", "onStop()");
    }
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Activity02", "onDestroy()");
    }
    protected void Click(View view){
        Intent intent = new Intent(this,Soft1714080902238Activity.class);
        startActivity(intent);
    }

}
