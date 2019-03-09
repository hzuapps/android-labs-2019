package edu.hzuapps.androidlabs.Soft1714080902133;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//惠大宿舍缴电费app
/**
 *手机号/学号为账号，登陆界面，还有登陆后的界面，登陆前界面
 * 大体改了我以前做过的
 * 后端服务器数据库后期加进去，暂时实现点击跳转到主页面,不判断用户是否在数据库
 */
public class Soft1714080902133LoginActivity extends AppCompatActivity{

    // 登陆按钮
    Button loginbutton;
    // 显示账号和密码
    EditText number,password;

    // 创建等待框
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902133_loginactivity);

        //界面标题bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.login_toolbar);
        setSupportActionBar(toolbar);//获得ToolBar实例
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);//菜单，默认图片返回图片
        }

        // 获取控件
        number = findViewById(R.id.number);
        password = findViewById(R.id.password);
        loginbutton = findViewById(R.id.login_button);

        // 设置按钮监听器,实现点击
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isUserNameAndPwdValid()&&number.getText().toString().equals("1714080902133")
                        &&password.getText().toString().equals("123456")){
                    Intent intent = new Intent(Soft1714080902133LoginActivity.this,Soft1714080902133HomeActivity.class);
                    startActivity(intent);//登录成功
                }
            }
        });
    }


    //跳转注册页面
    public void user_register(View view) {
        Intent intent = new Intent(Soft1714080902133LoginActivity.this, Soft1714080902133RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    //跳转忘记密码页面
    public void forget_password(View view) {
        Intent intent = new Intent(Soft1714080902133LoginActivity.this, Soft1714080902133ForgetPasswordActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home://点击了返回，结束该活动，返回上一个活动
                Intent intent = new Intent(Soft1714080902133LoginActivity.this, Soft1714080902133HomeActivity.class);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //判断用户名和密码是否有效
    public boolean isUserNameAndPwdValid() {// 用户名和密码不得为空
        if (number.getText().toString().equals("")||!(number.getText().toString().length()==11
                ||number.getText().toString().length()==13)){
            Toast.makeText(this,"手机号/学号格式错误！" ,
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.getText().toString().equals("")) {
            Toast.makeText(this, "密码不能为空！",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}

