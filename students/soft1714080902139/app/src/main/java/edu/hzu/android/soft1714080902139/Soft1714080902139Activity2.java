package edu.hzu.android.soft1714080902139;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Soft1714080902139Activity2 extends AppCompatActivity {

    private Button btn01;
    private TextView tv_name,tv_password,tv_sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft17140809021392);

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String password = intent.getStringExtra("password");
        String sex = intent.getStringExtra("sex");
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_password = (TextView) findViewById(R.id.tv_password);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        tv_name.setText("用户名："+name);
        tv_password.setText("密  码："+password);
        tv_sex.setText("性  别："+sex);

        btn01 = (Button) findViewById(R.id.btn01);
        btn01.getBackground().setAlpha(0);
    }



    public void ski01(View view){
        Intent i = new Intent();
        i.setClass(Soft1714080902139Activity2.this,Soft1714080902139Activity.class);
        startActivity(i);
    }

    public void ski02(View view){
        Intent i = new Intent();
        i.setClass(Soft1714080902139Activity2.this,Activity03.class);
        startActivity(i);
    }

}
