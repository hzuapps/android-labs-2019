package edu.hzuapps.androidlabs.soft1714080902120;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Soft1714080902120SuggestionActivity extends AppCompatActivity {
//内部存储读取
    private EditText text_add;
    private Button btn_save;
    private Button btn_read;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
        //获取布局文件中的控件
        text_add= (EditText)findViewById(R.id.text_add);
        btn_save=(Button) findViewById(R.id.btn_save);
        btn_read=(Button) findViewById(R.id.btn_read);
        btn_save.setOnClickListener((View.OnClickListener) new ButtonListener());
        btn_read.setOnClickListener((View.OnClickListener) new ButtonListener());


    }


    //定义Button的时间按钮点击事件
    private class ButtonListener implements View.OnClickListener {
        public void onClick(View v){
            switch(v.getId()){

                //保存数据
                case R.id.btn_save:
                    String saveinfo=text_add.getText().toString().trim();
                    FileOutputStream fos;
                    try{
                        fos=openFileOutput("suggestion.txt", Context.MODE_APPEND);
                        fos.write(saveinfo.getBytes());
                        fos.close();

                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft1714080902120SuggestionActivity.this,"保存成功！",1000).show();
                    break;

                //获取保存的数据
                case R.id.btn_read:
                    String content="";
                    try{
                        FileInputStream fis=openFileInput("suggestion.txt");
                        byte[] buffer=new byte[fis.available()];
                        fis.read(buffer);
                        content=new String(buffer);
                        fis.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft1714080902120SuggestionActivity.this, "您的评论为："+content,1000).show();

                    break;
                default:
                    break;
            }
        }





    }
}
