package edu.hzuapps.soft1714080902221;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Aty1 extends Activity {

    private Button btnclose;


    protected void oncreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty1);

        Button btnClose = (Button) findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View v){
              finish();
          }
        });
    }

}
