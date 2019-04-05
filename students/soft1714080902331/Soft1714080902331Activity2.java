package edu.hzuapps.androidlabs.soft1714080902331;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Soft1714080902331Activity2 extends AppCompatActivity {
    private EditText record;
    private Button button1;
    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902331_activity2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");// HH:mm:ss
//获取当前时间
        TextView data = (TextView) findViewById(R.id.data);
        Date date = new Date(System.currentTimeMillis());
        data.setText("Today:" + simpleDateFormat.format(date));
        record = (EditText) findViewById(R.id.record);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button1.setOnClickListener(new ButtonListener());
        button2.setOnClickListener(new ButtonListener());
    }

    private class ButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button1:
                    String saveinfo = record.getText().toString().trim();
                    FileOutputStream fos;
                    try {
                        fos = openFileOutput("things.txt", Context.MODE_APPEND);
                        fos.write(saveinfo.getBytes());
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft1714080902331Activity2.this, "记录成功", 0).show();
                    break;
                case R.id.button2:
                    String content = "";
                    try {
                        FileInputStream fis = openFileInput("things.txt");
                        byte[] buffer = new byte[fis.available()];
                        fis.read(buffer);
                        content = new String(buffer);
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft1714080902331Activity2.this, "记录的事情是：" + content, 0).show();
                    break;
                default:
                    break;
            }
        }
    }
}



