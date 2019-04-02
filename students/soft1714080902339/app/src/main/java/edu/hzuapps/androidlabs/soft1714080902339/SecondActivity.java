package edu.hzuapps.androidlabs.soft1714080902339;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileOutputStream;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        Button button_0 = (Button) findViewById(R.id.button_0);
        Button button_1 = (Button) findViewById(R.id.button_1);
        Button button_2 = (Button) findViewById(R.id.button_2);
        Button button_3 = (Button) findViewById(R.id.button_3);
        Button button_4 = (Button) findViewById(R.id.button_4);
        Button button_write = (Button) findViewById(R.id.button_write);

        button_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://down.sparke.cn/search/1/1?content=f5a5403271b04b2690960a170c37b7d3&here=1"));
                startActivity(intent);
            }
        });
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://down.sparke.cn/search/1/1?content=30916acfac32458c9cfe7b5f8cd620bf&here=1"));
                startActivity(intent);
            }
        });
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://down.sparke.cn/search/1/1?content=faa2abd9bb854fdcacb2676d97bfff96&here=1"));
                startActivity(intent);
            }
        });
        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://down.sparke.cn/search/1/1?content=937c633592974ff39184cc626d729f2f&here=1"));
                startActivity(intent);
            }
        });
        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://down.sparke.cn/search/1/1?content=4029a6c2f62d478188070f5ce579fcbe&here=1"));
                startActivity(intent);
            }
        });
        button_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SecondActivity.this, WriteActivity.class);
                startActivity(i);
            }
        });


        //定义Button按钮的点击事件

    }
}
