package edu.hzuapps.androidlabs.diary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private EditText et_coast;
    private Button btn_save;
    private Button btn_look;
    private Button btn_detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_coast);
        //从布局文件中获取数据
        et_coast=findViewById(R.id.et_coast);
        btn_save=findViewById(R.id.btn_save);
        btn_look=findViewById(R.id.btn_look);
        btn_detail=findViewById(R.id.btn_detail);
        //设计响应事件
        btn_save.setOnClickListener(new ButtonListener());
        btn_look.setOnClickListener(new ButtonListener());
        btn_detail.setOnClickListener(new ButtonListener());
    }
    //定义button按钮的点击事件
    private class ButtonListener implements View.OnClickListener {
       public void onClick(View  v){
             switch (v.getId()){
                 case R.id.btn_save:
                     String saveinfo=et_coast.getText().toString().trim();
                     FileOutputStream fos;
                     try{
                         //保存数据
                         fos=openFileOutput("data.txt", Context.MODE_PRIVATE);
                         fos.write(saveinfo.getBytes());
                         fos.close();
                     }catch (Exception e){
                         e.printStackTrace();
                     }
                     Toast.makeText(MainActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                     break;

                 case R.id.btn_look:
                     String content="";
                     try {
                         FileInputStream inputStream = openFileInput("data.txt");
                         byte[] buffer = new byte[inputStream.available()];
                         inputStream.read(buffer);
                         content = new String(buffer);
                         inputStream.close();
                     }
                     catch (Exception e) {
                         e.printStackTrace();
                     }
                     Toast.makeText(MainActivity.this,"今天支出（元）："+content,Toast.LENGTH_SHORT).show();
                     break;
                 case R.id.btn_detail:
                     Intent intent=new Intent(MainActivity.this,InternetActivity.class);
                     startActivity(intent);
                     break;
                 default:
                     break;
             }
       }
    }
}
