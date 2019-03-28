package edu.hzuapps.androidlabs.soft1714080902232;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Soft1714080902232Activity_Start extends AppCompatActivity {
    ImageButton imgButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902232_activity_start);
        imgButton =(ImageButton)findViewById(R.id.imageButton);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Soft1714080902232Activity_Start.this, Soft1714080902232Activity_Before.class);
                startActivity(intent);
            }
        });
    }
}
