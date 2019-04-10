package edu.hzuapps.androidlabs.soft1709081602231;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileStorageActivity extends AppCompatActivity {
    private EditText editText;
    private Button save;
    private Button read;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_storage_activity);
        editText=(EditText)findViewById(R.id.editText);
        save=(Button)findViewById(R.id.save);
        read=(Button)findViewById(R.id.read);
        save.setOnClickListener(new ButtonListener());
        read.setOnClickListener(new ButtonListener());
    }
    private class ButtonListener implements View.OnClickListener {
        public void onClick(View v){
            switch (v.getId()){
                case R.id.save:
                    String saveinfo=editText.getText().toString().trim();
                    FileOutputStream fos;
                    try{
                        fos=openFileOutput("data.txt", Context.MODE_APPEND);
                        fos.write(saveinfo.getBytes());
                        fos.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(FileStorageActivity.this,"数据保存成功",0).show();
                    break;
                case R.id.read:
                    String content="";
                    try{
                        FileInputStream fis=openFileInput("data.txt");
                        byte[] buffer=new byte[fis.available()];
                        fis.read(buffer);
                        content=new String(buffer);
                        fis.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(FileStorageActivity.this,"保存的数据是："+content,0).show();
                    break;
                    default:
                    break;
            }
        }
    }
}


