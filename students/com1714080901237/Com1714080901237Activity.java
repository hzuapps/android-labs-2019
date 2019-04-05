package edu.huzapp.fuyouapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Com1714080901237Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com1714080901237);
    }
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Button01:
                Intent intent_1 = new Intent(this, FileStorageActivity.class);
                startActivity(intent_1);
                break;
            case R.id.Button02:
                Intent intent_2 = new Intent(this, Com1714080901237Activity03.class);
                startActivity(intent_2);
                break;
        }

    }


 }
