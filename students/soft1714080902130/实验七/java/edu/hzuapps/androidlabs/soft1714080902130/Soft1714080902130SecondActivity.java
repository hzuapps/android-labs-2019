package edu.hzuapps.androidlabs.soft1714080902130;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Soft1714080902130SecondActivity extends AppCompatActivity {

    public static final String SaveFileName="file_activities.txt";
    private EditText editText;
    private Button save_button;
    private Button load_button;
    private Button photo_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902130_second);
        editText=(EditText)findViewById(R.id.et_info);
        save_button=(Button)findViewById(R.id.save_button);
        load_button=(Button)findViewById(R.id.load_button);
        photo_button=(Button)findViewById(R.id.photo);
        save_button.setOnClickListener(new ButtonListener());
        load_button.setOnClickListener(new ButtonListener());
        photo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Soft1714080902130SecondActivity.this,
                        Soft1714080902130PhotoActivity.class);
                startActivity(intent);
            }
        });
    }
    private class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.save_button:
                    String saveInfo=editText.getText().toString().trim();
                    FileOutputStream fos;
                    try {
                        fos=openFileOutput(SaveFileName,MODE_PRIVATE);
                        fos.write(saveInfo.getBytes());
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft1714080902130SecondActivity.this,"数据保存成功",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.load_button:
                    String content="";
                    try {
                        FileInputStream fis=openFileInput(SaveFileName);
                        byte [] buffer=new  byte[fis.available()];
                        fis.read(buffer);
                        content=new String(buffer);
                        fis.close();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft1714080902130SecondActivity.this,"保存活动为："
                            +content,Toast.LENGTH_SHORT).show();
                    break;
                default:
            }
        }
    }
}
