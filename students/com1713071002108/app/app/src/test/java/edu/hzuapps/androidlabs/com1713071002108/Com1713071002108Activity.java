package edu.hzuapps.androidlabs.com1713071002108;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;

public class Com1713071002108Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_1713071002108_activity);
        TextView text=findViewById(R.id.textView2);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Com1713071002108Activity.this,Com1713071002108Activity2.class);
                startActivity(intent);

            }

        });

    }

}