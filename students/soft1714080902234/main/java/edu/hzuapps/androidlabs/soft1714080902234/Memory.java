package edu.hzuapps.androidlabs.soft1714080902234;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Memory extends AppCompatActivity {


    private EditText et_info;
    private Button save;
    private Button read;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        et_info= findViewById(R.id.et_info);
        save= findViewById(R.id.save);
        read= findViewById(R.id.read);
        save.setOnClickListener(new ButtonListener());
        read.setOnClickListener(new ButtonListener());
    }
    private class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.save:
                    String saveinfo=et_info.getText().toString();
                    FileOutputStream fos;
                    BufferedWriter writer;
                    try{
                        fos=openFileOutput("save", Context.MODE_APPEND);
                        writer = new BufferedWriter(new OutputStreamWriter(fos));
                        writer.write(saveinfo);
                        writer.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Memory.this,"保存成功",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.read:
                    String line="";
                    BufferedReader reader;
                    StringBuffer content = new StringBuffer();
                    try{
                        FileInputStream fis=openFileInput("save");
                        reader = new BufferedReader(new InputStreamReader(fis));
                        while ((line = reader.readLine()) != null) {
                            content.append(line);
                        }
                        reader.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(Memory.this, "笔记内容：" + content.toString(), Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }
}