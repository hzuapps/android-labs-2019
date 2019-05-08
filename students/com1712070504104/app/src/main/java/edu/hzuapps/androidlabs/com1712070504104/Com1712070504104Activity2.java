package edu.hzuapps.androidlabs.com1712070504104;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Com1712070504104Activity2<b> extends AppCompatActivity {
    private EditText et_info;
    private Button btn_save;
    private Button btn_read;
    private Button btn_lable;
    private Button btn_lablemessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_1712070504104_activity2);
        et_info = (EditText) findViewById(R.id.et_info);
        btn_read = (Button) findViewById(R.id.btn_read);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_read.setOnClickListener(new ButtonListener());
        btn_save.setOnClickListener(new ButtonListener());
        btn_lable = (Button) findViewById(R.id.btn_lable);
        btn_lable.setOnClickListener(new ButtonListener());
        btn_lablemessage = (Button) findViewById(R.id.btn_lablemessage);
        btn_lablemessage.setOnClickListener(new ButtonListener()
        {@Override
        public void onClick(View v) {
            {
                Intent intent2= new Intent(Com1712070504104Activity2.this,Com1712070504104Activity3.class);
                startActivity(intent2);
            }
        }

        });


    }
   private class ButtonListener implements View.OnClickListener {
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
                    Toast.makeText(Com1712070504104Activity2.this,"生日信息保存成功",0).show();
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
                    Toast.makeText(Com1712070504104Activity2.this,"保存的生日信息是："+content,0).show();
                    break;
                case R.id.btn_lable:
                    String lable="";
                    try{
                        FileInputStream fis=openFileInput("data.txt");
                        byte[] buffer=new byte[fis.available()];
                        fis.read(buffer);
                        lable=new String(buffer);
                        fis.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(Com1712070504104Activity2.this,"星标的生日信息是："+lable,0).show();
                    break;
                default:
                    break;

            }
        }
    }


}


