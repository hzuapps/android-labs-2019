package edu.hzuapps.androidlabs.forthapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void playButtonClick(View view){
        Toast.makeText(MainActivity.this,"游戏马上开始，正在加载...",Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this,"游戏开始",Toast.LENGTH_LONG).show();
    }

}
