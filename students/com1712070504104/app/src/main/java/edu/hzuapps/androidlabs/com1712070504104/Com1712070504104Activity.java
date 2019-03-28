package edu.hzuapps.androidlabs.com1712070504104;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;



public class Com1712070504104Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_1712070504104_activity);
        TextView text=findViewById(R.id.app_name);
        text.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Com1712070504104Activity.this,Com1712070504104Activity2.class);
                startActivity(intent);
            }
        });

    }

}