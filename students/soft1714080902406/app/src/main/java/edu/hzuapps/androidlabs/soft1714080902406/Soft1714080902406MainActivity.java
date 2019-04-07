package edu.hzuapps.androidlabs.soft1714080902406;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Soft1714080902406MainActivity extends AppCompatActivity {

    final String space = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902406_activity_main);

        ImageView imageView =(ImageView) findViewById(R.id.imageView2);

        View.OnClickListener onClickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(Soft1714080902406MainActivity.this,ResultActivity.class);
                startActivity(intent);
            }
        };

        imageView.setOnClickListener(onClickListener);

        //创建储存显示视图
        initSaveOnputView();



    }

    private void initSaveOnputView()
    {
        View recordsHistoryView = LayoutInflater.from(this).inflate(R.layout.soft_1714080902406_activity_main, null);


        //存储数据
        ImageView imageView =(ImageView) findViewById(R.id.imageView2);
        final EditText editText7  = findViewById(R.id.editText7);
        View.OnClickListener onClickListenerSave = new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                if(v.getId() == R.id.imageView2)
                {
                    String saveInfo=editText7.getText().toString().trim();
                    saveInfo = saveInfo + space;
                    FileOutputStream fos;
                    try
                    {
                        fos=openFileOutput("hospitalDataA.txt",MODE_APPEND);//把文件输出到data中
                        fos.write(saveInfo.getBytes());//将我们写入的字符串变成字符数组）
                        fos.close();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft1714080902406MainActivity.this,"搜索成功",Toast.LENGTH_SHORT).show();
                }

            }
        };
        imageView.setOnClickListener(onClickListenerSave);



        //显示历史记录

        View.OnClickListener onClickListenerOnput = new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                if(v.getId() == R.id.editText7)
                {
                    String content="";
                    try {
                        FileInputStream fis=openFileInput("hospitalDataA.txt");
                        byte [] buffer=new  byte[fis.available()];
                        fis.read(buffer);
                        content=new String(buffer);
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    int k = 0;
                    String data = null;
                    for (int i = 0, j = 0; i < content.length(); i++)
                    {
                        if(content.charAt(i) == ' ' && k < 5)
                        {
                            data = content.substring(j,i);
                        }

                    }

                    editText7.setText(data);
                    Toast.makeText(Soft1714080902406MainActivity.this,"历史显示成功",Toast.LENGTH_SHORT).show();

                }

            }
        };

       editText7.setOnClickListener(onClickListenerOnput);
    }
}
