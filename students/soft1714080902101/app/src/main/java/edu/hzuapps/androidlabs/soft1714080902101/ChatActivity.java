package edu.hzuapps.androidlabs.soft1714080902101;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ChatActivity extends AppCompatActivity {

    private Button btn_send;
    private Button btn_show;
    private EditText et_info;
    private EditText et_info_up;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        et_info=(EditText) findViewById(R.id.et_info);
        et_info_up=(EditText) findViewById(R.id.et_info_up);
        btn_show=(Button) findViewById(R.id.btn_show);
        btn_send=(Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new ButtonListener());
        btn_show.setOnClickListener(new ButtonListener());
    }

    private class ButtonListener implements View.OnClickListener{
        public void onClick(View v){
            switch(v.getId()){
                case R.id.btn_send:

                    //存储
                    String sendinfo=et_info.getText().toString();
                    sendinfo='·'+sendinfo+'\n';
                    FileOutputStream fos;
                    try{
                        fos=openFileOutput("data.txt", Context.MODE_APPEND);
                        fos.write(sendinfo.getBytes());
                        fos.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(ChatActivity.this, "消息发送成功", Toast.LENGTH_SHORT).show();

                    //获取
                    String content="";
                    try{
                        FileInputStream fis=openFileInput("data.txt");
                        byte[] buffer=new byte[fis.available()];
                        fis.read(buffer);
                        content=new String(buffer);
                        fis.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    et_info_up.setText(String.valueOf(content));//显示在et_info_up中
                    break;
                case R.id.btn_show:
                    String content_two="";
                    try{
                        FileInputStream fis=openFileInput("data.txt");
                        byte[] buffer=new byte[fis.available()];
                        fis.read(buffer);
                        content_two=new String(buffer);
                        fis.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    et_info_up.setText(String.valueOf(content_two));//显示在et_info_up中
                    break;
                default:
                    break;
            }
        }
    }
}

