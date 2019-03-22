package com.example.tuku;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //view层的控件和业务层的控件，靠id关联和映射  给btn1赋值，即设置布局文件中的Button按钮id进行关联
        Button btn1=(Button)findViewById(R.id.btn1);
        //给btn1绑定监听事件
        btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 给bnt1添加点击响应事件
                Intent intent =new Intent(MainActivity.this,BankActivity.class);
                //启动
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /**
         * 在onCreateOptionsMenu执行后，菜单被显示前调用；如果菜单已经被创建，则在菜单显示前被调用。 同样的，
         * 返回true则显示该menu,false 则不显示; （可以通过此方法动态的改变菜单的状态，比如加载不同的菜单等） TODO
         */
        getMenuInflater().inflate(R.menu.activity_main, menu); //这个函数使不使用好像没有影响
        /*
        在Activity类中有一个getMenuInflater()的函数用来返回这个Activity的MenuInflater，
        通过MenuInflater对象来设置menu XML里的menu作为该Activity的菜单。
        */
        return true;
    }
}