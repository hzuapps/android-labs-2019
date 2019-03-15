package edu.hzuapps.androidapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Soft1714080902239 extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "edu.hzuapps.androidapps.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902239_activty);
    }
        public void sendMessage (View view){
            Intent intent = new Intent(this, Soft1714080902239Activty.class);
            EditText editText = (EditText) findViewById(R.id.editText);
            String message = editText.getText().toString();
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        }
    }