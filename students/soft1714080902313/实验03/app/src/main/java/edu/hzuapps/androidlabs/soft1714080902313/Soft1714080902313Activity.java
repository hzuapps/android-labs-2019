package edu.hzuapps.androidlabs.soft1714080902313;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Soft1714080902313Activity extends AppCompatActivity {
    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902313);
        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到Soft1714080902313Activity2
                Intent intent = new Intent();
                intent.setClass(Soft1714080902313Activity.this, Soft1714080902313Activity2.class);
                startActivity(intent);
            }
        });

        button2=findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(Soft1714080902313Activity.this,Soft1714080902313Activity3.class);
                startActivity(intent);

            }
        });


    }
}
