package edu.hzuapps.androidlabs.soft1714080902313;


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

public class Soft1714080902313Activity2 extends AppCompatActivity {
    private EditText message;
    private Button save,show;
    private TextView content;
    private final String mFileName="test.txt";

    public Soft1714080902313Activity2() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_soft1714080902313);
        message=findViewById(R.id.message);
        save=findViewById(R.id.save);
        show=findViewById(R.id.show);
        content=findViewById(R.id.content);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(message.getText().toString());
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
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
