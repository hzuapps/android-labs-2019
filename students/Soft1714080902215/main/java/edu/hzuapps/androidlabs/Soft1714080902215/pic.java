package edu.hzuapps.androidlabs.Soft1714080902215;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class pic extends AppCompatActivity {
    private Button button2,button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);

        button2 = (Button) findViewById(R.id.button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(edu.hzuapps.androidlabs.Soft1714080902215.pic.this, Soft1714080902215Activity.class);
                startActivity(intent);
            }
        });
        button3 = (Button) findViewById(R.id.button03);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(edu.hzuapps.androidlabs.Soft1714080902215.pic.this, Questionnaire.class);
                startActivity(intent);
            }
        });
    }
}
