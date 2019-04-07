package edu.hzuapps.androidlabs.com1713071002108;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Com1713071002108Activity extends AppCompatActivity {

    private EditText et_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_1713071002108_activity);
        et_info = (EditText) findViewById(R.id.et_info);
        Button btn_read = (Button) findViewById(R.id.btn_read);
        Button btn_save = (Button) findViewById(R.id.btn_save);
        btn_read.setOnClickListener(new ButtonListener());
        btn_save.setOnClickListener(new ButtonListener());
    }

    private class ButtonListener implements View.OnClickListener {
        @SuppressLint({"WrongConstant", "ShowToast"})
        public void onClick(View v){
            switch (v.getId()){
                case R.id.btn_save:
                    String saveinfo=et_info.getText().toString().trim();
                    FileOutputStream fos;
                    try{
                        fos=openFileOutput("data.txt", Context.MODE_APPEND);
                        fos.write(saveinfo.getBytes());
                        fos.close();
                    }catch (Exception e){
                        e.printStackTrace();

                    }
                    Toast.makeText(Com1713071002108Activity.this,"日期保存成功",0).show();
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
                    Toast.makeText(Com1713071002108Activity.this,"查询日期的信息是："+content,0).show();
                    break;
                default:
                    break;
            }
    }

}

    }
