package edu.hzuapps.androidlabs.soft1714080902210;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Soft1714080902210Activity_Second extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902210__second);
        Button btnOpen = findViewById(R.id.button2);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Soft1714080902210Activity_Second.this,Soft1714080902210Activity_New.class));
            }
        });
        Button btnOpen1 = findViewById(R.id.button3);
        btnOpen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Soft1714080902210Activity_Second.this,Soft1714080902210Activity_Check.class));
            }
        });
        Button btnOpen2 = findViewById(R.id.button10);
        btnOpen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Soft1714080902210Activity_Second.this,Sort1714080902210Activity.class));
            }
        });
    }

}
