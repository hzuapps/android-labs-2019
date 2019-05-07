package edu.hzuapps.androidlabs.soft1714080902401;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Soft1714080902401_01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902401_01);



        Button NewD = (Button) findViewById(R.id.newD);
        NewD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Soft1714080902401_01Activity.this, Soft1714080902401_newActivity.class);
                startActivity(intent);
            }
        });
    }
}
