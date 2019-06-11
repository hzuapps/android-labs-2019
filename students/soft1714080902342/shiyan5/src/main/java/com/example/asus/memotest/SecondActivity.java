package com.example.asus.memotest;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SecondActivity extends AppCompatActivity {
    private EditText editText;
    private Button btn_save;
    private Button btn_read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shiyan5_layout);
        editText = findViewById(R.id.edit_text);
        btn_save = findViewById(R.id.btn_save);
        btn_read = findViewById(R.id.btn_read);
        btn_save.setOnClickListener(new ButtonListener());
        btn_read.setOnClickListener(new ButtonListener());
        isGrantExternalRW(this);
    }

    FileStorage fileStorage ;
    //定义Button按钮的点击事件
    private class ButtonListener implements View.OnClickListener {
        public void onClick(View v)
        {
            try {
                fileStorage   = new FileStorage("czl/lab5","test.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            switch (v.getId()) {

                case R.id.btn_save:

                    String saveinfo = editText.getText().toString().trim();
                    try {

                        fileStorage.store(saveinfo);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(SecondActivity.this,"保存数据成功\n",Toast.LENGTH_SHORT).show();
                                       break;
                case R.id.btn_read:
                    String content = "";
                        content = fileStorage.get();
                    Toast.makeText(SecondActivity.this,"保存的数据是"+content,Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;


            }
        }

    }

    public static boolean isGrantExternalRW(Activity activity)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity.checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {

            activity.requestPermissions(new String[]
                    {
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, 1);

            return false;
        }

        return true;
    }



}

