package edu.hzuapps.androidlabs.soft1714080902439;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soft171408092439.R;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Soft1714080902439Activity extends AppCompatActivity {

    private ImageButton button;
    private EditText time;
    private TextView textView;
    //获取时间
    SimpleDateFormat formatter=new SimpleDateFormat("yyyy年MM月dd日");
    Date currentDate=new Date(System.currentTimeMillis());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902439_activity);
        button=(ImageButton) findViewById(R.id.add_button);
        time=(EditText) findViewById(R.id.time);
        textView=(TextView) findViewById(R.id.text);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast button=Toast.makeText(edu.hzuapps.androidlabs.soft1714080902439.Soft1714080902439Activity.this,"",Toast.LENGTH_SHORT);
                Intent intent=new Intent(edu.hzuapps.androidlabs.soft1714080902439.Soft1714080902439Activity.this,edu.hzuapps.androidlabs.soft1714080902439.Soft1714080902439AddBirthdayActivity.class);
                startActivity(intent);
                button.show();
            }
        });
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
