package edu.hzuapps.androidlabs.soft1714080902221;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.StringReader;


public class Soft1714080902221activity_shiyan5 extends Activity {

    private EditText info;
    private Button baochun;
    private Button xianshi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shiyan5);

        info = (EditText) findViewById(R.id.info);
        baochun=(Button)findViewById(R.id.baochun);
        xianshi=(Button)findViewById(R.id.xianshi);
        baochun.setOnClickListener(new ButtonListener());
        xianshi.setOnClickListener(new ButtonListener());
    }
    private class ButtonListener implements View.OnClickListener {
        private String content;

        @SuppressLint("WrongConstant")
        public void onClick(View v){
            switch(v.getId()){
                case R.id.xianshi:
                    String saveinfo=info.getText().toString().trim();
                    FileOutputStream fos;
                    try{
                        fos=openFileOutput("data.txt", Context.MODE_APPEND);
                        fos.write(saveinfo.getBytes());
                        fos.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft1714080902221activity_shiyan5.this,"明天应做的事情记录成功",0).show();
                    break;
                case R.id.baochun:
                    String Content="";
                    try{
                        FileInputStream fis=openFileInput("data.txt");
                        byte[] buffer=new byte[fis.available()];
                        fis.read(buffer);
                        content=new String(buffer);
                        fis.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft1714080902221activity_shiyan5.this,"记录的事情是："+content,0).show();
                    break;
                    default:
                        break;
            }
        }
    }
}
