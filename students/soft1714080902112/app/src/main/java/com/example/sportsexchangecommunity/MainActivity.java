package com.example.sportsexchangecommunity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Toast.makeText(this, "按键按下！", 0).show();
        return super.onKeyDown(keyCode, event);
    }
    public boolean onKeyIp(int keyCode,KeyEvent event) {
        Toast.makeText(this, "按键弹起！", 0).show();
        return super.onKeyUp(keyCode, event);
    }

    public boolean onTouchEvent(MotionEvent event) {
        float x=event.getX();
        float y=event.getY();
        Toast.makeText(this,"点击的坐标为（"+x+":"+y+"）",0).show();
        return super.onTouchEvent(event);
    }
}
