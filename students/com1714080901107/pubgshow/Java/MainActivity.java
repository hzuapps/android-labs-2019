package com.example.pubgshow;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
        public void onClick(View view){
            switch (view.getId()){
                case R.id.bt1:
                Intent intent1=new Intent(this,Main2Activity.class);
                startActivity(intent1);
                break;

                case R.id.bt2:
                    Intent intent2=new Intent(this,Main3Activity.class);
                    startActivity(intent2);
                    break;

                case R.id.bt3:
                    Intent intent3=new Intent(this,Main4Activity.class);
                    startActivity(intent3);
                    break;
            }
        }



   }

