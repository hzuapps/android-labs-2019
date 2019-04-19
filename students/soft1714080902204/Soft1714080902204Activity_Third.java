package edu.hzuapps.androidlabs.soft1714080902204;

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
import java.io.IOException;

public class Soft1714080902204Activity_Third extends AppCompatActivity {
    byte[] buffer=null;//定义保存数据的数组

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902204_third);
        final EditText etext7=(EditText) findViewById(R.id.editText7);
        final EditText etext8=(EditText) findViewById(R.id.editText8);
        final EditText etext9=(EditText) findViewById(R.id.editText9);




        Button btnOpen = findViewById(R.id.button7);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Soft1714080902204Activity_Third.this,Soft1714080902204Activity_Second.class));

                /***********************************************保存信息***********************/
                FileOutputStream fos=null;
                String text1=etext7.getText().toString();
                String text2=etext8.getText().toString();
                String text3=etext9.getText().toString();

                    try {
                        fos=openFileOutput("memo",MODE_PRIVATE);//获取输入流对象
                        fos.write(text1.getBytes());//保存信息
                        fos.flush();//清除缓存
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        if(fos!=null){

                        try {
                            fos.close();//关闭输出流
                            Toast.makeText(Soft1714080902204Activity_Third.this,"保存成功",Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }}

            }
        });
        /****************读取保存的信息*******************************/
        FileInputStream fis=null;
        try {
            fis=openFileInput("memo");
            buffer=new byte[fis.available()];//实例化字节数组
            fis.read(buffer);//从输入流中读取数据

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fis!=null){
            try {
                fis.close();//关闭输入流对象
                String data=new String(buffer);//把字节数组中的数据转化为字符串
                etext7.setText(data);//显示读取内容
                etext8.setText(data);
                etext9.setText(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }}


    }
}
