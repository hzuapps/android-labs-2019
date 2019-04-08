package edu.hzuapps.androidlabs.soft1714080902414;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Soft1714080902414 extends AppCompatActivity implements View.OnClickListener {
    private Button button1;
    private Button button2;
    private EditText editText1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=(Button) findViewById(R.id.bt1);
        button2=(Button) findViewById(R.id.bt2);
        editText1=(EditText) findViewById(R.id.et1);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.bt1: String save=editText1.getText().toString().trim();
            FileOutputStream fos;
            try{
                fos=openFileOutput("data.txt",MODE_PRIVATE);
                fos.write(save.getBytes());
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
                Toast.makeText( Soft1714080902414.this,"数据保存成功",Toast.LENGTH_SHORT).show();
            break;
            case R.id.bt2:   String content="";
                try {
                    FileInputStream fis=openFileInput("data.txt");
                    byte[] buffer=new byte[fis.available()];
                    fis.read(buffer);
                    content=new String(buffer);
                    fis.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(Soft1714080902414.this,"保存的数据为"+content,Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
