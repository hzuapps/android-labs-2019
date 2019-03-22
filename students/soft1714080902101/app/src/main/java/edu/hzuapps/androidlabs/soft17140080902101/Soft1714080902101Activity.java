package edu.hzuapps.androidlabs.soft17140080902101;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Soft1714080902101Activity extends AppCompatActivity {

    private Button myButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902101_activity);

        myButton = (Button)findViewById(R.id.button1);
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(Soft1714080902101Activity.this,SecordActivity.class);
                    startActivity(intent);

            }
        });

    }
}
