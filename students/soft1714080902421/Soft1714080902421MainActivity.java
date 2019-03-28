package edu.hzuapps.androidlabs.soft1714080902421;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


@SuppressLint("Registered")
public class Soft1714080902421MainActivity extends AppCompatActivity {
    private ImageView imageView = null;
    private Button mBtnImageView=null;
    private Button mBtnmytree=null;
    private Button mBtntreeplant=null;
    private Button mBtntotaltime=null;
    private Button mBtnsetting=null;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnImageView = findViewById(R.id.btn_imageview);
        mBtnmytree = findViewById(R.id.btn_mytree);
        mBtntreeplant = findViewById(R.id.btn_treeplant);
        mBtntotaltime = findViewById(R.id.btn_totaltime);
        mBtnsetting = findViewById(R.id.btn_setting);
        setOnClickListener();
        //根据id找到按钮
    }

    private void setOnClickListener(){
        OnClick onClick = new OnClick();
        mBtnImageView.setOnClickListener(onClick);
        mBtnmytree.setOnClickListener(onClick);
        mBtntreeplant.setOnClickListener(onClick);
        mBtntotaltime.setOnClickListener(onClick);
        mBtnsetting.setOnClickListener(onClick);
        //设置点击事件
    }

    private class  OnClick  implements View.OnClickListener{
        @Override
        public void onClick(View v){
            Intent intent = null;
            switch (v.getId()){
            case R.id.btn_imageview:
                intent = new Intent(Soft1714080902421MainActivity.this, ImageViewActivity.class);
            break;
            case R.id.btn_mytree:
                intent = new Intent(Soft1714080902421MainActivity.this, MyTreeActivity.class);
            break;
            case R.id.btn_treeplant:
                intent = new Intent(Soft1714080902421MainActivity.this, TreePlantActivity.class);
            break;
            case R.id.btn_totaltime:
                 intent = new Intent(Soft1714080902421MainActivity.this, TotalTimeActivity.class);
            break;
            case R.id.btn_setting:
                  intent = new Intent(Soft1714080902421MainActivity.this, SettingActivity.class);
            break;
            }
            startActivity(intent);
             }
    }

    public boolean onTouchEvent(MotionEvent event){
        float x=event.getX();
        float y=event.getY();
        Toast.makeText(this, "Good！",0).show();
        return super.onTouchEvent(event);
    }
}
