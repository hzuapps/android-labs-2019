package edu.hzuapps.androidlabs.soft1714080902122;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class myidea1 extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myidea1_activity);

        button = (Button) findViewById(R.id.button_5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myidea1.this, SaveActivity.class);
                startActivity(intent);            }
        });

    }
}
