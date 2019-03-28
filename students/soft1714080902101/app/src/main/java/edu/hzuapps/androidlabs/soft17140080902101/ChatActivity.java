package edu.hzuapps.androidlabs.soft17140080902101;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChatActivity extends AppCompatActivity {

    private Button myButton_chatting1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        myButton_chatting1 = (Button)findViewById(R.id.button_chatting1);
        myButton_chatting1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatActivity.this,ChattingActivity.class);
                startActivity(intent);

            }
        });

    }
}
