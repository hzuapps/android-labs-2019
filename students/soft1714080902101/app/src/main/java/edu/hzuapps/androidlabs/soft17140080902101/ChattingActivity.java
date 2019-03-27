package edu.hzuapps.androidlabs.soft17140080902101;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChattingActivity extends AppCompatActivity {

    private Button myButton_button_sent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);

        final EditText mychattext1=(EditText) findViewById(R.id.chattext1);
        myButton_button_sent = (Button)findViewById(R.id.button_sent);
        myButton_button_sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText=mychattext1.getText().toString();
                Toast.makeText(ChattingActivity.this, inputText, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
