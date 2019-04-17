package edu.hzuapps.androidlabs.soft1714080902337;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
public class Soft1714080902337Activity extends AppCompatActivity {
    Button button = null;
    final String FILE_NAME = "test.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902337_activity);
        button = findViewById(R.id.bb);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Soft1714080902337Activity.this, Soft1714080902337Activity2.class);
                startActivity(intent);
            }
        });


    }
}
