package xyz.hlm.lab_5;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class Soft1714080902317Activity extends AppCompatActivity implements View.OnClickListener{

    private static String[] PERMISSIONS_STORAGE = {
//            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft1714080902317_activity);
        permission();
        ((Button)findViewById(R.id.file)).setOnClickListener(this);
        ((Button)findViewById(R.id.sql)).setOnClickListener(this);
    }

    private void permission(){
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                Log.d("权限动态申请", "申请的权限为：" + permissions[i] + ",申请结果：" + grantResults[i]);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.file:
                try {
                    FileStorage fileStorage = new FileStorage("lab5","test.txt");
                    if (fileStorage.store("这是文件外部存储！"))
                        Toast.makeText(this,"文件存储成功！",Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.sql:
                SQLiteStorage sqLiteStorage = new SQLiteStorage(this,"Lab.db",null,1);
                SQLiteDatabase db = sqLiteStorage.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("id",1);
                values.put("content","这是数据库存储！");
                if (db.insert("lab5",null,values) != -1)
                    Toast.makeText(this,"数据库存储成功!",Toast.LENGTH_LONG).show();
                values.clear();
                break;
        }
    }
}
