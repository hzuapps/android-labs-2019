package edu.hzuapps.androidlabs.com1714080901129;

import android.content.Context;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Lab5Activity extends AppCompatActivity {
    private EditText editText;
    private Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab5);

        editText=(EditText)findViewById(R.id.edit_text);
        bt=(Button)findViewById(R.id.bt_save);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data=editText.getText().toString();
                save(data);
            }
        });
    }
    private void save(String save){
        File sdCard=Environment.getExternalStorageDirectory();
        String FILE_NAME="mytext.txt";
        File file=new File(sdCard,FILE_NAME);
        if(!sdCard.exists()){
            Toast.makeText(getApplicationContext(), "当前系统不具备SD卡目录", Toast.LENGTH_LONG).show();
        }
        try {
            verifyStoragePermissions(this);
            file.createNewFile();
            Toast.makeText(getApplicationContext(), "文件已经创建完成", Toast.LENGTH_LONG).show();
            FileOutputStream fos=new FileOutputStream(file);
            OutputStreamWriter osw=new OutputStreamWriter(fos);
            osw.write(save);
            osw.flush();
            osw.close();
            fos.close();
            Toast.makeText(getApplicationContext(), "文件已经写入完成", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(file.getAbsolutePath());
    }
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };


    public static void verifyStoragePermissions(Activity activity) {

        try {
        //检测是否有写的权限
            int permission = android.support.v4.app.ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != android.content.pm.PackageManager.PERMISSION_GRANTED) {
        // 没有写的权限，去申请写的权限，会弹出对话框
                android.support.v4.app.ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
