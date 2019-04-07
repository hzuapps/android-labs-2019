package com.example.administrator.basketball;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class StorageActivity extends ActionBarActivity {
    private EditText et_info1;
    private EditText et_info2;
    private EditText et_info3;
    private Button btn_save;
    private Button btn_read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        //获取布局文件中的控件
        et_info1=(EditText) findViewById(R.id.et_info1);
        et_info2=(EditText) findViewById(R.id.et_info2);
        et_info3=(EditText) findViewById(R.id.et_info3);
        btn_save=(Button) findViewById(R.id.btn_save);
        btn_read=(Button) findViewById(R.id.btn_read);
        btn_save.setOnClickListener(new ButtonListener());
        btn_read.setOnClickListener(new ButtonListener());
    }
    private class ButtonListener implements View.OnClickListener {
        public void onClick(View v){
            switch(v.getId()){
                case R.id.btn_save:
                    String saveinfo1=et_info1.getText().toString().trim();
                    String saveinfo2=et_info2.getText().toString().trim();
                    String saveinfo3=et_info3.getText().toString().trim();
                    String turn="\n";
                    FileOutputStream fos;
                    try{
                        fos=openFileOutput("data.txt", Context.MODE_APPEND);
                        fos.write("年龄（岁）:    ".getBytes());
                        fos.write(saveinfo1.getBytes());
                        fos.write(turn.getBytes());
                        fos.write("身高（cm）:    ".getBytes());
                        fos.write(saveinfo2.getBytes());
                        fos.write(turn.getBytes());
                        fos.write("体重（kg）:    ".getBytes());
                        fos.write(saveinfo3.getBytes());
                        fos.write(turn.getBytes());
                        fos.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(StorageActivity.this, "数据保存成功",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_read:
                    String content="";
                    try{
                        FileInputStream fis=openFileInput("data.txt");
                        byte[] buffer=new byte[fis.available()];
                        fis.read(buffer);
                        content=new String(buffer);
                        fis.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(StorageActivity.this,"保存的数据是："+content,Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }

    }
}
