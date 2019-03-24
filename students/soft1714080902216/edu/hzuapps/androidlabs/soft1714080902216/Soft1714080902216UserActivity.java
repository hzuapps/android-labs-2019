package edu.hzuapps.androidlabs.soft1714080902216;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Soft1714080902216UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902216_user);
        Button denglu=(Button) findViewById(R.id.denglu);
        Button zhuce= (Button) findViewById(R.id.zhuce);
        Button back=(Button) findViewById(R.id.back);
        denglu.setOnClickListener(new Soft1714080902216UserActivity.myButton());
        zhuce.setOnClickListener(new Soft1714080902216UserActivity.myButton());
        back.setOnClickListener(new Soft1714080902216UserActivity.myButton());
    }

    private class myButton implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            Intent intent;
            switch(v.getId())
            {
                case R.id.button1:
                    break;
                case R.id.button2:
                    break;
                case R.id.imageButton:
                    intent=new Intent(Soft1714080902216UserActivity.this,Soft1714080902216MainActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}
