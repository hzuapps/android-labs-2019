package edu.hzuapps.androidlabs.soft1714080902128;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SecondActivity extends AppCompatActivity {
    private ImageView beijin;
    private Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        ((ImageButton)findViewById(R.id.tp01_01)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondActivity.this,ThirdActivity.class);
                intent.putExtra("name","maomia");
                washow("maomia");
                startActivity(intent);
            }
        });
        ((ImageButton)findViewById(R.id.tp01_02)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondActivity.this,ThirdActivity.class);
                intent.putExtra("name","maomib");
                washow("maomib");
                startActivity(intent);
            }
        });
        ((ImageButton)findViewById(R.id.tp02_01)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondActivity.this,ThirdActivity.class);
                intent.putExtra("name","maomic");
                washow("maomic");
                startActivity(intent);
            }
        });
        beijin=findViewById(R.id.tp02_02);
        ((Button)findViewById(R.id.button_xiazai01)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=((EditText)findViewById(R.id.edit_wangzhi)).getText().toString();
                new HttpThread(s,beijin,handler).start();
            }
        });
        ((Button)findViewById(R.id.button_find)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editText_name=((EditText)findViewById(R.id.edit_name)).getText().toString();
                R.drawable drawables=new R.drawable();
                //默认的id
                Boolean open=true;//定义判断是否搜索成功
                int resId=0x7f02000b;
                try {
                    //根据字符串字段名，取字段//根据资源的ID的变量名获得Field的对象,使用反射机制来实现的
                    java.lang.reflect.Field field=R.drawable.class.getField(editText_name);
                    //取值
                    resId=(Integer)field.get(drawables);
                } catch (Exception e) {
                    //百度的
                    // TODO Auto-generated catch block
                    //e.printStackTrace();
                    Toast.makeText(SecondActivity.this,"没有该名称的图片",Toast.LENGTH_SHORT).show();//搜索失败提示
                    open=false;//不跳转窗体
                }
                if(open){
                    Intent intent=new Intent(SecondActivity.this,ThirdActivity.class);
                    intent.putExtra("name",editText_name);
                    washow(editText_name);
                    startActivity(intent);
                }
            }
        });
        initHistoricalRecord();
    }

    private  void washow(String s) {
        write(s);
        refreshHistoricalRecord(s);
    }
    private void refreshHistoricalRecord(String msg)  //刷新一下记录
    {
        if(msg==null) return;
        TextView textView = (TextView) findViewById(R.id.textview_history);
        String s = textView.getText().toString();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String formattedDate=df.format(c.getTime());
        textView.setText(s+"时间："+formattedDate+" 查看 "+msg+"\n");
    }
    private void initHistoricalRecord()  //初始化记录
    {
        TextView textView = (TextView) findViewById(R.id.textview_history);
        String s = read();
        if(s!=null)
            refreshHistoricalRecord(s);
    }

    public String read() {   //读入存储数据
        try {
            FileInputStream inStream = this.openFileInput("message.txt");
            byte[] buffer = new byte[1024];
            int hasRead = 0;
            StringBuilder sb = new StringBuilder();
            while ((hasRead = inStream.read(buffer)) != -1) {
                sb.append(new String(buffer, 0, hasRead));
            }

            inStream.close();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void write(String msg){   //写出存储数据
        // 步骤1：获取输入值
        if(msg == null) return;
        try {
            // 步骤2:创建一个FileOutputStream对象,MODE_APPEND追加模式
            msg += read();  //前面的也读进来
            FileOutputStream fos = openFileOutput("storage.txt", MODE_APPEND);
            // 步骤3：将获取过来的值放入文件
            fos.write(msg.getBytes());
            // 步骤4：关闭数据流
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}