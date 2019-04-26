package edu.hzuapps.androidlabs.soft1709081602513;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    private Button  mBtnSetting;
    private ImageButton mBtnStart;
    private TextView xiaView;
    //圆图片
    private TextView circleimageview;
    //路径
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化数据


        mBtnStart = findViewById(R.id.btn_start);
        mBtnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //跳转到开始界面StartActivity
                Intent intent = new Intent(MainActivity.this, StartActivity.class);
                startActivity(intent);

            }
        });

        mBtnSetting = findViewById(R.id.btn_setting);
        //设置触摸监听器
        mBtnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到设置界面SettingsActivity
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        xiaView = findViewById(R.id.textview_01);
        //设置触摸监听器
        xiaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到Sof1709081602513Activity界面
                Intent intent = new Intent(MainActivity.this, Soft1709081602513Activity.class);
                startActivity(intent);
            }
        });
        //调用初始化用户名
        Initname();


    }
    //初始化用户名
    public void Initname(){
        SharedPreferences pref=getSharedPreferences("config",MODE_PRIVATE);
        String name=pref.getString("username","");
        if(name!=null){
            xiaView.setText(name);
        }
    }

}
