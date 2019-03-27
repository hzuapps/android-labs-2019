package edu.hzuapps.androidlabs.com1714080901119;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;
public class Com1714080901119Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_1714080901119_activity);
        TextView denglu = findViewById(R.id.cai);
        denglu.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Com1714080901119Activity.this,Com1714080901119Activity2.class));
            }
        });
    }
}
