package edu.hzuapps.androidlabs.soft1714080902401;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Soft1714080902401Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft_1714080902401);

    Button begin = (Button) findViewById(R.id.Begin);
        begin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Soft1714080902401Activity.this,Soft1714080902401_01Activity.class);
            startActivity(intent);
        }
    });
}

}

