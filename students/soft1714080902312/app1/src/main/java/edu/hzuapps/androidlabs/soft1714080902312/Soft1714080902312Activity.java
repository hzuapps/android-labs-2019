package edu.hzuapps.androidlabs.soft1714080902312;
import android.view.Menu;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Soft1714080902312Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902312_activity);

        Button btn1=(Button)findViewById(R.id.btn1);

        btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(Soft1714080902312Activity.this, MainActivity.class);
                    startActivity(intent);

            }
        });
    }
    @Override
      public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
               getMenuInflater().inflate(R.menu.soft_1714080902312_activity, menu);
                return true;
            }
}
