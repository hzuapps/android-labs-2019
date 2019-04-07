package edu.hzuapps.androidlabs.com1714080901107;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

public class Com171408091107 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com171408091107);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button button1=(Button) findViewById(R.id.Lmusic);
        Button button2=(Button) findViewById(R.id.Zj);

        button1.setOnClickListener(new MyButton());
        button2.setOnClickListener(new MyButton());
    }
        private class MyButton implements View.OnClickListener{
        @Override
            public void onClick(View v){
            switch (v.getId()){
                case R.id.Lmusic:
                  //  Log.i();
                            break;

                case R.id.Zj:
                   // Log.i();
                    break;
            }

        }

        }



    }


