package edu.hzuapps.androidlabs.com1714080901120;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.view.View;

import edu.hzuapps.androidlabs.com1714080901120.R;


public class Com1714080901120Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_1714080901120_activity);
        TextView text=findViewById(R.id.textView2);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Com1714080901120Activity.this, Com1714080901120Activity2.class);
                startActivity(intent);
            }
        });
    }

}
