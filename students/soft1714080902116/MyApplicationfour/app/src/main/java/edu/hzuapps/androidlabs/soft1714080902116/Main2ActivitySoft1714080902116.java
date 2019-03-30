package edu.hzuapps.androidlabs.soft1714080902116;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2ActivitySoft1714080902116 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_activity_soft1714080902116);
        Button button2 = (Button) findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent = new Intent( Main2ActivitySoft1714080902116.this,MainActivitySoft1714080902116.class);
                startActivity(intent);
            }

        });
    }
}
