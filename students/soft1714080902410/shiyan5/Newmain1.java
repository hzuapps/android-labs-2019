package com.example.shiyan4;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Newmain1 extends AppCompatActivity {
    private EditText et_into;
    private EditText it_into;
    private Button Btn_Do;
    private Button Btn_save;
    private Button Btn_read;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymain1);
        et_into=(EditText) findViewById(R.id.et_info);
        it_into=(EditText) findViewById(R.id.it_info);
        Btn_Do=(Button) findViewById(R.id.Btn_Do);
        Btn_read=(Button) findViewById(R.id.Btn_read);
        Btn_save=(Button) findViewById(R.id.Btn_save);
        Btn_Do.setOnClickListener(new Newmain1.D());
        Btn_save.setOnClickListener(new Newmain1.L());
        Btn_read.setOnClickListener(new Newmain1.L());
    }
    private class D implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Newmain1.this, MainActivity.class);
            startActivity(intent);
        }
    }
    private class L implements View.OnClickListener{
        @SuppressLint("WrongConstant")
        public void onClick(View v){
            switch (v.getId()){
                case R.id.Btn_save:
                    String saveinfo1=et_into.getText().toString().trim();
                    String saveinfo2=it_into.getText().toString().trim();
                    FileOutputStream fos;
                    FileOutputStream fes;
                    try{
                        fos=openFileOutput("data.txt", Context.MODE_APPEND);
                        fos.write(saveinfo1.getBytes());
                        fos.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    try{
                        fes=openFileOutput("data.txt", Context.MODE_APPEND);
                        fes.write(saveinfo2.getBytes());
                        fes.close();
                    }catch (Exception e1){
                        e1.printStackTrace();
                    }
                    Toast.makeText(Newmain1.this,"已保存",0).show();
                    break;
                case R.id.Btn_read:
                    String content1="";
                    String content2="";
                    try{
                        FileInputStream fis=openFileInput("data.txt");
                        FileInputStream fas=openFileInput("data.txt");
                        byte[] buffer=new byte[fis.available()];
                        fis.read(buffer);
                        content1=new String(buffer);
                        content2=new String(buffer);
                        fis.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(Newmain1.this,"QQ帐号是:"+content1,0).show();

                    Toast.makeText(Newmain1.this,"QQ密码是:"+content2,0).show();
                    break;
                    default:
                        break;
            }

        }
    }



}
