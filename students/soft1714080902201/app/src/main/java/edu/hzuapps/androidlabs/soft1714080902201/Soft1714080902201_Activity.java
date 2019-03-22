package edu.hzuapps.androidlabs.soft1714080902201;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Soft1714080902201_Activity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "edu.hzuapps.androidlabs.soft1714080902201.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902201);
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, Soft1714080902201_Activity_2.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void sendMessage2 (View view){
        Intent intent = new Intent(this, Soft1714080902201_Activity_3.class);
        startActivity(intent);
    }

    public void sendMessage3 (View view){
        Intent intent = new Intent(this, Soft1714080902201_Activity_3.class);
        startActivity(intent);
    }

}
