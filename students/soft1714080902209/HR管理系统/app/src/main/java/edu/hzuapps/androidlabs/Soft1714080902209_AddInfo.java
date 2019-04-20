package edu.hzuapps.androidlabs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Soft1714080902209_AddInfo extends AppCompatActivity {
    private Button Add_SaveBtn;
    private  Button Add_ReadBtn;

    public boolean IsSave(String FileName){
        String ReadName;
        String name=((EditText)findViewById(R.id.Add_Name)).getText().toString();
        String ReadInfo = "";
        FileInputStream FIS;
        try{
            FIS = openFileInput(FileName);
            byte[] ri = new byte[FIS.available()];
            FIS.read(ri);
            ReadInfo = new String(ri);
            String[] ReadCut = ReadInfo.split("#");//分割字符串，以#作为标识
            ReadName = ReadCut[0];
            if(ReadName.equals(name)){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft1714080902209_addinfo);



        Add_SaveBtn=(Button)findViewById(R.id.Add_SaveBtn);
        Add_SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=((EditText)findViewById(R.id.Add_Name)).getText().toString();
                String age="#" + ((EditText)findViewById(R.id.Add_Age)).getText().toString();
                String sex="#" + ((EditText)findViewById(R.id.Add_Sex)).getText().toString();
                String tel="#" + ((EditText)findViewById(R.id.Add_TEL)).getText().toString();//使用分隔符号#对信息进行隔开
                String FileName = name + ".txt";
                FileOutputStream FOS;
                try{
                    FOS = openFileOutput(FileName, MODE_PRIVATE);
                    FOS.write(name.getBytes());
                    FOS.write(age.getBytes());
                    FOS.write(sex.getBytes());
                    FOS.write(tel.getBytes());
                    FOS.close();
                    if(IsSave(FileName)){
                        Toast.makeText(Soft1714080902209_AddInfo.this,"保存成功",Toast.LENGTH_SHORT).show();
                        Intent intent_login = new Intent();
                        intent_login.setClass(Soft1714080902209_AddInfo.this, Soft1714080902209_Login.class);
                        startActivity(intent_login);
                        Soft1714080902209_AddInfo.this.finish();//保存完后跳转回login，并杀掉add activity
                    }else{
                        Toast.makeText(Soft1714080902209_AddInfo.this,"保存失败",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });



        Add_ReadBtn=(Button)findViewById(R.id.Add_ReadBtn);
        Add_ReadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /*本地读取测试
                EditText Add_ETname = findViewById(R.id.add_Name);
                EditText Add_ETsex = findViewById(R.id.add_Sex);
                EditText Add_ETage = findViewById(R.id.add_Age);
                EditText Add_ETtel = findViewById(R.id.add_TEL);
                String readinfo = "";
                FileInputStream FIS;
                try{
                    FIS = openFileInput("ZhangSan.txt");
                    byte[] ri = new byte[FIS.available()];
                    FIS.read(ri);
                    readinfo = new String(ri);
                    String[] readcut = readinfo.split("#");//分割字符串，以#作为标识
                    Add_ETname.setText(readcut[0]);
                    Add_ETage.setText(readcut[1]);
                    Add_ETsex.setText(readcut[2]);
                    Add_ETtel.setText(readcut[3]);
                }catch (Exception e){
                    e.printStackTrace();
                }*/


            }
        });
    }
}
