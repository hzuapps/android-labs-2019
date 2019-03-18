package edu.hzuapps.androidlabs.soft1714080902214;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class Soft1714080902214Activity2 extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902214_activity2);
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
        Intent intent = new Intent(this,Soft1714080902214Activity.class);
        startActivity(intent);
    }
}
