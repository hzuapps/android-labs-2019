package edu.hzuapps.androidlabs.soft1714080902131;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Soft1714080902131ModifyActivity extends AppCompatActivity {
    private Button btn1;
    private EditText et1;
    public final String mFileName = "text.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902131_modify);
        btn1 = findViewById(R.id.rs);
        et1 = findViewById(R.id.et);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    save(et1.getText().toString());
                    Toast.makeText(Soft1714080902131ModifyActivity.this,"保存的文字为："+read(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void save(String content){
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput(mFileName,MODE_PRIVATE);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private   String read()
    {
        FileInputStream fileInputStream= null;
        try {
            fileInputStream = openFileInput(mFileName);
            byte[] buff = new byte[1024];
            StringBuilder sb = new StringBuilder("");//实现字符串的拼接
            int len;
            while((len=fileInputStream.read(buff))>0)
            {
                sb.append(new String(buff,0,len));
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileInputStream != null)
            {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }        }
        return null;
    }

}
