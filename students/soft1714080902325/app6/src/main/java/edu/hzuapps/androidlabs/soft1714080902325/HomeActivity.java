package edu.hzuapps.androidlabs.soft1714080902325;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    private Button button01;
    private Button button02;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    button01=(Button)findViewById(R.id.button1);
        button01.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(HomeActivity.this, CunchuActivity.class);
            startActivity(intent);
        }
    });
    button02=(Button)findViewById(R.id.button2);
    button02.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(HomeActivity.this, MusicActivity.class);
            startActivity(intent);
        }
    });
    }
}


