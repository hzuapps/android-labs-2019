package edu.hzuapps.androidlabs.com1714080901137;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class RecieveActivity extends AppCompatActivity {
    private EditText et_info;
    private Button btn_read;
    private Button btn_save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve);
        et_info=(EditText) findViewById(R.id.et_info);
        btn_read=(Button) findViewById(R.id.btn_read);
        btn_save=(Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new ButtonListener());
        btn_read.setOnClickListener(new ButtonListener());
    }

    private class ButtonListener implements View.OnClickListener{
        public void onClick(View v){
            switch(v.getId()){
                case R.id.btn_save:
                    String saveinfo=et_info.getText().toString().trim();
                    FileOutputStream fos;
                    try{
                        fos=openFileOutput("data.txt", Context.MODE_APPEND);
                        fos.write(saveinfo.getBytes());
                        fos.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(RecieveActivity.this,"数据保存成功",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_read:
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
                    Toast.makeText(RecieveActivity.this,"保存的数据是："+content,Toast.LENGTH_SHORT).show();
                    break;
                    default:
                        break;
            }
        }
    }

}
