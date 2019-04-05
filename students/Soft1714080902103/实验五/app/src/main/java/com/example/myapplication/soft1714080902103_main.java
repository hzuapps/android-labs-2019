package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class soft1714080902103_main extends AppCompatActivity
{
    private EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902103_main);
        edit =(EditText) findViewById(R.id.edit);
        String inputText=load();
        if(!TextUtils.isEmpty(inputText)){
            edit.setText(inputText);
            edit.setSelection(inputText.length());
            Toast.makeText(this,"Restoring succeeded",Toast.LENGTH_SHORT).show();
        }
        Button button1=(Button) findViewById(R.id.button);
        Button button2=(Button) findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener()
                                   {
                                       @Override
                                       public  void onClick(View v){
                                           Toast.makeText(soft1714080902103_main.this,"正在进入",
                                                   Toast.LENGTH_SHORT).show();
                                           Intent intent = new Intent(soft1714080902103_main.this, soft1714080902103_login1.class);
                                           startActivity(intent);
                                                                     }
                                   }

                                   );
        button2.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public  void onClick(View v){
                                           Toast.makeText(soft1714080902103_main.this,"正在进入",
                                                   Toast.LENGTH_SHORT).show();
                                           Intent intent = new Intent(soft1714080902103_main.this, soft1714080902103_login2.class);
                                           startActivity(intent);
                                       }
                                                              }
                                    );
                                                             }

    public String load() {
        FileInputStream in = null;
        BufferedReader reader =null;
        StringBuilder content = new StringBuilder();
        try {
            in = openFileInput("date");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while((line = reader.readLine()) != null){
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }return content.toString();
    }


    @Override
    protected  void onDestroy(){
        super.onDestroy();
        String inputText=edit.getText().toString();
        save(inputText);
    }
    public void save(String inputText){
        FileOutputStream out=null;
        BufferedWriter writer=null;
        try{
            out=openFileOutput("date", Context.MODE_PRIVATE);
            writer=new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(writer!=null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
