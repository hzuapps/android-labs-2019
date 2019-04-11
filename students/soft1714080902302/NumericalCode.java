package com.example.soft1714080902302.first_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.soft1714080902302.R;
import com.example.soft1714080902302.second_activity.NumericalCodeLocal;
import com.example.soft1714080902302.second_activity.NumericalCodeNetwork;


public class NumericalCode extends AppCompatActivity {
    private Button local_button;
    private Button network_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numerical_code);


        local_button = (Button) findViewById(R.id.local_button);
        network_button = (Button) findViewById(R.id.network_button);
        local_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(NumericalCode.this, NumericalCodeLocal.class);
                startActivity(intent);
            }
        });
        network_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(NumericalCode.this, NumericalCodeNetwork.class);
                startActivity(intent1);
            }
        });
    }

}
