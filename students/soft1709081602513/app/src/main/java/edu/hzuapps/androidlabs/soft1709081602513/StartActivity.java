package edu.hzuapps.androidlabs.soft1709081602513;

import android.content.Context;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.util.AttributeSet;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {


    private TextView mCave;
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

        setContentView(R.layout.activity_start);
        mCave= findViewById(R.id.cave);
    }
}