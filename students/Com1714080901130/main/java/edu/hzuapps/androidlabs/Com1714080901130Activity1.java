package edu.hzuapps.androidlabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.asus.myapplication.R;

public class Com1714080901130Activity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com17140809011301);
        ImageView self = (ImageView) findViewById(R.id.imageView3);
        self.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Com1714080901130Activity1.this,Com1714080901130Activity2.class));
            }
        });
        ImageView friend= (ImageView) findViewById(R.id.imageView4);
        friend.setOnClickListener (new View.OnClickListener() {
            public void onClick(View v1) {
                startActivity(new Intent(Com1714080901130Activity1.this,Com1714080901130Activity3.class));
            }
        });

    }


}



