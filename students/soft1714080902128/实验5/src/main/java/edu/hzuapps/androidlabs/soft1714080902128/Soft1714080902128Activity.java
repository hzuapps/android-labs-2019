package edu.hzuapps.androidlabs.soft1714080902128;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Soft1714080902128Activity extends AppCompatActivity {
    private EditText info;
    private ImageButton read;
    private ImageButton save;
    private String fileName = "datae.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902128_activity);
        info = findViewById(R.id.info);
        read = findViewById(R.id.wendu);
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(getFilesDir().getAbsolutePath() , fileName);
                Intent intent=new Intent(Soft1714080902128Activity.this,SecondActivity.class);
                startActivity(intent);
                String saveInfo=info.getText().toString().trim();
                FileOutputStream fos;
                try {
                    fos=openFileOutput(fileName, MODE_APPEND);
                    fos.write(saveInfo.getBytes());
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(Soft1714080902128Activity.this,"success", Toast.LENGTH_SHORT).show();
            }
        });

        save = findViewById(R.id.shidu);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Soft1714080902128Activity.this,ThirdActivity.class);
                startActivity(intent);
                String content="";
                try {
                    FileInputStream fis=openFileInput(fileName);
                    byte [] buffer=new  byte[fis.available()];
                    fis.read(buffer);
                    content=new String(buffer);
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(Soft1714080902128Activity.this,"content:"+content,Toast.LENGTH_SHORT).show();
            }
        });
    }
}