package com.end.activitytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        Button button1=(Button) findViewById(R.id.button1); //已经制定了，返回的是一个view
        button1.setOnClickListener(new View.OnClickListener(){      //调用setOnClickList（）方法注册一个监听器
            @Override
            public void onClick(View v){
                Toast.makeText(FirstActivity.this,"You clicked Button 1",       //有三个参数，第一个是context对象，第二个是toast要显示的文本内容，第三个是显示时长
                        Toast.LENGTH_SHORT).show();                                             //有两种时长：Toast.LENGTH_SHORT和Toast.LENGTH_LONG

                Intent intent=new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intent);

            }


        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);       //第一个参数用于指定我们通过哪一个资源文件来创建菜单，第二个参数用于指定我们的菜单项将添加到哪一个menu对象当中
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {       //我们定义菜单不是仅为了看，关键是要菜单真正用到才行，因此还要再定义菜单响应事件。
        switch(item.getItemId()) {                      //用来判断我们调用的是哪一个菜单项
            case R.id.add_item:
                Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();     //调用toast提醒
                break;
            case R.id.remove_item:
                Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show();
                break;
            default:
            }
            return true;
        }
}

