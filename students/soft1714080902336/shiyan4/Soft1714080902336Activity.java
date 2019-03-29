package edu.hzuapps.androidlabs.soft1714080902336;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Soft1714080902336Activity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902336);
        button=(Button)findViewById(R.id.cs01);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Soft1714080902336Activity.this,SecondActivity.class);
                startActivity(intent);
            }
        });

        button=(Button)findViewById(R.id.cs02);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Soft1714080902336Activity.this,FriendActivity.class);
                startActivity(intent);
            }
        });
    }
}

