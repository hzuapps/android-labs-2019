package com.example.edu.hzuapps.androidlabs.soft1714080902316;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.example.edu.hzuapps.androidlabs.R;
import java.lang.String;
import java.io.FileOutputStream;

public class writng extends AppCompatActivity {
    private EditText write;
    private Button button1;
    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writng);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        write=findViewById(R.id.edit_text);
        button1.setOnClickListener(new ButtonListener());
        button2.setOnClickListener(new ButtonListener());
    }
        private class ButtonListener implements OnClickListener{
            @Override
            public void onClick(View v){
                switch (v.getId()){
                    case R.id.button1:
                        Intent intent = new Intent(writng.this, Soft1714080902316.class) ;
                        startActivity(intent);
                        break;
                    case R.id.button2:
                        String saveinfo=write.getText().toString().trim();
                        FileOutputStream fos;
                        try{
                            fos=openFileOutput("data.txt", Context.MODE_APPEND);
                            fos.write(saveinfo.getBytes());
                            fos.close();
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        Toast.makeText(writng.this,"日记保存成功",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }
    }
