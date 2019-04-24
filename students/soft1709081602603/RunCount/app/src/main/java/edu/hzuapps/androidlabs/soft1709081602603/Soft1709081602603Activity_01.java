package edu.hzuapps.androidlabs.soft1709081602603;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import edu.hzuapps.androidlabs.soft1709081602603.location.LocationActivity;

public class Soft1709081602603Activity_01 extends AppCompatActivity implements View.OnClickListener {
    private Button button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1709081602603_activity_01);
        button4= (Button) findViewById(R.id.button4);
        button4.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        Intent intent=new Intent(Soft1709081602603Activity_01.this, LocationActivity.class);
        startActivity(intent);
    }
}
