package edu.hzuapps.androidlabs.com1709081602330;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class Com1709081602330Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_1709081602330_activity);
        ImageView self = findViewById(R.id.imageView);
        self.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Com1709081602330Activity.this,Com1709081602330Activity2.class));
            }
        });

    }


}
