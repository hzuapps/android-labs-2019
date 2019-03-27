package edu.hzuapps.androidlabs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Soft1714080902437Activity extends AppCompatActivity implements View.OnClickListener{


    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902437);
        button = findViewById(R.id.button1);
        button.setOnClickListener(this);
    }
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(Soft1714080902437Activity.this,MainActivity.class);
            startActivity(intent);
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

