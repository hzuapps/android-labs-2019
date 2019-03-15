package edu.huzapp.fuyouapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Com1714080901237Activity03 extends AppCompatActivity {
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        //setContentView(R.layout.activity03_com1714080901237);
    }


    public void click(View view) {
        Intent intent = new Intent(this, Com1714080901237Activity.class);
        startActivity(intent);
    }
}
