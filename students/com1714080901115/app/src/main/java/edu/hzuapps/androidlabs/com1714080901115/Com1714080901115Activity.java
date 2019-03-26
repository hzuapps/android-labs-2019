package edu.hzuapps.androidlabs.com1714080901115;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
public class Com1714080901115Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_1714080901115_activity);
        ImageView self = findViewById(R.id.imageView3);
       self.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Com1714080901115Activity.this,Com1714080901115Activity2.class));
            }
        });
        ImageView friend= findViewById(R.id.imageView4);
        friend.setOnClickListener (new View.OnClickListener() {
            public void onClick(View v1) {
                startActivity(new Intent(Com1714080901115Activity.this,Com1714080901115Activity3.class));
            }
        });

    }


}
