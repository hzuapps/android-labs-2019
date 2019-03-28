package com.example.shiyan4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button a = null;
    private Button b = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a = (Button) findViewById(R.id.next);
        a.setOnClickListener(new MainActivity.abc());
        b = (Button) findViewById(R.id.last);
        b.setOnClickListener(new MainActivity.cba());
    }

    private class abc implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, Newmain2.class);
            startActivity(intent);
        }
    }
    private class cba implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, Newmain3.class);
            startActivity(intent);
        }
    }

}
