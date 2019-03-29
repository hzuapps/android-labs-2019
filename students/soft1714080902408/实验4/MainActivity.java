package com.hzuandroid.soft1714080902408;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button test_btn=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._02);
        test_btn=(Button) findViewById(R.id.Button1);
        test_btn.setOnClickListener(new MainActivity.MyButtonListener());
    }
    class MyButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View view){
            Intent intent=new Intent(MainActivity.this,Mm.class);
            startActivity(intent);
        }
    }
}
