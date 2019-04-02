package edu.hzuapps.androidlabs.soft1714080902127;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Soft1714080902127MainActivity extends AppCompatActivity{

    private EditText edit;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902127_mainactivity);

        //根据Id获取EditText实例
        edit=(EditText) findViewById(R.id.input_message);

        String inputText=load();
        if(!TextUtils.isEmpty(inputText)){
            edit.setText(inputText);
            edit.setSelection(inputText.length());
            Toast.makeText(this,"你上次输入的数据是："+inputText,Toast.LENGTH_SHORT).show();
        }

        ImageButton button1 = (ImageButton) findViewById(R.id.image_button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Soft1714080902127MainActivity.this, Soft1714080902127Show1Activity.class);
                startActivity(intent);
            }
        });

    }

    //读取数据
    private String load() {
        FileInputStream in = null;
        BufferedReader reader=null;
        StringBuilder content=new StringBuilder();
        try{
            in =openFileInput("data");
            reader =new BufferedReader(new InputStreamReader(in));
            String line="";
            while ((line=reader.readLine())!=null){
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }

    //重写onDestrory方法
    @Override
    public void onDestroy(){
        super.onDestroy();
        String inputText=edit.getText().toString();
        //将输入的内容存储到文件中
        save(inputText);
    }

   //保存数据
    private void save(String inputText) {
        FileOutputStream out=null;
        BufferedWriter writer=null;
        try{
            out=openFileOutput("data", Context.MODE_PRIVATE);
            writer=new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                if (writer!=null){
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
