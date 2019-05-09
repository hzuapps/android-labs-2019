package edu.hzuapps.androidlabs.com1714080901121signinapp;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Com1714080901121FirstActivity extends Activity implements OnClickListener {
    private EditText et_info;
    private Button btn_save;
    private Button btn_read;

    @SuppressLint("CutPasteId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com1714080901121homepage);
        et_info = (EditText) findViewById(R.id.et_info);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_read = (Button) findViewById(R.id.btn_read);
        btn_save.setOnClickListener(new ButtonListener());
        btn_read.setOnClickListener(new ButtonListener());
    }

    // ����Button��ť�ĵ���¼�
    private class ButtonListener implements OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_save:
                    String saveinfo = et_info.getText().toString().trim();
                    FileOutputStream fos;
                    try {
                        fos = openFileOutput("data.txt", Context.MODE_APPEND);
                        fos.write(saveinfo.getBytes());
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Com1714080901121FirstActivity.this, "数据保存成功", 0).show();
                    break;
                case R.id.btn_read:
                    String content = "";
                    try {
                        FileInputStream fis = openFileInput("data.txt");
                        byte[] buffer = new byte[fis.available()];
                        fis.read(buffer);
                        content = new String(buffer);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Com1714080901121FirstActivity.this, "保存的数据是" + content, 0)
                            .show();
                    break;
                default:
                    break;
            }
        }
    }

    public void read(View view) {

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

    }
}