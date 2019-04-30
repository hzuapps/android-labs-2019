package edu.hzuapps.androidlabs.soft1714080902415;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Soft1714080902415Activity extends AppCompatActivity {

    private Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=findViewById(R.id.entern);
    button1.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick (View v){
        Intent intent1 = new Intent(Soft1714080902415Activity.this, SecondActivity.class);
        startActivity(intent1);
        }
    });
        if (!ConnectionNetwork.isConn(getApplicationContext())) {
            ConnectionNetwork.setNetworkMethod(Soft1714080902415Activity.this);
        }
    }
}
