package edu.hzuapps.androidlabs.soft1709081602603;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class Soft1709081602603Activity_01 extends AppCompatActivity implements View.OnClickListener {
    private Button button1,button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1709081602603_activity_01);
        button1= (Button) findViewById(R.id.button1);
        button2= (Button) findViewById(R.id.button2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        Intent intent=new Intent(Soft1709081602603Activity_01.this,Soft1709081602603Activity_02.class);
        Intent intent1=new Intent(Soft1709081602603Activity_01.this,Soft1709081602603SetActivity.class);
        startActivity(intent);
        startActivity(intent1);
    }
}
