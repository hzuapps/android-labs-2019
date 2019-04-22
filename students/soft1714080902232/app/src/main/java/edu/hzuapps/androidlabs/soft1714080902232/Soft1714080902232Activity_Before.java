package edu.hzuapps.androidlabs.soft1714080902232;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class Soft1714080902232Activity_Before extends AppCompatActivity {
    final String LOG = "------ScreenOrientation------" ;
    Button b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        if (savedInstanceState == null) {
            setContentView(R.layout.soft_1714080902232_activity_before);
            b1 = (Button) findViewById(R.id.button1);
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Soft1714080902232Activity_Before.this, Soft1714080902232Activity.class);
                    startActivity(intent);
                }
            });
            b2 = (Button) findViewById(R.id.button2);
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Soft1714080902232Activity_Before.this, Soft1714080902232Activity_Success.class);
                    startActivity(intent);
                }
            });
            b3 = (Button) findViewById(R.id.button3);
            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Soft1714080902232Activity_Before.this, Soft1714080902232Activity_Web.class);
                    startActivity(intent);
                }
            });
        }
        if(savedInstanceState != null){
            //横屏
            if( ScreenOrient(this)==ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE  )
                setContentView(R.layout.soft_1714080902232_activity_before);
            //竖屏
            if( ScreenOrient(this)==ActivityInfo.SCREEN_ORIENTATION_PORTRAIT  )
                setContentView(R.layout.soft_1714080902232_activity_before2);
            String temp = savedInstanceState.getString("data_key") ;
            Log.d(LOG , "重新创建了Activity，之前保存的内容是"+temp) ;
        }
    }
    public int ScreenOrient(Activity activity)
    {
        int orient = activity.getRequestedOrientation();
        if(orient != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE && orient != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            WindowManager windowManager = activity.getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            int screenWidth  = display.getWidth();
            int screenHeight = display.getHeight();
            orient = screenWidth < screenHeight ?  ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE:ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        }
        return orient;
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String tempData = "Something you want to save";
        outState.putString("data_key", tempData);
        Log.d(LOG, "onSaveInstanceState..");
    }
}
