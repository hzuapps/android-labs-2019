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

import static edu.hzuapps.androidlabs.soft1714080902216.R.*;

public class Soft1714080902216RecordActivity extends AppCompatActivity {
    private EditText username,birth;
    private String user,day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902216_record);
        Button inputLogin = (Button) findViewById(R.id.inputLogin);
        Button Comeback = (Button) findViewById(R.id.Comeback);
        username=findViewById(R.id.Inputname_name);
        birth=findViewById(R.id.inputdata);
        inputLogin.setOnClickListener(new Soft1714080902216RecordActivity.MyButton());
        Comeback.setOnClickListener(new Soft1714080902216RecordActivity.MyButton());
    }

    private void saveBirthInfo(String user,String day)
    {
        SharedPreferences sp=getSharedPreferences("BirthInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString(user, day);
        editor.commit();
    }

    private class MyButton implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.inputLogin:
                    user=username.getText().toString().trim();
                    day=birth.getText().toString().trim();
                    if (TextUtils.isEmpty(user)) {
                        Toast.makeText(Soft1714080902216RecordActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (TextUtils.isEmpty(day)) {
                        Toast.makeText(Soft1714080902216RecordActivity.this, "请输入生日", Toast.LENGTH_SHORT).show();
                        return;
                    }  else {
                        Toast.makeText(Soft1714080902216RecordActivity.this, "输入成功", Toast.LENGTH_SHORT).show();
                        saveBirthInfo(user, day);
                        Intent data = new Intent();
                        data.putExtra("user", user);
                        setResult(RESULT_OK, data);
                        Soft1714080902216RecordActivity.this.finish();
                        intent=new Intent(Soft1714080902216RecordActivity.this,Soft1714080902216MainActivity.class);
                        startActivity(intent);
                        break;
                    }
                case R.id.Comeback:
                    intent = new Intent(Soft1714080902216RecordActivity.this, Soft1714080902216MainActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}


