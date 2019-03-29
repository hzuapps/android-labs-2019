package edu.hzuapps.androidlabs.soft1714080902435;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Soft1714080902435Activity2 extends AppCompatActivity implements View.OnClickListener {
    private Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft17140809024352);
        button1=(Button)findViewById(R.id.Over);
        button1.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(Soft1714080902435Activity2.this,Soft1714080902435Activity.class);
        startActivity(intent);
        Soft1714080902435Activity2.this.finish();

    }
}
