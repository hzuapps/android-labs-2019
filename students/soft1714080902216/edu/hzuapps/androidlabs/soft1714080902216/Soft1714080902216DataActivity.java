package edu.hzuapps.androidlabs.soft1714080902216;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Soft1714080902216DataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902216_data);
        Button inputLogin=(Button) findViewById(R.id.inputLogin);
        Button Comeback=(Button) findViewById(R.id.Comeback);
        inputLogin.setOnClickListener(new Soft1714080902216DataActivity.MyButton());
        Comeback.setOnClickListener(new Soft1714080902216DataActivity.MyButton());
    }

    private class MyButton implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            Intent intent;
            switch(v.getId())
            {
                case R.id.inputLogin:
                    break;
                case R.id.Comeback:
                    intent=new Intent(Soft1714080902216DataActivity.this,Soft1714080902216MainActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}
