package edu.hzuapps.androidlabs.soft1714080902210;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;
public class Sort1714080902210Activity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort1714080902210);
        Button btnOpen = findViewById(R.id.button);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Sort1714080902210Activity.this,Soft1714080902210Activity_Second.class));
            }
        });
    }
}
