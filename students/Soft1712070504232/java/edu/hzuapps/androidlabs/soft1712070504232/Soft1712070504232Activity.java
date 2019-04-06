package edu.hzuapps.androidlabs.soft1712070504232;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.widget.Toast.LENGTH_SHORT;

public class Soft1712070504232Activity extends Activity {
    private Button button;
    private EditText et_info;
    private Button btn_save;
    private Button btn_read;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1712070504232_activity);

        button = (Button)findViewById(R.id.button1);
        et_info = (EditText)findViewById(R.id.et_info);
        btn_save = (Button)findViewById(R.id.btn_save);
        btn_read = (Button) findViewById(R.id.btn_read);
        btn_save.setOnClickListener(new ButtonListener());
        btn_read.setOnClickListener(new ButtonListener());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Soft1712070504232Activity.this, Soft1712070504232Activity2.class);
                startActivity(intent);
            }
        });
    }
    private class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btn_save:
                String saveinfo = et_info.getText().toString().trim();
                    FileOutputStream fos;
                    try{
                        fos = openFileOutput("data.txt", Context.MODE_APPEND);
                        fos.write(saveinfo.getBytes());
                        fos.close();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft1712070504232Activity.this,"数据保存成功",LENGTH_SHORT).show();
                        break;
                case R.id.btn_read:
                    String content = "";
                    FileInputStream fis = null;
                    try {
                        fis = openFileInput("data.txt");
                        byte[] buffer = new byte[fis.available()];
                        fis.read(buffer);
                        content = new String(buffer);
                        fis.close();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft1712070504232Activity.this,"保存的数据是：" + content,LENGTH_SHORT).show();
                        break;
                    default:
                        break;

            }
        }
    }
}
