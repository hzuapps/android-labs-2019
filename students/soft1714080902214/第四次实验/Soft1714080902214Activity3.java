package edu.hzuapps.androidlabs.soft1714080902214;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Created by windowsPC on 2019/3/25.
 */

public class Soft1714080902214Activity3 extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902214_activity3);
        Log.i("Activity03", "onCreate()");
    }
    protected void Click(View view){
        Intent intent = new Intent(this,Soft1714080902214Activity4.class);
        startActivity(intent);
    }
}
