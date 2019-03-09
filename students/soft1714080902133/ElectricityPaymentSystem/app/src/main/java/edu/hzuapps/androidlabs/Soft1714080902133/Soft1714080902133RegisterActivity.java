package edu.hzuapps.androidlabs.Soft1714080902133;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//惠大宿舍缴电费app
//注册账号，后期实现后端功能，现在只实现前端的
public class Soft1714080902133RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902133_registeractivity);

        Toolbar toolbar = (Toolbar)findViewById(R.id.register_toolbar);
        setSupportActionBar(toolbar);//获得ToolBar实例
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);//菜单，默认图片返回图片
        }
    }

    //注册功能,跳转到完善信息页面
    public void register(View view) {
        EditText userTelephone = findViewById(R.id.register_telephone);
        EditText userPassword = findViewById(R.id.register_password);
        String telephone = userTelephone.getText().toString();
        String password = userPassword.getText().toString();
        if(telephone.length() !=11 || password.length() < 6) {
            Toast.makeText(Soft1714080902133RegisterActivity.this, "手机号或密码错误",Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(Soft1714080902133RegisterActivity.this,Soft1714080902133PerfectInformationActivity.class);
            intent.putExtra("telephone", telephone);
            intent.putExtra("password", password);
            startActivity(intent);
            finish();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home://点击了返回，结束该活动，返回上一个活动
                Intent intent = new Intent(Soft1714080902133RegisterActivity.this, Soft1714080902133LoginActivity.class);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void get_verification_code(View view) {
    }
}
