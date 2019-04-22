package edu.hzuapps.androidlabs.soft1714080902120;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class Soft1714080902120NetCheckActivity extends AppCompatActivity {

    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_check);
        btn1 = (Button) findViewById(R.id.testNetwrkBtn);
        btn1.setOnClickListener(new Soft1714080902120Btn1Listener(this));
    }


}

