package com.hzuandroid.soft1714080902408;

import android.app.Activity;
import android.content.Intent;
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

public class Mm extends MainActivity {
    private Button test_btn=null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._03);
        test_btn = (Button) findViewById(R.id.Button01);
        test_btn.setOnClickListener(new Mm.MyButtonListener1());
        test_btn = (Button) findViewById(R.id.Button02);
        test_btn.setOnClickListener(new Mm.MyButtonListener2());
        test_btn = (Button) findViewById(R.id.Button03);
        test_btn.setOnClickListener(new Mm.MyButtonListener3());

    }
    private class MyButtonListener1 implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Mm.this, Gwsy.class);
            startActivity(intent);
        }
    }
    private class MyButtonListener2 implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Mm.this, Gwss.class);
            startActivity(intent);
        }
    }
    private class MyButtonListener3 implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Mm.this, Gwwd.class);
            startActivity(intent);
        }
    }
}
