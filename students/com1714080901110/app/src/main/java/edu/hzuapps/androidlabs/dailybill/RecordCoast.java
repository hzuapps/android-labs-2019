package edu.hzuapps.androidlabs.dailybill;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class RecordCoast extends AppCompatActivity {
    private EditText et_coast;
    private Button btn_save;
    private Button btn_look;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_coast);
        //从布局文件中获取数据
        et_coast=findViewById(R.id.et_coast);
        btn_save=findViewById(R.id.btn_save);
        btn_look=findViewById(R.id.btn_look);
        //设计响应事件
        btn_save.setOnClickListener(new ButtonListener());
        btn_look.setOnClickListener(new ButtonListener());
    }
    //定义button按钮的点击事件
    private class ButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_save:
                    String saveinfo = et_coast.getText().toString().trim();
                    FileOutputStream fos;
                    try {
                        //保存数据
                        fos = openFileOutput("data.txt", Context.MODE_PRIVATE);
                        fos.write(saveinfo.getBytes());
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(RecordCoast.this, "保存成功", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.btn_look:
                    String content = "";
                    try {
                        FileInputStream inputStream = openFileInput("data.txt");
                        byte[] buffer = new byte[inputStream.available()];
                        inputStream.read(buffer);
                        content = new String(buffer);
                        inputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(RecordCoast.this, "你保存的数据是：" + content, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }

        }
    }
}
