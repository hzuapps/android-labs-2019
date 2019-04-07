package edu.hzuapps.androidlabs.soft1714080902440;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import soft1714080902440.androidlabs.hzuapps.edu.soft1714080902440.R;
public class Soft1714080902440Activity03 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft171408090244003);
        final EditText etext=(EditText)findViewById(R.id.editText);
        Button button7=findViewById(R.id.button7);
        Button btnOpen= findViewById(R.id.button8);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileOutputStream fos=null;
                String text=etext.getText().toString();
                try {
                    fos=openFileOutput("memo",MODE_PRIVATE);
                    fos.write(text.getBytes());
                    fos.flush();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        fos.close();
                        Toast.makeText(Soft1714080902440Activity03.this,"保存成功",Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Soft1714080902440Activity03.this,Soft1714080902440Activity.class));
            }
        });
    }
}