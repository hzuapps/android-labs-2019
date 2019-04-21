package edu.hzuapps.androidlabs.soft1714080902413.wuziqi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Soft1714080902413Activity extends AppCompatActivity implements View.OnClickListener {

    private Button button_sta,button_text,button_record,button_gamer,button_egg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902413);
        button_sta=(Button)findViewById(R.id.btn_start);
        button_text=(Button)findViewById(R.id.btn_text);
        button_record=(Button)findViewById(R.id.btn_record);
        button_gamer=(Button)findViewById(R.id.btn_gamer);
        button_egg=(Button)findViewById(R.id.btn_egg);
        button_sta.setOnClickListener(this);
        button_text.setOnClickListener(this);
        button_record.setOnClickListener(this);
        button_gamer.setOnClickListener(this);
        button_egg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start:
                Intent sta_intent=new Intent(Soft1714080902413Activity.this,Soft1714080902413Activity4.class);
                startActivity(sta_intent);break;
            case R.id.btn_text:
                Intent text_intent=new Intent(Soft1714080902413Activity.this,Soft1714080902413Activity3.class);
                startActivity(text_intent);break;
            case R.id.btn_record:
                Intent rec_intent=new Intent(Soft1714080902413Activity.this,Soft1714080902413Activity2.class);
                startActivity(rec_intent);break;
            case R.id.btn_gamer:
                Intent gamer_intent=new Intent(Soft1714080902413Activity.this,Soft1714080902413Activity5.class);
                startActivity(gamer_intent);break;
            case R.id.btn_egg:
                Intent egg_intent=new Intent(Soft1714080902413Activity.this,Soft1714080902413Activity6.class);
                startActivity(egg_intent);break;
        }
    }
}
