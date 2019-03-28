package edu.hzuapps.androidlabs.soft1714080902436;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Soft1714080902436Activity extends AppCompatActivity {

    public static final String transmit = "transmit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902436_activity);

        Button gobutton1 = (Button) findViewById(R.id.gobutton1);
        gobutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit_message1;
                edit_message1 = (EditText) findViewById(R.id.edit_message1);
                String edit_message2 = edit_message1.getText().toString();
                Intent intent1 = new Intent(Soft1714080902436Activity.this, Soft1714080902436ConfirmActivity.class);
                intent1.putExtra("transmit", edit_message2);
                startActivity(intent1);
            }
        });
        Button gobutton2 = (Button) findViewById(R.id.gobutton2);
        gobutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
    }
}
