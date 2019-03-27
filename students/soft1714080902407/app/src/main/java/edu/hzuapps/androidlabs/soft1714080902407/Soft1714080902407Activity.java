package edu.hzuapps.androidlabs.soft1714080902407;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Soft1714080902407Activity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902407_activity);
    }

    public void onClick(View view){
        Intent intent = new Intent(Soft1714080902407Activity.this,Detail_Activity.class);
        startActivity(intent);
    }

}
