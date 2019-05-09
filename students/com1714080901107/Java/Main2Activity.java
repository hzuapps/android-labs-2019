package com.example.pubgshow;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }


    public void onClick(View view){
        switch (view.getId()){
            case R.id.bt1_1:
                Intent intent1=new Intent(this,Message_of_shou.class);
                startActivity(intent1);
                break;

            case R.id.bt1_2:
                Intent intent2=new Intent(this,Message_of_shox.class);
                startActivity(intent2);
                break;

            case R.id.bt1_3:
                Intent intent3=new Intent(this,Music_of_17.class);
                startActivity(intent3);
                break;

            case R.id.bt1_4:
                Intent intent4=new Intent(this,Others_of_17.class);
                startActivity(intent4);
                break;
        }
    }






}
