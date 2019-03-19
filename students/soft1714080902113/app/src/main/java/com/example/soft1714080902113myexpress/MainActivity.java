package com.example.soft1714080902113myexpress;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.nio.InvalidMarkException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button) findViewById(R.id.start_button);
        imageView=(ImageView) findViewById(R.id.head_view);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(MainActivity.this,MyInfo.class);
        startActivity(intent);
    }
}
