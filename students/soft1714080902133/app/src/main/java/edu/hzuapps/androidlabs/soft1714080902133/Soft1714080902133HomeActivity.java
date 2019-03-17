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
        Button b1 = findViewById(R.id.home_button4);
        //点击事件
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //意图实现跳转Activity
                Intent intent = new Intent(Soft1714080902133HomeActivity.this, Soft1714080902133UserActivity.class);
                startActivity(intent);
            }
        });
    }
}
