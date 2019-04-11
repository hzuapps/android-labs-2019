package edu.hzuapps.androidlabs.soft1714080902227;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tuku.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Soft1714080902227Activity7 extends AppCompatActivity {

    private EditText et_info;
    private Button btn_save;
    private Button btn_read;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft17140809022277);

        et_info=(EditText)findViewById(R.id.et_info);
        btn_save=(Button)findViewById(R.id.btn_save);
        btn_read=(Button)findViewById(R.id.btn_read);
        btn_save.setOnClickListener(new ButtonListener());
        btn_read.setOnClickListener(new ButtonListener());
    }

    private class ButtonListener implements View.OnClickListener{

        @SuppressLint("WrongConstant")
        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.btn_save:
                    String saveinfo=et_info.getText().toString().trim();
                    FileOutputStream fos;
                    try{
                        fos = openFileOutput("data.txt", Context.MODE_APPEND);
                        fos.write(saveinfo.getBytes());
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft1714080902227Activity7.this,"数据保存成功",0).show();
                    break;
                case R.id.btn_read:
                    String content="";
                    try{
                        FileInputStream fis=openFileInput("data.txt");
                        byte[] buffer=new byte[fis.available()];
                        fis.read(buffer);
                        content=new String(buffer);
                        fis.close();
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft1714080902227Activity7.this,"数据的数据是："+content,0).show();
                    break;
                    default:
                        break;

            }
        }
    }
}
