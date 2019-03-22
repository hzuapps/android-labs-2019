package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class soft1714080902103_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902103_main);
        Button button1=(Button) findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener()
                                   {
                                       @Override
                                       public  void onClick(View v){
                                           Toast.makeText(soft1714080902103_main.this,"正在进入",
                                                   Toast.LENGTH_SHORT).show();
                                           Intent intent = new Intent(soft1714080902103_main.this,soft1714080902103_login.class);
                                           startActivity(intent);
                                       }
                                   }
        );
    }

}
