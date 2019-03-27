package edu.hzuapps.androidlabs.soft1709081602231;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Soft1709081602231Activity extends AppCompatActivity {

    private Button button3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1709081602231_activity);
        button3=findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i=new Intent(Soft1709081602231Activity.this,MainActivity.class);

                startActivity(i);

            }
        });
    }

}
