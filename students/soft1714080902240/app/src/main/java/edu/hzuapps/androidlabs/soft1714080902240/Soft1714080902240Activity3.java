package edu.hzuapps.androidlabs.soft1714080902240;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Soft1714080902240Activity3 extends AppCompatActivity {
    private TextView edittext;
    public static final String DIRECTORY = "demo";
    public static final String FILENAME = "file_demo.txt";
    public static final String TAG = Soft1714080902240Activity3.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft17140809022403);

        boolean conn = ConnectionUtil.isConn(Soft1714080902240Activity3.this);
        //如果没有网络
        if (!conn) {
            //跳转到设置网络的界面
            ConnectionUtil.setNetworkMethod(Soft1714080902240Activity3.this);
        } else {
        }

        edittext = (TextView) findViewById(R.id.danci);
        Button button = (Button) findViewById(R.id.btn_save);
        button.setOnClickListener(new MyonclickListener());

    }




    private class MyonclickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String string = edittext.getText().toString();
            saveTextIntoInternalStorage(string);
        }
    }

    private void saveTextIntoInternalStorage(String text) {
        // 获取内部存储目录
        File dir = this.getFilesDir();
        if (dir.isDirectory()) {
//            dir.mkdir();
//            dir.mkdirs();
        }

            if (dir.isFile()) {
            // D:/Abc.txt , -> D:/Abc1.txt ->D:/abc.txt
        }

        File file = new File(dir, FILENAME);

        if (file.exists()) { // 判断文件是否存在
                Log.i(TAG, file.getAbsolutePath());
                Log.i(TAG, file.length() + ""); // bytes*1024=kb *1024 MB
                Log.i(TAG, file.isFile() + "");
                file.canRead();
                file.canWrite();
                file.canExecute();

                file.getFreeSpace();
                file.getTotalSpace();
        }

        FileOutputStream fos = null;  // 字节流  | char | cn : gbk 2 bytes, utf8 3 bytes

        try { // 使用API打开输出流
            fos = openFileOutput(FILENAME, MODE_PRIVATE);
            //FileOutputStream fos = new FileOutputStream(file);
            fos.write(text.getBytes()); // 写入内容
            fos.close(); // 关闭流
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Toast.makeText(Soft1714080902240Activity3.this,"保存成功", 0).show();
    }

}
