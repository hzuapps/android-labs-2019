package edu.hzuapps.androidlabs.com1714080901136;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Com136Activity2 extends Activity {
    private Button button1;
    private Button button2;
    private Button button3;
    private EditText edit_1;
    private EditText edit_2;
    private EditText edit_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com1362);
       button1= findViewById(R.id.clean);
       button2= findViewById(R.id.sava);
       button3= findViewById(R.id.read);
        edit_1=findViewById(R.id.biaoti);
        edit_2=findViewById(R.id.riqi);
        edit_3=findViewById(R.id.beizhu);
        button1.setOnClickListener(new ButtonListener());
        button2.setOnClickListener(new ButtonListener());
        button3.setOnClickListener(new ButtonListener());
    }
private class ButtonListener implements View.OnClickListener{
    @SuppressLint("WrongConstant")
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sava:
                String biaoti = edit_1.getText().toString().trim();
                String riqi = edit_2.getText().toString().trim();
                String beizhu = edit_3.getText().toString().trim();
                String sava_info="标题："+biaoti+"\n"+"日期："+riqi+"\n"+"备注:"+beizhu;
                FileOutputStream fos;
                try {
                    fos = openFileOutput("data.txt", Context.MODE_APPEND);
                    fos.write(sava_info.getBytes());

                    fos.close();
                }catch(Exception e) {
                    e.printStackTrace();

                }
                Toast.makeText(Com136Activity2.this, "数据保存成功", 0).show();
            case R.id.read:
                String content = "";
                try {
                    FileInputStream fis = openFileInput("data.txt");
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    content = new String(buffer);
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(Com136Activity2.this, "保存的数据是：" + content, 0).show();
                break;
            default:
                break;
            case R.id.clean:
                Intent intent = new Intent(Com136Activity2.this,Com136Activity2.class);

                startActivity(intent);
        }}
    }



}







