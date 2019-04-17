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

public class Soft1714080902216UserActivity extends AppCompatActivity {
    private String userName,psw,spPsw;
    private EditText et_user_name,et_psw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902216_user);
        Button denglu=(Button) findViewById(R.id.denglu);
        Button zhuce= (Button) findViewById(R.id.zhuce);
        Button back=(Button) findViewById(R.id.back);
        et_user_name=findViewById(R.id.Inputname_name);
        et_psw=findViewById(R.id.inputdata);
        denglu.setOnClickListener(new Soft1714080902216UserActivity.myButton());
        zhuce.setOnClickListener(new Soft1714080902216UserActivity.myButton());
        back.setOnClickListener(new Soft1714080902216UserActivity.myButton());
    }

    private String readPsw(String userName){
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        return sp.getString(userName , "");
    }

    private void saveLoginStatus(boolean status,String userName){
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean("isLogin", status);
        editor.putString("loginUserName", userName);
        editor.commit();
    }

    private class myButton implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            Intent intent;
            switch(v.getId())
            {
                case R.id.denglu:
                    userName=et_user_name.getText().toString().trim();
                    psw=et_psw.getText().toString().trim();
                    spPsw=readPsw(userName);
                    if(TextUtils.isEmpty(userName)){
                        Toast.makeText(Soft1714080902216UserActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                        return;
                    }else if(TextUtils.isEmpty(psw)){
                        Toast.makeText(Soft1714080902216UserActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                        return;
                    }else if(psw.equals(spPsw)){
                        Toast.makeText(Soft1714080902216UserActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                        saveLoginStatus(true, userName);
                        Intent data=new Intent();
                        data.putExtra("isLogin",true);
                        setResult(RESULT_OK,data);
                        Soft1714080902216UserActivity.this.finish();
                        startActivity(new Intent(Soft1714080902216UserActivity.this, Soft1714080902216MainActivity.class));
                        return;
                    }else if((spPsw!=null&&!TextUtils.isEmpty(spPsw)&&!psw.equals(spPsw))){
                        Toast.makeText(Soft1714080902216UserActivity.this, "输入的用户名和密码不一致", Toast.LENGTH_SHORT).show();
                        return;
                    }else{
                        Toast.makeText(Soft1714080902216UserActivity.this, "此用户名不存在", Toast.LENGTH_SHORT).show();
                    }
                case R.id.zhuce:
                    intent=new Intent(Soft1714080902216UserActivity.this,Soft1714080902216RegisterActivity.class);
                    startActivity(intent);
                    break;
                case R.id.back:
                    intent=new Intent(Soft1714080902216UserActivity.this,Soft1714080902216MainActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}
