package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView spsl=findViewById(R.id.spsl);
                TextView sl=findViewById(R.id.sl);
                TextView spjg=findViewById(R.id.spjg);
                TextView jg=findViewById(R.id.jg);
                TextView spdd=findViewById(R.id.spdd);
                TextView ddh=findViewById(R.id.ddh);
                String text=spsl.getText().toString()+sl.getText().toString()+"\n"+spjg.getText().toString()+jg.getText().toString()+"\n"+spdd.getText().toString()+ddh.getText().toString();
               // Log.i("1",spsl.getText().toString());
                //save(text);
                saveTextIntoExternalStorage(text);

            }
        });
    }
    private void save(String text){
        // 获取内部存储目录
        File dir = this.getFilesDir();
        //File dir = getCacheDir();
        File file = new File(dir,"test.txt");
        if (file.exists()) { // 判断文件是否存在
            Log.i("TAG", file.getAbsolutePath());
            Log.i("TAG", file.length() + ""); // bytes*1024=kb *1024 MB
            Log.i("TAG", file.isFile() + "");
            file.canRead();
            file.canWrite();
            file.canExecute();

            file.getFreeSpace();
            file.getTotalSpace();
        }

        FileOutputStream fos = null;  // 字节流  | char | cn : gbk 2 bytes, utf8 3 bytes

        try { // 使用API打开输出流
            fos = openFileOutput("test.txt", MODE_PRIVATE);
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
            String line;
            BufferedReader bReader = new BufferedReader(reader);
            while ((line =bReader.readLine())!=null){
                Log.i("读取","从文件读取的内容: " + line);
            }
            bReader.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 显示结果
        showResult(file.getAbsolutePath());

        // 删除文件
        file.delete();
        deleteFile("test.txt");
    }

    // 将文字保存到外部存储
    private void saveTextIntoExternalStorage(String text) {
        if (!isExternalStorageWritable()) {
            Log.e("外部存储", "外部存储不可写!");
            return;
        }

        File dir = getPublicExtStorageDir("DIRECTORY", Environment.DIRECTORY_DOWNLOADS);
        File file = new File(dir, "test01.txt");

        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(text.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileReader reader = null;
        try {
            reader = new FileReader(file.getAbsoluteFile());
            String line;
            BufferedReader bReader = new BufferedReader(reader);
            while ((line =bReader.readLine())!=null){
                Log.i("读取","从文件读取的内容: " + line);
            }
            bReader.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 显示结果
        showResult(file.getAbsolutePath());
    }

    /* Checks if external storage is available for read and write */
    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    private boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    // 创建公开的外部存储目录（App卸载时不会删除）
    private File getPublicExtStorageDir(String dirName, String type) {
        if (type == null) { // 指定文件类型
            type = Environment.DIRECTORY_PICTURES;
        }
        File dir = new File(Environment.getExternalStoragePublicDirectory(type), dirName);
        if (!dir.mkdirs()) {
            Log.e("无法创建", "目录无法创建!");
        }

        long freeSpace = dir.getFreeSpace();
        Log.i("剩余空间", "剩余空间大小: " + (freeSpace / 1024 / 1024) + "MB");
        long totalSpace = dir.getTotalSpace();
        Log.i("总空间", "总空间大小: " + (totalSpace / 1024 / 1024) + "MB");

        return dir;
    }

    // 创建私有的外部存储目录（App卸载时会一同删除）
    private File getPrivateExtStorageDir(Context context, String dirName, String type) {
        if (type == null) { // 指定文件类型
            type = Environment.DIRECTORY_PICTURES;
        }
        File file = new File(context //
                .getExternalFilesDir(type), dirName);
        if (!file.mkdirs()) {
            Log.e("无法创建", "目录无法创建!");
        }
        return file;
    }


    private void showResult(String result) {
        ((TextView) findViewById(R.id.text_path)) //
                .setText(result.toCharArray(), 0, result.length());
    }
}
