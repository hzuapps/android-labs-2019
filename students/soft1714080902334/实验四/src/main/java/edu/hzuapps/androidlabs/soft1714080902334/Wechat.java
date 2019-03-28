package edu.hzuapps.androidlabs.soft1714080902334;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Wechat extends AppCompatActivity implements View.OnClickListener {
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat);
        b = (Button)findViewById(R.id.but);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==b){
            Intent intent = new Intent();
            intent.setClass(Wechat.this,Second_Activity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}