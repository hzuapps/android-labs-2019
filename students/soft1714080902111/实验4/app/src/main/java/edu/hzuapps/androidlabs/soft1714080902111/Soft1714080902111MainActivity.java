package edu.hzuapps.androidlabs.soft1714080902111;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Soft1714080902111MainActivity extends AppCompatActivity {

    private TextView mtv1;
    private Button mBtnTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft1714080902111mainactivity);//alayout的mainactivity
        mtv1 = findViewById(R.id.ttv2);
        mBtnTextView = findViewById(R.id.btnTextView1);
        mBtnTextView = findViewById(R.id.btnTextView2);
        mBtnTextView = findViewById(R.id.btnTextView3);
        mtv1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        mtv1.getPaint().setAntiAlias(true);
    }
        public void onClick1(View view) {
            Intent intent = new Intent(Soft1714080902111MainActivity.this,
                    Soft1714080902111Login .class);
            startActivity(intent);
        }
          public void onClick2(View view) {
        Intent intent = new Intent(Soft1714080902111MainActivity.this,
                Soft1714080902111TestActivity21.class);
        startActivity(intent);
    }
         public void onClick3(View view) {
             Intent intent = new Intent(Soft1714080902111MainActivity.this, Soft1714080902111TestActivity31.class);
             startActivity(intent);
         }
}
