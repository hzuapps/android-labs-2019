package edu.hzuapps.androidlabs.soft1714080902202;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Soft1714080902202MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnOpen = findViewById(R.id.button2);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("进入计时器");
                startActivity(new Intent(Soft1714080902202MainPage.this,Soft1714080902202timer.class));
            }
        });
    }
}