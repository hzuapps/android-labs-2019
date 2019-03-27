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

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902421_activity);
        mBtnImageView = findViewById(R.id.btn_imageview);
        setOnClickListener();
    }

    private void setOnClickListener(){
        OnClick onClick = new OnClick();
        mBtnImageView.setOnClickListener(onClick);
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
