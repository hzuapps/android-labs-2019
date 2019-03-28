package edu.hzuapps.androidlabs.soft1714080902131;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Soft1714080902131MainActivity extends AppCompatActivity {
    private ImageView btn1;
    private ImageView btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902131_main);
        btn1 = findViewById(R.id.imageView);
        btn2 = findViewById(R.id.add);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Soft1714080902131MainActivity.this,Soft1714080902131ModifyActivity.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Soft1714080902131MainActivity.this,Soft1714080902131EditActivity.class);
                startActivity(intent);
            }
        });
    }

}
