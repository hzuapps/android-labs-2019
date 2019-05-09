package edu.hzuapps.androidlabs.soft1714080902319;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=(Button) findViewById(R.id.button1);
        button1.setOnClickListener(listener1);

    }
    Button.OnClickListener listener1= new Button.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,main2.class);
            startActivity(intent);
        }
    };

}