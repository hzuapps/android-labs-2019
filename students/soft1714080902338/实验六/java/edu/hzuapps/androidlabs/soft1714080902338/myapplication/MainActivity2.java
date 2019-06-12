package edu.hzuapps.androidlabs.soft1714080902338.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {
    private EditText et_day;
    private Button sent,read;
    private TextView content;
    private final String mFileName="test.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        et_day=findViewById(R.id.et_day);
        sent=findViewById(R.id.sent);
        read=findViewById(R.id.read);
        content=findViewById(R.id.content);
        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(et_day.getText().toString());
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content.setText(read());
            }
        });


    }

    //存储
    private void save(String content){
        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream=openFileOutput(mFileName,MODE_PRIVATE);
            fileOutputStream.write(content.getBytes());
        }  catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileOutputStream!=null)
            {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //读取
    private String read(){
        FileInputStream fileInputStream=null;
        try {
            fileInputStream=openFileInput(mFileName);
            byte[] buff=new byte[1024];
            StringBuilder sb=new StringBuilder("");
            int len=0;
            while((len=fileInputStream.read(buff))>0){
                sb.append(new String(buff,0,len));
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
