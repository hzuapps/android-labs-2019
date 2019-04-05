package com.hzuandroid.soft1714080902408;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hzuandroid.soft1714080902408.utils.Utils;

import java.util.Map;


    public class Gwwd extends Mm implements View.OnClickListener {
        private EditText etNumber;
        private EditText etPassword;

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout._wd);
            initView();
            Map<String, String> userInfo = Utils.getUserInfo(this);
            if (userInfo != null) {
                etNumber.setText(userInfo.get("number"));
                etPassword.setText(userInfo.get("password"));
            }
        }

        private void initView() {
            etNumber = (EditText) findViewById(R.id.et_number);
            etPassword = (EditText) findViewById(R.id.et_password);
            findViewById(R.id.btn_login).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String number = etNumber.getText().toString().trim();
            String password = etPassword.getText().toString();
            if (TextUtils.isEmpty(number)) {
                Toast.makeText(this, "请输入账户号码", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "请输入账户密码", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            Log.i("dlcc", "记住密码:" + number + "," + password);
            boolean isSaveSuccess = Utils.saveUserInfo(this, number, password);
            if (isSaveSuccess) {
                Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
            }
        }
    }