package edu.hzuapps.androidlabs.soft1714080902216;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Soft1714080902216MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Record(View view)
    {
        Intent intent=new Intent(this,Soft1714080902216RecordActivity.class);
        startActivity(intent);
    }

    public void User(View view)
    {
        Intent intent=new Intent(this,Soft1714080902216UserActivity.class);
        startActivity(intent);
    }

    public void Data(View view)
    {
        Intent intent=new Intent(this,Soft1714080902216DataActivity.class);
        startActivity(intent);
    }
}
