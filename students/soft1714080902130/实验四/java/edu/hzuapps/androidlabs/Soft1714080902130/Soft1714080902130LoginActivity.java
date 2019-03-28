package edu.hzuapps.androidlabs.Soft1714080902130;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Soft1714080902130LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902130_login);
        ImageView img1 = findViewById(R.id.img2);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Soft1714080902130LoginActivity.this,
                        Soft1714080902130SecondActivity.class);
                startActivity(intent);
            }
        });
        ImageView img2 = findViewById(R.id.img3);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Soft1714080902130LoginActivity.this,
                        Soft1714080902130SecondActivity.class);
                startActivity(intent);
            }
        });
        ImageView img3 = findViewById(R.id.img4);
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Soft1714080902130LoginActivity.this,
                        Soft1714080902130SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
