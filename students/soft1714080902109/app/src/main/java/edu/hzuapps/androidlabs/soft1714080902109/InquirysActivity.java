package edu.hzuapps.androidlabs.soft1714080902109;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import edu.hzuapps.androidlabs.Soft1714080902109.R;

public class InquirysActivity extends AppCompatActivity {
    private EditText et_info;
    private Button btn_save;
    private Button btn_read;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiry);

        //获取布局文件中的控件
        et_info=(EditText) findViewById(R.id.et_info);
        btn_save=(Button) findViewById(R.id.btn_save);
        btn_read=(Button) findViewById(R.id.btn_read);
        btn_save.setOnClickListener(new ButtonListener());
        btn_read.setOnClickListener(new ButtonListener());
    }
    //定义Button按钮的点击事件
    private class ButtonListener implements View.OnClickListener{
        public void onClick(View v){
            switch (v.getId()){
                case R.id.btn_save:
                    String saveinfo=et_info.getText().toString().trim();
                    FileOutputStream fos;
                    try{
                        //保存数据
                        fos=openFileOutput("data.txt", Context.MODE_APPEND);
                        fos.write(saveinfo.getBytes());
                        fos.close();
                    }   catch (Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(InquirysActivity.this,"数据保存成功",0).show();
                    break;
                case R.id.btn_read:
                    String content="";
                    try{
                        //获取保存的数据
                        FileInputStream fis=openFileInput("data.txt");
                        byte[] buffer=new byte[fis.available()];
                        fis.read(buffer);
                        content=new String(buffer);
                        fis.close();
                    }  catch (Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(InquirysActivity.this,"保存的数据是："+content,0).show();
                    break;
                    default:
                        break;
            }
        }
    }
}
