package edu.hzuapps.androidlabs.soft1714080902129.electricitypaymentsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Soft1714080902129Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft17140809021292);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onStarting……");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
