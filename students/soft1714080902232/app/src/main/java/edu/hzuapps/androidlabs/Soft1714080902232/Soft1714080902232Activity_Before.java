package edu.hzuapps.androidlabs.Soft1714080902232;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Soft1714080902232Activity_Before extends AppCompatActivity {
    Button b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902232__activity_before);
        b1 =(Button)findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Soft1714080902232Activity_Before.this, Soft1714080902232Activity.class);
                startActivity(intent);
            }
        });
        b2 =(Button)findViewById(R.id.button2);
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Soft1714080902232Activity_Before.this,"Please look forward",Toast.LENGTH_LONG).show();
                    }
                });
        b3 =(Button)findViewById(R.id.button3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Soft1714080902232Activity_Before.this,"Please look forward",Toast.LENGTH_LONG).show();
            }
        });
    }
}
