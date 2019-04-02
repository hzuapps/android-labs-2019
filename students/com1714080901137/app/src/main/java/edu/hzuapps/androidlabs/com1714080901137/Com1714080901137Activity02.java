package edu.hzuapps.androidlabs.com1714080901137;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Com1714080901137Activity02 extends AppCompatActivity {
    private TextView tv_name,tv_password,tv_sex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com171408090113702);
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        String password=intent.getStringExtra("password");
        String sex=intent.getStringExtra("sex");
        tv_name=(TextView)findViewById(R.id.tv_name);
        tv_password=(TextView)findViewById(R.id.tv_password);
        tv_sex=(TextView)findViewById(R.id.tv_sex);
        tv_name.setText("用户名："+name);
        tv_password.setText("密  码："+password);
        tv_sex.setText("性  别："+sex);
    }
}
