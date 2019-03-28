package edu.hzuapps.androidlabs.Soft1714080902238;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import edu.hzuapps.androidlabs.Soft1714080902238.pic;
import lun.com.myapplication.R;

/**
 * Created by 75661 on 2019/3/13.
 */

public class Soft1714080902238Activity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pic);
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
    public void Click(View view){
        Intent intent = new Intent(this,pic.class);
        startActivity(intent);
    }


}
