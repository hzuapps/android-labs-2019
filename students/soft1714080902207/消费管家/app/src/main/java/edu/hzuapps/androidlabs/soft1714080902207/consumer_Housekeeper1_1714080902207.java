package edu.hzuapps.androidlabs.soft1714080902207;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class consumer_Housekeeper1_1714080902207 extends AppCompatActivity implements View.OnClickListener{

Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer__housekeeper1_1714080902207);
        button3 = (Button) findViewById(R.id.button3);
        button2=findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(consumer_Housekeeper1_1714080902207.this, consumer_Housekeeper_1714080902207.class);
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
                Toast.makeText(consumer_Housekeeper1_1714080902207.this,"本次消费记录成功",Toast.LENGTH_SHORT).show();
        }
    }
}



