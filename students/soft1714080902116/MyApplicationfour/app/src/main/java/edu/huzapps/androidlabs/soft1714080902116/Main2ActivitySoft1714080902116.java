package edu.huzapps.androidlabs.soft1714080902116;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.content.Context;

import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Main2ActivitySoft1714080902116 extends AppCompatActivity {

    private EditText et_info;
    private Button btn_save;
    private Button btn_read;
    private Button button2;
    private Button button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_soft1714080902116);

        et_info = (EditText) findViewById(R.id.et_info);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_read = (Button) findViewById(R.id.btn_read);
        button2=(Button) findViewById(R.id.button_2);
        button3=(Button) findViewById(R.id.button_3);
        btn_save.setOnClickListener(new ButtonListener());
        btn_read.setOnClickListener(new ButtonListener());
        button2.setOnClickListener(new ButtonListener());
        button3.setOnClickListener(new ButtonListener());

       /* Button button2 = (Button) findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent = new Intent( Main2ActivitySoft1714080902116.this,MainActivitySoft1714080902116.class);
                startActivity(intent);
            }

        });*/
    }

    private class ButtonListener implements View.OnClickListener{
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_save:

                    //存储
                    String sendinfo = et_info.getText().toString().trim();
                    FileOutputStream fos;
                    try {
                        //保存人物
                        fos = openFileOutput("data.txt", Context.MODE_APPEND);
                        fos.write(sendinfo.getBytes());
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Main2ActivitySoft1714080902116.this, "人物保存成功", Toast.LENGTH_SHORT).show();
                    break;

                //获取
                case R.id.btn_read:
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
                    Toast.makeText(Main2ActivitySoft1714080902116.this, "保存的数据是：" + content, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button_2:
                    Intent intent = new Intent( Main2ActivitySoft1714080902116.this,MainActivitySoft1714080902116.class);
                    startActivity(intent);
                    break;
                case R.id.button_3:
                    Intent intent1 = new Intent( Main2ActivitySoft1714080902116.this,Main3Activity.class);
                    startActivity(intent1);


                default:
                    break;

            }
        }
    }


}
