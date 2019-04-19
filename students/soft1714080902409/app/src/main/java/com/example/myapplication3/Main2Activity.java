package com.example.myapplication3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Main2Activity extends Activity {
    public void openg(View v) {
        Intent intent2 = new Intent(Main2Activity.this, Main3Activity.class);
        startActivity(intent2); }
    private EditText et_info;
    private Button button;
    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        et_info=(EditText) findViewById(R.id.et_info);
        button=(Button) findViewById(R.id.button);
        button2=(Button) findViewById(R.id.button2);
        button.setOnClickListener(new ButtonListener());
        button2.setOnClickListener(new ButtonListener());
    }

    private class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button2:
                    String buttoninfo = et_info.getText().toString().trim();
                    FileOutputStream fos;
                    try {
                        fos = openFileOutput("shu1.txt", Context.MODE_APPEND);
                        fos.write(buttoninfo.getBytes());
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Main2Activity.this,"数据保存成功",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button:
                    String content = "";
                    try {
                        FileInputStream fis = openFileInput("shu1.txt");
                        byte[] buffer = new byte[fis.available()];
                        fis.read(buffer);
                        content = new String(buffer);
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Main2Activity.this, "保存的数据是：" + content, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }
}
