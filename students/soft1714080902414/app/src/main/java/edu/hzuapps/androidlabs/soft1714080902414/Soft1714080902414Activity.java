package edu.hzuapps.androidlabs.soft1714080902414;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Soft1714080902414Activity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902414_activity);
        button=(Button)findViewById(R.id.button);
        button1=(Button)findViewById(R.id.button1);
        button.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent =new Intent(Soft1714080902414Activity.this,Soft1714080902414Activity2.class);
        startActivity(intent);
    }


}
