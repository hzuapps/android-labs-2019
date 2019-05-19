package com.example.soft1714080902422;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private Button button1;
    private EditText et_info1;
    private EditText et_info2;
    private EditText et_info3;
    private EditText et_info4;
    private EditText et_info5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_info1 = (EditText) findViewById(R.id.et_info1);
        et_info2 = (EditText) findViewById(R.id.et_info2);
        et_info3 = (EditText) findViewById(R.id.et_info3);
        et_info4 = (EditText) findViewById(R.id.et_info4);
        et_info5 = (EditText) findViewById(R.id.et_info5);
        button1 = (Button) findViewById(R.id.button);



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, NewActivity.class);
                startActivity(intent);
                switch (v.getId()) {
                    case R.id.button:
                        String saveInfo = et_info1.getText().toString().trim();
                        String saveInfo2 = et_info2.getText().toString().trim();
                        String saveInfo3 = et_info3.getText().toString().trim();
                        String saveInfo4 = et_info4.getText().toString().trim();
                        String saveInfo5 = et_info5.getText().toString().trim();
                        FileOutputStream fos;
                        try {
                            fos = openFileOutput("data.txt", MODE_APPEND);//把文件输出到data中
                            fos.write(saveInfo.getBytes());//将我们写入的字符串变成字符数组）
                            fos.write(saveInfo2.getBytes());
                            fos.write(saveInfo3.getBytes());
                            fos.write(saveInfo4.getBytes());
                            fos.write(saveInfo5.getBytes());
                            fos.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(MainActivity.this, "数据保存成功", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });
    }
};
