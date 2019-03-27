package com.example.aishop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
private EditText et_name;
private Button btn_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name=(EditText)findViewById(R.id.et_name);
        btn_send=(Button)findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passDate();
            }
        });
    }

    private void passDate() {
        Intent intent=new Intent(this,ShowActivity.class);
        intent.putExtra("the moon and sixpence",et_name.getText().toString().trim());
        startActivity(intent);
    }
}
