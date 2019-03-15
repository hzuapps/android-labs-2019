package edu.androidlabs.soft1714080902425;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Activity02 extends Activity {
    private TextView tv_name,tv_password,tv_sex;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity02);
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        String password=intent.getStringExtra("password");
        String sex=intent.getStringExtra("sex");
        tv_name=(TextView) findViewById(R.id.tv_username);
        tv_password=(TextView) findViewById(R.id.tv_password);
        tv_sex=(TextView) findViewById(R.id.textView2);
        tv_name.setText("用户名:"+name);
        tv_password.setText("密码:"+password);
        tv_sex.setText("性别:"+sex);
    }
}
