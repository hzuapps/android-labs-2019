package edu.hzuapps.androidlabs.soft1714080902216;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Soft1714080902216MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Record=(Button) findViewById(R.id.button1);
        ImageButton User= (ImageButton) findViewById(R.id.imageButton);
        Button Data=(Button) findViewById(R.id.button2);
        Record.setOnClickListener(new MyButton());
        User.setOnClickListener(new MyButton());
        Data.setOnClickListener(new MyButton());
    }

    private class MyButton implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            Intent intent;
            switch(v.getId())
            {
                case R.id.button1:
                    intent=new Intent(Soft1714080902216MainActivity.this,Soft1714080902216RecordActivity.class);
                    startActivity(intent);
                    break;
                case R.id.button2:
                    intent=new Intent(Soft1714080902216MainActivity.this,Soft1714080902216DataActivity.class);
                    startActivity(intent);
                    break;
                case R.id.imageButton:
                    intent=new Intent(Soft1714080902216MainActivity.this,Soft1714080902216UserActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }


}
