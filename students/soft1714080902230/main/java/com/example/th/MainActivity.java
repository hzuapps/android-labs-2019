package com.example.th;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {


    private TextView hina_info;
    private Button   button_save;
    private Button  button_read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hina_info=(TextView)findViewById(R.id.hina_info);
        button_read=(Button)findViewById(R.id.button_read);
        button_save=(Button)findViewById(R.id.button_save);
        button_save.setOnClickListener(new ButtonListener());
        button_read.setOnClickListener(new ButtonListener());


        Button Button_skip = (Button) findViewById(R.id.button);
        Button_skip.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this, Main2Activity.class));
                    }
                });
    }


    private class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_save:
                    String saveInfo = hina_info.getText().toString().trim();
                    FileOutputStream fos;
                    try {
                        fos = openFileOutput("data.txt", MODE_APPEND);
                        fos.write(saveInfo.getBytes());//
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.this, "简介保存成功", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button_read:
                    String content = "";
                    try {
                        FileInputStream fis = openFileInput("data.txt");
                        byte[] buffer = new byte[fis.available()];
                        fis.read(buffer);
                        content = new String(buffer);
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.this, "" + content, Toast.LENGTH_SHORT).show();
                    break;
                default:
            }
        }
    }

}