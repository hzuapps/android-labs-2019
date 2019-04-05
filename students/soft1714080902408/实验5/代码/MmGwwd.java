package com.hzuandroid.soft1714080902408;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hzuandroid.soft1714080902408.utils.Utils;

import java.util.Map;

import static com.hzuandroid.soft1714080902408.R.id.btn_login;

public class MmGwwd extends Mm {

    private Button test_btn=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._03);
        test_btn=(Button) findViewById(R.id.Button03);
        test_btn.setOnClickListener(new Mm.MyButtonListener());
    }

    private class MyButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View view){
            Intent intent=new Intent(MmGwwd.this,Gwwd.class);
            startActivity(intent);
        }
    }
}