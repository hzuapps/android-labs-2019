package edu.hzuapps.androidlabs.com1714080901133;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Com1714080901133Activity extends Activity implements OnClickListener {
    private EditText et_info;
    private EditText et_info2;
    private Button btn_save;
    private Button btn_read;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_1714080901133_activity);
        et_info=(EditText) findViewById(R.id.et_info);
        et_info2=(EditText) findViewById(R.id.et_info2);
        btn_save=(Button) findViewById(R.id.btn_save);
        btn_read=(Button) findViewById(R.id.btn_read);
        btn_save.setOnClickListener(new ButtonListener());
        btn_read.setOnClickListener(new ButtonListener());
    }
    private class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btn_save:
                    String saveinfo=et_info.getText().toString().trim();
                    String saveinfo2=et_info2.getText().toString().trim();
                    FileOutputStream fos;
                    try{
                        fos=openFileOutput("data.txt", Context.MODE_APPEND);
                        fos.write(saveinfo.getBytes());
                        fos.write(saveinfo2.getBytes());
                        fos.close();
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(Com1714080901133Activity.this, "保存成功", 1).show();
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
                    Toast.makeText(Com1714080901133Activity.this, "保存数据是" + content, 1)
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

    }
}