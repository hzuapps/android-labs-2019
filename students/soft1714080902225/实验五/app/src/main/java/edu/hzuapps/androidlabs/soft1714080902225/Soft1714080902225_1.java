package edu.hzuapps.androidlabs.soft1714080902225;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Soft1714080902225_1<view> extends AppCompatActivity {
    Button love;
    Button detail;
    private EditText et_info;
    private Button btn_save;
    private Button btn_read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902225_1_activity);

        et_info = (EditText) findViewById(R.id.et_info);
        Button btn_save = (Button) findViewById(R.id.btn_save);
        btn_read = (Button) findViewById(R.id.btn_read);
        btn_save.setOnClickListener((OnClickListener) new ButtonListener());
        btn_read.setOnClickListener((OnClickListener) new ButtonListener());

        detail = (Button) findViewById(R.id.detail);
        detail.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Soft1714080902225_1.this, Soft1714080902225_2.class);
                startActivity(intent);
            }
        });
        View button = findViewById(R.id.love);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Soft1714080902225_1.this, Soft1714080902225_3.class);
                startActivity(intent);
            }
        });
    }

    public Button getbtn_save() {
        return btn_save;
    }

    public void setBtn_save(Button btn_save) {
        this.btn_save = btn_save;
    }

    private class ButtonListener implements OnClickListener {
        private view v;

        @SuppressLint("WrongConstant")
        public void onclick(View view) {
            switch (view.getId()) {
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

                    Toast.makeText(Soft1714080902225_1.this, "数据保存成功", 0).show();

                    break;
                case R.id.btn_read:
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
                    Toast.makeText(Soft1714080902225_1.this, "保存的数据是：" + content, 0).show();
                    break;
                default:
                    break;
            }
        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {

        }
    }
}
