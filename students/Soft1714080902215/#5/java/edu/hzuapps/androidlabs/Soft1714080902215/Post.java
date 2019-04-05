package edu.hzuapps.androidlabs.Soft1714080902215;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Post extends AppCompatActivity {
    private EditText et_info;
    private Button btn_save;
    private Button btn_read;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post);
        Log.i("Post","onCreate()");
        et_info=(EditText) findViewById(R.id.postB1);
        btn_save=(Button) findViewById(R.id.save);
        btn_read=(Button) findViewById(R.id.look);
        btn_save.setOnClickListener(new ButtonListener());
        btn_read.setOnClickListener(new ButtonListener());
    }
    private class ButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.save:
                String saveinfo = et_info.getText().toString().trim();
                FileOutputStream fos;
                try {
                    fos = openFileOutput("data.txt", Context.MODE_APPEND);
                    fos.write(saveinfo.getBytes());
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(Post.this,"数据保存成功",0).show();
                break;
                case R.id.look:
                    String content="";
                    FileInputStream fis;
                    try{
                        fis=openFileInput("data.txt");
                        byte[] buffer=new byte[fis.available()];
                        fis.read(buffer);
                        content=new String(buffer);
                        fis.close();
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    Toast.makeText(Post.this,"保存的数据是："+content,0).show();
                    break;
                default:
                    break;
            }
        }
    }
}
