package com.example.ll;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button3 = (Button) findViewById(R.id.button1);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View LL) {
                Intent intent = new Intent(MainActivity.this, LLActivity.class);
                startActivity(intent);
            }
        });
    }

}









