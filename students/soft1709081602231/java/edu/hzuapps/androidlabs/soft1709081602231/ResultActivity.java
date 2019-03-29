package edu.hzuapps.androidlabs.soft1709081602231;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private
    TextView jieguo;
    Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        Intent intent = getIntent();
        String result = intent.getStringExtra("result");
        jieguo=findViewById(R.id.jieguo);
        jieguo.setText(result);
        button1=findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent a=new Intent(ResultActivity.this,MainActivity.class);

                startActivity(a);

            }
        });
    }
}
