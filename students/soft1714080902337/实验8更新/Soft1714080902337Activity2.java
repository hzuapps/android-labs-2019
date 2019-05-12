package edu.hzuapps.androidlabs.soft1714080902337;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.shake.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Soft1714080902337Activity2 extends AppCompatActivity {
    Button button = null;
    private Button btn;
    private ImageView imageview;
    private EditText Text1;
    private Button btn_save;
    private Button btn_read;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.soft_1714080902337_activity2);
        Text1=(EditText) findViewById(R.id.Text);
        btn_save=(Button)    findViewById(R.id.btn_save);
        btn_read=(Button)    findViewById(R.id.btn_read);
        btn_save.setOnClickListener(new Soft1714080902337Activity2.ButtonListener());
        btn_read.setOnClickListener(new Soft1714080902337Activity2.ButtonListener());
        StrictMode.setThreadPolicy(new
                StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(
                new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());

        button = findViewById(R.id.bb1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Soft1714080902337Activity2.this,Soft1714080902337Activity2.class);
                startActivity(intent);
            }
        });
        button = findViewById(R.id.bb2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Soft1714080902337Activity2.this,Soft1714080902337Activity4.class);
                startActivity(intent);
            }
        });
    }
    private class ButtonListener implements View.OnClickListener{
        @SuppressLint("WrongConstant")
        public void onClick(View v) {
            switch (v.getId()){
                //保存数据部分
                case R.id.btn_save:
                    String text1=Text1.getText().toString().trim();
                    FileOutputStream fos;
                    try {
                        fos=openFileOutput("data.txt", Context.MODE_APPEND);
                        fos.write(text1.getBytes());
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft1714080902337Activity2.this,"保存成功",0).show();
                    break;
                //读取数据部分
                case R.id.btn_read:
                    String content="";
                    try{
                        FileInputStream fis=openFileInput("data.txt");
                        byte[] buffer=new byte[fis.available()];
                        fis.read(buffer);
                        content=new String(buffer);
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft1714080902337Activity2.this,"你写的是"+content,0).show();
                    break;
                default:
                    break;
            }
        }
    }
}
