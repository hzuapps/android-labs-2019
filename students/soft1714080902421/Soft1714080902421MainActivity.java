package com.example.Soft1714080902421;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Toast;

@SuppressLint("Registered")
public class Soft1714080902421MainActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902421_activity);
    }
    //
    public boolean onKeyDown(int keyCode, KeyEvent event){
        Toast.makeText(this,"按时完成学习任务即可种树！",0).show();
        return super.onKeyDown(keyCode,event);
    }

    public boolean onKeyUp(int keyCode,KeyEvent event){
        Toast.makeText(this,"种树成功！",2).show();
        return super.onKeyUp(keyCode,event);
    }

    public boolean onTouchEvent(MotionEvent event){
        float x=event.getX();
        float y=event.getY();
        Toast.makeText(this, "Good！",2).show();
        return super.onTouchEvent(event);
    }
}
