package edu.hzuapps.androidlabs.soft1714080902338.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void  click1(View view){
        Intent intent=new Intent(this,MainActivity1.class);
        startActivity(intent);
    }
    public void  click2(View view){
        Intent intent=new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
    public void  click3(View view){
        Intent intent=new Intent(this,MainActivity3.class);
        startActivity(intent);
    }
    public void  click4(View view){
        Intent intent=new Intent(this,MainActivity4.class);
        startActivity(intent);
    }


}