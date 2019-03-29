package com.example.shiyan4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Newmain2 extends AppCompatActivity {
    private Button c = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymain2);
        c = (Button) findViewById(R.id.next);
        c.setOnClickListener(new Newmain2.efg());
    }
    private class efg implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Newmain2.this, Newmain3.class);
            startActivity(intent);
        }
    }

}