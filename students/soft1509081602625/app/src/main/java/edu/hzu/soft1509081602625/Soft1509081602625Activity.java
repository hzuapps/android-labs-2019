package edu.hzu.soft1509081602625;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Soft1509081602625Activity extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1509081602625);


        textView =(TextView)findViewById(R.id.aodi);

	        textView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(Soft1509081602625Activity.this,CarInfo.class);
            startActivity(intent);
             }
        });
    }
}
