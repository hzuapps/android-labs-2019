package edu.hzuapps.androidlabs.soft1714080902229;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Main3Activity extends AppCompatActivity {

    private EditText et_info;
    private Button   btn_read;
    private  Button  btn_save;
    private Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        et_info=(EditText)findViewById(R.id.et_info);
        btn_read=(Button)findViewById(R.id.btn_read);
        btn_save=(Button)findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new ButtonListener());
        btn_read.setOnClickListener(new ButtonListener());
        button1=(Button) findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(edu.hzuapps.androidlabs.soft1714080902229.Main3Activity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
    }
    private class ButtonListener implements View.OnClickListener{


        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_save:
                    String saveInfo=et_info.getText().toString().trim();
                    FileOutputStream fos;
                    try {
                        fos=openFileOutput("data.txt",MODE_APPEND);
                        fos.write(saveInfo.getBytes());
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Main3Activity.this,"数据保存成功",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_read:
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
                    Toast.makeText(Main3Activity.this,"保存的数据是"+content,Toast.LENGTH_SHORT).show();
                    break;
                default:
            }
        }
    }
}
