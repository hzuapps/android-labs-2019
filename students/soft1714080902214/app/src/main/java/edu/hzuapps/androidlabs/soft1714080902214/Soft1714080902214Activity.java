package edu.hzuapps.androidlabs.soft1714080902214;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Soft1714080902214Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902214_activity);
        Log.i("Activity01","onCreate()");
    }
    protected void onStart() {
        super.onStart();
        Log.i("Activity01", "onStart()");
    }
    protected void onRestart() {
        super.onRestart();
        Log.i("Activity01", "onRestart()");
    }

    protected void onResume() {
        super.onResume();
        Log.i("Activity01", "onResume()");
    }
    protected void onPause() {
        super.onPause();
        Log.i("Activity01", "onPause()");
    }

    protected void onStop() {
        super.onStop();
        Log.i("Activity01", "onStop()");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i("Activity01", "onDestroy()");
    }

    public void click(View view){
        Intent intent = new Intent(this,Soft1714080902214Activity2.class);
        startActivity(intent);
    }
}
