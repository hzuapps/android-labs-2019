package com.example.shiyan4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.shiyan4.cn.itcast.saveqq.utils.Utils;

import java.util.Map;

public class Newmain4 extends Activity implements View.OnClickListener {
    private EditText etinfo;
    private EditText itinfo;
    private Button Btn_Do = null;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymain4);
        Btn_Do=(Button) findViewById(R.id.Btn_Do);
        Btn_Do.setOnClickListener(new Newmain4.D());
        initView();
        Map<String,String>userInfo= Utils.getUserInfo(this);
        if (userInfo!=null){
            etinfo.setText(userInfo.get("number"));
            itinfo.setText(userInfo.get("password"));
        }
    }
    private class D implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Newmain4.this, Newmain2.class);
            startActivity(intent);
        }
    }

    private void initView(){
        etinfo=(EditText) findViewById(R.id.et_info);
        itinfo=(EditText) findViewById(R.id.it_info);
        findViewById(R.id.Btn_Do).setOnClickListener(this);
    }
    public void onClick(View v){
        String number=etinfo.getText().toString().trim();
        String password=itinfo.getText().toString();
        if (TextUtils.isEmpty(number)){
            Toast.makeText(this,"请输入QQ帐号",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"请输入QQ密码",Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
        Log.i("Newmain4","保存帐号和密码:"+number+","+password);
        boolean isSaveSuccess=Utils.saveUserInfo(this,number,password);
        if (isSaveSuccess){
            Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
        }

        else {
            Toast.makeText(this,"保存失败",Toast.LENGTH_SHORT).show();
        }
        if (isSaveSuccess){

        }
    }


}
