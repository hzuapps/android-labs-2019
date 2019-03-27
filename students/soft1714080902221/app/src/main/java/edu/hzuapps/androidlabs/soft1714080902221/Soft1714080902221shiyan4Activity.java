package edu.hzuapps.androidlabs.soft1714080902221;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.hzuapps.androidlabs.shiyan4.R;

public class Soft1714080902221shiyan4Activity extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContentView(R.layout.shiyan4activity_soft1714080902221);

        textView=(TextView)findViewById(R.id.chengshi_jpg);

        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Soft1714080902221shiyan4Activity.this, jiaofei.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Soft1714080902221shiyan4Activity.this, chakanyue.class);
                startActivity(intent);
            }
        });

    }
}