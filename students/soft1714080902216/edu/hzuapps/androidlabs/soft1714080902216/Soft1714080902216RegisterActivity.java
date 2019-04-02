package edu.hzuapps.androidlabs.soft1714080902216;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Soft1714080902216RegisterActivity extends AppCompatActivity {
    private EditText et_user_name,et_psw,et_psw_again;
    private String userName,psw,pswAgain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902216_register);
        Button zhuce=(Button) findViewById(R.id.zhuce);
        Button back=(Button) findViewById(R.id.back);
        et_user_name=findViewById(R.id.Inputname_name);
        et_psw=findViewById(R.id.inputdata);
        et_psw_again=findViewById(R.id.inputdata1);
        zhuce.setOnClickListener(new Soft1714080902216RegisterActivity.myButton());
        back.setOnClickListener(new Soft1714080902216RegisterActivity.myButton());
    }

    private void getEditString()
    {
        userName=et_user_name.getText().toString().trim();
        psw=et_psw.getText().toString().trim();
        pswAgain=et_psw_again.getText().toString().trim();
    }

    private boolean isExistUserName(String userName)
    {
        boolean has_userName=false;
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        String spPsw=sp.getString(userName, "");
        if(!TextUtils.isEmpty(spPsw))
        {
            has_userName=true;
        }
        return has_userName;
    }

    private void saveRegisterInfo(String userName,String psw)
    {
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString(userName, psw);
        editor.commit();
    }

    private class myButton implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            Intent intent;
            switch(v.getId()) {
                case R.id.zhuce:
                    getEditString();
                    if (TextUtils.isEmpty(userName)) {
                        Toast.makeText(Soft1714080902216RegisterActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (TextUtils.isEmpty(psw)) {
                        Toast.makeText(Soft1714080902216RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (TextUtils.isEmpty(pswAgain)) {
                        Toast.makeText(Soft1714080902216RegisterActivity.this, "请再次输入密码", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (!psw.equals(pswAgain)) {
                        Toast.makeText(Soft1714080902216RegisterActivity.this, "密码不一致", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (isExistUserName(userName)) {
                        Toast.makeText(Soft1714080902216RegisterActivity.this, "此账户名已经存在", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        Toast.makeText(Soft1714080902216RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        saveRegisterInfo(userName, psw);
                        Intent data = new Intent();
                        data.putExtra("userName", userName);
                        setResult(RESULT_OK, data);
                        Soft1714080902216RegisterActivity.this.finish();
                        intent=new Intent(Soft1714080902216RegisterActivity.this,Soft1714080902216UserActivity.class);
                        startActivity(intent);
                        break;
                    }
                case R.id.back:
                    intent=new Intent(Soft1714080902216RegisterActivity.this,Soft1714080902216UserActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}