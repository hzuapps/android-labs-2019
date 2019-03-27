package edu.hzuapps.androidlabs.soft1714080902128;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
public class Soft1714080902128Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902128_activity);

        ImageButton pai1 = findViewById(R.id.wendu);
        pai1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Soft1714080902128Activity.this,SecondActivity.class);
                startActivity(intent);
            }
        });

        ImageButton pai2 = findViewById(R.id.shidu);
        pai2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Soft1714080902128Activity.this,ThirdActivity.class);
                startActivity(intent);
            }
        });
    }
}