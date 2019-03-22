package edu.hzuapps.androidlabs.soft1714080902223;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Soft1714080902223Activity extends AppCompatActivity {

    private Button mBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 调用父亲保存的状态
        super.onCreate(savedInstanceState);
        // 布局方法
        setContentView(R.layout.soft_1714080902223_activity);

        mBtn = findViewById(R.id.btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到首页
                Intent intent = new Intent(Soft1714080902223Activity.this,
                        HomeActivity.class);
                startActivity(intent);
            }
        });


    }


}
