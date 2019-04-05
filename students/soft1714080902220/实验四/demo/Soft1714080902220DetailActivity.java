package edu.hzuapps.androidlabs.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Soft1714080902220DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
         Button button=(Button)findViewById(R.id.button2);
         button.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View v){
                 finish();
             }
         });
        }

}
