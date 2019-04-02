package edu.hzuapps.androidlabs.soft1714080902216;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Soft1714080902216DataActivity extends AppCompatActivity {
    private EditText username,birth;
    private String user,day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902216_data);
        Button inputLogin=(Button) findViewById(R.id.inputLogin);
        Button Comeback=(Button) findViewById(R.id.Comeback);
        username=findViewById(R.id.Inputname_name);
        birth=findViewById(R.id.inputdata);
        inputLogin.setOnClickListener(new Soft1714080902216DataActivity.MyButton());
        Comeback.setOnClickListener(new Soft1714080902216DataActivity.MyButton());
    }

    private String readday(String user){
        SharedPreferences sp=getSharedPreferences("BirthInfo", MODE_PRIVATE);
        return sp.getString(user , "");
    }

    private class MyButton implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            Intent intent;
            switch(v.getId())
            {
                case R.id.inputLogin:
                    user=username.getText().toString().trim();
                    day=readday(user);
                    if(TextUtils.isEmpty(day)){
                        Toast.makeText(Soft1714080902216DataActivity.this, "该人物生日没有添加", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        Toast.makeText(Soft1714080902216DataActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
                        birth.setText(day);
                        break;
                    }
                case R.id.Comeback:
                    intent=new Intent(Soft1714080902216DataActivity.this,Soft1714080902216MainActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}
