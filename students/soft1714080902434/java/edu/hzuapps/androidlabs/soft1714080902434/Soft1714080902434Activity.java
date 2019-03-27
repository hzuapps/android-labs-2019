package edu.hzuapps.androidlabs.soft1714080902434;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Soft1714080902434Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902434);

        Button myFirstButton = (Button) findViewById(R.id.button);
        myFirstButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(Soft1714080902434Activity.this,SecondActivity.class);

                startActivity(intent);
            }
        });

    }



}
