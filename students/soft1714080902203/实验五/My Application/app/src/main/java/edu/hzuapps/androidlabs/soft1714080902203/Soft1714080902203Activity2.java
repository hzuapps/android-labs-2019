package edu.hzuapps.androidlabs.soft1714080902203;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Soft1714080902203Activity2 extends Activity {
    private EditText et_info;
    private Button btn_save;
    private Button btn_read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902203_activity2);
        //获取布局文件中的控件
        et_info = (EditText) findViewById(R.id.et_info);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_read = (Button) findViewById(R.id.btn_read);
        //设置按钮点击函数
        btn_save.setOnClickListener(new ButtonListener());
        btn_read.setOnClickListener(new ButtonListener());
    }
    //定义Button按钮点击事件
    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_save:
                    String saveinfo = et_info.getText().toString().trim();
                    FileOutputStream fileOutputStream;
                    try {
                        fileOutputStream=openFileOutput("data.txt", Context.MODE_APPEND);
                        fileOutputStream.write(saveinfo.getBytes());
                        fileOutputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft1714080902203Activity2.this,"以保存！",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.btn_read:
                    String content="";
                    try{
                        FileInputStream fileInputStream=openFileInput("data.txt");
                        byte[] buffer=new byte[fileInputStream.available()];
                        fileInputStream.read(buffer);
                        content=new String(buffer);
                        fileInputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }Toast.makeText(Soft1714080902203Activity2.this,"您的爱豆简介已保存："+content,Toast.LENGTH_SHORT).show();
                    break;


                default: break;
            }
        }
    }
}