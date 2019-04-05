package com.example.menu;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import static android.provider.Telephony.Mms.Part.FILENAME;

public class CPSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpselect);
        saveTextIntoInternalStorage("存储读取成功");
    }

    private void saveTextIntoInternalStorage(String text) {
        // 获取内部存储目录
        File dir = this.getFilesDir();
        //File dir = getCacheDir();

        if (dir.isDirectory()) {
//            dir.mkdir();
//            dir.mkdirs();
        }

        if (dir.isFile()) {
            // D:/Abc.txt , -> D:/Abc1.txt ->D:/abc.txt
        }

        File file = new File(dir, FILENAME);

//        try {
//            File = File.createTempFile(FILENAME, null, dir);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        if (file.exists()) { // 判断文件是否存在
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

        FileReader reader = null; // char

        try {
            reader = new FileReader(file.getAbsoluteFile());
            BufferedReader bReader = new BufferedReader(reader);
            String line = bReader.readLine();
            bReader.close();
            reader.close();
            showResult(line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 显示结果

        // 删除文件
        // file.delete();
        // deleteFile(FILENAME);
    }

    private void showResult(String result) {
        ((TextView) findViewById(R.id.cp_caiming_text)) //
                .setText(result.toCharArray(), 0, result.length());
    }
}
