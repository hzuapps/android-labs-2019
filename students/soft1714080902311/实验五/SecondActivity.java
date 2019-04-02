package edu.hzuapps.androidlabs.soft1714080902311;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;

public class SecondActivity extends AppCompatActivity {
    private EditText editText3;
    private EditText editText1;
    private EditText editText2;
    private Button button9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //获取布局文件
        editText3 = (EditText) findViewById(R.id.editText3);
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        button9 = (Button) findViewById((R.id.button9));
        button9.setOnClickListener(new ButtonListener());
    }
    //定义Button按钮的点击事件
    private class ButtonListener implements View.OnClickListener{
        public void onClick(View v){
            switch(v.getId()){
                case R.id.button9:
                String saveinfo=editText3.getText().toString().trim();
                String saveinfo1=editText1.getText().toString().trim();
                String saveinfo2=editText2.getText().toString().trim();
                    FileOutputStream fos;
                    try{
                        //保存数据
                        fos=openFileOutput("data.text", Context.MODE_APPEND);
                        fos.write(saveinfo1.getBytes());
                        fos.write(saveinfo2.getBytes());
                        fos.write(saveinfo.getBytes());
                        fos.close();
                    } catch(Exception e){
                        e.printStackTrace();;
                    }
                    Toast.makeText(SecondActivity.this,"数据保存成功",0).show();
                    break;
                default:
                    break;

            }
        }


    }
}
