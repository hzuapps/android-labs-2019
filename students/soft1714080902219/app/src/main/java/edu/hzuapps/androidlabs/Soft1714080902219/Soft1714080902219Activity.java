package edu.hzuapps.androidlabs.Soft1714080902219;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Soft1714080902219Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902219_activity);

        Button btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Soft1714080902219Activity.this,Soft1714080902219Activity3.class);
                startActivity(intent);
            }
        });

        TextView registe=findViewById(R.id.registe);
        registe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Soft1714080902219Activity.this,Soft1714080902219Activity2.class);
                startActivity(intent);
            }
        });

    }
}
