package edu.hzuapps.androidlabs.soft1714080902329;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Activity2 extends AppCompatActivity {
    public final String mFileName = "text1.txt";
    private Button save;

    TextView textView05;
    TextView textView06;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        textView05= (TextView)findViewById(R.id.textView05);
        textView06= (TextView)findViewById(R.id.textView06);

        Intent intent = getIntent();
        Bundle ces = intent.getBundleExtra("Ces");
        String thing = ces.getString("thing");
        String day = ces.getString("day");
        textView05.setText(thing);
        textView06.setText(day);

        save = findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save(textView05.getText().toString());
                Toast.makeText(Activity2.this,"保存的事件为："+read(),Toast.LENGTH_SHORT).show();

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

