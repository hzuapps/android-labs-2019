package edu.hzuapps.androidlabs.soft1714080902229;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
public class Main2Activity extends AppCompatActivity {

    private Button button1,button2,button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button1 = (Button) findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(edu.hzuapps.androidlabs.soft1714080902229.Main2Activity.this, Soft1714080902229Activity.class);
                startActivity(intent);

            }
        });
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent();
                intent2.setClass(edu.hzuapps.androidlabs.soft1714080902229.Main2Activity.this, Main3Activity.class);
                startActivity(intent2);
            }
        });
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent();
                intent4.setClass(edu.hzuapps.androidlabs.soft1714080902229.Main2Activity.this, Main4Activity.class);
                startActivity(intent4);
            }
        });
    }

    }

