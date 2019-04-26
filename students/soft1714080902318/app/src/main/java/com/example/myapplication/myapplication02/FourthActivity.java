package com.example.myapplication.myapplication02;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FourthActivity extends AppCompatActivity {
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private Button button_create;
    private Button button_load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        // 获取布局文件中的控件
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        button_create = (Button) findViewById(R.id.button_create);
        button_load = (Button) findViewById(R.id.button_load);
        button_create.setOnClickListener(new ButtonListener());
        button_load.setOnClickListener(new ButtonListener());
    }
    private class ButtonListener implements View.OnClickListener {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.button_create:
                        String saveText1=editText1.getText().toString().trim();
                        String saveText2=editText2.getText().toString().trim();
                        String saveText3=editText3.getText().toString().trim();
                        String lineChange="\n";
                        FileOutputStream fileOutputStream;
                        try{
                            fileOutputStream=openFileOutput("data.txt", Context.MODE_APPEND);
                            fileOutputStream.write("姓名：     ".getBytes());
                            fileOutputStream.write(saveText1.getBytes());
                            fileOutputStream.write(lineChange.getBytes());
                            fileOutputStream.write("种族：     ".getBytes());
                            fileOutputStream.write(saveText2.getBytes());
                            fileOutputStream.write(lineChange.getBytes());
                            fileOutputStream.write("能力：     ".getBytes());
                            fileOutputStream.write(saveText3.getBytes());
                            fileOutputStream.write(lineChange.getBytes());
                            fileOutputStream.close();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        Toast.makeText(FourthActivity.this,"人物新建成功!",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.button_load:
                        String content="";
                        try{
                            FileInputStream fileInputStream=openFileInput("data.txt");
                            byte[] buffer=new byte[fileInputStream.available()];
                            fileInputStream.read(buffer);
                            content=new String(buffer);
                            fileInputStream.close();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    Toast.makeText(FourthActivity.this,"保存的人物是:"+content,Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
            }
        }
    }
}
