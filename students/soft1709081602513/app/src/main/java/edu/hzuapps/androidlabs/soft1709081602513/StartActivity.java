package edu.hzuapps.androidlabs.soft1709081602513;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.util.AttributeSet;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.TextView;



public class StartActivity extends AppCompatActivity {
    final String LOG = "------Screen------" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        }//使横屏

        // 应用运行时，保持屏幕高亮，不锁屏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        // 全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN) ;
        //Activity的方向由方向传感器来决定，显示会根据用户设备的移动情况来旋转
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
//        setContentView(R.layout.activity_start);

        if (savedInstanceState == null) {
            setContentView(R.layout.activity_start);
        }
        //检测是横屏还是竖屏
        if(savedInstanceState != null){
            //若为横屏
            if( ScreenOrient(this)==ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE  )
                setContentView(R.layout.activity_start_landscape);
            //竖屏
            if( ScreenOrient(this)==ActivityInfo.SCREEN_ORIENTATION_PORTRAIT  )
                setContentView(R.layout.activity_start);
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
        Log.v(LOG, "onSaveInstanceState..");
    }

}