package com.example.edu.hzuapps.androidlabs.soft1714080902316;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.view.View.OnClickListener;
import com.example.edu.hzuapps.androidlabs.R;

public class writng extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writng);
        Button button1 = findViewById(R.id.button2) ;
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(writng.this,Soft1714080902316.class) ;
                startActivity(intent) ;
            }
        });
    }
}
