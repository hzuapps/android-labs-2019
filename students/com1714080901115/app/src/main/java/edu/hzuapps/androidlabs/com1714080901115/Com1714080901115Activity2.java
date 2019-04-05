package edu.hzuapps.androidlabs.com1714080901115;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class Com1714080901115Activity2 extends AppCompatActivity {
    private EditText edit;
    private Button button;
    private Button buttonx;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_1714080901115_activity2);
        edit = (EditText) findViewById(R.id.edit_text);
        button = (Button) findViewById(R.id.button);
        buttonx = (Button) findViewById(R.id.buttonx);
        button.setOnClickListener(new ButtonListener());
        buttonx.setOnClickListener(new ButtonListener());
    }
    private class ButtonListener implements View.OnClickListener {
        @SuppressLint("WrongConstant")
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button:
                    String saveinfo = edit.getText().toString().trim();
                    FileOutputStream fos;
                    try {
                        fos = openFileOutput("data.txt", Context.MODE_APPEND);
                        fos.write(saveinfo.getBytes());
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Com1714080901115Activity2.this, "歌曲保存成功", 0).show();
                    break;
                case R.id.buttonx:
                    String content = "";
                    try {
                        FileInputStream fis = openFileInput("data.txt");
                        byte[] buffer = new byte[fis.available()];
                        fis.read(buffer);
                        content = new String(buffer);
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Com1714080901115Activity2.this, "喜欢的歌曲为：" + content, 0).show();


            }
        }

    };

}