package edu.hzuapps.androidlabs.com1714080901140;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button01=(Button)findViewById(R.id.button1);
        Button button03=(Button)findViewById(R.id.button2);
        button01.setOnClickListener(new View.OnClickListener()
      {
              public void onClick(View view){
                    Intent intent = new Intent(MainActivity.this,com1714080901140Activity.class);
                    startActivity(intent);
                }
       );

        button03.setOnClickListener(new View.OnClickListener()
          {
              public void onClick(View view){
              Intent intent = new Intent(MainActivity.this,updateActivity.class);
              startActivity(intent);
          }
       )
}
