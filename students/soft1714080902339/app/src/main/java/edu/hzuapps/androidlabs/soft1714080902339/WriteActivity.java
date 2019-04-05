package edu.hzuapps.androidlabs.soft1714080902339;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;

public class WriteActivity extends AppCompatActivity {

    private EditText editTextW01;
    private EditText editTextW02;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        //获取布局文件
        editTextW01 = (EditText) findViewById(R.id.editTextW01);
        editTextW02 = (EditText) findViewById(R.id.editTextW02);
        buttonSave = (Button) findViewById((R.id.buttonSave));
      //  buttonSave.setOnClickListener(new ButtonListener());

        buttonSave .setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                switch (v.getId()) {
                    case R.id.buttonSave:
                        String saveinfo1 = editTextW01.getText().toString().trim();
                        String saveinfo2 = editTextW02.getText().toString().trim();
                        FileOutputStream fos;
                        try {
                            //保存数据
                            fos = openFileOutput("data.text", Context.MODE_APPEND);
                            fos.write(saveinfo1.getBytes());
                            fos.write(saveinfo2.getBytes());
                            fos.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                            ;
                        }
                        Toast.makeText(WriteActivity.this, "数据保存成功", 0).show();
                        break;
                    default:
                        break;

                }

            }
        });


    }
}