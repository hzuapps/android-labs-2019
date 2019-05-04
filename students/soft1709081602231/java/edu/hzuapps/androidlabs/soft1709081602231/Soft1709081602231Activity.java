package edu.hzuapps.androidlabs.soft1709081602231;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Soft1709081602231Activity extends AppCompatActivity {

    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;

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
        button4=findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent a = new Intent(Soft1709081602231Activity.this,FileStorageActivity.class);

                startActivity(a);

            }
        });
        button5=findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent b = new Intent(Soft1709081602231Activity.this,OpenActivity.class);

                startActivity(b);

            }
        });
        button6=findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent c = new Intent(Soft1709081602231Activity.this,OrientationActivity.class);

                startActivity(c);

            }
        });
     }

}
