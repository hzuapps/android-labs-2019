package edu.hzuapps.androidlabs.soft1714080902128;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Soft1714080902128Activity extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902128_activity);
        textView=(TextView)findViewById(R.id.textview_01);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Soft1714080902128Activity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}