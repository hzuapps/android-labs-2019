package edu.hzuapps.androidlabs.soft1714080902111;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;



class Soft1714080902111TestActivity21 extends AppCompatActivity {

    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private Button btn_save;
    private Button btn_read;
///////////////////////////////////////////////////////////////
    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft1714080902111textactivity);//xml部分

        editText1=(EditText) findViewById(R.id.editText1);//这里出错过
        editText2=(EditText) findViewById(R.id.editText2);//这里出错过
        editText3=(EditText) findViewById(R.id.editText3);//这里出错过，写成R.id.btnTextView3
        btn_save=(Button)    findViewById(R.id.btn_save);
        btn_read=(Button)    findViewById(R.id.btn_read);
        btn_save.setOnClickListener(new ButtonListener());
        btn_read.setOnClickListener(new ButtonListener());
    }

    private class ButtonListener implements View.OnClickListener{
        @SuppressLint("WrongConstant")
        public void onClick(View v) {
            switch (v.getId()){
                //保存数据部分
                case R.id.btn_save:
                String savetext1=editText1.getText().toString().trim();
                String savetext2=editText2.getText().toString().trim();
                String savetext3=editText3.getText().toString().trim();
                FileOutputStream fos;
                try {
                    fos=openFileOutput("data.txt", Context.MODE_APPEND);
                    fos.write(savetext1.getBytes());
                    fos.write(savetext2.getBytes());
                    fos.write(savetext3.getBytes());
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                    Toast.makeText(Soft1714080902111TestActivity21.this,"周记保存成功",0).show();
                break;
                //读取数据部分
                case R.id.btn_read:
                    String content="";
                    try{
                        FileInputStream fis=openFileInput("data.txt");
                        byte[] buffer=new byte[fis.available()];
                        fis.read(buffer);
                        content=new String(buffer);
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft1714080902111TestActivity21.this,"保存的日记是："+content,0).show();
                    break;
                    default:
                        break;
            }
        }
    }
}