package edu.hzuapps.androidlabs.soft1714080902305;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Save_Activity extends AppCompatActivity {
    private EditText edit_money;
    private Button buttonchong;
    private Button buttoncha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fouth);//获取布局文件中的控件
        edit_money=(EditText) findViewById(R.id.edit_money);
        buttonchong=(Button) findViewById(R.id.buttonchong);
        buttoncha=(Button) findViewById(R.id.buttoncha);
        buttonchong.setOnClickListener(new ButtonListener());
        buttoncha.setOnClickListener(new ButtonListener());

    }
    //定义Button按钮的点击事件
    private class ButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.buttonchong:
                    String hang = "\n";
                    String saveinfo5 = edit_money.getText().toString().trim();
                    FileOutputStream fos;
                    try {
                        //保留数据
                        fos = openFileOutput("record.txt", Context.MODE_APPEND);
                        fos.write(hang.getBytes());
                        fos.write(saveinfo5.getBytes());
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Save_Activity.this,"数据保存成功",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.buttoncha:
                    String content="";
                    try {
                        //获取保存的数据
                        FileInputStream fis=openFileInput("record.txt");
                        byte[] buffer=new byte[fis.available()];
                        fis.read(buffer);
                        content=new String(buffer);
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Save_Activity.this,"充值记录是："+content,Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }
}


