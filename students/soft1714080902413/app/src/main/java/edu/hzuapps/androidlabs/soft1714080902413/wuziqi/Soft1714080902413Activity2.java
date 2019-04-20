package edu.hzuapps.androidlabs.soft1714080902413.wuziqi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Soft1714080902413Activity2 extends AppCompatActivity implements View.OnClickListener {

    private Button button_fir,button_ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft17140809024132);
        button_fir =(Button)findViewById(R.id.btn_first);
        button_ref =(Button)findViewById(R.id.btn_refresh);

        button_fir.setOnClickListener(this);
        button_ref.setOnClickListener(this);

    }
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_first:
                Intent fir_intent =new Intent(Soft1714080902413Activity2.this,Soft1714080902413Activity.class);
                startActivity(fir_intent);break;
            case R.id.btn_refresh:
                Intent intent =new Intent(Soft1714080902413Activity2.this,Soft1714080902413Activity2.class);
                startActivity(intent);break;
        }
    }
}