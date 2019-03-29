package com.example.shiyan4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Newmain3 extends AppCompatActivity {
    private Button d = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymain3);
        d = (Button) findViewById(R.id.next);
        d.setOnClickListener(new Newmain3.hij());
    }
    private class hij implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Newmain3.this, MainActivity.class);
            startActivity(intent);
        }
    }
}

