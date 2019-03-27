package edu.hzuapps.androidlabs.soft1714080902420;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Soft1714080902420Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902420_activity);
        Button bt1 = (Button) findViewById(R.id.button1);
        Button bt2=(Button)findViewById(R.id.button2);
        bt1.setOnClickListener(listener);
        bt2.setOnClickListener(listener2);
    }

    Button.OnClickListener listener = new Button.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(Soft1714080902420Activity.this, Soft1714080902420SourceActivity.class);
            startActivity(intent);
            Soft1714080902420Activity.this.finish();
        }
    };
    Button.OnClickListener listener2 = new Button.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(Soft1714080902420Activity.this, Soft1714080902420SearceActivity.class);
            startActivity(intent);
            Soft1714080902420Activity.this.finish();
        }
    };


}