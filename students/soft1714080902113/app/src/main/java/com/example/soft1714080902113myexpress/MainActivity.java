package com.example.soft1714080902113myexpress;



import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class MainActivity extends AppCompatActivity  {

    private EditText toFindPackage;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }

        toFindPackage=(EditText) findViewById(R.id.toFindPackage);
        String inputText=load();
        if(!TextUtils.isEmpty(inputText)){
            toFindPackage.setText(inputText);
            toFindPackage.setSelection(inputText.length());
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        String inputText=toFindPackage.getText().toString();
        save(inputText);
    }
    public void save(String inputText){
        FileOutputStream out;
        BufferedWriter writer=null;
        try{
            out=openFileOutput("data", Context.MODE_PRIVATE);
            writer=new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(writer!=null){
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public String load(){
        FileInputStream in;
        BufferedReader reader=null;
        StringBuilder content=new StringBuilder();
        try{
            in=openFileInput("data");
            reader=new BufferedReader(new InputStreamReader(in));
            String line="";
            while ((line=reader.readLine())!=null){
                content.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(reader!=null){
                try {
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }
}

