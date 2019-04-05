package edu.hzuapps.androidlabs.soft1714080902411;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Main3Activity extends AppCompatActivity {
    private EditText editText11;
    private Button button4;
    private Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        //变量初始化
        editText11=(EditText)findViewById(R.id.editText11);
        button5=(Button)findViewById(R.id.button5);
        button4=(Button)findViewById(R.id.button4);
        button4.setOnClickListener(new ButtonListener());
        button5.setOnClickListener(new ButtonListener());
    }
    private class ButtonListener implements View.OnClickListener{


        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button5:
                    String saveInfo=editText11.getText().toString().trim();
                    FileOutputStream fos;
                    try {
                        fos=openFileOutput("data.txt",MODE_APPEND);//把文件输出到data中
                        fos.write(saveInfo.getBytes());//将我们写入的字符串变成字符数组）
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Main3Activity.this,"查询信息已确定",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button4:
                    String content="";
                    try {
                        FileInputStream fis=openFileInput("data.txt");
                        byte [] buffer=new  byte[fis.available()];
                        fis.read(buffer);
                        content=new String(buffer);
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Main3Activity.this,"信息为："+content,Toast.LENGTH_SHORT).show();
                    break;
                default:
            }
        }
    }
}
