package edu.hzuapps.androidlabs.Soft1714080902215;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class pic extends AppCompatActivity {
    private Button button2,button3,button4,pic_comment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);

        button2 = (Button) findViewById(R.id.button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(edu.hzuapps.androidlabs.Soft1714080902215.pic.this, Soft1714080902215Activity.class);
                startActivity(intent);
            }
        });
        //获取布局文件中的控件
        button3 = (Button) findViewById(R.id.button03);
        //定义Button按钮的点击时事件
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(edu.hzuapps.androidlabs.Soft1714080902215.pic.this, Questionnaire.class);
                startActivity(intent);
                if (isNetWorkContent(pic.this) == true) {
                    Toast.makeText(pic.this, "当前网络通畅", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(pic.this, "当前无网络连接", Toast.LENGTH_LONG).show();
                }
            }
        });

        button4 = (Button) findViewById(R.id.button04);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(edu.hzuapps.androidlabs.Soft1714080902215.pic.this, Post.class);
                startActivity(intent);

                if (isNetWorkContent(pic.this) == true) {
                    Toast.makeText(pic.this, "当前网络通畅", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(pic.this, "当前无网络连接", Toast.LENGTH_LONG).show();
                }
            }
        });

        pic_comment = (Button) findViewById(R.id.pic_comment);
        pic_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(edu.hzuapps.androidlabs.Soft1714080902215.pic.this, Pic_comment.class);
                startActivity(intent);

                if (isNetWorkContent(pic.this) == true) {
                    Toast.makeText(pic.this, "当前网络通畅", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(pic.this, "当前无网络连接", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public boolean isNetWorkContent(Context context) {
        if (context != null) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info != null) {
                return info.isAvailable();
            }
        }
        return false;
    }
}
