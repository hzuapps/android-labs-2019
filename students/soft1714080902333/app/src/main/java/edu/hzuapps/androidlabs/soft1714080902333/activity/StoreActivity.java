package edu.hzuapps.androidlabs.soft1714080902333.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import edu.hzuapps.androidlabs.soft1714080902333.R;

public class StoreActivity extends AppCompatActivity {
    private EditText ip_info;
    private Button but_rd;
    private Button but_wr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        // 获取Layout控件
        ip_info = (EditText) findViewById(R.id.ip_info);
        but_rd = (Button) findViewById(R.id.but_rd);
        but_wr = (Button) findViewById(R.id.but_wr);

        but_wr.setOnClickListener(new ButtonListener());
        but_rd.setOnClickListener(new ButtonListener());
    }

    // Button点击事件
    private class ButtonListener implements View.OnClickListener {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.but_wr:
                    String message_wr = "\n";
                    message_wr = ip_info.getText().toString().trim();
                    FileOutputStream fileOutputStream;
                    try {
                        fileOutputStream = openFileOutput("cache.txt", Context.MODE_APPEND);
                        fileOutputStream.write(message_wr.getBytes());
                        fileOutputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(StoreActivity.this, "数据保存成功", Toast.LENGTH_SHORT)
                            .show();
                    break;
                case R.id.but_rd:
                    String message_rd = "";
                    try {
                        FileInputStream fileInputStream = openFileInput("cache.txt");
                        byte[] buffer = new byte[fileInputStream.available()];
                        fileInputStream.read(buffer);
                        message_rd = new String(buffer);
                        fileInputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(StoreActivity.this, "保存的数据是：" + message_rd,
                            Toast.LENGTH_SHORT).show();
                    break;
                    default:
                        break;
            }
        }
    }
}
