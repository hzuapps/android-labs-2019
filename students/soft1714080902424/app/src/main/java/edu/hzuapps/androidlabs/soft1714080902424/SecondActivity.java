package edu.hzuapps.androidlabs.soft1714080902424;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    private Button button1,button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        button1 = findViewById(R.id.button_return);
        button2 = findViewById(R.id.button_next);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, Soft1714080902424Activity.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, NameActivity.class);
                startActivity(intent);
            }
        });

        if (!ConnectionNetwork.isConn(getApplicationContext())) {
            ConnectionNetwork.setNetworkMethod(SecondActivity.this);
        }
    }
}
