package edu.hzuapps.androidlabs.soft17140080902101;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    private Button myButton_read;
    private Button myButton_chat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secord);

        myButton_chat = (Button)findViewById(R.id.button_chat);
        myButton_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this,ChatActivity.class);
                startActivity(intent);

            }
        });

        myButton_read = (Button)findViewById(R.id.button_read);
        myButton_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this,ReadActivity.class);
                startActivity(intent);

            }
        });

    }
}
