package com.example.edu.hzuapps.androidlabs.soft1714080902316;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.Toast;

import com.example.edu.hzuapps.androidlabs.R;

import java.io.FileOutputStream;

public class Writing extends AppCompatActivity {
    private EditText write;
    private Button button1;
    private Button button2;
    private TextClock textClock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writng);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        write=findViewById(R.id.edit_text);
        textClock=findViewById(R.id.textclock3);
        button1.setOnClickListener(new ButtonListener());
        button2.setOnClickListener(new ButtonListener());

    }
        private class ButtonListener implements OnClickListener{
            @Override
            public void onClick(View v){
                switch (v.getId()){
                    case R.id.button1:
                        Intent intent = new Intent(Writing.this, Soft1714080902316.class) ;
                        startActivity(intent);
                        break;
                    case R.id.button2:
                        String saveinfo=write.getText().toString().trim();
                        FileOutputStream fos;
                        try{
                            fos=openFileOutput(textClock.getFormat24Hour()+".txt", Context.MODE_APPEND);
                            fos.write(saveinfo.getBytes());
                            fos.close();
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        Toast.makeText(Writing.this,"日记保存成功",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }
    }
