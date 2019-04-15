package edu.hzuapps.androidlabs.soft1714080902434;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ThirdActivity extends AppCompatActivity {
    private EditText evaluation;
    private Button save_eva;
    private Button read_eva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        //获取布局文件中的控件
        evaluation=(EditText) findViewById(R.id.evaluation);
        save_eva=(Button) findViewById(R.id.save_eva);
        read_eva=(Button) findViewById(R.id.read_eva);
        save_eva.setOnClickListener(new ButtonListener());
        read_eva.setOnClickListener(new ButtonListener());
    }
    //定义Button按钮的点击事件
    private class ButtonListener implements View.OnClickListener{
        public void onClick(View v){
            switch(v.getId()){
                case R.id.save_eva:
                    String saveinfo=evaluation.getText().toString().trim();
                    FileOutputStream fos;
                    try{
                        //保存数据
                        fos=openFileOutput("data.txt", Context.MODE_APPEND);
                        fos.write(saveinfo.getBytes());
                        fos.close();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(ThirdActivity.this,"评价保存成功",Toast.LENGTH_LONG).show();
                    break;
                case R.id.read_eva:
                    String content="";
                    try{
                        //获取保存的数据
                        FileInputStream fis=openFileInput("data.txt");
                        byte[] buffer=new byte[fis.available()];
                        fis.read(buffer);
                        content=new String(buffer);
                        fis.close();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(ThirdActivity.this,"保存的评价是："+content,Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
        }
    }
}
