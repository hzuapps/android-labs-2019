package com.example.soft1714080902427test5application;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private EditText yonghu,chepai,leixin,tell,zhuzhi;
    private Button bu_save,bu_read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        yonghu=(EditText) findViewById(R.id.yonghu);
        chepai=(EditText) findViewById(R.id.chepai);
        leixin=(EditText) findViewById(R.id.leixin);
        tell=(EditText) findViewById(R.id.tell);
        zhuzhi=(EditText) findViewById(R.id.zhuzhi);

    }

    private class ButtonListener implements View.OnClickListener {
        public void onClick(View v){
            switch(v.getId()){
                case R.id.bu_save:
                    String saveyonghu=yonghu.getText().toString().trim();
                    String savechepai=chepai.getText().toString().trim();
                    String saveleixin=leixin.getText().toString().trim();
                    String savetell=tell.getText().toString().trim();
                    String savezhuzhi=zhuzhi.getText().toString().trim();
                    FileOutputStream fos;
                    try{
                        fos=openFileOutput("data.text", Context.MODE_APPEND);
                        fos.write(saveyonghu.getBytes());
                        fos.write(savechepai.getBytes());
                        fos.write(saveleixin.getBytes());
                        fos.write(savetell.getBytes());
                        fos.write(savezhuzhi.getBytes());
                        fos.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.this,"数据保存成功",2).show();
                    break;
                case R.id.bu_read:
                    String content="";
                    FileInputStream fis;
                    try{
                       fis=openFileInput("data.text");
                       byte[] buffer=new byte[fis.available()];
                       fis.read(buffer);
                       content=new String(buffer);
                       fis.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.this,"保存的数据是："+content,2).show();
                    break;
                default:break;
            }
        }
    }
}
