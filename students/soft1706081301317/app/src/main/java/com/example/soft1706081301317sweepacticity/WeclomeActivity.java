package com.example.soft1706081301317sweepacticity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WeclomeActivity extends AppCompatActivity {
    String text;
    String str1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weclome);
        Button button = findViewById(R.id.Go);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeclomeActivity.this,WriteActivity.class);
                startActivity(intent);
            }
        });
      SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_MULTI_PROCESS);
        String username = sharedPreferences.getString("username","");
        TextView textView = findViewById(R.id.showUserName);
        textView.setText(username);
    }

}
