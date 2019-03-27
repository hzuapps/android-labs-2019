package edu.hzuapps.androidlabs.soft1714080902124;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myfirstapp.R;

public class Soft1714080902124Activity extends AppCompatActivity{

    private EditText editText;

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902124_activity);

        button = (Button) findViewById(R.id.button);
//        editText =(EditTextActivity) findViewById(R.id.edit_text);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Soft1714080902124Activity.this, EditTextActivity.class);
                startActivity(i);
            }
        });

    }

}
