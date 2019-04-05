package edu.hzuapps.androidlabs.soft1714080902431;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Main3Activity extends AppCompatActivity {
    private EditText et_info;
    private Button btn_save;
    private  Button btn_read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //获取布局文件中的控件
        et_info=(EditText) findViewById(R.id.et_info);
        btn_read=(Button) findViewById(R.id.btn_read);
        btn_save=(Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new ButtonListener());
        btn_read.setOnClickListener(new ButtonListener());
    }

    //定义Button按钮点击事件

    private class ButtonListener implements View.OnClickListener{
        @SuppressLint("WrongConstant")
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_save:
                    String saveinfo=et_info.getText().toString().trim();
                    FileOutputStream fos;
                    try{
                        fos=openFileOutput("data.txt", Context.MODE_APPEND);
                        fos.write(saveinfo.getBytes());
                        fos.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(Main3Activity.this,"数据保存成功",0).show();
                    break;

                case R.id.btn_read:
                    String content="";
                    try{
                        //获取保存数据
                        FileInputStream fis=openFileInput("data.txt");
                        byte[] buffer =new byte[fis.available()];
                        fis.read(buffer);
                        content=new String(buffer);
                        fis.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(Main3Activity.this,"保存的数据是："+content,0).show();
                    break;
                default:
                    break;
            }
        }
    }

}
