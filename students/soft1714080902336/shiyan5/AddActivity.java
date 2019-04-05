package edu.hzuapps.androidlabs.soft1714080902336;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class AddActivity extends AppCompatActivity {
    private EditText et_info01;
    private EditText et_info02;
    private EditText et_info03;
    private Button btn_save;
    private Button btn_read;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);//获取布局文件中的控件
        et_info01=(EditText) findViewById(R.id.et_info01);
        et_info02=(EditText) findViewById(R.id.et_info02);
        et_info03=(EditText) findViewById(R.id.et_info03);
        btn_save=(Button) findViewById(R.id.btn_save);
        btn_read=(Button) findViewById(R.id.btn_read);
        btn_save.setOnClickListener(new ButtonListener());
        btn_read.setOnClickListener(new ButtonListener());

    }
    //定义Button按钮的点击事件
    private class ButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.btn_save:
                    String hang="\n";
                    String saveinfo1=et_info01.getText().toString().trim();
                    String saveinfo2=et_info02.getText().toString().trim();
                    String saveinfo3=et_info03.getText().toString().trim();
                    FileOutputStream fos;
                    try {
                        //保留数据
                        fos=openFileOutput("data01.txt", Context.MODE_APPEND);
                        fos.write(hang.getBytes());
                        fos.write(saveinfo1.getBytes());
                        fos.write(hang.getBytes());
                        fos.write(saveinfo2.getBytes());
                        fos.write(hang.getBytes());
                        fos.write(saveinfo3.getBytes());
                        fos.write(hang.getBytes());
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(AddActivity.this,"数据保存成功",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_read:
                    String content="";
                    try {
                        //获取保存的数据
                        FileInputStream fis=openFileInput("data01.txt");
                        byte[] buffer=new byte[fis.available()];
                        fis.read(buffer);
                        content=new String(buffer);
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(AddActivity.this,"保存的数据是："+content,Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }
}