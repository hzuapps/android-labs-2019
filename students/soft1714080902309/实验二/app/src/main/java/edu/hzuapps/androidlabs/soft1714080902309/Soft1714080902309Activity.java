package edu.hzuapps.androidlabs.soft1714080902309;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Soft1714080902309Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902309_activity);

        //通过id找到关联布局中的view控件
        Button btn = (Button) findViewById(R.id.button1);
        //给Button按钮添加点击的监听
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            //只要当前的设置的监听器被触发，这个方法就会被执行
            public void onClick(View v) {
                startActivity(new Intent(Soft1714080902309Activity.this, Soft1714080902309Activity2.class));
            }
        });
    }

}
