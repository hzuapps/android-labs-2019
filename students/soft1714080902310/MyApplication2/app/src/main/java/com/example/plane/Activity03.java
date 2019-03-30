package com.example.plane;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity03 extends ActionBarActivity {
    private Button purChause;
    private Button canSue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_03);
        purChause = (Button) findViewById(R.id.purChase);
        System.out.println("onCreate");
    }
}
