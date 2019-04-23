package edu.hzuapps.androidlabs.soft1714080902414;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WordtestActivity extends AppCompatActivity {

    private Button button;
    private Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordtest);
        button=(Button)findViewById(R.id.button);
        button1=(Button)findViewById(R.id.button1);
        button.setOnClickListener(new MyButton());
        button1.setOnClickListener(new MyButton());
    }

    public class MyButton implements View.OnClickListener{
        @Override
        public void onClick(View v) {


            switch (v.getId())
            {

                case R.id.button:   Intent intent1 =new Intent(WordtestActivity.this, WordtestanswerActivity.class);
                                        startActivity(intent1);break;

                case R.id.button1:   Intent intent =new Intent(WordtestActivity.this, Soft1714080902414Activity.class);
                                        startActivity(intent);break;
            }

        }
    }





}
