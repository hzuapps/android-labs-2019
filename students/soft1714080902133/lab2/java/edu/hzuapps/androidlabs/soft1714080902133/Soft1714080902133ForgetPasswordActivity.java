package edu.hzuapps.androidlabs.soft1714080902133;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

//惠大宿舍缴电费app 没想到实验2只需建立activity就好，讲课前我就打了好多
//登录页面，点击忘记密码，跳转过来，实现修改密码，后期加更多
public class Soft1714080902133ForgetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902133_forgetpasswordactivity);

        Toolbar toolbar = (Toolbar)findViewById(R.id.forget_password_toolbar);
        setSupportActionBar(toolbar);//获得ToolBar实例
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);//菜单，默认图片返回图片
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home://点击了返回，结束该活动，返回上一个活动
                Intent intent = new Intent(Soft1714080902133ForgetPasswordActivity.this, Soft1714080902133LoginActivity.class);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
