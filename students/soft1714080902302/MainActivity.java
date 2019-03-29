package com.example.soft1714080902302.Controller;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soft1714080902302.R;
import com.example.soft1714080902302.activity.Activity_food;
import com.example.soft1714080902302.activity.Activity_numerical_code;

public class MainActivity extends Activity implements View.OnClickListener {

    private TextView mTextMessage;
        //监听函数mOnNavigationItemSelectedListener
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };
    private TextView textView;
  //  private Button button;

   // private TextView textView;//定义图片变量
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //打开进入的第一个Activity

        Button button1 = (Button) findViewById(R.id.button_food);
        Button button2 = (Button) findViewById(R.id.button_numerical_code);
        Button button3 = (Button) findViewById(R.id.button_appliance);
        Button button4 = (Button) findViewById(R.id.button_car);
        Button button5 = (Button) findViewById(R.id.button_books);
        Button button6 = (Button) findViewById(R.id.button_clothes);
        Button button7 = (Button) findViewById(R.id.button_jewelry);
        Button button8 = (Button) findViewById(R.id.button_luggage);
        Button button9 = (Button) findViewById(R.id.button_travel);
        Button button10 = (Button) findViewById(R.id.button_pets);
        //按钮绑定接口
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button10.setOnClickListener(this);
        //在重载的方法中实现点击设置，用switch-case实现多按钮
    }
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.button_food:
                   Intent intent=new Intent(this,Activity_food.class);
                   startActivity(intent);
                   break;
                case R.id.button_numerical_code:
                    Intent intent2 = new Intent(this, Activity_numerical_code.class);
                    startActivity(intent2);
                    break;
//                case R.id.button_appliance:
//                    Intent intent=new Intent(this,Activity_appliance.class);
//                    startActivity(intent);
//                    break;
//                case R.id.button_food:
//                    Intent intent=new Intent(this,Activity_food.class);
//                    startActivity(intent);
//                    break;
//                case R.id.button_food:
//                    Intent intent=new Intent(this,Activity_food.class);
//                    startActivity(intent);
//                    break;
//                case R.id.button_food:
//                    Intent intent=new Intent(this,Activity_food.class);
//                    startActivity(intent);
//                    break;
//                case R.id.button_food:
//                    Intent intent=new Intent(this,Activity_food.class);
//                    startActivity(intent);
//                    break;
//                case R.id.button_food:
//                    Intent intent=new Intent(this,Activity_food.class);
//                    startActivity(intent);
//                    break;
//                case R.id.button_food:
//                    Intent intent=new Intent(this,Activity_food.class);
//                    startActivity(intent);
//                    break;
//                case R.id.button_food:
//                    Intent intent=new Intent(this,Activity_food.class);
//                    startActivity(intent);
//                    break;

            }
        }

    //button of Activity_food
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(MainActivity.this,
//                        Activity_food.class);
//                //this前面为当前activty名称，class前面为要跳转到得activity名称
//                startActivity(intent);
//            }
//        });
//        //button of Activity_numerical_code
//        button = (Button) findViewById(R.id.button_numerical_code);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(MainActivity.this,
//                        Activity_numerical_code.class);
//                startActivity(intent);
//            }
//        });


    }


