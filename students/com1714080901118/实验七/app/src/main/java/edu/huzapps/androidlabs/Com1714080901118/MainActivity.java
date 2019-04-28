package com.example.aishop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
private EditText et_name;
private Button btn_send;
private Button p_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name=(EditText)findViewById(R.id.et_name);
        btn_send=(Button)findViewById(R.id.btn_send);
        p_btn=(Button)findViewById(R.id.p_btn);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passDate();
            }
        });
        p_btn .setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent2=new Intent(MainActivity.this,PersonalActivity.class);
                startActivity(intent2); //do something
            }
        });
    }
    private void passDate() {
        Intent intent=new Intent(this,ShowActivity.class);
        intent.putExtra("the moon and sixpence",et_name.getText().toString().trim());
        startActivity(intent);
    }
}
