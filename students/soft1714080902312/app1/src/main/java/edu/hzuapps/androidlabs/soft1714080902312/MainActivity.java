package edu.hzuapps.androidlabs.soft1714080902312;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
              super.onCreate(savedInstanceState);
              setContentView(R.layout.activity_main);

              Button btn2=(Button)findViewById(R.id.btn2);
              btn2.setOnClickListener(new OnClickListener() {
                  @Override
             public void onClick(View v) {
                               finish();
                          }
        });
    }

             @Override
    public boolean onCreateOptionsMenu(Menu menu) {
             // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.activity_main, menu);
               return true;
           }
}
