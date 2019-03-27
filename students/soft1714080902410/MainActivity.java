package com.example.soft410;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button test_btn=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test_btn=(Button) findViewById(R.id.Button1);
        test_btn.setOnClickListener(new MainActivity.MyButtonListener());
    }
    private class MyButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View view){
            Intent intent=new Intent(MainActivity.this,newmain2.class);
            startActivity(intent);
        }
    }
}
