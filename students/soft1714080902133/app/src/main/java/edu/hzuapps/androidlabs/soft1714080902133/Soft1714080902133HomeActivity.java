package edu.hzuapps.androidlabs.soft1714080902133;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


//惠大宿舍缴电费app
//主页，查询，缴费，充值
//只实现了一个点击事件，以后实验补充
public class Soft1714080902133HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902133_homeactivity);
        Button b4 = findViewById(R.id.home_button4);//个人中心
        //点击事件
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //意图实现跳转Activity
                Intent intent = new Intent(Soft1714080902133HomeActivity.this, Soft1714080902133UserActivity.class);
                startActivity(intent);
            }
        });

        Button b1 = findViewById(R.id.home_button1);//充值
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Soft1714080902133HomeActivity.this, Soft1714080902133RechargeActivity.class);
                startActivity(intent);
            }
        });

        Button b2 = findViewById(R.id.home_button2);//查询
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Soft1714080902133HomeActivity.this, Soft1714080902133QueryActivity.class);
                startActivity(intent);
            }
        });

        Button b3 = findViewById(R.id.home_button3);//缴费
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Soft1714080902133HomeActivity.this,Soft1714080902133PaymentActivity.class);
                startActivity(intent);
            }
        });
    }
}
