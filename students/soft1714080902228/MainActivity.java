package edu.hzuapps.androidlabs.soft1714080902228;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.SearchManager;
import android.content.Context;
import android.support.v4.os.CancellationSignal;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText et_info;
    private Button btn_save;
    private Button btn_read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取布局文件中的控件
        EditText et_info = (EditText) findViewById(R.id.et_info);
        Button btn_read = (Button) findViewById(R.id.btn_read);
        Button btn_save = (Button) findViewById(R.id.btn_save);
    }

    //定义button按钮的点击事件
    private abstract class ButtonListener implements CancellationSignal.OnCancelListener {
        @SuppressLint("WrongConstant")
        public void onClick(View v) throws IOException {
            switch (v.getId()) {
                case R.id.btn_save:
                    String saveinfo = et_info.getText().toString().trim();
                    FileOutputStream fos;
                    try {
                        //保存数据
                        fos = openFileOutput("data.txt", Context.MODE_APPEND);
                        fos.write(saveinfo.getBytes());
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.this,"数据保存成功",0).show();
                    break;
                case R.id.btn_read:
                    String content = "";
                    try {
                        //获取保存的数据
                        FileInputStream fis = openFileInput("data.txt");
                        byte[] buffer = new byte[fis.available()];
                        fis.read(buffer);
                        content = new String(buffer);
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.this, "保存的数据是：" + content, 0).show();
                    break;
                default:
                    break;
            }
        }
    }
}
