package edu.hzuapps.androidlabs.com1714080901125;
import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

import com.example.myapplication.R;

public class AnotherActivity extends AppCompatActivity {
    private TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_com1714080901125);

        textView1=(TextView)findViewById(R.id.button1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnotherActivity.this,NS.class);
                startActivity(intent);
            }

        });
    }
}




