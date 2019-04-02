package edu.hzuapps.androidlabs.soft1714080902207;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Soft_1714080902207_MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902207_activity_main);
        button3 = (Button) findViewById(R.id.button3);
        button2=findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Soft_1714080902207_MainActivity.this, Soft_1714080902207_SecondActivity.class);
                startActivity(intent);
            }

        });


    }
    private Button button3;


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button2:
                Toast.makeText(Soft_1714080902207_MainActivity.this,"本次消费记录成功",Toast.LENGTH_SHORT).show();
        }
    }
}
