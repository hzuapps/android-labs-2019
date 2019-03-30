package edu.hzuapps.androidlabs.soft1714080902336;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class
SecondActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        button=(Button)findViewById(R.id.dtn1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondActivity.this,ScienceActivity.class);
                startActivity(intent);
            }
        });

        button=(Button)findViewById(R.id.dtn2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondActivity.this,ArtActivity.class);
                startActivity(intent);
            }
        });

        button=(Button)findViewById(R.id.dtn3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondActivity.this,LifeActivity.class);
                startActivity(intent);
            }
        });

        button=(Button)findViewById(R.id.dtn4);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondActivity.this,TvActivity.class);
                startActivity(intent);
            }
        });

        button=(Button)findViewById(R.id.dtn5);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondActivity.this,MusicActivity.class);
                startActivity(intent);
            }
        });

        button=(Button)findViewById(R.id.dtn6);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondActivity.this,CartoonActivity.class);
                startActivity(intent);
            }
        });
    }
}
