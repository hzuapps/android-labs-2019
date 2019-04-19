package edu.hzuapps.androidlabs.soft1714080902322;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class FilesaveActivity extends AppCompatActivity {
    private EditText editText0;
    private Button btn_save;
    private Button btn_read;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filesave);//获取布局文件中的控件
        editText0=(EditText) findViewById(R.id.editText0);
        btn_save=(Button) findViewById(R.id.btn0);
        btn_read=(Button) findViewById(R.id.btn1);
        btn_save.setOnClickListener(new ButtonListener());
        btn_read.setOnClickListener(new ButtonListener());

    }
    //定义Button按钮的点击事件
    private class ButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.btn0:
                    String hang="\n";
                    String saveinfo1=editText0.getText().toString().trim();
                    FileOutputStream fos;
                    try {
                        //保留数据
                        fos=openFileOutput("data01.txt", Context.MODE_APPEND);
                        fos.write(hang.getBytes());
                        fos.write(saveinfo1.getBytes());
                        fos.write(hang.getBytes());
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(FilesaveActivity.this,"数据保存成功",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn1:
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
                    Toast.makeText(FilesaveActivity.this,"保存的数据是："+content,Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }
}