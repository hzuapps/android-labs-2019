package edu.hzuapps.androidlabs.soft1714080902331;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class Soft1714080902331Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902331_activity);
        ImageView btnOpen = (ImageView) findViewById(R.id.imageButton);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Soft1714080902331Activity.this,Soft1714080902331Activity2.class));
            }
        });

        ImageView btnOpen2 = (ImageView) findViewById(R.id.imageButton2);
        btnOpen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Soft1714080902331Activity.this,Soft1714080902331Activity3.class));
            }
        });
    }

}
