package edu.hzuapps.androidlabs.soft1714080902424;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class NameActivity extends AppCompatActivity {

    private EditText et_info;
    private Button button_save,button_read,button_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        //获取布局文件中的控件
        et_info=(EditText) findViewById(R.id.et_info);
        button_save=(Button) findViewById(R.id.button_save);
        button_read=(Button) findViewById(R.id.button_read);
        button_next=(Button) findViewById(R.id.button_next);
        button_save.setOnClickListener(new ButtonListener());
        button_read.setOnClickListener(new ButtonListener());

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NameActivity.this, HeadPortraitActivity.class);
                startActivity(intent);
            }
        });
    }
    private class ButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.button_save:
                    String saveinfo=et_info.getText().toString().trim();
                    FileOutputStream fos;
                    try {
                        //保存数据
                        fos=openFileOutput("data.txt", Context.MODE_APPEND);
                        fos.write(saveinfo.getBytes());
                        fos.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(NameActivity.this,"数据保存成功", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button_read:
                    String content="";
                    try {
                        //获得保存的数据
                        FileInputStream fis=openFileInput("data.txt");
                        byte[] buffer=new byte[fis.available()];
                        fis.read(buffer);
                        content=new String(buffer);
                        fis.close();
                    }catch(Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(NameActivity.this,"保存的数据是:"+content,Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }
}
