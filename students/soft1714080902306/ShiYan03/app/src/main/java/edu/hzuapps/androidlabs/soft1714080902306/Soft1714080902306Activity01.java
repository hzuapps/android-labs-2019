package edu.hzuapps.androidlabs.soft1714080902306;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import edu.hzuapps.androidlabs.R;

public class Soft1714080902306Activity01 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902306_activity01);

        TextView btnOpen =  findViewById(R.id.Button_1);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Soft1714080902306Activity01.this,Soft1714080902306Activity02.class));
            }
        });
    }

}