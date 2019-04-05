package com.example.myapplication;

import android.app.Notification;
import android.app.assist.AssistStructure;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ResourceCursorAdapter;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.zip.CheckedOutputStream;


public class MainActivity extends AppCompatActivity {


    private EditText edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit =(EditText) findViewById(R.id.edit_text);
        String inputText = load();
        if(!TextUtils.isEmpty(inputText)){
            edit.setText(inputText);
            edit.setSelection(inputText.length());
            Toast.makeText(this, "Restoring succeeded", Toast.LENGTH_SHORT).show();
        }
        Button button = (Button) findViewById(R.id.buttonx);
        Button button1 = (Button) findViewById(R.id.button) ;
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.button:
                            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                            dialog.setCancelable(false);
                            dialog.setTitle("喜欢我吗？");
                            dialog.setMessage("有以下选择：");
                            dialog.setPositiveButton("真的很喜欢呢", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            dialog.setNegativeButton("喜欢呢", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            dialog.show();

                            break;
                            default:
                                break;
                    }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Main3Activity.class);
                startActivity(intent);

            }
        });

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        String inputText = edit.getText().toString();
        save(inputText);
    }
   public void save(String inputText)
   {
       FileOutputStream out=null;
       BufferedWriter writer = null;
       try{
           out=openFileOutput("data", Context.MODE_PRIVATE);
           writer = new BufferedWriter(new OutputStreamWriter(out));
           writer.write(inputText);
       }catch (IOException e){
           e.printStackTrace();
       }finally {
           try {
               if (writer !=null){
                   writer.close();
               }
           }catch(IOException e){
               e.printStackTrace();
           }
       }
   }
public String load(){
    FileInputStream in = null;
    BufferedReader reader = null;
    StringBuilder content = new StringBuilder();
    try {
        in = openFileInput("data");
        reader = new BufferedReader(new InputStreamReader(in));
        String line = "";
        while ((line = reader.readLine())!=null){
            content.append(line);
        }
    }catch (IOException e){
        e.printStackTrace();
    }finally{
        if(reader !=null){
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
