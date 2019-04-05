package edu.hzuapps.androidlabs.soft1714080902436;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;


public class Soft1714080902436Activity extends AppCompatActivity {

    private  EditText edit_message1,edit_message2,edit_message3,edit_message4;
    private Button savebutton;
    private Button readbutton;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902436);
        edit_message1=(EditText) findViewById(R.id.edit_message1);
        edit_message2=(EditText) findViewById(R.id.edit_message2);
        edit_message3=(EditText) findViewById(R.id.edit_message3);
        edit_message4=(EditText) findViewById(R.id.edit_message4);
        savebutton=(Button) findViewById(R.id.savebutton);
        readbutton=(Button) findViewById(R.id.readbutton);
        savebutton.setOnClickListener(new ButtonListener());
        readbutton.setOnClickListener(new ButtonListener());
    }
    //定义Button按钮的点击事件
    private  class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.savebutton:
                    String  saveinfo=edit_message1.getText().toString().trim()
                            +" 性别"+edit_message2.getText().toString().trim()
                            +" "+edit_message3.getText().toString().trim()
                            +"岁 居住地："+edit_message4.getText().toString().trim();
                    FileOutputStream fos;
                    try{
                        //保存数据
                        fos=openFileOutput("data.txt",Context.MODE_APPEND);
                        fos.write(saveinfo.getBytes());
                        fos.close();
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft1714080902436Activity.this,"数据保存成功！",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.readbutton:
                    String content="";
                    try{
                        //获取保存信息
                        FileInputStream fis=openFileInput("data.txt");
                        byte[] buffer=new byte[fis.available()];
                        fis.read(buffer);
                        content=new String(buffer);
                        fis.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft1714080902436Activity.this,"个人资料："+content,Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }

}
