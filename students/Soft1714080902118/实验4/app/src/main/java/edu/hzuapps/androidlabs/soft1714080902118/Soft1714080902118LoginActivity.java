package edu.hzuapps.androidlabs.soft1714080902118;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Soft1714080902118LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902118_loginactivity);
        Button button2=(Button) findViewById(R.id.button2);
        Button button3=(Button) findViewById(R.id.button3);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(Soft1714080902118LoginActivity.this,"正在登陆",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Soft1714080902118LoginActivity.this,Soft1714080902118thisActivity.class);
                startActivity(intent);
            }
           }
        );

        button3.setOnClickListener(new View.OnClickListener(){
                                       @Override
                                       public void onClick(View v){
                                           Toast.makeText(Soft1714080902118LoginActivity.this,"正在登陆",Toast.LENGTH_SHORT).show();
                                           Intent intent = new Intent(Soft1714080902118LoginActivity.this,Soft1714080902118outActivity.class);
                                           startActivity(intent);
                                       }
                                   }
        );


    }


}
