package edu.hzuapps.androidlabs.soft1714080902329;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Activity1 extends AppCompatActivity {

    EditText thing;
    EditText day;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        thing=(EditText)findViewById(R.id.editText);
        day=(EditText)findViewById(R.id.editText011);
        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String things = thing.getText().toString();
                String days = day.getText().toString();
                Bundle  bundle = new Bundle();
                Intent intent = new Intent(Activity1.this,Activity2.class);
                bundle.putString("thing",things);
                bundle.putString("day",days);
                intent.putExtra("Ces",bundle);
                startActivity(intent);


            }
        });


    }
}
