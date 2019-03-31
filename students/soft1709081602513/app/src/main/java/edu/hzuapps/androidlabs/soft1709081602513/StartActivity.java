package edu.hzuapps.androidlabs.soft1709081602513;

import android.content.Context;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.util.AttributeSet;
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
        setContentView(R.layout.activity_start);
        mCave= findViewById(R.id.cave);
    }
}