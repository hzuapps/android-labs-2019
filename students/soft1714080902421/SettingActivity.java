package edu.hzuapps.androidlabs.soft1714080902421;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;


public class SettingActivity extends AppCompatActivity {
    private EditText et_info;
    private Button mBtnread=null;
    private Button mBtnsave=null;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        //获取布局文件中的控件
        et_info = findViewById(R.id.et_info);
        mBtnread = findViewById(R.id.btn_read);
        mBtnsave = findViewById(R.id.btn_save);
        setOnClickListener();
        //根据id找到按钮
    }

    private void setOnClickListener(){
        OnClick onClick = new OnClick();
        mBtnread.setOnClickListener(onClick);
        mBtnsave.setOnClickListener(onClick);
        //设置点击事件
    }

    private class  OnClick  implements View.OnClickListener{
        @Override
        public void onClick(View v){
            switch (v.getId()) {
                case R.id.btn_save:
                    String saveinfo=et_info.getText().toString().trim();
                    FileOutputStream fos;
                    try {
                        //保存设定
                        fos = openFileOutput("data.txt", MODE_APPEND);
                        fos.write(saveinfo.getBytes());
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(SettingActivity.this, "设定保存成功", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_read:
                    String context = "";
                    try {
                        //获取已保存设定
                        FileInputStream fis = openFileInput("data.txt");
                        byte[] buffer = new byte[fis.available()];
                        fis.read(buffer);
                        context = new String(buffer);
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(SettingActivity.this, "已保存的设定为：" + context,Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }

}
