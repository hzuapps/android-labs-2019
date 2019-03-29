package edu.hzuapps.soft1714080902437test3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class No2Activity extends AppCompatActivity implements View.OnClickListener {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no2);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }


   @SuppressLint("WrongConstant")
   public boolean onTouchEvent(MotionEvent event)
   {
       Toast.makeText(this,"抢答成功",0).show();
       return super.onTouchEvent(event);
   }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(No2Activity.this, No3Activity.class);
        startActivity(intent);

    }
}


