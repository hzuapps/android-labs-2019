package edu.hzuapps.androidlabs.soft1714080902120;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Soft1714080902120InitialPageActivity extends AppCompatActivity {
    private MyCountDownTimer mc;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        tv = (TextView) findViewById(R.id.InitialPage_View);
        mc = new MyCountDownTimer(5000, 1000);
        mc.start();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //这里实现初始页面跳转到主页面

                Intent intent=new Intent(Soft1714080902120InitialPageActivity.this,Soft1714080902120NameListActivity.class);
                startActivity(intent);
            }
        }, 5000);
    }
    private Handler handler=new Handler();

     // 继承 CountDownTimer 防范重写; 父类的方法 onTick() 、 onFinish()

    class MyCountDownTimer extends CountDownTimer {

         // @param millisInFuture表示以毫秒为单位 倒计时的总数
        // @param countDownInterval表示 间隔 多少微秒 调用一次 onTick 方法

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        public void onFinish() {
            tv.setText(getString(R.string.tz));
        }
        public void onTick(long millisUntilFinished) {
            tv.setText(getString(R.string.LostingTime) + millisUntilFinished / 1000 + ")");
        }
    }


    }

