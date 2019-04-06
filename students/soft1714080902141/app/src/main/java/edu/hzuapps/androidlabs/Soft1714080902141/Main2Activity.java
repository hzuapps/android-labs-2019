package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Main2Activity extends AppCompatActivity {


    public Button button;
    public EditText edit_text;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
       button=findViewById(R.id.button3);
              edit_text=findViewById(R.id.a3);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                           public void onClick(View v) {
                     String saveinfo = edit_text.getText().toString().trim();
                     FileOutputStream fos;
                     FileInputStream fis;
                     String content = "";

                     try {
                         fos = openFileOutput("data.txt", MODE_PRIVATE);
                         fos.write(saveinfo.getBytes());
                         fos.close();
                     } catch (Exception e) {
                         e.printStackTrace();
                     }
                     try {

                          fis = openFileInput("data.txt");
                         byte[] buffer = new byte[fis.available()];
                         fis.read(buffer);

                         content = new String(buffer);
                         fis.close();

                     } catch (Exception e) {
                         e.printStackTrace();
                     }
                     Toast.makeText(Main2Activity.this, "欢迎您，" + content + "!", Toast.LENGTH_LONG).show();
                }

            });
    }
}
