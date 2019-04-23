package edu.hzuapps.androidlabs.com1714080901106;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Activity_Login extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        Button login = findViewById(R.id.login);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Login.this, Com1714080901106Activity01.class);
                startActivity(intent);
            }
        });

    }
}
