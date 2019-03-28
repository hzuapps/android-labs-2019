package edu.hzuapps.androidlabs.soft1714080902424;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Soft1714080902424Activity extends Activity{

    private Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902424_activity);
        button1 = findViewById(R.id.login);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(edu.hzuapps.androidlabs.soft1714080902424.Soft1714080902424Activity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
