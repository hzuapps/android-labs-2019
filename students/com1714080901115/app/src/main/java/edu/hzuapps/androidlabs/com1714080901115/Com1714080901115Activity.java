package edu.hzuapps.androidlabs.com1714080901115;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
public class Com1714080901115Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_1714080901115_activity);
        Button denglu = findViewById(R.id.button1);
       denglu.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Com1714080901115Activity.this,Com1714080901115Activity2.class));
            }
        });
    }
}
