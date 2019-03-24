package edu.hzuapps.androidlabs.soft1714080902439;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.soft171408092439.R;


public class Soft1714080902439Activity extends AppCompatActivity {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902439_activity);
        button=(Button) findViewById(R.id.button);
        //按钮绑定接口
    }

    /*//添加生日
    @SuppressLint("WrongConstant")
    public boolean onKeyDown(int keyCode, KeyEvent event)   {
        Toast.makeText(this,"123" ,0).show();
        return super.onKeyDown(keyCode,event);
    }
    //相应屏幕触摸事件
    @SuppressLint("WrongConstant")
    public boolean onTouchEvent(MotionEvent event)  {
        float x=event.getX();
        float y=event.getY();
        Toast.makeText(this,"Service unavaliable!",0).show();
        return super.onTouchEvent(event);
    }
*/
}
