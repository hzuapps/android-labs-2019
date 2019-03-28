package edu.hzuapps.androidlabs.soft1714080902127;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Soft1714080902127MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902127_mainactivity);

        ImageButton button1 = (ImageButton) findViewById(R.id.image_button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Soft1714080902127MainActivity.this, Soft1714080902127Show1Activity.class);
                startActivity(intent);
            }
        });

    }
}
