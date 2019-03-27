package edu.hzuapps.androidlabs.soft1714080902419;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Soft1714080902419Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902419);

        Button GoButton = (Button) findViewById(R.id.button);
        GoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Soft1714080902419Activity.this,Soft1714080902419pikaActivity.class);
                startActivity(intent);
            }
        });
    }
}
