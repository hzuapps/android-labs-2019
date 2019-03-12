package edu.hzuapps.androidlabs.Soft1714080902133;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

//惠大宿舍缴电费app
//个人中心，用户信息,先实现点击事件，后期把首页，个人中心，用tab连接起来，实现滑动
public class Soft1714080902133UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902133_useractivity);
    }

    //跳转登录界面
    public void go_login(View view) {
        Intent intent = new Intent(Soft1714080902133UserActivity.this, Soft1714080902133LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home://点击了返回，结束该活动，返回上一个活动
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
