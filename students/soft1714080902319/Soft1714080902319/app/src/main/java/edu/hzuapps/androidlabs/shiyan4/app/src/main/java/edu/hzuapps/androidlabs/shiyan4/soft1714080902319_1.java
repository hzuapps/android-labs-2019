package edu.hzuapps.androidlabs.shiyan4;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class soft1714080902319_1 extends AppCompatActivity {
    private Button button1;
    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft1714080902319_1);
        button1=(Button) findViewById(R.id.button1);
        button2=(Button) findViewById(R.id.button2);
        button1.setOnClickListener(listener1);
        button2.setOnClickListener(listener2);
    }
    Button.OnClickListener listener1= new Button.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(soft1714080902319_1.this,soft1714080902319_2.class);
            startActivity(intent);
        }
    };
    Button.OnClickListener listener2= new Button.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(soft1714080902319_1.this,soft1714080902319_3.class);
            startActivity(intent);
        }
    };

}
