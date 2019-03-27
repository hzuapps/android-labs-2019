package com.example.th;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Button_skip =(Button) findViewById(R.id.button);
        Button_skip.setOnClickListener(
                new View.OnClickListener()
                {@Override
                    public void onClick(View view)
                    { startActivity(new Intent(MainActivity.this,Main2Activity.class)); }}); }}
