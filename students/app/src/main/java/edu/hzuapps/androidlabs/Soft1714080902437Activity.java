package edu.hzuapps.androidlabs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class Soft1714080902437Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902437);
    }


    @SuppressLint("WrongConstant")
    public boolean onKeyDown(int keyCode, KeyEvent event){
        Toast.makeText(this,"点击签到！",0).show();
        return super.onKeyUp(keyCode,event);
    }
    @SuppressLint("WrongConstant")
    public boolean onKeyUp(int keyCode, KeyEvent event){
        Toast.makeText(this,"签到完成！",0).show();
        return super.onKeyUp(keyCode,event);
    }


    @SuppressLint("WrongConstant")
    public boolean onTouchEvent(MotionEvent event)
    {
        Toast.makeText(this,"签到成功！",0).show();
        return super.onTouchEvent(event);
    }

}

