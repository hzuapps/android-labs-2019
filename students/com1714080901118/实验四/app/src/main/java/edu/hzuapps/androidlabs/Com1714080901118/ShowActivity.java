package com.example.aishop;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import org.w3c.dom.Text;

public class ShowActivity extends AppCompatActivity {

    private TextView tv_name;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Intent intent=getIntent();
        String name=intent.getStringExtra("the moon and sixpence");
       tv_name=(TextView)findViewById(R.id.tv_name);
        tv_name.setText("bookname："+name);
        textView=(TextView)findViewById(R.id.sp);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShowActivity.this,DetailActivity.class);
                startActivity(intent);
            }
        });
    }

}
