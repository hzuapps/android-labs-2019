package edu.hzuapps.androidlabs.soft1714080902434;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myFirstButton = (Button) findViewById(R.id.buttona);
        myFirstButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(MainActivity.this,Soft1714080902434Activity.class);

                startActivity(intent);
            }
        });
    }
}
