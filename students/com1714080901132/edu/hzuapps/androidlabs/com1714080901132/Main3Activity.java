package edu.hzuapps.androidlabs.com1714080901132;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.myapplication.R;
import java.io.FileInputStream;
import java.io.FileOutputStream;
public class Main3Activity extends AppCompatActivity {
    private EditText et_info;
    private Button btn_save;
    private Button btn_read;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        et_info=(EditText)findViewById(R.id.et_info);
        btn_save=(Button)findViewById(R.id.btn_save);
        btn_read=(Button)findViewById(R.id.btn_read);
        btn_save.setOnClickListener(new ButtonListener( ));
        btn_read.setOnClickListener(new ButtonListener( ));
    }
    private class ButtonListener implements View.OnClickListener {
        public void onClick(View v){
            switch (v.getId()){
                case R.id.btn_save:
                    String saveinfo=et_info.getText().toString().trim();
                    FileOutputStream fos;
                    try{
                        fos=openFileOutput("data.txt", Context.MODE_PRIVATE);
                        fos.write(saveinfo.getBytes());
                        fos.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(Main3Activity.this, "数据保存成功", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_read:
                    String content="";
                    try{
                        FileInputStream fis=openFileInput("data.txt");
                        byte[] buffer=new byte[fis.available()];
                        fis.read(buffer);
                        content=new String(buffer);
                        fis.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(Main3Activity.this, "上次搜索记录是" + content, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }
}
