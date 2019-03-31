package edu.hzuapps.androidlabs.soft1714080902319;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Soft1714080902319 extends AppCompatActivity {

    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft1714080902319);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(listener);
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(listener2);
    }

    Button.OnClickListener listener = new Button.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(Soft1714080902319.this, Soft1714080902319_2.class);
            startActivity(intent);
        }

    };
    Button.OnClickListener listener2 = new Button.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(Soft1714080902319.this, Soft1714080902319_3.class);
            startActivity(intent);
        }

    };
}

