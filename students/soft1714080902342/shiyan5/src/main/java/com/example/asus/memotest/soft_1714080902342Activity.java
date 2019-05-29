package com.example.asus.memotest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class soft_1714080902342Activity extends AppCompatActivity {
private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902342_activity);
        Button button1=findViewById(R.id.button_1);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast.makeText(soft_1714080902342Activity.this,"用心记录生活",Toast.LENGTH_SHORT).show();

               Intent intent=new Intent(soft_1714080902342Activity.this,SecondActivity.class);
                startActivity(intent);
            }
        });

    }
}
