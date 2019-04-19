package edu.hzuapps.androidlabs.soft1714080902318;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.gdy.touhouproject.R;

public class Soft1714080902318MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902318_main);

        textView = (TextView)findViewById(R.id.button1);

        textView.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Soft1714080902318MainActivity.this, SecondActivity.class);
            startActivity(intent);
            }
        });
    }




}
