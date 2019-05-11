package edu.hzuapps.androidlabs.com1714080901133;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
public class Com1714080901133Activity3 extends AppCompatActivity {
    //显示数据
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_17140809011333_activity3);

        Intent intent=getIntent();
        TextView tv=findViewById(R.id.tv);
        tv.setText(intent.getStringExtra("data"));
    }
}
