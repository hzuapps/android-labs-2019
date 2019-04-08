package edu.hzuapps.androidlabs.soft1709081602513;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity{
    private EditText mName;
    private Button mSave;
//    private String TAG="SettingsActivity";
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mName = findViewById(R.id.setname);
        mSave = findViewById(R.id.config_save);

        SharedPreferences pref=getSharedPreferences("config",MODE_PRIVATE);
        String name=pref.getString("username","");
        mName.setText(name);



        mSave.setOnClickListener(new Button.OnClickListener(){
        // 保存用户名
            @Override
        public void onClick(View v){
//            //实例化获取SharedPreferences对象,创建config.xml文件保存
//            SharedPreferences sharedPre=getApplicationContext().getSharedPreferences("config", Context.MODE_PRIVATE);
//            //获取Editor对象
//            SharedPreferences.Editor editor=sharedPre.edit();
//            //设置参数
//            editor.putString("username", mName.getText().toString().trim());
//            //提交
//            editor.commit();
                mName=findViewById(R.id.setname);
                String name=mName.getText().toString();
                SharedPreferences.Editor editor=getSharedPreferences("config",MODE_PRIVATE).edit();
                editor.putString("username",name);
                editor.commit();
                Toast.makeText(SettingsActivity.this,"提交成功",Toast.LENGTH_SHORT).show();

        }
    });
    }


}
